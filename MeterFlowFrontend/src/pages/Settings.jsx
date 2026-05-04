import React from "react";
import Sidebar from "../components/Sidebar";
import Navbar from "../components/Navbar";

export default function Settings() {
  return (
    <div className="flex bg-[#19160b] min-h-screen text-white">
      <Sidebar />

      <div className="flex-1 ml-64">
        <Navbar />

        <div className="p-8 space-y-6">
          <h1 className="text-2xl font-bold">Settings</h1>

          <div className="bg-[#111827] p-6 rounded-xl">
            <p className="text-gray-400">
              Settings panel (Environment, API configs, etc)
            </p>
          </div>

          {/* 🔥 FUTURE: ENV TOGGLE */}
          <div className="bg-[#111827] p-6 rounded-xl">
            <h2 className="mb-2 font-semibold">Environment</h2>
            <p className="text-gray-400">Switch between TEST / LIVE</p>
          </div>

        </div>
      </div>
    </div>
  );
}