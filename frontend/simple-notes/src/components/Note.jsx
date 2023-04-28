import React from 'react'
import { removeNote } from '../fetch'

export default function Note({note, updateNotes}) {

    async function removeNoteHandler() {
        const response = await removeNote(note.id);
        if (response == 200) {
            console.log('Successfully removed from server!');
            updateNotes();
        }
    }

  return (
    <div className="noteContainer">
        <h2>{note.title}</h2>
        <p>{note.content}</p>
        <button onClick={removeNoteHandler}>Remove</button>
    </div>
  )
}
