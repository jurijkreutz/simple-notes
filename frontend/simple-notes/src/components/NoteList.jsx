import React from 'react'
import Note from './Note'


export default function NoteList({ notes, updateNotes }) {

  return (
    <div id="noteList">
      {notes.map((note) => (
        <Note note={note} key={note.id} updateNotes={updateNotes} />
      ))}
    </div>
  )
}
