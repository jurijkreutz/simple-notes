import React, { useEffect } from 'react'
import Note from './Note'
import WarningBox from './boxes/WarningBox';


export default function NoteList({ notes, updateNotes, userLoggedIn }) {

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
      : <WarningBox text={'You are not logged in. Please log in to see & manage your notes.'} />
      }
    </>
  )
}
