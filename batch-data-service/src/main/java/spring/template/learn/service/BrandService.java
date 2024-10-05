package spring.template.learn.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import spring.template.learn.dto.ResMessageDto;
import spring.template.learn.dto.brand.BrandResponseDto;
import spring.template.learn.dto.brand.UpsertBrandRequestDto;
import spring.template.learn.entity.BrandEntity;
import spring.template.learn.mapper.BrandMapper;
import spring.template.learn.repository.BrandRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {

    //mapper
    private final BrandMapper brandMapper;

    //repository
    private final BrandRepository brandRepository;


    public ResMessageDto<Void> createBrand(UpsertBrandRequestDto request) {
        BrandEntity brandEntity = brandMapper.upsertBrandRequestDtoToBrandEntity(request);
        brandRepository.save(brandEntity);
        return ResMessageDto.<Void>builder()
                .message("Brand created successfully")
                .statusCode(HttpStatus.CREATED.value())
                .build();
    }

    public ResMessageDto<Void> updateBrand(UpsertBrandRequestDto request) {
        BrandEntity brandEntity = brandMapper.upsertBrandRequestDtoToBrandEntity(request);
        brandRepository.save(brandEntity);
        return ResMessageDto.<Void>builder()
                .message("Brand updated successfully")
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    //getAllBrand
    public ResMessageDto<List<BrandResponseDto>> getAllBrand() {
        List<BrandEntity> brandEntities = brandRepository.findAll();
        List<BrandResponseDto> brandResponseDtos = brandMapper.brandEntitiesToBrandResponseDtos(brandEntities);
        return ResMessageDto.<List<BrandResponseDto>>builder()
                .data(brandResponseDtos)
                .message("Get all brand success")
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    public void deleteBrand(Long brandId) {
        brandRepository.deleteById(brandId);
    }
}
