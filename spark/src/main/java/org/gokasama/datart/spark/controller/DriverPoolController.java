package org.gokasama.datart.spark.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.gokasama.datart.spark.service.SparkLauncherService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author ka wujia@chinamobile.com
 */
@Api(tags = "调度接口", value = "DriverPool API")
@RestController
@RequestMapping("/api/v1/dp")
@Validated
@Slf4j
public class DriverPoolController {

    final SparkLauncherService sparkLauncherService;

    public DriverPoolController(SparkLauncherService sparkLauncherService) {
        this.sparkLauncherService = sparkLauncherService;
    }

    /**
     *
     * @param app string
     * @return string
     */
    @ApiOperation(value = "提交")
    @PostMapping("/")
    public String submit(@RequestParam String app) {

        return sparkLauncherService.submit(app);
    }
}
