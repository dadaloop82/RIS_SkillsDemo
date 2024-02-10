import React, { useState, useEffect } from 'react'; // Importing necessary dependencies
import axios from 'axios'; // Importing Axios for making HTTP requests
import NoteForm from './components/NoteForm'; // Importing the NoteForm component
import NoteList from './components/NoteList'; // Importing the NoteList component
import './App.css'; // Importing the CSS file for styling

function App() {
  // State to manage the list of notes
  const [notes, setNotes] = useState([]); // Initializing state for notes as an empty array

  // Function to load initial notes from the backend when the application loads
  useEffect(() => {
    const fetchNotes = async () => {
      try {
        // Sending a GET request to the backend API to fetch notes
        const response = await axios.get('/api/notes');
        // Updating the notes state with the data received from the backend
        setNotes(response.data);
      } catch (error) {
        console.error('Error fetching notes:', error);
      }
    };
    fetchNotes(); // Calling the fetchNotes function when the component mounts
  }, []); // Running this effect only once when the component mounts

  // Function to add a new note
  const addNote = async (title, text) => {
    try {
      // Sending a POST request to the backend API to add a new note
      const response = await axios.post('/api/notes', { title, text });
      // Extracting the new note data from the response
      const newNote = response.data;
      // Updating the notes state by adding the new note
      setNotes([...notes, newNote]);
    } catch (error) {
      console.error('Error adding note:', error);
    }
  };

  // Function to update an existing note
  const updateNote = async (index, title, text) => {
    try {
      // Sending a PUT request to the backend API to update the specified note
      await axios.put(`/api/notes/${index}`, { title, text });
      // Creating a copy of the current notes array
      const updatedNotes = [...notes];
      // Updating the note at the specified index with the new data
      updatedNotes[index] = { title, text };
      // Updating the notes state with the updated array
      setNotes(updatedNotes);
    } catch (error) {
      console.error('Error updating note:', error);
    }
  };

  // Function to delete a note
  const deleteNote = async (index) => {
    try {
      // Sending a DELETE request to the backend API to delete the specified note
      await axios.delete(`/api/notes/${index}`);
      // Filtering out the deleted note from the current notes array
      const updatedNotes = notes.filter((_, i) => i !== index);
      // Updating the notes state with the filtered array
      setNotes(updatedNotes);
    } catch (error) {
      console.error('Error deleting note:', error);
    }
  };

  // Rendering the Note Management Web Application
  return (
    <div className="app">
      <h1>Note Management Web Application</h1>
      {/* NoteForm component for adding new notes */}
      <NoteForm onAddNote={addNote} />
      {/* NoteList component for displaying and managing notes */}
      <NoteList notes={notes} onUpdateNote={updateNote} onDeleteNote={deleteNote} />
    </div>
  );
}

export default App; // Exporting the App component as the default export
