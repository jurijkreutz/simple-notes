package com.codecool.simplenotes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Note {

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
        this.id = instances++;
    }

    private static int instances = 1;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    private String title;

    private String content;

}
