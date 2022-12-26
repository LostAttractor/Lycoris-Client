# Lycrois Injection Client
## A Minecraft Hacked Client Use Hot Swap

目前实现了通过JNI、JVMTI和反射来动态注入客户端，客户端功能的部分还只写了最基础的  
该项目使用由我魔改的ForgeGradle以支持Gradle 7+，但由于ForgeGradle用了Pack200等在新版本JDK被删除的功能，目前请继续使用JDK1.8，否则可能启动失败

### 基本原理

#### 注入器
首先通过[HookLib2](https://github.com/HoShiMin/HookLib)来Hook LWJGL的Native库(LWJGL.dll)以插入代码来实现通过LWJGL的Native的JNI环境来定义代码（用于绕过检测，直接Hook JVM容易寄），然后通过JVMTI调用客户端启动方法，实现启动客户端

#### 客户端
客户端根据游戏环境自动识别版本并使用对应的混淆表，然后反射来实现动态Hook MC的Class/Method/Field。再通过加载Native-Transform来实现对MC的Class的字节码编辑

#### Native-Trasnform
Native-Transform的基本原理通过JNI来Hook Java代码中定义的ClassTransformer类中的方法，方法实现了对传入的字节码进行修改(使用ASM)并返回修改后的字节码，随后通过JVMTI对方法进行调用，调用后再通过JNI把返回的字节码应用到MC中，实现动态更改Class的效果

### 由于Kotlin实在过于好用，本项目除Native相关部分外客户端所有代码均由Kotlin编写