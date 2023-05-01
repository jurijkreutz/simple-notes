import React from 'react'
import { addNote } from '../fetch';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Login from './Login';

export default function AddNote({ updateNotes, userLoggedIn, setUserLoggedIn }) {

    const navigate = useNavigate();

    const [inputData, setInputData] = useState({
        title: "",
        content: ""
      });

    const handleChange = (event) => {
        const value = event.target.value;
        setInputData({
            ...inputData,
            [event.target.name]: value
        });
    };

    async function addNewNote(event) {
        event.preventDefault();
        const result = await addNote(inputData.title, inputData.content)
        if (result == 200) {
          console.log('Successfully saved on server!');
          updateNotes();
          navigate("/");
        }
    }

  return (
    <div className="add-note">
        {userLoggedIn ? 
        <form>
          <label htmlFor="title">Title:</label><br/>
          <input type="text" id="title" name="title" value={inputData.title} onChange={handleChange}></input>
          <br/><br/>
          <label htmlFor="content">Content:</label><br/>
          <textarea name="content" cols="40" rows="5" value={inputData.content} onChange={handleChange}></textarea><br/>
          <input type="submit" onClick={addNewNote}></input>
        </form> 
        : <Login setUserLoggedIn={setUserLoggedIn} />
      }
    </div>
  )
}
