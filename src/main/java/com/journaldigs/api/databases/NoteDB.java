package com.journaldigs.api.databases;

import org.springframework.data.repository.CrudRepository;

import com.journaldigs.api.models.Note;

public interface NoteDB extends CrudRepository<Note, String> {}