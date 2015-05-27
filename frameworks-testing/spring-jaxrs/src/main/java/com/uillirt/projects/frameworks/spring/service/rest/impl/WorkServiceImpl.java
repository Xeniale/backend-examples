package com.uillirt.projects.frameworks.spring.service.rest.impl;

import com.uillirt.projects.frameworks.spring.model.Colleague;
import com.uillirt.projects.frameworks.spring.model.Work;
import com.uillirt.projects.frameworks.spring.service.rest.WorkService;

import javax.ws.rs.core.Response;

/**
 * Created by kshekhovtsova on 27.05.2015.
 */
public class WorkServiceImpl implements WorkService {
    @Override
    public Response getColleague(Long colleagueId) {
        Work work = new Work();
        work.setId(12L);
        work.setType("IT");
        work.setDescription("Information technologies");
        Colleague colleague = new Colleague();
        colleague.setId(colleagueId);
        //colleague.setFirstName("Ivan");
        colleague.setLastName("Ivanov");
        colleague.setPost("Programmer");
        colleague.setWork(work);
        return Response.ok(colleague).build();
    }
}
