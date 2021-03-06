	import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
	
	import org.xml.sax.SAXException;
	
	import java.io.*;
	
	
	import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
	
	public class MainPage {
		static JFrame mainPage = new JFrame("E-Negotiation System");
		static JPanel parent = new JPanel();
		static ChoicePage choicePage = new ChoicePage();
		LoginPage loginPage = new LoginPage();
		CreateProdNegotPage cProdNegPage = new CreateProdNegotPage();
		CreateServNegotPage cServNegPage = new CreateServNegotPage();
		static MyNegotiationsPage myNegotPage = new MyNegotiationsPage();
		static ProdNegotiationPage prodNegotPage = new ProdNegotiationPage();
		static ServNegotiationPage servNegotPage = new ServNegotiationPage();
		static MyCommitsPage myCPage = new MyCommitsPage();
		RetrievePage retPage = new RetrievePage();
		static MessageBuilder messenger;
		BufferedReader buffR;
		static Client newClient;
		static MessageReader reader;
		static boolean created = false;
	
		static CardLayout cl = new CardLayout();
	
		public MainPage() throws TransformerConfigurationException, ParserConfigurationException {
			
			try {
				reader = new MessageReader();
				String IP = JOptionPane.showInputDialog ( "Please provide an IP to connect to Server, type in 'localhost' to connect to localhost" );
				newClient = new Client(IP);
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        buffR = newClient.buffR;
			try {
				messenger = new MessageBuilder();
			} catch (TransformerConfigurationException
					| ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			// set Card Layout and add panels
			parent.setLayout(cl);
			parent.add(loginPage, "1");
			parent.add(choicePage, "2");
			parent.add(cProdNegPage, "3");
			parent.add(cServNegPage, "4");
			parent.add(myNegotPage, "5");
			parent.add(prodNegotPage, "6");
			parent.add(servNegotPage, "7");
			parent.add(retPage, "8");
			parent.add(myCPage, "9");
	
			// set frame
			mainPage.setSize(470, 300);
			mainPage.add(parent);
			mainPage.setResizable(false);
			mainPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			mainPage.setVisible(true);
			setActionListeners();
			openListenerThread();
		}
	
		public void setActionListeners() {
	
			cl.show(parent, "1");
	
			// actionlistener for login button on action page
			loginPage.btnLogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String enteredT = loginPage.loginField.getText();
					if(enteredT != new String("")){
					loginPage.loginField.setText("");
					String newM = messenger.loginRequestMessage(enteredT);
	                System.out.println (" NEWM : " + newM);
					newClient.sendMessage(newM);
					newM = null;
					mainPage.setSize(450, 350);
					reader.objContain.mainUser.setUserName(enteredT);
					}
					//cl.show(parent, "2");
				}
			});
			choicePage.cProdNegotBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
	
							mainPage.setSize(450, 350);
							cl.show(parent, "3");
						}
					});
			choicePage.cServNegotBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
	
							mainPage.setSize(450, 350);
							cl.show(parent, "4");
						}
					});
			choicePage.myNegotBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
	
					mainPage.setSize(650, 350);
					cl.show(parent, "5");
				}
			});
			choicePage.myCBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
	
					mainPage.setSize(650, 350);
					cl.show(parent, "9");
				}
			});
			cProdNegPage.cancelBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							mainPage.setSize(650,350);
							cl.show(parent, "2");
						}
					});
			/*cProdNegPage.createBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							mainPage.setSize(950, 510);
							cl.show(parent, "6");
						}
					});*/
			cServNegPage.cancelBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							mainPage.setSize(650,350);
							cl.show(parent, "2");
						}
					});
			/*cServNegPage.createBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
	
							mainPage.setSize(950, 510);
							cl.show(parent, "7");
						}
					});*/
			myNegotPage.backBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					mainPage.setSize(650,350);
					cl.show(parent, "2");
				}
			});
			retPage.backBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					mainPage.setSize(650,350);
					cl.show(parent, "2");
				}
			});
			choicePage.retrieveBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					mainPage.setSize(470, 350);
					cl.show(parent, "8");
				}
			});
			choicePage.joinNegotBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.out.println(choicePage.searchResultList.getSelectedValue());
					String whole = choicePage.searchResultList.getSelectedValue().toString();
			        String split[] = whole.split(",");
			        int ID = Integer.parseInt(split[2].trim());
			        String newM = "";
			        if(choicePage.chooserBox.getSelectedItem().equals("Products")){
			        	newM = messenger.retNegotiationInstanceByPNIDReq(Integer.toString(ID));
			        }
			        if(choicePage.chooserBox.getSelectedItem().equals("Services")){
			        	newM = messenger.retNegotiationInstanceBySNIDReq(Integer.toString(ID));
			        }
			        newClient.sendMessage(newM);
				}
			});
			
			prodNegotPage.backToMainBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
						    reader.setCreator(new Negotiations());
						    reader.setUser1(new Negotiations());
						    reader.setUser2(new Negotiations());
						    reader.setUser3(new Negotiations());
						    reader.setUser4(new Negotiations());
						    prodNegotPage.purgeAllFields();
						    mainPage.setSize(650,350);
							cl.show(parent, "2");
						}
					});
			prodNegotPage.usr1CommitBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
				    String newM = messenger.createProdCommitedNotification(reader.objContain.mainNegInst, 1);
				   
				    newClient.sendMessage(newM);
				    creatorProdCommitMessage(reader.objContain.mainNegInst.User1.user.getUserName(),
				    		reader.objContain.mainNegInst.PNID, reader.objContain.mainNegInst.User1.Price, 
				    		reader.objContain.mainNegInst.User1.Shipping, reader.objContain.mainNegInst.User1.shipTime, 
				    		reader.objContain.mainNegInst.User1.Quantity, reader.objContain.mainNegInst.prodOrServName);
				    
				}
			});
			servNegotPage.usr1CommitBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				    String newM = messenger.createServCommitedNotification(reader.objContain.mainNegInst, 1);
					newClient.sendMessage(newM);
					creatorServCommitMessage(reader.objContain.mainNegInst.User1.user.getUserName(), 
							reader.objContain.mainNegInst.SNID, reader.objContain.mainNegInst.User1.Price,
							reader.objContain.mainNegInst.User1.timeReq, reader.objContain.mainNegInst.prodOrServName);
				}
			});
			servNegotPage.usr2CommitBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				    String newM = messenger.createServCommitedNotification(reader.objContain.mainNegInst, 2);
					newClient.sendMessage(newM);
					creatorServCommitMessage(reader.objContain.mainNegInst.User2.user.getUserName(), 
							reader.objContain.mainNegInst.SNID, reader.objContain.mainNegInst.User2.Price,
							reader.objContain.mainNegInst.User2.timeReq, reader.objContain.mainNegInst.prodOrServName);
				    
				}
			});
			servNegotPage.usr3CommitBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				    String newM = messenger.createServCommitedNotification(reader.objContain.mainNegInst, 3);
					newClient.sendMessage(newM);
					creatorServCommitMessage(reader.objContain.mainNegInst.User3.user.getUserName(), 
							reader.objContain.mainNegInst.SNID, reader.objContain.mainNegInst.User3.Price,
							reader.objContain.mainNegInst.User3.timeReq, reader.objContain.mainNegInst.prodOrServName);
				    
				}
			});
			servNegotPage.usr4CommitBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				    String newM = messenger.createServCommitedNotification(reader.objContain.mainNegInst, 4);
					newClient.sendMessage(newM);
					creatorServCommitMessage(reader.objContain.mainNegInst.User4.user.getUserName(), 
							reader.objContain.mainNegInst.SNID, reader.objContain.mainNegInst.User4.Price,
							reader.objContain.mainNegInst.User4.timeReq, reader.objContain.mainNegInst.prodOrServName);
				    
				}
			});
			prodNegotPage.usr2CommitBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				    String newM = messenger.createProdCommitedNotification(reader.objContain.mainNegInst, 2);
					newClient.sendMessage(newM);
					creatorProdCommitMessage(reader.objContain.mainNegInst.User2.user.getUserName(),
				    		reader.objContain.mainNegInst.PNID, reader.objContain.mainNegInst.User2.Price, 
				    		reader.objContain.mainNegInst.User2.Shipping, reader.objContain.mainNegInst.User2.shipTime, 
				    		reader.objContain.mainNegInst.User2.Quantity, reader.objContain.mainNegInst.prodOrServName);
				    
				}
			});
			prodNegotPage.usr3CommitBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				    String newM = messenger.createProdCommitedNotification(reader.objContain.mainNegInst, 3);
					newClient.sendMessage(newM);
					creatorProdCommitMessage(reader.objContain.mainNegInst.User3.user.getUserName(),
				    		reader.objContain.mainNegInst.PNID, reader.objContain.mainNegInst.User3.Price, 
				    		reader.objContain.mainNegInst.User3.Shipping, reader.objContain.mainNegInst.User3.shipTime, 
				    		reader.objContain.mainNegInst.User3.Quantity, reader.objContain.mainNegInst.prodOrServName);
				    
				}
			});
			prodNegotPage.usr4CommitBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				    String newM = messenger.createProdCommitedNotification(reader.objContain.mainNegInst, 4);
					newClient.sendMessage(newM);
					creatorProdCommitMessage(reader.objContain.mainNegInst.User4.user.getUserName(),
				    		reader.objContain.mainNegInst.PNID, reader.objContain.mainNegInst.User4.Price, 
				    		reader.objContain.mainNegInst.User4.Shipping, reader.objContain.mainNegInst.User4.shipTime, 
				    		reader.objContain.mainNegInst.User4.Quantity, reader.objContain.mainNegInst.prodOrServName);
				    
				}
			});
			servNegotPage.backToMainBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							servNegotPage.purgeAllFields();
							mainPage.setSize(650,350);
							cl.show(parent, "2");
						}
					});
			myNegotPage.updateBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String choice = myNegotPage.choserBox.getSelectedItem().toString();
					if(choice.equals(new String("My Created Prod Negotiations"))){
						myNegotPage.setMyProdNegList(reader.objContain.myProdNegotiations);
					}
					if(choice.equals(new String("My Joined Prod Negotiations"))){
						myNegotPage.setMyProdNegList(reader.objContain.myJProdNegotiations);
					}
					if(choice.equals(new String("My Created Serv Negotiations"))){
						myNegotPage.setMyServNegList(reader.objContain.myServNegotiations);
					}
					if(choice.equals(new String("My Joined Serv Negotiations"))){
						myNegotPage.setMyServNegList(reader.objContain.myJServNegotiations);
					}
				}
			});
			myCPage.updateBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String choice = myCPage.choserBox.getSelectedItem().toString();
					if(choice.equals(new String("My Created Prod Negotiations"))){
						myCPage.setMyProdNegList(reader.objContain.myCComProdNegotiations);
					}
					if(choice.equals(new String("My Joined Prod Negotiations"))){
						myCPage.setMyProdNegList(reader.objContain.myJComProdNegotiations);
					}
					if(choice.equals(new String("My Created Serv Negotiations"))){
						myCPage.setMyServNegList(reader.objContain.myCComServNegotiations);
					}
					if(choice.equals(new String("My Joined Serv Negotiations"))){
						myCPage.setMyServNegList(reader.objContain.myJComServNegotiations);
					}
				}
			});
			myCPage.backBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					mainPage.setSize(650,350);
					cl.show(parent, "2");
				}
			});
			cProdNegPage.createBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				String price = cProdNegPage.priceField.getText();
				String shipping = cProdNegPage.shippField.getText();
				String shipTime = cProdNegPage.shippTimeField.getText();
				String quant = cProdNegPage.quantField.getText();
				String BS = " ";
				String prodName = cProdNegPage.prodNameField.getText();
				if(cProdNegPage.buyRadBtn.isSelected()){
					BS = "1";
				    
				}
				if(cProdNegPage.sellRadBtn.isSelected()){
					BS = "2";
				}
				boolean pCheck  = checkInput(price);
				boolean sCheck = checkInput(shipping);
				boolean sTCheck = checkInput(shipTime);
				boolean qCheck = checkInput(quant);
				
				if(!pCheck || !sCheck || !sTCheck || !qCheck){
					JOptionPane.showMessageDialog(MainPage.parent,
		                     "Invalid Input for either Price, Shipping, Shipping Time or Quantity, entered values must be in numeric or decimal format");
				}
				else if(price.equals(new String("")) || shipping.equals(new String("")) || shipTime.equals(new String("")) || quant .equals(new String("")) || (!cProdNegPage.buyRadBtn.isSelected() && !cProdNegPage.sellRadBtn.isSelected()))
				{
					JOptionPane.showMessageDialog(MainPage.parent,
		                     "Some Fields are missing please fill up Price, Shipping, Shipping Time, Quantity and specify whether you are Buying or Selling");
				}
				else{
					String newM = messenger.createProdNMessage(price, shipping, shipTime, BS, quant, Integer.toString(reader.objContain.mainUser.getUserID()), prodName, reader.objContain.mainUser.getUserName());
					System.out.println(newM);
					newClient.sendMessage(newM);
					//newClient.sendMessage(newM);
				}
							
				}
			});
			cServNegPage.createBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
	
					
						String price = cServNegPage.priceField.getText();
						String timeReq = cServNegPage.timeReqField.getText();
						
						
						String BS = " ";
						String prodName = cServNegPage.servNameField.getText();
						if(cServNegPage.buyRadBtn.isSelected()){
							BS = "1";
						    
						}
						if(cServNegPage.sellRadBtn.isSelected()){
							BS = "2";
						}
						boolean pCheck  = checkInput(price);
						boolean tRCheck = checkInput(timeReq);
						
						if(!pCheck || !tRCheck){
							JOptionPane.showMessageDialog(MainPage.parent,
				                     "Invalid Input for either Price, or TimeRequired, entered values must be in numeric or decimal format");
						}
						else if(price.equals(new String("")) ||timeReq.equals(new String("")) || (!cServNegPage.buyRadBtn.isSelected() && !cServNegPage.sellRadBtn.isSelected()))
						{
							JOptionPane.showMessageDialog(MainPage.parent,
				                     "Some Fields are missing please fill up Price, Shipping, Shipping Time, Quantity and specify whether you are Buying or Selling");
						}
						else{
							String newM = messenger.createServNMessage(price, timeReq, BS, Integer.toString(reader.objContain.mainUser.getUserID()), prodName, reader.objContain.mainUser.getUserName());
							System.out.println(newM);
							newClient.sendMessage(newM);
							
							//newClient.sendMessage(newM);
						}
				}
			});
			myNegotPage.enterBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String whole = myNegotPage.myNegList.getSelectedValue().toString();
			        String split[] = whole.split(",");
			        int ID = Integer.parseInt(split[2].trim());
			        System.out.println(ID);
			        if(myNegotPage.currState.equals(new String("PNID"))){
			        	String newM = messenger.retNegotiationInstanceByPNIDReq(Integer.toString(ID));
			        	newClient.sendMessage(newM);
			        }
			        else{
			        	 String newM = messenger.retNegotiationInstanceBySNIDReq(Integer.toString(ID));
					     newClient.sendMessage(newM);	
			        }
			      
				}
			});
			myCPage.enterBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String whole = myCPage.myNegList.getSelectedValue().toString();
			        String split[] = whole.split(",");
			        int ID = Integer.parseInt(split[2].trim());
			        System.out.println(ID);
			        String choice = myCPage.choserBox.getSelectedItem().toString();
			        
			        
			        if(choice.equals("My Created Prod Negotiations")){
			        	String newM = messenger.retCComProdNegotiationReq(Integer.toString(ID));
			        	newClient.sendMessage(newM);
			        }
			        else if(choice.equals("My Created Serv Negotiations"))
			        {
			        	 String newM = messenger.retCComServNegotiationReq(Integer.toString(ID));
					     newClient.sendMessage(newM);	
			        }
			        else if(choice.equals("My Joined Prod Negotiations")){
			        	 String newM = messenger.retJComProdNegotiationReq(Integer.toString(ID));
					     newClient.sendMessage(newM);
			        }
			        else if(choice.equals("My Joined Serv Negotiations")){
			        	 String newM = messenger.retJComServNegotiationReq(Integer.toString(ID));
					     newClient.sendMessage(newM);
			        }
			      
				}
			});
			retPage.retrieveBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String ID = retPage.IDField.getText();
					String newM = " ";
					
					if(retPage.servNegRadBtn.isSelected()){
						newM = messenger.retNegotiationInstanceBySNIDReq(ID);
						System.out.println(newM);
						newClient.sendMessage(newM);
						
						 
					}
					if(retPage.prodNegRadBtn.isSelected()){
						newM = messenger.retNegotiationInstanceByPNIDReq(ID);
						System.out.println(newM);
						newClient.sendMessage(newM);
						
					}
					
				}
			});
			prodNegotPage.creatSendBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String Price = prodNegotPage.creatPriceField.getText();
					String Quantity = prodNegotPage.creatQuantField.getText();
					String Shipping = prodNegotPage.creatShipField.getText();
					String ShippTime = prodNegotPage.creatShippTimeField.getText();
					
					boolean pCheck = checkInput(Price);
					boolean qCheck = checkInput(Quantity);
					boolean sCheck = checkInput(Shipping);
					boolean sTCheck = checkInput(ShippTime);
					if(!pCheck || !qCheck || !sCheck || !sTCheck){
					JOptionPane.showMessageDialog(MainPage.parent,
		                     "Invalid input please ensure all fields are numeric values");
					}
					else if(Double.parseDouble(Price) == 0 || Double.parseDouble(Shipping) == 0 || Double.parseDouble(Quantity) == 0 ||  Double.parseDouble(ShippTime) == 0){
						JOptionPane.showMessageDialog(MainPage.parent,
			                     "Please Ensure All Field Values Are Greater Than 0");	
					}
					else {
						ArrayList<Integer> IDList = new ArrayList<Integer>();
						if(reader.objContain.mainNegInst.User1.user.getUserID() != 0){
							IDList.add(reader.objContain.mainNegInst.User1.user.getUserID());
						}
						if(reader.objContain.mainNegInst.User2.user.getUserID() != 0){
							IDList.add(reader.objContain.mainNegInst.User2.user.getUserID());
						}
						if(reader.objContain.mainNegInst.User3.user.getUserID() != 0){
							IDList.add(reader.objContain.mainNegInst.User3.user.getUserID());
						}
						if(reader.objContain.mainNegInst.User4.user.getUserID() != 0){
							IDList.add(reader.objContain.mainNegInst.User4.user.getUserID());
						}
					
						String newM = messenger. createProdUpdateMessage(Price, Shipping, ShippTime, Quantity, reader.objContain.mainNegInst.creatorID, IDList, reader.objContain.mainNegInst.PNID);
					    newClient.sendMessage(newM);
					}
				}
			});
			prodNegotPage.usr1SendBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String Price = prodNegotPage.usr1PriceField.getText();
					String Quantity = prodNegotPage.usr1QuantField.getText();
					String Shipping = prodNegotPage.usr1ShippField.getText();
					String ShippTime = prodNegotPage.usr1ShippTimeField.getText();
					
					boolean pCheck = checkInput(Price);
					boolean qCheck = checkInput(Quantity);
					boolean sCheck = checkInput(Shipping);
					boolean sTCheck = checkInput(ShippTime);
					if(!pCheck || !qCheck || !sCheck || !sTCheck){
					JOptionPane.showMessageDialog(MainPage.parent,
		                     "Invalid input please ensure all fields are numeric values");
					}
					else if(Double.parseDouble(Price) == 0 || Double.parseDouble(Shipping) == 0 || Double.parseDouble(Quantity) == 0 ||  Double.parseDouble(ShippTime) == 0){
						JOptionPane.showMessageDialog(MainPage.parent,
			                     "Please Ensure All Field Values Are Greater Than 0");	
					}
					else {
						ArrayList<Integer> IDList = new ArrayList<Integer>();
						
							IDList.add(reader.objContain.mainNegInst.creatorID);
						
						if(reader.objContain.mainNegInst.User2.user.getUserID() != 0){
							IDList.add(reader.objContain.mainNegInst.User2.user.getUserID());
						}
						if(reader.objContain.mainNegInst.User3.user.getUserID() != 0){
							IDList.add(reader.objContain.mainNegInst.User3.user.getUserID());
						}
						if(reader.objContain.mainNegInst.User4.user.getUserID() != 0){
							IDList.add(reader.objContain.mainNegInst.User4.user.getUserID());
						}
							
						String newM = messenger. createProdUpdateMessage(Price, Shipping, ShippTime, Quantity, reader.objContain.mainNegInst.User1.user.getUserID(), IDList, reader.objContain.mainNegInst.PNID);
					    newClient.sendMessage(newM);
					    
					}
					reader.objContain.mainNegInst.User1.Price = Double.parseDouble(Price);
					reader.objContain.mainNegInst.User1.Shipping = Double.parseDouble(Shipping);
					reader.objContain.mainNegInst.User1.Quantity = Integer.parseInt(Quantity);
					reader.objContain.mainNegInst.User1.shipTime = Integer.parseInt(ShippTime);
				 updateLowestProdValues();
				}
			});
			prodNegotPage.usr2SendBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String Price = prodNegotPage.usr2PriceField.getText();
					String Quantity = prodNegotPage.usr2QuantField.getText();
					String Shipping = prodNegotPage.usr2ShippField.getText();
					String ShippTime = prodNegotPage.usr2ShippTimeField.getText();
					
					boolean pCheck = checkInput(Price);
					boolean qCheck = checkInput(Quantity);
					boolean sCheck = checkInput(Shipping);
					boolean sTCheck = checkInput(ShippTime);
					if(!pCheck || !qCheck || !sCheck || !sTCheck){
					JOptionPane.showMessageDialog(MainPage.parent,
		                     "Invalid input please ensure all fields are numeric values");
					}
					else if(Double.parseDouble(Price) == 0 || Double.parseDouble(Shipping) == 0 || Double.parseDouble(Quantity) == 0 ||  Double.parseDouble(ShippTime) == 0){
						JOptionPane.showMessageDialog(MainPage.parent,
			                     "Please Ensure All Field Values Are Greater Than 0");	
					}
					else {
						ArrayList<Integer> IDList = new ArrayList<Integer>();
						
							IDList.add(reader.objContain.mainNegInst.creatorID);
						
						if(reader.objContain.mainNegInst.User1.user.getUserID() != 0){
							IDList.add(reader.objContain.mainNegInst.User1.user.getUserID());
						}
						if(reader.objContain.mainNegInst.User3.user.getUserID() != 0){
							IDList.add(reader.objContain.mainNegInst.User3.user.getUserID());
						}
						if(reader.objContain.mainNegInst.User4.user.getUserID() != 0){
							IDList.add(reader.objContain.mainNegInst.User4.user.getUserID());
						}
							
						String newM = messenger. createProdUpdateMessage(Price, Shipping, ShippTime, Quantity, reader.objContain.mainNegInst.User2.user.getUserID(), IDList, reader.objContain.mainNegInst.PNID);
					    newClient.sendMessage(newM);
					}
					reader.objContain.mainNegInst.User2.Price = Double.parseDouble(Price);
					reader.objContain.mainNegInst.User2.Shipping = Double.parseDouble(Shipping);
					reader.objContain.mainNegInst.User2.Quantity = Integer.parseInt(Quantity);
					reader.objContain.mainNegInst.User2.shipTime = Integer.parseInt(ShippTime);
					 updateLowestProdValues();
				}
			});
			prodNegotPage.usr3SendBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String Price = prodNegotPage.usr3PriceField.getText();
					String Quantity = prodNegotPage.usr3QuantField.getText();
					String Shipping = prodNegotPage.usr3ShippField.getText();
					String ShippTime = prodNegotPage.usr3ShippTimeField.getText();
					
					boolean pCheck = checkInput(Price);
					boolean qCheck = checkInput(Quantity);
					boolean sCheck = checkInput(Shipping);
					boolean sTCheck = checkInput(ShippTime);
					if(!pCheck || !qCheck || !sCheck || !sTCheck){
					JOptionPane.showMessageDialog(MainPage.parent,
		                     "Invalid input please ensure all fields are numeric values");
					}
					else if(Double.parseDouble(Price) == 0 || Double.parseDouble(Shipping) == 0 || Double.parseDouble(Quantity) == 0 ||  Double.parseDouble(ShippTime) == 0){
						JOptionPane.showMessageDialog(MainPage.parent,
			                     "Please Ensure All Field Values Are Greater Than 0");	
					}
					else {
						ArrayList<Integer> IDList = new ArrayList<Integer>();
						
							IDList.add(reader.objContain.mainNegInst.creatorID);
						
						if(reader.objContain.mainNegInst.User1.user.getUserID() != 0){
							IDList.add(reader.objContain.mainNegInst.User1.user.getUserID());
						}
						if(reader.objContain.mainNegInst.User2.user.getUserID() != 0){
							IDList.add(reader.objContain.mainNegInst.User2.user.getUserID());
						}
						if(reader.objContain.mainNegInst.User4.user.getUserID() != 0){
							IDList.add(reader.objContain.mainNegInst.User4.user.getUserID());
						}
							
						String newM = messenger. createProdUpdateMessage(Price, Shipping, ShippTime, Quantity, reader.objContain.mainNegInst.User3.user.getUserID(), IDList, reader.objContain.mainNegInst.PNID);
					    newClient.sendMessage(newM);
					}
					reader.objContain.mainNegInst.User3.Price = Double.parseDouble(Price);
					reader.objContain.mainNegInst.User3.Shipping = Double.parseDouble(Shipping);
					reader.objContain.mainNegInst.User3.Quantity = Integer.parseInt(Quantity);
					reader.objContain.mainNegInst.User3.shipTime = Integer.parseInt(ShippTime);
					 updateLowestProdValues();
				}
			});
			prodNegotPage.withdrawBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				String PNID = null;
				ArrayList<Integer> IDList = new ArrayList<Integer>();
				String prodName = reader.objContain.mainNegInst.prodOrServName;
				String creatorName = reader.objContain.mainNegInst.creatorName;
				String newM = null;
				String userID = null;
				PNID = Integer.toString(reader.objContain.mainNegInst.PNID);
				if(reader.objContain.mainNegInst.creatorID == reader.objContain.mainUser.getUserID()){
					
					if(reader.objContain.mainNegInst.User1.user.getUserID() != 0){
						IDList.add(reader.objContain.mainNegInst.User1.user.getUserID());
					}
					if(reader.objContain.mainNegInst.User2.user.getUserID() != 0){
						IDList.add(reader.objContain.mainNegInst.User2.user.getUserID());
					}
					if(reader.objContain.mainNegInst.User3.user.getUserID() != 0){
						IDList.add(reader.objContain.mainNegInst.User3.user.getUserID());
					}
					if(reader.objContain.mainNegInst.User4.user.getUserID() != 0){
						IDList.add(reader.objContain.mainNegInst.User4.user.getUserID());
					}
					newM = messenger.createCreatorProdWithdrawMessage(PNID, prodName, creatorName, IDList);	
					mainPage.setSize(650,350);
					cl.show(parent, "2");
					prodNegotPage.purgeAllFields();
				}
				if(reader.objContain.mainNegInst.creatorID != reader.objContain.mainUser.getUserID()){
					userID = Integer.toString(reader.objContain.mainUser.getUserID());
					IDList.add(reader.objContain.mainNegInst.creatorID);
					if(reader.objContain.mainNegInst.User1.user.getUserID() != 0){
						IDList.add(reader.objContain.mainNegInst.User1.user.getUserID());
					}
					if(reader.objContain.mainNegInst.User2.user.getUserID() != 0){
						IDList.add(reader.objContain.mainNegInst.User2.user.getUserID());
					}
					if(reader.objContain.mainNegInst.User3.user.getUserID() != 0){
						IDList.add(reader.objContain.mainNegInst.User3.user.getUserID());
					}
					if(reader.objContain.mainNegInst.User4.user.getUserID() != 0){
						IDList.add(reader.objContain.mainNegInst.User4.user.getUserID());
					}
				  newM = messenger.createUserWithdraProdNegotiation(PNID, userID, IDList);
				}
				
				newClient.sendMessage(newM);
				}	
			});
			prodNegotPage.btnSendChat.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
			    String chat = null;
			    String senderID = null;
			    String receiverID = null;
			    String PNID = null;
			    chat = reader.objContain.mainUser.getUserName() +": " + prodNegotPage.chatField.getText();
			    prodNegotPage.chatField.setText("");
			    senderID = Integer.toString(reader.objContain.mainUser.getUserID());
			    
			    if(reader.objContain.mainNegInst.User1.user.getUserName().equals(prodNegotPage.chatList.getSelectedValue())){
			    	receiverID = Integer.toString(reader.objContain.mainNegInst.User1.user.getUserID());
			    	prodNegotPage.chatArea.append(chat + "\n");
			    	reader.objContain.mainNegInst.User1.userChat.add(chat);
			    }
			    else if(reader.objContain.mainNegInst.User2.user.getUserName().equals(prodNegotPage.chatList.getSelectedValue())){
			    	receiverID = Integer.toString(reader.objContain.mainNegInst.User2.user.getUserID());
			    	prodNegotPage.chatArea.append(chat + "\n");
			    	reader.objContain.mainNegInst.User2.userChat.add(chat);
			    }
			    else if(reader.objContain.mainNegInst.User3.user.getUserName().equals(prodNegotPage.chatList.getSelectedValue())){
			    	receiverID = Integer.toString(reader.objContain.mainNegInst.User3.user.getUserID());
			    	prodNegotPage.chatArea.append(chat + "\n");
			    	reader.objContain.mainNegInst.User3.userChat.add(chat);
			    }
			    else if(reader.objContain.mainNegInst.User4.user.getUserName().equals(prodNegotPage.chatList.getSelectedValue())){
			    	receiverID = Integer.toString(reader.objContain.mainNegInst.User4.user.getUserID());
			    	prodNegotPage.chatArea.append(chat + "\n");
			    	reader.objContain.mainNegInst.User3.userChat.add(chat);
			    }
			    else if(reader.objContain.mainNegInst.creatorID != reader.objContain.mainUser.getUserID()){
			    	receiverID = Integer.toString(reader.objContain.mainNegInst.creatorID);
			    	prodNegotPage.chatArea.append(chat + "\n");
			    	reader.objContain.mainNegInst.creator.userChat.add(chat);    
			    }
			    
			    PNID = Integer.toString(reader.objContain.mainNegInst.PNID);
			    
			    String newM = messenger.createProdSendChat(PNID, senderID, receiverID, chat);
			    newClient.sendMessage(newM);
			    
				}	
			});
			servNegotPage.sendChatBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
			    String chat = null;
			    String senderID = null;
			    String receiverID = null;
			    String SNID = null;
			    chat = reader.objContain.mainUser.getUserName() +": " + servNegotPage.chatField.getText();
			    servNegotPage.chatField.setText("");
			    senderID = Integer.toString(reader.objContain.mainUser.getUserID());
			    
			    if(reader.objContain.mainNegInst.User1.user.getUserName().equals(servNegotPage.chatList.getSelectedValue())){
			    	receiverID = Integer.toString(reader.objContain.mainNegInst.User1.user.getUserID());
			    	servNegotPage.chatArea.append(chat + "\n");
			    	reader.objContain.mainNegInst.User1.userChat.add(chat);
			    }
			    else if(reader.objContain.mainNegInst.User2.user.getUserName().equals(servNegotPage.chatList.getSelectedValue())){
			    	receiverID = Integer.toString(reader.objContain.mainNegInst.User2.user.getUserID());
			    	servNegotPage.chatArea.append(chat + "\n");
			    	reader.objContain.mainNegInst.User2.userChat.add(chat);
			    }
			    else if(reader.objContain.mainNegInst.User3.user.getUserName().equals(servNegotPage.chatList.getSelectedValue())){
			    	receiverID = Integer.toString(reader.objContain.mainNegInst.User3.user.getUserID());
			    	servNegotPage.chatArea.append(chat + "\n");
			    	reader.objContain.mainNegInst.User3.userChat.add(chat);
			    }
			    else if(reader.objContain.mainNegInst.User4.user.getUserName().equals(servNegotPage.chatList.getSelectedValue())){
			    	receiverID = Integer.toString(reader.objContain.mainNegInst.User4.user.getUserID());
			    	servNegotPage.chatArea.append(chat + "\n");
			    	reader.objContain.mainNegInst.User3.userChat.add(chat);
			    }
			    else if(reader.objContain.mainNegInst.creatorID != reader.objContain.mainUser.getUserID()){
			    	receiverID = Integer.toString(reader.objContain.mainNegInst.creatorID);
			    	servNegotPage.chatArea.append(chat + "\n");
			    	reader.objContain.mainNegInst.creator.userChat.add(chat);    
			    }
			    
			    SNID = Integer.toString(reader.objContain.mainNegInst.SNID);
			    
			    String newM = messenger.createServSendChat(SNID, senderID, receiverID, chat);
			    newClient.sendMessage(newM);
			    
				}	
			});
			prodNegotPage.dataBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
			    String newm = null;
			    newm = messenger.createProdDataMineReq(reader.objContain.mainNegInst.prodOrServName);
			    newClient.sendMessage(newm);
				}		
			});
			servNegotPage.dataBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
			    String newm = null;
			    newm = messenger.createServDataMineReq(reader.objContain.mainNegInst.prodOrServName);
			    newClient.sendMessage(newm);
				}		
			});
			servNegotPage.withdrawBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				String SNID = null;
				ArrayList<Integer> IDList = new ArrayList<Integer>();
				String prodName = reader.objContain.mainNegInst.prodOrServName;
				String creatorName = reader.objContain.mainNegInst.creatorName;
				String newM = null;
				String userID = null;
				SNID = Integer.toString(reader.objContain.mainNegInst.SNID);
				if(reader.objContain.mainNegInst.creatorID == reader.objContain.mainUser.getUserID()){
					
					if(reader.objContain.mainNegInst.User1.user.getUserID() != 0){
						IDList.add(reader.objContain.mainNegInst.User1.user.getUserID());
					}
					if(reader.objContain.mainNegInst.User2.user.getUserID() != 0){
						IDList.add(reader.objContain.mainNegInst.User2.user.getUserID());
					}
					if(reader.objContain.mainNegInst.User3.user.getUserID() != 0){
						IDList.add(reader.objContain.mainNegInst.User3.user.getUserID());
					}
					if(reader.objContain.mainNegInst.User4.user.getUserID() != 0){
						IDList.add(reader.objContain.mainNegInst.User4.user.getUserID());
					}
					newM = messenger.createCreatorServWithdrawMessage(SNID, prodName, creatorName, IDList);					
				}
				if(reader.objContain.mainNegInst.creatorID != reader.objContain.mainUser.getUserID()){
					userID = Integer.toString(reader.objContain.mainUser.getUserID());
					IDList.add(reader.objContain.mainNegInst.creatorID);
					if(reader.objContain.mainNegInst.User1.user.getUserID() != 0){
						IDList.add(reader.objContain.mainNegInst.User1.user.getUserID());
					}
					if(reader.objContain.mainNegInst.User2.user.getUserID() != 0){
						IDList.add(reader.objContain.mainNegInst.User2.user.getUserID());
					}
					if(reader.objContain.mainNegInst.User3.user.getUserID() != 0){
						IDList.add(reader.objContain.mainNegInst.User3.user.getUserID());
					}
					if(reader.objContain.mainNegInst.User4.user.getUserID() != 0){
						IDList.add(reader.objContain.mainNegInst.User4.user.getUserID());
					}
				  newM = messenger.createUserWithdrawServNegotiation(SNID, userID, IDList);
				}
				
				newClient.sendMessage(newM);
				}	
			});
			prodNegotPage.joinBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				String userName = reader.objContain.mainUser.getUserName();
				String userID = Integer.toString(reader.objContain.mainUser.getUserID());
				String PNID = Integer.toString(reader.objContain.mainNegInst.PNID);
				ArrayList<Integer> IDList = new ArrayList<Integer>();
				IDList.add(reader.objContain.mainNegInst.creatorID);
				if(reader.objContain.mainNegInst.User1.user.getUserID() != 0){
					IDList.add(reader.objContain.mainNegInst.User1.user.getUserID());
				}
				if(reader.objContain.mainNegInst.User2.user.getUserID() != 0){
					IDList.add(reader.objContain.mainNegInst.User2.user.getUserID());
				}
				if(reader.objContain.mainNegInst.User3.user.getUserID() != 0){
					IDList.add(reader.objContain.mainNegInst.User3.user.getUserID());
				}
				if(reader.objContain.mainNegInst.User4.user.getUserID() != 0){
					IDList.add(reader.objContain.mainNegInst.User4.user.getUserID());
				}
				String newM = messenger.createProdJoinReq(userName, userID, PNID, IDList);
				
				reader.objContain.myJProdNegotiations.add(new ProdNegInstList(reader.objContain.mainNegInst.prodOrServName, 
														reader.objContain.mainNegInst.creatorID, reader.objContain.mainNegInst.creatorName,
														reader.objContain.mainNegInst.PNID, reader.objContain.mainNegInst.type));
				newClient.sendMessage(newM);
				}	
			});
			prodNegotPage.usr4SendBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String Price = prodNegotPage.usr4PriceField.getText();
					String Quantity = prodNegotPage.usr4QuantField.getText();
					String Shipping = prodNegotPage.usr4ShippField.getText();
					String ShippTime = prodNegotPage.usr4ShippTimeField.getText();
					
					boolean pCheck = checkInput(Price);
					boolean qCheck = checkInput(Quantity);
					boolean sCheck = checkInput(Shipping);
					boolean sTCheck = checkInput(ShippTime);
					if(!pCheck || !qCheck || !sCheck || !sTCheck){
					JOptionPane.showMessageDialog(MainPage.parent,
		                     "Invalid input please ensure all fields are numeric values");
					}
					else if(Double.parseDouble(Price) == 0 || Double.parseDouble(Shipping) == 0 || Double.parseDouble(Quantity) == 0 ||  Double.parseDouble(ShippTime) == 0){
						JOptionPane.showMessageDialog(MainPage.parent,
			                     "Please Ensure All Field Values Are Greater Than 0");	
					}
					else {
						ArrayList<Integer> IDList = new ArrayList<Integer>();
						
							IDList.add(reader.objContain.mainNegInst.creatorID);
						
						if(reader.objContain.mainNegInst.User1.user.getUserID() != 0){
							IDList.add(reader.objContain.mainNegInst.User1.user.getUserID());
						}
						if(reader.objContain.mainNegInst.User2.user.getUserID() != 0){
							IDList.add(reader.objContain.mainNegInst.User2.user.getUserID());
						}
						if(reader.objContain.mainNegInst.User3.user.getUserID() != 0){
							IDList.add(reader.objContain.mainNegInst.User3.user.getUserID());
						}
							
						String newM = messenger. createProdUpdateMessage(Price, Shipping, ShippTime, Quantity, reader.objContain.mainNegInst.User4.user.getUserID(), IDList, reader.objContain.mainNegInst.PNID);
					    newClient.sendMessage(newM);
					}
					reader.objContain.mainNegInst.User4.Price = Double.parseDouble(Price);
					reader.objContain.mainNegInst.User4.Shipping = Double.parseDouble(Shipping);
					reader.objContain.mainNegInst.User4.Quantity = Integer.parseInt(Quantity);
					reader.objContain.mainNegInst.User4.shipTime = Integer.parseInt(ShippTime);
				   updateLowestProdValues();
				}
			});
			servNegotPage.joinButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				String userName = reader.objContain.mainUser.getUserName();
				String userID = Integer.toString(reader.objContain.mainUser.getUserID());
				String SNID = Integer.toString(reader.objContain.mainNegInst.SNID);
				ArrayList<Integer> IDList = new ArrayList<Integer>();
				IDList.add(reader.objContain.mainNegInst.creatorID);
				if(reader.objContain.mainNegInst.User1.user.getUserID() != 0){
					IDList.add(reader.objContain.mainNegInst.User1.user.getUserID());
				}
				if(reader.objContain.mainNegInst.User2.user.getUserID() != 0){
					IDList.add(reader.objContain.mainNegInst.User2.user.getUserID());
				}
				if(reader.objContain.mainNegInst.User3.user.getUserID() != 0){
					IDList.add(reader.objContain.mainNegInst.User3.user.getUserID());
				}
				if(reader.objContain.mainNegInst.User4.user.getUserID() != 0){
					IDList.add(reader.objContain.mainNegInst.User4.user.getUserID());
				}
				String newM = messenger.createServJoinReq(userName, userID, SNID, IDList);
				reader.objContain.myJServNegotiations.add(new ServNegInstList(reader.objContain.mainNegInst.prodOrServName, 
						reader.objContain.mainNegInst.creatorID, reader.objContain.mainNegInst.creatorName,
						reader.objContain.mainNegInst.SNID, reader.objContain.mainNegInst.type));
				newClient.sendMessage(newM);
				
				}	
			});
			servNegotPage.creatSendBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String Price = servNegotPage.creatPriceField.getText();
					String timeReq = servNegotPage.creatTimeReqField.getText();
										
					boolean pCheck = checkInput(Price);
					boolean tRCheck = checkInput(timeReq);
					if(!pCheck || !tRCheck){
					JOptionPane.showMessageDialog(MainPage.parent,
		                     "Invalid input please ensure all fields are numeric values");
					}
					else if(Double.parseDouble(Price) == 0 || Double.parseDouble(timeReq) == 0){
						JOptionPane.showMessageDialog(MainPage.parent,
			                     "Please Ensure All Field Values Are Greater Than 0");	
					}
					else {
						ArrayList<Integer> IDList = new ArrayList<Integer>();
						if(reader.objContain.mainNegInst.User1.user.getUserID() != 0){
							IDList.add(reader.objContain.mainNegInst.User1.user.getUserID());
						}
						if(reader.objContain.mainNegInst.User2.user.getUserID() != 0){
							IDList.add(reader.objContain.mainNegInst.User2.user.getUserID());
						}
						if(reader.objContain.mainNegInst.User3.user.getUserID() != 0){
							IDList.add(reader.objContain.mainNegInst.User3.user.getUserID());
						}
						if(reader.objContain.mainNegInst.User4.user.getUserID() != 0){
							IDList.add(reader.objContain.mainNegInst.User4.user.getUserID());
						}
							
						String newM = messenger. createServUpdateMessage(Price, timeReq, reader.objContain.mainNegInst.creatorID, IDList, reader.objContain.mainNegInst.SNID);
					    newClient.sendMessage(newM);
					}
				}
			});
			servNegotPage.usr1SendBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String Price = servNegotPage.usr1PriceField.getText();
					String timeReq = servNegotPage.usr1TimeReqField.getText();
										
					boolean pCheck = checkInput(Price);
					boolean tRCheck = checkInput(timeReq);
					if(!pCheck || !tRCheck){
					JOptionPane.showMessageDialog(MainPage.parent,
		                     "Invalid input please ensure all fields are numeric values");
					}
					else if(Double.parseDouble(Price) == 0 || Double.parseDouble(timeReq) == 0){
						JOptionPane.showMessageDialog(MainPage.parent,
			                     "Please Ensure All Field Values Are Greater Than 0");	
					}
					else {
						ArrayList<Integer> IDList = new ArrayList<Integer>();
						
							IDList.add(reader.objContain.mainNegInst.creator.user.getUserID());
						
						if(reader.objContain.mainNegInst.User2.user.getUserID() != 0){
							IDList.add(reader.objContain.mainNegInst.User2.user.getUserID());
						}
						if(reader.objContain.mainNegInst.User3.user.getUserID() != 0){
							IDList.add(reader.objContain.mainNegInst.User3.user.getUserID());
						}
						if(reader.objContain.mainNegInst.User4.user.getUserID() != 0){
							IDList.add(reader.objContain.mainNegInst.User4.user.getUserID());
						}
							
						String newM = messenger. createServUpdateMessage(Price, timeReq, reader.objContain.mainNegInst.User1.user.getUserID(), IDList, reader.objContain.mainNegInst.SNID);
					    newClient.sendMessage(newM);
					    reader.objContain.mainNegInst.User1.Price = Double.parseDouble(Price);
					    reader.objContain.mainNegInst.User1.timeReq = Integer.parseInt(timeReq);
					    updateLowestServValues();
					}
				}
			});
			choicePage.searchBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String search = choicePage.searchField.getText();
					String userID = Integer.toString(reader.objContain.mainUser.getUserID());
					String type = "";
					String newM = "";
					if(choicePage.sellRadBtn.isSelected()){
						type = "2";
					}
					else{
						type = "1";
					}
					if(choicePage.chooserBox.getSelectedItem() == "Products"){
						newM = messenger.createSearchProducts(search, userID, type);
					    newClient.sendMessage(newM);
					}
					if(choicePage.chooserBox.getSelectedItem() == "Services"){
						newM = messenger.createSearchServices(search, userID, type);
						newClient.sendMessage(newM);
					}
				
				}
			});
		
			servNegotPage.usr2SendBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String Price = servNegotPage.usr2PriceField.getText();
					String timeReq = servNegotPage.usr2TimeReqField.getText();
										
					boolean pCheck = checkInput(Price);
					boolean tRCheck = checkInput(timeReq);
					if(!pCheck || !tRCheck){
					JOptionPane.showMessageDialog(MainPage.parent,
		                     "Invalid input please ensure all fields are numeric values");
					}
					else if(Double.parseDouble(Price) == 0 || Double.parseDouble(timeReq) == 0){
						JOptionPane.showMessageDialog(MainPage.parent,
			                     "Please Ensure All Field Values Are Greater Than 0");	
					}
					else {
						ArrayList<Integer> IDList = new ArrayList<Integer>();
						
							IDList.add(reader.objContain.mainNegInst.creator.user.getUserID());
						
						if(reader.objContain.mainNegInst.User1.user.getUserID() != 0){
							IDList.add(reader.objContain.mainNegInst.User1.user.getUserID());
						}
						if(reader.objContain.mainNegInst.User3.user.getUserID() != 0){
							IDList.add(reader.objContain.mainNegInst.User3.user.getUserID());
						}
						if(reader.objContain.mainNegInst.User4.user.getUserID() != 0){
							IDList.add(reader.objContain.mainNegInst.User4.user.getUserID());
						}
							
						String newM = messenger. createServUpdateMessage(Price, timeReq, reader.objContain.mainNegInst.User2.user.getUserID(), IDList, reader.objContain.mainNegInst.SNID);
					    newClient.sendMessage(newM);
					    reader.objContain.mainNegInst.User2.Price = Double.parseDouble(Price);
					    reader.objContain.mainNegInst.User2.timeReq = Integer.parseInt(timeReq);
					    updateLowestServValues();
					}
				}
			});
			servNegotPage.usr3SendBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String Price = servNegotPage.usr3PriceField.getText();
					String timeReq = servNegotPage.usr3TimeReqField.getText();
										
					boolean pCheck = checkInput(Price);
					boolean tRCheck = checkInput(timeReq);
					if(!pCheck || !tRCheck){
					JOptionPane.showMessageDialog(MainPage.parent,
		                     "Invalid input please ensure all fields are numeric values");
					}
					else if(Double.parseDouble(Price) == 0 || Double.parseDouble(timeReq) == 0){
						JOptionPane.showMessageDialog(MainPage.parent,
			                     "Please Ensure All Field Values Are Greater Than 0");	
					}
					else {
						ArrayList<Integer> IDList = new ArrayList<Integer>();
						
							IDList.add(reader.objContain.mainNegInst.creator.user.getUserID());
						
						if(reader.objContain.mainNegInst.User2.user.getUserID() != 0){
							IDList.add(reader.objContain.mainNegInst.User2.user.getUserID());
						}
						if(reader.objContain.mainNegInst.User1.user.getUserID() != 0){
							IDList.add(reader.objContain.mainNegInst.User1.user.getUserID());
						}
						if(reader.objContain.mainNegInst.User4.user.getUserID() != 0){
							IDList.add(reader.objContain.mainNegInst.User4.user.getUserID());
						}
							
						String newM = messenger. createServUpdateMessage(Price, timeReq, reader.objContain.mainNegInst.User3.user.getUserID(), IDList, reader.objContain.mainNegInst.SNID);
					    newClient.sendMessage(newM);
					    reader.objContain.mainNegInst.User3.Price = Double.parseDouble(Price);
					    reader.objContain.mainNegInst.User3.timeReq = Integer.parseInt(timeReq);
					    updateLowestServValues();
					}
				}
			});
			servNegotPage.usr4SendBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String Price = servNegotPage.usr4PriceField.getText();
					String timeReq = servNegotPage.usr4TimeReqField.getText();
										
					boolean pCheck = checkInput(Price);
					boolean tRCheck = checkInput(timeReq);
					if(!pCheck || !tRCheck){
					JOptionPane.showMessageDialog(MainPage.parent,
		                     "Invalid input please ensure all fields are numeric values");
					}
					else if(Double.parseDouble(Price) == 0 || Double.parseDouble(timeReq) == 0){
						JOptionPane.showMessageDialog(MainPage.parent,
			                     "Please Ensure All Field Values Are Greater Than 0");	
					}
					else {
						ArrayList<Integer> IDList = new ArrayList<Integer>();
						
							IDList.add(reader.objContain.mainNegInst.creator.user.getUserID());
						
						if(reader.objContain.mainNegInst.User2.user.getUserID() != 0){
							IDList.add(reader.objContain.mainNegInst.User2.user.getUserID());
						}
						if(reader.objContain.mainNegInst.User3.user.getUserID() != 0){
							IDList.add(reader.objContain.mainNegInst.User3.user.getUserID());
						}
						if(reader.objContain.mainNegInst.User1.user.getUserID() != 0){
							IDList.add(reader.objContain.mainNegInst.User1.user.getUserID());
						}
							
						String newM = messenger. createServUpdateMessage(Price, timeReq, reader.objContain.mainNegInst.User4.user.getUserID(), IDList, reader.objContain.mainNegInst.SNID);
					    newClient.sendMessage(newM);
					    reader.objContain.mainNegInst.User4.Price = Double.parseDouble(Price);
					    reader.objContain.mainNegInst.User4.timeReq = Integer.parseInt(timeReq);
					    updateLowestServValues();
					}
				}
			});
		}
		
		
		public class MessageReceiver implements Runnable {
			 
			public void run(){
				
				String MessageReceived = " ";
				try {
					while((MessageReceived = buffR.readLine()) != null){
					System.out.println(MessageReceived);
					if(!MessageReceived.equals(new String(" ")))
					reader.readMessage(MessageReceived);
						
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					System.out.println(MessageReceived + " THIS IS WHAT CAUSED YOUR ERROR");
					e.printStackTrace();
				}
				
				
			}
			
		}
	 
	 
	      public void openListenerThread(){
		  Thread MessageReceiver = new Thread(new MessageReceiver()); 
	      MessageReceiver.start();
	      }
	      public static void loginToMainPage(){
	    	  mainPage.setSize(650,350);
	    	  cl.show(parent, "2");
		      }
		      public boolean checkInput(String x){
		    	  
		    	  if (x == null || x.trim().equals("")) {
		    		 return false; 
		    	  }
		    	  for (int iCount = 0; iCount < x.length(); iCount++) {
		    		  if (!Character.isDigit(x.charAt(iCount)) && !((x.charAt(iCount))==(new Character('.')))) {
		    	       return false;
		    		  }
		    	  
		    	
		      }
		    	  return true;
		}
		      	public static boolean setMyNegotiationList(){
		      		myNegotPage.setMyProdNegList(reader.objContain.myProdNegotiations);
		      		
		      		return true;
		      	}
	
				
				public static void setMainProdNegPageAsCreator() {
					
					prodNegotPage.joinBtn.setEnabled(false);
					
					
					
				}
	
				
	
				public static void setProdNegotiationUsers(int participants) {
					ArrayList<String> userNames = new ArrayList<String>();
					if(reader.objContain.mainNegInst.creatorID != reader.objContain.mainUser.getUserID())
						userNames.add(reader.objContain.mainNegInst.creator.user.getUserName());
						
					prodNegotPage.lblProductName.setText(reader.objContain.mainNegInst.prodOrServName);
					prodNegotPage.lblUseridCreator.setText(reader.objContain.mainNegInst.creator.user.getUserName());
					prodNegotPage.creatPriceField.setText(Double.toString(reader.objContain.mainNegInst.creator.Price));
					prodNegotPage.creatQuantField.setText(Integer.toString(reader.objContain.mainNegInst.creator.Quantity));
					prodNegotPage.creatShipField.setText(Double.toString(reader.objContain.mainNegInst.creator.Shipping));
					prodNegotPage.creatShippTimeField.setText(Integer.toString(reader.objContain.mainNegInst.creator.shipTime));
					if(reader.objContain.mainUser.getUserID() == reader.objContain.mainNegInst.creatorID){
						prodNegotPage.creatPriceField.setEnabled(true);
						prodNegotPage.creatQuantField.setEnabled(true);
						prodNegotPage.creatShipField.setEnabled(true);
						prodNegotPage.creatShippTimeField.setEnabled(true);
						prodNegotPage.creatSendBtn.setEnabled(true);
					}
					
					
					if(participants > 1){
					prodNegotPage.lblUserID1.setText(reader.objContain.mainNegInst.User1.user.getUserName());
					prodNegotPage.usr1PriceField.setText(Double.toString(reader.objContain.mainNegInst.User1.Price));
					prodNegotPage.usr1QuantField.setText(Integer.toString(reader.objContain.mainNegInst.User1.Quantity));
					prodNegotPage.usr1ShippField.setText(Double.toString(reader.objContain.mainNegInst.User1.Shipping));
					prodNegotPage.usr1ShippTimeField.setText(Integer.toString(reader.objContain.mainNegInst.User1.shipTime));
					if(reader.objContain.mainNegInst.creatorID == reader.objContain.mainUser.getUserID())
						userNames.add(reader.objContain.mainNegInst.User1.user.getUserName());
					if(reader.objContain.mainUser.getUserID() == reader.objContain.mainNegInst.User1.user.getUserID()){
						prodNegotPage.usr1PriceField.setEnabled(true);
						prodNegotPage.usr1QuantField.setEnabled(true);
						prodNegotPage.usr1ShippField.setEnabled(true);
						prodNegotPage.usr1ShippTimeField.setEnabled(true);
						prodNegotPage.usr1SendBtn.setEnabled(true);
					}
					}
					if(participants > 2){
				    prodNegotPage.lblUserID2.setText(reader.objContain.mainNegInst.User2.user.getUserName());
					prodNegotPage.usr2PriceField.setText(Double.toString(reader.objContain.mainNegInst.User2.Price));
					prodNegotPage.usr2QuantField.setText(Integer.toString(reader.objContain.mainNegInst.User2.Quantity));
					prodNegotPage.usr2ShippField.setText(Double.toString(reader.objContain.mainNegInst.User2.Shipping));
					prodNegotPage.usr2ShippTimeField.setText(Integer.toString(reader.objContain.mainNegInst.User2.shipTime));
					if(reader.objContain.mainNegInst.creatorID == reader.objContain.mainUser.getUserID())
						userNames.add(reader.objContain.mainNegInst.User2.user.getUserName());
					if(reader.objContain.mainUser.getUserID() == reader.objContain.mainNegInst.User2.user.getUserID()){
						prodNegotPage.usr2PriceField.setEnabled(true);
						prodNegotPage.usr2QuantField.setEnabled(true);
						prodNegotPage.usr2ShippField.setEnabled(true);
						prodNegotPage.usr2ShippTimeField.setEnabled(true);
						prodNegotPage.usr2SendBtn.setEnabled(true);
					}
					}
					if(participants > 3){
					prodNegotPage.lblUserID3.setText(reader.objContain.mainNegInst.User3.user.getUserName());
					prodNegotPage.usr3PriceField.setText(Double.toString(reader.objContain.mainNegInst.User3.Price));
					prodNegotPage.usr3QuantField.setText(Integer.toString(reader.objContain.mainNegInst.User3.Quantity));
					prodNegotPage.usr3ShippField.setText(Double.toString(reader.objContain.mainNegInst.User3.Shipping));
					prodNegotPage.usr3ShippTimeField.setText(Integer.toString(reader.objContain.mainNegInst.User3.shipTime));
					if(reader.objContain.mainNegInst.creatorID == reader.objContain.mainUser.getUserID())
						userNames.add(reader.objContain.mainNegInst.User3.user.getUserName());
					if(reader.objContain.mainUser.getUserID() == reader.objContain.mainNegInst.User3.user.getUserID()){
						prodNegotPage.usr3PriceField.setEnabled(true);
						prodNegotPage.usr3QuantField.setEnabled(true);
						prodNegotPage.usr3ShippField.setEnabled(true);
						prodNegotPage.usr3ShippTimeField.setEnabled(true);
						prodNegotPage.usr3SendBtn.setEnabled(true);
					}
					}
					if(participants > 4){
					prodNegotPage.lblUserID4.setText(reader.objContain.mainNegInst.User4.user.getUserName());
					prodNegotPage.usr4PriceField.setText(Double.toString(reader.objContain.mainNegInst.User4.Price));
					prodNegotPage.usr4QuantField.setText(Integer.toString(reader.objContain.mainNegInst.User4.Quantity));
					prodNegotPage.usr4ShippField.setText(Double.toString(reader.objContain.mainNegInst.User4.Shipping));
					prodNegotPage.usr4ShippTimeField.setText(Integer.toString(reader.objContain.mainNegInst.User4.shipTime));
					prodNegotPage.joinBtn.setEnabled(false);
					if(reader.objContain.mainNegInst.creatorID == reader.objContain.mainUser.getUserID())
						userNames.add(reader.objContain.mainNegInst.User4.user.getUserName());
					if(reader.objContain.mainUser.getUserID() == reader.objContain.mainNegInst.User4.user.getUserID()){
						prodNegotPage.usr4PriceField.setEnabled(true);
						prodNegotPage.usr4QuantField.setEnabled(true);
						prodNegotPage.usr4ShippField.setEnabled(true);
						prodNegotPage.usr4ShippTimeField.setEnabled(true);
						prodNegotPage.usr4SendBtn.setEnabled(true);
					}
					}
					prodNegotPage.disableFields(reader.objContain.mainNegInst, participants, reader.objContain.mainUser);
					updateLowestProdValues();
					prodNegotPage.setUserList(userNames);
					prodNegotPage.disableChat();
					mainPage.setSize(950, 750);
					cl.show(parent, "6");
				}
	
				public static void setServNegotiationUsers(int participants) {
					
					ArrayList<String> userNames = new ArrayList<String>();
					if(reader.objContain.mainNegInst.creatorID != reader.objContain.mainUser.getUserID())
						userNames.add(reader.objContain.mainNegInst.creator.user.getUserName());
					
					servNegotPage.lblServName.setText(reader.objContain.mainNegInst.prodOrServName);
					servNegotPage.lblUseridCreator.setText(reader.objContain.mainNegInst.creator.user.getUserName());
					servNegotPage.creatPriceField.setText(Double.toString(reader.objContain.mainNegInst.creator.Price));
					servNegotPage.creatTimeReqField.setText(Integer.toString(reader.objContain.mainNegInst.creator.timeReq));
				    if(reader.objContain.mainUser.getUserID() == reader.objContain.mainNegInst.creatorID){
				    	servNegotPage.creatPriceField.setEnabled(true);
				    	servNegotPage.creatSendBtn.setEnabled(true);
				    	servNegotPage.creatTimeReqField.setEnabled(true);
				    }
					
					if(participants > 1){
					servNegotPage.lblUserID1.setText(reader.objContain.mainNegInst.User1.user.getUserName());
					servNegotPage.usr1PriceField.setText(Double.toString(reader.objContain.mainNegInst.User1.Price));
					servNegotPage.usr1TimeReqField.setText(Integer.toString(reader.objContain.mainNegInst.User1.timeReq));
					if(reader.objContain.mainNegInst.creatorID == reader.objContain.mainUser.getUserID())
					userNames.add(reader.objContain.mainNegInst.User1.user.getUserName());
					if(reader.objContain.mainUser.getUserID() == reader.objContain.mainNegInst.User1.user.getUserID()){
						servNegotPage.usr1PriceField.setEnabled(true);
						servNegotPage.usr1TimeReqField.setEnabled(true);
						servNegotPage.usr1SendBtn.setEnabled(true);
					}
					}
					
					if(participants > 2){
					servNegotPage.lblUserID2.setText(reader.objContain.mainNegInst.User2.user.getUserName());
					servNegotPage.usr2PriceField.setText(Double.toString(reader.objContain.mainNegInst.User2.Price));
					servNegotPage.usr2TimeReqField.setText(Integer.toString(reader.objContain.mainNegInst.User2.timeReq));
					if(reader.objContain.mainNegInst.creatorID == reader.objContain.mainUser.getUserID())
					userNames.add(reader.objContain.mainNegInst.User2.user.getUserName());
					if(reader.objContain.mainUser.getUserID() == reader.objContain.mainNegInst.User2.user.getUserID()){
						servNegotPage.usr2PriceField.setEnabled(true);
						servNegotPage.usr2TimeReqField.setEnabled(true);
						servNegotPage.usr2SendBtn.setEnabled(true);
					}
					}
					if(participants > 3){
					servNegotPage.lblUserID3.setText(reader.objContain.mainNegInst.User3.user.getUserName());
					servNegotPage.usr3PriceField.setText(Double.toString(reader.objContain.mainNegInst.User3.Price));
					servNegotPage.usr3TimeReqField.setText(Integer.toString(reader.objContain.mainNegInst.User3.timeReq));
					if(reader.objContain.mainNegInst.creatorID == reader.objContain.mainUser.getUserID())
					userNames.add(reader.objContain.mainNegInst.User3.user.getUserName());
					if(reader.objContain.mainUser.getUserID() == reader.objContain.mainNegInst.User3.user.getUserID()){
						servNegotPage.usr3PriceField.setEnabled(true);
						servNegotPage.usr3TimeReqField.setEnabled(true);
						servNegotPage.usr3SendBtn.setEnabled(true);
					}
					}
					if(participants > 4){
					servNegotPage.lblUserID4.setText(reader.objContain.mainNegInst.User4.user.getUserName());
					servNegotPage.usr4PriceField.setText(Double.toString(reader.objContain.mainNegInst.User4.Price));
					servNegotPage.usr4TimeReqField.setText(Integer.toString(reader.objContain.mainNegInst.User4.timeReq));
					if(reader.objContain.mainNegInst.creatorID == reader.objContain.mainUser.getUserID())
					userNames.add(reader.objContain.mainNegInst.User4.user.getUserName());
					if(reader.objContain.mainUser.getUserID() == reader.objContain.mainNegInst.User1.user.getUserID()){
						servNegotPage.usr4PriceField.setEnabled(true);
						servNegotPage.usr4TimeReqField.setEnabled(true);
						servNegotPage.usr4SendBtn.setEnabled(true);
					}
					
					prodNegotPage.joinBtn.setEnabled(false);
					}
					servNegotPage.disableFields(reader.objContain.mainNegInst, participants, reader.objContain.mainUser);
					servNegotPage.setUserList(userNames);
					updateLowestServValues();
					mainPage.setSize(950, 750);
					servNegotPage.disableChat();
					cl.show(parent, "7");
					
				}
	
				public static void disableServJoinButton() {
					// TODO Auto-generated method stub
					servNegotPage.joinButton.setEnabled(false);
				}
				public static void disableProdJoinButton() {
					// TODO Auto-generated method stub
					prodNegotPage.joinBtn.setEnabled(false);
				}
	
				public static void updateProdCreateView() {
					prodNegotPage.creatPriceField.setText(Double.toString(reader.objContain.mainNegInst.creator.Price));
					prodNegotPage.creatQuantField.setText(Integer.toString(reader.objContain.mainNegInst.creator.Quantity));
					prodNegotPage.creatShippTimeField.setText(Integer.toString(reader.objContain.mainNegInst.creator.shipTime));
					prodNegotPage.creatShipField.setText(Double.toString(reader.objContain.mainNegInst.creator.Shipping));
					updateLowestProdValues();
				}
	
				public static void updateProdUser1View() {
					if(reader.objContain.mainNegInst.creatorID == reader.objContain.mainUser.getUserID()){
					prodNegotPage.usr1PriceField.setText(Double.toString(reader.objContain.mainNegInst.User1.Price));
					prodNegotPage.usr1QuantField.setText(Integer.toString(reader.objContain.mainNegInst.User1.Quantity));
					prodNegotPage.usr1ShippTimeField.setText(Integer.toString(reader.objContain.mainNegInst.User1.shipTime));
					prodNegotPage.usr1ShippField.setText(Double.toString(reader.objContain.mainNegInst.User1.Shipping));
					updateLowestProdValues();
					}
				}
	
				public static void updateProdUser2View() {
					if(reader.objContain.mainNegInst.creatorID == reader.objContain.mainUser.getUserID()){
					prodNegotPage.usr2PriceField.setText(Double.toString(reader.objContain.mainNegInst.User2.Price));
					prodNegotPage.usr2QuantField.setText(Integer.toString(reader.objContain.mainNegInst.User2.Quantity));
					prodNegotPage.usr2ShippTimeField.setText(Integer.toString(reader.objContain.mainNegInst.User2.shipTime));
					prodNegotPage.usr2ShippField.setText(Double.toString(reader.objContain.mainNegInst.User2.Shipping));
					updateLowestProdValues();
					}		
				}
	
				public static void updateProdUser3View() {
					if(reader.objContain.mainNegInst.creatorID == reader.objContain.mainUser.getUserID()){
					prodNegotPage.usr3PriceField.setText(Double.toString(reader.objContain.mainNegInst.User3.Price));
					prodNegotPage.usr3QuantField.setText(Integer.toString(reader.objContain.mainNegInst.User3.Quantity));
					prodNegotPage.usr3ShippTimeField.setText(Integer.toString(reader.objContain.mainNegInst.User3.shipTime));
					prodNegotPage.usr3ShippField.setText(Double.toString(reader.objContain.mainNegInst.User3.Shipping));
					updateLowestProdValues();
					}		
				}    
				public static void updateProdUser4View() {
					if(reader.objContain.mainNegInst.creatorID == reader.objContain.mainUser.getUserID()){
					prodNegotPage.usr4PriceField.setText(Double.toString(reader.objContain.mainNegInst.User4.Price));
					prodNegotPage.usr4QuantField.setText(Integer.toString(reader.objContain.mainNegInst.User4.Quantity));
					prodNegotPage.usr4ShippTimeField.setText(Integer.toString(reader.objContain.mainNegInst.User4.shipTime));
					prodNegotPage.usr4ShippField.setText(Double.toString(reader.objContain.mainNegInst.User4.Shipping));
					updateLowestProdValues();
					}		
				}

				public static void updateServCreateView() {
					servNegotPage.creatPriceField.setText(Double.toString(reader.objContain.mainNegInst.creator.Price));
					servNegotPage.creatTimeReqField.setText(Double.toString(reader.objContain.mainNegInst.creator.timeReq));	
					updateLowestServValues();
				}

				public static void updateServUser1View() {
					if(reader.objContain.mainNegInst.creatorID == reader.objContain.mainUser.getUserID()){
					servNegotPage.usr1PriceField.setText(Double.toString(reader.objContain.mainNegInst.User1.Price));
					servNegotPage.usr1TimeReqField.setText(Double.toString(reader.objContain.mainNegInst.User1.timeReq));
					updateLowestServValues();
					}
				}  
				public static void updateServUser2View() {
					if(reader.objContain.mainNegInst.creatorID == reader.objContain.mainUser.getUserID()){
					servNegotPage.usr2PriceField.setText(Double.toString(reader.objContain.mainNegInst.User2.Price));
					servNegotPage.usr2TimeReqField.setText(Double.toString(reader.objContain.mainNegInst.User2.timeReq));
					updateLowestServValues();
					}
				}    
				public static void updateServUser3View() {
					if(reader.objContain.mainNegInst.creatorID == reader.objContain.mainUser.getUserID()){
					servNegotPage.usr3PriceField.setText(Double.toString(reader.objContain.mainNegInst.User3.Price));
					servNegotPage.usr3TimeReqField.setText(Double.toString(reader.objContain.mainNegInst.User3.timeReq));
					updateLowestServValues();
					}
				}  
				public static void updateServUser4View() {
					if(reader.objContain.mainNegInst.creatorID == reader.objContain.mainUser.getUserID()){
					servNegotPage.usr4PriceField.setText(Double.toString(reader.objContain.mainNegInst.User4.Price));
					servNegotPage.usr4TimeReqField.setText(Double.toString(reader.objContain.mainNegInst.User4.timeReq));
					updateLowestServValues();
					}
				}

				public static void setProdSearchArea() {
					System.out.println("WE REACHED " + reader.objContain.mySProdNegotiations.size());
					choicePage.setMyProdNegList(reader.objContain.mySProdNegotiations);
				   
					
				}    

				public static void setServSearchArea() {
					// TODO Auto-generated method stub
					choicePage.setMyServNegList(reader.objContain.mySServNegotiations);
				}

				public static void setUserJoinedProdNegotiation() {
					if(reader.objContain.mainNegInst.User1.user.getUserID() == 0){
						reader.objContain.mainNegInst.User1.user.setUserID(reader.objContain.mainUser.getUserID());
						reader.objContain.mainNegInst.User1.user.setUserName(reader.objContain.mainUser.getUserName());
						reader.objContain.mainNegInst.User1.Price = 0;
						reader.objContain.mainNegInst.User1.Quantity = 0;
						reader.objContain.mainNegInst.User1.Shipping = 0;
						reader.objContain.mainNegInst.User1.shipTime = 0;
						prodNegotPage.usr1PriceField.setEnabled(true);
						prodNegotPage.usr1QuantField.setEnabled(true);
						prodNegotPage.usr1ShippField.setEnabled(true);
						prodNegotPage.usr1ShippTimeField.setEnabled(true);
						prodNegotPage.usr1SendBtn.setEnabled(true);
					}
					else if(reader.objContain.mainNegInst.User2.user.getUserID() == 0){
						reader.objContain.mainNegInst.User2.user.setUserID(reader.objContain.mainUser.getUserID());
						reader.objContain.mainNegInst.User2.user.setUserName(reader.objContain.mainUser.getUserName());
						reader.objContain.mainNegInst.User2.Price = 0;
						reader.objContain.mainNegInst.User2.Quantity = 0;
						reader.objContain.mainNegInst.User2.Shipping = 0;
						reader.objContain.mainNegInst.User2.shipTime = 0;
						prodNegotPage.usr2PriceField.setEnabled(true);
						prodNegotPage.usr2QuantField.setEnabled(true);
						prodNegotPage.usr2ShippField.setEnabled(true);
						prodNegotPage.usr2ShippTimeField.setEnabled(true);
						prodNegotPage.usr2SendBtn.setEnabled(true);
					}
					else if(reader.objContain.mainNegInst.User3.user.getUserID() == 0){
						reader.objContain.mainNegInst.User3.user.setUserID(reader.objContain.mainUser.getUserID());
						reader.objContain.mainNegInst.User3.user.setUserName(reader.objContain.mainUser.getUserName());
						reader.objContain.mainNegInst.User3.Price = 0;
						reader.objContain.mainNegInst.User3.Quantity = 0;
						reader.objContain.mainNegInst.User3.Shipping = 0;
						reader.objContain.mainNegInst.User3.shipTime = 0;
						prodNegotPage.usr3PriceField.setEnabled(true);
						prodNegotPage.usr3QuantField.setEnabled(true);
						prodNegotPage.usr3ShippField.setEnabled(true);
						prodNegotPage.usr3ShippTimeField.setEnabled(true);
						prodNegotPage.usr3SendBtn.setEnabled(true);
					}
					else if(reader.objContain.mainNegInst.User4.user.getUserID() == 0){
						reader.objContain.mainNegInst.User4.user.setUserID(reader.objContain.mainUser.getUserID());
						reader.objContain.mainNegInst.User4.user.setUserName(reader.objContain.mainUser.getUserName());
						reader.objContain.mainNegInst.User4.Price = 0;
						reader.objContain.mainNegInst.User4.Quantity = 0;
						reader.objContain.mainNegInst.User4.Shipping = 0;
						reader.objContain.mainNegInst.User4.shipTime = 0;
						prodNegotPage.usr4PriceField.setEnabled(true);
						prodNegotPage.usr4QuantField.setEnabled(true);
						prodNegotPage.usr4ShippField.setEnabled(true);
						prodNegotPage.usr4ShippTimeField.setEnabled(true);
						prodNegotPage.usr4SendBtn.setEnabled(true);
					}
					reader.objContain.mainNegInst.currentPart++;
					prodNegotPage.joinBtn.setEnabled(false);
					setProdNegotiationUsers(reader.objContain.mainNegInst.currentPart);
				}
				public static void setOtherUserJoinedProdNegotiation(String userName, int userID) {
					if(reader.objContain.mainNegInst.User1.user.getUserID() == 0){
						reader.objContain.mainNegInst.User1.user.setUserID(userID);
						reader.objContain.mainNegInst.User1.user.setUserName(userName);
						reader.objContain.mainNegInst.User1.Price = 0;
						reader.objContain.mainNegInst.User1.Quantity = 0;
						reader.objContain.mainNegInst.User1.Shipping = 0;
						reader.objContain.mainNegInst.User1.shipTime = 0;
					}
					else if(reader.objContain.mainNegInst.User2.user.getUserID() == 0){
						reader.objContain.mainNegInst.User2.user.setUserID(userID);
						reader.objContain.mainNegInst.User2.user.setUserName(userName);
						reader.objContain.mainNegInst.User2.Price = 0;
						reader.objContain.mainNegInst.User2.Quantity = 0;
						reader.objContain.mainNegInst.User2.Shipping = 0;
						reader.objContain.mainNegInst.User2.shipTime = 0;
					}
					else if(reader.objContain.mainNegInst.User3.user.getUserID() == 0){
						reader.objContain.mainNegInst.User3.user.setUserID(userID);
						reader.objContain.mainNegInst.User3.user.setUserName(userName);
						reader.objContain.mainNegInst.User3.Price = 0;
						reader.objContain.mainNegInst.User3.Quantity = 0;
						reader.objContain.mainNegInst.User3.Shipping = 0;
						reader.objContain.mainNegInst.User3.shipTime = 0;
					}
					else if(reader.objContain.mainNegInst.User4.user.getUserID() == 0){
						reader.objContain.mainNegInst.User4.user.setUserID(userID);
						reader.objContain.mainNegInst.User4.user.setUserName(userName);
						reader.objContain.mainNegInst.User4.Price = 0;
						reader.objContain.mainNegInst.User4.Quantity = 0;
						reader.objContain.mainNegInst.User4.Shipping = 0;
						reader.objContain.mainNegInst.User4.shipTime = 0;
					}
					reader.objContain.mainNegInst.currentPart++;
					prodNegotPage.joinBtn.setEnabled(false);
					setProdNegotiationUsers(reader.objContain.mainNegInst.currentPart);
				}

				public static void setUserJoinedServNegotiation() {
					if(reader.objContain.mainNegInst.User1.user.getUserID() == 0){
						reader.objContain.mainNegInst.User1.user.setUserID(reader.objContain.mainUser.getUserID());
						reader.objContain.mainNegInst.User1.user.setUserName(reader.objContain.mainUser.getUserName());
						reader.objContain.mainNegInst.User1.Price = 0;
						reader.objContain.mainNegInst.User1.timeReq = 0;
						servNegotPage.usr1PriceField.setEnabled(true);
						servNegotPage.usr1TimeReqField.setEnabled(true);
						servNegotPage.usr1SendBtn.setEnabled(true);
					}
					else if(reader.objContain.mainNegInst.User2.user.getUserID() == 0){
						reader.objContain.mainNegInst.User2.user.setUserID(reader.objContain.mainUser.getUserID());
						reader.objContain.mainNegInst.User2.user.setUserName(reader.objContain.mainUser.getUserName());
						reader.objContain.mainNegInst.User2.Price = 0;
						reader.objContain.mainNegInst.User2.timeReq = 0;
						servNegotPage.usr2PriceField.setEnabled(true);
						servNegotPage.usr2TimeReqField.setEnabled(true);
						servNegotPage.usr2SendBtn.setEnabled(true);
					}
					else if(reader.objContain.mainNegInst.User3.user.getUserID() == 0){
						reader.objContain.mainNegInst.User3.user.setUserID(reader.objContain.mainUser.getUserID());
						reader.objContain.mainNegInst.User3.user.setUserName(reader.objContain.mainUser.getUserName());
						reader.objContain.mainNegInst.User3.Price = 0;
						reader.objContain.mainNegInst.User3.timeReq = 0;
						servNegotPage.usr3PriceField.setEnabled(true);
						servNegotPage.usr3TimeReqField.setEnabled(true);
						servNegotPage.usr3SendBtn.setEnabled(true);
					}
					else if(reader.objContain.mainNegInst.User4.user.getUserID() == 0){
						reader.objContain.mainNegInst.User4.user.setUserID(reader.objContain.mainUser.getUserID());
						reader.objContain.mainNegInst.User4.user.setUserName(reader.objContain.mainUser.getUserName());
						reader.objContain.mainNegInst.User4.Price = 0;
						reader.objContain.mainNegInst.User4.timeReq = 0;
						servNegotPage.usr4PriceField.setEnabled(true);
						servNegotPage.usr4TimeReqField.setEnabled(true);
						servNegotPage.usr4SendBtn.setEnabled(true);
					}
					reader.objContain.mainNegInst.currentPart++;
					servNegotPage.joinButton.setEnabled(false);
					setServNegotiationUsers(reader.objContain.mainNegInst.currentPart);
					
				}   
				public static void setOtherUserJoinedServNegotiation(String userName, int userID) {
					if(reader.objContain.mainNegInst.User1.user.getUserID() == 0){
						reader.objContain.mainNegInst.User1.user.setUserID(userID);
						reader.objContain.mainNegInst.User1.user.setUserName(userName);
						reader.objContain.mainNegInst.User1.Price = 0;
						reader.objContain.mainNegInst.User1.timeReq = 0;
					}
					else if(reader.objContain.mainNegInst.User2.user.getUserID() == 0){
						reader.objContain.mainNegInst.User2.user.setUserID(userID);
						reader.objContain.mainNegInst.User2.user.setUserName(userName);
						reader.objContain.mainNegInst.User2.Price = 0;
						reader.objContain.mainNegInst.User2.timeReq = 0;
					}
					else if(reader.objContain.mainNegInst.User3.user.getUserID() == 0){
						reader.objContain.mainNegInst.User3.user.setUserID(userID);
						reader.objContain.mainNegInst.User3.user.setUserName(userName);
						reader.objContain.mainNegInst.User3.Price = 0;
						reader.objContain.mainNegInst.User3.timeReq = 0;
					}
					else if(reader.objContain.mainNegInst.User4.user.getUserID() == 0){
						reader.objContain.mainNegInst.User4.user.setUserID(userID);
						reader.objContain.mainNegInst.User4.user.setUserName(userName);
						reader.objContain.mainNegInst.User4.Price = 0;
						reader.objContain.mainNegInst.User4.timeReq = 0;
					}
					reader.objContain.mainNegInst.currentPart++;
					servNegotPage.joinButton.setEnabled(false);
					setServNegotiationUsers(reader.objContain.mainNegInst.currentPart);
					
				}

				public static void handleCommitProdNegotiation(int pNID,
						int userID, String prodName, double price, int quantity, double shipping, int shipTime, String creatorName) {
					/*if(reader.objContain.mainNegInst.creatorID == reader.objContain.mainUser.getUserID()){
						JOptionPane.showMessageDialog(mainPage,
							    "Congratulations, You have successfully concluded a negotiation. \n" +
						        "Details of the Negotiation and their agreed upon values are as follows :\n"+
							    "PNID = " + pNID + " ProdName = " + prodName + " Price = " + price + " Shipping = " + shipping + " ShipTime = " + shipTime + " Quantity = " + quantity + "\n An E-mail with these details has also been sent to you.");
					}
				else*/
					if(reader.objContain.mainUser.getUserID() == userID){
						JOptionPane.showMessageDialog(mainPage,
							    "Congratulations, one of your joined Negotiations has been concluded and the Creator has accepted your offer. \n" +
						        "Details of the Negotiation and their agreed upon values are as follows :\n"+
							    "Accepted By: " + creatorName + " \n"	+	
							    "PNID = " + pNID + " ProdName = " + prodName + " Price = " + price + " Shipping = " + shipping + " ShipTime = " + shipTime + " Quantity = " + quantity + "\n An E-mail with these details has also been sent to you.");
						 reader.objContain.myJComProdNegotiations.add(new ProdNegInstList(prodName, 0, creatorName, pNID, 0));	
				}
					else if (reader.objContain.mainUser.getUserID() != userID){
						JOptionPane.showMessageDialog(mainPage,
							    "One of your joined Negotiations has been concluded but your offer was rejected. \n" +
						        "Details of the Negotiation are as follows :\n"+
							    "Created By: " + creatorName + " \n"	+	
							    "PNID = " + pNID + " ProdName = " + prodName + "\n An E-mail with these details has also been sent to you.");
						    }
					if(reader.objContain.mainNegInst.PNID == pNID){
						mainPage.setSize(650,350);
						cl.show(parent, "2");
						prodNegotPage.purgeAllFields();
					}
				}
					public static void handleCommitServNegotiation(int sNID,
							int userID, String prodName, double price, int timeReq, String creatorName) {
						
						if(reader.objContain.mainUser.getUserID() == userID){
							JOptionPane.showMessageDialog(mainPage,
								    "Congratulations, one of your joined Negotiations has been concluded and the Creator has accepted your offer. \n" +
							        "Details of the Negotiation and their agreed upon values are as follows :\n"+
								    "Accepted By: " + creatorName + " \n"	+	
								    "SNID = " + sNID + " ServName = " + prodName + " Price = " + price + " timeReq = " + timeReq + "\n An E-mail with these details has also been sent to you.");
						           reader.objContain.myJComServNegotiations.add(new ServNegInstList(prodName, 0, creatorName, sNID, 0));
						}
						else if (reader.objContain.mainUser.getUserID() != userID){
							JOptionPane.showMessageDialog(mainPage,
								    "One of your joined Negotiations has been concluded but your offer was rejected. \n" +
							        "Details of the Negotiation are as follows :\n"+
								    "Created By: " + creatorName + " \n"	+	
								    "SNID = " + sNID + " ServName = " + prodName + "\n An E-mail with these details has also been sent to you.");
							    }
						if(reader.objContain.mainNegInst.SNID == sNID){
							mainPage.setSize(650,350);
							cl.show(parent, "2");
							servNegotPage.purgeAllFields();
						}
				}

					public static void handleProdCreatorWithdraw(int pNID,
							String prodName, String creatorName) {
						if(reader.objContain.mainNegInst.PNID == pNID){
							reader.objContain.mainNegInst = new NegotiationInstance();
							prodNegotPage.purgeAllFields();
							mainPage.setSize(650,350);
							cl.show(parent, "2");
						}
						JOptionPane.showMessageDialog(mainPage,
							    "One of your joined Negotiations has been cancellled. \n" +
						        "Details of the Negotiation are as follows :\n"+
							    "Created By: " + creatorName + " \n"	+	
							    "PNID = " + pNID + " ProdName = " + prodName + "\n An E-mail with these details has also been sent to you.");
						    }

					public static void handleServCreatorWithdraw(int sNID,
							String prodName, String creatorName) {
					
							if(reader.objContain.mainNegInst.SNID == sNID){
								reader.objContain.mainNegInst = new NegotiationInstance();
								servNegotPage.purgeAllFields();
								mainPage.setSize(650,350);
								cl.show(parent, "2");
							}
							JOptionPane.showMessageDialog(mainPage,
								    "One of your joined Negotiations has been cancellled. \n" +
							        "Details of the Negotiation are as follows :\n"+
								    "Created By: " + creatorName + " \n"	+	
								    "SNID = " + sNID + " Service Name = " + prodName + "\n An E-mail with these details has also been sent to you.");
							    }

					public static void handleProdUserWithdraw(int pNID,
							String userID) {
						int userID1 = Integer.parseInt(userID);
						if( userID1 == reader.objContain.mainUser.getUserID()){
							reader.objContain.mainNegInst = new NegotiationInstance();
							prodNegotPage.purgeAllFields();
							mainPage.setSize(650,350);
							cl.show(parent, "2");
							JOptionPane.showMessageDialog(mainPage,
								    "You have successfully withdrawn from this product negotiation.");
						}
						if(pNID == reader.objContain.mainNegInst.PNID ){
							if(userID1 == reader.objContain.mainNegInst.User1.user.getUserID()){
								reader.objContain.mainNegInst.User1 = new Negotiations();
								prodNegotPage.usr1PriceField.setText("");
								prodNegotPage.usr1ShippField.setText("");
								prodNegotPage.usr1QuantField.setText("");
								prodNegotPage.usr1ShippTimeField.setText("");
								prodNegotPage.lblUserID1.setText("");
								prodNegotPage.usr1CommitBtn.setEnabled(false);
								prodNegotPage.usr1SendBtn.setEnabled(false);
							}
							if(userID1 == reader.objContain.mainNegInst.User2.user.getUserID()){
								reader.objContain.mainNegInst.User2 = new Negotiations();
								prodNegotPage.usr2PriceField.setText("");
								prodNegotPage.usr2ShippField.setText("");
								prodNegotPage.usr2QuantField.setText("");
								prodNegotPage.usr2ShippTimeField.setText("");
								prodNegotPage.lblUserID2.setText("");
								prodNegotPage.usr2CommitBtn.setEnabled(false);
								prodNegotPage.usr2SendBtn.setEnabled(false);
							}
							if(userID1 == reader.objContain.mainNegInst.User3.user.getUserID()){
								reader.objContain.mainNegInst.User3 = new Negotiations();
								prodNegotPage.usr3PriceField.setText("");
								prodNegotPage.usr3ShippField.setText("");
								prodNegotPage.usr3QuantField.setText("");
								prodNegotPage.usr3ShippTimeField.setText("");
								prodNegotPage.lblUserID3.setText("");
								prodNegotPage.usr3CommitBtn.setEnabled(false);
								prodNegotPage.usr3SendBtn.setEnabled(false);
							}
							if(userID1 == reader.objContain.mainNegInst.User4.user.getUserID()){
								reader.objContain.mainNegInst.User4 = new Negotiations();
								prodNegotPage.usr4PriceField.setText("");
								prodNegotPage.usr4ShippField.setText("");
								prodNegotPage.usr4QuantField.setText("");
								prodNegotPage.usr4ShippTimeField.setText("");
								prodNegotPage.lblUserID4.setText("");
								prodNegotPage.usr4CommitBtn.setEnabled(false);
								prodNegotPage.usr4SendBtn.setEnabled(false);
							}
						}
					}

					public static void handleServUserWithdraw(int sNID,
							String userID) {
						int userID1 = Integer.parseInt(userID);
						if( userID1 == reader.objContain.mainUser.getUserID()){
							reader.objContain.mainNegInst = new NegotiationInstance();
							servNegotPage.purgeAllFields();
							mainPage.setSize(650,350);
							cl.show(parent, "2");
							JOptionPane.showMessageDialog(mainPage,
								    "You have successfully withdrawn from this product negotiation.");
						}
						if(sNID == reader.objContain.mainNegInst.SNID ){
							if(userID1 == reader.objContain.mainNegInst.User1.user.getUserID()){
								reader.objContain.mainNegInst.User1 = new Negotiations();
								servNegotPage.usr1PriceField.setText("");
								servNegotPage.usr1TimeReqField.setText("");
								servNegotPage.lblUserID1.setText("");
								servNegotPage.usr1CommitBtn.setEnabled(false);
								servNegotPage.usr1SendBtn.setEnabled(false);
							}
							if(userID1 == reader.objContain.mainNegInst.User2.user.getUserID()){
								reader.objContain.mainNegInst.User2 = new Negotiations();
								servNegotPage.usr2PriceField.setText("");
								servNegotPage.usr2TimeReqField.setText("");
								servNegotPage.lblUserID2.setText("");
								servNegotPage.usr2CommitBtn.setEnabled(false);
								servNegotPage.usr2SendBtn.setEnabled(false);
							}
							if(userID1 == reader.objContain.mainNegInst.User3.user.getUserID()){
								
								reader.objContain.mainNegInst.User3 = new Negotiations();
								servNegotPage.usr3PriceField.setText("");
								servNegotPage.usr3TimeReqField.setText("");
								servNegotPage.lblUserID3.setText("");
								servNegotPage.usr3CommitBtn.setEnabled(false);
								servNegotPage.usr3SendBtn.setEnabled(false);
							}
							if(userID1 == reader.objContain.mainNegInst.User4.user.getUserID()){
								reader.objContain.mainNegInst.User4 = new Negotiations();
								servNegotPage.usr4PriceField.setText("");
								servNegotPage.usr4TimeReqField.setText("");
								servNegotPage.lblUserID4.setText("");
								servNegotPage.usr4CommitBtn.setEnabled(false);
								servNegotPage.usr4SendBtn.setEnabled(false);
							}
						}
						
					}
						
			public static void updateLowestServValues(){
				double price = 1000000000000000000000.0;
				int timeReq = 100000000;
			    System.out.println("THIS IS USER 3 ID " + reader.objContain.mainNegInst.User3.user.getUserID());
				if(reader.objContain.mainNegInst.User1.user.getUserID() != 0){
					if(reader.objContain.mainNegInst.User1.Price != 0)
						price = reader.objContain.mainNegInst.User1.Price;
					if(reader.objContain.mainNegInst.User1.timeReq != 0)
					    timeReq = reader.objContain.mainNegInst.User1.timeReq;
					
				}
				if(reader.objContain.mainNegInst.User2.user.getUserID() != 0){
					if(reader.objContain.mainNegInst.User2.Price != 0 && reader.objContain.mainNegInst.User2.Price < price)
						price = reader.objContain.mainNegInst.User2.Price;
					if(reader.objContain.mainNegInst.User2.timeReq != 0 && reader.objContain.mainNegInst.User2.timeReq < timeReq)
						timeReq = reader.objContain.mainNegInst.User2.timeReq;
					
				
				}
				if(reader.objContain.mainNegInst.User3.user.getUserID() != 0){
					if(reader.objContain.mainNegInst.User3.Price != 0 && reader.objContain.mainNegInst.User3.Price < price)
						price = reader.objContain.mainNegInst.User3.Price;
					if(reader.objContain.mainNegInst.User3.timeReq != 0 && reader.objContain.mainNegInst.User3.timeReq < timeReq)
						timeReq = reader.objContain.mainNegInst.User3.timeReq;
					
				}
				if(reader.objContain.mainNegInst.User4.user.getUserID() != 0){
					if(reader.objContain.mainNegInst.User4.Price != 0 && reader.objContain.mainNegInst.User4.Price < price)
						price = reader.objContain.mainNegInst.User4.Price;
					if(reader.objContain.mainNegInst.User4.timeReq != 0 && reader.objContain.mainNegInst.User2.timeReq < timeReq)
						timeReq = reader.objContain.mainNegInst.User4.timeReq;
					
				}
				if(price != 1000000000000000000000.0)
					servNegotPage.lowCurrPriceField.setText(Double.toString(price));
				if(timeReq != 100000000 )
					servNegotPage.lowCurrTimeReqField.setText(Integer.toString(timeReq));
			}
			
			public static void updateLowestProdValues(){
				double price = 1000000000000000000000.0;
				double shipping = 10000000000000000000.0;
				int shippTime = 100000000;
			    System.out.println("THIS IS USER 3 ID " + reader.objContain.mainNegInst.User3.user.getUserID());
				if(reader.objContain.mainNegInst.User1.user.getUserID() != 0){
					if(reader.objContain.mainNegInst.User1.Price != 0)
						price = reader.objContain.mainNegInst.User1.Price;
					if(reader.objContain.mainNegInst.User1.Shipping != 0)
						shipping = reader.objContain.mainNegInst.User1.Shipping;
					if(reader.objContain.mainNegInst.User1.shipTime != 0)
						shippTime = reader.objContain.mainNegInst.User1.shipTime;
					
				}
				if(reader.objContain.mainNegInst.User2.user.getUserID() != 0){
					if(reader.objContain.mainNegInst.User2.Price != 0 && reader.objContain.mainNegInst.User2.Price < price)
						price = reader.objContain.mainNegInst.User2.Price;
					if(reader.objContain.mainNegInst.User2.Shipping != 0 && reader.objContain.mainNegInst.User2.Shipping < shipping)
						shipping = reader.objContain.mainNegInst.User2.Shipping;
					if(reader.objContain.mainNegInst.User2.shipTime != 0 && reader.objContain.mainNegInst.User2.shipTime < shippTime)
						shippTime = reader.objContain.mainNegInst.User2.shipTime;
				
				}
				if(reader.objContain.mainNegInst.User3.user.getUserID() != 0){
					if(reader.objContain.mainNegInst.User3.Price != 0 && reader.objContain.mainNegInst.User3.Price < price)
						price = reader.objContain.mainNegInst.User3.Price;
					if(reader.objContain.mainNegInst.User3.Shipping != 0 && reader.objContain.mainNegInst.User3.Shipping < shipping)
						shipping = reader.objContain.mainNegInst.User3.Shipping;
					if(reader.objContain.mainNegInst.User3.shipTime != 0 && reader.objContain.mainNegInst.User3.shipTime < shippTime)
						shippTime = reader.objContain.mainNegInst.User3.shipTime;
					
				}
				if(reader.objContain.mainNegInst.User4.user.getUserID() != 0){
					if(reader.objContain.mainNegInst.User4.Price != 0 && reader.objContain.mainNegInst.User4.Price < price)
						price = reader.objContain.mainNegInst.User4.Price;
					if(reader.objContain.mainNegInst.User4.Shipping != 0 && reader.objContain.mainNegInst.User4.Shipping < shipping)
						shipping = reader.objContain.mainNegInst.User4.Shipping;
					if(reader.objContain.mainNegInst.User4.shipTime != 0 && reader.objContain.mainNegInst.User4.shipTime < shippTime)
						shippTime = reader.objContain.mainNegInst.User4.shipTime;
					
				}
				if(price != 1000000000000000000000.0)
					prodNegotPage.lowCurrPriceField.setText(Double.toString(price));
				if(shipping != 10000000000000000000.0)
					prodNegotPage.lowCurrShippField.setText(Double.toString(shipping));
				if(shippTime != 100000000 )
					prodNegotPage.lowCurrShippTimeField.setText(Integer.toString(shippTime));
			}

			public static void setChatArea(int q) {
				if(reader.objContain.mainNegInst.PNID != 0){
				String userName = (String) prodNegotPage.chatList.getSelectedValue();
				
				if(userName.equals(reader.objContain.mainNegInst.creatorName)){
					prodNegotPage.chatArea.setText("");
					if(reader.objContain.mainNegInst.creator.userChat.size() > 1000){
						reader.objContain.mainNegInst.creator.userChat.subList(0, 500).clear();
					}
					for(int i = 0; i < reader.objContain.mainNegInst.creator.userChat.size(); i++){
						prodNegotPage.chatArea.append(reader.objContain.mainNegInst.creator.userChat.get(i) + "\n");
					}
				}
				else if(userName.equals(reader.objContain.mainNegInst.User1.user.getUserName())){
					
					if(reader.objContain.mainNegInst.User1.userChat.size() > 1000){
						reader.objContain.mainNegInst.User1.userChat.subList(0, 500).clear();
					}
					prodNegotPage.chatArea.setText("");
					for(int i = 0; i < reader.objContain.mainNegInst.User1.userChat.size(); i++){
			
						prodNegotPage.chatArea.append(reader.objContain.mainNegInst.User1.userChat.get(i) + "\n");
					}
				}
				else if(userName.equals(reader.objContain.mainNegInst.User2.user.getUserName())){
					prodNegotPage.chatArea.setText("");
					if(reader.objContain.mainNegInst.User2.userChat.size() > 1000){
						reader.objContain.mainNegInst.User2.userChat.subList(0, 500).clear();
					}
					for(int i = 0; i < reader.objContain.mainNegInst.User2.userChat.size(); i++){
						prodNegotPage.chatArea.append(reader.objContain.mainNegInst.User2.userChat.get(i) + "\n");
					}
				}
				else if(userName.equals(reader.objContain.mainNegInst.User3.user.getUserName())){
					prodNegotPage.chatArea.setText("");
					if(reader.objContain.mainNegInst.User3.userChat.size() > 1000){
						reader.objContain.mainNegInst.User3.userChat.subList(0, 500).clear();
					}
					
					for(int i = 0; i < reader.objContain.mainNegInst.User3.userChat.size(); i++){
						prodNegotPage.chatArea.append(reader.objContain.mainNegInst.User3.userChat.get(i) + "\n");
					}
				}
				else if(userName.equals(reader.objContain.mainNegInst.User4.user.getUserName())){
					prodNegotPage.chatArea.setText("");
					if(reader.objContain.mainNegInst.User4.userChat.size() > 1000){
						reader.objContain.mainNegInst.User4.userChat.subList(0, 500).clear();
					}
					for(int i = 0; i < reader.objContain.mainNegInst.User4.userChat.size(); i++){
						prodNegotPage.chatArea.append(reader.objContain.mainNegInst.User4.userChat.get(i) + "\n");
					}
				}
				}
				else{
					String userName = (String) servNegotPage.chatList.getSelectedValue();
					
					if(userName.equals(reader.objContain.mainNegInst.creatorName)){
						servNegotPage.chatArea.setText("");
						if(reader.objContain.mainNegInst.creator.userChat.size() > 1000){
							reader.objContain.mainNegInst.creator.userChat.subList(0, 500).clear();
						}
						for(int i = 0; i < reader.objContain.mainNegInst.creator.userChat.size(); i++){
							servNegotPage.chatArea.append(reader.objContain.mainNegInst.creator.userChat.get(i) + "\n");
						}
					}
					else if(userName.equals(reader.objContain.mainNegInst.User1.user.getUserName())){
						if(reader.objContain.mainNegInst.User1.userChat.size() > 1000){
							reader.objContain.mainNegInst.User1.userChat.subList(0, 500).clear();
						}
						servNegotPage.chatArea.setText("");
						for(int i = 0; i < reader.objContain.mainNegInst.User1.userChat.size(); i++){
							servNegotPage.chatArea.append(reader.objContain.mainNegInst.User1.userChat.get(i) + "\n");
						}
					}
					else if(userName.equals(reader.objContain.mainNegInst.User2.user.getUserName())){
						servNegotPage.chatArea.setText("");
						if(reader.objContain.mainNegInst.User2.userChat.size() > 1000){
							reader.objContain.mainNegInst.User2.userChat.subList(0, 500).clear();
						}
						for(int i = 0; i < reader.objContain.mainNegInst.User2.userChat.size(); i++){
							servNegotPage.chatArea.append(reader.objContain.mainNegInst.User2.userChat.get(i) + "\n");
						}
					}
					else if(userName.equals(reader.objContain.mainNegInst.User3.user.getUserName())){
						servNegotPage.chatArea.setText("");
						if(reader.objContain.mainNegInst.User3.userChat.size() > 1000){
							reader.objContain.mainNegInst.User3.userChat.subList(0, 500).clear();
						}
						
						for(int i = 0; i < reader.objContain.mainNegInst.User3.userChat.size(); i++){
							servNegotPage.chatArea.append(reader.objContain.mainNegInst.User3.userChat.get(i) + "\n");
						}
					}
					else if(userName.equals(reader.objContain.mainNegInst.User4.user.getUserName())){
						servNegotPage.chatArea.setText("");
						if(reader.objContain.mainNegInst.User4.userChat.size() > 1000){
							reader.objContain.mainNegInst.User4.userChat.subList(0, 500).clear();
						}
						for(int i = 0; i < reader.objContain.mainNegInst.User4.userChat.size(); i++){
							servNegotPage.chatArea.append(reader.objContain.mainNegInst.User4.userChat.get(i) + "\n");
						}
					}
					
				}
				
			}
           public void creatorProdCommitMessage(String userName, int PNID, double Price, double Shipping, double ShippTime, int Quantity, String prodName){
        	   JOptionPane.showMessageDialog(MainPage.parent,
	                     "Congratulations, you have successfully concluded a Negotiations, below is the information, \n" +
	                     "PNID :" + PNID +" ProdName :" + prodName + "\n"+
	                     "Commited To :" + userName + "\n" +
	                     "Price :" + Price + " ,Shipping :" + Shipping + ",Shipping Time :" + ShippTime + " Quantity :" + Quantity);
        	   mainPage.setSize(650,350);
        	   cl.show(parent, "2");
        	   prodNegotPage.purgeAllFields();
           }
           public void creatorServCommitMessage(String userName, int SNID, double Price, int timeReq, String servName){
        	   JOptionPane.showMessageDialog(MainPage.parent,
	                     "Congratulations, you have successfully concluded a Negotiations, below is the information, \n" +
	                     "SNID :" + SNID +" ProdName :" + servName + "\n"+
	                     "Commited To :" + userName + "\n" +
	                     "Price :" + Price + " ,Time Required :" + timeReq);
        	   mainPage.setSize(650,350);
        	   cl.show(parent, "2");
        	   prodNegotPage.purgeAllFields();
           }
			public static void showProdDataMine(double price, double shipping,
					int quantity, int shipTime) {
				JOptionPane.showMessageDialog(MainPage.parent,
	                     "Here are the data mined values for the product: " + reader.objContain.mainNegInst.prodOrServName +
	                     "\n Price :" + price + " Shipping :" + shipping + " Quantity :" + quantity + " ShippingTime :" + shipTime +
	                     "\n All values are averages of previously completed transactions.");
				
			}
			public static void showServDataMine(double price, int timeReq) {
				JOptionPane.showMessageDialog(MainPage.parent,
	                     "Here are the data mined values for the service: " + reader.objContain.mainNegInst.prodOrServName +
	                     "\n Price :" + price + " Time Required :" + timeReq +
	                     "\n All values are averages of previously completed transactions.");
				
			}

	       public static void appendChat(String msg, boolean check){
	    	   if(check == true)
	    	   prodNegotPage.chatArea.append(msg + "\n");
	    	   else
	    	   servNegotPage.chatArea.append(msg + "\n");   
	       }

		public static void handleProdChat(String chat, int senderID,
				int receiverID) {
			if(reader.objContain.mainNegInst.creatorID == senderID){
				appendChat(chat, true);
				reader.objContain.mainNegInst.creator.userChat.add(chat);
				
			}
			else{
			if(reader.objContain.mainNegInst.User1.user.getUserID() == senderID){
				reader.objContain.mainNegInst.User1.userChat.add(chat);
				for(int i = 0; i < reader.objContain.mainNegInst.User1.userChat.size(); i++ ){
					System.out.println(reader.objContain.mainNegInst.User1.userChat.get(i));
				}
				if(!prodNegotPage.chatList.isSelectionEmpty()){
				if(prodNegotPage.chatList.getSelectedValue().equals(reader.objContain.mainNegInst.User1.user.getUserName())){
					appendChat(chat, true);
				}}
			}
			else if(reader.objContain.mainNegInst.User2.user.getUserID() == senderID){
				reader.objContain.mainNegInst.User2.userChat.add(chat);
				if(!prodNegotPage.chatList.isSelectionEmpty()){
				if(prodNegotPage.chatList.getSelectedValue().equals(reader.objContain.mainNegInst.User2.user.getUserName())){
					appendChat(chat, true);
				}
				}
			}
			else if(reader.objContain.mainNegInst.User3.user.getUserID() == senderID){
				reader.objContain.mainNegInst.User3.userChat.add(chat);
				if(!prodNegotPage.chatList.isSelectionEmpty()){
				if(prodNegotPage.chatList.getSelectedValue().equals(reader.objContain.mainNegInst.User3.user.getUserName())){
					appendChat(chat, true);
				}
				}
			else if(reader.objContain.mainNegInst.User4.user.getUserID() == senderID){
				reader.objContain.mainNegInst.User4.userChat.add(chat);
				if(!prodNegotPage.chatList.isSelectionEmpty()){
				if(prodNegotPage.chatList.getSelectedValue().equals(reader.objContain.mainNegInst.User4.user.getUserName())){
					appendChat(chat, true);
				}
				}
				}
			}
			}
			
		}
		public static void handleServChat(String chat, int senderID,
				int receiverID) {
			if(reader.objContain.mainNegInst.creatorID == senderID){
				appendChat(chat, false);
				reader.objContain.mainNegInst.creator.userChat.add(chat);
				
			}
			else{
			if(reader.objContain.mainNegInst.User1.user.getUserID() == senderID){
				reader.objContain.mainNegInst.User1.userChat.add(chat);
				for(int i = 0; i < reader.objContain.mainNegInst.User1.userChat.size(); i++ ){
					System.out.println(reader.objContain.mainNegInst.User1.userChat.get(i));
				}
				if(!servNegotPage.chatList.isSelectionEmpty()){
				if(servNegotPage.chatList.getSelectedValue().equals(reader.objContain.mainNegInst.User1.user.getUserName())){
					appendChat(chat, false);
				}
				}
			}
			else if(reader.objContain.mainNegInst.User2.user.getUserID() == senderID){
				reader.objContain.mainNegInst.User2.userChat.add(chat);
				if(!servNegotPage.chatList.isSelectionEmpty()){
				if(servNegotPage.chatList.getSelectedValue().equals(reader.objContain.mainNegInst.User2.user.getUserName())){
					appendChat(chat, false);
				}}
			}
			else if(reader.objContain.mainNegInst.User3.user.getUserID() == senderID){
				reader.objContain.mainNegInst.User3.userChat.add(chat);
				if(!servNegotPage.chatList.isSelectionEmpty()){
				if(servNegotPage.chatList.getSelectedValue().equals(reader.objContain.mainNegInst.User3.user.getUserName())){
					appendChat(chat, false);
				}}
			else if(reader.objContain.mainNegInst.User4.user.getUserID() == senderID){
				reader.objContain.mainNegInst.User4.userChat.add(chat);
				if(!servNegotPage.chatList.isSelectionEmpty()){
				if(servNegotPage.chatList.getSelectedValue().equals(reader.objContain.mainNegInst.User4.user.getUserName())){
					appendChat(chat, false);
				}}
				}
			}
			}
			
		}
		public static void createServNegotiationNotice(int SNID){
			JOptionPane.showMessageDialog(MainPage.parent,
                    "Service Negotiation successfully created,\n please note down its ID for future use: " + SNID);
			String newM = messenger.retNegotiationInstanceBySNIDReq(Integer.toString(SNID));
			newClient.sendMessage(newM);
			
			created = true;
    		
			
		}
		public static void createProdNegotiationNotice(int PNID){
			JOptionPane.showMessageDialog(MainPage.parent,
                    "Product Negotiation successfully created,\n please note down its ID for future use: " + PNID);
			String newM = messenger.retNegotiationInstanceByPNIDReq(Integer.toString(PNID));
			newClient.sendMessage(newM);
		    created = true;
			
			
		}
		public static boolean getCreated(){
			return created;
		}
		public static void setCreated(boolean c){
			created = c;
		}

		public static void setMyCComNegotiationList() {
			
	      		myCPage.setMyProdNegList(reader.objContain.myCComProdNegotiations);
	      			      			      		
		}

		public static void JComProdMessage(String prodName, String userName,
				double price, int pNID, double shipping, int shipTime, int quantity, String creatorName) {
			JOptionPane.showMessageDialog(MainPage.parent,
                    "COMMIT \n PNID: " + pNID + "\n" +
			        "Product Name: " + prodName + "\n"+
                    "Commited By : " + creatorName + "\n" +
			        "Price : " + price + " Shipping : " + shipping + " Shipping Time : " + shipTime + " Quantity : " + quantity);
			
		}

		public static void CComProdMessage(String prodName, String userName,
				double price, int pNID, double shipping, int shipTime,
				int quantity) {
			JOptionPane.showMessageDialog(MainPage.parent,
                    "COMMIT \n PNID: " + pNID + "\n" +
			        "Product Name: " + prodName + "\n"+
                    "Commited To : " + userName + "\n" +
			        "Price : " + price + " Shipping : " + shipping + " Shipping Time : " + shipTime + " Quantity : " + quantity);
			
		}

		public static void JComServMessage(int sNID, double price, int timeReq,
				String creatorName, String prodName, String userName) {
			JOptionPane.showMessageDialog(MainPage.parent,
			        "COMMIT \n SNID: " + sNID + "\n" +
			        "Service Name: " + prodName + "\n"+
                    "Commited By : " + creatorName + "\n" +
			        "Price : " + price + " Time Required : " + timeReq);
			
		}

		public static void CComServMessage(int sNID, double price, int timeReq,
				String prodName, String userName) {
			JOptionPane.showMessageDialog(MainPage.parent,
			        "COMMIT \n SNID: " + sNID + "\n" +
			        "Service Name: " + prodName + "\n"+
                    "Commited To : " + userName + "\n" +
			        "Price : " + price + " Time Required : " + timeReq);
			
		}

		public static void negotiationNotFound() {
			JOptionPane.showMessageDialog(MainPage.parent,
			        "Negotiation not found, either it does not exist, has been concluded or cancelled.");
			
		}
	    public static void addMyCProdNegotiation(ProdNegInstList prodNeg){
	    	reader.objContain.myCComProdNegotiations.add(prodNeg);
	    }
	    public static void addMyCServNegotiation(ServNegInstList servNeg){
	    	reader.objContain.myCComServNegotiations.add(servNeg);
	    }
	 
	}
					
	
	
