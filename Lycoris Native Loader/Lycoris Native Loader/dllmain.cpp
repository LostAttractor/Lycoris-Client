#include "rbq_wtf_lycoris_agent_instrument_impl_InstrumentationImpl.h"
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
	cout << "[LycorisAgent] Setting up JVMTI!" << endl;
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
	capabilities.can_redefine_any_class = 1;
	capabilities.can_redefine_classes = 1;
	error = jvmti->AddCapabilities(&capabilities);
	if (Env->ExceptionOccurred()) {
		cout << "[LycorisAgent] JVMTI Setup Failed!" << endl;
		Env->ExceptionDescribe();
		return;
	}

	cout << "[LycorisAgent] JVMTI was set!" << endl;
}

DWORD WINAPI MainThread(CONST LPVOID lpParam)
{
	//MessageBoxA(NULL, "Injection-Thread Started! Code By Nplus", "NextAgent v4", MB_OK);

	//MessageBoxA(NULL, "Starting injecting", "nextAgentDebug", MB_OK | MB_ICONINFORMATION);
	HMODULE jvmDll = GetModuleHandleA("jvm.dll");
	if (!jvmDll)
	{
		DWORD lastError = GetLastError();
		MessageBoxA(NULL, "Error: 0x00000001", "nextAgentDebug", MB_OK | MB_ICONERROR);
		//OutputLastError(lastError);
		ExitThread(0);
	}

	FARPROC getJvmsVoidPtr = GetProcAddress(jvmDll, "JNI_GetCreatedJavaVMs");

	if (!getJvmsVoidPtr)
	{
		DWORD lastError = GetLastError();
		MessageBoxA(NULL, "Error: 0x00000002", "nextAgentDebug", MB_OK | MB_ICONERROR);
		ExitThread(0);
	}

	typedef jint(JNICALL* GetCreatedJavaVMs)(JavaVM**, jsize, jsize*);

	GetCreatedJavaVMs jni_GetCreatedJavaVMs = (GetCreatedJavaVMs)getJvmsVoidPtr;

	jni_GetCreatedJavaVMs(NULL, 0, &nVMs);

	jni_GetCreatedJavaVMs(buffer, nVMs, &nVMs);
	if (nVMs == 0)
	{
		MessageBoxA(NULL, "Error: NoJVMsFound", "nextAgentDebug", MB_OK | MB_ICONERROR);
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
				MessageBoxA(NULL, "Error: NoJniEnv", "nextAgentDebug", MB_OK | MB_ICONERROR);
				jvm->DetachCurrentThread();
				break;
			}
			jclass classLoaderClazz = NULL;

			jvm->GetEnv((void**)&jvmti, JVMTI_VERSION_1_0);

			if (!jvmti)
			{
				MessageBoxA(NULL, "Error: NoJvmtiEnv", "nextAgentDebug", MB_OK | MB_ICONERROR);
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
	//cout << "Event Trigged" << endl;
	if (name == NULL)
		return;
	//cout << "a" << endl;
	if (!loaded) {
		jvmti->Allocate(data_len, new_data);
		*new_data_len = data_len;
		memcpy(*new_data, data, data_len);
		//cout << "b" << endl;
	}

	else {
		//cout << "c" << endl;
		const jclass stringCls = env->FindClass("java/lang/String");
		const jmethodID startsWith = env->GetMethodID(stringCls, "startsWith", "(Ljava/lang/String;)Z");
		const jstring javaClassName = env->NewStringUTF(name),
			javaIndexName = env->NewStringUTF(names[index].c_str());
		//cout << "d" << endl;
		if (!env->CallBooleanMethod(javaIndexName, startsWith, javaClassName)) {
			jvmti->Allocate(data_len, new_data);
			*new_data_len = data_len;
			memcpy(*new_data, data, data_len);
			return;
		}
		Env = env;
		//cout << "e" << endl;
		jvmti->Allocate(data_len, new_data);
		*new_data_len = data_len;
		memcpy(*new_data, data, data_len);
		//cout << "aa" << endl;
		reflections = Env->FindClass("rbq/wtf/lycoris/agent/utils/Reflections");
		//cout << "bb" << endl;
		nativeAccesses = Env->FindClass("rbq/wtf/lycoris/agent/Access");
		//	cout << "cc" << endl;
		getMethod = Env->GetStaticMethodID(reflections, "getMethod", "(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Method;");
		//cout << "dd" << endl;
		invokeMethod = Env->GetStaticMethodID(reflections, "invokeMethod", "(Ljava/lang/reflect/Method;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;");
		//cout << "ee" << endl;


		//cout << "f" << endl;
		const jobject method = env->CallStaticObjectMethod(reflections, getMethod, nativeAccesses, env->NewStringUTF("transformClass"));
		//cout << "f1" << endl;
		const jclass arrayCls = env->FindClass("java/lang/reflect/Array");
		//cout << "f2" << endl;
		const jclass object = env->FindClass("java/lang/Object");
		//cout << "f3" << endl;
		const jmethodID newInstance = env->GetStaticMethodID(arrayCls, "newInstance", "(Ljava/lang/Class;I)Ljava/lang/Object;");
		//cout << "f4" << endl;
		const jobjectArray newArray = (jobjectArray)env->CallStaticObjectMethod(arrayCls, newInstance, object, (jint)5);
		//cout << "f5" << endl;
		const jbyteArray plainBytes = asByteArray(env, *new_data, *new_data_len);
		//cout << "f6" << endl;
		const jbyteArray byteArray = env->NewByteArray(0);
		//cout << "f7" << endl;
		env->SetObjectArrayElement(newArray, 0, loader);
		//cout << "f8" << endl;
		env->SetObjectArrayElement(newArray, 1, env->NewStringUTF(name));
		//cout << "f9" << endl;
		env->SetObjectArrayElement(newArray, 2, class_being_redefined);
		//cout << "f10" << endl;
		env->SetObjectArrayElement(newArray, 3, protection_domain);
		//cout << "f11" << endl;
		env->SetObjectArrayElement(newArray, 4, plainBytes);

		//cout << "g" << endl;
		const jbyteArray newByteArray = (jbyteArray)env->CallStaticObjectMethod(reflections, invokeMethod, method, NULL, newArray);
		unsigned char* newChars = asUnsignedCharArray(env, newByteArray);
		const jint newLength = (jint)env->GetArrayLength(newByteArray);
		//cout << "h" << endl;
		jvmti->Allocate(newLength, new_data);
		*new_data_len = newLength;
		memcpy(*new_data, newChars, newLength);
		//cout << "i" << endl;
		index++;
		jvmtiClassDefinition def = jvmtiClassDefinition();
		redefine(jvmti, &def);
		//cout << "j" << endl;
		cout << "[LycorisAgent] Transformed class: " << name << endl;
	}
}
extern "C" __declspec(dllexport)  jobjectArray JNICALL Java_rbq_wtf_lycoris_agent_instrument_impl_InstrumentationImpl_getAllLoadedClasses(JNIEnv* env, jobject instrumentationInstance) {
	jclass* jvmClasses;
	jint classCount;

	if (!jvmti)
	{
		env->GetJavaVM(&jvm);
		jvm->GetEnv((void**)&jvmti, JVMTI_VERSION_1_0);
	}
	const jint err = jvmti->GetLoadedClasses(&classCount, &jvmClasses);
	if (err) {
		cout << "Unable to get loaded classes at runtime!" << endl;
		return asClassArray(env, jvmClasses, classCount);
	}

	return asClassArray(env, jvmClasses, classCount);
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

extern "C" __declspec(dllexport)  void JNICALL Java_rbq_wtf_lycoris_agent_instrument_impl_InstrumentationImpl_retransformClasses(JNIEnv* env, jobject instrumentationInstance, jobjectArray classes) {
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
	capabilities.can_redefine_any_class = 1;
	capabilities.can_redefine_classes = 1;
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
	cout << "[LycorisAgent] Retransforming " << "classes.. Count: " << size << endl;

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
	//cout << "error" << i  << endl;
	index = 0;
	//cout << "8" << endl;
	//loaded = false;
	//cout << "9" << endl;
	//jvmti->SetEventNotificationMode(JVMTI_DISABLE, JVMTI_EVENT_CLASS_FILE_LOAD_HOOK, NULL);
	//cout << "10" << endl;
	cout << "[LycorisAgent] Retransformed " << size << " classes." << endl;
	//cout << "[Agent] Retransformed " << size << " classes." << endl;
}

BOOL APIENTRY DllMain(HMODULE hModule, DWORD dwReason, LPVOID lpReserved) {
	DisableThreadLibraryCalls(hModule);

	switch (dwReason)
	{
	case DLL_PROCESS_ATTACH:
		CreateThread(NULL, 0, (LPTHREAD_START_ROUTINE)&MainThread, NULL, 0, NULL);
		break;
	}

	return TRUE;
}
