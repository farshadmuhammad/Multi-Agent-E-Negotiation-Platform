
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class MessageReader {
	DocumentBuilderFactory fac;
	DocumentBuilder facBuild;
	ObjectWrapper objContain;
	
	public MessageReader() throws ParserConfigurationException, TransformerConfigurationException{
		  
		   fac = DocumentBuilderFactory.newInstance();
	       facBuild = fac.newDocumentBuilder();
	       objContain = new ObjectWrapper();
		
	}
	
	public String readMessage(String message) throws SAXException, IOException{
		String reply = " ";
		if(!message.equals(new String(""))){
		Document convertedMess = stringToDom(message);
		convertedMess.normalize();
	    
		Element root = convertedMess.getDocumentElement();
		String rootName = root.getNodeName();
		
		switch(rootName){
		case "loginaccepted":  handleLoginRequest(convertedMess);
		                        break;
		case "loginrejected":  JOptionPane.showMessageDialog(MainPage.parent,
			                     "Invalid User Name Please Try Again");
							    break;
	    case "createServNegotiationReply": handleCreateServNeg(convertedMess);
								break;
	    case "createProdNegotiationReply": handleCreateProdNeg(convertedMess);
								break;
		case "myCProdNegotiationInstances": handleMyProdNegotiations(convertedMess);
								break;
		case "myCComProdNegotiationInstances": handleMyCComProdNegotiations(convertedMess);
								break;
		case "myCServNegotiationInstances": handleMyServNegotiations(convertedMess);
								break;
		case "myCComServNegotiationInstances": handleMyCComServNegotiations(convertedMess);
								break;
		case "myJProdNegotiationInstances": handleMyJProdNegotiations(convertedMess);
								break;
		case "myJComProdNegotiationInstances": handleMyJComProdNegotiations(convertedMess);
								break;
		case "myJServNegotiationInstances": handleMyJServNegotiations(convertedMess);
								break;
		case "myJComServNegotiationInstances": handleMyJComServNegotiations(convertedMess);
								break;
		case "RetrievedProductNegotiationInstance": handleRetProdNegInst(convertedMess);
								break;
		case "RetrievedServiceNegotiationInstance": handleRetServNegInst(convertedMess);
		                        break;
		case "ProdNegotiationsByPNID": handleProdNegotiationsByPNID(convertedMess);	
								break;
		case "ServNegotiationsBySNID": handleProdNegotiationsBySNID(convertedMess);
								break;
		case "UpdateProdNegotiation": handleProdNegotiationsUpdate(convertedMess);
		                        break;
		case "UpdateServNegotiation": handleServNegotiationsUpdate(convertedMess);
        					    break;                        
		case "mySProdNegotiationInstances":  handleMySProdNegotiations(convertedMess);
	    						break;  
		case "mySServNegotiationInstances":  handleMySServNegotiations(convertedMess);
								break;  
		case "joinProdNegotiationReply":  handleJoinProdNegotiation(convertedMess);
		                        break;  
		case "joinServNegotiationReply":  handleServProdNegotiation(convertedMess);
        						break;  
		case "forwardProdNegotiationReply":  handleForwardJoinProdNeg(convertedMess);
								break; 
		case "forwardServNegotiationReply":  handleForwardJoinServNeg(convertedMess);
								break; 
		case "forwardCommitProdNegotiationReply":  handleForwardCommitProdNeg(convertedMess);
		                        break;  
		case "forwardCommitServNegotiationReply":  handleForwardCommitServNeg(convertedMess);
        						break;
		case "forwardCreatorWithdrawProdNegotiation":  handleForwardCreatorWithdrawProdNegotiation(convertedMess);
								break;
		case "forwardCreatorWithdrawServNegotiation":  handleForwardCreatorWithdrawServNegotiation(convertedMess);
								break;
		case "forwardUserWithdrawProdNegotiation":  handleForwardUserWithdrawProdNegotiation(convertedMess);
								break;
		case "forwardUserWithdrawServNegotiation":  handleForwardUserWithdrawServNegotiation(convertedMess);
								break;
		case "ProdDataMineRep":  handleProdDataMineRep(convertedMess);
								break;
		case "ServDataMineRep":  handleServDataMineRep(convertedMess);
								break;
								
		case "ProdChatNotOnline":  handleProdChatNotOnline(convertedMess);
								break;
		case "ServChatNotOnline":  handleServChatNotOnline(convertedMess);
								break;
		case "ProdChat":  handleProdChat(convertedMess);
								break;
		case "ServChat":  handleServChat(convertedMess);
								break;
		case "myCComProdRep":  handleCComProdRep(convertedMess);
						        break;
		case "myJComProdRep":  handleJComProdRep(convertedMess);
        						break;
		case "myJComServRep":  handleJComServRep(convertedMess);
								break;
		case "myCComServRep":  handleCComServRep(convertedMess);
								break;

														
		}
		
		}
		 return reply;
	   }
			
	private void handleCComServRep(Document cMess) {
		Element root = cMess.getDocumentElement();
   	    Node commitNegNode = root.getFirstChild();
   	    int SNID = Integer.parseInt(commitNegNode.getAttributes().item(1).getNodeValue());
   	    double Price = Double.parseDouble(commitNegNode.getAttributes().item(0).getNodeValue());
   	    
   	    int timeReq = Integer.parseInt(commitNegNode.getAttributes().item(3).getNodeValue());
   	  
   	    String prodName = commitNegNode.getAttributes().item(2).getNodeValue();
   	    String userName = commitNegNode.getAttributes().item(4).getNodeValue();
		MainPage.CComServMessage(SNID, Price, timeReq, prodName, userName);
	}

	
	

	private void handleJComServRep(Document cMess) {
		Element root = cMess.getDocumentElement();
   	    Node commitNegNode = root.getFirstChild();
   	    int SNID = Integer.parseInt(commitNegNode.getAttributes().item(1).getNodeValue());
   	    double Price = Double.parseDouble(commitNegNode.getAttributes().item(0).getNodeValue());
   	    
   	    int timeReq = Integer.parseInt(commitNegNode.getAttributes().item(4).getNodeValue());
   	    String creatorName = commitNegNode.getAttributes().item(2).getNodeValue();
   	    String prodName = commitNegNode.getAttributes().item(3).getNodeValue();
   	    String userName = commitNegNode.getAttributes().item(5).getNodeValue();
		MainPage.JComServMessage(SNID, Price, timeReq, creatorName, prodName, userName);
	}

	private void handleCComProdRep(Document cMess) {
		Element root = cMess.getDocumentElement();
   	    Node commitNegNode = root.getFirstChild();
   	    int PNID = Integer.parseInt(commitNegNode.getAttributes().item(0).getNodeValue());
   	    double Price = Double.parseDouble(commitNegNode.getAttributes().item(1).getNodeValue());
   	    double Shipping = Double.parseDouble(commitNegNode.getAttributes().item(4).getNodeValue());
   	    int Quantity = Integer.parseInt(commitNegNode.getAttributes().item(2).getNodeValue());
   	    int shipTime = Integer.parseInt(commitNegNode.getAttributes().item(3).getNodeValue());
   	    String prodName = commitNegNode.getAttributes().item(5).getNodeValue();
   	    String userName = commitNegNode.getAttributes().item(6).getNodeValue();
   	    MainPage.CComProdMessage(prodName, userName, Price, PNID, Shipping, shipTime, Quantity);
		
	}
	private void handleJComProdRep(Document cMess) {
		Element root = cMess.getDocumentElement();
   	    Node commitNegNode = root.getFirstChild();
   	    int PNID = Integer.parseInt(commitNegNode.getAttributes().item(0).getNodeValue());
   	    double Price = Double.parseDouble(commitNegNode.getAttributes().item(1).getNodeValue());
   	    double Shipping = Double.parseDouble(commitNegNode.getAttributes().item(4).getNodeValue());
   	    int Quantity = Integer.parseInt(commitNegNode.getAttributes().item(2).getNodeValue());
   	    int shipTime = Integer.parseInt(commitNegNode.getAttributes().item(3).getNodeValue());
   	    String creatorName = commitNegNode.getAttributes().item(5).getNodeValue();
   	    String prodName = commitNegNode.getAttributes().item(6).getNodeValue();
   	    String userName = commitNegNode.getAttributes().item(7).getNodeValue();
   	    MainPage.JComProdMessage(prodName, userName, Price, PNID, Shipping, shipTime, Quantity, creatorName);
   	    
		
	}

	private void handleCreateProdNeg(Document cMess) {
		Element root = cMess.getDocumentElement();
   	    Node commitNegNode = root.getFirstChild();
   	    int PNID = Integer.parseInt(commitNegNode.getAttributes().item(0).getNodeValue());
		MainPage.createProdNegotiationNotice(PNID);
		
	}

	private void handleCreateServNeg(Document cMess) {
		Element root = cMess.getDocumentElement();
   	    Node commitNegNode = root.getFirstChild();
   	    int SNID = Integer.parseInt(commitNegNode.getAttributes().item(0).getNodeValue());
		MainPage.createServNegotiationNotice(SNID);
	}

	private void handleServChat(Document cMess) {
		Element root = cMess.getDocumentElement();
   	    Node commitNegNode = root.getFirstChild();
   	    int SNID = Integer.parseInt(commitNegNode.getAttributes().item(0).getNodeValue());
   	    String chat = commitNegNode.getAttributes().item(1).getNodeValue();
   	    int receiverID = Integer.parseInt(commitNegNode.getAttributes().item(2).getNodeValue());
		int senderID = Integer.parseInt(commitNegNode.getAttributes().item(3).getNodeValue());
		
		if(objContain.mainNegInst.SNID == SNID){
			MainPage.handleServChat(chat, senderID, receiverID);}
		
	}

	private void handleProdChat(Document cMess) {
		Element root = cMess.getDocumentElement();
   	    Node commitNegNode = root.getFirstChild();
   	    int PNID = Integer.parseInt(commitNegNode.getAttributes().item(0).getNodeValue());
   	    String chat = commitNegNode.getAttributes().item(1).getNodeValue();
   	    int receiverID = Integer.parseInt(commitNegNode.getAttributes().item(2).getNodeValue());
		int senderID = Integer.parseInt(commitNegNode.getAttributes().item(3).getNodeValue());
		
		if(objContain.mainNegInst.PNID == PNID){
			MainPage.handleProdChat(chat, senderID, receiverID);
				
			
		}
	}
	private void handleServChatNotOnline(Document cMess) {
		String msg = "User is currently not online. Message was not delivered!";
		MainPage.appendChat(msg, false);
		
	}
	private void handleProdChatNotOnline(Document convertedMess) {
		String msg = "User is currently not online. Message was not delivered!";
		MainPage.appendChat(msg, true);
		
	}

	private void handleServDataMineRep(Document cMess) {
		Element root = cMess.getDocumentElement();
   	    Node commitNegNode = root.getFirstChild();
   	    double Price = Double.parseDouble(commitNegNode.getAttributes().item(0).getNodeValue());
   	    int timeReq = Integer.parseInt(commitNegNode.getAttributes().item(1).getNodeValue());
		MainPage.showServDataMine(Price, timeReq);
		
	}

	private void handleProdDataMineRep(Document cMess) {
		Element root = cMess.getDocumentElement();
   	    Node commitNegNode = root.getFirstChild();
   	    double Price = Double.parseDouble(commitNegNode.getAttributes().item(0).getNodeValue());
   	    double Shipping = Double.parseDouble(commitNegNode.getAttributes().item(3).getNodeValue());
		int Quantity = Integer.parseInt(commitNegNode.getAttributes().item(1).getNodeValue());
		int shipTime = Integer.parseInt(commitNegNode.getAttributes().item(2).getNodeValue());
		MainPage.showProdDataMine(Price, Shipping, Quantity, shipTime);
	}

	private void handleForwardUserWithdrawServNegotiation(Document cMess) {
		Element root = cMess.getDocumentElement();
   	    Node commitNegNode = root.getFirstChild();
   	    int SNID = Integer.parseInt(commitNegNode.getAttributes().item(0).getNodeValue());
		String userID = commitNegNode.getAttributes().item(1).getNodeValue();
		MainPage.handleServUserWithdraw(SNID, userID);
		
	}

	private void handleForwardUserWithdrawProdNegotiation(Document cMess) {
		Element root = cMess.getDocumentElement();
   	    Node commitNegNode = root.getFirstChild();
   	    int PNID = Integer.parseInt(commitNegNode.getAttributes().item(0).getNodeValue());
		String userID = commitNegNode.getAttributes().item(1).getNodeValue();
		MainPage.handleProdUserWithdraw(PNID, userID);
		
	}

	private void handleForwardCreatorWithdrawServNegotiation(
			Document cMess) {
		Element root = cMess.getDocumentElement();
   	    Node commitNegNode = root.getFirstChild();
   	    int SNID = Integer.parseInt(commitNegNode.getAttributes().item(0).getNodeValue());
		String prodName = commitNegNode.getAttributes().item(2).getNodeValue();
		String creatorName = commitNegNode.getAttributes().item(1).getNodeValue();
		MainPage.handleServCreatorWithdraw(SNID, prodName, creatorName);
		
	}

	private void handleForwardCreatorWithdrawProdNegotiation(
			Document cMess) {
		Element root = cMess.getDocumentElement();
   	    Node commitNegNode = root.getFirstChild();
   	    int PNID = Integer.parseInt(commitNegNode.getAttributes().item(0).getNodeValue());
		String prodName = commitNegNode.getAttributes().item(2).getNodeValue();
		String creatorName = commitNegNode.getAttributes().item(1).getNodeValue();
		MainPage.handleProdCreatorWithdraw(PNID, prodName, creatorName);
	}

	private void handleForwardCommitServNeg(Document cMess) {
		Element root = cMess.getDocumentElement();
   	    Node commitNegNode = root.getFirstChild();
		int SNID = Integer.parseInt(commitNegNode.getAttributes().item(1).getNodeValue());
		double price = Double.parseDouble(commitNegNode.getAttributes().item(0).getNodeValue());
		int timeReq = Integer.parseInt(commitNegNode.getAttributes().item(4).getNodeValue());
		
		
		int userID = Integer.parseInt(commitNegNode.getAttributes().item(5).getNodeValue());
		String prodName = commitNegNode.getAttributes().item(3).getNodeValue();
		String creatorName = commitNegNode.getAttributes().item(2).getNodeValue();
		MainPage.handleCommitServNegotiation(SNID, userID, prodName, price, timeReq, creatorName);
		
		
	}

	private void handleForwardCommitProdNeg(Document cMess) {
		Element root = cMess.getDocumentElement();
   	    Node commitNegNode = root.getFirstChild();
		int PNID = Integer.parseInt(commitNegNode.getAttributes().item(0).getNodeValue());
		double price = Double.parseDouble(commitNegNode.getAttributes().item(1).getNodeValue());
		int quantity = Integer.parseInt(commitNegNode.getAttributes().item(2).getNodeValue());
		int shipTime = Integer.parseInt(commitNegNode.getAttributes().item(3).getNodeValue());
		double shipping = Double.parseDouble(commitNegNode.getAttributes().item(4).getNodeValue());
		int userID = Integer.parseInt(commitNegNode.getAttributes().item(7).getNodeValue());
		String prodName = commitNegNode.getAttributes().item(6).getNodeValue();
		String creatorName = commitNegNode.getAttributes().item(5).getNodeValue();
		MainPage.handleCommitProdNegotiation(PNID, userID, prodName, price, quantity, shipping, shipTime, creatorName);
	}

	private void handleForwardJoinServNeg(Document cMess) {
		Element root = cMess.getDocumentElement();
   	    Node joinNegNode = root.getFirstChild();
   	    int SNID = Integer.parseInt(joinNegNode.getAttributes().item(0).getNodeValue());
   	    int userID = Integer.parseInt(joinNegNode.getAttributes().item(1).getNodeValue());
		String userName = joinNegNode.getAttributes().item(2).getNodeValue();
		if(SNID == objContain.mainNegInst.SNID){
			if(objContain.mainNegInst.User1.user.getUserID() == 0){
				objContain.mainNegInst.User1.user.setUserID(userID);
				objContain.mainNegInst.User1.user.setUserName(userName);
				objContain.mainNegInst.User1.Price = 0;
				objContain.mainNegInst.User1.timeReq = 0;
				objContain.mainNegInst.User1.isCommited = false;
				objContain.mainNegInst.currentPart++;
			}
			else if(objContain.mainNegInst.User2.user.getUserID() == 0){
				objContain.mainNegInst.User2.user.setUserID(userID);
				objContain.mainNegInst.User2.user.setUserName(userName);
				objContain.mainNegInst.User2.Price = 0;
				objContain.mainNegInst.User2.timeReq = 0;
				objContain.mainNegInst.User2.isCommited = false;
				objContain.mainNegInst.currentPart++;
			}
			else if(objContain.mainNegInst.User3.user.getUserID() == 0){
				objContain.mainNegInst.User3.user.setUserID(userID);
				objContain.mainNegInst.User3.user.setUserName(userName);
				objContain.mainNegInst.User3.Price = 0;
				objContain.mainNegInst.User3.timeReq = 0;
				objContain.mainNegInst.User3.isCommited = false;
				objContain.mainNegInst.currentPart++;
			}
			else if(objContain.mainNegInst.User4.user.getUserID() == 0){
				objContain.mainNegInst.User4.user.setUserID(userID);
				objContain.mainNegInst.User4.user.setUserName(userName);
				objContain.mainNegInst.User4.Price = 0;
				objContain.mainNegInst.User4.timeReq = 0;
				objContain.mainNegInst.User4.isCommited = false;
				objContain.mainNegInst.currentPart++;
			}
			MainPage.setServNegotiationUsers(objContain.mainNegInst.currentPart);
		}
	}

	
	private void handleForwardJoinProdNeg(Document cMess) {
		Element root = cMess.getDocumentElement();
   	    Node joinNegNode = root.getFirstChild();
   	    int PNID = Integer.parseInt(joinNegNode.getAttributes().item(0).getNodeValue());
   	    int userID = Integer.parseInt(joinNegNode.getAttributes().item(1).getNodeValue());
		String userName = joinNegNode.getAttributes().item(2).getNodeValue();
		if(PNID == objContain.mainNegInst.PNID){
			if(objContain.mainNegInst.User1.user.getUserID() == 0){
				objContain.mainNegInst.User1.user.setUserID(userID);
				objContain.mainNegInst.User1.user.setUserName(userName);
				objContain.mainNegInst.User1.Price = 0;
				objContain.mainNegInst.User1.Quantity = 0;
				objContain.mainNegInst.User1.Shipping = 0;
				objContain.mainNegInst.User1.shipTime = 0;
				objContain.mainNegInst.User1.isCommited = false;
				objContain.mainNegInst.currentPart++;
			}
			else if(objContain.mainNegInst.User2.user.getUserID() == 0){
				objContain.mainNegInst.User2.user.setUserID(userID);
				objContain.mainNegInst.User2.user.setUserName(userName);
				objContain.mainNegInst.User2.Price = 0;
				objContain.mainNegInst.User2.Quantity = 0;
				objContain.mainNegInst.User2.Shipping = 0;
				objContain.mainNegInst.User2.shipTime = 0;
				objContain.mainNegInst.User2.isCommited = false;
				objContain.mainNegInst.currentPart++;
			}
			else if(objContain.mainNegInst.User3.user.getUserID() == 0){
				objContain.mainNegInst.User3.user.setUserID(userID);
				objContain.mainNegInst.User3.user.setUserName(userName);
				objContain.mainNegInst.User3.Price = 0;
				objContain.mainNegInst.User3.Quantity = 0;
				objContain.mainNegInst.User3.Shipping = 0;
				objContain.mainNegInst.User3.shipTime = 0;
				objContain.mainNegInst.User3.isCommited = false;
				objContain.mainNegInst.currentPart++;
			}
			else if(objContain.mainNegInst.User4.user.getUserID() == 0){
				objContain.mainNegInst.User4.user.setUserID(userID);
				objContain.mainNegInst.User4.user.setUserName(userName);
				objContain.mainNegInst.User4.Price = 0;
				objContain.mainNegInst.User4.Quantity = 0;
				objContain.mainNegInst.User4.Shipping = 0;
				objContain.mainNegInst.User4.shipTime = 0;
				objContain.mainNegInst.User4.isCommited = false;
				objContain.mainNegInst.currentPart++;
			}
			MainPage.setProdNegotiationUsers(objContain.mainNegInst.currentPart);
		}
	}

	private void handleServProdNegotiation(Document cMess) {
		Element root = cMess.getDocumentElement();
   	    Node joinNegNode = root.getFirstChild();
   	    if(joinNegNode.getAttributes().item(0).getNodeValue().toString().equals(new String("accepted"))){
   	    	//MainPage.setUserJoinedServNegotiation();
   	    }
		
	}

	private void handleJoinProdNegotiation(Document cMess) {
		Element root = cMess.getDocumentElement();
   	    Node joinNegNode = root.getFirstChild();
   	    if(joinNegNode.getAttributes().item(0).getNodeValue().toString().equals(new String("accepted"))){
   	    	//MainPage.setUserJoinedProdNegotiation();
   	    }
		
	}

	private void handleMySServNegotiations(Document cMess) {
		objContain.mySServNegotiations.clear();
		Element root = cMess.getDocumentElement();
    	 Node myNegNode = root.getFirstChild();
    	 
    	 NodeList List = myNegNode.getChildNodes();
    	
    	for(int i = 0; i < List.getLength(); i++){
    		
    		int SNID = Integer.parseInt(List.item(i).getAttributes().item(0).getNodeValue());
    		String servName = List.item(i).getAttributes().item(4).getNodeValue();
    		int type = Integer.parseInt(List.item(i).getAttributes().item(1).getNodeValue());
    		int userID = Integer.parseInt(List.item(i).getAttributes().item(2).getNodeValue());
    		String userName =  List.item(i).getAttributes().item(3).getNodeValue();
    		objContain.addMySServNegotiation(new ServNegInstList(servName, userID, userName, SNID, type ));
    	}
		MainPage.setServSearchArea();
	}

	private void handleMySProdNegotiations(Document cMess) {
		objContain.mySProdNegotiations.clear(); 
		Element root = cMess.getDocumentElement();
    	 Node myNegNode = root.getFirstChild();
    	 
    	 NodeList List = myNegNode.getChildNodes();
    	
    	for(int i = 0; i < List.getLength(); i++){
    		
    		int PNID = Integer.parseInt(List.item(i).getAttributes().item(0).getNodeValue());
    		String prodName = List.item(i).getAttributes().item(1).getNodeValue();
    		int type = Integer.parseInt(List.item(i).getAttributes().item(2).getNodeValue());
    		int userID = Integer.parseInt(List.item(i).getAttributes().item(3).getNodeValue());
    		String userName =  List.item(i).getAttributes().item(4).getNodeValue();
    		objContain.addMySProdNegotiation(new ProdNegInstList(prodName, userID, userName, PNID, type ));
    	}
    	MainPage.setProdSearchArea();
		
	}

	public void handleProdNegotiationsUpdate(Document cMess) {
		Element root = cMess.getDocumentElement();
    	Node myNegNode = root.getFirstChild();
		if(Integer.parseInt(myNegNode.getAttributes().item(0).getNodeValue()) == objContain.mainNegInst.PNID){
		   objContain.mainNegInst.resolveProdUpdate(Integer.parseInt(myNegNode.getAttributes().item(5).getNodeValue()), Double.parseDouble(myNegNode.getAttributes().item(1).getNodeValue()),
				   Double.parseDouble(myNegNode.getAttributes().item(4).getNodeValue()), Integer.parseInt(myNegNode.getAttributes().item(2).getNodeValue()), Integer.parseInt(myNegNode.getAttributes().item(3).getNodeValue())); 
			}
	}
	public void handleServNegotiationsUpdate(Document cMess) {
		Element root = cMess.getDocumentElement();
    	Node myNegNode = root.getFirstChild();
		if(Integer.parseInt(myNegNode.getAttributes().item(1).getNodeValue()) == objContain.mainNegInst.SNID){
		   objContain.mainNegInst.resolveServUpdate(Integer.parseInt(myNegNode.getAttributes().item(3).getNodeValue()), Double.parseDouble(myNegNode.getAttributes().item(0).getNodeValue()),
				 Integer.parseInt(myNegNode.getAttributes().item(2).getNodeValue())); 
			}
	}
	public void handleProdNegotiationsBySNID(Document cMess) {
		ArrayList<Negotiations> ServNegs = new ArrayList<Negotiations>();
		Element root = cMess.getDocumentElement();
    	Node myNegNode = root.getFirstChild();
    	 NodeList List = myNegNode.getChildNodes();
    	 for(int i = 0; i < List.getLength(); i++){
    		ServNegs.add(new Negotiations(new User(Integer.parseInt(List.item(i).getAttributes().item(2).getNodeValue()),List.item(i).getAttributes().item(4).getNodeValue()),
					Double.parseDouble(List.item(i).getAttributes().item(0).getNodeValue()),0,
					0, Integer.parseInt(List.item(i).getAttributes().item(3).getNodeValue()),false,0));  
	 
    		 
    	 }
        if(ServNegs.size() == 1){
        	setCreator(ServNegs.get(0));
        	objContain.mainNegInst.currentPart = 1;
        	MainPage.setServNegotiationUsers(1);
        }
        else if(ServNegs.size() == 2){
        	for(int i = 0; i < ServNegs.size(); i++){
        		if(ServNegs.get(i).user.getUserID() == objContain.mainNegInst.creatorID){
        			setCreator(ServNegs.get(i));
        			ServNegs.remove(i);
        			break;
        		}
        	}
            setUser1(ServNegs.get(0));
            objContain.mainNegInst.currentPart = 2;
            MainPage.setServNegotiationUsers(2);
        }
        else if(ServNegs.size() == 3){
        	for(int i = 0; i < ServNegs.size(); i++){
        		if(ServNegs.get(i).user.getUserID() == objContain.mainNegInst.creatorID){
        			setCreator(ServNegs.get(i));
        			ServNegs.remove(i);
        			break;
        		}
        	}
            setUser1(ServNegs.get(0));
            setUser2(ServNegs.get(1));
            objContain.mainNegInst.currentPart = 3;
            MainPage.setServNegotiationUsers(3);
        }
        else if(ServNegs.size() == 4){
        	for(int i = 0; i < ServNegs.size(); i++){
        		if(ServNegs.get(i).user.getUserID() == objContain.mainNegInst.creatorID){
        			setCreator(ServNegs.get(i));
        			ServNegs.remove(i);
        			break;
        		}
        	}
            setUser1(ServNegs.get(0));
            setUser2(ServNegs.get(1));
            setUser3(ServNegs.get(2));
            objContain.mainNegInst.currentPart = 4;
            MainPage.setServNegotiationUsers(4);
            
        }
        else if(ServNegs.size() == 5){
        	for(int i = 0; i < ServNegs.size(); i++){
        		if(ServNegs.get(i).user.getUserID() == objContain.mainNegInst.creatorID){
        			setCreator(ServNegs.get(i));
        			ServNegs.remove(i);
        			break;
        		}
        	}
            setUser1(ServNegs.get(0));
            setUser2(ServNegs.get(1));
            setUser3(ServNegs.get(2));
            setUser4(ServNegs.get(3));
            objContain.mainNegInst.currentPart = 5;
            MainPage.setServNegotiationUsers(5);
            MainPage.disableServJoinButton();
        }
		
	}

	public Document stringToDom(String message) throws SAXException, IOException{
	     
		return facBuild.parse(new InputSource(new StringReader(message)));
		
		
		
	}
	public void handleLoginRequest(Document cMess){
		Element root = cMess.getDocumentElement();
		Node loginReqNode = root.getFirstChild();
		String loginReq = loginReqNode.getAttributes().item(0).getNodeValue();
		int ID = Integer.parseInt(loginReq);
		objContain.mainUser.setUserID(ID);
		
		 MainPage.loginToMainPage();	
		
		System.out.println("USERNAME: " + objContain.mainUser.getUserName() + "\nID: " + objContain.mainUser.getUserID());				
		 
	 }
     public void handleMyProdNegotiations(Document cMess){
    	 Element root = cMess.getDocumentElement();
    	 Node myNegNode = root.getFirstChild();
    	 
    	 NodeList List = myNegNode.getChildNodes();
    	
    	for(int i = 0; i < List.getLength(); i++){
    		
    		int PNID = Integer.parseInt(List.item(i).getAttributes().item(0).getNodeValue());
    		String prodName = List.item(i).getAttributes().item(1).getNodeValue();
    		int type = Integer.parseInt(List.item(i).getAttributes().item(2).getNodeValue());
    		int userID = Integer.parseInt(List.item(i).getAttributes().item(3).getNodeValue());
    		String userName =  List.item(i).getAttributes().item(4).getNodeValue();
    		objContain.addMyProdNegotiation(new ProdNegInstList(prodName, userID, userName, PNID, type ));
    	}
    	MainPage.setMyNegotiationList();
    	
     }
     public void handleMyCComProdNegotiations(Document cMess){
    	 Element root = cMess.getDocumentElement();
    	 Node myNegNode = root.getFirstChild();
    	 
    	 NodeList List = myNegNode.getChildNodes();
    	
    	for(int i = 0; i < List.getLength(); i++){
    		
    		int PNID = Integer.parseInt(List.item(i).getAttributes().item(0).getNodeValue());
    		String prodName = List.item(i).getAttributes().item(1).getNodeValue();
    		int type = Integer.parseInt(List.item(i).getAttributes().item(2).getNodeValue());
    		int userID = Integer.parseInt(List.item(i).getAttributes().item(3).getNodeValue());
    		String userName =  List.item(i).getAttributes().item(4).getNodeValue();
    		objContain.addMyCComProdNegotiation(new ProdNegInstList(prodName, userID, userName, PNID, type ));
    	}
    	MainPage.setMyCComNegotiationList();
    	
     }
     public void handleMyJProdNegotiations(Document cMess){
    	 Element root = cMess.getDocumentElement();
    	 Node myNegNode = root.getFirstChild();
    	 
    	 NodeList List = myNegNode.getChildNodes();
    	
    	for(int i = 0; i < List.getLength(); i++){
    		
    		int PNID = Integer.parseInt(List.item(i).getAttributes().item(0).getNodeValue());
    		String prodName = List.item(i).getAttributes().item(1).getNodeValue();
    		int type = Integer.parseInt(List.item(i).getAttributes().item(2).getNodeValue());
    		int userID = Integer.parseInt(List.item(i).getAttributes().item(3).getNodeValue());
    		String userName =  List.item(i).getAttributes().item(4).getNodeValue();
    		objContain.addMyJProdNegotiation(new ProdNegInstList(prodName, userID, userName, PNID, type ));
    	}
    
    	
     }
     public void handleMyJComProdNegotiations(Document cMess){
    	 Element root = cMess.getDocumentElement();
    	 Node myNegNode = root.getFirstChild();
    	 
    	 NodeList List = myNegNode.getChildNodes();
    	
    	for(int i = 0; i < List.getLength(); i++){
    		
    		int PNID = Integer.parseInt(List.item(i).getAttributes().item(0).getNodeValue());
    		String prodName = List.item(i).getAttributes().item(1).getNodeValue();
    		int type = Integer.parseInt(List.item(i).getAttributes().item(2).getNodeValue());
    		int userID = Integer.parseInt(List.item(i).getAttributes().item(3).getNodeValue());
    		String userName =  List.item(i).getAttributes().item(4).getNodeValue();
    		objContain.addMJComProdNegotiation(new ProdNegInstList(prodName, userID, userName, PNID, type ));
    	}
    
    	
     }
     public void handleMyServNegotiations(Document cMess){
    	 Element root = cMess.getDocumentElement();
    	 Node myNegNode = root.getFirstChild();
    	 
    	 NodeList List = myNegNode.getChildNodes();
    	
    	for(int i = 0; i < List.getLength(); i++){
    		
    		int SNID = Integer.parseInt(List.item(i).getAttributes().item(0).getNodeValue());
    		String servName = List.item(i).getAttributes().item(1).getNodeValue();
    		int type = Integer.parseInt(List.item(i).getAttributes().item(2).getNodeValue());
    		int userID = Integer.parseInt(List.item(i).getAttributes().item(3).getNodeValue());
    		String userName =  List.item(i).getAttributes().item(4).getNodeValue();
    		objContain.addMyServNegotiation(new ServNegInstList(servName, userID, userName, SNID, type ));
    	}
    	
    	
     }
     public void handleMyCComServNegotiations(Document cMess){
    	 Element root = cMess.getDocumentElement();
    	 Node myNegNode = root.getFirstChild();
    	 
    	 NodeList List = myNegNode.getChildNodes();
    	
    	for(int i = 0; i < List.getLength(); i++){
    		
    		int SNID = Integer.parseInt(List.item(i).getAttributes().item(0).getNodeValue());
    		String servName = List.item(i).getAttributes().item(1).getNodeValue();
    		int type = Integer.parseInt(List.item(i).getAttributes().item(2).getNodeValue());
    		int userID = Integer.parseInt(List.item(i).getAttributes().item(3).getNodeValue());
    		String userName =  List.item(i).getAttributes().item(4).getNodeValue();
    		objContain.addMyCComServNegotiation(new ServNegInstList(servName, userID, userName, SNID, type ));
    	}
    	
    	
     }
     public void handleMyJServNegotiations(Document cMess){
    	 Element root = cMess.getDocumentElement();
    	 Node myNegNode = root.getFirstChild();
    	 
    	 NodeList List = myNegNode.getChildNodes();
    	
    	for(int i = 0; i < List.getLength(); i++){
    		
    		int SNID = Integer.parseInt(List.item(i).getAttributes().item(0).getNodeValue());
    		String servName = List.item(i).getAttributes().item(1).getNodeValue();
    		int type = Integer.parseInt(List.item(i).getAttributes().item(2).getNodeValue());
    		int userID = Integer.parseInt(List.item(i).getAttributes().item(3).getNodeValue());
    		String userName =  List.item(i).getAttributes().item(4).getNodeValue();
    		objContain.addMyJServNegotiation(new ServNegInstList(servName, userID, userName, SNID, type ));
    	}
    	
    	
     }
     public void handleMyJComServNegotiations(Document cMess){
    	 Element root = cMess.getDocumentElement();
    	 Node myNegNode = root.getFirstChild();
    	 
    	 NodeList List = myNegNode.getChildNodes();
    	
    	for(int i = 0; i < List.getLength(); i++){
    		
    		int SNID = Integer.parseInt(List.item(i).getAttributes().item(0).getNodeValue());
    		String servName = List.item(i).getAttributes().item(1).getNodeValue();
    		int type = Integer.parseInt(List.item(i).getAttributes().item(2).getNodeValue());
    		int userID = Integer.parseInt(List.item(i).getAttributes().item(3).getNodeValue());
    		String userName =  List.item(i).getAttributes().item(4).getNodeValue();
    		objContain.addMyJComServNegotiation(new ServNegInstList(servName, userID, userName, SNID, type ));
    	}
    	
    	
     }
     public void handleRetProdNegInst(Document cMess) {
    	Element root = cMess.getDocumentElement();
    	Node myNegNode = root.getFirstChild();
        if(myNegNode.hasAttributes()){
    	int PNID = Integer.parseInt(myNegNode.getAttributes().item(0).getNodeValue());
 		String prodName = myNegNode.getAttributes().item(1).getNodeValue();
 		int type = Integer.parseInt(myNegNode.getAttributes().item(2).getNodeValue());
 		int userID = Integer.parseInt(myNegNode.getAttributes().item(3).getNodeValue());
 		String userName =  myNegNode.getAttributes().item(4).getNodeValue();
 		objContain.mainNegInst.creatorID = userID;
 		objContain.mainNegInst.PNID = PNID;
 		objContain.mainNegInst.prodOrServName = prodName;
 		objContain.mainNegInst.type = type;
 		objContain.mainNegInst.creatorName = userName;
 		objContain.mainNegInst.creator.user.setUserID(userID);
 		objContain.mainNegInst.creator.user.setUserName(userName);
 		if(MainPage.getCreated()){
 			objContain.addMyProdNegotiation(new ProdNegInstList(prodName, userID, userName, PNID, type ));
 		    MainPage.setCreated(false);
 		}}
        else
        	MainPage.negotiationNotFound();
 	
 	}
    public void handleProdNegotiationsByPNID(Document cMess){
    	ArrayList<Negotiations> ProdNegs = new ArrayList<Negotiations>();
    	Element root = cMess.getDocumentElement();
    	Node myNegNode = root.getFirstChild();
    	 NodeList List = myNegNode.getChildNodes();
    	 for(int i = 0; i < List.getLength(); i++){
    		ProdNegs.add(new Negotiations(new User(Integer.parseInt(List.item(i).getAttributes().item(5).getNodeValue()),List.item(i).getAttributes().item(6).getNodeValue()),
    						Double.parseDouble(List.item(i).getAttributes().item(1).getNodeValue()),Double.parseDouble(List.item(i).getAttributes().item(3).getNodeValue()),
    						Integer.parseInt(List.item(i).getAttributes().item(2).getNodeValue()), 0,false,Integer.parseInt(List.item(i).getAttributes().item(4).getNodeValue())));  
    		 
    	 }
        if(ProdNegs.size() == 1){
        	setCreator(ProdNegs.get(0));
        	objContain.mainNegInst.currentPart = 1;
        	MainPage.setProdNegotiationUsers(1);
        }
        else if(ProdNegs.size() == 2){
        	for(int i = 0; i < ProdNegs.size(); i++){
        		if(ProdNegs.get(i).user.getUserID() == objContain.mainNegInst.creatorID){
        			setCreator(ProdNegs.get(i));
        			ProdNegs.remove(i);
        			break;
        		}
        	}
            setUser1(ProdNegs.get(0));
            objContain.mainNegInst.currentPart = 2;
            MainPage.setProdNegotiationUsers(2);
        }
        else if(ProdNegs.size() == 3){
        	for(int i = 0; i < ProdNegs.size(); i++){
        		if(ProdNegs.get(i).user.getUserID() == objContain.mainNegInst.creatorID){
        			setCreator(ProdNegs.get(i));
        			ProdNegs.remove(i);
        			break;
        		}
        	}
            setUser1(ProdNegs.get(0));
            setUser2(ProdNegs.get(1));
            objContain.mainNegInst.currentPart = 3;
            MainPage.setProdNegotiationUsers(3);
        }
        else if(ProdNegs.size() == 4){
        	for(int i = 0; i < ProdNegs.size(); i++){
        		if(ProdNegs.get(i).user.getUserID() == objContain.mainNegInst.creatorID){
        			setCreator(ProdNegs.get(i));
        			ProdNegs.remove(i);
        			break;
        		}
        	}
            setUser1(ProdNegs.get(0));
            setUser2(ProdNegs.get(1));
            setUser3(ProdNegs.get(2));
            objContain.mainNegInst.currentPart = 4;
            MainPage.setProdNegotiationUsers(4);
            
        }
        else if(ProdNegs.size() == 5){
        	for(int i = 0; i < ProdNegs.size(); i++){
        		if(ProdNegs.get(i).user.getUserID() == objContain.mainNegInst.creatorID){
        			setCreator(ProdNegs.get(i));
        			ProdNegs.remove(i);
        			break;
        		}
        	}
            setUser1(ProdNegs.get(0));
            setUser2(ProdNegs.get(1));
            setUser3(ProdNegs.get(2));
            setUser4(ProdNegs.get(3));
            objContain.mainNegInst.currentPart = 5;
            MainPage.setProdNegotiationUsers(5);
        }
    }
    public void handleRetServNegInst(Document cMess) {
		Element root = cMess.getDocumentElement();
    	Node myNegNode = root.getFirstChild();
    	if(myNegNode.hasAttributes()){
    	int SNID = Integer.parseInt(myNegNode.getAttributes().item(1).getNodeValue());
 		String prodName = myNegNode.getAttributes().item(0).getNodeValue();
 		int type = Integer.parseInt(myNegNode.getAttributes().item(2).getNodeValue());
 		int userID = Integer.parseInt(myNegNode.getAttributes().item(3).getNodeValue());
 		String userName =  myNegNode.getAttributes().item(4).getNodeValue();
 		objContain.mainNegInst.creatorID = userID;
 		objContain.mainNegInst.SNID = SNID;
 		objContain.mainNegInst.prodOrServName = prodName;
 		objContain.mainNegInst.type = type;
 		objContain.mainNegInst.creatorName = userName;
 		objContain.mainNegInst.creator.user.setUserID(userID);
 		objContain.mainNegInst.creator.user.setUserName(userName);
 		objContain.mainNegInst.User1 = new Negotiations();
 		objContain.mainNegInst.User2 = new Negotiations();
 		objContain.mainNegInst.User3 = new Negotiations();
 		objContain.mainNegInst.User4 = new Negotiations();
 		if(MainPage.getCreated()){
 			objContain.addMyServNegotiation(new ServNegInstList(prodName, userID, userName, SNID, type ));
 		    MainPage.setCreated(false);
 		}}
    	else
    		MainPage.negotiationNotFound();
	}

    
		    public void setCreator(Negotiations cret){
		   	 objContain.mainNegInst.creator = cret;
		    }
		    public void setUser1 (Negotiations user){
		   	 objContain.mainNegInst.User1 = user;
		    }
		    public void setUser2 (Negotiations user){
		     objContain.mainNegInst.User2 = user;
		    }
		    public void setUser3 (Negotiations user){
		     objContain.mainNegInst.User3 = user;
		    }
		    public void setUser4 (Negotiations user){
		     objContain.mainNegInst.User4 = user;
		    }
}



