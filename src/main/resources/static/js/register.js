document.addEventListener("DOMContentLoaded", function () {
  const registerForm = document.getElementById("registerForm");
  const registerMessage = document.getElementById("registerMessage");

  if (!registerForm) return;

  registerForm.addEventListener("submit", async function (e) {
    e.preventDefault();

    const name = document.getElementById("name").value.trim();
    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value.trim();
    const confirmPassword = document.getElementById("confirmPassword").value.trim();
    const role = document.getElementById("role").value;

    registerMessage.textContent = "";

    if (!name || !email || !password || !confirmPassword || !role) {
      registerMessage.textContent = "Please fill all fields.";
      return;
    }

    if (password !== confirmPassword) {
      registerMessage.textContent = "Password and confirm password do not match.";
      return;
    }

    if (password.length < 6) {
      registerMessage.textContent = "Password must be at least 6 characters.";
      return;
    }

    try {
      const response = await fetch("https://careerbridge-drls.onrender.com/api/auth/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
          name,
          email,
          password,
          role
        })
      });

      const result = await response.json();

      if (result.success) {
        registerMessage.textContent = "Registration successful. Redirecting to login...";

        setTimeout(() => {
          window.location.href = "login.html";
        }, 1500);
      } else {
        registerMessage.textContent = result.message || "Registration failed.";
      }
    } catch (error) {
      console.error("Registration error:", error);
      registerMessage.textContent = "Server error. Check backend connection.";
    }
  });
});
