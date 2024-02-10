import React, { useState } from 'react';

function NoteList({ notes, onUpdateNote, onDeleteNote }) {
  // State to track notes being edited
  const [editingNote, setEditingNote] = useState(null);
  const [editedTitle, setEditedTitle] = useState('');
  const [editedText, setEditedText] = useState('');

  // Function to start editing a note
  const startEditing = (index) => {
    setEditingNote(index);
    const { title, text } = notes[index];
    setEditedTitle(title);
    setEditedText(text);
  };

  // Function to save changes made to a note
  const saveEditing = () => {
    onUpdateNote(editingNote, editedTitle, editedText); // Call onUpdateNote function with updated note data
    setEditingNote(null); // Reset editing state
    setEditedTitle(''); // Clear edited title
    setEditedText(''); // Clear edited text
  };

  // Function to cancel editing a note
  const cancelEditing = () => {
    setEditingNote(null); // Reset editing state
    setEditedTitle(''); // Clear edited title
    setEditedText(''); // Clear edited text
  };

  // Function to delete a note
  const handleDeleteNote = (index) => {
    onDeleteNote(index); // Call onDeleteNote function to delete the note
    if (editingNote === index) {
      cancelEditing(); // If the note being edited is deleted, cancel editing
    }
  };

  return (
    <div>
      <h2>Notes</h2>
      {notes.length === 0 ? (
        <p>No notes available</p>
      ) : (
        <ul>
          {notes.map((note, index) => (
            <li key={index}>
              {editingNote === index ? (
                // Render input fields for editing if currently editing this note
                <>
                  <input
                    type="text"
                    value={editedTitle}
                    onChange={(e) => setEditedTitle(e.target.value)}
                  />
                  <textarea
                    value={editedText}
                    onChange={(e) => setEditedText(e.target.value)}
                  />
                  <button onClick={saveEditing}>Save</button>
                  <button onClick={cancelEditing}>Cancel</button>
                </>
              ) : (
                // Render note title and text if not currently editing this note
                <>
                  <strong>{note.title}</strong>: {note.text}
                  <button onClick={() => startEditing(index)}>Edit</button>
                  <button onClick={() => handleDeleteNote(index)}>Delete</button>
                </>
              )}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default NoteList;
