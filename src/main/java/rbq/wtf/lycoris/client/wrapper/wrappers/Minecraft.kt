package rbq.wtf.lycoris.client.wrapper.wrappers

import rbq.wtf.lycoris.client.wrapper.IWrapper
import rbq.wtf.lycoris.client.wrapper.MapEnum
import rbq.wtf.lycoris.client.wrapper.annotation.WrapClassAuto
import rbq.wtf.lycoris.client.wrapper.annotation.WrapField
import rbq.wtf.lycoris.client.wrapper.annotation.WrapMethod
import rbq.wtf.lycoris.client.wrapper.annotation.WrapperClass
import rbq.wtf.lycoris.client.wrapper.utils.ReflectUtil
import rbq.wtf.lycoris.client.wrapper.wrappers.entity.EntityPlayerSP
import rbq.wtf.lycoris.client.wrapper.wrappers.gui.GuiScreen
import rbq.wtf.lycoris.client.wrapper.wrappers.gui.ScaledResolution
import rbq.wtf.lycoris.client.wrapper.wrappers.multiplayer.PlayerControllerMP
import rbq.wtf.lycoris.client.wrapper.wrappers.multiplayer.WorldClient
import rbq.wtf.lycoris.client.wrapper.wrappers.resources.IResourceManager
import rbq.wtf.lycoris.client.wrapper.wrappers.util.MovingObjectPosition
import java.lang.reflect.Field
import java.lang.reflect.Method

@WrapperClass(mcpName = "net.minecraft.client.Minecraft", targetMap = MapEnum.VANILLA189)
class Minecraft(obj: Any) : IWrapper(obj) {
    val displayHeight: Int
        get() = getField(Companion.displayHeight) as Int
    val displayWidth: Int
        get() = getField(Companion.displayWidth) as Int
    val player: EntityPlayerSP
        get() = EntityPlayerSP(getField(Companion.player))
    val world: WorldClient?
        get() = getField(Companion.world)?.let { WorldClient(it) }
    val playerController: PlayerControllerMP
        get() = PlayerControllerMP(getField(Companion.playerController)!!)
    val resourceManager: IResourceManager
        get() = IResourceManager(invoke(getResourceManager)!!)
    val gameSettings: GameSettings
        get() = GameSettings(getField(Companion.gameSettings)!!)
    val objectMouseOver: MovingObjectPosition?
        get() = getField(Companion.objectMouseOver)?.let { MovingObjectPosition(it) }

    val currentScreen: GuiScreen?
        get() = getField(Companion.currentScreen)?.let { GuiScreen(it) }

    fun displayGuiScreenBypass(screen: GuiScreen) { //不使用构造器
        setField(Companion.currentScreen, screen.wrapObject)
        invoke(setIngameNotInFocus)
        val scaledResolution = ScaledResolution(this)
        val i = scaledResolution.scaledWidth
        val j = scaledResolution.scaledHeight
        this.currentScreen!!.setMc(this)
        this.currentScreen!!.height = j
        this.currentScreen!!.width = i
        this.currentScreen!!.initGui()
    }

