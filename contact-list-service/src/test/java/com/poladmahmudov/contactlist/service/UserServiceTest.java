package com.poladmahmudov.contactlist.service;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import com.poladmahmudov.contactlist.persistence.domain.UserPo;
import com.poladmahmudov.contactlist.persistence.repository.UserRepository;
import com.poladmahmudov.contactlist.persistence.specs.UserSpecification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    @Test
    void getAll() {
        // given
        var search = "test-*";
        var user = createUser();
        var pageable = PageRequest.of(0, 1);
        when(repository.findAll(UserSpecification.of(search), pageable))
                .thenReturn(new PageImpl<>(singletonList(user), pageable, 1));

        // when
        var pagedUsers = service.getAll(search, pageable);

        // then
        assertTrue(pagedUsers.get().findFirst().isPresent());
        assertEquals(user.getName(), pagedUsers.get().findFirst().get().getName());
        assertEquals(user.getAvatar(), pagedUsers.get().findFirst().get().getAvatar());
    }

    private static UserPo createUser() {
        return new UserPo()
                .setId(1L)
                .setName("test-name")
                .setAvatar("https://test.com");
    }
}
