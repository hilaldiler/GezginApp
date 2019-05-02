package com.example.androidgezginapp.model;

public class Notes {

    String noteID;
    String noteTitle;

    public Notes(String noteID, String noteTitle) {
        this.noteID = noteID;
        this.noteTitle = noteTitle;
    }

    public String getNoteID() {
        return noteID;
    }

    public void setNoteID(String noteID) {
        this.noteID = noteID;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }
}
