import axios from "axios";

const PaymentButton = () => {

  const handlePayment = async () => {
    try {
      // 🔥 STEP 1: Create Order from backend
      const { data } = await axios.post(
        "http://localhost:8081/api/payment/create-order",
        { amount: 49900 },
        {
          headers: {
            Authorization: "Bearer YOUR_JWT_TOKEN"
          }
        }
      );

      const order = data;

      
      const options = {
        key: "rzp_test_xxxxx", 
        amount: order.amount,
        currency: order.currency,
        name: "MeterFlow",
        description: "Subscription Payment",
        order_id: order.id,

        handler: async function (response) {

          
          await axios.post(
            "http://localhost:8081/api/payment/verify",
            {
              razorpay_order_id: response.razorpay_order_id,
              razorpay_payment_id: response.razorpay_payment_id,
              razorpay_signature: response.razorpay_signature
            },
            {
              headers: {
                Authorization: "Bearer YOUR_JWT_TOKEN"
              }
            }
          );

          alert("✅ Payment Successful & Subscription Activated");
        },

        prefill: {
          name: "User Name",
          email: "user@email.com"
        },

        theme: {
          color: "#3399cc"
        }
      };

      // 🔥 STEP 4: OPEN CHECKOUT
      const rzp = new window.Razorpay(options);
      rzp.open();

    } catch (err) {
      console.error(err);
      alert("❌ Payment Failed");
    }
  };

  return (
    <button onClick={handlePayment}>
      Pay ₹499
    </button>
  );
};

export default PaymentButton;