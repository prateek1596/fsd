import React, { useEffect, useState } from "react";
import { getProjects, createProject, deleteProject } from "../api";
import "./ProjectPage.css";

export default function ProjectPage() {
  const [projects, setProjects] = useState([]);
  const [newProject, setNewProject] = useState({ name: "", description: "" });

  // ✅ You can change this default teamId later (or make it dynamic)
  const [teamId, setTeamId] = useState(1);

  // ✅ Fetch all projects when page loads
  useEffect(() => {
    fetchProjects();
  }, []);

  const fetchProjects = async () => {
    const data = await getProjects();
    setProjects(data);
  };

  const handleCreateProject = async (e) => {
    e.preventDefault();
    if (!newProject.name) {
      alert("Project name is required!");
      return;
    }

    try {
      await createProject(newProject, teamId); // ✅ pass teamId here
      setNewProject({ name: "", description: "" });
      fetchProjects();
    } catch (error) {
      console.error("Error creating project:", error);
      alert("Failed to create project. Check console for details.");
    }
  };

  const handleDelete = async (id) => {
    if (window.confirm("Are you sure you want to delete this project?")) {
      try {
        await deleteProject(id);
        fetchProjects();
      } catch (error) {
        console.error("Error deleting project:", error);
        alert("Failed to delete project.");
      }
    }
  };

  return (
    <div className="project-page">
      <h1>📁 Project Management</h1>

      <form className="create-form" onSubmit={handleCreateProject}>
        <input
          type="text"
          placeholder="Project name"
          value={newProject.name}
          onChange={(e) =>
            setNewProject({ ...newProject, name: e.target.value })
          }
        />
        <input
          type="text"
          placeholder="Description"
          value={newProject.description}
          onChange={(e) =>
            setNewProject({ ...newProject, description: e.target.value })
          }
        />

        {/* ✅ Optional: allow user to select team */}
        <input
          type="number"
          placeholder="Team ID (default 1)"
          value={teamId}
          onChange={(e) => setTeamId(e.target.value)}
          min="1"
        />

        <button type="submit">Create Project</button>
      </form>

      <div className="project-list">
        {projects.length === 0 ? (
          <p>No projects yet.</p>
        ) : (
          projects.map((p) => (
            <div key={p.id} className="project-card">
              <h2>{p.name}</h2>
              <p>{p.description}</p>
              {p.team && <p><strong>Team:</strong> {p.team.name}</p>}
              <button onClick={() => handleDelete(p.id)}>Delete</button>
            </div>
          ))
        )}
      </div>
    </div>
  );
}
