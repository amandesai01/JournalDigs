package com.journaldigs.api.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.journaldigs.api.databases.JournalDB;
import com.journaldigs.api.databases.NoteDB;
import com.journaldigs.api.models.Journal;
import com.journaldigs.api.models.Note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotesService {
    @Autowired
    private NoteDB noteDB;

    @Autowired
    private JournalDB journalDB;

    public Map<String, String> createNewNote(String title, String contents, String journalId, String userId){
        Map<String, String> response = new HashMap<>();
        try {
            Journal j = journalDB.findById(journalId).get();
            if(j != null && j.getOwner().getId().equals(userId)){
                Note note = new Note(title, contents, new Date(), j);
                noteDB.save(note);
                response.put("status", "OK");
                response.put("noteid", note.getNoteid());
                return response;
            }
            response.put("status", "FAIL");
            response.put("error", "No Such Journal Exists under this user");
            return response;
        } catch (Exception e) {
            response.put("status", "FAIL");
            response.put("error", e.toString());
            return response;
        }
    }

    public Map<String, Object> getNote(String noteid, String userid){
        Map<String, Object> response = new HashMap<>();
        try {
            Note n = noteDB.findById(noteid).get();
            if(n!=null && n.getJournal().getOwner().getId().equals(userid)){
                response.put("status", "OK");
                response.put("data", n);
                return response;
            }
            response.put("status", "FAIL");
            response.put("error", "No Such Note Exists under this user");
            return response;
        } catch (Exception e) {
            response.put("status", "FAIL");
            response.put("error", e.toString());
            return response;
        }
    }

    public Map<String, Object> getAllNotes(String userid, String journalid){
        Map<String, Object> response = new HashMap<>();
        try {
            Journal target = journalDB.findById(journalid).get();
            if(target.getOwner().getId().equals(userid)){
                List<Note> notes = noteDB.findByJournal(target);
                response.put("status", "OK");
                response.put("data", notes);
                return response;
            }
            response.put("status", "FAIL");
            response.put("error", "No Such Journal Exists under this user");
            return response;
            
        } catch (Exception e) {
            response.put("status", "FAIL");
            response.put("error", e.toString());
            return response;
        }
    }

    public Map<String, String> deleteNote(String userid, String noteid){
        Map<String, String> response = new HashMap<>();
        try {
            Note n = noteDB.findById(noteid).get();
            if(n!=null && n.getJournal().getOwner().getId().equals(userid)){
                noteDB.delete(n);
                response.put("status", "OK");
                return response;
            }
            response.put("status", "FAIL");
            response.put("error", "No Such Note Exists under this user");
            return response;
        } catch (Exception e) {
            response.put("status", "FAIL");
            response.put("error", e.toString());
            return response;
        }
    }
}