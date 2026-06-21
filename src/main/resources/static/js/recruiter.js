document.addEventListener("DOMContentLoaded", function () {
  const userName = localStorage.getItem("userName") || "Recruiter";
  const userEmail = localStorage.getItem("userEmail") || "recruiter@company.com";

  const recruiterName = document.getElementById("recruiterName");
  const sidebarRecruiterName = document.getElementById("sidebarRecruiterName");
  const sidebarRecruiterEmail = document.getElementById("sidebarRecruiterEmail");
  const recruiterInitial = document.getElementById("recruiterInitial");
  const recruiterProgressFill = document.getElementById("recruiterProgressFill");
  const recruiterProgressText = document.getElementById("recruiterProgressText");

  if (recruiterName) recruiterName.textContent = userName;
  if (sidebarRecruiterName) sidebarRecruiterName.textContent = userName;
  if (sidebarRecruiterEmail) sidebarRecruiterEmail.textContent = userEmail;
  if (recruiterInitial) recruiterInitial.textContent = userName.charAt(0).toUpperCase();

  const progress = 68;
  if (recruiterProgressFill) recruiterProgressFill.style.width = progress + "%";
  if (recruiterProgressText) recruiterProgressText.textContent = progress + "% monthly target reached";
});
