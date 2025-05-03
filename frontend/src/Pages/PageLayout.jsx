import React from 'react'
import { Outlet } from 'react-router-dom'
import SideBar from '../Component/Common/SideBar'

function PageLayout() {
  return (
    <div className='flex flex-row h-screen w-full '>
      <SideBar />
      <Outlet />
    </div>
  )
}

export default PageLayout
