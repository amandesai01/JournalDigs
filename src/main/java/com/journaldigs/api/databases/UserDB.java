package com.journaldigs.api.databases;

import org.springframework.data.repository.CrudRepository;

import com.journaldigs.api.models.User;

public interface UserDB extends CrudRepository<User, String> {}