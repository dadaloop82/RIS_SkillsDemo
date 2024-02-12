import React, { useState, useEffect, useRef } from "react";
import { Form, Button, CloseButton } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPlus, faTimes } from "@fortawesome/free-solid-svg-icons";

function NoteForm({ onAddNote }) {
  const [title, setTitle] = useState("");
  const [text, setText] = useState("");
  const [showForm, setShowForm] = useState(false);

  const formRef = useRef(null);

  useEffect(() => {
    const handleClickOutside = (event) => {
      if (formRef.current && !formRef.current.contains(event.target)) {
        setShowForm(false);
      }
    };

    document.addEventListener("mousedown", handleClickOutside);
    return () => {
      document.removeEventListener("mousedown", handleClickOutside);
    };
  }, []);

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!title.trim() || !text.trim()) return;
    onAddNote(title, text);
    setTitle("");
    setText("");
    setShowForm(false);
  };

  return (
    <div ref={formRef}>
      {!showForm ? (
        <Button variant="outline-primary" onClick={() => setShowForm(true)}>
          <FontAwesomeIcon icon={faPlus} /> Add Note
        </Button>
      ) : (
        <Form onSubmit={handleSubmit} className="newForm">
          <CloseButton
            onClick={() => setShowForm(false)}
            className="close-button"
            aria-label="Close"
          >
            <FontAwesomeIcon icon={faTimes} />
          </CloseButton>
          <Form.Group controlId="formTitle">
            <Form.Control
              type="text"
              placeholder="Title"
              value={title}
              onChange={(e) => setTitle(e.target.value)}
              required
            />
          </Form.Group>
          <Form.Group controlId="formText">
            <Form.Control
              as="textarea"
              rows={3}
              placeholder="Text"
              value={text}
              onChange={(e) => setText(e.target.value)}
              required
            />
          </Form.Group>
          <Button variant="primary" type="submit">
            <FontAwesomeIcon icon={faPlus} /> Add Note
          </Button>
        </Form>
      )}
    </div>
  );
}

export default NoteForm;
