package com.oauth.business.service.implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.commons.lang3.RandomStringUtils;
import org.bouncycastle.openssl.PasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import com.oauth.business.UserDto;
import com.oauth.business.common.Converter;
import com.oauth.business.service.UserService;
import com.oauth.entity.User;
import com.oauth.entity.repository.UserRepository;



@Service
public class UserServiceImplementation implements UserService<UserDto, Integer> {

	@Autowired
	UserRepository userRepository;
	

	final String emailAddress = "www.zeromsi2@gmail.com";
	final String password = "PaglaNewton1*";

	@Override
	public Boolean save(UserDto userVM) {
		try {
			String tempPassword = "";
			if (userVM.getPassword() == null) {
				String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
				tempPassword = RandomStringUtils.random(8, characters);
				userVM.setPassword(tempPassword);
				boolean res = sendMail("Here's your temporary password:" + tempPassword, "Password Credential",
						userVM.getEmail());
			} else {
				tempPassword = userVM.getPassword();
			}
			
			userVM.setPassword("{bcrypt}"+BCrypt.hashpw(userVM.getPassword(), BCrypt.gensalt(10)));
			userRepository.save(Converter.getUser(userVM));
			return true;
		} catch (Exception e) {
			return false;
		}
	}



	public boolean sendMail(String text, String subject, String email) throws MessagingException, AddressException {
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.connectiontimeout", 1000);

		Session session = Session.getInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailAddress, password);
			}
		});

		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(emailAddress));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject(subject);
			String msg = text;

			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			mimeBodyPart.setContent(msg, "text/html");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(mimeBodyPart);

			message.setContent(multipart);

			Transport.send(message);
		} catch (NoSuchProviderException ex) {
			return false;
		} catch (AuthenticationFailedException ex) {
			return false;
		} catch (MessagingException ex) {
			return false;
		}
		return true;

	}

	@Override
	public boolean checkUsersExistency(String username) {
		if (userRepository.findByUsername(username) == null) {
			return false;
		} else {
			return true;
		}

	}

	@Override
	public boolean checkIfPasswordMeetsRequirements(String pass) {
		boolean complies = pass.matches(".*[0-9].*") && pass.matches(".*[a-z].*") && pass.matches(".*[A-Z].*")
				&& pass.matches(".*[$&+,:;=?@#|'<>.^*()%!-].*");
		return complies;
	}

	@Override
	public boolean checkIfEmailAddressExistsAlready(String email) {
		if (userRepository.findByEmail(email) == null) {
			return false;
		} else {
			return true;
		}
	}



}