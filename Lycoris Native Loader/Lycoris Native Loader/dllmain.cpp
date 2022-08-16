#include "windows.h"
#include "main.h"
#include "jni.h"
#include "jvmti.h"
#include "classes.h"
#include <fstream>
#include <iostream>
#include "jni.h"
#include "jvmti.h"

using namespace std;


jsize nVMs;
JavaVM** buffer = new JavaVM * [nVMs];
JavaVM* jvm;
JNIEnv* Env = NULL;
jvmtiEnv* jvmti = NULL;


static bool loaded;

jrawMonitorID vmtrace_lock;
jlong start_time;

/* utilities */

static jmethodID getMethod = NULL, invokeMethod = NULL;
static jclass nativeAccesses = NULL, reflections = NULL;
static string* names = NULL;




void Jvmtiinit() {
	cout << "[Lycoris Agent] Setting up JVMTI!" << endl;
	int error;
	jvmti->CreateRawMonitor("vmtrace_lock", &vmtrace_lock);
	jvmti->GetTime(&start_time);

	if (Env->ExceptionOccurred()) {
		Env->ExceptionDescribe();
		return;
	}

	jvmtiCapabilities capabilities;
	error = jvmti->GetPotentialCapabilities(&capabilities);
	capabilities.can_generate_all_class_hook_events = 1;
	capabilities.can_retransform_any_class = 1;
	capabilities.can_retransform_classes = 1;
	error = jvmti->AddCapabilities(&capabilities);
	if (Env->ExceptionOccurred()) {
		cout << "[Lycoris Agent] JVMTI Setup Failed!" << endl;
		Env->ExceptionDescribe();
		return;
	}

	cout << "[Lycoris Agent] JVMTI was set!" << endl;
}

DWORD WINAPI MainThread(CONST LPVOID lpParam)
{
	//MessageBoxA(NULL, "Injection-Thread Started! Code By Nplus", "NextAgent v4", MB_OK);

	//MessageBoxA(NULL, "Starting injecting", "nextAgentDebug", MB_OK | MB_ICONINFORMATION);
	HMODULE jvmDll = GetModuleHandleA("jvm.dll");
	if (!jvmDll)
	{
		DWORD lastError = GetLastError();
		MessageBoxA(NULL, "Error: 0x00000001", "Lycoris Loader", MB_OK | MB_ICONERROR);
		//OutputLastError(lastError);
		ExitThread(0);
	}

	FARPROC getJvmsVoidPtr = GetProcAddress(jvmDll, "JNI_GetCreatedJavaVMs");

	if (!getJvmsVoidPtr)
	{
		DWORD lastError = GetLastError();
		MessageBoxA(NULL, "Error: 0x00000002", "Lycoris Loader", MB_OK | MB_ICONERROR);
		ExitThread(0);
	}

	typedef jint(JNICALL* GetCreatedJavaVMs)(JavaVM**, jsize, jsize*);

	GetCreatedJavaVMs jni_GetCreatedJavaVMs = (GetCreatedJavaVMs)getJvmsVoidPtr;

	jni_GetCreatedJavaVMs(NULL, 0, &nVMs);

	jni_GetCreatedJavaVMs(buffer, nVMs, &nVMs);
	if (nVMs == 0)
	{
		MessageBoxA(NULL, "Error: NoJVMsFound", "Lycoris Loader", MB_OK | MB_ICONERROR);
		ExitThread(0);
	}

	if (nVMs > 0)
	{
		for (jsize i = 0; i < nVMs; i++)
		{
			jvm = buffer[i];

			jvm->AttachCurrentThread((void**)(&Env), 0);

			jvm->GetEnv((void**)(&Env), JNI_VERSION_1_8);
			if (!Env)
			{
				MessageBoxA(NULL, "Error: NoJniEnv", "Lycoris Loader", MB_OK | MB_ICONERROR);
				jvm->DetachCurrentThread();
				break;
			}
			jclass classLoaderClazz = NULL;

			jvm->GetEnv((void**)&jvmti, JVMTI_VERSION_1_0);

			if (!jvmti)
			{
				MessageBoxA(NULL, "Error: NoJvmtiEnv", "Lycoris Loader", MB_OK | MB_ICONERROR);
				jvm->DetachCurrentThread();
				break;
			}

			Jvmtiinit();


			jvm->DetachCurrentThread();
		}
	}
	ExitThread(0);
}



