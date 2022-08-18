package test;

import net.minecraftforge.fml.common.Mod;
import rbq.wtf.lycoris.client.Client;

import javax.swing.*;

@Mod(modid = "Lycoris Client")
public class Loader {

    public Loader() {
        new Thread(() -> {
            if (Client.developEnv)
                JOptionPane.showConfirmDialog(null, "Wait");
            new Client();
        }).start();
    }
}
