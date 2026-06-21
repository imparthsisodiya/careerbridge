function getLoggedInUser() {
  return {
    isLoggedIn: localStorage.getItem("isLoggedIn"),
    email: localStorage.getItem("userEmail"),
    name: localStorage.getItem("userName"),
    role: localStorage.getItem("userRole"),
    token: localStorage.getItem("token")
  };
}

function isLoggedIn() {
  return localStorage.getItem("isLoggedIn") === "true";
}

function redirectIfNotLoggedIn() {
  if (!isLoggedIn()) {
    window.location.href = "login.html";
  }
}

function logoutUser() {
  localStorage.clear();
  window.location.href = "login.html";
}

function getAuthHeaders() {
  const token = localStorage.getItem("token");
  return {
    "Content-Type": "application/json",
    "Authorization": token ? `Bearer ${token}` : ""
  };
}

const API_BASE = "https://careerbridge-drls.onrender.com";
