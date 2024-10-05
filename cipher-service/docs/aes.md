# AES

## Tujuan
Tujuan dari AES (Advanced Encryption Standard) adalah untuk mengamankan informasi dari pihak-pihak yang tidak berhak.

## How AES Works
1. AES menggunakan blok 128-bit sebagai input.
2. AES menggunakan kunci 128-bit, 192-bit, atau 256-bit sebagai input.
3. AES menggunakan 10 round (iterasi) untuk 128-bit key, 12 round untuk 192-bit key, dan 14 round untuk 256-bit key.
4. AES menggunakan SubBytes, ShiftRows, MixColumns, dan AddRoundKey untuk enkripsi dan dekripsi.
5. AES menggunakan key schedule untuk menghasilkan subkey.
6. AES menggunakan mode ECB, CBC, CFB, OFB, dan CTR untuk enkripsi dan dekripsi.
7. AES menggunakan padding PKCS5, PKCS7, ISO10126, dan Zero untuk enkripsi dan dekripsi.
8. AES menggunakan IV (Initialization Vector) untuk mode CBC, CFB, OFB, dan CTR.
9. AES menggunakan nonce untuk mode CTR.
10. AES menggunakan MAC (Message Authentication Code) untuk integritas data.
11. AES menggunakan HMAC (Hash-based Message Authentication Code) untuk integritas data.