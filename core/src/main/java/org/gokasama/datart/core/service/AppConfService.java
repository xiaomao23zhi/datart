package org.gokasama.datart.core.service;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.math.NumberUtils;
import org.gokasama.datart.core.annotation.RedisLock;
import org.gokasama.datart.core.model.AppConf;
import org.gokasama.datart.core.repository.AppConfRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ka wujia@chinamobile.com
 */
@Component
public class AppConfService {

    final AppConfRepository appConfRepository;

    public AppConfService(AppConfRepository appConfRepository) {
        this.appConfRepository = appConfRepository;
    }

    /**
     * @param key key
     * @return String
     */
    private String value(String key) {
        if (appConfRepository.existsByKey(key)) {
            return appConfRepository.findByKey(key).getValue();
        } else {
            return null;
        }
    }

    /**
     * @param key key
     * @return String
     */
    public String getString(String key) {
        return this.value(key);
    }

    /**
     * @param key key
     * @return int
     */
    public int getInt(String key) {
        return NumberUtils.toInt(this.value(key));
    }

    /**
     * @param key key
     * @return double
     */
    public double getDouble(String key) {
        return NumberUtils.toDouble(this.value(key));
    }

    /**
     * @param key key
     * @return JSONObject
     */
    public JSONObject getJSONObject(String key) {
        return JSONObject.parseObject(this.value(key));
    }

    /**
     * @param key key
     * @return JSONArray
     */
    public JSONArray getJSONArray(String key) {
        return JSONArray.parseArray(this.value(key));
    }

    /**
     * @return List
     */
    @RedisLock(name = "AppConf", key = "appConf")
    public List<AppConf> findAll() {
        return appConfRepository.findAll();
    }

    /**
     * @param key key
     * @return boolean
     */
    public boolean exist(String key) {
        return appConfRepository.existsByKey(key);
    }

    /**
     * @param key key
     * @return AppConf
     */
    public AppConf find(String key) {
        return appConfRepository.findByKey(key);
    }
}
