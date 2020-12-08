package com.journaldigs.api.databases;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

import com.journaldigs.api.models.Journal;
import com.journaldigs.api.models.Note;

public interface NoteDB extends CrudRepository<Note, String> {
    public List<Note> findByJournal(Journal journal);
}