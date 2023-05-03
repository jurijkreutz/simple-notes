import React from 'react'
import { Link } from 'react-router-dom'

export default function Menu( {userLoggedIn, setUserLoggedIn} ) {

  const logoutUser = () => {
    console.log("Logout clicked");
    setUserLoggedIn(false);
    localStorage.removeItem('token');
  }

  return (
    <div className='menu-container'>
      <ul className='menu'>
        <li><Link to="/">All Notes</Link></li> 
        <li><Link to="/add-note">Add Note</Link></li>
        {userLoggedIn ? <li onClick={logoutUser}><a href="#">Logout</a></li> : ''}
      </ul>
    </div>
  )
}
