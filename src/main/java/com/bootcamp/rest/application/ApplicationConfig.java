package com.bootcamp.rest.application;

import com.bootcamp.rest.controllers.*;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;
import javax.ws.rs.ApplicationPath;

/**
 *
 * @author Iso-Doss
 */
@ApplicationPath("rest")
public class ApplicationConfig extends Application {

    /*@Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }*/
    @Override
    public Set<Class<?>> getClasses() {

        Set<Class<?>> resources = new HashSet<>();
        resources.add(CommuneRestController.class);
        resources.add(DepartementRestController.class);
        resources.add(RestWelcomeController.class);

        return resources;

    }

    /**
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
    /*private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.bootcamp.rest.controllers.CommuneRestController.class);
        resources.add(com.bootcamp.rest.controllers.DepartementRestController.class);
        resources.add(com.bootcamp.rest.controllers.RestWelcomeController.class);
    }*/
}
