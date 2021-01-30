package com.poladmahmudov.contactlist.persistence.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.poladmahmudov.contactlist.persistence.domain.UserPo;
import com.poladmahmudov.contactlist.persistence.specs.UserSpecification;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    void save() {
        // given
        var po = createUser();

        // when
        repository.save(po);

        // then
        var persisted = repository.findById(po.getId());

        assertTrue(persisted.isPresent());
        assertEquals(po.getName(), persisted.get().getName());
        assertEquals(po.getAvatar(), persisted.get().getAvatar());
    }

    @Test
    void findAllWithSpecs() {
        // given
        var po = repository.save(createUser());
        var specs = UserSpecification.of("test-*");

        // when
        var found = repository.findAll(specs);

        // then
        assertEquals(1, found.size());
        assertEquals(po, found.get(0));
    }

    @Test
    void findAllPaged() {
        // given
        var po = repository.save(createUser());
        var specs = PageRequest.of(0, 1);

        // when
        var found = repository.findAll(specs);

        // then
        assertTrue(found.get().findFirst().isPresent());
        assertEquals(po, found.get().findFirst().get());
    }

    private static UserPo createUser() {
        return new UserPo()
                .setName("test-name")
                .setAvatar("https://test.com");
    }
}
