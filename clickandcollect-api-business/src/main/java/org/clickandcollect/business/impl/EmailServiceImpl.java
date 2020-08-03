package org.clickandcollect.business.impl;

import lombok.extern.slf4j.Slf4j;
import org.clickandcollect.business.contract.EmailService;
import org.clickandcollect.model.entity.ClientOrder;
import org.clickandcollect.model.entity.MenuOrder;
import org.clickandcollect.model.entity.ProductOrder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final TemplateEngine templateEngine;
    private final JavaMailSender mailSender;
    @Value("${spring.mail.default-encoding}")
    private String encoding;
    @Value("${email.username}")
    private String EMAIL_USERNAME;

    public EmailServiceImpl(TemplateEngine templateEngine, JavaMailSender mailSender) {
        this.templateEngine = templateEngine;
        this.mailSender = mailSender;
    }

    private String buildEmailForClientNotification(ClientOrder clientOrder, String template) {
        Context context = new Context();
        context.setVariable("order", clientOrder);
        context.setVariable("totalPrice", getOrderPrice(clientOrder));
        return templateEngine.process(template, context);
    }

    private Double getOrderPrice(ClientOrder clientOrder) {
        double price = 0D;
        for (ProductOrder product : clientOrder.getProductOrders()) {
            price += product.getProduct().getPrice() * product.getQuantity();
        }
        for (MenuOrder menu : clientOrder.getMenuOrders()) {
            price += menu.getMenu().getPrice() * menu.getQuantity();
        }
        return price;
    }

    @Async
    @Override
    public void sendClientOrderNotification(ClientOrder order) {
        log.info("Preparing email for client notification");
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, encoding);
            messageHelper.setFrom(EMAIL_USERNAME);
            messageHelper.setTo(order.getEmail());
            messageHelper.setSubject("Click'n Collect : Votre commande est envoyée !");
            String content = this.buildEmailForClientNotification(order, "clientOrderNotificationTemplate");
            messageHelper.setText(content, true);
        };
        try {
            log.info("Sending email to {}, for order '{}'", order.getEmail(), order.getId());
            mailSender.send(messagePreparator);
        } catch (MailException e) {
            log.error("Error while sending email to {}, for order '{}'", order.getEmail(), order.getId());
            log.error(e.getMessage());
        }
    }

    @Async
    @Override
    public void sendRestaurantOrderNotification(ClientOrder order) {
        log.info("Preparing email for restaurant notification");
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, encoding);
            messageHelper.setFrom(EMAIL_USERNAME);
            messageHelper.setTo(order.getRestaurant().getEmail());
            messageHelper.setSubject("Click'n Collect : Vous avez reçu une nouvelle commande !");
            String content = this.buildEmailForClientNotification(order, "restaurantOrderNotificationTemplate");
            messageHelper.setText(content, true);
        };
        try {
            log.info("Sending email to {}, for order '{}'", order.getRestaurant().getEmail(), order.getId());
            mailSender.send(messagePreparator);
        } catch (MailException e) {
            log.error("Error while sending email to {}, for order '{}'", order.getRestaurant().getEmail(), order.getId());
            log.error(e.getMessage());
        }
    }
}
