package rbq.wtf.lycoris.client.config.configs

import com.google.gson.JsonElement
import com.google.gson.JsonNull
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import rbq.wtf.lycoris.client.Client
import rbq.wtf.lycoris.client.config.FileConfig
import rbq.wtf.lycoris.client.manager.ConfigManager
import rbq.wtf.lycoris.client.module.Module
import java.io.*

class ModulesConfig(file: File) : FileConfig(file) {
    /**
     * Load config from file
     *
     * @throws IOException
     */
    @Throws(IOException::class)
    override fun loadConfig() {
        val jsonElement = JsonParser().parse(BufferedReader(FileReader(file)))
        if (jsonElement is JsonNull) return
        val entryIterator: Iterator<Map.Entry<String, JsonElement>> =
            jsonElement.asJsonObject.entrySet().iterator()
        while (entryIterator.hasNext()) {
            val (key, value) = entryIterator.next()
            val module: Module = Client.moduleManager.getModuleByName(key)!!
            val jsonModule = value as JsonObject
            module.state = jsonModule["State"].asBoolean
            module.keyBind = jsonModule["KeyBind"].asInt
            if (jsonModule.has("Array")) module.array = jsonModule["Array"].asBoolean
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
        for (module in Client.moduleManager.modules.filter { it.saveState }) {
            val jsonMod = JsonObject()
            jsonMod.addProperty("State", module.state)
            jsonMod.addProperty("KeyBind", module.keyBind)
            jsonMod.addProperty("Array", module.array)
            jsonObject.add(module.name, jsonMod)
        }
        val printWriter = PrintWriter(FileWriter(file))
        printWriter.println(ConfigManager.PRETTY_GSON.toJson(jsonObject))
        printWriter.close()
    }
}
