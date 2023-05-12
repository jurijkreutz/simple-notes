import React from 'react'
import { useState } from 'react';
import { login } from '../fetch';
import { useNavigate } from 'react-router-dom';
import BodyBox from './boxes/BodyBox';
import WarningBox from './boxes/WarningBox';

export default function Login( {setUserLoggedIn} ) {

  const navigate = useNavigate();

    const [inputData, setInputData] = useState({
        email: "",
        password: ""
      });

    const handleChange = (event) => {
        const value = event.target.value;
        setInputData({
            ...inputData,
            [event.target.name]: value
        });
    };

    async function loginHandler(event) {
        event.preventDefault();
        const result = await login(inputData.email, inputData.password)
        if (result.status == 200) {
          console.log(result);
          console.log('Successfully logged in on server!');
          localStorage.setItem('token', result.data.jwt);
          setUserLoggedIn(true);
          navigate("/");
        }
    }


  return (
    <div>
      <WarningBox text={'You are not logged in. Please log in to see & manage your notes.'} />
      <BodyBox content={
        <form>
          <label htmlFor="email">E-Mail:</label><br/>
          <input type="text" id="email" name="email" value={inputData.email} onChange={handleChange}></input>
          <br/><br/>
          <label htmlFor="password">Password:</label><br/>
          <input type="text" id="password" name="password" value={inputData.password} onChange={handleChange}></input>
          <br></br><br></br>
          <input type="submit" onClick={loginHandler}></input>
        </form> }
        >
       </BodyBox>
    </div>
  )
}
