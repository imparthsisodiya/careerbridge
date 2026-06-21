document.addEventListener("DOMContentLoaded", function () {
  const postJobForm = document.getElementById("postJobForm");
  const postJobMessage = document.getElementById("postJobMessage");
  const recruiterEmailInput = document.getElementById("recruiterEmail");

  const savedEmail = localStorage.getItem("userEmail");
  if (savedEmail && recruiterEmailInput) recruiterEmailInput.value = savedEmail;

  if (!postJobForm) return;

  postJobForm.addEventListener("submit", async function (e) {
    e.preventDefault();

    const title = document.getElementById("title").value.trim();
    const company = document.getElementById("company").value.trim();
    const location = document.getElementById("location").value.trim();
    const type = document.getElementById("type").value;
    const experienceRequired = document.getElementById("experienceRequired").value.trim();
    const salaryRange = document.getElementById("salaryRange").value.trim();
    const description = document.getElementById("description").value.trim();
    const skills = document.getElementById("skills")?.value.trim() || "";
    const recruiterEmail = document.getElementById("recruiterEmail").value.trim();

    postJobMessage.textContent = "";

    if (!title || !company || !location || !type || !experienceRequired || !salaryRange || !description || !recruiterEmail) {
      postJobMessage.textContent = "Please fill all required fields.";
      return;
    }

    try {
      const response = await fetch("https://careerbridge-drls.onrender.com/api/jobs", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ title, company, location, type, experienceRequired, salaryRange, description, recruiterEmail, skills })
      });

      // FIXED: /api/jobs POST returns plain Job object, not ApiResponse
      if (response.ok) {
        postJobMessage.textContent = "Job posted successfully!";
        postJobForm.reset();
        if (savedEmail && recruiterEmailInput) recruiterEmailInput.value = savedEmail;
      } else {
        const error = await response.json();
        postJobMessage.textContent = error.message || "Failed to post job.";
      }
    } catch (error) {
      console.error("Post job error:", error);
      postJobMessage.textContent = "Server error. Please try again.";
    }
  });
});
