
// import React, { useEffect, useState } from "react";
// import api from "../services/api";
// import Navbar from "../components/Navbar";
// import Sidebar from "../components/Sidebar";

// export default function ApiKeys() {
//   const [keys, setKeys] = useState([]);

//   const fetchKeys = async () => {
//     try {
//       const res = await api.get("/key/all");
//       setKeys(res.data);
//     } catch (err) {
//       console.error(err);
//     }
//   };

//   const generateKey = async () => {
//     try {
//       await api.post("/key/generate");
//       fetchKeys();
//     } catch (err) {
//       console.error(err);
//     }
//   };

//   const revokeKey = async (id) => {
//     await api.post(`/key/revoke/${id}`);
//     fetchKeys();
//   };

//   const rotateKey = async (id) => {
//     await api.post(`/key/rotate/${id}`);
//     fetchKeys();
//   };

//   const copyKey = (key) => {
//     navigator.clipboard.writeText(key);
//     alert("Copied API Key ✅");
//   };

//   const testApi = async (key) => {
//     try {
//       const res = await api.get("/data", {
//         headers: {
//           "x-api-key": key,
//         },
//       });
//       alert(res.data);
//     } catch (err) {
//       alert("API call failed ❌");
//     }
//   };

//   useEffect(() => {
//     fetchKeys();
//   }, []);

//   return (
//     <div className="flex bg-[#19160b] min-h-screen text-white">
//       <Sidebar />

//       <div className="flex-1 ml-64">
//         <Navbar />

//         <div className="p-8">
//           <h2 className="text-2xl mb-4">API Keys</h2>

//           <button
//             onClick={generateKey}
//             className="bg-indigo-600 px-4 py-2 rounded mb-6"
//           >
//             Generate Key
//           </button>

//           {keys.map((k) => (
//             <div key={k.id} className="bg-[#111827] p-5 rounded mb-5">

//               {/* KEY INFO */}
//               <p className="text-sm text-gray-400 break-all">
//                 {k.keyValue}
//               </p>

//               <p className="mt-1">
//                 Status:{" "}
//                 <span className={k.active ? "text-green-400" : "text-red-400"}>
//                   {k.active ? "Active" : "Revoked"}
//                 </span>
//               </p>

//               {/* 🔥 API USAGE SECTION */}
//               <div className="mt-4 bg-black p-4 rounded-lg text-sm">

//                 <p className="text-gray-400">Base URL</p>
//                 <p className="font-mono">http://localhost:8080</p>

//                 <p className="text-gray-400 mt-2">Endpoint</p>
//                 <p className="font-mono">GET /api/data</p>

//                 <p className="text-gray-400 mt-2">Example</p>

//                 <pre className="bg-gray-900 p-3 rounded mt-2 overflow-x-auto text-xs">
// {`fetch("http://localhost:8080/api/data", {
//   method: "GET",
//   headers: {
//     "x-api-key": "${k.keyValue}"
//   }
// })
// .then(res => res.text())
// .then(data => console.log(data));`}
//                 </pre>
//               </div>

//               {/* ACTION BUTTONS */}
//               <div className="flex flex-wrap gap-3 mt-4">

//                 <button
//                   onClick={() => copyKey(k.keyValue)}
//                   className="bg-blue-600 px-3 py-1 rounded"
//                 >
//                   Copy Key
//                 </button>

//                 <button
//                   onClick={() => testApi(k.keyValue)}
//                   className="bg-green-600 px-3 py-1 rounded"
//                 >
//                   Try API 🚀
//                 </button>

//                 <button
//                   onClick={() => rotateKey(k.id)}
//                   className="bg-yellow-500 px-3 py-1 rounded"
//                 >
//                   Rotate
//                 </button>

//                 <button
//                   onClick={() => revokeKey(k.id)}
//                   className="bg-red-500 px-3 py-1 rounded"
//                 >
//                   Revoke
//                 </button>

