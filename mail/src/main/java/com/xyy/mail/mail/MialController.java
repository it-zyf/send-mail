package com.xyy.mail.mail;

import com.xyy.mail.MailApplication;
import com.xyy.mail.mail.entity.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;

/**
 * @author yayu
 * @title: MialController
 * @description: TODO
 * @date 2020/8/269:58
 */
@RestController
public class MialController {
    @Autowired
    private JavaMailSender javaMailSender;

    //todo 纯文字邮件
    @RequestMapping("/mail1")
    public void test() throws MessagingException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("这是一封测试邮件");
        message.setFrom("761769578@qq.com");
        message.setTo("marshallwjc@163.com");
        message.setCc("18734724467@163.com");
        message.setCc("15735653659@163.com");
        message.setSentDate(new Date());
        message.setText("hello IM curry oligei");
        javaMailSender.send(message);

    }

    //todo 带图片文件
    @RequestMapping("/mail2")
    public void test2() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("这是一封测试邮件");
        helper.setFrom("761769578@qq.com");
        helper.setTo("15735653659@163.com");
//       helper.setCc("37xxxxx37@qq.com");
//       helper.setBcc("14xxxxx098@qq.com");
        helper.setSentDate(new Date());
        helper.setText("<p>hello 大家好，这是一封测试邮件，这封邮件包含两种图片，分别如下</p><p>第一张图片：</p><img src='cid:p01'/><p>第二张图片：</p><img src='cid:p02'/>", true);
        helper.addInline("p01", new FileSystemResource(new File("P:\\crawler\\马蓉1596762535754\\1596762539259.jpg")));
        helper.addInline("p02", new FileSystemResource(new File("P:\\crawler\\马蓉1596762535754\\1596762539341.jpg")));
        javaMailSender.send(mimeMessage);
    }

    //todo 发送带附件的邮件
    @RequestMapping("/mail3")
    public void test3() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("这是一封测试邮件");
        helper.setFrom("761769578@qq.com");
        helper.setTo("15735653659@163.com");
        helper.setSentDate(new Date());
        helper.setText("这是测试邮件的正文");
        //添加附件
        helper.addAttachment("马蓉.jpg", new File("P:\\crawler\\马蓉1596762535754\\1596762539259.jpg"));
        javaMailSender.send(mimeMessage);
    }

    //todo 模板发送
    @RequestMapping("/mail4")
    public void test4() throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("这是一封java邮件,通过亚爷的程序发出来的");
        helper.setFrom("761769578@qq.com");
        helper.setTo("1457937239@qq.com");
//        helper.setCc("37xxxxx37@qq.com");
//        helper.setBcc("14xxxxx098@qq.com");
        helper.setSentDate(new Date());
        //构建 Freemarker 的基本配置
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
        // 配置模板位置
        ClassLoader loader = MailApplication.class.getClassLoader();
        configuration.setClassLoaderForTemplateLoading(loader, "templates");
        //加载模板
        Template template = configuration.getTemplate("mail.ftl");
        User user = new User();
        user.setUserName("珍爷");
        user.setNum(1);
        user.setSalary((double) 99999);
        StringWriter out = new StringWriter();
        //模板渲染，渲染的结果将被保存到 out 中 ，将out 中的 html 字符串发送即可
        template.process(user, out);
        helper.setText(out.toString(),true);
        javaMailSender.send(mimeMessage);
    }

}
