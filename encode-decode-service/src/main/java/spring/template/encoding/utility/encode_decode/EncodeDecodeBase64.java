package spring.template.encoding.utility.encode_decode;

import java.util.Base64;

public class EncodeDecodeBase64 {
    public String encode(String input) {
        return Base64.getEncoder().encodeToString(input.getBytes());
    }

    public String decode(String encodedInput) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedInput);
        return new String(decodedBytes);
    }
}
