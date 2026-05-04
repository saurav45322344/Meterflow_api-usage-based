
// import React, { useEffect, useState } from "react";
// import api from "../services/api";
// import Navbar from "../components/Navbar";
// import Sidebar from "../components/Sidebar";
// import { Activity, IndianRupee, Key } from "lucide-react";

// import {
//   LineChart,
//   Line,
//   XAxis,
//   YAxis,
//   Tooltip,
//   CartesianGrid,
//   ResponsiveContainer,
// } from "recharts";

// export default function Dashboard() {
//   const [stats, setStats] = useState({
//     totalRequests: 0,
//     revenue: 0,
//     activeApis: 0,
//   });

//   const [chartData, setChartData] = useState([]);

//   const [loading, setLoading] = useState(false);

//   useEffect(() => {
//     fetchDashboard();
//     fetchUsageChart();
//   }, []);

//   // ✅ FETCH DASHBOARD STATS (FIXED)
//   const fetchDashboard = async () => {
//     try {
//       const res = await api.get("/dashboard");
//       setStats(res.data); // ✅ FIXED (you had wrong setData)
//     } catch (err) {
//       console.error("Dashboard error:", err);
//     }
//   };

//   // ✅ FETCH USAGE CHART
//   const fetchUsageChart = async () => {
//     try {
//       const apiKey = localStorage.getItem("apiKey");
//       const res = await api.get(`/usage/chart?apiKey=${apiKey}`);
//       //setChartData(res.data);
//       let data = res.data;
//       if (!Array.isArray(data)) {
//       data = data?.data || [];
//     }
// if (typeof data === "string") {
//       data = JSON.parse(data);
//     }
//      setChartData(Array.isArray(data) ? data : []);

//     console.log("FINAL CHART DATA:", data);
//     } catch (err) {
//       console.error("Chart error:", err);
//       setChartData([]);
//     }
//   };

//   // 💰 RAZORPAY PAYMENT HANDLER
//   const handleBuy = async (plan) => {
//   try {
//     setLoading(true);

//     // 1️⃣ CREATE ORDER (Backend)
//     const res = await api.post(
//       "/payment/create-order",
//       { plan },
//       {
//         headers: {
//           Authorization: `Bearer ${localStorage.getItem("token")}`,
//           "Content-Type": "application/json",
//         },
//       }
//     );

//     const order = res.data;

//     // 2️⃣ RAZORPAY OPTIONS
//     const options = {
//       key: "rzp_test_SgakxlMTeHrwsf", // your Razorpay key
//       amount: order.amount,
//       currency: order.currency,
//       name: "Your SaaS Platform",
//       description: `${plan} Subscription`,
//       order_id: order.id,

//       handler: async function (response) {
//         try {
//           // 3️⃣ VERIFY PAYMENT (Backend)
//           await api.post(
//             "/payment/verify",
//             {
//               razorpay_order_id: response.razorpay_order_id,
//               razorpay_payment_id: response.razorpay_payment_id,
//               razorpay_signature: response.razorpay_signature,
//             },
//             {
//               headers: {
//                 Authorization: `Bearer ${localStorage.getItem("token")}`,
//                 "Content-Type": "application/json",
//               },
//             }
//           );

//           alert("✅ Payment Successful & Verified");

//           fetchDashboard(); // refresh UI
//         } catch (err) {
//           console.error(err);
//           alert("⚠ Payment verification failed");
//         }
//       },

//       modal: {
//         ondismiss: function () {
//           alert("❌ Payment Cancelled");
//         },
//       },

//       theme: {
//         color: "#4f46e5",
//       },
//     };

//     // 4️⃣ OPEN RAZORPAY
//     const rzp = new window.Razorpay(options);
//     rzp.open();

//   } catch (err) {
//     console.error("CREATE ORDER ERROR:", err);
//     alert("Payment initiation failed");
//   } finally {
//     setLoading(false);
//   }
// };

//   return (
//     <div className="flex bg-gray-100 min-h-screen">

//       {/* 🔥 SIDEBAR */}
//       <Sidebar />

//       <div className="flex-1 flex flex-col">

//         {/* 🔝 NAVBAR */}
//         <Navbar />

//         <div className="p-6 space-y-6">

//           {/* 🔢 STATS */}
//           <div className="grid grid-cols-1 md:grid-cols-3 gap-6">

//             <Card
//               title="Total Requests"
//               value={stats.totalRequests}
//               icon={<Activity />}
//               color="bg-blue-500"
//             />

//             <Card
//               title="Revenue"
//               value={`₹${stats.revenue}`}
//               icon={<IndianRupee />}
//               color="bg-green-500"
//             />

//             <Card
//               title="Active APIs"
//               value={stats.activeApis}
//               icon={<Key />}
//               color="bg-purple-500"
//             />

//           </div>

