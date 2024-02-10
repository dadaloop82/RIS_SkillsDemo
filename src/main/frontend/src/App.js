import React, { useState } from 'react';
import NoteForm from './components/NoteForm'; // Importing the component for note form
import NoteList from './components/NoteList'; // Importing the component for note list
import './App.css';

function App() {
  // State to manage the list of notes
  const [notes, setNotes] = useState([]);

  // Function to add a new note to the list
  const addNote = (title, text) => {
    const newNote = { title, text }; // Creating a new note object
    setNotes([...notes, newNote]); // Adding the new note to the notes state
  };

  // Function to update a note in the list
  const updateNote = (index, title, text) => {
    const updatedNotes = [...notes]; // Copying the notes array
    updatedNotes[index] = { title, text }; // Updating the note at the specified index with new data
    setNotes(updatedNotes); // Updating the notes state with the updated array
  };

  // Function to delete a note from the list
  const deleteNote = (index) => {
    const updatedNotes = notes.filter((_, i) => i !== index); // Filtering out the note with the specified index
    setNotes(updatedNotes); // Updating the notes state with the filtered array
  };

  return (
    <div className="app">
      <h1>Note Management Web Application</h1>
      {/* Component for the note form, passing the addNote function as a prop */}
      <NoteForm onAddNote={addNote} />
      {/* Component for the note list, passing the notes state and onUpdateNote and onDeleteNote functions as props */}
      <NoteList notes={notes} onUpdateNote={updateNote} onDeleteNote={deleteNote} />
    </div>
  );
}

export default App;
