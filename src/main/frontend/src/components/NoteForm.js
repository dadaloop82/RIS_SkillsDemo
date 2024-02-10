import React, { useState } from 'react';

function NoteForm({ onAddNote }) {
  // State to manage the title and text of the note
  const [title, setTitle] = useState('');
  const [text, setText] = useState('');

  // Function to handle form submission
  const handleSubmit = (e) => {
    e.preventDefault(); // Prevent the default form submission behavior
    if (!title.trim() || !text.trim()) return; // Check if title or text are empty or whitespace
    onAddNote(title, text); // Call the onAddNote function passed as prop, passing title and text
    setTitle(''); // Clear the title field after submission
    setText(''); // Clear the text field after submission
  };

  return (
    <form onSubmit={handleSubmit}>
      {/* Input field for the title of the note */}
      <input
        type="text"
        placeholder="Title"
        value={title}
        onChange={(e) => setTitle(e.target.value)} // Update the title state as the user types
      />
      {/* Textarea for the text of the note */}
      <textarea
        placeholder="Text"
        value={text}
        onChange={(e) => setText(e.target.value)} // Update the text state as the user types
      />
      {/* Submit button to add the note */}
      <button type="submit">Add Note</button>
    </form>
  );
}

export default NoteForm;
