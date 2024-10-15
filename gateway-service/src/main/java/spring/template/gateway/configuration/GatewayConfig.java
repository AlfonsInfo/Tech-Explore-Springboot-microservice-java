package spring.template.gateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("product-service",
                        predicateSpec -> predicateSpec.path("/product/**")
                                .filters(gatewayFilterSpec -> gatewayFilterSpec.rewritePath("/gateway/product/(?<segment>.*)", "/product/${segment}"))
                                .uri("lb://product-service"))
                .build();
    }
}
