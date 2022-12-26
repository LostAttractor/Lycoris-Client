package rbq.lycoris.client.config.configs

/*
 * LiquidBounce Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge.
 * https://github.com/CCBlueX/LiquidBounce/
 */
import com.google.gson.JsonElement
import com.google.gson.JsonNull
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import rbq.lycoris.client.Client
import rbq.lycoris.client.config.FileConfig
import rbq.lycoris.client.manager.ConfigManager
import rbq.lycoris.client.module.Module
import java.io.*


class ValuesConfig(file: File) : FileConfig(file) {

    /**
     * Load config from file
     *
     * @throws IOException
     */
    @Throws(IOException::class)
    override fun loadConfig() {
        val jsonElement = JsonParser().parse(BufferedReader(FileReader(file)))
        if (jsonElement is JsonNull) return
        val jsonObject = jsonElement as JsonObject
        val iterator: Iterator<Map.Entry<String, JsonElement>> = jsonObject.entrySet().iterator()
        while (iterator.hasNext()) {
            val (key, value) = iterator.next()
            if (key.equals("CommandPrefix", ignoreCase = true)) {
                //Client.commandManager.setPrefix(value.asCharacter)
            } else if (key.equals("targets", ignoreCase = true)) {
                //val jsonValue = value as JsonObject
                //if (jsonValue.has("TargetPlayer")) EntityUtils.targetPlayer = jsonValue["TargetPlayer"].asBoolean
                //if (jsonValue.has("TargetMobs")) EntityUtils.targetMobs = jsonValue["TargetMobs"].asBoolean
                //if (jsonValue.has("TargetAnimals")) EntityUtils.targetAnimals = jsonValue["TargetAnimals"].asBoolean
                //if (jsonValue.has("TargetInvisible")) EntityUtils.targetInvisible = jsonValue["TargetInvisible"].asBoolean
                //if (jsonValue.has("TargetDead")) EntityUtils.targetDead = jsonValue["TargetDead"].asBoolean
            } else if (key.equals("features", ignoreCase = true)) {
                //val jsonValue = value as JsonObject
                //if (jsonValue.has("AntiForge")) AntiForge.enabled = jsonValue["AntiForge"].asBoolean
                //if (jsonValue.has("AntiForgeFML")) AntiForge.blockFML = jsonValue["AntiForgeFML"].asBoolean
                //if (jsonValue.has("AntiForgeProxy")) AntiForge.blockProxyPacket = jsonValue["AntiForgeProxy"].asBoolean
                //if (jsonValue.has("AntiForgePayloads")) AntiForge.blockPayloadPackets = jsonValue["AntiForgePayloads"].asBoolean
                //if (jsonValue.has("BungeeSpoof")) BungeeCordSpoof.enabled = jsonValue["BungeeSpoof"].asBoolean
                //if (jsonValue.has("AutoReconnectDelay")) AutoReconnect.INSTANCE.setDelay(jsonValue["AutoReconnectDelay"].asInt)
            } else {
                val module: Module = Client.moduleManager.getModuleByName(key)!!
                val jsonModule = value as JsonObject
                for (moduleValue in module.values) {
                    val element = jsonModule[moduleValue.name]
                    if (element != null) moduleValue.fromJson(element)
                }
            }
        }
    }

    /**
     * Save config to file
     *
     * @throws IOException
     */
    @Throws(IOException::class)
    override fun saveConfig() {
        val jsonObject = JsonObject()
        //jsonObject.addProperty("CommandPrefix", LiquidBounce.commandManager.getPrefix())
//        jsonObject.addProperty("ShowRichPresence", LiquidBounce.clientRichPresence.getShowRichPresenceValue())
//        val jsonTargets = JsonObject()
//        jsonTargets.addProperty("TargetPlayer", EntityUtils.targetPlayer)
//        jsonTargets.addProperty("TargetMobs", EntityUtils.targetMobs)
//        jsonTargets.addProperty("TargetAnimals", EntityUtils.targetAnimals)
//        jsonTargets.addProperty("TargetInvisible", EntityUtils.targetInvisible)
//        jsonTargets.addProperty("TargetDead", EntityUtils.targetDead)
//        jsonObject.add("targets", jsonTargets)
//        val jsonFeatures = JsonObject()
//        jsonFeatures.addProperty("AntiForge", AntiForge.enabled)
//        jsonFeatures.addProperty("AntiForgeFML", AntiForge.blockFML)
//        jsonFeatures.addProperty("AntiForgeProxy", AntiForge.blockProxyPacket)
//        jsonFeatures.addProperty("AntiForgePayloads", AntiForge.blockPayloadPackets)
//        jsonFeatures.addProperty("BungeeSpoof", BungeeCordSpoof.enabled)
//        jsonFeatures.addProperty("AutoReconnectDelay", AutoReconnect.INSTANCE.getDelay())
//        jsonObject.add("features", jsonFeatures)
//        val theAlteningObject = JsonObject()
//        theAlteningObject.addProperty("API-Key", GuiTheAltening.Companion.getApiKey())
//        jsonObject.add("thealtening", theAlteningObject)
//        val liquidChatObject = JsonObject()
//        liquidChatObject.addProperty("token", LiquidChat.Companion.getJwtToken())
//        jsonObject.add("liquidchat", liquidChatObject)
//        val capeObject = JsonObject()
//        capeObject.addProperty("TransferCode", GuiDonatorCape.Companion.getTransferCode())
//        capeObject.addProperty("CapeEnabled", GuiDonatorCape.Companion.getCapeEnabled())
//        jsonObject.add("DonatorCape", capeObject)
//        val backgroundObject = JsonObject()
//        backgroundObject.addProperty("Enabled", GuiBackground.Companion.getEnabled())
//        backgroundObject.addProperty("Particles", GuiBackground.Companion.getParticles())
//        jsonObject.add("Background", backgroundObject)
        Client.moduleManager.modules.stream().filter { module -> module.values.isNotEmpty() }
            .forEach { module ->
                val jsonModule = JsonObject()
                module.values.forEach { value -> jsonModule.add(value.name, value.toJson()) }
                jsonObject.add(module.name, jsonModule)
            }
        val printWriter = PrintWriter(FileWriter(file))
        printWriter.println(ConfigManager.PRETTY_GSON.toJson(jsonObject))
        printWriter.close()
    }
}
