package org.gokasama.datart.gateway.model;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ka wujia@chinamobile.com
 */
@Data
public class FilterDefinition {

    /**
     * Filter Name
     */
    private String name;

    /**
     * Filter Args
     */
    private Map<String, String> args = new LinkedHashMap<>();

}
