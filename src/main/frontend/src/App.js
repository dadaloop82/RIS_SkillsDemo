import React, { useState, useEffect } from "react";
import axios from "axios";
import NoteForm from "./components/NoteForm";
import NoteList from "./components/NoteList";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faDatabase } from "@fortawesome/free-solid-svg-icons";

import "./App.css";

function App() {
  const [notes, setNotes] = useState([]);
  const [dbInfo, setDbInfo] = useState({
    connectionStatus: "",
    recordCount: 0,
  });

  const fetchDbInfo = async () => {
    try {
      const response = await axios.get("/api/db-info");
      setDbInfo(response.data);
    } catch (error) {
      console.error("Error fetching DB info:", error);
    }
  };

  useEffect(() => {
    const fetchNotes = async () => {
      try {
        const response = await axios.get("/api/notes");
        setNotes(response.data);
      } catch (error) {
        console.error("Error fetching notes:", error);
      }
    };

    fetchNotes();
    fetchDbInfo();
  }, []);

  const addNote = async (title, text) => {
    try {
      const response = await axios.post("/api/notes", { title, text });
      const newNote = response.data;
      setNotes([...notes, newNote]);
      // Dopo aver aggiunto il nuovo elemento, richiedi le informazioni aggiornate dal backend
      fetchDbInfo();
    } catch (error) {
      console.error("Error adding note:", error);
    }
  };

  const updateNote = async (index, title, text) => {
    try {
      await axios.put(`/api/notes/${index}`, { title, text });
      const updatedNotes = [...notes];
      updatedNotes[index] = { title, text };
      setNotes(updatedNotes);
    } catch (error) {
      console.error("Error updating note:", error);
    }
  };

  const deleteNote = async (index) => {
    try {
      await axios.delete(`/api/notes/${index}`);
      const updatedNotes = notes.filter((_, i) => i !== index);
      setNotes(updatedNotes);
      // Dopo aver cancellato l'elemento, richiedi le informazioni aggiornate dal backend
      fetchDbInfo();
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
      <NoteForm onAddNote={addNote} />
      <NoteList
        notes={notes}
        onUpdateNote={updateNote}
        onDeleteNote={deleteNote}
      />
    </div>
  );
}

export default App;
