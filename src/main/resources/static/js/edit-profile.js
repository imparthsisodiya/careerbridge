document.addEventListener("DOMContentLoaded", async function () {
  const currentEmail = localStorage.getItem("userEmail");
  const editProfileForm = document.getElementById("editProfileForm");
  const editProfileMessage = document.getElementById("editProfileMessage");
  const editProfilePreview = document.getElementById("editProfilePreview");
  const profileImageInput = document.getElementById("profileImageInput");
  const resumeInput = document.getElementById("resumeInput");

  if (!currentEmail) {
    window.location.href = "login.html";
    return;
  }

  let profileImageBase64 = null;
  let existingResumeFileName = "";
  let existingResumePath = "";

  try {
    const response = await fetch(
      `https://careerbridge-drls.onrender.com/api/users/profile?email=${encodeURIComponent(currentEmail)}`
    );
    const result = await response.json();

    if (result.success && result.data) {
      const user = result.data;

      document.getElementById("fullName").value = user.name || "";
      document.getElementById("email").value = user.email || "";
      document.getElementById("phone").value = user.phone || "";
      document.getElementById("location").value = user.location || "";
      document.getElementById("degree").value = user.degree || "";
      document.getElementById("college").value = user.college || "";
      document.getElementById("educationYear").value = user.educationYear || "";
      document.getElementById("linkedIn").value = user.linkedIn || "";
      document.getElementById("about").value = user.about || "";

      existingResumeFileName = user.resumeFileName || "";
      existingResumePath = user.resumePath || "";

      if (user.profileImage) {
        editProfilePreview.src = user.profileImage;
        profileImageBase64 = user.profileImage;
      }
    }
  } catch (error) {
    console.error("Load profile error:", error);
  }

  if (profileImageInput) {
    profileImageInput.addEventListener("change", function () {
      const file = profileImageInput.files[0];
      if (!file) return;

      const reader = new FileReader();
      reader.onload = function (e) {
        profileImageBase64 = e.target.result;
        editProfilePreview.src = e.target.result;
      };
      reader.readAsDataURL(file);
    });
  }

  editProfileForm.addEventListener("submit", async function (e) {
    e.preventDefault();

    const payload = {
      name: document.getElementById("fullName").value.trim(),
      email: document.getElementById("email").value.trim(),
      phone: document.getElementById("phone").value.trim(),
      location: document.getElementById("location").value.trim(),
      degree: document.getElementById("degree").value.trim(),
      college: document.getElementById("college").value.trim(),
      educationYear: document.getElementById("educationYear").value.trim(),
      linkedIn: document.getElementById("linkedIn").value.trim(),
      about: document.getElementById("about").value.trim(),
      profileImage: profileImageBase64,
      resumeFileName: resumeInput.files[0]
        ? resumeInput.files[0].name
        : existingResumeFileName,
      resumePath: resumeInput.files[0] ? "#" : existingResumePath
    };

    try {
      const response = await fetch(
        `https://careerbridge-drls.onrender.com/api/users/profile?email=${encodeURIComponent(currentEmail)}`,
        {
          method: "PUT",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify(payload)
        }
      );

      const result = await response.json();

      if (result.success) {
        localStorage.setItem("userName", payload.name);
        localStorage.setItem("userEmail", payload.email);

        editProfileMessage.textContent = "Profile updated successfully.";

        setTimeout(() => {
          window.location.href = "profile.html";
        }, 1200);
      } else {
        editProfileMessage.textContent = result.message || "Profile update failed.";
      }
    } catch (error) {
      console.error("Update profile error:", error);
      editProfileMessage.textContent = "Server error. Please try again.";
    }
  });
});
