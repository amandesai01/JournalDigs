package com.journaldigs.api.controllers;

import java.util.Map;

import com.journaldigs.api.forms.NewNoteForm;
import com.journaldigs.api.services.JwtService;
import com.journaldigs.api.services.NotesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoteController {
    @Autowired
    private NotesService notesService;

    @Autowired
    private JwtService jwtService;

    @RequestMapping(method = RequestMethod.GET,  value = "/journal/{journalid}/notes/")
    public Map<String, Object> getAllNotes(@RequestHeader(value="token") String token, @PathVariable String journalid){
        return notesService.getAllNotes(jwtService.decode(token), journalid);
    }

    @RequestMapping(method = RequestMethod.GET,  value = "/journal/{journalid}/notes/{noteid}")
    public Map<String, Object> getNote(@RequestHeader(value="token") String token, @PathVariable String noteid, @PathVariable String journalid){
        return notesService.getNote(noteid, journalid, jwtService.decode(token));
    }

    @RequestMapping(method = RequestMethod.DELETE,  value = "/journal/{journalid}/notes/{noteid}")
    public Map<String, String> deleteNote(@RequestHeader(value="token") String token, @PathVariable String noteid, @PathVariable String journalid){
        return notesService.deleteNote(jwtService.decode(token), noteid, journalid);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/journal/{journalid}/notes/new")
    public Map<String, String> newNote(@RequestHeader(value="token") String token, @PathVariable String journalid, @RequestBody NewNoteForm newNoteForm){
        return notesService.createNewNote(newNoteForm.title, newNoteForm.contents, newNoteForm.journalid, jwtService.decode(token));
    }
}
