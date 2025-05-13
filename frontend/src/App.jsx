import React from 'react'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import LoginPage from './Pages/Auth/LoginPage'
import AdminDashboard from './Pages/Admin/AdminDashboard'
import ManagerDashboard from './Pages/Manager/ManagerDashboard';
import Dashboard from './Pages/Layout/Dashboard';
import DefaultPageLayout from './Pages/Layout/DefaultPageLayout';
import Users from './Pages/Admin/Users';

function App() {
  let role = localStorage.getItem("role");
  role = "ADMIN";
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/login' element={<LoginPage />} />
        <Route path='/' element={<DefaultPageLayout />}>
          {
            role == "ADMIN" ? (
              <Route path='/admin/dashboard' element={<AdminDashboard />} />
            ) :
              role == "MANAGER" ? (
                <Route path='/manager/dashboard' element={<ManagerDashboard />} />
              ) :
                <Route path='/dashboard' element={<Dashboard />} />
          }
          <Route path='/admin/user' element={<Users />} />
        </Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App
