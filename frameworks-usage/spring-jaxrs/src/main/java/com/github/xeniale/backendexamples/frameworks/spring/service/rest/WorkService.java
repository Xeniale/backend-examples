package com.github.xeniale.backendexamples.frameworks.spring.service.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by kshekhovtsova on 27.05.2015.
 */
@Path("work")
public interface WorkService {
    @GET
    @Path("colleagues/{colleagueId}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getColleague(@PathParam("colleagueId") Long colleagueId);
}
