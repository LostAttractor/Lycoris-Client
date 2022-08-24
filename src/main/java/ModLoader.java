import net.minecraftforge.fml.common.Mod;
import rbq.wtf.lycoris.client.Client;

import javax.swing.*;

@Mod(modid = "Lycoris Client")
public class ModLoader {

    public ModLoader() {
        new Thread(() -> {
            if (Client.developEnv)
                JOptionPane.showConfirmDialog(null, "Wait");
            Client.INSTANCE.start();
        }).start();
    }
}
