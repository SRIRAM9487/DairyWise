import React, { useState } from 'react';
import { FaSearch, FaHome, FaChartBar, FaCog, FaUsers, FaFileAlt } from 'react-icons/fa';
import { BiLogOut } from 'react-icons/bi';
import { NavLink } from 'react-router-dom';

function SideBar() {
  const [searchValue, setsearchValue] = useState('');

  const baseLiCss = `w-full h-[50px] flex items-center pl-6 transition-colors`;
  const iconCss = `mr-3 text-lg`;
  const activeClass = `bg-blue-100 text-blue-600 font-semibold border-l-4 border-blue-600`;

  return (
    <div className='border-r border-gray-200 w-[250px] h-full flex flex-col justify-between bg-white'>

      <div>

        <div className='flex items-center justify-center h-[120px] border-b border-gray-200'>
          <span className='text-xl font-semibold text-gray-800'>My Dashboard</span>
        </div>

        <div className="flex items-center border-b border-gray-200 p-3">
          <FaSearch className="text-gray-400 mr-2" />
          <input
            type="text"
            name="search"
            id="search"
            placeholder="Search..."
            className="w-full h-[35px] border-none focus:ring-0 text-sm text-gray-700"
            value={searchValue}
            onChange={(e) => setsearchValue(e.target.value)}
          />
        </div>

        <ul className='flex flex-col py-2'>
          <NavLink to="/dashboard" className={({ isActive }) => `${baseLiCss} ${isActive ? activeClass : 'text-gray-700 hover:bg-gray-100'}`}>
            <FaHome className={iconCss} />
            Dashboard
          </NavLink>

          <NavLink to="/analytics" className={({ isActive }) => `${baseLiCss} ${isActive ? activeClass : 'text-gray-700 hover:bg-gray-100'}`}>
            <FaChartBar className={iconCss} />
            Analytics
          </NavLink>

          <NavLink to="/users" className={({ isActive }) => `${baseLiCss} ${isActive ? activeClass : 'text-gray-700 hover:bg-gray-100'}`}>
            <FaUsers className={iconCss} />
            Users
          </NavLink>

          <NavLink to="/reports" className={({ isActive }) => `${baseLiCss} ${isActive ? activeClass : 'text-gray-700 hover:bg-gray-100'}`}>
            <FaFileAlt className={iconCss} />
            Reports
          </NavLink>

        </ul>

      </div>

      <div className='p-4 border-t border-gray-200'>
        <button className='flex items-center text-gray-600 hover:text-gray-800 transition-colors'>
          <BiLogOut className='mr-3 text-lg' />
          Logout
        </button>
      </div>

    </div>
  );
}

export default SideBar;
