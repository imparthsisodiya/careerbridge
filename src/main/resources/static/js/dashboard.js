document.addEventListener("DOMContentLoaded", async function () {
  const storedEmail = localStorage.getItem("userEmail");
  const userRole = localStorage.getItem("userRole");

  if (!storedEmail || userRole !== "STUDENT") {
    window.location.href = "login.html";
    return;
  }

  const studentName = document.getElementById("studentName");
  const sidebarName = document.getElementById("sidebarName");
  const sidebarEmail = document.getElementById("sidebarEmail");
  const profileInitial = document.getElementById("profileInitial");
  const profileProgressFill = document.getElementById("profileProgressFill");
  const profileProgressText = document.getElementById("profileProgressText");

  function setDashboardData(name, email, completion) {
    const safeName = (name || "").trim() || "Student";
    const safeEmail = (email || "").trim();

    if (studentName) studentName.textContent = safeName;
    if (sidebarName) sidebarName.textContent = safeName;
    if (sidebarEmail) sidebarEmail.textContent = safeEmail;
    if (profileInitial) profileInitial.textContent = safeName.charAt(0).toUpperCase();

    if (profileProgressFill) profileProgressFill.style.width = `${completion}%`;
    if (profileProgressText) profileProgressText.textContent = `${completion}% completed`;
  }

  try {
    const response = await fetch(
      `https://careerbridge-drls.onrender.com/api/users/profile?email=${encodeURIComponent(storedEmail)}`
    );

    const result = await response.json();
    console.log("Dashboard user response:", result);

    if (!result.success || !result.data) {
      console.warn("User data not found");
      setDashboardData(
        localStorage.getItem("userName"),
        localStorage.getItem("userEmail"),
        0
      );
      return;
    }

    const user = result.data;

    const realName = user.name || localStorage.getItem("userName") || "Student";
    const realEmail = user.email || localStorage.getItem("userEmail") || "";

    // localStorage ko backend ke real data se sync karo
    localStorage.setItem("userName", realName);
    localStorage.setItem("userEmail", realEmail);

    let completedFields = 0;
    const totalFields = 6;

    if (user.name && user.name.trim() !== "") completedFields++;
    if (user.email && user.email.trim() !== "") completedFields++;
    if (user.phone && user.phone.trim() !== "") completedFields++;
    if (user.location && user.location.trim() !== "") completedFields++;
    if (user.bio && user.bio.trim() !== "") completedFields++;
    if (user.skills && user.skills.trim() !== "") completedFields++;

    const completion = Math.floor((completedFields / totalFields) * 100);

    setDashboardData(realName, realEmail, completion);
  } catch (error) {
    console.error("Dashboard error:", error);

    setDashboardData(
      localStorage.getItem("userName"),
      localStorage.getItem("userEmail"),
      0
    );
  }
});
