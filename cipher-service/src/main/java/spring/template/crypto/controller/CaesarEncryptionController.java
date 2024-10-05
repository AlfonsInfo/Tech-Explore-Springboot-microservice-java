package spring.template.crypto.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.template.crypto.cipher.CaesarCipher;
import spring.template.crypto.dto.ReqCaesarCipherDto;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/caesar")
public class CaesarEncryptionController {

    private final CaesarCipher caesarCipher;


    @PostMapping("/encrypt")
    public String encrypt(
            @RequestBody ReqCaesarCipherDto reqCaesarCipherDto
    ){
        return caesarCipher.encrypt(reqCaesarCipherDto.getData(), reqCaesarCipherDto.getShift());
    }

    @PostMapping("/decrypt")
    public String decrypt(
            @RequestBody ReqCaesarCipherDto reqCaesarCipherDto
    ){
        return caesarCipher.decrypt(
                reqCaesarCipherDto.getData(),
                reqCaesarCipherDto.getShift()
        );
    }

    @GetMapping("/brute-force-demo")
    public List<String> doBruteorce(String data){
        List<String> results = new ArrayList<>();
        for(int i = 0 ; i < 25 ; i++){
            results.add(caesarCipher.decrypt(data,i));
        }
        return results;
    }

}
