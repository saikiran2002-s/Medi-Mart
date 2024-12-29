package com.med.MediMart.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.med.MediMart.entity.Admin;
import com.med.MediMart.entity.Member;
import com.med.MediMart.repository.AdminRepository;
import com.med.MediMart.repository.MemberRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.util.List;

@Component
public class MediMartMailSender {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private MemberRepository memberRepository;

    public void sendEmailToAdmins(Member member) throws MessagingException {
        // Fetch all admins
        List<Admin> admins = adminRepository.findAll();

        // Loop through each admin and send personalized HTML email
        for (Admin admin : admins) {
            String recipient = admin.getAdminemail();
            String firstName = admin.getAdminname();

            // Prepare the subject and HTML content with background and improved styling
            String subject = "A new member registered";
            String htmlContent = "<div style=\"padding: 20px; text-align: center; background-image: url('https://png.pngtree.com/thumb_back/fh260/background/20230903/pngtree-drug-safety-what-the-pharmacist-should-know-image_13182921.jpg'); "
                    + "background-size: cover; background-position: center; background-color: rgba(0, 0, 0, 0.5);\">\r\n"
                    + "    <div style=\"background-color: rgba(0, 0, 0, 0.6); padding: 20px; border-radius: 10px; color: white;\">\r\n"
                    + "        <h1 style=\"font-size: 28px;\">Hello, " + firstName + "!</h1>\r\n"
                    + "        <p>A new Member has registered into MediMart: <strong style=\"font-size: 20px;\">" + member.getName() + "</strong>. Below are the details:</p>\r\n"
                    + "        <table style=\"width: 100%; border-collapse: collapse; margin-top: 20px;\">\r\n"
                    + "            <tr style=\"background-color: rgba(255, 255, 255, 0.2);\">\r\n"
                    + "                <th style=\"padding: 10px; border: 1px solid #ddd;\">Field</th>\r\n"
                    + "                <th style=\"padding: 10px; border: 1px solid #ddd;\">Details</th>\r\n"
                    + "            </tr>\r\n"
                    + "            <tr>\r\n"
                    + "                <td style=\"padding: 10px; border: 1px solid #ddd;\">ID</td>\r\n"
                    + "                <td style=\"padding: 10px; border: 1px solid #ddd;\">" + member.getMemberid() + "</td>\r\n"
                    + "            </tr>\r\n"
                    + "            <tr style=\"background-color: rgba(255, 255, 255, 0.1);\">\r\n"
                    + "                <td style=\"padding: 10px; border: 1px solid #ddd;\">Name</td>\r\n"
                    + "                <td style=\"padding: 10px; border: 1px solid #ddd;\">" + member.getName() + "</td>\r\n"
                    + "            </tr>\r\n"
                    + "            <tr>\r\n"
                    + "                <td style=\"padding: 10px; border: 1px solid #ddd;\">Email</td>\r\n"
                    + "                <td style=\"padding: 10px; border: 1px solid #ddd;color: white;\">" + member.getEmail() + "</td>\r\n"
                    + "            </tr>\r\n"
                    + "            <tr style=\"background-color: rgba(255, 255, 255, 0.1);\">\r\n"
                    + "                <td style=\"padding: 10px; border: 1px solid #ddd;\">Mobile No</td>\r\n"
                    + "                <td style=\"padding: 10px; border: 1px solid #ddd;\">" + member.getMobileno() + "</td>\r\n"
                    + "            </tr>\r\n"
                    + "            <tr>\r\n"
                    + "                <td style=\"padding: 10px; border: 1px solid #ddd;\">Gender</td>\r\n"
                    + "                <td style=\"padding: 10px; border: 1px solid #ddd;\">" + member.getGender() + "</td>\r\n"
                    + "            </tr>\r\n"
                    + "        </table>\r\n"
                    + "        <p style=\"margin-top: 20px;\">We hope you're having a great day!</p>\r\n"
                    + "        <p>Best regards,</p>\r\n"
                    + "        <p><strong>- @MediMart.corp</strong></p>\r\n"
                    + "    </div>\r\n"
                    + "</div>";

            // Send the email
            sendHtmlEmail(recipient, subject, htmlContent);
        }
    }

