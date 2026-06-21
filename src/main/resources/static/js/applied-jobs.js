document.addEventListener("DOMContentLoaded", async function () {
  const appliedJobsContainer = document.getElementById("appliedJobsContainer");
  const appliedJobsCountText = document.getElementById("appliedJobsCountText");
  const filterStatus = document.getElementById("filterStatus");
  const userEmail = localStorage.getItem("userEmail");

  if (!userEmail) { window.location.href = "login.html"; return; }

  let appliedJobs = [];

  function getStatusClass(status) {
    if (status === "PENDING") return "pending";
    if (status === "SHORTLISTED") return "shortlisted";
    if (status === "REJECTED") return "rejected";
    if (status === "ACCEPTED") return "shortlisted";
    return "";
  }

  function renderAppliedJobs(jobs) {
    appliedJobsContainer.innerHTML = "";
    if (jobs.length === 0) {
      appliedJobsContainer.innerHTML = `<div class="application-item"><div><h3>No applications found</h3><p>You have not applied to any jobs yet.</p></div></div>`;
      if (appliedJobsCountText) appliedJobsCountText.textContent = "0 applications";
      return;
    }
    if (appliedJobsCountText) appliedJobsCountText.textContent = `${jobs.length} applications`;
    jobs.forEach(app => {
      const card = document.createElement("div");
      card.className = "application-item";
      card.innerHTML = `
        <div>
          <h3>${app.title || "Job Title"}</h3>
          <p>${app.company || ""} • ${app.location || ""}</p>
        </div>
        <div style="display:flex;gap:10px;align-items:center;flex-wrap:wrap;">
          <span class="status-badge ${getStatusClass(app.status)}">${app.status}</span>
          <a href="job-details.html" class="btn btn-small">View Job</a>
        </div>
      `;
      appliedJobsContainer.appendChild(card);
    });
  }

  async function loadAppliedJobs() {
    try {
      const response = await fetch(`https://careerbridge-drls.onrender.com/api/applications/user?email=${encodeURIComponent(userEmail)}`);
      const result = await response.json();
      appliedJobs = Array.isArray(result) ? result : (result.data || []);
      filterAppliedJobs();
    } catch (error) {
      console.error("Applied jobs error:", error);
      appliedJobsContainer.innerHTML = `<div class="application-item"><h3>Error loading applications</h3></div>`;
    }
  }

  function filterAppliedJobs() {
    const selectedStatus = filterStatus?.value || "";
    renderAppliedJobs(!selectedStatus ? appliedJobs : appliedJobs.filter(j => j.status === selectedStatus));
  }

  if (filterStatus) filterStatus.addEventListener("change", filterAppliedJobs);
  await loadAppliedJobs();
});
