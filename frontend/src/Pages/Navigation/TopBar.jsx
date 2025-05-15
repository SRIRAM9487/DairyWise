import React from 'react'
import { IoIosLogOut } from "react-icons/io";

function TopBar() {
  return (
    <div className='h-[50px] flex justify-end border-b items-center '>
      <div>
        <button className='h-[40px] w-[110px] border  mr-6 bg-blue-600 text-white hover:bg-blue-700 flex flex-row justify-center items-center transition duration-200 ease-in-out'>
          <IoIosLogOut className='w-5 h-5 mr-2' />
          Log In
        </button>
      </div>
    </div>
  )

}

export default TopBar
