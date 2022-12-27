package rbq.lycoris.client.config

import java.io.File
import java.io.IOException

abstract class FileConfig(val file: File) {

    /**
     * Load config from file
     *
     * @throws IOException
     */
    @Throws(IOException::class)
    abstract fun loadConfig()

    /**
     * Save config to file
     *
     * @throws IOException
     */
    @Throws(IOException::class)
    abstract fun saveConfig()

    /**
     * Create config
     *
     * @throws IOException
     */
    @Throws(IOException::class)
    fun createConfig() {
        file.createNewFile()
    }

    /**
     * @return config file exist
     */
    fun hasConfig(): Boolean {
        return file.exists()
    }
}