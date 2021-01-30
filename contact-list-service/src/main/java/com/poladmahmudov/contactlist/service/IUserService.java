package com.poladmahmudov.contactlist.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {

    /**
     * Finds all user profiles that match the name predicate (if set), and paged.
     *
     * @param nameCriteria matcher for name
     * @param pageable    paging params
     * @return paged list of profiles
     */
    Page<User> getAll(String nameCriteria, Pageable pageable);
}
