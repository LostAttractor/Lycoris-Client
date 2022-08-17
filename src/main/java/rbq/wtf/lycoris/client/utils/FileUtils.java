package rbq.wtf.lycoris.client.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {
    public static String readFileByPath(Path path) throws IOException {
        InputStream stream = Files.newInputStream(path);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[512000];

        int length;
        while ((length = stream.read(buffer)) != -1) {
            bos.write(buffer, 0, length);
        }
        return bos.toString();
    }
}
