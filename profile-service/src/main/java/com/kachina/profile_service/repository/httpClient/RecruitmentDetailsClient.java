package com.kachina.profile_service.repository.httpClient;


import com.kachina.profile_service.config.AuthenticationRequestInterceptor;
import com.kachina.profile_service.dto.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "recruitment-details-service",
        path = "/rds",
        configuration = {AuthenticationRequestInterceptor.class}
)
public interface RecruitmentDetailsClient {

    @DeleteMapping("/delete-by-profile/{id}")
    ApiResponse<Boolean> deleteByProfileId(@PathVariable("id") String profileId);

}
