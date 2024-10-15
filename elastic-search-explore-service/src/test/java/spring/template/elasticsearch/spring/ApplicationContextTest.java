package spring.template.elasticsearch.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Slf4j
public class ApplicationContextTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void testContextLoads() {
        System.out.println("Beans in context: " + context.getBeanDefinitionCount());
        int index = 1;
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.println("Bean " + index + ":" + beanName);
            index++;
        }
        assertTrue(context.getBeanDefinitionCount() > 0);
    }





}
