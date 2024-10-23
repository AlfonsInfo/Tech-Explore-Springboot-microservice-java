package spring.template.learn.dto.address;

import lombok.Data;

@Data
public class AddressResponseDto {
    private Long addressId;
    private String address;
    private String address2;
    private String district;
    private Long cityId;
    private String postalCode;
    private String phone;
    private String lastUpdate;
}
