package rbq.wtf.lycoris.client.wrapper;

import rbq.wtf.lycoris.client.LycorisClient;
import rbq.wtf.lycoris.client.gui.Font.FontLoaders;
import rbq.wtf.lycoris.client.wrapper.SRGReader.Reader;
import rbq.wtf.lycoris.client.wrapper.SRGReader.map.MapNode;
import rbq.wtf.lycoris.client.wrapper.SRGReader.map.MethodNode;
import rbq.wtf.lycoris.client.wrapper.SRGReader.map.NodeType;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.*;
import rbq.wtf.lycoris.client.wrapper.wrappers.annotation.repeat.*;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.GameSettings;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.IWrapper;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.KeyBinding;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.Minecraft;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.entity.Entity;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.gui.*;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.render.*;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.render.texture.AbstractTexture;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.render.texture.DynamicTexture;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.utils.*;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.utils.event.HoverEvent;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.utils.event.click.ClickEvent;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.utils.event.click.ClickEventAction;
import rbq.wtf.lycoris.client.wrapper.wrappers.wrapper.utils.text.IChatComponent;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Wrapper {

    public static MapEnum MapEnv;
    private static Reader reader;
    private static final List<Class<? extends IWrapper>> wrappers = new ArrayList<Class<? extends IWrapper>>();

    public static void initWrapper() {
        MapEnv = MapEnum.VANILLA189;
        Path path = Paths.get("").toAbsolutePath().getParent().resolve("maps/" + MapEnv.toString() + ".srg");
        String map = readFileByPath(path);
        reader = new Reader(map);
        reader.preRead();
        loadWrapper();
        //ReflectLoading.loadingProgress.setString("Loading Wrapper");
        try {
            applyMap();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String readFileByPath(Path path) {
        try {
            InputStream stream = Files.newInputStream(path);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[512000];

            int length;
            while ((length = stream.read(buffer)) != -1) {
                bos.write(buffer, 0, length);
            }
            return bos.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void loadWrapper() {
        System.out.println("[Wrapper] Loading wrapper");
        List<Class<?>> classes = new ArrayList<Class<?>>();
        //client
        classes.add(Minecraft.class);
        classes.add(KeyBinding.class);
        classes.add(GameSettings.class);
        //entity
        classes.add(Entity.class);
        //gui
        classes.add(Gui.class);
        classes.add(ScaledResolution.class);
        classes.add(GuiIngame.class);
        classes.add(GuiChat.class);
        classes.add(GuiScreen.class);
        classes.add(GuiTextField.class);
        //render
        classes.add(DefaultVertexFormats.class);
        classes.add(GlStateManager.class);
        classes.add(OpenGlHelper.class);
        classes.add(Tessellator.class);
        classes.add(VertexFormat.class);
        classes.add(WorldRenderer.class);
        classes.add(FontLoaders.class);
        //render.texture
        classes.add(AbstractTexture.class);
        classes.add(DynamicTexture.class);
        //utils
        classes.add(AxisAlignedBB.class);
        classes.add(BlockPos.class);
        classes.add(Vec3i.class);
        classes.add(Vec3.class);
        classes.add(ChatAllowedCharacters.class);
        classes.add(ChatStyle.class);
        //utils.event
        classes.add(ClickEvent.class);
        classes.add(ClickEventAction.class);
        classes.add(HoverEvent.class);
        //utils.text
        classes.add(IChatComponent.class);


        for (Class<?> aClass : classes) {
            System.out.println(aClass.getCanonicalName());
            if (aClass != IWrapper.class) {
                wrappers.add((Class<? extends IWrapper>) aClass);
            }
        }
    }


    public static List<Class<? extends IWrapper>> getWrappers() {
        return wrappers;
    }

    private static void applyMap() throws IllegalAccessException, ClassNotFoundException, NoSuchFieldException, NoSuchMethodException {
        int index = 1;
        for (Class<? extends IWrapper> wrapper : wrappers) {
            System.out.println("apply wrapper " + wrapper.getCanonicalName());
            for (Annotation declaredAnnotation : wrapper.getDeclaredAnnotations()) {
                if (declaredAnnotation instanceof WrapperClasses) {
                    for (WrapperClass wrapperClass : ((WrapperClasses) declaredAnnotation).value()) {
                        applyClass(wrapperClass, wrapper);
                    }
                } else if (declaredAnnotation instanceof WrapperClass) {
                    WrapperClass wrapperClass = (WrapperClass) declaredAnnotation;
                    applyClass(wrapperClass, wrapper);
                }
            }
            index++;
        }
        index = 1;
        for (Class<? extends IWrapper> wrapper : wrappers) {
            System.out.println("apply constructor " + wrapper.getCanonicalName());
            for (Annotation declaredAnnotation : wrapper.getDeclaredAnnotations()) {
                if (declaredAnnotation instanceof WrapperClasses) {
                    for (WrapperClass wrapperClass : ((WrapperClasses) declaredAnnotation).value()) {
                        applyConstructor(wrapperClass, wrapper);
                    }
                } else if (declaredAnnotation instanceof WrapperClass) {
                    WrapperClass wrapperClass = (WrapperClass) declaredAnnotation;
                    applyConstructor(wrapperClass, wrapper);
                }
            }
            index++;
        }
    }

    private static void applyConstructor(WrapperClass wrapperClass, Class<? extends IWrapper> wrapper) {
        if (wrapperClass.targetMap() == MapEnv) {
            for (Field declaredField : wrapper.getDeclaredFields()) {
                try {
                    for (Annotation annotation : declaredField.getDeclaredAnnotations()) {
                        if (annotation instanceof WrapConstructors) {
                            for (WrapConstructor wrapConstructor : ((WrapConstructors) annotation).value()) {
                                applyConstructor(wrapperClass, wrapConstructor, declaredField);
                            }
                        } else if (annotation instanceof WrapConstructor) {
                            WrapConstructor wrapConstructor = (WrapConstructor) annotation;
                            applyConstructor(wrapperClass, wrapConstructor, declaredField);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void applyConstructor(WrapperClass wrapperClass, WrapConstructor wrapConstructor, Field declaredField) throws NoSuchFieldException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException {
        if (wrapConstructor.targetMap() == MapEnv) {
            if (Modifier.isStatic(declaredField.getModifiers())) {
                List<Class<?>> classes = new ArrayList<Class<?>>();
                for (Class<?> aClass : wrapConstructor.signature()) {
                    if (IWrapper.class.isAssignableFrom(aClass)) {
                        for (Annotation declaredAnnotation : aClass.getDeclaredAnnotations()) {
                            if (declaredAnnotation instanceof WrapperClasses) {
                                for (WrapperClass target : ((WrapperClasses) declaredAnnotation).value()) {
                                    if (target.targetMap() == MapEnv) {
                                        MapNode mapNode = readClass(target.mcpName());
                                        classes.add(reflectClassByMap(mapNode));
                                    }
                                }
                            } else if (declaredAnnotation instanceof WrapperClass) {
                                WrapperClass target = (WrapperClass) declaredAnnotation;
                                if (target.targetMap() == MapEnv) {
                                    MapNode mapNode = readClass(target.mcpName());
                                    classes.add(reflectClassByMap(mapNode));
                                }
                            }
                        }
                    } else {
                        classes.add(aClass);
                    }
                }
                MapNode mapNode = readClass(wrapperClass.mcpName());
                Class target = reflectClassByMap(mapNode);
                Constructor constructor;
                if (classes.size() == 0) {
                    constructor = target.getConstructors()[0];
                } else {
                    constructor = target.getConstructor(classes.toArray(new Class[0]));
                }
                declaredField.set(null, constructor);
            }
        }
    }

    private static void applyClass(WrapperClass wrapperClass, Class<? extends IWrapper> wrapper) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, NoSuchMethodException {
        if (wrapperClass.targetMap() == MapEnv) {
            for (Field declaredField : wrapper.getDeclaredFields()) {
                try {
                    for (Annotation annotation : declaredField.getDeclaredAnnotations()) {
                        if (annotation instanceof WrapFields) {
                            for (WrapField wrapField : ((WrapFields) annotation).value()) {
                                applyField(wrapperClass, wrapField, declaredField);
                            }
                        } else if (annotation instanceof WrapField) {
                            WrapField wrapField = (WrapField) annotation;
                            applyField(wrapperClass, wrapField, declaredField);
                        } else if (annotation instanceof WrapMethods) {
                            for (WrapMethod wrapMethod : ((WrapMethods) annotation).value()) {
                                applyMethod(wrapperClass, wrapMethod, declaredField);
                            }
                        } else if (annotation instanceof WrapMethod) {
                            WrapMethod wrapMethod = (WrapMethod) annotation;
                            applyMethod(wrapperClass, wrapMethod, declaredField);
                        } else if (annotation instanceof WrapClasses) {
                            for (WrapClass wrapClass : ((WrapClasses) annotation).value()) {
                                applyClass(wrapperClass, wrapClass, declaredField);
                            }
                        } else if (annotation instanceof WrapClass) {
                            WrapClass wrapClass = (WrapClass) annotation;
                            applyClass(wrapperClass, wrapClass, declaredField);
                        } else if (annotation instanceof WrapEnums) {
                            for (WrapEnum wrapEnum : ((WrapEnums) annotation).value()) {
                                applyEnum(wrapperClass, wrapEnum, declaredField);
                            }
                        } else if (annotation instanceof WrapEnum) {
                            WrapEnum wrapEnum = (WrapEnum) annotation;
                            applyEnum(wrapperClass, wrapEnum, declaredField);
                        } else if (annotation instanceof WrapObjects) {
                            for (WrapObject wrapObject : ((WrapObjects) annotation).value()) {
                                applyObject(wrapperClass, wrapObject, declaredField);
                            }
                        } else if (annotation instanceof WrapObject) {
                            WrapObject wrapObject = (WrapObject) annotation;
                            applyObject(wrapperClass, wrapObject, declaredField);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void applyEnum(WrapperClass wrapperClass, WrapEnum
            wrapEnum, Field declaredField) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException {
        if (wrapEnum.targetMap() == MapEnv) {
            if (Modifier.isStatic(declaredField.getModifiers())) {
                //ReadMap
                MapNode mapNode = readField(wrapperClass.mcpName(), wrapEnum.mcpName());
                if (!wrapEnum.customSrgName().equals("none")) {
                    MapNode clazz = readClass(wrapperClass.mcpName());
                    if (clazz != null)
                        mapNode = new MapNode(NodeType.Field, "", clazz.getSrg().replace(".", "/") + wrapEnum.customSrgName());
                }
                if (mapNode != null) {
                    System.out.println(wrapperClass.mcpName() + " " + wrapEnum.mcpName() + " -> " + mapNode.getSrg());
                    declaredField.setAccessible(true);
                    System.out.println("Enum null: " + (reflectEnumByMap(mapNode) == null));
                    System.out.println(mapNode.getMcp() + " " + mapNode.getSrg());
                    declaredField.set(null, reflectEnumByMap(mapNode));
                }
            }
        }
    }

    private static Enum<?> reflectEnumByMap(MapNode mapNode) {
        String srg = mapNode.getSrg();
        String field = srg.split("/")[srg.split("/").length - 1];
        String clazz = srg.replace("/", ".").replace("." + field, "");
        Class<?> c = reader.getClassNative(clazz);
        for (Object enumConstant : c.getEnumConstants()) {
            if (enumConstant instanceof Enum) {
                if (((Enum<?>) enumConstant).name().equals(field))
                    return (Enum<?>) enumConstant;
            }
        }
        return reflectEnumByMapMcp(mapNode, c);
    }

    private static Enum<?> reflectEnumByMapMcp(MapNode mapNode, Class<?> c) {
        String srg = mapNode.getMcp();
        String field = srg.split("/")[srg.split("/").length - 1];
        for (Object enumConstant : c.getEnumConstants()) {
            if (enumConstant instanceof Enum) {
                if (((Enum<?>) enumConstant).name().equals(field))
                    return (Enum<?>) enumConstant;
            }
        }
        return null;
    }

    private static void applyObject(WrapperClass wrapperClass, WrapObject wrapObject, Field declaredField) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException {
        if (wrapObject.targetMap() == MapEnv) {
            if (Modifier.isStatic(declaredField.getModifiers())) {
                //ReadMap
                MapNode mapNode = readField(wrapperClass.mcpName(), wrapObject.mcpName());
                String customSrg = wrapObject.customSrgName();
                if (!customSrg.equals("none")) {
                    MapNode clazz = readClass(wrapperClass.mcpName());
                    mapNode = new MapNode(NodeType.Field, "", clazz.getSrg().replace(".", "/") + customSrg);
                }
                System.out.println(wrapperClass.mcpName() + " " + wrapObject.mcpName() + " -> " + mapNode.getSrg());
                if (mapNode != null) {
                    declaredField.setAccessible(true);
                    declaredField.set(null, reflectFieldByMap(mapNode).get(null));
                }
            }
        }
    }

    private static void applyField(WrapperClass wrapperClass, WrapField wrapField, Field declaredField) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException {
        if (wrapField.targetMap() == MapEnv) {
            if (Modifier.isStatic(declaredField.getModifiers())) {
                //ReadMap
                MapNode mapNode = readField(wrapperClass.mcpName(), wrapField.mcpName());
                String customSrg = wrapField.customSrgName();
                if (!customSrg.equals("none")) {
                    MapNode clazz = readClass(wrapperClass.mcpName());
                    mapNode = new MapNode(NodeType.Field, "", clazz.getSrg().replace(".", "/") + customSrg);
                }
                System.out.println(wrapperClass.mcpName() + " " + wrapField.mcpName() + " -> " + mapNode.getSrg());
                if (mapNode != null) {
                    declaredField.setAccessible(true);
                    declaredField.set(null, reflectFieldByMap(mapNode));
                }
            }
        }
    }

    private static void applyClass(WrapperClass wrapperClass, WrapClass wrapClass, Field declaredField) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException {
        if (wrapClass.targetMap() == MapEnv) {
            if (Modifier.isStatic(declaredField.getModifiers())) {
                //ReadMap
                MapNode mapNode = readClass(wrapClass.mcpName());

                System.out.println(wrapClass.mcpName() + " -> " + mapNode.getSrg());
                if (mapNode != null) {
                    declaredField.setAccessible(true);
                    declaredField.set(null, reflectClassByMap(mapNode));
                }
            }
        }
    }

    private static void applyMethod(WrapperClass wrapperClass, WrapMethod wrapMethod, Field declaredField) throws NoSuchFieldException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException {
        if (wrapMethod.targetMap() == MapEnv) {
            if (Modifier.isStatic(declaredField.getModifiers())) {
                String costumSig = null;
                if (!wrapMethod.signature().equals("none")) {
                    costumSig = wrapMethod.signature();
                }
                MapNode mapNode = readMethod(wrapperClass.mcpName(), wrapMethod.mcpName(), costumSig);
                if (mapNode != null) {
                    if (mapNode.getSrg() == null) {
                        System.out.println("null" + " " + wrapperClass.mcpName() + " " + wrapMethod.mcpName());
                    }
                    try {
                        declaredField.setAccessible(true);
                        declaredField.set(null, reflectMethodByMap(mapNode));
                        System.out.println(wrapperClass.mcpName() + " " + wrapMethod.mcpName() + " -> " + mapNode.getSrg());
                    } catch (Exception e) {
                        System.out.println("[Failed]" + wrapperClass.mcpName() + " " + wrapMethod.mcpName() + " -> " + mapNode.getSrg());
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static Field reflectFieldByMap(MapNode mapNode) throws ClassNotFoundException, NoSuchFieldException {
        String srg = LycorisClient.useMapObf ? mapNode.getSrg() : mapNode.getMcp();
        String field = srg.split("/")[srg.split("/").length - 1];
        String clazz = srg.replace("/", ".").replace("." + field, "");
        Class<?> c = reader.getClassNative(clazz);
        for (Field cField : c.getFields()) {
            if (cField.getName().equals(field)) {
                cField.setAccessible(true);
                return cField;
            }
        }
        for (Field declaredField : c.getDeclaredFields()) {
            if (declaredField.getName().equals(field)) {
                declaredField.setAccessible(true);
                return declaredField;
            }
        }
        throw new NullPointerException("Can't wrap " + mapNode.getMcp() + " -> " + c.getCanonicalName() + "." + field + "]");
    }

    private static Class<?> reflectClassByMap(MapNode mapNode) throws ClassNotFoundException, NoSuchFieldException {
        String srg = LycorisClient.useMapObf ? mapNode.getSrg() : mapNode.getMcp();
        String clazz = srg.replace("/", ".");
        Class<?> c = reader.getClassNative(clazz);
        return c;
    }

    private static Method reflectMethodByMap(MapNode mapNode) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException {
        Method m;
        if (mapNode instanceof MethodNode) {
            String srg = LycorisClient.useMapObf ? mapNode.getSrg() : mapNode.getMcp();
            String method = srg.split("/")[srg.split("/").length - 1];
            String clazz = srg.replace("/", ".").replace("." + method, "");
            for (Class<?> arg : ((MethodNode) mapNode).getSignature().getArgs()) {
                if (arg == null) {
                    System.out.println(mapNode.getSrg() + " arg is null");
                }
            }
            Class<?> c = reader.getClassNative(clazz);
            m = c.getDeclaredMethod(method, ((MethodNode) mapNode).getSignature().getArgs());
            m.setAccessible(true);
        } else {
            m = null;
        }
        return m;
    }

    public static MapNode readField(String clazz, String field) {
        String map = clazz.replace(".", "/") + "/" + field;
        for (MapNode mapNode : reader.getMapNodes()) {
            if (mapNode.getNodeType() == NodeType.Field && mapNode.getMcp().equals(map)) {
                return mapNode;
            }
        }
        return null;
    }

    public static MapNode readClass(String clazz) {
        String map = clazz.replace(".", "/");
        for (MapNode mapNode : reader.getMapNodes()) {
            if (mapNode.getNodeType() == NodeType.Class && mapNode.getMcp().equals(map)) {
                return mapNode;
            }
        }
        return null;
    }

    public static MapNode readMethod(String clazz, String method, String customSig) {
        String map = clazz.replace(".", "/") + "/" + method;
        for (MapNode mapNode : reader.getMapNodes()) {
            if (mapNode.getNodeType() == NodeType.Method && mapNode.getMcp().equals(map)) {
                if (customSig != null) {
                    if (customSig.equals(((MethodNode) mapNode).getDeobfSig())) {
                        return mapNode;
                    }
                } else {
                    return mapNode;
                }
            }
        }
        return null;
    }
}
