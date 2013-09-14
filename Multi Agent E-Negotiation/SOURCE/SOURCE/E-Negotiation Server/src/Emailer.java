import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
public class Emailer {
	
 public Emailer(){
	 
 }
	
	   public void sendProdCommitEmailToCreator(String pNID, String userID,
				String userName, String price, String quantity, String shipTime,
				String shipping, String prodName, String creatorName, String creatorID, String email, int state)
	   {    
		   final String username = "m.farshad77@gmail.com";
			final String password = "NOSnumber1";
	 
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
	 
			Session session = Session.getInstance(props,
			  new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			  });
	 
			try {
	 
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("m.farshad77@gmail.com"));
				message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(email));
				message.setSubject("E-Negotiation Notifier: Product Name : " + prodName + " PNID : " + pNID);
				if(state == 1){
				message.setText("You have a concluded a Negotiation and accepted an offer.\n " +
				                "Below are the details:\n" +
						        "PNID: " + pNID +" Product Name: " + prodName + " Price: " + price + " Quatity: " + quantity + 
						        "Shipping : " + shipping + ", ShippingTime: " + shipTime + ", User: " + userName);
				}
				if(state == 2){
					message.setText("Congratualtions, one of your joined Negotiations has been concluded and the Creator has accepted your offer. \n" +
					        "Details of the Negotiation and their agreed upon values are as follows :\n"+
						    "Accepted By: " + creatorName + " \n"	+	
						    "PNID = " + pNID + ", ProdName = " + prodName + ", Price = " + price + ", Shipping = " + shipping + ", ShipTime = " + shipTime + ", Quantity = " + quantity);
				}
				if(state == 3){
				message.setText("One of your joined Negotiations has been concluded but your offer was rejected. \n" +
				        "Details of the Negotiation are as follows :\n"+
					    "Created By: " + creatorName + " \n"	+	
					    "PNID = " + pNID + ", ProdName = " + prodName);
				}
				Transport.send(message);
	 
				System.out.println("Done");
	 
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		}

	public void sendServCommitEmailToCreator(String sNID, String userID,
			String userName, String price, String timeReq, String prodName,
			String creatorName, String creatorID, String email,
			int state) {
		 final String username = "m.farshad77@gmail.com";
			final String password = "NOSnumber1";
	 
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
	 
			Session session = Session.getInstance(props,
			  new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			  });
	 
			try {
	 
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("m.farshad77@gmail.com"));
				message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(email));
				message.setSubject("E-Negotiation Notifier: Service Name : " + prodName + " SNID : " + sNID);
				if(state == 1){
				message.setText("You have a concluded a Negotiation and accepted an offer.\n " +
				                "Below are the details:\n" +
						        "SNID: " + sNID +" Service Name: " + prodName + " Price: " + price + " timeReq: " + timeReq + 
						        ", User: " + userName);
				}
				if(state == 2){
					message.setText("Congratulations, one of your joined Negotiations has been concluded and the Creator has accepted your offer. \n" +
					        "Details of the Negotiation and their agreed upon values are as follows :\n"+
						    "Accepted By: " + creatorName + " \n"	+	
						    "SNID = " + sNID + ", ServName = " + prodName + ", Price = " + price + " timeReq = " + timeReq);
				}
				if(state == 3){
				message.setText("One of your joined Negotiations has been concluded but your offer was rejected. \n" +
				        "Details of the Negotiation are as follows :\n"+
					    "Created By: " + creatorName + " \n"	+	
					    "SNID = " + sNID + ", servName = " + prodName);
				}
				Transport.send(message);
	 
				System.out.println("Done");
	 
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		
	}

	public void createCreatorProdWithdrawNegotiationsNotify(String pNID, String prodName, String creatorName,
			String email) {
		
	 final String username = "m.farshad77@gmail.com";
		final String password = "NOSnumber1";
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("m.farshad77@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(email));
			message.setSubject("E-Negotiation Notifier: Negotiation Cancelled - PNID : " + pNID);
			
			message.setText("One of your joined product Negotiations has been cancelled, below are the details " +
			                "Below are the details:\n" +
					        "PNID: " + pNID +" Product Name: " + prodName + "Creator Name : " + creatorName);
			
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
	}

	public void createCreatorServWithdrawNegotiationsNotify(String sNID,
			String prodName, String creatorName, String email) {
		
		 final String username = "m.farshad77@gmail.com";
			final String password = "NOSnumber1";
	 
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
	 
			Session session = Session.getInstance(props,
			  new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			  });
	 
			try {
	 
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("m.farshad77@gmail.com"));
				message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(email));
				message.setSubject("E-Negotiation Notifier: Negotiation Cancelled - SNID : " + sNID);
				
				message.setText("One of your joined Service Negotiations has been cancelled, below are the details " +
				                "Below are the details:\n" +
						        "sNID: " + sNID +" Service Name: " + prodName + "Creator Name : " + creatorName);
				
				Transport.send(message);
	 
				System.out.println("Done");
	 
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
			
		
	}
	   
	}

