package rbq.wtf.lycoris.client.utils;

import java.security.MessageDigest;

public class HWIDUtil {

    public static String getHWID() {
        try {
            String hwidmain = System.getenv("PROCESSOR_IDENTIFIER")
                    + System.getenv("PROCESSOR_LEVEL")
                    + System.getenv("PROCESSOR_REVISION")
                    + System.getenv("PROCESSOR_ARCHITECTURE")
                    + System.getenv("PROCESSOR_ARCHITEW6432")
                    + System.getenv("NUMBER_OF_PROCESSORS")
                    + System.getenv("COMPUTERNAME")
                    + System.getProperty("user.name");
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(hwidmain.getBytes());
            StringBuffer hexString = new StringBuffer();

            byte byteData[] = md.digest();

            for (byte aByteData : byteData) {
                String hex = Integer.toHexString(0xff & aByteData);
                hexString.append(hex);

            }
            return hexString.toString();
        } catch (Exception e) {
            e.printStackTrace();

            return "Error";
        }
    }
}
