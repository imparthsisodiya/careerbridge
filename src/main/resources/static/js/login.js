document.addEventListener("DOMContentLoaded", function () {
  const loginForm = document.getElementById("loginForm");
  const loginMessage = document.getElementById("loginMessage");

  if (!loginForm) return;

  loginForm.addEventListener("submit", async function (e) {
    e.preventDefault();

    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value.trim();

    loginMessage.textContent = "";

    if (!email || !password) {
      loginMessage.textContent = "Please fill all fields.";
      return;
    }

    try {
      const response = await fetch("https://careerbridge-drls.onrender.com/api/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, password })
      });

      const result = await response.json();

      if (result.success) {
        const user = result.data;

        localStorage.setItem("isLoggedIn", "true");
        localStorage.setItem("token", user.token || "");
        localStorage.setItem("userEmail", user.email || email);
        localStorage.setItem("userName", user.name || "User");
        localStorage.setItem("userRole", user.role || "STUDENT");

        loginMessage.textContent = "Login successful...";

        if (user.role === "STUDENT") {
          window.location.href = "Student-dashboard.html";
        } else if (user.role === "RECRUITER") {
          window.location.href = "Recruiter-dashboard.html";
        } else if (user.role === "ADMIN") {
          window.location.href = "admin-dashboard.html";

        } else {
          window.location.href = "index.html";
        }
      } else {
        loginMessage.textContent = result.message || "Invalid email or password.";
      }
    } catch (error) {
      console.error("Login error:", error);
      loginMessage.textContent = "Server error. Please try again.";
    }
  });
});
