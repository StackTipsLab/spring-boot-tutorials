package com.stacktips.app.repositories;

import com.stacktips.app.entities.MyUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<MyUser, Long> {
    Optional<MyUser> findByEmail(String username);
}