document.addEventListener("DOMContentLoaded", async function () {
  const savedJobsContainer = document.getElementById("savedJobsContainer");
  const savedJobsCountText = document.getElementById("savedJobsCountText");
  const savedJobTypeFilter = document.getElementById("savedJobTypeFilter");
  const userEmail = localStorage.getItem("userEmail");

  if (!userEmail) { window.location.href = "login.html"; return; }

  let savedJobs = [];

  function getBadgeClass(type) {
    if (type === "Remote") return "remote-badge";
    if (type === "Hybrid") return "hybrid-badge";
    return "";
  }

  function renderSavedJobs(jobs) {
    savedJobsContainer.innerHTML = "";
    if (jobs.length === 0) {
      savedJobsContainer.innerHTML = `<div class="job-item"><h3>No saved jobs</h3><p class="job-desc">You have not saved any jobs yet.</p></div>`;
      savedJobsCountText.textContent = "0 saved jobs";
      return;
    }
    savedJobsCountText.textContent = `${jobs.length} saved jobs`;
    jobs.forEach(job => {
      const card = document.createElement("div");
      card.className = "job-item";
      card.innerHTML = `
        <div class="job-item-top">
          <div><h3>${job.title || "No title"}</h3><p class="company-name">${job.company || ""}</p></div>
          <span class="job-badge ${getBadgeClass(job.type)}">${job.type || "N/A"}</span>
        </div>
        <div class="job-meta">
          <span>${job.location || "Not added"}</span>
          <span>${job.salaryRange || "Not added"}</span>
        </div>
        <p class="job-desc">${job.description || ""}</p>
        <div class="job-actions">
          <a href="job-details.html?id=${job.id}" class="btn btn-small">View Job</a>
          <button class="btn btn-outline remove-saved-btn" data-id="${job.id}">Remove</button>
        </div>
      `;
      savedJobsContainer.appendChild(card);
    });
    attachRemoveEvents();
  }

  async function loadSavedJobs() {
    try {
      const response = await fetch(`https://careerbridge-drls.onrender.com/api/saved-jobs?email=${encodeURIComponent(userEmail)}`);
      // FIXED: /api/saved-jobs returns plain List<Job>
      const result = await response.json();
      savedJobs = Array.isArray(result) ? result : (result.data || []);
      filterSavedJobs();
    } catch (error) {
      console.error("Saved jobs error:", error);
      savedJobsContainer.innerHTML = `<div class="job-item"><h3>Error loading saved jobs</h3></div>`;
    }
  }

  async function removeSavedJob(jobId) {
    try {
      await fetch(`https://careerbridge-drls.onrender.com/api/saved-jobs?email=${encodeURIComponent(userEmail)}&jobId=${jobId}`, { method: "DELETE" });
      await loadSavedJobs();
    } catch (error) {
      alert("Failed to remove saved job.");
    }
  }

  function attachRemoveEvents() {
    document.querySelectorAll(".remove-saved-btn").forEach(button => {
      button.addEventListener("click", function () { removeSavedJob(this.getAttribute("data-id")); });
    });
  }

  function filterSavedJobs() {
    const selectedType = savedJobTypeFilter?.value || "";
    renderSavedJobs(!selectedType ? savedJobs : savedJobs.filter(j => j.type === selectedType));
  }

  if (savedJobTypeFilter) savedJobTypeFilter.addEventListener("change", filterSavedJobs);
  await loadSavedJobs();
});
