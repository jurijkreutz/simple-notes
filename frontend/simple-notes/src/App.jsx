import { useState, useEffect } from 'react'
import './App.css'
import NoteList from './components/NoteList'
import AddNote from './components/AddNote'
import { fetchNotes } from './fetch'

function App() {

  const [notes, setNotes] = useState([]);

  useEffect(() => {
    async function getNotes() {
      setNotes(await fetchNotes());
    }
    getNotes();
  }, [])

  const updateNotes = async () => {
    setNotes(await fetchNotes());
  }

  return (
    <>
      <h1>Simple Note App</h1>
        <AddNote notes={notes} updateNotes={updateNotes}/>
        <NoteList notes={notes}/>
    </>
  )
}

export default App
