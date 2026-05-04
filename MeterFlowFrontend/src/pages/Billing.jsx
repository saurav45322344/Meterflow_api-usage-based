import { useEffect, useState } from "react";
import BillingCard from "../components/BillingCard";
import PaymentTable from "../components/PaymentTable";
import UsageChart from "../components/UsageChart";
import SubscriptionCard from "../components/SubscriptionCard";
import { getBilling } from "../services/billingService";
import { getUsage } from "../services/usageService";

export default function Billing() {
  const [billing, setBilling] = useState(null);
  const [usage, setUsage] = useState([]);

  useEffect(() => {
    load();
  }, []);

  const load = async () => {
    const apiKey = localStorage.getItem("apiKey");

    const billRes = await getBilling(apiKey);
    const usageRes = await getUsage(apiKey);

    setBilling(billRes.data);
    setUsage(usageRes.data);
  };

  return (
    <div className="p-6 grid grid-cols-12 gap-6">

      {/* LEFT MAIN AREA */}
      <div className="col-span-8 space-y-6">

        <BillingCard billing={billing} />

        <UsageChart data={usage} />

        <PaymentTable />
      </div>

      {/* RIGHT SIDE */}
      <div className="col-span-4 space-y-6">

        <SubscriptionCard billing={billing} />

      </div>

    </div>
  );
}