//               </div>
//             </div>
//           ))}
//         </div>
//       </div>
//     </div>
//   );
// }

//testing
// import React, { useEffect, useState } from "react";
// import api from "../services/api";
// import Navbar from "../components/Navbar";
// import Sidebar from "../components/Sidebar";

// export default function ApiKeys() {
//   const [keys, setKeys] = useState([]);
//   const [projects, setProjects] = useState([]);

//   const [form, setForm] = useState({
//     name: "",
//     method: "GET",
//     response: ""
//   });

//   // 🔑 FETCH API KEYS
//   const fetchKeys = async () => {
//     try {
//       const res = await api.get("/key/all");
//       setKeys(res.data);
//     } catch (err) {
//       console.error(err);
//     }
//   };

//   // 🚀 FETCH PROJECTS
//   const fetchProjects = async () => {
//     try {
//       const res = await api.get("/project/all");
//       setProjects(res.data);
//     } catch (err) {
//       console.error(err);
//     }
//   };

//   // 🚀 CREATE API
//   const createApi = async () => {
//     try {
//       await api.post("/project/create", form);

//       alert("API Created ✅");

//       // setForm({
//       //   name: "",
//       //   method: "GET",
//       //   response: ""
//       // });
//       const [form, setForm] = useState({
//   name: "",
//   method: "GET",
//   response: "{\"msg\":\"Hello {{name}}\"}"
// });
// if (!form.name || !form.response) {
//   alert("Please fill all fields");
//   return;
// }

//       fetchProjects();

//     } catch (err) {
//       console.error(err);
//       alert("Failed to create API ❌");
//     }
//   };

//   // 🔐 ACTIONS
//   const generateKey = async () => {
//     await api.post("/key/generate");
//     fetchKeys();
//   };

//   const revokeKey = async (id) => {
//     await api.post(`/key/revoke/${id}`);
//     fetchKeys();
//   };

//   const rotateKey = async (id) => {
//     await api.post(`/key/rotate/${id}`);
//     fetchKeys();
//   };

//   const copyKey = (key) => {
//     navigator.clipboard.writeText(key);
//     alert("Copied API Key ✅");
//   };

// const testApi = async (key, project) => {
//   try {
//     let url = `http://localhost:8080${project.endpoint}`;

//     if (project.method === "GET") {
//       url += "?name=saurav";
//     }

//     const res = await fetch(url, {
//       method: project.method,
//       headers: {
//         "x-api-key": key,
//         "Content-Type": "application/json"
//       }
//     });

//     const text = await res.text(); // ALWAYS SAFE FIRST

//     console.log("RAW RESPONSE:", text);

//     if (!res.ok) {
//       alert(`error: ${text || "Request failed"}`);
//       return;
//     }

//     // try parse JSON safely
//     let data;
//     try {
//       data = JSON.parse(text);
//       alert(`data fetched: ${data.msg || text}`);
//     } catch (e) {
//       alert(`data fetched: ${text}`);
//     }

//   } catch (err) {
//     alert(`API failed: ${err.message}`);
//   }
// };

// // const testApi = async (key, project) => {
// //   try {
// //     const endpoint = project.endpoint;

// //     const res = await api({
// //       method: project.method,
// //       url: endpoint,
// //       params: project.method === "GET" ? { name: "Saurav" } : {},
// //       headers: {
// //         "x-api-key": key
// //       }
// //     });

// //     console.log("Response:", res.data);
// //     alert(JSON.stringify(res.data));

// //   } catch (err) {
// //     console.error(err);
// //     alert(err.response?.data || "API failed");
// //   }
// // };
//   useEffect(() => {
//     fetchKeys();
//     fetchProjects();
//   }, []);

//   return (
//     <div className="flex bg-[#19160b] min-h-screen text-white">
//       <Sidebar />

//       <div className="flex-1 ml-64">
//         <Navbar />

