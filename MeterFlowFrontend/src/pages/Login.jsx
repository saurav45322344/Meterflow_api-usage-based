
// import React, { useState } from "react";
// import { useNavigate } from "react-router-dom";
// import api from "../services/api";

// export default function Login() {
//   const [email, setEmail] = useState("");
//   const [password, setPassword] = useState("");
//   const [loading, setLoading] = useState(false);
//   const navigate = useNavigate();

// const handleLogin = async () => {
//   if (!email || !password) {
//     alert("Please enter email and password");
//     return;
//   }

//   setLoading(true);

//   try {
//     const res = await api.post("/auth/login", {
//       email,
//       password,
//     });

//     // ✅ store JWT
//     localStorage.setItem("token", res.data.token);

//     // ✅ GET API KEY (NOT generate every time)
//     const keyRes = await api.get("/key");

//     console.log("API KEY RESPONSE:", keyRes.data);

//     // ✅ store apiKey
//     localStorage.setItem("apiKey", keyRes.data.apiKey);

//     navigate("/dashboard");

//   } catch (err) {
//     console.error(err);
//     alert("Login failed");
//   }

//   setLoading(false);
// };

//   return (
//     <div className="h-screen flex items-center justify-center bg-gray-100">
//       <div className="bg-white p-6 rounded shadow w-80">
        
//         <h2 className="text-xl font-bold mb-4 text-center">
//           Login
//         </h2>

//         <input
//           className="w-full border p-2 mb-3 rounded"
//           placeholder="Email"
//           value={email}
//           onChange={(e) => setEmail(e.target.value)}
//         />

//         <input
//           type="password"
//           className="w-full border p-2 mb-3 rounded"
//           placeholder="Password"
//           value={password}
//           onChange={(e) => setPassword(e.target.value)}
//         />

//         <button
//           onClick={handleLogin}
//           disabled={loading}
//           className="w-full bg-indigo-500 text-white p-2 rounded hover:bg-indigo-600"
//         >
//           {loading ? "Logging in..." : "Login"}
//         </button>

//         {/* ✅ SIGNUP LINK */}
//         <p className="mt-3 text-sm text-center">
//           Don't have an account?{" "}
//           <span
//             onClick={() => navigate("/signup")}
//             className="text-blue-500 cursor-pointer hover:underline"
//           >
//             Signup
//           </span>
//         </p>

//       </div>
//     </div>
//   );
// }



import React from "react";
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import api from "../services/api";

export default function Login() {
  const [formData, setFormData] = useState({
    email: "",
    password: "",
  });
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();

    if (!formData.email || !formData.password) {
      setError("Please enter email and password");
      return;
    }

    setLoading(true);
    setError("");

    try {
      const res = await api.post("/auth/login", formData);

      // ✅ store JWT
      localStorage.setItem("token", res.data.token);

      // ✅ GET API KEY
      const keyRes = await api.get("/key");
      localStorage.setItem("apiKey", keyRes.data.apiKey);

      navigate("/dashboard");
    } catch (err) {
      console.error(err);
      setError(err.response?.data?.message || "Login failed");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gradient-to-br from-slate-900 via-slate-800 to-slate-900">
      
      {/* Background Blur */}
      <div className="absolute inset-0 overflow-hidden">
        <div className="absolute -top-40 -right-40 w-80 h-80 bg-green-500/10 rounded-full blur-3xl"></div>
        <div className="absolute -bottom-40 -left-40 w-80 h-80 bg-blue-500/10 rounded-full blur-3xl"></div>
      </div>

      {/* Card */}
      <div className="relative w-full max-w-md p-8 bg-slate-800/50 backdrop-blur-xl rounded-2xl border border-white/10 shadow-2xl">
        
        {/* Header */}
        <div className="text-center mb-8">
          <div className="inline-flex items-center justify-center w-16 h-16 bg-gradient-to-br from-green-400 to-green-600 rounded-2xl mb-4 shadow-lg shadow-green-500/25">
            ⚡
          </div>
          <h1 className="text-3xl font-bold text-white">MeterFlow</h1>
          <p className="text-slate-400 mt-2">API Billing Platform</p>
        </div>

        {/* Error */}
        {error && (
          <div className="mb-6 p-4 bg-red-500/10 border border-red-500/20 text-red-400 rounded-xl text-sm">
            {error}
          </div>
        )}

        {/* Form */}
        <form onSubmit={handleLogin} className="space-y-5">
          
          {/* Email */}
          <input
            type="email"
            placeholder="you@example.com"
            value={formData.email}
            onChange={(e) =>
              setFormData({ ...formData, email: e.target.value })
            }
            className="w-full px-4 py-3 bg-slate-900/50 border border-white/10 rounded-xl text-white placeholder-slate-500 focus:outline-none focus:ring-2 focus:ring-green-500"
          />

          {/* Password */}
          <input
            type="password"
            placeholder="••••••••"
            value={formData.password}
            onChange={(e) =>
              setFormData({ ...formData, password: e.target.value })
            }
            className="w-full px-4 py-3 bg-slate-900/50 border border-white/10 rounded-xl text-white placeholder-slate-500 focus:outline-none focus:ring-2 focus:ring-green-500"
          />

          {/* Button */}
          <button
            type="submit"
            disabled={loading}
            className="w-full py-3 bg-gradient-to-r from-green-500 to-green-600 text-white font-semibold rounded-xl hover:from-green-600 hover:to-green-700 transition-all"
          >
            {loading ? "Signing in..." : "Sign In"}
          </button>
        </form>

        {/* Signup */}
        <p className="mt-6 text-center text-sm text-slate-400">
          Don't have an account?{" "}
          <Link to="/signup" className="text-green-400 hover:underline">
            Sign up
          </Link>
        </p>
      </div>
    </div>
  );
}