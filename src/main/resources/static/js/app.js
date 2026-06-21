async function loadRecruiterDashboard() {
    const user = getCurrentUser();
    if (!user) return;

    try {
        const [jobsRes, appsRes] = await Promise.all([
            fetch(`${API_BASE}/api/jobs`),
            fetch(`${API_BASE}/api/applications`)
        ]);

        const jobs = await jobsRes.json();
        const applications = await appsRes.json();

        const myJobs = jobs.filter(job => job.recruiterEmail === user.email);

        const myApplications = applications.filter(app =>
            app.job && app.job.recruiterEmail === user.email
        );

        document.getElementById("totalJobsCount").innerText = myJobs.length;
        document.getElementById("totalApplicationsCount").innerText = myApplications.length;
        document.getElementById("pendingApplicationsCount").innerText =
            myApplications.filter(app => app.status === "APPLIED").length;

        const body = document.getElementById("recruiterApplicationsBody");
        body.innerHTML = "";

        if (!myApplications.length) {
            body.innerHTML = `<tr><td colspan="5">No applications yet</td></tr>`;
            return;
        }

        myApplications.forEach(app => {
            let badgeClass = "bg-secondary";

            if (app.status === "APPLIED") badgeClass = "bg-warning";
            else if (app.status === "SHORTLISTED") badgeClass = "bg-success";
            else if (app.status === "REJECTED") badgeClass = "bg-danger";

            body.innerHTML += `
                <tr>
                    <td>${app.applicantName || "-"}</td>
                    <td>${app.applicantEmail || "-"}</td>
                    <td>${app.job?.title || "-"}</td>
                    <td><span class="badge ${badgeClass}">${app.status || "APPLIED"}</span></td>
                    <td>
                        <button class="btn btn-success btn-sm me-2"
                                onclick="shortlistApplication(${app.id})"
                                ${app.status === "SHORTLISTED" ? "disabled" : ""}>
                            Shortlist
                        </button>
                        <button class="btn btn-danger btn-sm"
                                onclick="rejectApplication(${app.id})"
                                ${app.status === "REJECTED" ? "disabled" : ""}>
                            Reject
                        </button>
                    </td>
                </tr>
            `;
        });

    } catch (error) {
        console.error(error);
        showMessage("Failed to load recruiter dashboard", "danger");
    }
}

