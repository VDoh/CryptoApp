package com.project.currency.controlers;

import com.project.currency.arbs.Data;
import com.project.currency.models.*;
import com.project.currency.services.HistoryMeasureService;
import com.project.currency.services.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;

@Controller
@RequestMapping("/notes")
public class NotesController
{
    @Autowired
    private NotesService service;

    @RequestMapping(method = RequestMethod.GET)
    public String showNotes(Model model)
    {
        List<NotesClass> notesList = service.getUsers();
        model.addAttribute("history", notesList);
        return "notes";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addNotes(@ModelAttribute(name = "notesClass") NotesClass notesClass, Model model)
    {
        SimpleDateFormat format = new SimpleDateFormat();
        Date date = new Date();
        String dataNow = format.format(date).toString();

        String note = notesClass.getNote();

        service.createNote(note, dataNow);


        List<NotesClass> notesList = service.getUsers();
        model.addAttribute("history", notesList);
        return "notes";
    }
}