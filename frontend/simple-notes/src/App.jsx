import { useState, useEffect } from 'react'
import './App.css'
import NoteList from './components/NoteList'
import AddNote from './components/AddNote'
import Layout from './components/Layout'
import Login from './components/Login'
import Register from './components/Register'
import { fetchNotes } from './fetch'
import { BrowserRouter, Routes, Route } from "react-router-dom";

function App() {

  const [notes, setNotes] = useState([]);

  useEffect(() => {
    if (userLoggedIn) {
      async function getNotes() {
        setNotes(await fetchNotes());
      }
      getNotes();
    }
  }, [])

  const updateNotes = async () => {
    console.log('fetching')
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
                <Route path="login" element={<Login setUserLoggedIn={setUserLoggedIn} /> } />
                <Route path="register" element={<Register/> } />
              </Route>
        </Routes>
      </BrowserRouter>
    </>
  )
}

export default App
