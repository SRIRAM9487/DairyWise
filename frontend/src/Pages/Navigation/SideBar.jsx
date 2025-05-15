import React from 'react';
import { NavLink } from 'react-router-dom';
import { FaUserCircle, FaSearch } from 'react-icons/fa';

function SideBar() {

  // ADMIN ROLE FIELDS
  const adminFields = [
    { field: "Dashboard", navigate: "/admin/dashboard" },
    { field: "User", navigate: "/admin/user" },
  ];

  // MANAGER ROLE FIELDS
  const managerFields = [
    { field: "Dashboard", navigate: "/manager/dashboard" },
    { field: "Customers", navigate: "/manager/customers" },
  ];

  // ROLE FROM LOCAL STORAGE 
  const role = "ADMIN";

  return (

    <nav className="h-screen shadow-lg flex flex-col">

      <div className="flex flex-col items-center py-6 border-b">
        <FaUserCircle className="text-4xl text-blue-600 mb-1" />
        <h1 className="text-lg font-semibold text-gray-800">Manager</h1>
      </div>

      <div className="relative px-4 py-3">
        <FaSearch className="absolute left-5 top-1/2 transform -translate-y-1/2 text-gray-400" />
        <input
          type="text"
          placeholder="Search..."
          className="w-full pl-10 pr-4 py-2 border border-gray-300 focus:ring-2 focus:ring-blue-500 outline-none text-sm shadow-sm"
        />
      </div>

      <div className="px-4 flex-1 overflow-y-auto">

        <ul className="space-y-2 mt-2">
          {
            (role === "ADMIN" ? adminFields : role === "MANAGER" ? managerFields : []).map((item, index) => (

              <li key={index}>

                <NavLink
                  to={item.navigate}
                  className={({ isActive }) =>
                    `flex items-center gap-2 px-4 py-2 text-sm font-medium transition-all duration-200 ${isActive
                      ? 'bg-blue-600 text-white shadow'
                      : 'text-gray-700 hover:bg-blue-100 hover:text-blue-800'
                    }`}>

                  <span className="truncate">{item.field}</span>
                </NavLink>
              </li>
            ))}

        </ul>
      </div>
    </nav>
  );
}

export default SideBar;
