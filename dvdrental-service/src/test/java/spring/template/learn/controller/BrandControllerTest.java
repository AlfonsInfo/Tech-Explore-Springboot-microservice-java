package spring.template.learn.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import spring.template.learn.dto.ResMessageDto;
import spring.template.learn.dto.brand.BrandResponseDto;
import spring.template.learn.dto.brand.UpsertBrandRequestDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Slf4j
@DisplayName("Brand Controller Test")
@ActiveProfiles("sit")
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class BrandControllerTest {

    //this
    @InjectMocks // using this annotation to class under test //mockito manage the creation and injection of mocks into the class under test
    BrandController brandController;

    // mocking
    @Autowired
    private MockMvc mockMvc;

    @Mock // using this annotation to mock the dependencies
    BrandService brandService;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @AfterAll
    static void tearDown() {
        log.info("All test has been executed");
    }



    @Test
    @DisplayName("Create Brand Test (Method test)")
    void whenCreateBrand_thenReturnSuccessMessage() {
        // given
        UpsertBrandRequestDto mockRequestBody = new UpsertBrandRequestDto(1L,"Brand 1","Brand 1 Description");
        ResMessageDto<Void> mockResponse = ResMessageDto.<Void>builder()
                .message("Brand created successfully")
                .statusCode(201)
                .build();
        // when
        when(brandService.createBrand(mockRequestBody))
        .thenReturn(mockResponse);

        // actual
        var actual = brandController.createBrand(mockRequestBody);

        log.info(actual.toString());
        // then
        assertNotNull(actual);
        assertEquals("Brand created successfully", actual.getMessage());
        assertEquals(201, actual.getStatusCode());
    }

    @Test
    @DisplayName("Brand Test (Hit API)")
    void whenHitApiCreateBrand_thenReturnSuccessMessage() throws Exception {
        // given
        mockMvc.perform(MockMvcRequestBuilders.post("/brands")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "name": "Brand X",
                                "description": "Brand 1 Description"
                            }
                        """))
                .andExpect(status().isCreated())
                .andExpect(result -> {
                    String content = result.getResponse().getContentAsString();
                    log.info(content);
                    assertTrue(content.contains("Brand created successfully"));
                });
    }

    @Test
    @DisplayName("Brand Test (Hit API) - Error")
    void givenNullName_whenHitApiCreateBrand_thenReturnError() throws Exception {
        // given
        mockMvc.perform(MockMvcRequestBuilders.post("/brands")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "name": null,
                                "description": "Brand 1 Description"
                            }
                        """))
                .andExpect(status().isBadRequest());

    }

    // Test Update Brand
    @Test
    @DisplayName("Update Brand Test (Method test)")
    void givenValidBrand_whenHitUpdateBrand_thenReturnSuccessMessage() {
        //given
        UpsertBrandRequestDto mockRequestBody = new UpsertBrandRequestDto(1L,"Brand 1","Brand 1 Description");
        ResMessageDto<Void> mockResponse = ResMessageDto.<Void>builder()
                .message("Brand updated successfully")
                .statusCode(200)
                .build();
        //when
        when(brandService.updateBrand(mockRequestBody))
                .thenReturn(mockResponse);
        //actual
        var actual = brandController.updateBrand(mockRequestBody);
        //then
        assertNotNull(actual);
        assertEquals("Brand updated successfully", actual.getMessage());
        assertEquals(200, actual.getStatusCode());
    }

    @Test
    @DisplayName("Update Brand Test (Hit API)")
    void givenValidBrand_whenHitApiUpdateBrand_thenReturnSuccessMessage() throws Exception {
        //given
        mockMvc.perform(MockMvcRequestBuilders.put("/brands")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "id": 1,
                                "name": "Brand 1",
                                "description": "Brand 1 Description"
                            }
                        """))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String content = result.getResponse().getContentAsString();
                    log.info(content);
                    assertTrue(content.contains("Brand updated successfully"));
                });
    }


    @Test
    @DisplayName("Update Brand Test (Hit API) - Error")
    void givenNullName_whenHitApiUpdateBrand_thenReturnError() throws Exception {
        //given
        mockMvc.perform(MockMvcRequestBuilders.put("/brands")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "name": null,
                                "description": "Brand 1 Description"
                            }
                        """))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Get All Brand Test (Method test)")
    void givenValidBrand_whenHitGetAllBrand_thenReturnSuccessMessage() {
        //given
        ResMessageDto<List<BrandResponseDto>> mockResponse = ResMessageDto.<List<BrandResponseDto>>builder()
                .message("Get all brand success")
                .data(List.of(new BrandResponseDto(1L,"Brand 1","Brand 1 Description")))
                .statusCode(200)
                .build();

        //when
        when(brandService.getAllBrand()).thenReturn(mockResponse);
        //actual
        var actual = brandController.getAllBrand();
        //then
        assertNotNull(actual);
        assertEquals("Get all brand success", actual.getMessage());
        assertEquals(200, actual.getStatusCode());
    }

    @Test
    @DisplayName("Get All Brand Test (Hit API)")
    void givenValidBrand_whenHitApiGetAllBrand_thenReturnSuccessMessage() throws Exception {
        when(brandService.getAllBrand()).thenReturn(ResMessageDto.<List<BrandResponseDto>>builder()
                .message("Get all brand success")
                .data(List.of(new BrandResponseDto(1L,"Brand 1","Brand 1 Ajah")))
                .statusCode(200)
                .build());


        mockMvc.perform(MockMvcRequestBuilders.get("/brands")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String content = result.getResponse().getContentAsString();
                    log.info(content);
                    assertTrue(content.contains("Get all brand success"));
                });

        // Verification (ensure brandService.getAllBrand() is called)
        verify(brandService).getAllBrand();
    }

}