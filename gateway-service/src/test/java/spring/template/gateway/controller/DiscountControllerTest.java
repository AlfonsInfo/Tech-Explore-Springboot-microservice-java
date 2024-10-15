package spring.template.gateway.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import spring.template.gateway.dto.discount.UpsertDiscountDto;
import spring.template.gateway.service.DiscountService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@Slf4j
@DisplayName("Discount Controller Test")
@ActiveProfiles("sit")
@AutoConfigureMockMvc
class DiscountControllerTest {

    @MockBean
    private DiscountController discountController;

    // mocking
    @Autowired
    private MockMvc mockMvc;


    @Mock // using this annotation to mock the dependencies
    private DiscountService discountService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @AfterAll
    static void tearDown() {
        log.info("All test has been executed");
    }


    @Test
    void givenNewDiscount_whenDiscountIsNotUnique_thenShouldReturnError() throws Exception {
        UpsertDiscountDto upsertDiscountDto = new UpsertDiscountDto();
        upsertDiscountDto.setCode("DISCOUNT_CODE");
        upsertDiscountDto.setPercentage(10);

        // Mock the service to throw a custom exception
        doThrow(new RuntimeException("Discount code is not unique"))
                .when(discountService).createDiscount(any(UpsertDiscountDto.class));

        mockMvc.perform(post("/discounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"discountCode\": \"DISCOUNT_CODE\",\n" +
                                "    \"discountPercentage\": 10\n" +
                                "}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(discountService, times(1)).createDiscount(any(UpsertDiscountDto.class));
    }


}