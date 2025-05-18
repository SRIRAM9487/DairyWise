import React from 'react'
import SideBar from '../Navigation/SideBar'
import { Outlet } from 'react-router-dom'
import TopBar from '../Navigation/TopBar'

function DefaultPageLayout() {
  return (
    <div className='flex  w-screen h-screen overflow-hidden'>

      <div className='w-[12%] min-w-[150px] max-w-[200px] bg-gray-50'>
        <SideBar />
      </div>

      <div className='flex flex-col flex-1 overflow-hidden' >
        <div className='shrink-0'> <TopBar />
        </div>
        <div className='flex-1 overflow-auto p-4'>
          <Outlet />
        </div>
      </div>

    </div>
  )
}

export default DefaultPageLayout
