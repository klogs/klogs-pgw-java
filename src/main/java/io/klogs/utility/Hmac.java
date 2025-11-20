package io.klogs.utility;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Hmac {
    private static final String UTF8 = "UTF-8";
    private  static  final  String ALG = "HmacSHA256";

    public static String Sha256(String cipherText, String key) {
        try {
            var hmac = Mac.getInstance(ALG);

            var spec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), ALG);

            hmac.init(spec);

            var bytes = hmac.doFinal(cipherText.getBytes(StandardCharsets.UTF_8));

            return Base64.getEncoder().encodeToString(bytes);

            //return Hex.encodeHexString();
        } catch (Exception ex) {
            return null;
        }
    }
}