jbyteArray asByteArray(JNIEnv* env, const unsigned char* buf, int len) {
	jbyteArray array = env->NewByteArray(len);
	env->SetByteArrayRegion(array, 0, len, (const jbyte*)buf);
	return array;
}

unsigned char* asUnsignedCharArray(JNIEnv* env, jbyteArray array) {
	int len = env->GetArrayLength(array);
	unsigned char* buf = new unsigned char[len];
	env->GetByteArrayRegion(array, 0, len, reinterpret_cast<jbyte*>(buf));
	return buf;
}

static int index, classesToRedefine;
static int redefine(jvmtiEnv* jvmti, jvmtiClassDefinition* class_def) {
	if (!jvmti->RedefineClasses(classesToRedefine, class_def))
		return 0;

	return 1;
}

jobjectArray asClassArray(JNIEnv* env, jclass* buf, int len) {
	jobjectArray array = env->NewObjectArray(len, env->FindClass("java/lang/Class"), NULL);

	for (int i = 0; i < len; i++) {
		env->SetObjectArrayElement(array, i, buf[i]);
	}

	return array;
}

jclass findClass(JNIEnv* env, jvmtiEnv* jvmti, const char* name) {
	jclass* loadedClasses;
	jint loadedClassesCount = 0;
	jvmti->GetLoadedClasses(&loadedClassesCount, &loadedClasses);

	jclass findClass = NULL;
	for (jint i = 0; i < loadedClassesCount; i++)
	{
		char* signature;
		jvmti->GetClassSignature(loadedClasses[i], &signature, NULL);
		if (!strcmp(signature, name))
		{
			findClass = loadedClasses[i];
			return findClass;
		}
	}
	return NULL;
}
jclass clazztigger;
void JNICALL classTransformerHook
(
	jvmtiEnv* jvmti,
	JNIEnv* env,
	jclass class_being_redefined,
	jobject loader, const char* name,
	jobject protection_domain,
	jint data_len,
	const unsigned char* data,
	jint* new_data_len,
	unsigned char** new_data
) {

	jvmti->Allocate(data_len, new_data);
	jclass transformerClass = findClass(env,jvmti,"Lrbq/wtf/lycoris/client/transformer/TransformManager;");
	jmethodID transfrom = env->GetStaticMethodID(transformerClass, "onTransform", "(Ljava/lang/Class;[B)[B");
	jclass clazzt = class_being_redefined;
	jbyteArray classdata = asByteArray(env, data, data_len);
	jbyteArray transformedData = env->NewByteArray(0);
	clazztigger = clazzt;
	//cout << "0" << endl;
	if (!class_being_redefined) {
		*new_data_len = data_len;
		memcpy(*new_data, data, data_len);
		return;
	}
	if (!clazzt || !classdata || !transformerClass || !transfrom) {
		//cout << "Unknown transform" << endl;
		*new_data_len = data_len;
		memcpy(*new_data, data, data_len);
		return;
	}
	transformedData = (jbyteArray)env->CallStaticObjectMethod(transformerClass, transfrom, clazzt, classdata);
	if (!transformedData) {
		//cout << "Unknown transform" << endl;
		*new_data_len = data_len;
		memcpy(*new_data, data, data_len);
		return;
	}

	unsigned char* newChars = asUnsignedCharArray(env, transformedData);
	const jint newLength = (jint)env->GetArrayLength(transformedData);
	jvmti->Allocate(newLength, new_data);
	*new_data_len = newLength;
	memcpy(*new_data, newChars, newLength);
}


extern "C" __declspec(dllexport) jobjectArray Java_rbq_wtf_lycoris_agent_instrument_impl_InstrumentationImpl_getAllLoadedClasses(JNIEnv* env) {
	//cout << "getAllLoadedClasses trigger" << endl;
	JNIEnv* jnienv = env;
	jint err = 0;
	jclass* jvmClasses;
	jint classCount;

	err = (jint)jvmti->GetLoadedClasses(&classCount, &jvmClasses);
	if (err) {

		return asClassArray(jnienv, jvmClasses, classCount);
	}

	return asClassArray(jnienv, jvmClasses, classCount);
}
extern "C" __declspec(dllexport)  jobjectArray JNICALL Java_rbq_wtf_lycoris_agent_instrument_impl_InstrumentationImpl_getLoadedClasses(JNIEnv* env, jobject instrumentationInstance, jobject classLoader) {
	jclass* jvmClasses;
	jint classCount;
	if (!jvmti)
	{
		env->GetJavaVM(&jvm);
		jvm->GetEnv((void**)&jvmti, JVMTI_VERSION_1_0);
	}
	const jint err = jvmti->GetClassLoaderClasses(classLoader, &classCount, &jvmClasses);
	if (err) {
		cout << "Unable to get loaded classes at runtime!" << endl;
		return asClassArray(env, jvmClasses, classCount);
	}

	return asClassArray(env, jvmClasses, classCount);
}

