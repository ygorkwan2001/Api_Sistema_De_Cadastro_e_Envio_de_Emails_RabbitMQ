package com.example.rest.api.crud.user.repositories;

import com.example.rest.api.crud.user.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {

}
