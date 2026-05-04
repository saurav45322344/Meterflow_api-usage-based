import React from "react";

export default function Plan({ title, price, onClick, highlight }) {
  return (
    <div
      className={`p-6 rounded-2xl border transition ${
        highlight
          ? "border-indigo-500 bg-indigo-500/10 scale-[1.03]"
          : "border-gray-800 bg-[#0F172A]"
      }`}
    >
      <h3 className="font-semibold text-lg">{title}</h3>

      <p className="text-3xl font-bold mt-2">{price}</p>

      <button
        onClick={onClick}
        className="mt-5 w-full bg-indigo-600 hover:bg-indigo-700 transition py-2 rounded-lg"
      >
        Buy Now
      </button>
    </div>
  );
}