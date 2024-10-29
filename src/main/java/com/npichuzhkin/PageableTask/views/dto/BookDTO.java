package com.npichuzhkin.PageableTask.views.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.npichuzhkin.PageableTask.views.dto.DetailsBookInfo;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    @JsonView(DetailsBookInfo.class)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID uuid;

    @Pattern(regexp = "^[A-Za-z]+( [A-Za-z]+)*$", message = "Name field can only contain letters.")
    @Size(min = 2, max = 200, message = "Name field must contain from 2 to 200 letters.")
    @JsonView(DetailsBookInfo.class)
    private String name;

    @JsonView(DetailsBookInfo.class)
    private AuthorDTO author;

    public BookDTO(String name, AuthorDTO author){
        this.name = name;
        this.author = author;
    }

}
