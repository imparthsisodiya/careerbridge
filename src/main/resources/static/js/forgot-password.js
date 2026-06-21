document.addEventListener("DOMContentLoaded", function () {
  const forgotPasswordForm = document.getElementById("forgotPasswordForm");
  const forgotPasswordMessage = document.getElementById("forgotPasswordMessage");

  if (!forgotPasswordForm) return;

  forgotPasswordForm.addEventListener("submit", async function (e) {
    e.preventDefault();

    const email = document.getElementById("resetEmail").value.trim();
    forgotPasswordMessage.textContent = "";

    if (!email) {
      forgotPasswordMessage.textContent = "Please enter your email.";
      return;
    }

    try {
      const response = await fetch("https://careerbridge-drls.onrender.com/api/auth/forgot-password", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({ email })
      });

      const result = await response.json();

      if (result.success) {
        forgotPasswordMessage.textContent =
          result.message || "Password reset link sent successfully.";
      } else {
        forgotPasswordMessage.textContent =
          result.message || "Unable to send reset link.";
      }
    } catch (error) {
      console.error("Forgot password error:", error);
      forgotPasswordMessage.textContent = "Server error. Check backend connection.";
    }
  });
});
