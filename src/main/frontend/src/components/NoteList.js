import React, { useState } from "react";
import { Button, Form } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTrashAlt, faEdit } from "@fortawesome/free-solid-svg-icons";

function NoteList({ notes, onUpdateNote, onDeleteNote }) {
  // State to track notes being edited
  const [editingNote, setEditingNote] = useState(null);
  const [editedTitle, setEditedTitle] = useState("");
  const [editedText, setEditedText] = useState("");

  // Function to start editing a note
  const startEditing = (index) => {
    setEditingNote(index);
    const { title, text } = notes[index];
    setEditedTitle(title);
    setEditedText(text);
  };

  // Function to save changes made to a note
  const saveEditing = () => {
    onUpdateNote(editingNote, editedTitle, editedText); // Update the note with new data
    setEditingNote(null); // Reset editing state
    setEditedTitle(""); // Clear edited title
    setEditedText(""); // Clear edited text
  };

  // Function to cancel editing a note
  const cancelEditing = () => {
    setEditingNote(null); // Reset editing state
    setEditedTitle(""); // Clear edited title
    setEditedText(""); // Clear edited text
  };

  // Function to delete a note
  const handleDeleteNote = (id) => {
    onDeleteNote(id); // Delete the note using its ID
    if (editingNote === id) {
      cancelEditing(); // If the note being edited is deleted, cancel editing
    }
  };

  // Function to format the date and time
  const formatDateTime = () => {
    const currentDate = new Date();
    return `${currentDate.toLocaleDateString()} ${currentDate.toLocaleTimeString()}`;
  };

  return (
    <div>
      <h2>Notes</h2>
      <div className="note-container">
        {notes.length === 0 ? (
          <p>No notes available</p>
        ) : (
          notes.map((note, index) => (
            <div key={index} className="note">
              {editingNote === index ? (
                // Render input fields for editing if currently editing this note
                <>
                  <Form.Group controlId={`editFormTitle-${index}`}>
                    <Form.Control
                      type="text"
                      placeholder="Title"
                      value={editedTitle}
                      onChange={(e) => setEditedTitle(e.target.value)}
                    />
                  </Form.Group>
                  <Form.Group controlId={`editFormText-${index}`}>
                    <Form.Control
                      as="textarea"
                      rows={3}
                      placeholder="Text"
                      value={editedText}
                      onChange={(e) => setEditedText(e.target.value)}
                    />
                  </Form.Group>
                  <Button variant="primary" onClick={saveEditing}>
                    Save
                  </Button>
                  <Button variant="secondary" onClick={cancelEditing}>
                    Cancel
                  </Button>
                </>
              ) : (
                // Render note title, text, and timestamp if not currently editing this note
                <>
                  <h3>{note.title}</h3>
                  <p>{note.text}</p>
                  <p className="note-timestamp">(ID:{note.id}) Created on: {formatDateTime()}</p> {/* Display timestamp */}
                  <div className="toolBox">
                    <Button
                      variant="primary"
                      onClick={() => startEditing(index)}
                      className="me-2"
                    >
                      <FontAwesomeIcon icon={faEdit} />
                    </Button>
                    <Button
                      variant="danger"
                      onClick={() => handleDeleteNote(note.id)} // Pass the note ID to handleDeleteNote
                    >
                      <FontAwesomeIcon icon={faTrashAlt} />
                    </Button>
                  </div>
                </>
              )}
            </div>
          ))
        )}
      </div>
    </div>
  );
}

export default NoteList;
