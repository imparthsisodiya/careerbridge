package com.careerbridge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendApplicationConfirmation(String toEmail, String applicantName,
                                             String jobTitle, String company) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(fromEmail);
            helper.setTo(toEmail);
            helper.setSubject("Application Submitted — " + jobTitle + " at " + company);

            String html = """
                <div style="font-family: Inter, sans-serif; max-width: 600px; margin: auto; background: #f9fafb; padding: 30px; border-radius: 16px;">
                  <div style="background: linear-gradient(135deg, #2563eb, #7c3aed); padding: 30px; border-radius: 12px; text-align: center;">
                    <h1 style="color: white; margin: 0; font-size: 1.8rem;">CareerBridge</h1>
                    <p style="color: #c7d2fe; margin: 6px 0 0;">Your career journey starts here</p>
                  </div>

                  <div style="background: white; padding: 32px; border-radius: 12px; margin-top: 20px; box-shadow: 0 2px 10px rgba(0,0,0,0.05);">
                    <h2 style="color: #0f172a; margin-top: 0;">🎉 Application Submitted Successfully!</h2>
                    <p style="color: #475569;">Hi <strong>%s</strong>,</p>
                    <p style="color: #475569;">Your application for the following position has been submitted:</p>

                    <div style="background: #eef2ff; border-left: 4px solid #4338ca; padding: 16px 20px; border-radius: 8px; margin: 20px 0;">
                      <p style="margin: 0; font-size: 1.1rem; font-weight: 700; color: #1e1b4b;">%s</p>
                      <p style="margin: 4px 0 0; color: #4338ca; font-weight: 600;">%s</p>
                    </div>

                    <p style="color: #475569;">The recruiter will review your application and get back to you soon. You can track your application status on CareerBridge.</p>

                    <div style="text-align: center; margin-top: 28px;">
                      <a href="https://careerbridge-drls.onrender.com/applied-jobs.html"
                         style="background: linear-gradient(135deg, #2563eb, #7c3aed); color: white; text-decoration: none; padding: 14px 32px; border-radius: 10px; font-weight: 700; display: inline-block;">
                        Track My Applications
                      </a>
                    </div>
                  </div>

                  <p style="text-align: center; color: #94a3b8; font-size: 0.85rem; margin-top: 24px;">
                    © 2024 CareerBridge. All rights reserved.
                  </p>
                </div>
                """.formatted(applicantName, jobTitle, company);

            helper.setText(html, true);
            mailSender.send(message);

        } catch (MessagingException e) {
            // Log but don't fail the application — email is bonus feature
            System.err.println("Email send failed: " + e.getMessage());
        }
    }
}
