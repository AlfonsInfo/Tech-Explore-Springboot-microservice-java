package spring.template.learn.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import spring.template.learn.dto.brand.BrandResponseDto;
import spring.template.learn.dto.brand.UpsertBrandRequestDto;
import spring.template.learn.entity.BrandEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    @Mapping(target = "id")
    BrandEntity upsertBrandRequestDtoToBrandEntity(UpsertBrandRequestDto request);

    //brandEntitiesToBrandResponseDtos
    List<BrandResponseDto> brandEntitiesToBrandResponseDtos(List<BrandEntity> brandEntities);
}
