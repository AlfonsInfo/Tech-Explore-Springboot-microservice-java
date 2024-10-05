package spring.template.encoding.utility.encode_decode;



public class EncodeDecodeHex {
    // Metode untuk mengencode string ke format Hexadecimal
    public String encode(String input) {
        StringBuilder hexString = new StringBuilder();
        for (char c : input.toCharArray()) {
            hexString.append(String.format("%02x", (int) c));
        }
        return hexString.toString();
    }

    // Metode untuk mendecode string dari format Hexadecimal
    public String decode(String hexInput) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < hexInput.length(); i += 2) {
            String str = hexInput.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }
        return output.toString();
    }
}
