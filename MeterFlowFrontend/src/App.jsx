// import React from "react";   // ✅ ADD THIS
// import { BrowserRouter, Routes, Route } from "react-router-dom";
// import Signup from "./pages/Signup";
// import Login from "./pages/Login";
// import Dashboard from "./pages/Dashboard";
// import ApiKeys from "./pages/ApiKeys";
// import Analytics from "./pages/Analytics";
// import { Navigate } from "react-router-dom";


// export default function App() {
//   return (
//     <BrowserRouter>
//       <Routes>
//         <Route path="/signup" element={<Signup />} />
//         <Route path="/" element={<Login />} />
//         <Route path="/dashboard" element={<Dashboard />} />
//         <Route path="/keys" element={<ApiKeys />} />
//         <Route path="/analytics" element={<Analytics />} />
//          <Route path="/billing" element={<div>Billing Page</div>} />
//         <Route path="/profile" element={<div>Profile Page</div>} />
//         <Route path="/settings" element={<div>Settings Page</div>} />
//       </Routes>
//     </BrowserRouter>
//   );
// }



import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";

import Login from "./pages/Login";
import Signup from "./pages/Signup";
import Dashboard from "./pages/Dashboard";
import ApiKeys from "./pages/ApiKeys";
import Billing from "./pages/Billing";
import Usage from "./pages/Usage";   // ✅ ADD THIS
import Profile from "./pages/Profile";
import Settings from "./pages/Settings";

import AdminDashboard from "./pages/AdminDashboard";

export default function App() {
  return (
    <BrowserRouter>
      <Routes>

        <Route path="/" element={<Login />} />
        <Route path="/signup" element={<Signup />} />

        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/apis" element={<ApiKeys />} />
        <Route path="/billing" element={<Billing />} />
        <Route path="/usage" element={<Usage />} />   {/* ✅ FIX */}
        <Route path="/profile" element={<Profile />} />
        <Route path="/settings" element={<Settings />} />
<Route path="/admin" element={<AdminDashboard />} />
      </Routes>
    </BrowserRouter>
  );
}