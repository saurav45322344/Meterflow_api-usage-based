// import React, { useEffect, useState } from "react";
// import api from "../services/api";

// export default function AdminDashboard() {

//   const [stats, setStats] = useState({});
//   const [users, setUsers] = useState([]);

//   useEffect(() => {
//     fetchStats();
//     fetchUsers();
//   }, []);

//   const fetchStats = async () => {
//     const res = await api.get("/admin/stats");
//     setStats(res.data);
//   };

//   const fetchUsers = async () => {
//     const res = await api.get("/admin/users");
//     setUsers(res.data);
//   };

//   return (
//     <div className="p-8 text-white">

//       <h1 className="text-2xl mb-6">Admin Dashboard 👑</h1>

//       {/* STATS */}
//       <div className="grid grid-cols-4 gap-6">
//         <Card title="Users" value={stats.users} />
//         <Card title="APIs" value={stats.apis} />
//         <Card title="Requests" value={stats.requests} />
//         <Card title="Revenue" value={`₹${stats.revenue}`} />
//       </div>

//       {/* USERS TABLE */}
//       <div className="mt-8 bg-[#111827] p-6 rounded-xl">
//         <h2 className="mb-4">All Users</h2>

//         {users.map(u => (
//           <div key={u.id} className="flex justify-between border-b py-2">
//             <span>{u.email}</span>
//             <span>{u.role}</span>
//           </div>
//         ))}
//       </div>

//     </div>
//   );
// }

// function Card({ title, value }) {
//   return (
//     <div className="bg-[#1f2937] p-4 rounded-xl">
//       <p>{title}</p>
//       <h2 className="text-xl font-bold">{value}</h2>
//     </div>
//   );
// }




import React, { useEffect, useState } from "react";
import api from "../services/api";
import Sidebar from "../components/Sidebar";
import Navbar from "../components/Navbar";

export default function AdminDashboard() {
  const [stats, setStats] = useState({
    totalUsers: 0,
    totalRevenue: 0,
    totalRequests: 0,
  });

  const [users, setUsers] = useState([]);

  useEffect(() => {
    fetchAdminStats();
    fetchUsers();
  }, []);

  const fetchAdminStats = async () => {
    try {
      const res = await api.get("/admin/stats");
      setStats(res.data);
    } catch (err) {
      console.error(err);
    }
  };

  const fetchUsers = async () => {
    try {
      const res = await api.get("/admin/users");
      setUsers(res.data);
    } catch (err) {
      console.error(err);
    }
  };

  return (
    <div className="flex bg-[#0b0f19] min-h-screen text-white">
      <Sidebar />

      <div className="flex-1 ml-64">
        <Navbar />

        <div className="p-8 space-y-8">

          {/* 🔥 STATS */}
          <div className="grid md:grid-cols-3 gap-6">
            <Card title="Total Users" value={stats.totalUsers} />
            <Card title="Total Revenue" value={`₹${stats.totalRevenue}`} />
            <Card title="Total Requests" value={stats.totalRequests} />
          </div>

          {/* 🔥 USERS TABLE */}
          <div className="bg-[#111827] p-6 rounded-2xl border border-gray-800">
            <h2 className="text-lg font-semibold mb-4">All Users</h2>

            <table className="w-full text-sm">
              <thead>
                <tr className="text-gray-400 border-b border-gray-700">
                  <th className="text-left py-2">Email</th>
                  <th>Role</th>
                  <th>Requests</th>
                  <th>Revenue</th>
                </tr>
              </thead>

              <tbody>
                {users.map((u) => (
                  <tr key={u.id} className="border-b border-gray-800">
                    <td className="py-2">{u.email}</td>
                    <td className="text-center">{u.role}</td>
                    <td className="text-center">{u.requests}</td>
                    <td className="text-center">₹{u.revenue}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>

        </div>
      </div>
    </div>
  );
}

function Card({ title, value }) {
  return (
    <div className="bg-[#111827] p-6 rounded-2xl border border-gray-800">
      <p className="text-gray-400">{title}</p>
      <p className="text-2xl font-bold mt-2">{value}</p>
    </div>
  );
}