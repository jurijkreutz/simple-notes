import React from 'react'
import Menu from './Menu'
import { Outlet } from 'react-router-dom'

export default function Layout() {
  return (
    <>
      <h1>Simple Note App</h1>
        <Menu/>
        <Outlet />
    </>
  )
}
