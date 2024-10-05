package spring.template.learn.junit5;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.commons.util.StringUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
//Extend with a dependency
//@DisplayName("Junit 5 Test")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Junit5Test {

    @Test
    @DisplayName("Addition Test")
    void addition() {
        int a = 1;
        int b = 2;
        int result = a + b;
        log.info("Result: {}", result);
        assertEquals(3 , result);
    }


    //Parameterized Test
    @DisplayName("Parameterized Test")
    @ParameterizedTest
    @ValueSource(strings = {"Hello", "World"})
    void testWithStringParameter(String argument) {
        log.info("Argument: {}", argument);
        assertTrue(StringUtils.isNotBlank(argument));
    }

    @DisplayName("Repeated Test")
    @RepeatedTest(10)
    void testRepeated() {
        log.info("Repeated Test");
    }

    @Test
    void targetDisplayNameGenerator() {
        //given
        String name = "John";
        //when
        String result = "Hello " + name;
        //then
        log.info("Result: {}", result);
        assertEquals("Hello John", result);
    }

}
