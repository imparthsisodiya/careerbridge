document.addEventListener("DOMContentLoaded", async function () {
  const notificationsContainer = document.getElementById("notificationsContainer");
  const notificationCountText = document.getElementById("notificationCountText");
  const notificationFilter = document.getElementById("notificationFilter");
  const markAllReadBtn = document.getElementById("markAllReadBtn");
  const userEmail = localStorage.getItem("userEmail");

  if (!userEmail) { window.location.href = "login.html"; return; }

  let notifications = [];

  function renderNotifications(data) {
    if (!notificationsContainer) return;
    notificationsContainer.innerHTML = "";
    if (data.length === 0) {
      notificationsContainer.innerHTML = `<div class="notification-preview-item"><div><h4>No notifications</h4><p>You have no notifications yet.</p></div></div>`;
      if (notificationCountText) notificationCountText.textContent = "0 notifications";
      return;
    }
    if (notificationCountText) notificationCountText.textContent = `${data.length} notifications`;
    data.forEach(item => {
      const card = document.createElement("div");
      card.className = "notification-preview-item notification-item-custom";
      card.innerHTML = `
        <div>
          <h4>${item.title || "Notification"}</h4>
          <p>${item.timeText || ""}</p>
        </div>
        <span class="notification-status ${item.status === "UNREAD" ? "pending" : "shortlisted"}">${item.status || "UNREAD"}</span>
      `;
      notificationsContainer.appendChild(card);
    });
  }

  async function loadNotifications() {
    try {
      const response = await fetch(`https://careerbridge-drls.onrender.com/api/notifications?email=${encodeURIComponent(userEmail)}`);
      // FIXED: returns plain List, not ApiResponse
      const result = await response.json();
      notifications = (Array.isArray(result) ? result : []).map(item => ({
        id: item.id,
        title: item.title || "Notification",
        timeText: item.timeText || "",
        status: item.status || "UNREAD"
      }));
      filterNotifications();
    } catch (error) {
      console.error("Notifications fetch error:", error);
      if (notificationsContainer) notificationsContainer.innerHTML = `<div class="notification-preview-item"><h4>Error loading notifications</h4></div>`;
    }
  }

  function filterNotifications() {
    const selected = notificationFilter ? notificationFilter.value : "";
    renderNotifications(!selected ? notifications : notifications.filter(n => n.status === selected));
  }

  if (notificationFilter) notificationFilter.addEventListener("change", filterNotifications);

  if (markAllReadBtn) {
    markAllReadBtn.addEventListener("click", async function () {
      try {
        await fetch(`https://careerbridge-drls.onrender.com/api/notifications/mark-all-read?email=${encodeURIComponent(userEmail)}`, { method: "PUT" });
        notifications = notifications.map(n => ({ ...n, status: "READ" }));
        filterNotifications();
      } catch (error) {
        console.error("Mark all read error:", error);
      }
    });
  }

  await loadNotifications();
});
