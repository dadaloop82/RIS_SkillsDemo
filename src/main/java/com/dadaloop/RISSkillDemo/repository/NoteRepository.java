package com.dadaloop.RISSkillDemo.repository;

import com.dadaloop.RISSkillDemo.model.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NoteRepository extends JpaRepository<NoteEntity, Long> {
  
}
