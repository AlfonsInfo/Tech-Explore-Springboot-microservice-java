package spring.template.crypto.dto;

import lombok.Data;

@Data
public class ReqCaesarCipherDto {
    private String data;
    private int shift;
}
