package rbq.wtf.lycoris.client.wrapper.SRGReader;


import rbq.wtf.lycoris.client.utils.Logger;
import rbq.wtf.lycoris.client.wrapper.SRGReader.map.MapNode;
import rbq.wtf.lycoris.client.wrapper.SRGReader.map.MethodNode;
import rbq.wtf.lycoris.client.wrapper.SRGReader.map.NodeType;
import rbq.wtf.lycoris.client.wrapper.SRGReader.map.Signature;
import rbq.wtf.lycoris.client.wrapper.SRGReader.utils.StringStream;

import java.util.ArrayList;
import java.util.List;

public class SRGReader {
    private final List<MapNode> mapNodes;
    //private List<Class<?>> loadedClasses = new ArrayList<>();

    public SRGReader(String srgMap) {
        mapNodes = readMap(srgMap);
    }

    public List<MapNode> readMap(String srgMap) {
        List<MapNode> mapNodes = new ArrayList<MapNode>();
        for (String s : srgMap.split("\\n")) {
            try {
                String[] strings = s.split(" ");
                if (strings.length != 0) {
                    if (getNodeType(strings[0]) == NodeType.Method) {
                        if (strings.length == 5) {
                            Signature sig = genSignature(strings[4]);
                            mapNodes.add(new MethodNode(getNodeType(strings[0]), strings[1], strings[3], sig, strings[4], strings[2]));
                        }
                    } else {
                        if (strings.length == 3) {
                            mapNodes.add(new MapNode(getNodeType(strings[0]), strings[1], strings[2]));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mapNodes;
    }

    public Class<?> getClassNative(String name) throws ClassNotFoundException {
        //return SRGReader.class.getClassLoader().loadClass(name);
        //return ClassLoader.getSystemClassLoader().loadClass(name);
        //return Class.forName(name, false, ClassLoader.getSystemClassLoader());
        //return Loader.instance().getModClassLoader().loadClass(name);
        //return Launch.classLoader.loadClass(name);
        return ClassLoader.getSystemClassLoader().loadClass(name);
    }

    private Signature genSignature(String sig) {
        List<Class<?>> args = new ArrayList<>(); //方法传入参数列表
        Class<?> returnType = void.class; //返回值类型,默认为void
        boolean onReadingClassName = false; //是否在阅读ClassName
        boolean onReadingArgs = true; //是否在阅读参数
        StringBuilder className = new StringBuilder();
        // 示例:
        // (Lnet/minecraft/util/EnumFacing;)Z
        // (I)Lnet/minecraft/block/state/IBlockState;
        // (Lnet/minecraft/network/INetHandler;)V
        StringStream ss = new StringStream(sig);
        while (ss.available()) {
            String t = ss.read(); // 获取一个字符
            if (onReadingClassName) {
                if (t.equals(";")) { // 一个ClassName阅读到末尾了,开始解析
                    try {
                        Logger.log("Finding Class: " + className.toString().replace("/", "."), "SRGReader", Logger.LogLevel.DEBUG);
                        Class<?> target = Class.forName(className.toString().replace("/", "."));
                        if (onReadingArgs)
                            args.add(target);
                        else
                            returnType = target;
                    } catch (Exception e) {
                        if (className.toString().contains("net/minecraft/server")) {
                            Logger.log("Failed to find a Server Class: " + className + ", Ignored", "SRGReader", Logger.LogLevel.WARNING);
                        } else {
                            e.printStackTrace();
                            Logger.log("Failed to find Class: " + className, "SRGReader", Logger.LogLevel.ERROR);
                            Logger.log("Failed to Generate Signature: " + sig, "SRGReader", Logger.LogLevel.ERROR);
                        }
                    }
                    onReadingClassName = false;
                } else {
                    className.append(t);
                }
                continue;
            }
            switch (t) {
                case "(": // 起始必定为左'(',即开始阅读方法传参
                    onReadingArgs = true;
                    break;
                case ")": // 变量传参结束，开始阅读返回值
                    onReadingArgs = false;
                    break;
                case "L": // 变量传参输入了一个Class,开始阅读ClassName
                    onReadingClassName = true;
                    className = new StringBuilder();
                    break;
                case "Z": // 传入一个boolean类型变量
                    if (onReadingArgs)
                        args.add(boolean.class);
                    else
                        returnType = boolean.class;
                    break;
                case "C": // 传入一个char类型变量
                    if (onReadingArgs)
                        args.add(char.class);
                    else
                        returnType = char.class;
                    break;
                case "B": // 传入一个byte类型变量
                    if (onReadingArgs)
                        args.add(byte.class);
                    else
                        returnType = byte.class;
                    break;
                case "S": // 传入一个short类型变量
                    if (onReadingArgs)
                        args.add(short.class);
                    else
                        returnType = short.class;
                    break;
                case "I": // 传入一个int类型变量
                    if (onReadingArgs)
                        args.add(int.class);
                    else
                        returnType = int.class;
                    break;
                case "F": // 传入一个float类型变量
                    if (onReadingArgs)
                        args.add(float.class);
                    else
                        returnType = float.class;
                    break;
                case "J": // 传入一个long类型变量
                    if (onReadingArgs)
                        args.add(long.class);
                    else
                        returnType = long.class;
                    break;
                case "D": // 传入一个double类型变量
                    if (onReadingArgs)
                        args.add(double.class);
                    else
                        returnType = double.class;
                    break;
                case "V":
                    if (!onReadingArgs)
                        returnType = void.class;
                    break;
                default:
                    Logger.log("Found a Unknown Identifier: " + t, "SRGReader", Logger.LogLevel.ERROR);
            }
        }
        Class<?>[] classes = new Class[args.size()];
        classes = args.toArray(classes);
        return new Signature(classes, returnType);
    }

    public List<MapNode> getMapNodes() {
        return mapNodes;
    }

    private NodeType getNodeType(String s) {
        switch (s) {
            case "CL:":
                return NodeType.Class;
            case "FD:":
                return NodeType.Field;
            case "MD:":
                return NodeType.Method;
        }
        return null;
    }
}
