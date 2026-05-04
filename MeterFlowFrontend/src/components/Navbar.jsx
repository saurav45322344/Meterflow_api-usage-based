
// import React from "react";
// import { Bell } from "lucide-react";

// export default function Navbar() {
//   return (
//     <div className="sticky top-0 z-50 backdrop-blur-xl bg-[#0B0F19]/70 border-b border-white/10 px-8 py-4 flex justify-between items-center">

//       {/* LEFT */}
//       <h1 className="text-lg font-semibold tracking-wide text-white">
//         Dashboard
//       </h1>

//       {/* RIGHT */}
//       <div className="flex items-center gap-6">

//         {/* Notification */}
//         <div className="relative cursor-pointer">
//           <Bell className="text-gray-400 hover:text-white transition" />
//           <span className="absolute -top-1 -right-1 bg-red-500 text-xs px-1 rounded-full">
//             3
//           </span>
//         </div>

//         {/* PROFILE */}
//         <div className="flex items-center gap-3 cursor-pointer hover:bg-white/5 px-3 py-2 rounded-lg transition">
//           <img
//             src="https://i.pravatar.cc/40"
//             alt="profile"
//             className="w-8 h-8 rounded-full"
//           />
//           <div className="text-sm">
//             <p className="font-medium">Saurav</p>
//             <p className="text-gray-400 text-xs">API Owner</p>
//           </div>
//         </div>

//       </div>
//     </div>
//   );
// }

import React from "react";
import { Bell, LogOut } from "lucide-react";
import { useNavigate } from "react-router-dom";

export default function Navbar() {
  const navigate = useNavigate();

  const handleLogout = () => {
    // clear auth
    localStorage.removeItem("token");
    localStorage.removeItem("apiKey");

    // redirect
    navigate("/login");
  };

  return (
    <div className="sticky top-0 z-50 backdrop-blur-xl bg-[#0B0F19]/70 border-b border-white/10 px-8 py-4 flex justify-between items-center">

      {/* LEFT */}
      <h1 className="text-lg font-semibold tracking-wide text-white">
        Dashboard
      </h1>

      {/* RIGHT */}
      <div className="flex items-center gap-6">

        {/* Notification */}
        <div className="relative cursor-pointer">
          <Bell className="text-gray-400 hover:text-white transition" />
          <span className="absolute -top-1 -right-1 bg-green-500 text-xs px-1 rounded-full">
            3
          </span>
        </div>

        {/* PROFILE */}
        <div className="flex items-center gap-3 cursor-pointer hover:bg-white/5 px-3 py-2 rounded-lg transition">

          <img
            src="https://i.pravatar.cc/40"
            alt="profile"
            className="w-8 h-8 rounded-full"
          />

          <div className="text-sm">
            <p className="font-medium">Saurav</p>
            <p className="text-gray-400 text-xs">API Owner</p>
          </div>

        </div>

        {/* LOGOUT BUTTON (NEW) */}
        <button
          onClick={handleLogout}
          className="flex items-center gap-2 text-green-400 hover:text-orange-300 transition"
        >
          <LogOut size={20} />
          <span className="text-sm">Logout</span>
        </button>

      </div>
    </div>
  );
}