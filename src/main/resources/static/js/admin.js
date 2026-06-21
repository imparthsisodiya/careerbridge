document.addEventListener("DOMContentLoaded", function () {
  const userName = localStorage.getItem("userName") || "Admin";
  const userEmail = localStorage.getItem("userEmail") || "admin@careerbridge.com";

  const adminName = document.getElementById("adminName");
  const sidebarAdminName = document.getElementById("sidebarAdminName");
  const sidebarAdminEmail = document.getElementById("sidebarAdminEmail");
  const adminInitial = document.getElementById("adminInitial");
  const adminProgressFill = document.getElementById("adminProgressFill");
  const adminProgressText = document.getElementById("adminProgressText");

  if (adminName) {
    adminName.textContent = userName;
  }

  if (sidebarAdminName) {
    sidebarAdminName.textContent = userName;
  }

  if (sidebarAdminEmail) {
    sidebarAdminEmail.textContent = userEmail;
  }

  if (adminInitial) {
    adminInitial.textContent = userName.charAt(0).toUpperCase();
  }

  const progress = 84;

  if (adminProgressFill) {
    adminProgressFill.style.width = progress + "%";
  }

  if (adminProgressText) {
    adminProgressText.textContent = progress + "% platform performance";
  }
});