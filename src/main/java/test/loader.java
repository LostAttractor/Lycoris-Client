package test;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import rbq.wtf.lycoris.client.LycorisClient;

import javax.swing.*;
@Mod(modid = "Lycoris Client")
public class loader {

    public loader() {
        System.out.println("Print");
        new Thread(() -> {
            JOptionPane.showConfirmDialog(null,"Wait");
            new LycorisClient();
        }).start();
    }
}
