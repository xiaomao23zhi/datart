package org.gokasama.datart.gateway.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ka wujia@chinamobile.com
 */
@Data
public class RouteDefinition {

    /**
     * Route Id
     */
    private String id;

    /**
     * Predicates
     */
    private List<PredicateDefinition> predicates = new ArrayList<>();

    /**
     * Filters
     */
    private List<FilterDefinition> filters = new ArrayList<>();

    /**
     * Target url
     */
    private String uri;

    /**
     * Route Order
     */
    private int order = 0;

}
