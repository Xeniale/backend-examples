package com.uillirt.projects.frameworks.spring.service.rest.impl;

import com.uillirt.projects.frameworks.spring.model.Colleague;
import com.uillirt.projects.frameworks.spring.model.Work;
import com.uillirt.projects.frameworks.spring.service.rest.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.ws.rs.core.Response;

/**
 * Created by kshekhovtsova on 27.05.2015.
 */
public class WorkServiceImpl implements WorkService {

    @Autowired
    @Qualifier("cfg123")
    private Work work;

    @Override
    public Response getColleague(Long colleagueId) {
//        Work work = new Work();
//        work.setId(12L);
//        work.setType("IT");
//        work.setDescription("Information technologies");
        Colleague colleague = new Colleague();
        colleague.setId(colleagueId);
        //colleague.setFirstName("Ivan");
        colleague.setLastName("Ivanov");
        colleague.setPost("Programmer");
        colleague.setWork(work);
        return Response.ok(colleague).build();
    }

    public void setWork(Work work) {
        this.work = work;
    }
}
