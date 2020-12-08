package com.journaldigs.api.controllers;

import java.util.Map;

import com.journaldigs.api.forms.NewJournal;
import com.journaldigs.api.services.JournalService;
import com.journaldigs.api.services.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JournalController {

    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private JournalService journalService;

    @RequestMapping(method = RequestMethod.GET,  value = "/journal/all")
    public Map<String, Object> getAllJournals(@RequestHeader(value="token") String token){
        return journalService.getAllJournals(jwtService.decode(token));
    }

    @RequestMapping(method = RequestMethod.GET,  value = "/journal/{journalid}")
    public Map<String, Object> getJournal(@RequestHeader(value="token") String token, @PathVariable String journalid){
        return journalService.getJournal(jwtService.decode(token), journalid);
    }

    @RequestMapping(method = RequestMethod.DELETE,  value = "/journal/{journalid}")
    public Map<String, String> deleteJournal(@RequestHeader(value="token") String token, @PathVariable String journalid){
        return journalService.deleteJournal(jwtService.decode(token), journalid);
    }

    @RequestMapping(method = RequestMethod.POST,  value = "/journal/new")
    public Map<String, String> createNewJournal(@RequestHeader(value="token") String token, @RequestBody NewJournal newJournal){
        return journalService.createJournal(jwtService.decode(token), newJournal.title);
    }
}
