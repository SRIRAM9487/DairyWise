import { Outlet, Navigate } from "react-router-dom";


const ProtectedRoute = () => {
  const user = "ADMIN";
  return user ? <Outlet /> : <Navigate to="/login" />;
}

export default ProtectedRoute;
