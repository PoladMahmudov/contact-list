package com.poladmahmudov.contactlist.service;

import com.poladmahmudov.contactlist.persistence.domain.UserPo;
import com.poladmahmudov.contactlist.persistence.repository.UserRepository;
import com.poladmahmudov.contactlist.persistence.specs.UserSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository repository;

    @Override
    public Page<User> getAll(final String nameCriteria, final Pageable pageable) {
        final var users = repository.findAll(UserSpecification.of(nameCriteria), pageable);
        return users.map(UserService::map);
    }

    private static User map(final UserPo userPo) {
        return User.of(userPo.getName(), userPo.getAvatar());
    }
}
