document.addEventListener("DOMContentLoaded", async function () {
  const applyNowBtn = document.getElementById("applyNowBtn");
  const saveJobBtn = document.getElementById("saveJobBtn");
  const jobActionMessage = document.getElementById("jobActionMessage");

  const userEmail = localStorage.getItem("userEmail");
  const userName = localStorage.getItem("userName");
  const params = new URLSearchParams(window.location.search);
  const jobId = params.get("id");

  let currentJob = null;

  if (!jobId) {
    if (jobActionMessage) jobActionMessage.textContent = "Invalid job ID. Go back to Jobs and click View Details.";
    if (applyNowBtn) applyNowBtn.disabled = true;
    if (saveJobBtn) saveJobBtn.disabled = true;
  } else {
    try {
      const response = await fetch(`https://careerbridge-drls.onrender.com/api/jobs/${jobId}`);
      if (!response.ok) throw new Error("Job not found");
      currentJob = await response.json();

      document.getElementById("jobTitle").textContent = currentJob.title || "Job Title";
      document.getElementById("jobCompany").textContent = currentJob.company || "Company";
      document.getElementById("jobType").textContent = currentJob.type || "Type";
      document.getElementById("jobLocation").textContent = currentJob.location || "Location";
      document.getElementById("jobExperience").textContent = currentJob.experienceRequired || "Not added";
      document.getElementById("jobSalary").textContent = currentJob.salaryRange || "Not added";
      document.getElementById("jobDescription").textContent = currentJob.description || "No description.";

      if (document.getElementById("overviewRole")) document.getElementById("overviewRole").textContent = currentJob.title || "";
      if (document.getElementById("overviewEmployment")) document.getElementById("overviewEmployment").textContent = currentJob.type || "";
      if (document.getElementById("overviewExperience")) document.getElementById("overviewExperience").textContent = currentJob.experienceRequired || "";
      if (document.getElementById("overviewLocation")) document.getElementById("overviewLocation").textContent = currentJob.location || "";
    } catch (error) {
      if (jobActionMessage) jobActionMessage.textContent = "Failed to load job details.";
      if (applyNowBtn) applyNowBtn.disabled = true;
    }
  }

  // ===== Save Job =====
  if (saveJobBtn) {
    saveJobBtn.addEventListener("click", async function () {
      if (!jobId) return;
      if (!userEmail) { window.location.href = "login.html"; return; }
      try {
        await fetch(`https://careerbridge-drls.onrender.com/api/saved-jobs?email=${encodeURIComponent(userEmail)}&jobId=${jobId}`, { method: "POST" });
        if (jobActionMessage) jobActionMessage.textContent = "Job saved successfully!";
      } catch { if (jobActionMessage) jobActionMessage.textContent = "Failed to save job."; }
    });
  }

  // ===== Apply Now — Open Modal =====
  if (applyNowBtn) {
    applyNowBtn.addEventListener("click", function () {
      if (!jobId) return;
      if (!userEmail) { window.location.href = "login.html"; return; }
      openApplyModal();
    });
  }

  // ===== Build & Inject Modal =====
  function openApplyModal() {
    if (document.getElementById("applyModal")) {
      document.getElementById("applyModal").style.display = "flex";
      return;
    }

    const modal = document.createElement("div");
    modal.id = "applyModal";
    modal.style.cssText = `
      position:fixed; inset:0; background:rgba(0,0,0,0.55); z-index:9999;
      display:flex; align-items:center; justify-content:center; padding:16px;
    `;

    modal.innerHTML = `
      <div style="background:#fff; border-radius:20px; width:100%; max-width:520px;
                  box-shadow:0 20px 60px rgba(0,0,0,0.2); overflow:hidden; animation: slideUp 0.25s ease;">

        <!-- Header -->
        <div style="background:linear-gradient(135deg,#2563eb,#7c3aed); padding:24px 28px; display:flex; justify-content:space-between; align-items:center;">
          <div>
            <h2 style="color:#fff; margin:0; font-size:1.2rem;">Apply for this Job</h2>
            <p style="color:#c7d2fe; margin:4px 0 0; font-size:0.88rem;" id="modalJobTitle">Loading...</p>
          </div>
          <button id="closeModalBtn" style="background:rgba(255,255,255,0.2); border:none; color:#fff;
            width:34px; height:34px; border-radius:50%; font-size:1.2rem; cursor:pointer;">✕</button>
        </div>

        <!-- Body -->
        <div style="padding:28px;">

          <!-- Applicant Info (pre-filled) -->
          <div style="display:grid; grid-template-columns:1fr 1fr; gap:14px; margin-bottom:20px;">
            <div>
              <label style="font-size:0.83rem; font-weight:600; color:#374151; display:block; margin-bottom:5px;">Full Name</label>
              <input id="modalName" type="text" value="${userName || ""}" readonly
                style="width:100%; padding:10px 12px; border:1.5px solid #e5e7eb; border-radius:10px;
                       font-size:0.93rem; background:#f9fafb; color:#374151;" />
            </div>
            <div>
              <label style="font-size:0.83rem; font-weight:600; color:#374151; display:block; margin-bottom:5px;">Email</label>
              <input id="modalEmail" type="email" value="${userEmail || ""}" readonly
                style="width:100%; padding:10px 12px; border:1.5px solid #e5e7eb; border-radius:10px;
                       font-size:0.93rem; background:#f9fafb; color:#374151;" />
            </div>
          </div>

          <!-- Resume Upload -->
          <div style="margin-bottom:20px;">
            <label style="font-size:0.83rem; font-weight:600; color:#374151; display:block; margin-bottom:8px;">
              Resume / CV <span style="color:#ef4444;">*</span>
            </label>
            <div id="resumeDropzone" style="border:2px dashed #c7d2fe; border-radius:12px; padding:28px;
              text-align:center; cursor:pointer; transition:all 0.2s; background:#fafaff;">
              <div style="font-size:2rem;">📄</div>
              <p style="margin:8px 0 4px; font-weight:600; color:#4338ca;">Click or drag to upload resume</p>
              <p style="margin:0; font-size:0.82rem; color:#9ca3af;">PDF, DOC, DOCX up to 5MB</p>
              <input type="file" id="resumeInput" accept=".pdf,.doc,.docx" style="display:none;" />
            </div>
            <div id="resumeSelected" style="display:none; margin-top:10px; padding:10px 14px;
              background:#eef2ff; border-radius:10px; font-size:0.88rem; color:#4338ca; font-weight:600;"></div>
          </div>

          <!-- Cover Letter -->
          <div style="margin-bottom:24px;">
            <label style="font-size:0.83rem; font-weight:600; color:#374151; display:block; margin-bottom:8px;">
              Cover Letter <span style="color:#9ca3af; font-weight:400;">(optional)</span>
            </label>
            <textarea id="modalCoverLetter" rows="3" placeholder="Why are you a great fit for this role?"
              style="width:100%; padding:12px 14px; border:1.5px solid #e5e7eb; border-radius:10px;
                     font-size:0.92rem; resize:vertical; font-family:inherit; outline:none; box-sizing:border-box;"></textarea>
          </div>

          <!-- Submit -->
          <div id="applyModalMessage" style="min-height:18px; font-size:0.88rem; margin-bottom:12px; color:#dc2626;"></div>
          <button id="submitApplicationBtn"
            style="width:100%; padding:14px; background:linear-gradient(135deg,#2563eb,#7c3aed);
                   color:#fff; border:none; border-radius:12px; font-size:1rem; font-weight:700;
                   cursor:pointer; transition:opacity 0.2s;">
            Submit Application
          </button>
        </div>
      </div>

      <style>
        @keyframes slideUp {
          from { transform: translateY(30px); opacity: 0; }
          to { transform: translateY(0); opacity: 1; }
        }
      </style>
    `;

    document.body.appendChild(modal);

    // Set job title in modal
    if (currentJob) {
      document.getElementById("modalJobTitle").textContent =
        `${currentJob.title || "Job"} at ${currentJob.company || "Company"}`;
    }

    // Drag & drop + click to upload
    const dropzone = document.getElementById("resumeDropzone");
    const resumeInput = document.getElementById("resumeInput");
    const resumeSelected = document.getElementById("resumeSelected");
    let resumeBase64 = null;
    let resumeFileName = null;

    dropzone.addEventListener("click", () => resumeInput.click());
    dropzone.addEventListener("dragover", e => { e.preventDefault(); dropzone.style.borderColor = "#4338ca"; });
    dropzone.addEventListener("dragleave", () => { dropzone.style.borderColor = "#c7d2fe"; });
    dropzone.addEventListener("drop", e => {
      e.preventDefault();
      dropzone.style.borderColor = "#c7d2fe";
      handleFile(e.dataTransfer.files[0]);
    });

    resumeInput.addEventListener("change", () => {
      if (resumeInput.files[0]) handleFile(resumeInput.files[0]);
    });

    function handleFile(file) {
      if (!file) return;
      if (file.size > 5 * 1024 * 1024) {
        document.getElementById("applyModalMessage").textContent = "File too large. Max 5MB.";
        return;
      }
      resumeFileName = file.name;
      const reader = new FileReader();
      reader.onload = e => {
        resumeBase64 = e.target.result;
        resumeSelected.style.display = "block";
        resumeSelected.textContent = "✅ " + file.name;
        dropzone.style.background = "#eef2ff";
        document.getElementById("applyModalMessage").textContent = "";
      };
      reader.readAsDataURL(file);
    }

    // Close modal
    document.getElementById("closeModalBtn").addEventListener("click", () => {
      modal.style.display = "none";
    });
    modal.addEventListener("click", e => {
      if (e.target === modal) modal.style.display = "none";
    });

    // Submit application
    document.getElementById("submitApplicationBtn").addEventListener("click", async function () {
      const msg = document.getElementById("applyModalMessage");

      if (!resumeBase64) {
        msg.textContent = "Please upload your resume before submitting.";
        return;
      }

      this.textContent = "Submitting...";
      this.disabled = true;

      try {
        const response = await fetch("https://careerbridge-drls.onrender.com/api/applications/apply", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({
            jobId: parseInt(jobId),
            applicantName: userName || "User",
            applicantEmail: userEmail,
            resumeBase64: resumeBase64,
            resumeFileName: resumeFileName,
            coverLetter: document.getElementById("modalCoverLetter").value.trim()
          })
        });

        const result = await response.json();

        if (result.success) {
          // Success state
          modal.querySelector("div").innerHTML = `
            <div style="padding:48px 32px; text-align:center;">
              <div style="font-size:4rem; margin-bottom:16px;">🎉</div>
              <h2 style="color:#0f172a; margin-bottom:8px;">Application Submitted!</h2>
              <p style="color:#475569; margin-bottom:4px;">A confirmation has been sent to</p>
              <p style="color:#2563eb; font-weight:700; margin-bottom:24px;">${userEmail}</p>
              <p style="color:#94a3b8; font-size:0.88rem; margin-bottom:28px;">
                The recruiter will review your application and contact you soon.
              </p>
              <button onclick="document.getElementById('applyModal').style.display='none'"
                style="background:linear-gradient(135deg,#2563eb,#7c3aed); color:#fff;
                       border:none; padding:13px 36px; border-radius:10px; font-size:1rem;
                       font-weight:700; cursor:pointer;">
                Done
              </button>
            </div>
          `;
        } else {
          msg.style.color = "#dc2626";
          msg.textContent = result.message || "Application failed. Please try again.";
          this.textContent = "Submit Application";
          this.disabled = false;
        }
      } catch (error) {
        msg.textContent = "Server error. Please try again.";
        this.textContent = "Submit Application";
        this.disabled = false;
      }
    });
  }
});
