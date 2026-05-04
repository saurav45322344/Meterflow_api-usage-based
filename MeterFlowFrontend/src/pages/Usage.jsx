// import React from "react";
// import Sidebar from "../components/Sidebar";
// import Navbar from "../components/Navbar";

// export default function Usage() {
//   return (
//     <div className="flex bg-[#19160b] min-h-screen text-white">
//       <Sidebar />

//       <div className="flex-1 ml-64">
//         <Navbar />

//         <div className="p-8">
//           <h1 className="text-2xl font-bold mb-4">Usage Analytics</h1>

//           <div className="bg-[#111827] p-6 rounded-xl">
//             <p className="text-gray-400">
//               Usage charts will appear here 🚀
//             </p>
//           </div>
//         </div>
//       </div>
//     </div>
//   );
// }

// import React, { useEffect, useState } from "react";
// import api from "../services/api";
// import Sidebar from "../components/Sidebar";
// import Navbar from "../components/Navbar";

 import React, { useEffect, useState } from "react";
 import api from "../services/api";

export default function Usage() {
  const [data, setData] = useState([]);

  useEffect(() => {
    fetchUsage();
  }, []);

  const fetchUsage = async () => {
    try {
      const res = await api.get("/usage/chart");

      console.log("🔥 API RESPONSE:", res.data); // ✅ DEBUG

      let chart = res.data;

      // 🔥 handle string response (VERY COMMON BUG)
      if (typeof chart === "string") {
        chart = JSON.parse(chart);
      }

      setData(chart);

    } catch (err) {
      console.error("Usage error:", err);
      setData([]);
    }
  };

  return (
    <div>
      <h2 className="text-xl mb-4">Usage Analytics</h2>

      {data.length === 0 ? (
        <p>No usage data yet</p>
      ) : (
        data.map((item, index) => (
          <div key={index} className="mb-2 p-2 bg-gray-800 rounded">
            <p>Date: {item.date || item[0]}</p>
            <p>Requests: {item.requests || item[1]}</p>
          </div>
        ))
      )}
    </div>
  );
}