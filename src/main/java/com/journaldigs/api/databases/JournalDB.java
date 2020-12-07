package com.journaldigs.api.databases;

import org.springframework.data.repository.CrudRepository;

import com.journaldigs.api.models.Journal;

public interface JournalDB extends CrudRepository<Journal, String> {}