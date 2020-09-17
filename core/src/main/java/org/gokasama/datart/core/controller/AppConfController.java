package org.gokasama.datart.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.gokasama.datart.core.annotation.Auditing;
import org.gokasama.datart.core.exception.NotFoundException;
import org.gokasama.datart.core.model.AppConf;
import org.gokasama.datart.core.service.AppConfService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ka wujia@chinamobile.com
 */
@Api(tags = "管理接口", value = "Manager API")
@RestController
@RequestMapping("/api/v1/ac")
@Validated
@Slf4j
public class AppConfController {

    private final AppConfService appConfService;

    public AppConfController(AppConfService appConfService) {
        this.appConfService = appConfService;
    }

    @ApiOperation(value = "查看所有配置")
    @GetMapping("/")
    @Auditing(operation = "list")
    public List<AppConf> list() {

        return appConfService.findAll();
    }

    @ApiOperation(value = "查看配置")
    @GetMapping("/{key}")
    @Auditing(operation = "find")
    public AppConf find(@PathVariable String key) {

        if (appConfService.exist(key)) {
            return appConfService.find(key);
        } else {
            throw new NotFoundException("key-" + key);
        }
    }
}