//         <div className="p-8">

//           <h2 className="text-2xl mb-4">API Keys</h2>

//           {/* 🔑 GENERATE KEY */}
//           <button
//             onClick={generateKey}
//             className="bg-indigo-600 px-4 py-2 rounded mb-6"
//           >
//             Generate Key
//           </button>

//           {/* 🚀 CREATE API FORM */}
//           <div className="bg-[#111827] p-5 rounded mb-6">
//             <h3 className="text-lg mb-3">Create API 🚀</h3>

//             <input
//               placeholder="API Name"
//               value={form.name}
//               onChange={(e) =>
//                 setForm({ ...form, name: e.target.value })
//               }
//               className="w-full p-2 mb-2 bg-black border border-gray-700 rounded"
//             />

//             <select
//               value={form.method}
//               onChange={(e) =>
//                 setForm({ ...form, method: e.target.value })
//               }
//               className="w-full p-2 mb-2 bg-black border border-gray-700 rounded"
//             >
//               <option value="GET">GET</option>
//               <option value="POST">POST</option>
//             </select>

//             <textarea
//               placeholder='Response JSON e.g {"msg":"Hello {{name}}"}'
//               value={form.response}
//               onChange={(e) =>
//                 setForm({ ...form, response: e.target.value })
//               }
//               className="w-full p-2 mb-2 bg-black border border-gray-700 rounded"
//             />

//             <button
//               onClick={createApi}
//               className="bg-green-600 px-4 py-2 rounded"
//             >
//               Create API
//             </button>
//           </div>

//           {/* 🔑 API KEYS LIST */}
//           {keys.map((k) => (
//             <div key={k.id} className="bg-[#111827] p-5 rounded mb-5">

//               <p className="text-sm text-gray-400 break-all">
//                 {k.keyValue}
//               </p>

//               <p className="mt-1">
//                 Status:{" "}
//                 <span className={k.active ? "text-green-400" : "text-red-400"}>
//                   {k.active ? "Active" : "Revoked"}
//                 </span>
//               </p>

//               {/* 🚀 PROJECT LIST */}
//               <div className="mt-4 bg-black p-4 rounded-lg text-sm">

//                 <p className="text-gray-400">Base URL</p>
//                 <p className="font-mono">http://localhost:8080</p>

//                 <p className="text-gray-400 mt-2">Your APIs</p>

//                 {projects.length === 0 ? (
//                   <p className="text-red-400">No APIs created</p>
//                 ) : (
//                   projects.map((p) => (
//                     <div key={p.id} className="mt-3 border-b border-gray-700 pb-2">

//                       <p className="font-semibold">{p.name}</p>

//                       <p className="font-mono text-xs text-gray-400">
//                         {p.method} {p.endpoint}
//                       </p>

//                       <button
//                         onClick={() => testApi(k.keyValue, p)}
//                         className="bg-green-600 px-3 py-1 rounded mt-2"
//                       >
//                         Try API 🚀
//                       </button>
//                     </div>
//                   ))
//                 )}
//               </div>

//               {/* ACTION BUTTONS */}
//               <div className="flex gap-3 mt-4">

//                 <button
//                   onClick={() => copyKey(k.keyValue)}
//                   className="bg-blue-600 px-3 py-1 rounded"
//                 >
//                   Copy Key
//                 </button>

//                 <button
//                   onClick={() => rotateKey(k.id)}
//                   className="bg-yellow-500 px-3 py-1 rounded"
//                 >
//                   Rotate
//                 </button>

//                 <button
//                   onClick={() => revokeKey(k.id)}
//                   className="bg-red-500 px-3 py-1 rounded"
//                 >
//                   Revoke
//                 </button>

//               </div>
//             </div>
//           ))}
//         </div>
//       </div>
//     </div>
//   );
// }





import React, { useEffect, useState } from "react";
import api from "../services/api";
import Navbar from "../components/Navbar";
import Sidebar from "../components/Sidebar";

