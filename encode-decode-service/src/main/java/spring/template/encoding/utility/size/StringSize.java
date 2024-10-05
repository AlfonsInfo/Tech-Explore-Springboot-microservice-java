package spring.template.encoding.utility.size;

public class StringSize {
    public static void main(String[] args) {
        String originalString = "Hello, World!"; // String yang ingin diperiksa

        // Mengonversi string ke array byte
        byte[] byteArray = originalString.getBytes(); // Menggunakan encoding default (UTF-8)

        // Menampilkan ukuran byte
        System.out.println("Ukuran byte dari string: " + byteArray.length);

        // Jika ingin mengetahui byte per karakter
        System.out.println("Ukuran byte per karakter: " + (double) byteArray.length / originalString.length());
    }
}
