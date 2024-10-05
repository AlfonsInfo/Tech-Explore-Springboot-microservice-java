package spring.template.encoding;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import spring.template.encoding.utility.encode_decode.EncodeDecodeBase64;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EncodeDecodeBase64Test {


    EncodeDecodeBase64 encodeDecodeBase64 = new EncodeDecodeBase64();

    @Test
    public void testEncode() {
        String originalInput = "Hello, World!";
        String encodedInput = encodeDecodeBase64.encode(originalInput);
        assertNotNull(encodedInput);
        assertNotEquals(originalInput, encodedInput); // Pastikan hasil encoding tidak sama dengan input
        System.out.println("Encoded: " + encodedInput);
    }

    @Test
    public void testDecode() {
        String originalInput = "Hello, World!";
        String encodedInput = encodeDecodeBase64.encode(originalInput);
        String decodedInput = encodeDecodeBase64.decode(encodedInput);

        assertEquals(originalInput, decodedInput);
        System.out.println("Decoded: " + decodedInput);
    }
}

