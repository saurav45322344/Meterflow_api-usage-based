import React from "react";

export default function Card({ title, value, icon }) {
  return (
    <div className="bg-[#111827] border border-gray-800 p-6 rounded-2xl shadow-lg hover:scale-[1.02] transition">

      <div className="flex justify-between items-center text-gray-400">
        <span>{title}</span>
        <div className="text-indigo-400">{icon}</div>
      </div>

      <p className="text-3xl font-semibold mt-3 text-white">
        {value}
      </p>
    </div>
  );
}