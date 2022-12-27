import rbq.lycoris.client.Client
import javax.swing.JOptionPane

class Loader {
    init {
        Thread {Client.start()}.start()
    }
}