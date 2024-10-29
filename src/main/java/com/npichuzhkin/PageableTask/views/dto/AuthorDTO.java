package com.npichuzhkin.PageableTask.views.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.npichuzhkin.PageableTask.views.dto.DetailsBookInfo;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO {

    @JsonView(DetailsBookInfo.class)
    private String name;
}
