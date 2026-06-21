document.addEventListener("DOMContentLoaded", async function () {
  const searchBtn = document.getElementById("searchBtn");
  const jobsContainer = document.getElementById("jobsContainer");
  const jobCountText = document.getElementById("jobCountText");
  const sortJobs = document.getElementById("sortJobs");
  const userEmail = localStorage.getItem("userEmail");

  let allJobs = [];

  function getBadgeClass(type) {
    if (type === "Remote") return "remote-badge";
    if (type === "Hybrid") return "hybrid-badge";
    return "";
  }

  function renderJobs(jobs) {
    jobsContainer.innerHTML = "";

    if (jobs.length === 0) {
      jobsContainer.innerHTML = `<div class="job-item"><h3>No jobs found</h3><p class="job-desc">Try changing keyword, location, or filter options.</p></div>`;
      jobCountText.textContent = "0 jobs found";
      return;
    }

    jobCountText.textContent = `${jobs.length} jobs found`;

    jobs.forEach(job => {
      const jobCard = document.createElement("div");
      jobCard.className = "job-item";
      jobCard.innerHTML = `
        <div class="job-item-top">
          <div>
            <h3>${job.title || "No title"}</h3>
            <p class="company-name">${job.company || "No company"}</p>
          </div>
          <span class="job-badge ${getBadgeClass(job.type)}">${job.type || "N/A"}</span>
        </div>
        <div class="job-meta">
          <span>${job.location || "Not added"}</span>
          <span>${job.experienceRequired || "Not added"}</span>
          <span>${job.salaryRange || "Not added"}</span>
        </div>
        <p class="job-desc">${job.description || "No description available."}</p>
        <div class="job-actions">
          <a href="job-details.html?id=${job.id}" class="btn btn-small">View Details</a>
          <button class="btn btn-outline save-job-btn" data-id="${job.id}">Save Job</button>
        </div>
      `;
      jobsContainer.appendChild(jobCard);
    });

    attachSaveEvents();
  }

  function attachSaveEvents() {
    document.querySelectorAll(".save-job-btn").forEach(button => {
      button.addEventListener("click", async function () {
        if (!userEmail) {
          alert("Please login first.");
          window.location.href = "login.html";
          return;
        }
        const jobId = this.getAttribute("data-id");
        try {
          await fetch(`https://careerbridge-drls.onrender.com/api/saved-jobs?email=${encodeURIComponent(userEmail)}&jobId=${jobId}`, { method: "POST" });
          alert("Job saved successfully!");
        } catch (error) {
          alert("Failed to save job.");
        }
      });
    });
  }

  async function loadJobs() {
    try {
      const response = await fetch("https://careerbridge-drls.onrender.com/api/jobs");
      const result = await response.json();
      // API returns plain list, not {data:[]}
      allJobs = Array.isArray(result) ? result : (result.data || []);
      filterJobs();
    } catch (error) {
      console.error("Jobs fetch error:", error);
      jobsContainer.innerHTML = `<div class="job-item"><h3>Error loading jobs</h3><p class="job-desc">Please try again later.</p></div>`;
    }
  }

  function filterJobs() {
    const keyword = (document.getElementById("searchKeyword")?.value || "").toLowerCase().trim();
    const location = (document.getElementById("searchLocation")?.value || "").toLowerCase().trim();
    const type = document.getElementById("jobType")?.value || "";
    const experience = document.getElementById("experience")?.value || "";

    let filteredJobs = allJobs.filter(job => {
      const matchesKeyword = !keyword ||
        (job.title || "").toLowerCase().includes(keyword) ||
        (job.company || "").toLowerCase().includes(keyword) ||
        (job.description || "").toLowerCase().includes(keyword) ||
        (job.skills || "").toLowerCase().includes(keyword);
      const matchesLocation = !location || (job.location || "").toLowerCase().includes(location);
      const matchesType = !type || job.type === type;
      const matchesExperience = !experience || (job.experienceRequired || "").includes(experience);
      return matchesKeyword && matchesLocation && matchesType && matchesExperience;
    });

    if (sortJobs?.value === "company") {
      filteredJobs.sort((a, b) => (a.company || "").localeCompare(b.company || ""));
    }

    renderJobs(filteredJobs);
  }

  if (searchBtn) searchBtn.addEventListener("click", filterJobs);
  if (sortJobs) sortJobs.addEventListener("change", filterJobs);

  await loadJobs();
});
