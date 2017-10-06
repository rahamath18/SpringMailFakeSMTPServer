package com.test.smtp;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
 
public class TestFAKESMTP {
	
	public static void main(String[] args) {
		
		JavaMailSenderImpl impl = new JavaMailSender().getMailSender();
		
		Map<String, String> to = new HashMap<>();
		to.put("rahamath1805@gmail.com", "Rahamath");
		Map<String, String> cc = new HashMap<>(); 
		Map<String, String> bcc = new HashMap<>();
		
		String from = "rahamath1805@gmail.com";
		String fromName = "Rahamath";
		String subject = "Test email from Spring Mail to Fake SMTP Server";
		String body = "<div style=\"color:red;\">Email with static template</br></br><table border=\"1\" style=\"width:20%\"><tr><td>Email</td><td>Test</td><td>50</td></tr><tr><td>Email</td><td>Test</td><td>50</td></tr><tr><td>Email</td><td>Test</td><td>50</td></tr></table></div>";

		impl.send(new TestFAKESMTP().getMessagePreparator(from, fromName, to, cc, bcc, subject, body));
	}
	
	private MimeMessagePreparator getMessagePreparator(String from, String fromName, Map<String, String> to,
			Map<String, String> cc, Map<String, String> bcc, String subject, String body) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			public void prepare(MimeMessage mimeMessage) throws Exception {

				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

				helper.setFrom(new InternetAddress(from, fromName));

				InternetAddress[] toArr = new InternetAddress[to.size()];
				Set<String> toSet = to.keySet();
				int toCount = 0;
				for (String key : toSet) {
					toArr[toCount] = new InternetAddress(key, to.get(key));
					toCount = toCount + 1;
				}
				helper.setTo(toArr);

				InternetAddress[] ccArr = new InternetAddress[cc.size()];
				Set<String> ccSet = cc.keySet();
				int ccCount = 0;
				for (String key : ccSet) {
					ccArr[ccCount] = new InternetAddress(key, cc.get(key));
					ccCount = ccCount + 1;
				}
				helper.setCc(ccArr);

				InternetAddress[] bccArr = new InternetAddress[bcc.size()];
				Set<String> bccSet = bcc.keySet();
				int bccCount = 0;
				for (String key : bccSet) {
					bccArr[bccCount] = new InternetAddress(key, bcc.get(key));
					bccCount = bccCount + 1;
				}
				helper.setBcc(bccArr);

				helper.setText(body, true);
				helper.setSubject(subject);
			}
		};
		return preparator;
	}

}
