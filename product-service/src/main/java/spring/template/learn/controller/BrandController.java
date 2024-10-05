package spring.template.learn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.template.learn.dto.ResMessageDto;
import spring.template.learn.dto.brand.BrandResponseDto;
import spring.template.learn.dto.brand.UpsertBrandRequestDto;
import spring.template.learn.service.BrandService;
import spring.template.learn.validation.groups.Update;

import java.util.List;

@RestController
@RequestMapping("/brands")
@RequiredArgsConstructor
@Validated
public class BrandController {

    private final BrandService brandService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResMessageDto<Void> createBrand(
            @Validated
            @RequestBody UpsertBrandRequestDto brandDto
    ) {
        return brandService.createBrand(brandDto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    ResMessageDto<Void> updateBrand(
            @RequestBody
            @Validated({Update.class})
            UpsertBrandRequestDto brandDto
    ) {
        brandService.updateBrand(brandDto);
        return ResMessageDto.<Void>builder()
                .message("Brand updated successfully")
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    //Get All Brand
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    ResMessageDto<List<BrandResponseDto>> getAllBrand() {
        return brandService.getAllBrand();
    }


    //Delete Brand
    @DeleteMapping("/{brandId}")
    @ResponseStatus(HttpStatus.OK)
    ResMessageDto<Void> deleteBrand(
            @PathVariable
            Long brandId
    ) {
        brandService.deleteBrand(brandId);
        return ResMessageDto.<Void>builder()
                .message("Brand deleted successfully")
                .statusCode(HttpStatus.OK.value())
                .build();
    }
}