extern "C" __declspec(dllexport)  jint JNICALL Java_rbq_wtf_lycoris_agent_instrument_impl_InstrumentationImpl_retransformClasses(JNIEnv* env, jobject instrumentationInstance, jobjectArray classes) {
	//initializeJvmti();
	if (!jvmti)
	{
		env->GetJavaVM(&jvm);
		jvm->GetEnv((void**)&jvmti, JVMTI_VERSION_1_0);
	}
	int i;
	jvmtiCapabilities capabilities;
	int error = jvmti->GetPotentialCapabilities(&capabilities);
	capabilities.can_generate_all_class_hook_events = 1;
	capabilities.can_retransform_any_class = 1;
	capabilities.can_retransform_classes = 1;
	i =jvmti->AddCapabilities(&capabilities);


	const jclass stringCls = env->FindClass("java/lang/String");
	const jmethodID stringReplace = env->GetMethodID(stringCls, "replace", "(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;");
	const jstring dotString = env->NewStringUTF(".");
	const jstring slashString = env->NewStringUTF("/");

	const jclass javaClass = env->FindClass("java/lang/Class");
	const jmethodID getName = env->GetMethodID(javaClass, "getName", "()Ljava/lang/String;");

	jint size = env->GetArrayLength(classes);
	jclass* jvmClasses = new jclass[size];
	names = new string[size];

	for (int index = 0; index < size; index++) {
		jvmClasses[index] = (jclass)env->GetObjectArrayElement(classes, index);
		names[index] = env->GetStringUTFChars((jstring)env->CallObjectMethod((jstring)env->CallObjectMethod(jvmClasses[index], getName), stringReplace, dotString, slashString), JNI_FALSE);
	}
	//cout << "[LycorisAgent] Lycoris Agent Loaded! Version 1.0.0" << endl;
	cout << "[Lycoris Agent] Retransforming " << "classes.. Count: " << size << endl;

	//cout << "[Agent] Retransforming " << size << " classes.." << endl;

	loaded = true;
	//cout << "1" << endl;
	classesToRedefine = size;
	//cout << "2" << endl;
	jvmtiEventCallbacks callbacks ;
	//cout << "3" << endl;
	callbacks.ClassFileLoadHook = classTransformerHook;
	//cout << "4" << endl;
	jvmti->SetEventCallbacks(&callbacks, sizeof(callbacks));
	//cout << "5" << endl;
	jvmti->SetEventNotificationMode(JVMTI_ENABLE, JVMTI_EVENT_CLASS_FILE_LOAD_HOOK, NULL);
	//cout << "6" << endl;
	
	i = jvmti->RetransformClasses(size, jvmClasses);
	char* sig;
	jvmti->GetClassSignature(clazztigger, &sig, NULL);
	cout << "Class " << sig << " Error" << i;
	//MessageBoxA(NULL, "Error: " + i, "Lycoris Loader", MB_OK | MB_ICONERROR);
	//cout << "error" << i  << endl;
	index = 0;
	//cout << "8" << endl;
	//loaded = false;
	//cout << "9" << endl;
	//jvmti->SetEventNotificationMode(JVMTI_DISABLE, JVMTI_EVENT_CLASS_FILE_LOAD_HOOK, NULL);
	//cout << "10" << endl;
	cout << "[Lycoris Agent] Retransformed " << size << " classes." << endl;
	return i;
	//cout << "[Agent] Retransformed " << size << " classes." << endl;
}

BOOL APIENTRY DllMain(HMODULE hModule, DWORD dwReason, LPVOID lpReserved) {
	DisableThreadLibraryCalls(hModule);

	switch (dwReason)
	{
	case DLL_PROCESS_ATTACH:
		HANDLE hdlWrite = GetStdHandle(STD_OUTPUT_HANDLE);
		freopen("CONOUT$", "w+t", stdout);
		freopen("CONIN$", "r+t", stdin);
		CreateThread(NULL, 0, (LPTHREAD_START_ROUTINE)&MainThread, NULL, 0, NULL);
		break;
	}

	return TRUE;
}
