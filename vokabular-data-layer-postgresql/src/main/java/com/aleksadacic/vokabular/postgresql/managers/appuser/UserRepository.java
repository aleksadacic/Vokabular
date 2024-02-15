package com.aleksadacic.vokabular.postgresql.managers.appuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    //    TODO generator sva polja da se ubace
    Optional<User> findByUsername(String username);
}
