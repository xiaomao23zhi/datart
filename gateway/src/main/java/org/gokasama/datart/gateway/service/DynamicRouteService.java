package org.gokasama.datart.gateway.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 *
 */
@Component
@Slf4j
public class DynamicRouteService implements ApplicationEventPublisherAware {

    private final RouteDefinitionWriter routeDefinitionWriter;
    private ApplicationEventPublisher publisher;

    public DynamicRouteService(RouteDefinitionWriter routeDefinitionWriter) {
        this.routeDefinitionWriter = routeDefinitionWriter;
    }

    /**
     * 添加路由实体类
     * @param definition
     * @return
     */
    public boolean add(RouteDefinition definition){
        routeDefinitionWriter.save((Mono<RouteDefinition>) Mono.just(definition).subscribe());
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
        return true;
    }
    /**
     *
     * @param definition 路由实体类
     * @return
     */
    public boolean update(RouteDefinition definition){
        try {
            routeDefinitionWriter.delete(Mono.just(definition.getId()));
        }catch (Exception e){
            log.error("update 失败。没有找到对应的路由ID :{}",definition.getId());
        }
        routeDefinitionWriter.save((Mono<RouteDefinition>) (Mono.just(definition)).subscribe());
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
        return true;
    }
    /**
     * serviceId
     * @param id
     * @return
     */
    public boolean del(String id){
        routeDefinitionWriter.delete(Mono.just(id));
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
        return true;
    }
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}
