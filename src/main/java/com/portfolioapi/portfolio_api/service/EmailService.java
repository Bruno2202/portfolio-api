package com.portfolioapi.portfolio_api.service;

import com.portfolioapi.portfolio_api.dto.EmailDTO;
import com.portfolioapi.portfolio_api.entity.Email;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${portfolioapi.email.defautlReceiver}")
    private String defaultReceiver;

    public void sendIssuerEmail(EmailDTO email) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            Context context = new Context();
            context.setVariable("nome", email.issuerName());
            context.setVariable("emailRemetente", email.issuerEmail());
            context.setVariable("mensagem", email.message());

            String issuerHtmlContent = templateEngine.process("issuer-email-template", context);

            helper.setFrom(defaultReceiver);
            helper.setTo(email.issuerEmail());
            helper.setSubject("Olá " + email.issuerName() + ", vamos construir algo juntos? 🚀");
            helper.setText(issuerHtmlContent, true);


            mailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException("Erro ao processar e-mail", e);
        }
    }

    public void sendEmail(EmailDTO email) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            Context context = new Context();
            context.setVariable("nome", email.issuerName());
            context.setVariable("emailRemetente", email.issuerEmail());
            context.setVariable("mensagem", email.message());
            String HtmlContent = templateEngine.process("email-template", context);


            helper.setFrom(defaultReceiver);
            helper.setTo(defaultReceiver);
            helper.setSubject("Novo Contato: " + email.issuerName());
            helper.setText(HtmlContent, true);

            mailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException("Erro ao processar e-mail", e);
        }
    }
}