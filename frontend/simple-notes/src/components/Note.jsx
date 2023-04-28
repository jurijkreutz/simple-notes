import React from 'react'
import { removeNote, updateNote } from '../fetch'

export default function Note({note, updateNotes}) {

    async function removeNoteHandler() {
        const response = await removeNote(note.id);
        if (response == 200) {
            console.log('Successfully removed from server!');
            updateNotes();
        }
    }

    async function updateNoteHandler() {
        const newTitle = prompt('Enter new Note Title', note.title);
        const newContent = prompt('Enter new Note Content', note.content);
        const response = await updateNote(note.id, newTitle, newContent);
        if (response == 200) {
            console.log('Successfully updated on server!');
            updateNotes();
        }
    }

  return (
    <div className="noteContainer">
        <h2>{note.title}</h2>
        <p>{note.content}</p>
        <button onClick={removeNoteHandler}>Remove</button>
        <button onClick={updateNoteHandler}>Change</button>
    </div>
  )
}
