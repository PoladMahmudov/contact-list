package com.poladmahmudov.contactlist.web.rest.api;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import org.hibernate.validator.constraints.URL;

@Value
@Getter
@Builder
@AllArgsConstructor
public class UserDto {

    @NotBlank
    @Size(max = 150)
    String name;

    @URL
    @NotBlank
    @Size(max = 2000)
    String avatarUrl;
}
