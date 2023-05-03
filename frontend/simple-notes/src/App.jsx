import { useState, useEffect } from 'react'
import './App.css'
import NoteList from './components/NoteList'
import AddNote from './components/AddNote'
import Layout from './components/Layout'
import { fetchNotes } from './fetch'
import { BrowserRouter, Routes, Route } from "react-router-dom";

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

  useEffect(() => {
    setUserLoggedIn(localStorage.getItem('token') != null);
  }, [])

  const [userLoggedIn, setUserLoggedIn] = useState(false);

  return (
    <>
      <BrowserRouter>
        <Routes>
              <Route path="/" element={<Layout userLoggedIn={userLoggedIn} setUserLoggedIn={setUserLoggedIn} />}>
              <Route index element={<NoteList notes={notes} updateNotes={updateNotes} userLoggedIn={userLoggedIn} setUserLoggedIn={setUserLoggedIn} />} />
              <Route path="add-note" element={<AddNote notes={notes} updateNotes={updateNotes} userLoggedIn={userLoggedIn} setUserLoggedIn={setUserLoggedIn} />} />
            </Route>
        </Routes>
      </BrowserRouter>
    </>
  )
}

export default App
