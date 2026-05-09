import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8081/api",
});

// ✅ Fetch all projects
export const getProjects = async () => {
  try {
    const response = await api.get("/projects");
    return response.data;
  } catch (error) {
    console.error("Error fetching projects:", error);
    return [];
  }
};

// ✅ Create a new project (pass teamId)
export const createProject = async (projectData, teamId = 1) => {
  try {
    const response = await api.post(`/projects/create?teamId=${teamId}`, projectData);
    return response.data;
  } catch (error) {
    console.error("Error creating project:", error);
    throw error;
  }
};

// ✅ Delete a project by ID
export const deleteProject = async (id) => {
  try {
    await api.delete(`/projects/${id}`);
  } catch (error) {
    console.error("Error deleting project:", error);
    throw error;
  }
};

export default api;