export default function ApiKeys() {

  const [keys, setKeys] = useState([]);
  const [projects, setProjects] = useState([]);

  // ✅ DEFAULT SAFE FORM
  const [form, setForm] = useState({
    name: "",
    method: "GET",
    response: "{\"msg\":\"Hello {{name}}\"}"
  });

  // 🔑 FETCH KEYS
  const fetchKeys = async () => {
    try {
      const res = await api.get("/key/all");
      setKeys(res.data);
    } catch (err) {
      console.error(err);
    }
  };

  // 🚀 FETCH PROJECTS
  const fetchProjects = async () => {
    try {
      const res = await api.get("/project/all");
      setProjects(res.data);
    } catch (err) {
      console.error(err);
    }
  };

  // 🚀 CREATE API (FIXED)
  const createApi = async () => {
    try {

      // ✅ VALIDATION
      if (!form.name || !form.response) {
        alert("Please fill all fields");
        return;
      }

      await api.post("/project/create", form);

      alert("API Created ✅");

      // reset form safely
      setForm({
        name: "",
        method: "GET",
        response: "{\"msg\":\"Hello {{name}}\"}"
      });

      fetchProjects();

    } catch (err) {
      console.error(err);
      alert("Failed to create API ❌");
    }
  };

  // 🔐 ACTIONS
  const generateKey = async () => {
    await api.post("/key/generate");
    fetchKeys();
  };

  const revokeKey = async (id) => {
    await api.post(`/key/revoke/${id}`);
    fetchKeys();
  };

  const rotateKey = async (id) => {
    await api.post(`/key/rotate/${id}`);
    fetchKeys();
  };

  const copyKey = (key) => {
    navigator.clipboard.writeText(key);
    alert("Copied API Key ✅");
  };

  // 🚀 TEST API (FIXED & SAFE)
//   const testApi = async (key, project) => {
//   try {

//     console.log("DEBUG KEY:", key); // 🔥 MUST SEE VALUE

//     if (!key || key === "undefined") {
//       alert("API Key missing!");
//       return;
//     }

//     const baseUrl = import.meta.env.VITE_API_URL || "http://localhost:8080";
//     let url = `${baseUrl}${project.endpoint}`;

//     if (project.method === "GET") {
//       url += "?name=saurav";
//     }

//     const res = await fetch(url, {
//       method: project.method,
//       headers: {
//         "x-api-key": String(key), // 🔥 FORCE STRING
//         "Content-Type": "application/json"
//       }
//     });

//     const text = await res.text();

//     console.log("STATUS:", res.status);
//     console.log("RESPONSE:", text);

//     if (!res.ok) {
//       alert(`API Error ${res.status}: ${text}`);
//       return;
//     }

//     let data;
//     try {
//       data = JSON.parse(text);
//       alert(`data fetched: ${data.msg}`);
//     } catch {
//       alert(`data fetched: ${text}`);
//     }

//   } catch (err) {
//     console.error(err);
//     alert("Request failed: " + err.message);
//   }
// };

const testApi = async (key, project) => {
  try {
    const baseURL =
      import.meta.env.VITE_API_URL || "http://localhost:8080";

    let url = `${baseURL}${project.endpoint}`;

    if (project.method === "GET") {
      url += "?name=saurav";
    }

    console.log("CALLING:", url);
    console.log("KEY:", key);

    const res = await fetch(url, {
      method: project.method,
      headers: {
        "x-api-key": key,
      },
    });

    const text = await res.text();

    console.log("STATUS:", res.status);
    console.log("RAW:", text);

    if (!res.ok) {
     alert(`API failed: ${text || "Request failed"}`);
          //   alert(`API request added: successfully sent request to API. Check server Logs for response.`);

      return;
    }

    try {
      const json = JSON.parse(text);
      alert(`data fetched: ${JSON.stringify(json)}`);
    } catch {
      alert(`data fetched: ${text}`);
    }
  } catch (err) {
     alert(`API failed: ${err.message}`);
        //alert(`API request added: successfully sent request to API. Check server Logs for response.`);

  }
};

  useEffect(() => {
    fetchKeys();
    fetchProjects();
  }, []);

  return (
    <div className="flex bg-[#19160b] min-h-screen text-white">

      <Sidebar />

      <div className="flex-1 ml-64">
        <Navbar />

        <div className="p-8">

          <h2 className="text-2xl mb-4">API Keys</h2>

          {/* 🔑 GENERATE KEY */}
          <button
            onClick={generateKey}
            className="bg-indigo-600 px-4 py-2 rounded mb-6"
          >
            Generate Key
          </button>

          {/* 🚀 CREATE API FORM */}
          <div className="bg-[#111827] p-5 rounded mb-6">

            <h3 className="text-lg mb-3">Create API 🚀</h3>

            <input
              placeholder="API Name"
              value={form.name}
              onChange={(e) =>
                setForm({ ...form, name: e.target.value })
              }
              className="w-full p-2 mb-2 bg-black border border-gray-700 rounded"
            />

            <select
              value={form.method}
              onChange={(e) =>
                setForm({ ...form, method: e.target.value })
              }
              className="w-full p-2 mb-2 bg-black border border-gray-700 rounded"
            >
              <option value="GET">GET</option>
              <option value="POST">POST</option>
            </select>

            <textarea
              placeholder='Response JSON e.g {"msg":"Hello {{name}}"}'
              value={form.response}
              onChange={(e) =>
                setForm({ ...form, response: e.target.value })
              }
              className="w-full p-2 mb-2 bg-black border border-gray-700 rounded"
            />

            <button
              onClick={createApi}
              className="bg-green-600 px-4 py-2 rounded"
            >
              Create API
            </button>

          </div>

          {/* 🔑 API LIST */}
          {keys.map((k) => (
            <div key={k.id} className="bg-[#111827] p-5 rounded mb-5">

              <p className="text-sm text-gray-400 break-all">
                {k.keyValue}
              </p>

              <p className="mt-1">
                Status:{" "}
                <span className={k.active ? "text-green-400" : "text-red-400"}>
                  {k.active ? "Active" : "Revoked"}
                </span>
              </p>

              {/* PROJECTS */}
              <div className="mt-4 bg-black p-4 rounded-lg text-sm">

                <p className="text-gray-400">Base URL</p>
                <p className="font-mono">http://localhost:8080</p>

                <p className="text-gray-400 mt-2">Your APIs</p>

                {projects.length === 0 ? (
                  <p className="text-red-400">No APIs created</p>
                ) : (
                  projects.map((p) => (
                    <div key={p.id} className="mt-3 border-b border-gray-700 pb-2">

                      <p className="font-semibold">{p.name}</p>

                      <p className="font-mono text-xs text-gray-400">
                        {p.method} {p.endpoint}
                      </p>

                      <button
                        onClick={() => testApi(k.keyValue, p)}
                        className="bg-green-600 px-3 py-1 rounded mt-2"
                      >
                        Try API 🚀
                      </button>

                    </div>
                  ))
                )}

              </div>

              {/* ACTIONS */}
              <div className="flex gap-3 mt-4">

                <button
                  onClick={() => copyKey(k.keyValue)}
                  className="bg-blue-600 px-3 py-1 rounded"
                >
                  Copy Key
                </button>

                <button
                  onClick={() => rotateKey(k.id)}
                  className="bg-yellow-500 px-3 py-1 rounded"
                >
                  Rotate
                </button>

                <button
                  onClick={() => revokeKey(k.id)}
                  className="bg-red-500 px-3 py-1 rounded"
                >
                  Revoke
                </button>

              </div>

            </div>
          ))}

        </div>
      </div>
    </div>
  );
}