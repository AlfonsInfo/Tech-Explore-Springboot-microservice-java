package spring.template.learn;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;


public class ExampleTest {

    @Test
    void testMethodReference() {
        // Given
        List<String> list = Arrays.asList("a", "b", "c");
        // When
        list.forEach( s -> System.out.println(s));
        list.forEach(System.out::println);
        // Then
    }
}
