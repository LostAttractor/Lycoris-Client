import rbq.wtf.lycoris.client.Client;

import javax.swing.*;

public class Loader {
    public Loader() {
        new Thread(Client.INSTANCE::start).start();
    }
}
