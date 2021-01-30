package com.poladmahmudov.contactlist.web.rest.controller;

import com.poladmahmudov.contactlist.service.IUserService;
import com.poladmahmudov.contactlist.web.rest.api.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @GetMapping
    public Page<UserDto> getUsers(
            @RequestParam(name = "nameCriteria", defaultValue = "") final String nameCriteria,
            @PageableDefault(sort = "name") final Pageable pageable) {
        log.debug("Requested list of users with name filter {} and paging {}", nameCriteria, pageable);
        return userService.getAll(nameCriteria, pageable)
                .map(user -> UserDto.builder()
                        .name(user.getName())
                        .avatarUrl(user.getAvatar().toString())
                        .build());
    }
}
