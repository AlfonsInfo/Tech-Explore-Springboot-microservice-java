package spring.template.learn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.template.learn.dto.ResMessageDto;
import spring.template.learn.dto.discount.UpsertDiscountDto;
import spring.template.learn.service.DiscountService;

@RestController
@RequestMapping("/discounts")
@RequiredArgsConstructor
@Validated
public class DiscountController {

    private final DiscountService discountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResMessageDto<Void> createDiscount(
            @Validated
            @RequestBody UpsertDiscountDto discountDto
    ) {
        return discountService.createDiscount(discountDto);
    }
}
