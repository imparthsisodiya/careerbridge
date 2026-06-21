document.addEventListener("DOMContentLoaded", async function () {
  const applicantsContainer = document.getElementById("applicantsContainer");
  const applicantsCountText = document.getElementById("applicantsCountText");
  const statusFilter = document.getElementById("statusFilter");
  const recruiterEmail = localStorage.getItem("userEmail");

  if (!recruiterEmail) { window.location.href = "login.html"; return; }

  let applicants = [];

  function getStatusClass(status) {
    if (status === "PENDING") return "pending";
    if (status === "SHORTLISTED") return "shortlisted";
    if (status === "REJECTED") return "rejected";
    if (status === "ACCEPTED") return "shortlisted";
    return "";
  }

  function renderApplicants(data) {
    if (!applicantsContainer) return;
    applicantsContainer.innerHTML = "";
    if (data.length === 0) {
      applicantsContainer.innerHTML = `<div class="applicant-preview-item"><div><h3>No applicants found</h3></div></div>`;
      if (applicantsCountText) applicantsCountText.textContent = "0 applicants";
      return;
    }
    if (applicantsCountText) applicantsCountText.textContent = `${data.length} applicants`;
    data.forEach(app => {
      const card = document.createElement("div");
      card.className = "applicant-preview-item applicant-card-expanded";
      card.innerHTML = `
        <div class="applicant-main-info">
          <div>
            <h3>${app.applicantName || "Unknown"}</h3>
            <p>${app.applicantEmail || ""}</p>
            <p>Applied for: ${app.title || "Unknown Job"}</p>
            <p>Location: ${app.location || ""}</p>
          </div>
        </div>
        <div class="applicant-preview-actions applicant-actions-vertical">
          <span class="status-badge ${getStatusClass(app.status)}">${app.status}</span>
          <button class="btn btn-primary btn-mini shortlist-btn" data-id="${app.id}">Shortlist</button>
          <button class="btn btn-outline btn-mini reject-btn" data-id="${app.id}">Reject</button>
        </div>
      `;
      applicantsContainer.appendChild(card);
    });
    attachActionEvents();
  }

  function attachActionEvents() {
    document.querySelectorAll(".shortlist-btn").forEach(btn => {
      btn.addEventListener("click", async function () {
        await updateStatus(this.getAttribute("data-id"), "SHORTLISTED");
      });
    });
    document.querySelectorAll(".reject-btn").forEach(btn => {
      btn.addEventListener("click", async function () {
        await updateStatus(this.getAttribute("data-id"), "REJECTED");
      });
    });
  }

  async function updateStatus(id, status) {
    try {
      await fetch(`https://careerbridge-drls.onrender.com/api/applications/${id}/status?status=${status}`, { method: "PUT" });
      await loadApplicants();
    } catch (error) {
      alert("Failed to update status.");
    }
  }

  async function loadApplicants() {
    try {
      const response = await fetch(`https://careerbridge-drls.onrender.com/api/applications/recruiter?email=${encodeURIComponent(recruiterEmail)}`);
      const result = await response.json();
      applicants = Array.isArray(result) ? result : (result.data || []);
      applyFilters();
    } catch (error) {
      console.error("Applicants fetch error:", error);
      if (applicantsContainer) applicantsContainer.innerHTML = `<div class="applicant-preview-item"><h3>Error loading applicants</h3></div>`;
    }
  }

  function applyFilters() {
    const selectedStatus = statusFilter ? statusFilter.value : "";
    renderApplicants(!selectedStatus ? applicants : applicants.filter(a => a.status === selectedStatus));
  }

  if (statusFilter) statusFilter.addEventListener("change", applyFilters);
  await loadApplicants();
});
