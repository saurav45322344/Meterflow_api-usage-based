// import React from "react";

// export default function SubscriptionCard({ billing }) {

//   if (!billing) return null;

//   const usagePercent = billing.limit
//     ? Math.min((billing.totalRequests / billing.limit) * 100, 100)
//     : 0;

//   return (
//     <div className="bg-gradient-to-r from-indigo-500 to-purple-600 text-white p-6 rounded-xl shadow space-y-4">

//       <h2 className="text-lg font-bold">Current Plan</h2>

//       <p className="text-2xl font-bold">{billing.plan} PLAN</p>

//       <p className="text-sm">
//         {billing.totalRequests} / {billing.limit} requests used
//       </p>

//       {/* PROGRESS BAR */}
//       <div className="w-full bg-white/30 rounded-full h-2">
//         <div
//           className="bg-white h-2 rounded-full"
//           style={{ width: `${usagePercent}%` }}
//         />
//       </div>

//       {/* WARNING */}
//       {usagePercent > 80 && (
//         <p className="text-yellow-200 text-sm">
//           ⚠️ You are close to your limit
//         </p>
//       )}

//       <button className="mt-2 bg-white text-black px-4 py-2 rounded hover:bg-gray-200">
//         Upgrade Plan
//       </button>

//     </div>
//   );
// }


import React from "react";

export default function SubscriptionCard({ billing }) {

  if (!billing) return null;

  const usagePercent = billing.limit
    ? Math.min(((billing.totalRequests || 0) / billing.limit) * 100, 100)
    : 0;

  return (
    <div className="bg-gradient-to-r from-indigo-500 to-purple-600 text-white p-6 rounded-xl shadow space-y-4">

      <h2 className="text-lg font-bold">Current Plan</h2>

      <p className="text-2xl font-bold">{billing.plan} PLAN</p>

      <p className="text-sm">
        {(billing.totalRequests || 0)} / {(billing.limit || 0)} requests used
      </p>

      <div className="w-full bg-white/30 rounded-full h-2">
        <div
          className="bg-white h-2 rounded-full"
          style={{ width: `${usagePercent}%` }}
        />
      </div>

      {usagePercent > 80 && (
        <p className="text-yellow-200 text-sm">
          ⚠️ You are close to your limit
        </p>
      )}

      <button className="mt-2 bg-white text-black px-4 py-2 rounded hover:bg-gray-200">
        Upgrade Plan
      </button>

    </div>
  );
}