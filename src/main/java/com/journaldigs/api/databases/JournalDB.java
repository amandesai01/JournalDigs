package com.journaldigs.api.databases;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

import com.journaldigs.api.models.Journal;

public interface JournalDB extends CrudRepository<Journal, String> {
    public List<Journal> findByOwnerId(String uid);
}