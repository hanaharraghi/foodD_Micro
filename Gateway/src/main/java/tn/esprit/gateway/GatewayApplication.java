package tn.esprit.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;


@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("UserService", r -> r.path("/users/**")
                        .uri("lb://USERSERVICE"))

                .route("JobGestion", r -> r.path("/api/restaurants/**")
                        .uri("lb://JOBGESTION"))

                .route("JobGestion", r -> r.path("/api/restaurants/**", "/api/menus/**", "/api/foods/**")
                        .uri("lb://JOBGESTION"))

                .route("JobGestion", r -> r.path("/api/**")
                        .uri("lb://JOBGESTION"))

                .route("FeedbackGestion", r -> r.path("/api/feedbacks/**")
                        .uri("lb://FEEDBACKGESTION"))

                .route("OrderManagement", r -> r.path("/orders/**")
                        .uri("lb://ORDERMANAGEMENT"))

                .route("DeliveryANDTrackingService", r -> r.path("/api/deliveries/**")
                        .uri("lb://DELIVERYANDTRACKING"))


                .build();


    }
}
