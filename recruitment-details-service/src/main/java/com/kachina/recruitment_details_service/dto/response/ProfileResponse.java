package com.kachina.recruitment_details_service.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileResponse {
    private String id;
    private String name;
    private String tags;
}
