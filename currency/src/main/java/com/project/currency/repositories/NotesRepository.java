package com.project.currency.repositories;

import com.project.currency.models.NotesClass;
import com.project.currency.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesRepository extends JpaRepository<NotesClass, Long>
{

}
