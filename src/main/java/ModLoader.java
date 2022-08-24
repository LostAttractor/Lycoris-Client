import net.minecraftforge.fml.common.Mod;
import rbq.wtf.lycoris.client.Client;

import javax.swing.*;

@Mod(modid = "Lycoris Client")
public class ModLoader {

    public ModLoader() {
        new Thread(() -> {
            JOptionPane.showConfirmDialog(null, "Please wait for minecraft launch...");
            Client.INSTANCE.start();
        }).start();
    }
}
