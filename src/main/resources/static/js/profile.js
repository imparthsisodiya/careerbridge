document.addEventListener("DOMContentLoaded", async function () {
  const userEmail = localStorage.getItem("userEmail");

  if (!userEmail) {
    window.location.href = "login.html";
    return;
  }

  try {
    const response = await fetch(
      `https://careerbridge-drls.onrender.com/api/users/profile?email=${encodeURIComponent(userEmail)}`
    );
    const result = await response.json();

    if (!result.success || !result.data) {
      alert("Failed to load profile.");
      return;
    }

    const user = result.data;

    document.getElementById("profileName").textContent = user.name || "User Name";
    document.getElementById("profileEmail").textContent = user.email || "Email not added";
    document.getElementById("profileRole").textContent = user.role || "Role not added";
    document.getElementById("profileLocation").textContent = user.location || "Location not added";

    document.getElementById("contactEmail").textContent = user.email || "Not added";
    document.getElementById("contactPhone").textContent = user.phone || "Not added";
    document.getElementById("contactLocation").textContent = user.location || "Not added";
    document.getElementById("contactLinkedIn").textContent = user.linkedIn || "Not added";

    document.getElementById("profileAbout").textContent =
      user.about || "No about information added yet.";
    document.getElementById("educationDegree").textContent = user.degree || "Not added";
    document.getElementById("educationCollege").textContent = user.college || "Not added";
    document.getElementById("educationYear").textContent = user.educationYear || "Not added";

    document.getElementById("resumeFileName").textContent =
      user.resumeFileName || "No resume uploaded";

    const profileImage = document.getElementById("profileImage");
    if (user.profileImage) {
      profileImage.src = user.profileImage;
    }

    const resumeLink = document.getElementById("resumeLink");
    if (user.resumePath) {
      resumeLink.href = user.resumePath;
      resumeLink.classList.remove("profile-btn-disabled");
      resumeLink.style.pointerEvents = "auto";
      resumeLink.style.opacity = "1";
    }

    let completedFields = 0;
    const totalFields = 8;

    if (user.name && user.name.trim() !== "") completedFields++;
    if (user.email && user.email.trim() !== "") completedFields++;
    if (user.phone && user.phone.trim() !== "") completedFields++;
    if (user.location && user.location.trim() !== "") completedFields++;
    if (user.degree && user.degree.trim() !== "") completedFields++;
    if (user.college && user.college.trim() !== "") completedFields++;
    if (user.about && user.about.trim() !== "") completedFields++;
    if (user.skills && user.skills.trim() !== "") completedFields++;

    const completion = Math.floor((completedFields / totalFields) * 100);

    document.getElementById("profileCompletion").textContent = `${completion}%`;
    document.getElementById("profileProgressFill").style.width = `${completion}%`;
    document.getElementById("profileProgressText").textContent = `${completion}% completed`;

    document.getElementById("profileAppliedCount").textContent = "0";
    document.getElementById("profileSavedCount").textContent = "0";

    const skillsContainer = document.getElementById("profileSkills");
    skillsContainer.innerHTML = "";

    if (user.skills && user.skills.trim() !== "") {
      const skillsArray = user.skills
        .split(",")
        .map(skill => skill.trim())
        .filter(Boolean);

      if (skillsArray.length > 0) {
        skillsArray.forEach(skill => {
          const span = document.createElement("span");
          span.textContent = skill;
          span.className = "skill-tag";
          skillsContainer.appendChild(span);
        });
      } else {
        skillsContainer.innerHTML = `<span>No skills added</span>`;
      }
    } else {
      skillsContainer.innerHTML = `<span>No skills added</span>`;
    }
  } catch (error) {
    console.error("Profile fetch error:", error);
    alert("Server error. Please try again.");
  }
});
