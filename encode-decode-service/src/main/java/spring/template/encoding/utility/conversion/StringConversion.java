package spring.template.encoding.utility.conversion;

public class StringConversion {

    // string ke byte
    public static byte[] stringToByte(String string) {
        byte[] bytes = string.getBytes();
        System.out.print("Original bytes: ");
        for (byte b : bytes) {
            // Each bytes number represent a character in ASCII/UTF-8 (ASCII 0-127, UTF-8 0-255)
            System.out.print(b + " ");
        }
        System.out.println();
        return bytes;
    }
}
