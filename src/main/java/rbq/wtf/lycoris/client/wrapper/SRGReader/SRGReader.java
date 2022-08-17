package rbq.wtf.lycoris.client.wrapper.SRGReader;


import rbq.wtf.lycoris.client.wrapper.SRGReader.map.MapNode;
import rbq.wtf.lycoris.client.wrapper.SRGReader.map.MethodNode;
import rbq.wtf.lycoris.client.wrapper.SRGReader.map.NodeType;
import rbq.wtf.lycoris.client.wrapper.SRGReader.map.Signature;
import rbq.wtf.lycoris.client.wrapper.SRGReader.utils.StringStream;

import java.util.ArrayList;
import java.util.List;

public class SRGReader {
    //private final String srgMap;
    private List<MapNode> mapNodes;
    //private List<Class<?>> loadedClasses = new ArrayList<>();

    public SRGReader(String srgMap) {
        // srgMap = Mcp2SrgMap;
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

    public Class<?> getClassNative(String name) {
        try {
            return SRGReader.class.getClassLoader().loadClass(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Signature genSignature(String sig) throws ClassNotFoundException {
        List<Class<?>> arg = new ArrayList<Class<?>>();
        Class<?> ret = void.class;
        boolean readClassName = false;
        boolean readArg = true;
        StringBuilder className = new StringBuilder();
        StringStream ss = new StringStream(sig);
        while (ss.available()) {
            String t = ss.read();
            if (readArg) {
                if (readClassName) {
                    if (!t.equals(";")) {
                        className.append(t);
                    } else {
                        try {
                            readClassName = false;
                            Class<?> target = Class.forName(className.toString().replace("/", "."));
                            arg.add(target);
                        } catch (ClassNotFoundException e) {
                            if (className.toString().contains("net/minecraft/server")) {
                                System.out.println("Failed to find a Server Class:" + className + ", Ignored");
                            } else {
                                System.out.println("Failed to find Class:" + className);
                                e.printStackTrace();
                            }
                        }
                    }
                } else if (t.equals("(")) {

                } else if (t.equals("Z")) {
                    arg.add(boolean.class);
                } else if (t.equals("C")) {
                    arg.add(char.class);
                } else if (t.equals("B")) {
                    arg.add(byte.class);
                } else if (t.equals("S")) {
                    arg.add(short.class);
                } else if (t.equals("I")) {
                    arg.add(int.class);
                } else if (t.equals("F")) {
                    arg.add(float.class);
                } else if (t.equals("J")) {
                    arg.add(long.class);
                } else if (t.equals("D")) {
                    arg.add(double.class);
                } else if (t.equals("L")) {
                    readClassName = true;
                    className = new StringBuilder();
                } else if (t.equals(")")) {
                    readArg = false;
                }
            } else {
                if (readClassName) {
                    if (!t.equals(";")) {
                        className.append(t);
                    } else {
                        try {
                            readClassName = false;
                            ret = (Class.forName(className.toString().replace("/", ".")));
                        } catch (ClassNotFoundException e) {
                            if (className.toString().contains("net/minecraft/server")) {
                                System.out.println("Failed to find a Server Class:" + className + ", Ignored");
                            } else {
                                System.out.println("Failed to find Class:" + className);
                                e.printStackTrace();
                            }
                        }
                    }
                } else if (t.equals("Z")) {
                    ret = (boolean.class);
                } else if (t.equals("C")) {
                    ret = (char.class);
                } else if (t.equals("B")) {
                    ret = (byte.class);
                } else if (t.equals("S")) {
                    ret = (short.class);
                } else if (t.equals("I")) {
                    ret = (int.class);
                } else if (t.equals("F")) {
                    ret = (float.class);
                } else if (t.equals("J")) {
                    ret = (long.class);
                } else if (t.equals("D")) {
                    ret = (double.class);
                } else if (t.equals("L")) {
                    readClassName = true;
                    className = new StringBuilder();
                } else if (t.equals("V")) {
                    ret = void.class;
                }
            }

        }
        Class<?>[] classes = new Class[arg.size()];
        classes = arg.toArray(classes);
        for (Class<?> aClass : classes) {
            if (aClass == null) {
                System.out.println("class = null");
            }
        }
        return new Signature(classes, ret);
    }

    public Class<?>[] toArray(List<Class<?>> classes) {
        return (Class<?>[]) classes.toArray();
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
