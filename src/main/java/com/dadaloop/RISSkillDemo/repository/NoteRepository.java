package com.dadaloop.RISSkillDemo.repository;

import com.dadaloop.RISSkillDemo.model.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The NoteRepository interface extends the JpaRepository interface from Spring Data JPA.
 * This interface manages the database operations for the NoteEntity.
 * It provides CRUD (Create, Read, Update, Delete) operations and more on the NoteEntity.
 *
 * By extending JpaRepository, NoteRepository inherits several methods for working with NoteEntity persistence,
 * including methods for saving, deleting, and finding NoteEntity instances.
 *
 * @Repository annotation indicates that the class provides the mechanism for storage, retrieval,
 * update, delete and search operation on objects.
 */
@Repository
public interface NoteRepository extends JpaRepository<NoteEntity, Long> {
    // Here you can add custom methods for interacting with the database, if needed.
    // For example, if you need to find notes by their title, you could add:
    // List<NoteEntity> findByTitle(String title);
}
