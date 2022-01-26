package com.lzj.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 网关配置方式：1. yml文件配置、2. 配置类配置
 * 网关配置类
 */
@Configuration
public class GatewayConfig {

    /**
     * 注：path值必须与uri路径最后一个/后的值一致
     * @param routeLocatorBuilder
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route_lzj",
                r -> r.path("/guonei").uri("http://news.baidu.com/guonei")).build();
        return routes.build();
    }

    /**
     * 注：path值必须与uri路径最后一个/后的值一致
     * @param routeLocatorBuilder
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator2(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route_lzj",
                r -> r.path("/guoji").uri("http://news.baidu.com/guoji")).build();
        return routes.build();
    }

}
