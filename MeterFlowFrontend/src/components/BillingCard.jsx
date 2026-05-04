
export default function BillingCard({ billing }) {

  if (!billing) {
    return <p className="p-5">Loading billing...</p>;
  }

  const usagePercent = billing.limit
    ? (billing.totalRequests / billing.limit) * 100
    : 0;

  return (
    <div className="bg-white rounded-xl shadow p-6 space-y-4">

      <h2 className="text-lg font-bold">Billing Overview</h2>

      {/* TOP STATS */}
      <div className="grid grid-cols-3 gap-6">

        <Stat title="Total Requests" value={billing.totalRequests} />

        <Stat
          title="Total Revenue"
          value={`₹${billing.amount}`}
          color="text-green-600"
        />

        <Stat
          title="Cost / Request"
          value={`₹${billing.costPerRequest}`}
        />

      </div>

      {/* USAGE BAR */}
      <div className="mt-4">
        <div className="flex justify-between text-sm text-gray-500 mb-1">
          <span>Usage</span>
          <span>{billing.totalRequests} / {billing.limit}</span>
        </div>

        <div className="w-full bg-gray-200 rounded-full h-2">
          <div
            className="bg-blue-500 h-2 rounded-full"
            style={{ width: `${usagePercent}%` }}
          />
        </div>
      </div>

      {/* EXTRA INFO */}
      <div className="flex justify-between text-sm text-gray-500">
        <p>Remaining: {billing.remaining}</p>
        <p>Plan: {billing.plan}</p>
      </div>

    </div>
  );
}

function Stat({ title, value, color }) {
  return (
    <div>
      <p className="text-gray-500 text-sm">{title}</p>
      <p className={`text-xl font-bold ${color || ""}`}>{value}</p>
    </div>
  );
}