// navbar.js — Keeps the navbar in sync with login state on every page.
// Without this, public pages (index, about, jobs, etc.) always showed
// "Login / Register" even when the user was already logged in,
// which looked like an automatic logout when navigating between pages.

document.addEventListener("DOMContentLoaded", function () {
  const navButtons = document.querySelector(".nav-buttons");
  if (!navButtons) return;

  const isLoggedIn = localStorage.getItem("isLoggedIn") === "true";

  if (!isLoggedIn) return; // keep existing Login/Register buttons as-is

  const userName = localStorage.getItem("userName") || "Account";
  const userRole = localStorage.getItem("userRole") || "STUDENT";

  let dashboardLink = "index.html";
  if (userRole === "STUDENT") dashboardLink = "Student-dashboard.html";
  else if (userRole === "RECRUITER") dashboardLink = "Recruiter-dashboard.html";
  else if (userRole === "ADMIN") dashboardLink = "admin-dashboard.html";

  navButtons.innerHTML = `
    <a href="${dashboardLink}" class="btn btn-outline">Hi, ${userName.split(" ")[0]}</a>
    <a href="#" class="btn btn-primary" id="navLogoutBtn">Logout</a>
  `;

  document.getElementById("navLogoutBtn").addEventListener("click", function (e) {
    e.preventDefault();
    localStorage.clear();
    window.location.href = "login.html";
  });
});
