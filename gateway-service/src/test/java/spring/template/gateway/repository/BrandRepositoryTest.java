package spring.template.gateway.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import spring.template.gateway.entity.BrandEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("sit")
@Slf4j

//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class BrandRepositoryTest {

    @Autowired
    private BrandRepository brandRepository;

    //given_when_then
    @Test
    void brandRepository_saveAll_thenFindAll() {
        //given
        BrandEntity brandEntity = BrandEntity.builder().name("Brand 1").build();
        BrandEntity brandEntity2 = BrandEntity.builder().name("Brand 2").build();
        List<BrandEntity> brandEntities = List.of(brandEntity, brandEntity2);

        //when
        brandRepository.saveAll(brandEntities);
        var brands = brandRepository.findAll();

        //then
        assertThat(brands).isNotEmpty().hasSize(2);
    }

    @Test
    void brandRepository_save_ThenExistsByName() {
        //given
        BrandEntity brandEntity = BrandEntity.builder().name("Brand 1").build();

        //when
        brandRepository.save(brandEntity);
        var exists = brandRepository.existsByName("Brand 1");

        //then
        assertThat(exists).isTrue();
    }
}