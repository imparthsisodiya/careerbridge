document.addEventListener("DOMContentLoaded", async function () {
  const myJobsContainer = document.getElementById("myJobsContainer");
  const myJobsCountText = document.getElementById("myJobsCountText");
  const myJobsFilter = document.getElementById("myJobsFilter");
  const recruiterEmail = localStorage.getItem("userEmail");

  if (!recruiterEmail) { window.location.href = "login.html"; return; }

  let myJobs = [];

  function getBadgeClass(type) {
    if (type === "Remote") return "remote-badge";
    if (type === "Hybrid") return "hybrid-badge";
    return "";
  }

  function renderMyJobs(jobs) {
    myJobsContainer.innerHTML = "";
    if (jobs.length === 0) {
      myJobsContainer.innerHTML = `<div class="job-item"><h3>No jobs found</h3><p class="job-desc">You have not posted any jobs yet.</p></div>`;
      myJobsCountText.textContent = "0 jobs found";
      return;
    }
    myJobsCountText.textContent = `${jobs.length} jobs found`;
    jobs.forEach(job => {
      const card = document.createElement("div");
      card.className = "job-item";
      const skillsHtml = job.skills ? job.skills.split(",").map(s => `<span>${s.trim()}</span>`).join("") : "";
      card.innerHTML = `
        <div class="job-item-top">
          <div><h3>${job.title || "No title"}</h3><p class="company-name">${job.company || "No company"}</p></div>
          <span class="job-badge ${getBadgeClass(job.type)}">${job.type || "N/A"}</span>
        </div>
        <div class="job-meta">
          <span>${job.location || "Not added"}</span>
          <span>${job.salaryRange || "Not added"}</span>
        </div>
        <p class="job-desc">${job.description || "No description available."}</p>
        <div class="job-skills">${skillsHtml || "<span>No skills added</span>"}</div>
        <div class="job-actions">
          <a href="applicants.html?jobId=${job.id}" class="btn btn-small">View Applicants</a>
          <button class="btn btn-outline delete-job-btn" data-id="${job.id}">Delete</button>
        </div>
      `;
      myJobsContainer.appendChild(card);
    });
    attachDeleteEvents();
  }

  async function loadMyJobs() {
    try {
      // FIXED: correct endpoint - get all jobs then filter by recruiterEmail
      const response = await fetch("https://careerbridge-drls.onrender.com/api/jobs");
      const result = await response.json();
      const allJobs = Array.isArray(result) ? result : (result.data || []);
      myJobs = allJobs.filter(job => job.recruiterEmail === recruiterEmail);
      filterMyJobs();
    } catch (error) {
      console.error("My jobs fetch error:", error);
      myJobsContainer.innerHTML = `<div class="job-item"><h3>Error loading jobs</h3></div>`;
    }
  }

  async function deleteJob(jobId) {
    if (!confirm("Are you sure you want to delete this job?")) return;
    try {
      // FIXED: DELETE returns plain String not JSON
      await fetch(`https://careerbridge-drls.onrender.com/api/admin/jobs/${jobId}`, { method: "DELETE" });
      await loadMyJobs();
    } catch (error) {
      alert("Failed to delete job.");
    }
  }

  function attachDeleteEvents() {
    document.querySelectorAll(".delete-job-btn").forEach(button => {
      button.addEventListener("click", function () { deleteJob(this.getAttribute("data-id")); });
    });
  }

  function filterMyJobs() {
    const selectedType = myJobsFilter ? myJobsFilter.value : "";
    renderMyJobs(!selectedType ? myJobs : myJobs.filter(job => job.type === selectedType));
  }

  if (myJobsFilter) myJobsFilter.addEventListener("change", filterMyJobs);
  await loadMyJobs();
});
