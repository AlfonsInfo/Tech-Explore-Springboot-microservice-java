package spring.template.learn.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.template.learn.dto.ResMessageDto;
import spring.template.learn.dto.discount.UpsertDiscountDto;
import spring.template.learn.repository.DiscountRepository;

@Service
@RequiredArgsConstructor
public class DiscountService {

    //repository
    private final DiscountRepository discountRepository;


    //Create Discount
    public ResMessageDto<Void> createDiscount(UpsertDiscountDto discountDto) {

        return ResMessageDto.<Void>builder()
                .message("Discount created successfully")
                .statusCode(201)
                .build();
    }


}
