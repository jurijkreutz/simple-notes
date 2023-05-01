import React from 'react'
import { Link } from 'react-router-dom'

export default function Menu() {
  return (
    <div className='menu-container'>
      <ul className='menu'>
        <li><Link to="/">All Notes</Link></li> 
        <li><Link to="/add-note">Add Note</Link></li>
      </ul>
    </div>
  )
}
