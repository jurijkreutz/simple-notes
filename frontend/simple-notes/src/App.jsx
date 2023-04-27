import { useState } from 'react'
import './App.css'
import NoteList from './components/NoteList'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <h1>Simple Note App</h1>
      <div className="card">
        <NoteList/>
      </div>
      <p className="read-the-docs">
        This app allows you to see your latest notes.
      </p>
    </>
  )
}

export default App
