import net.minecraftforge.fml.common.Mod
import rbq.lycoris.client.Client
import javax.swing.JOptionPane

@Mod(modid = "Lycoris Client")
class ModLoader {
    init {
        Thread {
            JOptionPane.showConfirmDialog(null, "Please wait for minecraft launch...")
            Client.start()
        }.start()
    }
}