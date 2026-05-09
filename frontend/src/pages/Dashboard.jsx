// src/pages/Dashboard.jsx
import React, { useEffect, useState } from "react";
import api from "../api";
import KanbanColumn from "../components/KanbanColumn";

const Dashboard = () => {
  const [task, setTasks] = useState([]);
  const [loading, setLoading] = useState(true);

  const fetchTasks = async () => {
    try {
      const res = await api.get("/tasks");
      setTasks(res.data);
      setLoading(false);
    } catch (err) {
      console.error("Error fetching tasks:", err);
      setLoading(false);
    }
  };

  const handleStatusChange = async (taskId, newStatus) => {
    try {
      const updatedTask = { ...task.find(t => t.id === taskId), status: newStatus };
      await api.put(`/tasks/${taskId}`, updatedTask);
      setTasks(task.map(t => (t.id === taskId ? updatedTask : t)));
    } catch (err) {
      console.error("Error updating task:", err);
    }
  };

  useEffect(() => {
    fetchTasks();
  }, []);

  if (loading) return <p>Loading...</p>;

  const todo = task.filter(t => t.status === "To Do");
  const inProgress = task.filter(t => t.status === "In Progress");
  const done = task.filter(t => t.status === "Done");

  return (
    <div className="dashboard">
      <h1>Project & Task Dashboard</h1>
      <div className="kanban-board">
        <KanbanColumn title="To Do" task={todo} onStatusChange={handleStatusChange} />
        <KanbanColumn title="In Progress" task={inProgress} onStatusChange={handleStatusChange} />
        <KanbanColumn title="Done" task={done} onStatusChange={handleStatusChange} />
      </div>
    </div>
  );
};

export default Dashboard;
