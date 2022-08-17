package test;

import net.minecraftforge.fml.common.Mod;
import rbq.wtf.lycoris.client.Client;

import javax.swing.*;

@Mod(modid = "Lycoris Client")
public class loader {

    public loader() {
        System.out.println("Print");
        new Thread(() -> {
            JOptionPane.showConfirmDialog(null, "Wait");
            new Client();
        }).start();
    }
}
