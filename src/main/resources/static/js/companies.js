document.addEventListener("DOMContentLoaded", async function () {
  const companiesContainer = document.getElementById("companiesContainer");
  const companyCountText = document.getElementById("companyCountText");
  const companySearchBtn = document.getElementById("companySearchBtn");

  let companies = [];

  function renderCompanies(data) {
    companiesContainer.innerHTML = "";
    if (data.length === 0) {
      companiesContainer.innerHTML = `<div class="job-item"><h3>No companies found</h3></div>`;
      if (companyCountText) companyCountText.textContent = "0 companies";
      return;
    }
    if (companyCountText) companyCountText.textContent = `${data.length} companies`;
    data.forEach(company => {
      const card = document.createElement("div");
      card.className = "job-item";
      const skillsHtml = company.skills ? company.skills.split(",").map(s => `<span>${s.trim()}</span>`).join("") : "";
      card.innerHTML = `
        <div class="job-item-top">
          <div><h3>${company.name || "Company"}</h3><p class="company-name">${company.industry || ""}</p></div>
          <span class="job-badge">${company.jobsCount || "0 Jobs"}</span>
        </div>
        <div class="job-meta">
          <span>${company.location || ""}</span>
          <span>${company.type || ""}</span>
          <span>${company.rating || ""}</span>
        </div>
        <p class="job-desc">${company.description || ""}</p>
        <div class="job-skills">${skillsHtml}</div>
        <div class="job-actions"><a href="jobs.html" class="btn btn-small">View Jobs</a></div>
      `;
      companiesContainer.appendChild(card);
    });
  }

  function filterCompanies() {
    const keyword = (document.getElementById("companyKeyword")?.value || "").toLowerCase().trim();
    const location = (document.getElementById("companyLocation")?.value || "").toLowerCase().trim();
    const industry = document.getElementById("companyIndustry")?.value || "";
    const filtered = companies.filter(c =>
      (!keyword || (c.name || "").toLowerCase().includes(keyword) || (c.industry || "").toLowerCase().includes(keyword)) &&
      (!location || (c.location || "").toLowerCase().includes(location)) &&
      (!industry || c.industry === industry)
    );
    renderCompanies(filtered);
  }

  try {
    const response = await fetch("https://careerbridge-drls.onrender.com/api/companies");
    const result = await response.json();
    companies = Array.isArray(result) ? result : (result.data || []);
    renderCompanies(companies);
  } catch (error) {
    console.error("Companies fetch error:", error);
    companiesContainer.innerHTML = `<div class="job-item"><h3>Error loading companies</h3></div>`;
  }

  if (companySearchBtn) companySearchBtn.addEventListener("click", filterCompanies);
});
