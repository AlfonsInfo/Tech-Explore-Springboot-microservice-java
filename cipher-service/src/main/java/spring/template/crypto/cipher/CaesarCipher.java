package spring.template.crypto.cipher;

import org.springframework.stereotype.Component;

@Component
public class CaesarCipher {
    public String encrypt(String plainText, int shift) {
        StringBuilder result = new StringBuilder();
        for (char character : plainText.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isLowerCase(character) ? 'a' : 'A';
                result.append((char) ((character - base + shift) % 26 + base));
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }

    public String decrypt(String cipherText, int shift) {
        return encrypt(cipherText, 26 - shift);
    }

}
