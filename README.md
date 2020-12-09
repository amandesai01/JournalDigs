# JournalDigs
A Centralised Place to Create And Host Your Journals. (APIs Only)

### TechStack
* SpringBoot
* MySQL

### Actions:
* User Signs Up.
* User Logs In.
* User Creates a Journal.
* User Gives Journal a Title.
* User Adds new Notes into Journal.
* User Gives Title and Contents to Journal.

### Entities:
* User
  * UserId (Auto Generated)
  * Full Name
  * Email Id
  * Phone Number
  * Array of `Journal`s

* Journal
  * JournalId (Auto Generated)
  * Title
  * Created Date
  * Array of `Note`s
  
* Note
  * NoteId (Auto Generated)
  * Title
  * Content

This Project was made within 48hrs, from scratch (including Learning SpringBoot, Setting up CI/CD, Writing Unit tests, Developing above structure).
