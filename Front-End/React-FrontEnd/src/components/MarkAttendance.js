import React, { useState } from "react";
import { useParams, useNavigate } from "react-router-dom";

const MarkAttendance = () => {
  const { employeeId: paramEmployeeId } = useParams();
  const [employeeId, setEmployeeId] = useState(paramEmployeeId || "");
  const [attendanceDate, setAttendanceDate] = useState("");
  const [attendanceStatus, setAttendanceStatus] = useState("Present");
  const [error, setError] = useState(null);
  const [success, setSuccess] = useState(null);
  const navigate = useNavigate();

  const handleMarkAttendance = async () => {
    if (!employeeId || !attendanceDate) {
      setError("Please provide all the details.");
      setSuccess(null);
      return;
    }

    const attendanceRecord = {
      employeeId,
      date: attendanceDate,
      status: attendanceStatus,
    };

    try {
      const response = await fetch(
        "http://localhost:8082/api/attendance/mark",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(attendanceRecord),
        }
      );

      if (!response.ok) {
        throw new Error("Failed to mark attendance");
      }

      setSuccess("Attendance marked successfully!");
      setError(null);
      setTimeout(() => navigate("/viewAttendance"), 1500);
    } catch (err) {
      setError(err.message);
      setSuccess(null);
    }
  };

  return (
    <div className="container mx-auto p-6">
      <h2 className="text-2xl font-semibold mb-4">
        Mark Attendance {employeeId ? `for Employee ID: ${employeeId}` : ""}
      </h2>

      {!paramEmployeeId && (
        <div className="mb-4">
          <label className="block text-sm font-medium text-gray-700">
            Employee ID:
          </label>
          <input
            type="text"
            value={employeeId}
            onChange={(e) => setEmployeeId(e.target.value)}
            className="mt-1 block w-full p-2 border border-gray-300 rounded-md"
            placeholder="Enter Employee ID"
          />
        </div>
      )}

      <div className="mb-4">
        <label className="block text-sm font-medium text-gray-700">Date:</label>
        <input
          type="date"
          value={attendanceDate}
          onChange={(e) => setAttendanceDate(e.target.value)}
          className="mt-1 block w-full p-2 border border-gray-300 rounded-md"
        />
      </div>

      <div className="mb-4">
        <label className="block text-sm font-medium text-gray-700">
          Status:
        </label>
        <select
          value={attendanceStatus}
          onChange={(e) => setAttendanceStatus(e.target.value)}
          className="mt-1 block w-full p-2 border border-gray-300 rounded-md"
        >
          <option value="Present">Present</option>
          <option value="Absent">Absent</option>
          <option value="Leave">Leave</option>
        </select>
      </div>

      <button
        type="button"
        onClick={handleMarkAttendance}
        className="w-full py-2 px-4 bg-blue-600 text-white rounded-md hover:bg-blue-700"
      >
        Mark Attendance
      </button>

      {success && <p className="mt-4 text-green-500">{success}</p>}
      {error && <p className="mt-4 text-red-500">{error}</p>}
    </div>
  );
};

export default MarkAttendance;