    companion object {
        @WrapClassAuto
        lateinit var wrapClass: Class<*>;

        //    @WrapField(mcpName = "theMinecraft", targetMap = MapEnum.VANILLA189)
        //    public static Field theMinecraft;
        @WrapMethod(mcpName = "getMinecraft", targetMap = MapEnum.VANILLA189)
        lateinit var getMinecraft: Method

        @WrapField(mcpName = "timer", targetMap = MapEnum.VANILLA189)
        lateinit var timer: Field

        @WrapMethod(mcpName = "displayGuiScreen", targetMap = MapEnum.VANILLA189)
        lateinit var displayGuiScreen: Method

        @WrapField(mcpName = "theWorld", targetMap = MapEnum.VANILLA189)
        lateinit var world: Field

        @WrapField(mcpName = "thePlayer", targetMap = MapEnum.VANILLA189)
        lateinit var player: Field

        @WrapField(mcpName = "currentScreen", targetMap = MapEnum.VANILLA189)
        lateinit var currentScreen: Field

        @WrapField(mcpName = "ingameGUI", targetMap = MapEnum.VANILLA189)
        lateinit var ingameGUI: Field

        @WrapField(mcpName = "serverName", targetMap = MapEnum.VANILLA189)
        lateinit var serverName: Field

        @WrapField(mcpName = "serverPort", targetMap = MapEnum.VANILLA189)
        lateinit var serverPort: Field

        @WrapField(mcpName = "gameSettings", targetMap = MapEnum.VANILLA189)
        lateinit var gameSettings: Field

        @WrapField(mcpName = "objectMouseOver", targetMap = MapEnum.VANILLA189)
        lateinit var objectMouseOver: Field

        @WrapField(mcpName = "playerController", targetMap = MapEnum.VANILLA189)
        lateinit var playerController: Field

        @WrapField(mcpName = "leftClickCounter", targetMap = MapEnum.VANILLA189)
        lateinit var leftClickCounter: Field

        //    @WrapField(mcpName = "mcResourceManager", targetMap = MapEnum.VANILLA189)
        //    public static Field mcResourceManager;
        @WrapMethod(mcpName = "getResourceManager", targetMap = MapEnum.VANILLA189)
        lateinit var getResourceManager: Method

        @WrapMethod(mcpName = "runTick", targetMap = MapEnum.VANILLA189)
        lateinit var runTick: Method

        @WrapMethod(mcpName = "dispatchKeypresses", targetMap = MapEnum.VANILLA189)
        lateinit var dispatchKeypresses: Method

        //    @WrapField(mcpName = "debugFPS", targetMap = MapEnum.VANILLA189)
        //    public static Field debugFPS;
        @WrapMethod(mcpName = "getDebugFPS", targetMap = MapEnum.VANILLA189)
        lateinit var getDebugFPS: Method

        @WrapField(mcpName = "renderManager", targetMap = MapEnum.VANILLA189)
        lateinit var renderManager: Field

        @WrapField(mcpName = "renderEngine", targetMap = MapEnum.VANILLA189)
        lateinit var renderEngine: Field

        @WrapField(mcpName = "displayWidth", targetMap = MapEnum.VANILLA189)
        lateinit var displayWidth: Field

        @WrapField(mcpName = "displayHeight", targetMap = MapEnum.VANILLA189)
        lateinit var displayHeight: Field

        @WrapField(mcpName = "session", targetMap = MapEnum.VANILLA189)
        lateinit var session: Field

        @WrapMethod(mcpName = "setIngameNotInFocus", targetMap = MapEnum.VANILLA189)
        lateinit var setIngameNotInFocus: Method

        @WrapMethod(mcpName = "runGameLoop", targetMap = MapEnum.VANILLA189)
        lateinit var runGameLoop: Method

        @WrapMethod(mcpName = "getNetHandler", targetMap = MapEnum.VANILLA189)
        lateinit var getNetHandler: Method

        @WrapField(mcpName = "currentServerData", targetMap = MapEnum.VANILLA189)
        lateinit var currentServerData: Field

        @WrapField(mcpName = "fontRendererObj", targetMap = MapEnum.VANILLA189)
        lateinit var fontRendererObj: Field

        @WrapField(mcpName = "pointedEntity", targetMap = MapEnum.VANILLA189)
        lateinit var pointedEntity: Field

        @WrapField(mcpName = "renderViewEntity", targetMap = MapEnum.VANILLA189)
        lateinit var renderViewEntity: Field

        @WrapMethod(mcpName = "loadWorld", targetMap = MapEnum.VANILLA189)
        lateinit var loadWorld: Method

        @WrapField(mcpName = "entityRenderer", targetMap = MapEnum.VANILLA189)
        lateinit var entityRenderer: Field

        @WrapField(mcpName = "renderGlobal", targetMap = MapEnum.VANILLA189)
        lateinit var renderGlobal: Field

        @WrapMethod(
            mcpName = "addScheduledTask",
            targetMap = MapEnum.VANILLA189,
            signature = "(Ljava/lang/Runnable;)Lcom/google/common/util/concurrent/ListenableFuture;"
        )
        lateinit var addScheduledTask: Method

        val debugFPS: Int
            get() = ReflectUtil.invokeStatic(getDebugFPS) as Int
        val minecraft: Minecraft
            get() = Minecraft(ReflectUtil.invokeStatic(getMinecraft)!!)
    }
}