package org.gokasama.datart.spark.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.spark.launcher.SparkAppHandle;
import org.apache.spark.launcher.SparkLauncher;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author ka wujia@chinamobile.com
 */
@Component
@Slf4j
public class SparkLauncherService {

    private final String SPARK_MASTER = "spark://192.168.184.128:7077";

    /**
     *
     * @param appResource python
     * @return applicationId
     */
    public String submit(String appResource) {
        SparkAppHandle sparkAppHandle;
        try {
            sparkAppHandle = new SparkLauncher()
                    .setAppName("TEST")
                    .setSparkHome("/opt/spark")
                    .setMaster(SPARK_MASTER)
                    .setDeployMode("client")
                    .setAppResource(appResource)
                    .startApplication(new SparkAppHandle.Listener(){

                        @Override
                        public void stateChanged(SparkAppHandle sparkAppHandle) {
                            log.debug("!!!!!!!! {} - {}", sparkAppHandle.getAppId(), sparkAppHandle.getState());
                        }

                        @Override
                        public void infoChanged(SparkAppHandle sparkAppHandle) {
                            log.debug("!!!!!!!! {} - {}", sparkAppHandle.getAppId(), sparkAppHandle.getState());
                        }
                    });
        } catch (IOException e) {
            log.error("Cannot submit: {}", e.getMessage());
            return null;
        }

        String applicationId = null;

        while (applicationId == null) {
            applicationId = sparkAppHandle.getAppId();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        log.info("ApplicationId: {}", applicationId);

//        sparkAppHandle.stop();

        return applicationId;
    }
}
