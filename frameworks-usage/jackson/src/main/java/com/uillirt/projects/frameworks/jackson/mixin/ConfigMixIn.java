package com.uillirt.projects.frameworks.jackson.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.uillirt.projects.frameworks.jackson.model.Config;

/**
 * Created by kshekhovtsova on 22.06.2015.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class ConfigMixIn {
    @JsonIgnore
    private long id;
    @JsonProperty
    private String name;
    @JsonProperty
    private Config config;
//    @JsonIgnore
//    public abstract long getId();
//
//    @JsonProperty
//    public abstract String getName();
//
//    @JsonProperty
//    public abstract Config getConfig();

}
