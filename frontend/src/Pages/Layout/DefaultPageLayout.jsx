import React from 'react'
import SideBar from '../Navigation/SideBar'
import { Outlet } from 'react-router-dom'
import TopBar from '../Navigation/TopBar'

function DefaultPageLayout() {
  return (
    <div className='flex flex-row w-screen h-screen'>

      <div className='w-[12%] min-w-12 bg-gray-50'>
        <SideBar />
      </div>

      <div className='flex flex-col flex-1'>
        <div>
          <TopBar />
        </div>
        <div className='flex-1 overflow-auto '>
          <Outlet />
        </div>
      </div>

    </div>
  )
}

export default DefaultPageLayout
