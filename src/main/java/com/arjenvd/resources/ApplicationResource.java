package com.arjenvd.resources;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class ApplicationResource extends ResourceConfig {
    public ApplicationResource() {
        packages("main.java.com.arjenvd");
    }
}