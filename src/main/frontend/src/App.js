import React, { useState, useEffect } from "react";
import axios from "axios";
import NoteForm from "./components/NoteForm";
import NoteList from "./components/NoteList";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faDatabase } from "@fortawesome/free-solid-svg-icons";

import "./App.css";

function App() {
  // State for storing notes and database information
  const [notes, setNotes] = useState([]); // State for notes
  const [dbInfo, setDbInfo] = useState({ // State for database information
    connectionStatus: "",
    recordCount: 0,
  });

  // Function to fetch database information
  const fetchDbInfo = async () => {
    try {
      const response = await axios.get("/api/db-info");
      setDbInfo(response.data);
    } catch (error) {
      console.error("Error fetching DB info:", error);
    }
  };

  // Function to fetch notes and database information on component mount
  useEffect(() => {
    const fetchNotes = async () => {
      try {
        const response = await axios.get("/api/notes");
        setNotes(response.data);
      } catch (error) {
        console.error("Error fetching notes:", error);
      }
    };

    fetchNotes(); // Fetch notes
    fetchDbInfo(); // Fetch database information
  }, []); // Empty dependency array ensures this effect runs only once on mount

  // Function to add a new note
  const addNote = async (title, text) => {
    try {
      const response = await axios.post("/api/notes", { title, text });
      const newNote = response.data;
      setNotes([...notes, newNote]); // Add new note to the state
      fetchDbInfo(); // Fetch updated database information
    } catch (error) {
      console.error("Error adding note:", error);
    }
  };

  // Function to update an existing note
  const updateNote = async (index, title, text) => {
    try {
      await axios.put(`/api/notes/${index}`, { title, text });
      const updatedNotes = [...notes];
      updatedNotes[index] = { title, text };
      setNotes(updatedNotes); // Update note in the state
    } catch (error) {
      console.error("Error updating note:", error);
    }
  };

  // Function to delete a note
  const deleteNote = async (id) => {
    try {
      await axios.delete(`/api/notes/${id}`);
      const updatedNotes = notes.filter((note) => note.id !== id);
      setNotes(updatedNotes); // Update notes in the state after deletion
      fetchDbInfo(); // Fetch updated database information
    } catch (error) {
      console.error("Error deleting note:", error);
    }
  };

  return (
    <div className="app">
      <h1>Note Management Web Application</h1>
      <h2>
        Demo for Evaluating Daniel Stimpfl's Skills - Created for RIS Bolzano
      </h2>
      <p>Utilizes an embedded Java database (H2) for persistence</p>
      <div className="db-info">
        <FontAwesomeIcon
          icon={faDatabase}
          className={
            dbInfo.connectionStatus === "Connected"
              ? "connected"
              : "disconnected"
          }
        />{" "}
        <b>Java H2db</b>
        <p>
          {dbInfo.connectionStatus}
          <br />
          Record Count: {dbInfo.recordCount}
        </p>
      </div>
      <NoteForm onAddNote={addNote} /> {/* Component for adding a new note */}
      <NoteList
        notes={notes}
        onUpdateNote={updateNote} // Function for updating a note
        onDeleteNote={deleteNote} // Function for deleting a note
      />
    </div>
  );
}

export default App;
