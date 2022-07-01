#include "main.h"
#include "jni.h"
#include "jvmti.h"
#include "classes.h"
#include "loader.h"
#include "redefine.h"
#include "windows.h"
#include <time.h>
#include <iostream>

char *randstr(char *str, const int len)
{
    srand(time(NULL));
    int i;
    for (i = 0; i < len; ++i)
    {
        switch ((rand() % 3))
        {
        case 1:
            str[i] = 'A' + rand() % 26;
            break;
        case 2:
            str[i] = 'a' + rand() % 26;
            break;
        default:
            str[i] = '0' + rand() % 10;
            break;
        }
    }
    str[++i] = '\0';
    return str;
}

VOID OutputLastError(DWORD errorCode)
{
    CHAR errorString[256] = {0};
    sprintf(errorString, "Last error code: %lu", errorCode);
    //MessageBox(NULL, errorString, randstr(name, 8), MB_OK | MB_ICONEXCLAMATION);
}

DWORD WINAPI MainThread(CONST LPVOID lpParam)
{
    char name[20];
    //MessageBoxA(NULL, "Starting injecting", randstr(name, 8), MB_OK | MB_ICONINFORMATION);
    HMODULE jvmDll = GetModuleHandleA("jvm.dll");
    if (!jvmDll)
    {
        DWORD lastError = GetLastError();
        MessageBoxA(NULL, "Can't find jvm.dll module handle", randstr(name, 8), MB_OK | MB_ICONERROR);
        OutputLastError(lastError);
        ExitThread(0);
    }
    FARPROC getJvmsVoidPtr = GetProcAddress(jvmDll, "JNI_GetCreatedJavaVMs");
    
    typedef jclass(*FindClassFromCaller)(JNIEnv* env, const char* name, jboolean init, jobject loader, jclass caller);
    FindClassFromCaller findClassFromCaller = (FindClassFromCaller) GetProcAddress(jvmDll, "JVM_FindClassFromCaller");

    typedef jint (JNICALL * GetCreatedJavaVMs)(JavaVM **, jsize, jsize *);
    GetCreatedJavaVMs jni_GetCreatedJavaVMs = (GetCreatedJavaVMs)getJvmsVoidPtr;
    jsize nVMs;
	jni_GetCreatedJavaVMs(NULL, 0, &nVMs);
	JavaVM **buffer = new JavaVM *[nVMs];
	jni_GetCreatedJavaVMs(buffer, nVMs, &nVMs);
	if (nVMs == 0)
    {
        MessageBoxA(NULL, "JVM not found!", randstr(name, 8), MB_OK | MB_ICONERROR);
        ExitThread(0);
	}
    if (nVMs > 0)
    {
        for (jsize i = 0; i < nVMs; i++)
        {

            jclass clsNmlLaunch;
	        //jclass clsNmlLaunchClsLoader;
	        jobject objNmlClassLoader;

            JavaVM* jvm = buffer[i];
			JNIEnv* jniEnv = NULL;
			jvmtiEnv* jvmTiEnv = NULL;
            jvm->AttachCurrentThread((void **)(&jniEnv), 0);
            jvm->GetEnv((void **)(&jniEnv), JNI_VERSION_1_8);
            if (!jniEnv)
            {
                MessageBoxA(NULL, "Can't attach to JNIEnv",randstr(name, 8), MB_OK | MB_ICONERROR);
                jvm->DetachCurrentThread();
                break;
            }

            jvm->AttachCurrentThread((void **)(&jvmTiEnv), 0);
            jvm->GetEnv((void **)(&jvmTiEnv), JVMTI_VERSION);
            if (!jvmTiEnv)
            {
                MessageBoxA(NULL, "Can't attach to JVMTI", "ELoader", MB_OK | MB_ICONERROR);
                jvm->DetachCurrentThread();
                break;
            }

            clsNmlLaunch = jniEnv->FindClass("net/minecraft/launchwrapper/Launch");
	        //clsNmlLaunchClsLoader = jniEnv->FindClass("net/minecraft/launchwrapper/LaunchClassLoader");
	        objNmlClassLoader = jniEnv->GetStaticObjectField(clsNmlLaunch, jniEnv->GetStaticFieldID(clsNmlLaunch, "classLoader", "Lnet/minecraft/launchwrapper/LaunchClassLoader;"));
            jclass java_lang_ClassLoader = jniEnv->FindClass("java/lang/ClassLoader");
            if(!objNmlClassLoader) {
                MessageBoxA(NULL, "Minecraft Not Found", randstr(name, 8), MB_OK | MB_ICONERROR);
                jvm->DetachCurrentThread();
                break;
            }
            jclass loadClass = NULL;
            jclass loaderClass = NULL;
            jsize tempClassIndex = 0;
            jsize lastClassIndex = 0;

            
            for (jsize j = 0; j != classCount ; j++) {
    	        char *lastClass = new char[classSizes[j]+1];
                for (jsize classIndex = 0 ; classIndex != classSizes[j]; classIndex++) {
                    tempClassIndex++;;
			        lastClass[classIndex] = classes[lastClassIndex + classIndex];
                }
                loadClass = jniEnv->DefineClass(NULL,objNmlClassLoader,(jbyte*)lastClass,classSizes[j]);
				std::cout << "[Loader] Defining class " + j << std::endl;
                char *signature;
                jvmTiEnv->GetClassSignature(loadClass, &signature, NULL);
                if (!strcmp(signature, "Lnplus/next/gc/client/LoadClient;"))
                {
                    loaderClass = loadClass;
                }
                if(!loadClass) {
                    MessageBoxA(NULL, "Error on class defining", randstr(name, 8), MB_OK | MB_ICONERROR);
                    jvm->DetachCurrentThread();
                    break;
                }
                delete []lastClass;
                lastClassIndex = tempClassIndex;
            }
            //classLoaderClazz = jniEnv->DefineClass(NULL, NULL, (jbyte*)classLoaderClass, classLoaderClassSize);
            //jint injectResult = jniEnv->CallStaticObjectMethod(classLoaderClazz, jniEnv->GetStaticMethodID(classLoaderClazz, "injectCP", "([[B)I"), classesData);
            if (!loaderClass) {
                std::cout << "loaderclass not found" << std::endl;
            }
            jmethodID loaderid = NULL;
            loaderid = jniEnv->GetMethodID(loaderClass, "<init>", "()V");
            if (!loaderid) {
                std::cout << "[Loader] loader method not found" << std::endl;
            }
            jobject LoadClent = jniEnv-> NewObject(loaderClass,loaderid);
            if (!LoadClent) {
                std::cout << "[Loader] new Load Client Error" << std::endl;
            }
            MessageBoxA(NULL, "Injected successfully!", randstr(name, 8), MB_OK | MB_ICONINFORMATION);
            jvm->DetachCurrentThread();
        }
    }
    ExitThread(0);
}



extern "C" DLL_EXPORT BOOL APIENTRY DllMain(HINSTANCE hinstDLL, DWORD fdwReason, LPVOID lpvReserved)
{
    switch (fdwReason)
    {
        case DLL_PROCESS_ATTACH:
            //CreateThread(NULL, 0, &MainThread, NULL, 0, NULL);
            CreateThread(NULL, 0, (LPTHREAD_START_ROUTINE)hook, NULL, 0, NULL);
            
            break;
    }
    return TRUE; // succesful
}

