package com.poladmahmudov.contactlist.web.rest.controller;

import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toUnmodifiableMap;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Map;
import com.poladmahmudov.contactlist.ContactListApplication;
import com.poladmahmudov.contactlist.persistence.domain.UserPo;
import com.poladmahmudov.contactlist.persistence.repository.UserRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(
        classes = ContactListApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class UserControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    void init() {
        userRepository.deleteAll();
    }

    @Test
    void getUsers() throws Exception {
        // given
        var thomas = saveUser("Thomas Shelby");
        var arthur = saveUser("Arthur Shelby");
        var polly = saveUser("Polly Gray");

        // when
        var response = mvcGet("/api/users", Map.of(
                "nameCriteria", "* shelby",
                "size", "3",
                "page", "0",
                "name", "email,asc"
        ));

        // then
        response
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.content[0].name", is(arthur.getName())))
                .andExpect(jsonPath("$.content[0].avatarUrl", is(arthur.getAvatar())))
                .andExpect(jsonPath("$.content[1].name", is(thomas.getName())))
                .andExpect(jsonPath("$.content[1].avatarUrl", is(thomas.getAvatar())))
                .andExpect(jsonPath("$.totalElements", is(2)))
                .andExpect(jsonPath("$.totalPages", is(1)))
                .andExpect(jsonPath("$.size", is(3)))
                .andExpect(jsonPath("$.number", is(0)));

    }

    private UserPo saveUser(final String name) {
        return userRepository.save(new UserPo()
                .setName(name)
                .setAvatar("https://test.com"));
    }

    @SneakyThrows
    private ResultActions mvcGet(final String url, final Map<String, String> params) {
        var multiValuedParams = params.entrySet().stream()
                .collect(toUnmodifiableMap(Map.Entry::getKey, entry -> singletonList(entry.getValue())));
        return mvc.perform(get(url)
                .params(new LinkedMultiValueMap<>(multiValuedParams))
                .accept(APPLICATION_JSON));
    }
}
