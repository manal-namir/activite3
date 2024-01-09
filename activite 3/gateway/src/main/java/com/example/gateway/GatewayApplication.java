package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r->r.path("/customers/**").uri("http://localhost:8081/"))
                //.route(r->r.path("/customers/**").uri("lb://CUSTOMER_SERVICE")) //lorsqu'une requete est envoyer vers la gateway avec le pass /customers envoie la requete vers le service appellé customer_service avec la discovery on a plus besoin de connaitre ladresse de chaq MS
                .route(r->r.path("/products/**").uri("http://localhost:8082/"))
                //.route(r->r.path("/products/**").uri("lb://INVENTORY_SERVICE"))
                .build();
    }

    //la gateway ne connait pas les adresses de MS elle connait seulement leurs nom et ils sont deja enregistrer avec ce nom dans discovery avec leurs adresse
    //http://localhost:8880/customers
    /*@Bean
    public RouteLocator routes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r -> r.path("/customers/**").uri("lb://CUSTOMER_SERVICE"))
                .route(r -> r.path("/products/**").uri("lb://INVENTORY_SERVICE"))
                .build();
    }*/

    //configuration dynamic de la gateway
    //mnt dans le path il faut preciser le nom de MS http://localhost:8880/CUSTOMER_SERVICE/customers
    //@Bean
    public DiscoveryClientRouteDefinitionLocator dynamicRoutes(ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties dlp){
        return new DiscoveryClientRouteDefinitionLocator(rdc,dlp);
    }
}
