import rbq.wtf.lycoris.client.Client;

public class Loader { //因为需要由Native调用，请勿转成Kotlin
    public Loader() {
        new Thread(Client.INSTANCE::start).start();
    }
}
