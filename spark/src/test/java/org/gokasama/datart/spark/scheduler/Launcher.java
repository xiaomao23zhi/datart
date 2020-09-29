package org.gokasama.datart.spark.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.apache.spark.launcher.SparkAppHandle;
import org.apache.spark.launcher.SparkLauncher;

import java.io.IOException;

@Slf4j
public class Launcher {

    public static void main(String[] args) throws IOException {

        SparkAppHandle sparkAppHandle = new SparkLauncher()
        .setAppName("TEST")
                .setSparkHome("/opt/spark")
        .setMaster("spark://192.168.184.128:7077")
                .setDeployMode("client")
                .setAppResource("/root/spark/correlation_example.py")
                .startApplication();

        log.info("applicationId: {}", sparkAppHandle.getAppId());
    }
}