//           {/* 💰 PRICING BUTTONS (NEW) */}
//           <div className="bg-white p-6 rounded-2xl shadow">
//             <h2 className="text-lg font-semibold mb-4">
//               Upgrade Plan 🚀
//             </h2>

//             <div className="grid md:grid-cols-3 gap-4">

//               <Plan
//   title="Basic"
//   price="₹199"
//   onClick={() => handleBuy("BASIC")}
// />

// <Plan
//   title="Pro"
//   price="₹499"
//   highlight
//   onClick={() => handleBuy("PRO")}
// />

// <Plan
//   title="Enterprise"
//   price="₹999"
//   onClick={() => handleBuy("ENTERPRISE")}
// />

//             </div>
//           </div>

//           {/* 📊 CHART */}
//           <div className="bg-white p-6 rounded-2xl shadow">
//             <h2 className="mb-4 text-lg font-semibold">
//               Usage Analytics
//             </h2>

//             {Array.isArray(chartData) && chartData.length > 0 ? (
//               <ResponsiveContainer width="100%" height={300}>
//                 <LineChart data={chartData}>
//                   <CartesianGrid strokeDasharray="3 3" />
//                   <XAxis dataKey="date" />
//                   <YAxis />
//                   <Tooltip />
//                   <Line type="monotone" dataKey="requests" />
//                 </LineChart>
//               </ResponsiveContainer>
//             ) : (
//               <p className="text-gray-500 text-center py-10">
//                 No data available
//               </p>
//             )}
//           </div>

//         </div>
//       </div>
//     </div>
//   );
// }

// // 🔥 CARD COMPONENT
// function Card({ title, value, icon, color }) {
//   return (
//     <div className={`p-5 rounded-2xl text-white shadow ${color}`}>
//       <div className="flex justify-between items-center">
//         <h2 className="text-sm">{title}</h2>
//         {icon}
//       </div>
//       <p className="text-2xl font-bold mt-2">{value}</p>
//     </div>
//   );
// }

// // 💰 PLAN CARD
// function Plan({ title, price, onClick, highlight }) {
//   return (
//     <div className={`p-4 rounded-xl border text-center ${highlight ? "border-indigo-500" : ""}`}>
//       <h3 className="font-bold">{title}</h3>
//       <p className="text-xl my-2">{price}</p>

//       <button
//         onClick={onClick}
//         className="bg-indigo-600 text-white px-4 py-2 rounded mt-2 hover:bg-indigo-700"
//       >
//         Buy Now
//       </button>
//     </div>
//   );
// }




import React, { useEffect, useState } from "react";
import api from "../services/api";
import Navbar from "../components/Navbar";
import Sidebar from "../components/Sidebar";
import { Activity, IndianRupee, Key } from "lucide-react";

import { getSubscription } from "../services/subscriptionService";
import Card from "../components/Card";
import Plan from "../components/Plan";

import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  Tooltip,
  CartesianGrid,
  ResponsiveContainer,
} from "recharts";

export default function Dashboard() {
  const [stats, setStats] = useState({
    totalRequests: 0,
    revenue: 0,
    activeApis: 0,
  });

  const [chartData, setChartData] = useState([]);
  const [plan, setPlan] = useState(null);
  const [payments, setPayments] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    fetchDashboard();
    fetchUsageChart();
    fetchPlan();
    fetchPayments();
    loadRazorpay();
  }, []);

  // 🔥 Load Razorpay script
  const loadRazorpay = () => {
    const script = document.createElement("script");
    script.src = "https://checkout.razorpay.com/v1/checkout.js";
    script.async = true;
    document.body.appendChild(script);
  };

  // 📊 Dashboard stats
  const fetchDashboard = async () => {
    try {
      const res = await api.get("/dashboard");
      setStats(res.data);
    } catch (err) {
      console.error(err);
    }
  };

  // 📈 Usage chart
  const fetchUsageChart = async () => {
    try {
      const apiKey = localStorage.getItem("apiKey");
      const res = await api.get(`/usage/chart?apiKey=${apiKey}`);

      let data = res.data;
      if (typeof data === "string") data = JSON.parse(data);

      setChartData(Array.isArray(data) ? data : []);
    } catch (err) {
      console.error(err);
      setChartData([]);
    }
  };

  // 🔥 Current plan
  const fetchPlan = async () => {
    try {
      const res = await api.get("/subscription/current");
      setPlan(res.data);
    } catch (err) {
      console.error(err);
      setPlan(null);
    }
  };

  // 💰 Payment history
  const fetchPayments = async () => {
    try {
      const res = await api.get("/payment/history");
      setPayments(res.data);
    } catch (err) {
      console.error(err);
    }
  };

  // 💰 BUY PLAN (FIXED)
  const handleBuy = async (planName) => {
    try {
      setLoading(true);

      const res = await api.post(
        "/payment/create-order",
        { plan: planName },
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        }
      );

      const order = res.data;

      const options = {
        key: "rzp_test_SgakxlMTeHrwsf", // 🔥 your key
        amount: order.amount,
        currency: order.currency,
        name: "MeterFlow SaaS",
        description: `${planName} Plan`,
        order_id: order.id,

        handler: async function (response) {
          try {
            await api.post(
              "/payment/verify",
              {
                razorpay_order_id: response.razorpay_order_id,
                razorpay_payment_id: response.razorpay_payment_id,
                razorpay_signature: response.razorpay_signature,
                plan: planName
              },
              {
                headers: {
                  Authorization: `Bearer ${localStorage.getItem("token")}`,
                },
              }
            );

            alert("✅ Payment success");

            // 🔥 Wait for webhook → refresh UI
            setTimeout(() => {
              fetchPlan();
              fetchPayments();
              fetchDashboard();
            }, 3000);

          } catch (err) {
            console.error(err);
            alert("⚠ Verification pending (Webhook will update)");
          }
        },

        modal: {
          ondismiss: () => alert("❌ Payment cancelled"),
        },

        theme: {
          color: "#4f46e5",
        },
      };

      const rzp = new window.Razorpay(options);
      rzp.open();

    } catch (err) {
      console.error(err);
      alert("Payment failed");
    } finally {
      setLoading(false);
    }
  };

