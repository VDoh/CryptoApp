package com.project.currency.services;

import com.project.currency.models.NotesClass;
import com.project.currency.models.User;
import com.project.currency.repositories.NotesRepository;
import com.project.currency.repositories.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotesService
{
    @Autowired
    private NotesRepository repo;

    public List<NotesClass> getUsers(){
        try{
            return (List<NotesClass>) repo.findAll();
        }
        catch (Exception e)
        {

        }

        return (List<NotesClass>) repo.findAll();
    }

    public void createNote(String notes, String date){
        NotesClass newNote = new NotesClass();

        newNote.setNote(notes);
        newNote.setDate(date);
        newNote.setTitle("ff");

        repo.save(newNote);
    }
}
