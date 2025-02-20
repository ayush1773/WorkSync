import React, { useEffect, useState } from "react";

const ViewAttendance = () => {
  const [attendanceRecords, setAttendanceRecords] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    // Fetch all attendance records from backend
    const fetchAttendance = async () => {
      try {
        const response = await fetch(
          "http://localhost:8082/api/attendance/all"
        );

        if (!response.ok) {
          throw new Error("Failed to fetch attendance records");
        }

        const data = await response.json();
        setAttendanceRecords(data); // Set the fetched data
      } catch (err) {
        setError(err.message); // Handle errors
      } finally {
        setLoading(false); // Set loading to false once the fetch completes
      }
    };

    fetchAttendance();
  }, []); // Empty dependency array means this will run only once when the component mounts

  if (loading) {
    return <div>Loading...</div>; // Show loading text while fetching data
  }

  if (error) {
    return <div className="text-red-500">Error: {error}</div>; // Show error if fetching fails
  }

  return (
    <div className="container mx-auto p-6">
      <h2 className="text-2xl font-semibold mb-4">All Attendance Records</h2>
      <table className="table-auto w-full">
        <thead>
          <tr>
            <th className="px-4 py-2 border">Employee ID</th>
            <th className="px-4 py-2 border">Date</th>
            <th className="px-4 py-2 border">Status</th>
          </tr>
        </thead>
        <tbody>
          {attendanceRecords.length > 0 ? (
            attendanceRecords.map((record, index) => (
              <tr key={index}>
                <td className="px-4 py-2 border">{record.employeeId}</td>
                <td className="px-4 py-2 border">{record.date}</td>
                <td className="px-4 py-2 border">{record.status}</td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="3" className="px-4 py-2 border text-center">
                No attendance records found.
              </td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default ViewAttendance;
