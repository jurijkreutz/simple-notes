import React from 'react'
import Menu from './Menu'
import { Outlet } from 'react-router-dom'

export default function Layout( {userLoggedIn, setUserLoggedIn} ) {
  return (
    <>
      <h1>Simple Note App</h1>
      <Menu userLoggedIn={userLoggedIn} setUserLoggedIn={setUserLoggedIn} />
      <Outlet />
    </>
  )
}
