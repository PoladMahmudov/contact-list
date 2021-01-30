package com.poladmahmudov.contactlist.persistence.repository;

import com.poladmahmudov.contactlist.persistence.domain.UserPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserPo, Long>, JpaSpecificationExecutor<UserPo> {
}
