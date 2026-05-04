
// import React from "react";
// import { NavLink } from "react-router-dom";
// import { LayoutDashboard, Key, CreditCard, BarChart3, User, Settings } from "lucide-react";

// const menu = [
//   { name: "Dashboard", icon: <LayoutDashboard />, path: "/dashboard" },
//   { name: "APIs", icon: <Key />, path: "/apis" },
//   { name: "Billing", icon: <CreditCard />, path: "/billing" },
//   { name: "Analytics", icon: <BarChart3 />, path: "/usage" },
//   { name: "Profile", icon: <User />, path: "/profile" },
//   { name: "Settings", icon: <Settings />, path: "/settings" },
// ];

// export default function Sidebar() {
//   return (
//     <div className="fixed top-0 left-0 h-screen w-64 bg-[#0B0F19] text-white border-r border-gray-800">

//       <div className="p-6 text-xl font-bold">
//         ⚡ MeterFlow
//       </div>

//       <div className="space-y-2 px-3">
//         {menu.map((item) => (
//           <NavLink
//             key={item.name}
//             to={item.path}
//             className={({ isActive }) =>
//               `flex items-center gap-3 px-4 py-3 rounded-xl text-sm ${
//                 isActive
//                   ? "bg-indigo-600 text-white"
//                   : "text-gray-400 hover:bg-gray-800 hover:text-white"
//               }`
//             }
//           >
//             {item.icon}
//             {item.name}
//           </NavLink>
//         ))}
//       </div>

//     </div>
//   );
// }


//testing

import React from "react";
import { NavLink } from "react-router-dom";
import {
  LayoutDashboard,
  Key,
  CreditCard,
  BarChart3,
  User,
  Settings,
  Shield
} from "lucide-react";

export default function Sidebar() {
  const role = localStorage.getItem("role"); // 🔥 get role

  const menu = [
    { name: "Dashboard", icon: <LayoutDashboard />, path: "/dashboard" },
    { name: "APIs", icon: <Key />, path: "/apis" },
    // { name: "Billing", icon: <CreditCard />, path: "/billing" },
    { name: "Analytics", icon: <BarChart3 />, path: "/usage" },
   // { name: "Profile", icon: <User />, path: "/profile" },
    //{ name: "Settings", icon: <Settings />, path: "/settings" },

    // 🔥 ADMIN ONLY MENU
    ...(role === "ROLE_ADMIN"
      ? [
          {
            name: "Admin Panel",
            icon: <Shield />,
            path: "/admin",
          },
        ]
      : []),
  ];

  return (
    <div className="fixed top-0 left-0 h-screen w-64 bg-[#0B0F19] text-white border-r border-gray-800">

      {/* LOGO */}
      <div className="p-6 text-xl font-bold">
        ⚡ MeterFlow
      </div>

      {/* MENU */}
      <div className="space-y-2 px-3">
        {menu.map((item) => (
          <NavLink
            key={item.name}
            to={item.path}
            className={({ isActive }) =>
              `flex items-center gap-3 px-4 py-3 rounded-xl text-sm transition ${
                isActive
                  ? "bg-indigo-600 text-white"
                  : "text-gray-400 hover:bg-gray-800 hover:text-white"
              }`
            }
          >
            {item.icon}
            {item.name}
          </NavLink>
        ))}
      </div>

    </div>
  );
}