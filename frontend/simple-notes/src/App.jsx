import { useState } from 'react'
import './App.css'
import NoteList from './components/NoteList'
import AddNote from './components/AddNote'

function App() {
  return (
    <>
      <h1>Simple Note App</h1>
        <AddNote/>
        <NoteList/>
    </>
  )
}

export default App
