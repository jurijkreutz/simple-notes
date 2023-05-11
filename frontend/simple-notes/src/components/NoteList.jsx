import React, { useEffect } from 'react'
import Note from './Note'
import Login from './Login'


export default function NoteList({ notes, updateNotes, userLoggedIn, setUserLoggedIn }) {

  useEffect(() => {
    if (userLoggedIn) {
      updateNotes();
    }
  }, [])


  return (
    <>
      {userLoggedIn ? 
         <div id="note-list">
          {notes.map((note) => (
          <Note note={note} key={note.id} updateNotes={updateNotes} />
          ))}
        </div>
      : <Login setUserLoggedIn={setUserLoggedIn} />
      }
    </>
  )
}
