package com.project.currency.models;

import javax.persistence.*;

@Entity
@Table(name = "notes")
public class NotesClass
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_user;
    private String note_content;
    private String title;
    private String date;

    public long getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNote() {
        return note_content;
    }

    public void setNote(String note_content) {
        this.note_content = note_content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



}
