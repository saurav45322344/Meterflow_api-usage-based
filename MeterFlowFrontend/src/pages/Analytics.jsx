import React from "react";
import { useEffect, useState } from "react";
import api from "../services/api";
import usageApi from "../services/usageApi";
import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  Tooltip,
  ResponsiveContainer,
} from "recharts";

export default function Analytics() {
  const [data, setData] = useState([]);

  useEffect(() => {
    load();
  }, []);

  // const load = async () => {
  //   try {
  //     const apiKey = localStorage.getItem("apiKey");
  //     const res = await api.get(`/usage?apiKey=${apiKey}`);
  //     setData(res.data);
  //   } catch (err) {
  //     console.error(err);
  //   }
  // };
  const load = async () => {
  try {
    const apiKey = localStorage.getItem("apiKey");

    if (!apiKey) {
      console.error("No API key found");
      return;
    }

    //const res = await api.get("/usage"); // ✅ no query param
    //const res = await usageApi.get("/usage");
    const res = await api.get(`/usage/chart?apiKey=${apiKey}`);
   // setData(res.data);
   setData(Array.isArray(res.data) ? res.data : []);
   

  } catch (err) {
    console.error("Analytics error:", err);
  }
};

//   return (
//     <div className="p-6">
//       <h1 className="text-2xl font-bold mb-4">Analytics</h1>

//       <div className="bg-white p-4 shadow rounded">
//         <ResponsiveContainer width="100%" height={300}>
//           <LineChart data={data}>
//             <XAxis dataKey="timestamp" />
//             <YAxis />
//             <Tooltip />
//             <Line type="monotone" dataKey="requests" stroke="#6366f1" />
//           </LineChart>
//         </ResponsiveContainer>
//       </div>
//     </div>
//   );
// }

return (
  <div className="p-6">
    <h1 className="text-2xl font-bold mb-4">Analytics</h1>

    <div className="bg-white p-4 shadow rounded">
      {Array.isArray(data) && data.length > 0 ? (
        <ResponsiveContainer width="100%" height={300}>
          <LineChart data={data}>
            <XAxis dataKey="date" />   {/* ✅ use 'date' instead of 'timestamp' */}
            <YAxis />
            <Tooltip />
            <Line type="monotone" dataKey="requests" stroke="#6366f1" />
          </LineChart>
        </ResponsiveContainer>
      ) : (
        <p className="text-gray-500 text-center py-10">
          No analytics data
        </p>
      )}
    </div>
  </div>
);
}