    public void sendEmailToMembers(Member member) throws MessagingException {
        String recipient = member.getEmail();
        String firstName = member.getName();

        // Prepare the subject and HTML content with a background image and a dark overlay
        String subject = "Welcome to MediMart.";
        String htmlContent = "    <div style=\"padding: 20px; text-align: center; background-image: url('https://png.pngtree.com/thumb_back/fh260/background/20230903/pngtree-drug-safety-what-the-pharmacist-should-know-image_13182921.jpg'); background-size: cover; background-position: center; background-color: rgba(0, 0, 0, 0.5);\">\r\n"
        		+ "        <div style=\"background-color: rgba(0, 0, 0, 0.6); padding: 20px; border-radius: 10px; color: white;\">\r\n"
        		+ "            <h1>Welcome to MediMart,<span style=\"font-size: 50px;\"> "+firstName+"!</span></h1>\r\n"
        		+ "            <p>Thank you for registering with us. We're excited to have you as a part of our community.</p>\r\n"
        		+ "            <p>You can now explore our services, track your orders, and stay updated on our latest offerings.</p>\r\n"
        		+ "            <p>And please wait for us to verify Your account .</p>\r\n"
        		+ "            <p>If you have any questions, feel free to contact us at whatsapp7066@gmail.com.</p>\r\n"
        		+ "            <p>Best regards,</p>\r\n"
        		+ "            <p><strong style=\"font-size: 20px;\">- The MediMart Team</strong></p>\r\n"
        		+ "        </div>\r\n"
        		+ "    </div>";

        // Send the email
        sendHtmlEmail(recipient, subject, htmlContent);
    }

    public void sendVerifyEmailToTheMember(Member member) throws MessagingException {
    	 String recipient = member.getEmail();
         String firstName = member.getName();

         // Prepare the subject and HTML content with a background image and a dark overlay
         String subject = "Account Verified.";
         String htmlContent = "<div style=\"padding: 20px; text-align: center; background-image: url('https://png.pngtree.com/thumb_back/fh260/background/20230903/pngtree-drug-safety-what-the-pharmacist-should-know-image_13182921.jpg'); background-size: cover; background-position: center; background-color: rgba(0, 0, 0, 0.5);\">\r\n"
         		+ "        <div style=\"background-color: rgba(0, 0, 0, 0.6); padding: 20px; border-radius: 10px; color: white;\">\r\n"
         		+ "            <h1 style=\"color: white; font-size: 28px;\">Your Account is Verified</h1>\r\n"
         		+ "            <p style=\"font-size: 16px; color: white;\">\r\n"
         		+ "                Hi <strong>"+firstName+"</strong>,\r\n"
         		+ "            </p>\r\n"
         		+ "            <p style=\"font-size: 16px; color: white;\">\r\n"
         		+ "                Congratulations! Your account with <strong>MediMart</strong> has been successfully verified. You can now enjoy full access to our services, track your orders, and explore our latest offerings.\r\n"
         		+ "            </p>\r\n"
         		+ "            <p style=\"font-size: 16px; color: white;\">\r\n"
         		+ "                Click the button below to log in to your account and start exploring:\r\n"
         		+ "            </p>\r\n"
         		+ "            <a href=\"[LoginLink]\" style=\"display: inline-block; padding: 10px 20px; background-color: #4CAF50; color: white; text-decoration: none; font-size: 16px; border-radius: 5px; margin: 20px 0;\">Go to Your Account</a>\r\n"
         		+ "            <p style=\"font-size: 16px; color: white;\">\r\n"
         		+ "                If you have any questions or need assistance, feel free to contact our support team.\r\n"
         		+ "            </p>\r\n"
         		+ "            <p style=\"font-size: 16px; color: white;\">\r\n"
         		+ "                Best regards,<br>\r\n"
         		+ "                <strong>The MediMart Team</strong>\r\n"
         		+ "            </p>\r\n"
         		+ "        </div>\r\n"
         		+ "        <p style=\"font-size: 12px; color: white; margin-top: 20px;\">\r\n"
         		+ "            If you're having trouble clicking the \"Go to Your Account\" button, copy and paste the following link into your browser:<br>\r\n"
         		+ "            <a href=\"[LoginLink]\" style=\"color: #4CAF50;\">[LoginLink]</a>\r\n"
         		+ "        </p>\r\n"
         		+ "    </div>";

         // Send the email
         sendHtmlEmail(recipient, subject, htmlContent);
    }


    public void sendHtmlEmail(String recipient, String subject, String htmlContent) throws MessagingException {
        // Create a MimeMessage
        MimeMessage message = mailSender.createMimeMessage();

        // Use MimeMessageHelper to handle the HTML content and recipient
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom("sender@example.com");
        helper.setTo(recipient);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);  // Pass 'true' to indicate the content is HTML

        // Send the email
        mailSender.send(message);
    }
    
    
}