return (
  <div className="flex bg-[#19160b] min-h-screen text-white">

    <Sidebar />

    <div className="flex-1 flex flex-col ml-64">
      <Navbar />

      <div className="p-8 space-y-8">

        {/* 🔢 STATS */}
        <div className="grid md:grid-cols-3 gap-6">
          <Card
            title="Requests"
           // value={`${stats.totalRequests} / ${plan?.requestLimit || 0}`}
           value={`${stats.totalRequests} / ${plan?.limit || 0}`}
            icon={<Activity />}
          />
          <Card
            title="Revenue"
            value={`₹${stats.revenue}`}
            icon={<IndianRupee />}
          />
          <Card
            title="APIs"
            value={stats.activeApis}
            icon={<Key />}
          />
        </div>

        {/* 🔥 CURRENT PLAN */}
        <div className="bg-[#111827] p-6 rounded-2xl border border-gray-800 shadow-lg">
          <h2 className="text-lg font-semibold mb-3">Current Plan</h2>

          {plan ? (
            <>
             <p className="text-xl font-bold text-indigo-400">
  {plan?.plan || "FREE"}
</p>
<p className="text-gray-400">
  Limit: {plan?.limit || 0} requests
</p>
            </>
          ) : (
            <p className="text-red-400">No active plan</p>
          )}
        </div>

        {/* 💳 PRICING */}
        <div className="bg-[#111827] p-6 rounded-2xl border border-gray-800 shadow-lg">
          <h2 className="text-lg font-semibold mb-4">
            Upgrade Plan 🚀
          </h2>

          <div className="grid md:grid-cols-3 gap-6">
            <Plan title="Basic" price="₹199" onClick={() => handleBuy("BASIC")} />
            <Plan title="Pro" price="₹499" highlight onClick={() => handleBuy("PRO")} />
            <Plan title="Enterprise" price="₹999" onClick={() => handleBuy("ENTERPRISE")} />
          </div>
        </div>

        {/* 📊 CHART */}
        <div className="bg-[#111827] p-6 rounded-2xl border border-gray-800 shadow-lg">
          <h2 className="mb-4 text-lg font-semibold">
            Usage Analytics
          </h2>

          {chartData.length > 0 ? (
            <ResponsiveContainer width="100%" height={300}>
              <LineChart data={chartData}>
                <CartesianGrid stroke="#1f2937" />
                <XAxis dataKey="date" stroke="#9ca3af" />
                <YAxis stroke="#9ca3af" />
                <Tooltip
                  contentStyle={{
                    backgroundColor: "#111827",
                    border: "none",
                    color: "#fff",
                  }}
                />
                <Line
                  type="monotone"
                  dataKey="requests"
                  stroke="#6366F1"
                  strokeWidth={3}
                  dot={false}
                />
              </LineChart>
            </ResponsiveContainer>
          ) : (
            <p className="text-gray-400 text-center py-10">
              No data available
            </p>
          )}
        </div>

        {/* 💰 PAYMENT HISTORY */}
        <div className="bg-[#111827] p-6 rounded-2xl border border-gray-800 shadow-lg">
          <h2 className="text-lg font-semibold mb-4">
            Payment History
          </h2>

          {payments.length === 0 ? (
            <p className="text-gray-400">No payments yet</p>
          ) : (
            payments.map((p) => (
              <div
                key={p.id}
                className="flex justify-between border-b border-gray-800 py-3 hover:bg-[#1f2937] transition px-2 rounded"
              >
                <span>{p.planName}</span>
                <span>₹{p.amount}</span>
                <span className="text-green-400">{p.status}</span>
              </div>
            ))
          )}
        </div>

      </div>
    </div>
  </div>
);
}














