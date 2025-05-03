import React, { useState } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

import Login from './Pages/Auth/Login';
import Dashboard from './Component/Layout/Dashboard';
import PageLayout from './Pages/PageLayout';
import AdminDashBoard from './Pages/Admin/AdminDashBoard';
import ManagerDashBoard from './Pages/Manager/ManagerDashBoard';
import ProtectedRoute from './AuthContext/ProtectedRoute';

function App() {

  const [role] = useState("ADMIN");

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/login" element={<Login />} />

        <Route element={<ProtectedRoute />}>
          <Route element={<PageLayout />}>
            {
              role == "ADMIN" ?
                <Route path='/dashboard' element={<AdminDashBoard />} />
            :
            role == "MANAGER" ?
                <Route path='/dashboard' element={<ManagerDashBoard />} />
            : null
            }
            <Route path='/dashboard' element={<Dashboard />} />
          </Route>
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
