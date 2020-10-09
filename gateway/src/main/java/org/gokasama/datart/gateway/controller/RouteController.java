package org.gokasama.datart.gateway.controller;

import org.gokasama.datart.gateway.service.DynamicRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.web.bind.annotation.*;

/**
 * @author ka wujia@chinamobile.com
 */
@RestController
@RequestMapping("/route")
public class RouteController {

    @Autowired
    private DynamicRouteService dynamicRouteService;

    /**
     *
     * @param routeDefinition
     * @return
     */
    @PostMapping("/add")
    public String add(@RequestBody RouteDefinition routeDefinition) {
        try {
//            RouteDefinition definition = assembleRouteDefinition(gwdefinition);
            return this.dynamicRouteService.add(routeDefinition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "succss";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        return this.dynamicRouteService.delete(id);
    }

    @PostMapping("/update")
    public String update(@RequestBody RouteDefinition routeDefinition) {
//        RouteDefinition definition = assembleRouteDefinition(gwdefinition);
        return this.dynamicRouteService.update(routeDefinition);
    }

//    private RouteDefinition assembleRouteDefinition(GatewayRouteDefinition gwdefinition) {
//        RouteDefinition definition = new RouteDefinition();
//        List<PredicateDefinition> pdList=new ArrayList<>();
//        definition.setId(gwdefinition.getId());
//        List<GatewayPredicateDefinition> gatewayPredicateDefinitionList=gwdefinition.getPredicates();
//        for (GatewayPredicateDefinition gpDefinition: gatewayPredicateDefinitionList) {
//            PredicateDefinition predicate = new PredicateDefinition();
//            predicate.setArgs(gpDefinition.getArgs());
//            predicate.setName(gpDefinition.getName());
//            pdList.add(predicate);
//        }
//        definition.setPredicates(pdList);
//        URI uri = UriComponentsBuilder.fromHttpUrl(gwdefinition.getUri()).build().toUri();
//        definition.setUri(uri);
//        return definition;
//    }

}
