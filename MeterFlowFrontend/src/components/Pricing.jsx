
import React from "react";
import api from "../services/api";

export default function Pricing() {

  const handleBuy = async (plan) => {
    try {
      const res = await api.post("/payment/create-order", { amount });

      const order = res.data;

      const options = {
        key: "rzp_test_xxxxx",
        amount: order.amount,
        currency: "INR",
        name: "Your SaaS",
        description: "Subscription Plan",
        order_id: order.id,

        handler: async function (response) {
          await api.post("/payment/verify", {
            razorpay_order_id: response.razorpay_order_id,
            razorpay_payment_id: response.razorpay_payment_id,
            razorpay_signature: response.razorpay_signature,
          });

          alert("✅ Subscription Activated");
        },
      };

      const rzp = new window.Razorpay(options);
      rzp.open();

    } catch (err) {
      alert("Payment failed");
    }
  };

  return (
    <div className="min-h-screen bg-gray-100 flex items-center justify-center">
      <div className="grid md:grid-cols-3 gap-6 p-6">

        {/* BASIC */}
        <Plan
        //   title="Basic"
        //   price="₹199"
        //   features={["100 Requests", "Basic Support"]}
          //onClick={() => handleBuy(19900)}
          onClick={() => handleBuy("BASIC")}
          onClick={() => handleBuy("PRO")}
          onClick={() => handleBuy("ENTERPRISE")}
        />

        {/* PRO */}
        {/* 
        <Plan
  title="Basic"
  price="₹199"
  features={["100 Requests", "Basic Support"]}
  onClick={() => handleBuy("BASIC")}
/>

<Plan
  title="Pro 🚀"
  price="₹499"
  features={["1000 Requests", "Priority Support"]}
  highlight
  onClick={() => handleBuy("PRO")}
/>

<Plan
  title="Enterprise"
  price="₹999"
  features={["Unlimited", "Dedicated Support"]}
  onClick={() => handleBuy("ENTERPRISE")}
/> */}
<Plan
  title="Basic"
  price="₹199"
  features={["100 Requests", "Basic Support"]}
  onClick={() => handleBuy("BASIC")}
/>

<Plan
  title="Pro 🚀"
  price="₹499"
  features={["1000 Requests", "Priority Support"]}
  highlight
  onClick={() => handleBuy("PRO")}
/>

<Plan
  title="Enterprise"
  price="₹999"
  features={["Unlimited", "Dedicated Support"]}
  onClick={() => handleBuy("ENTERPRISE")}
/>

      </div>
    </div>
  );
}

function Plan({ title, price, features, onClick, highlight }) {
  return (
    <div className={`p-6 rounded-2xl shadow-lg bg-white ${highlight ? "border-2 border-indigo-500" : ""}`}>
      <h2 className="text-xl font-bold">{title}</h2>
      <p className="text-3xl font-bold my-4">{price}</p>

      <ul className="mb-4 space-y-2">
        {features.map((f, i) => (
          <li key={i}>✔ {f}</li>
        ))}
      </ul>

      <button
        onClick={onClick}
        className="w-full bg-indigo-600 text-white py-2 rounded-lg hover:bg-indigo-700"
      >
        Buy Now
      </button>
    </div>
  );
}