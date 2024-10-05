package spring.template.encoding;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import spring.template.encoding.utility.encode_decode.EncodeDecodeBase64;
import spring.template.encoding.utility.encode_decode.EncodeDecodeHex;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = EncodeDecodeHex.class)
public class EncodeDecodeHexTest {
    EncodeDecodeHex encodeDecodeHex = new EncodeDecodeHex();

    @Test
    public void testEncode() {
        String originalInput = "Hello, World!";
        String encodedInput = encodeDecodeHex.encode(originalInput);
        assertNotNull(encodedInput);
        assertNotEquals(originalInput, encodedInput); // Pastikan hasil encoding tidak sama dengan input
        System.out.println("Encoded Hex: " + encodedInput);
    }

    @Test
    public void testDecode() {
        String originalInput = "Hello, World!";
        String encodedInput = encodeDecodeHex.encode(originalInput);
        String decodedInput = encodeDecodeHex.decode(encodedInput);

        assertEquals(originalInput, decodedInput); // Pastikan hasil decoding sama dengan input asli
        System.out.println("Decoded: " + decodedInput);
    }
}

