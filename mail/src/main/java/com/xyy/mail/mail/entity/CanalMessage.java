package com.xyy.mail.mail.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: zyf
 * @create: 2022-01-05 15:28
 **/
@NoArgsConstructor
@Data
public class CanalMessage<T> {
    @JsonProperty("type")
    private String type;

    @JsonProperty("table")
    private String table;

    @JsonProperty("data")
    private List<T> data;

    @JsonProperty("database")
    private String database;

    @JsonProperty("es")
    private Long es;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("isDdl")
    private Boolean isDdl;

    @JsonProperty("old")
    private List<T> old;

    @JsonProperty("pkNames")
    private List<String> pkNames;

    @JsonProperty("sql")
    private String sql;

    @JsonProperty("ts")
    private Long ts;
}
