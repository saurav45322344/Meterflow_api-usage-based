export default function PaymentTable() {
  const payments = [
    { id: 1, amount: "$25", status: "Paid", date: "2026-04-10" },
    { id: 2, amount: "$50", status: "Pending", date: "2026-04-11" }
  ];

  return (
    <div className="bg-white p-5 rounded-xl shadow">
      <h2 className="font-bold mb-4">Payment History</h2>

      <table className="w-full text-left">
        <thead>
          <tr className="text-gray-500">
            <th>Amount</th>
            <th>Status</th>
            <th>Date</th>
          </tr>
        </thead>

        <tbody>
          {payments.map(p => (
            <tr key={p.id} className="border-t">
              <td>{p.amount}</td>
              <td className={p.status === "Paid" ? "text-green-600" : "text-orange-500"}>
                {p.status}
              </td>
              <td>{p.date}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}