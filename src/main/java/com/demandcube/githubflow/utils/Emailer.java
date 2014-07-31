package com.demandcube.githubflow.utils;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.log4j.Logger;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;

public final class Emailer {

	private static Logger logger = Logger.getLogger(Emailer.class);
	private String username = "";
	private String password = "";
	private String from;
	private Set<String> recipients = new HashSet<String>();
	private String subject;
	private String body;
	// private String filename;
	private String hostName;
	private String filename;

	// username password server port

	public Emailer(String hostname, String port, String username,
			String password) {
		this.hostName = hostname;
		this.username = username;
		this.password = password;
	}

	public Emailer() {

	}

	public Emailer sendFrom(String from) {
		this.from = checkNotNull(from);
		return this;
	}

	public Emailer setHostName(String hostName) {
		checkNotNull(hostName);
		this.hostName = hostName;
		return this;
	}

	public Emailer sendTo(String to) {
		String delim = to.contains(",") ? "," : ":";
		String[] recepients = checkNotNull(to).split(delim);
		this.recipients.addAll(Arrays.asList(recepients));
		return this;
	}

	public Emailer sendTo(Collection<String> to) {
		checkNotNull(to);
		this.recipients.addAll(to);
		return this;
	}

	public Emailer setSubject(String subject) {
		checkNotNull(subject);
		this.subject = subject;
		return this;
	}

	public Emailer setBody(String body) {
		this.body = body;
		return this;
	}

	public Emailer setAttachment(String filename) {
		this.filename = filename;
		return this;
	}

	public Emailer setAttachment(File file) {
		this.filename = file.getAbsolutePath();
		return this;
	}

	public void sendMail() throws EmailException {
		// Create the attachment
		logger.debug("Send mail");
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath(filename);
		logger.debug("file name " + filename);
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription("Weekly Summary for Week Starting "
				+ filename);
		logger.debug("hahaha "
				+ filename.substring(filename.lastIndexOf(System
						.getProperty("file.separator")) + 1, filename.length()));
		attachment.setName(filename.substring(
				filename.lastIndexOf(System.getProperty("file.separator")) + 1,
				filename.length()));

		// Create the email message
		MultiPartEmail email = new MultiPartEmail();
		email.setHostName(hostName);
		email.setAuthenticator(new GMailAuthenticator(from, password));
		// email.setTLS(true);
		// email.setSSL(true);
		// email.setSmtpPort(465);
		email.setSmtpPort(587);
		email.setTLS(true);

		email.setTo(Collections2.transform(recipients,
				new Function<String, InternetAddress>() {
					@Override
					public InternetAddress apply(String address) {
						try {
							return new InternetAddress(address);
						} catch (AddressException e) {
							e.printStackTrace();
						}
						return null;
					}
				}));

		email.setFrom(from);
		email.setSubject(subject);
		email.setMsg(body);

		logger.debug("attaching attachment " + attachment);
		// add the attachment
		email.attach(attachment);
		logger.debug("just before send");
		// send the email
		email.setSSL(true);
		email.setTLS(true);
		email.send();
		logger.debug("after sending to --> " + recipients);
	}

	public Emailer setPassword(String password) {
		this.password = checkNotNull(password);
		return this;
	}
}

class GMailAuthenticator extends Authenticator {
	String user;
	String pw;

	public GMailAuthenticator(String username, String password) {
		super();
		this.user = username;
		this.pw = password;
	}

	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(user, pw);
	}
}