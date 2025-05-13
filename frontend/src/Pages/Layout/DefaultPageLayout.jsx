import React from 'react'
import SideBar from '../Navigation/SideBar'
import { Outlet } from 'react-router-dom'

function DefaultPageLayout() {
  return (
    <div className='flex flex-row w-screen h-screen'>

      <div className='w-[12%] min-w-12'>
        <SideBar />
      </div>

      <div className='flex-1'>
        <Outlet />
      </div>

    </div>
  )
}

export default DefaultPageLayout
