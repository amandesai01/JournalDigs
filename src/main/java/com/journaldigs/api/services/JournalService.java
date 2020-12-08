package com.journaldigs.api.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.journaldigs.api.databases.JournalDB;
import com.journaldigs.api.models.Journal;
import com.journaldigs.api.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JournalService {
    @Autowired
    private JournalDB journalDB;

    public Map<String, String> deleteJournal(String userid, String id){
        Map<String, String> response = new HashMap<>();
        try {
            List<Journal> data = journalDB.findByUserId(userid);
            for (Journal journal : data) {
                if(journal.getJournalid().equals(id)){
                    journalDB.deleteById(id);
                    break;
                }
            }
            response.put("status", "OK");
            return response;
        } catch (Exception e) {
            response.put("status", "FAIL");
            response.put("error", e.toString());
            return response;
        }
    }

    public Map<String, Object> getJournal(User u, String id){
        Map<String, Object> response = new HashMap<>();
        try {
            List<Journal> j = journalDB.findByUserId(u.getId());
            for (Journal journal : j) {
                if(journal.getJournalid().equals(id)){
                    response.put("status", "OK");
                    response.put("data", journal);
                    return response;
                }
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

    public Map<String, Object> getAllJournals(User u){
        Map<String, Object> response = new HashMap<>();
        try {
            List<Journal> j = journalDB.findByUserId(u.getId());
            response.put("status", "ok");
            response.put("data", j);
            return response;
        } catch (Exception e) {
            response.put("status", "FAIL");
            response.put("error", e.toString());
            return response;
        }
    }

    public Map<String, String> createJournal(User u, String title){
        Map<String, String> response = new HashMap<>();
        Journal j = new Journal(title, new Date(), u);
        try {
            journalDB.save(j);
            response.put("status", "OK");
            response.put("journalid", j.getJournalid());
            response.put("createddate", j.getCreatedDate().toString());
            return response;
        } catch (Exception e) {
            response.put("status", "FAIL");
            response.put("error", e.toString());
            return response;
        }
    }
}
