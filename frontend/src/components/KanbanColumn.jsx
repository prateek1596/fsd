// src/components/KanbanColumn.jsx
import React from "react";

const KanbanColumn = ({ title, task, onStatusChange }) => {
  return (
    <div className="kanban-column">
      <h3>{title}</h3>
      {task.length === 0 ? (
        <p className="empty">No task</p>
      ) : (
        task.map((task) => (
          <div key={task.id} className="task-card">
            <h4>{task.title}</h4>
            <p>{task.description}</p>
            <p><b>Status:</b> {task.status}</p>
            <select
              value={task.status}
              onChange={(e) => onStatusChange(task.id, e.target.value)}
            >
              <option value="To Do">To Do</option>
              <option value="In Progress">In Progress</option>
              <option value="Done">Done</option>
            </select>
          </div>
        ))
      )}
    </div>
  );
};

export default KanbanColumn;
