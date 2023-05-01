import React from 'react'
import Note from './Note'
import Login from './Login'


export default function NoteList({ notes, updateNotes, userLoggedIn, setUserLoggedIn }) {

  return (
    <div id="note-list">
      {userLoggedIn ? 
        <>
          {notes.map((note) => (
          <Note note={note} key={note.id} updateNotes={updateNotes} />
          ))}
        </>
      : <Login setUserLoggedIn={setUserLoggedIn} />
      }
    </div>
  )
}
