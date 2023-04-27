import React from 'react'
import { useEffect, useState } from 'react';
import { fetchNotes } from '../fetch';

export default function NoteList() {

    const [notes, setNotes] = useState([]);

    useEffect(() => {
      async function getNotes() {
        setNotes(await fetchNotes());
      }
      getNotes();
    }, [])

    console.log(notes);

  return (
    <div>
      {notes.map((note) => (
        <div key={note.id}>
          <h2>{note.title}</h2>
          <p>{note.content}</p>
        </div>
      ))}
    </div>
  )
}
