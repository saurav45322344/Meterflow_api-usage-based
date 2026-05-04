import React from "react";
import Sidebar from "../components/Sidebar";
import Navbar from "../components/Navbar";

export default function Profile() {
  return (
    <div className="flex bg-[#19160b] min-h-screen text-white">
      <Sidebar />

      <div className="flex-1 ml-64">
        <Navbar />

        <div className="p-8">
          <h1 className="text-2xl font-bold mb-4">Profile</h1>

          <div className="bg-[#111827] p-6 rounded-xl">
            <p className="text-gray-400">
              User profile details will be shown here 🚀
            </p>
          </div>
        </div>
      </div>
    </div>
  );
}