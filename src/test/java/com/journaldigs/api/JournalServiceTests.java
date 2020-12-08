package com.journaldigs.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import com.journaldigs.api.databases.JournalDB;
import com.journaldigs.api.databases.UserDB;
import com.journaldigs.api.models.Journal;
import com.journaldigs.api.models.User;
import com.journaldigs.api.services.JournalService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class JournalServiceTests {
    @Autowired
    private JournalService journalService;

    @MockBean
    private JournalDB journalDB;

    @MockBean
    private UserDB userDB;

    @Test
    public void createJournalAddTest(){
        User u = new User("name", "9822222222", "email@domain.com", "password");
        Map<String, String> res = journalService.createJournal(u, "New Journal");
        assertEquals(res.get("status"), "OK");
        assertNotNull(res.get("journalid"));
        assertNotNull(res.get("createddate"));
    }

    @Test
    public void deleteJournalTest() {
        User u = new User("name", "9822222222", "email@domain.com", "password");
        Journal target = new Journal("title3", new Date(), u);
        when(journalDB.findByUserId(u.getId())).thenReturn(Arrays.asList(
            new Journal("title1", new Date(), u),
            new Journal("title2", new Date(), u),
            target,
            new Journal("title4", new Date(), u)
        ));
        Map<String, String> res = journalService.deleteJournal(u, target.getJournalid());
        assertEquals(res.get("status"), "OK");
    }

    @Test
    public void getJournalTest() {
        User u = new User("name", "9822222222", "email@domain.com", "password");
        Journal target = new Journal("title3", new Date(), u);
        when(journalDB.findByUserId(u.getId())).thenReturn(Arrays.asList(
            new Journal("title1", new Date(), u),
            new Journal("title2", new Date(), u),
            target,
            new Journal("title4", new Date(), u)
        ));
        Map<String, Object> res = journalService.getJournal(u, target.getJournalid());
        assertNotNull(res.get("data"));
        assertEquals(res.get("status"), "OK");
        assertEquals(res.get("data"), target);
    }

    @Test
    public void getJournalWrongUserTest() {
        User u = new User("name", "9822222222", "email@domain.com", "password");
        User u2 = new User("name2", "9822222222", "email2@domain.com", "password");
        Journal target = new Journal("title3", new Date(), u);
        when(journalDB.findByUserId(u.getId())).thenReturn(Arrays.asList(
            new Journal("title1", new Date(), u),
            new Journal("title2", new Date(), u),
            target,
            new Journal("title4", new Date(), u)
        ));
        Map<String, Object> res = journalService.getJournal(u2, target.getJournalid());
        assertNull(res.get("data"));
        assertEquals(res.get("status"), "FAIL");
    }
}
