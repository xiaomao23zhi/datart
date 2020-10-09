package org.gokasama.datart.gateway.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

@Component
@Slf4j
public class DynamicRouteServiceByNacos {

    private final DynamicRouteService dynamicRouteService;

    private final String SERVER_ADDR = "192.168.1.109:8848";

    private final String DATA_ID = "gateway-router";

    private final String GROUP_ID = "DEFAULT_GROUP";


    public DynamicRouteServiceByNacos(DynamicRouteService dynamicRouteService) {
        this.dynamicRouteService = dynamicRouteService;

        dynamicRouteListener(DATA_ID,GROUP_ID);
    }

    /**
     * @param dataId
     * @param group
     */
    public void dynamicRouteListener(String dataId, String group) {
        try {
            ConfigService configService = NacosFactory.createConfigService(SERVER_ADDR);
            String content = configService.getConfig(dataId, group, 5000);

            log.info(content);

            configService.addListener(dataId, group, new Listener() {
                @Override
                public void receiveConfigInfo(String configInfo) {
                    RouteDefinition definition = JSON.parseObject(configInfo, RouteDefinition.class);
                    dynamicRouteService.update(definition);
                }

                @Override
                public Executor getExecutor() {
                    return null;
                }
            });
        } catch (NacosException e) {
            //todo: Exception handler
        }
    }
}
