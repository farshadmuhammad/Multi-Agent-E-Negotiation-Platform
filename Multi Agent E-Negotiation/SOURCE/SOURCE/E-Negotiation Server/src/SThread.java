import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
public class SThread extends Thread {
   Socket socks;
   int userID1 = 0;
   String IP;
   DatabaseHandler singleHandle;
   MessageReader readThis;
   public SThread(Socket sock) throws ParserConfigurationException, TransformerConfigurationException{
	socks = sock;    
	singleHandle = new DatabaseHandler();
   readThis = new MessageReader();
   }
	public void run(){
		try {
			String Message = null;
			PrintWriter sockWriter = new PrintWriter(socks.getOutputStream(), true);
			BufferedReader buffR = new BufferedReader(new InputStreamReader(socks.getInputStream()));
		    while((Message = buffR.readLine()) != null){
		    	//System.out.println(Message);
		        
		    	try {
					sockWriter.println(readThis.readMessage(Message));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TransformerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	sockWriter.flush();
		    
		    }
		    socks.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
  public boolean sendMessageGlobal(String Message) throws IOException{
	  PrintWriter sockWriter = new PrintWriter(socks.getOutputStream(), true);
	  sockWriter.println(Message);
	  sockWriter.flush();
	  return true;
  }
  

   public class MessageReader {
	DocumentBuilderFactory fac;
	DocumentBuilder facBuild;
	MessageBuilder replier;
	public MessageReader() throws ParserConfigurationException, TransformerConfigurationException{
		   replier =  new MessageBuilder();
		   fac = DocumentBuilderFactory.newInstance();
	       facBuild = fac.newDocumentBuilder();
	       
		
	}
	
	public String readMessage(String message) throws SAXException, IOException, SQLException, NumberFormatException, TransformerException{
		Document convertedMess = stringToDom(message);
		convertedMess.normalize();
	    String reply = " ";
		Element root = convertedMess.getDocumentElement();
		String rootName = root.getNodeName();
		
		switch(rootName){
		case "loginrequest": reply = handleLoginRequest(convertedMess);
		                      break;
		case "createProdNegRequest": reply = handleCreateProdNegRequest(convertedMess);
							  break;
		case "createServNegRequest": reply = handleCreateServNegRequest(convertedMess);
							  break;
		case "NegotiationRequestByPNID": reply = handleNegotiationsByPNID(convertedMess);
		                      break;
		
		case "NegotiationInstanceRequestByPNID": reply = handleNegotiationInstanceByPNID(convertedMess);
							  break;
		case "NegotiationRequestBySNID": reply = handleNegotiationsBySNID(convertedMess);
        					  break;
		case "NegotiationInstanceRequestBySNID": reply = handleNegotiationInstanceBySNID(convertedMess);
		                      break;
		case "UpdateProdNegotiation": handleUpdateProdNegotiation(convertedMess);
                              break;
		case "UpdateServNegotiation": handleUpdateServNegotiation(convertedMess);
        					  break;	
		case "SearchProducts": reply = handleSearchProducts(convertedMess);
		                      break;
		case "SearchServices": reply = handleSearchServices(convertedMess);
                              break;
		case "JoinProdNegotiationRequest": reply = handleJoinProdNegReq(convertedMess);
        					  break;
		case "JoinServNegotiationRequest": reply = handleServProdNegReq(convertedMess);
		                      break;
		case "CommitProdNegotiationNotify": commitProdNegotiation(convertedMess);
                              break;
		case "CommitServNegotiationNotify": commitServNegotiation(convertedMess);
        					  break;
		case "CreatorWithdrawProdNegotiation": handleCreatorProdWithdraw(convertedMess);
		                      break;
		case "CreatorWithdrawServNegotiation": handleCreatorServWithdraw(convertedMess);
        					  break;
		case "UserWithdrawProdNegotiation": handleUserProdWithdraw(convertedMess);
		  					  break;
		case "UserWithdrawServNegotiation": handleUserServWithdraw(convertedMess);
		  					  break;
		case "ProdDataMine" : reply = handleProdDataMine(convertedMess);
							  break;
		case "ServDataMine" : reply = handleServDataMine(convertedMess);
		  					  break;
		case "ProdChat" : handleProdChat(convertedMess);
							  break;
		case "ServChat" : handleServChat(convertedMess);
		  					  break;
		case "RetCComProd" : reply = handleRetCComProd(convertedMess);
		  					  break;
		case "RetCComServ" : reply = handleRetCComServ(convertedMess);
		  					  break;
		case "RetJComProd" : reply = handleRetJComProd(convertedMess);
		  						break;
		case "RetJComServ" : reply = handleRetJComServ(convertedMess);
								break;
		  
		}
		
		 
		 return reply;
	   }
			
	  
	
	
	private String handleRetJComServ(Document cMess) throws SQLException, TransformerException {
		Element root = cMess.getDocumentElement();
    	Node cPRNode = root.getFirstChild();
    	String SNID = cPRNode.getAttributes().item(0).getNodeValue();
    	
    	return replier.createJComServRep(SNID);
	}

	private String handleRetJComProd(Document cMess) throws SQLException, TransformerException {
		Element root = cMess.getDocumentElement();
    	Node cPRNode = root.getFirstChild();
    	String PNID = cPRNode.getAttributes().item(0).getNodeValue();
    	
    	return replier.createJComProdRep(PNID);
	}

	private String handleRetCComServ(Document cMess) throws SQLException, TransformerException {
		Element root = cMess.getDocumentElement();
    	Node cPRNode = root.getFirstChild();
    	String SNID = cPRNode.getAttributes().item(0).getNodeValue();
    	
    	return replier.createCComServRep(SNID);
	}

	private void handleServChat(Document cMess) throws IOException, TransformerException {
		Element root = cMess.getDocumentElement();
		Node cPRNode = root.getFirstChild();
		String SNID = cPRNode.getAttributes().item(0).getNodeValue();
		String chat = cPRNode.getAttributes().item(1).getNodeValue();
		String receiverID = cPRNode.getAttributes().item(2).getNodeValue();
		String senderID = cPRNode.getAttributes().item(3).getNodeValue();
		replier.createServChatRep(SNID, chat, receiverID, senderID);
		
	}

	private void handleProdChat(Document cMess) throws TransformerException, IOException {
		Element root = cMess.getDocumentElement();
		Node cPRNode = root.getFirstChild();
		String PNID = cPRNode.getAttributes().item(0).getNodeValue();
		String chat = cPRNode.getAttributes().item(1).getNodeValue();
		String receiverID = cPRNode.getAttributes().item(2).getNodeValue();
		String senderID = cPRNode.getAttributes().item(3).getNodeValue();
		replier.createProdChatRep(PNID, chat, receiverID, senderID);
	}

	public String handleProdDataMine(Document cMess) throws TransformerException, SQLException {
		Element root = cMess.getDocumentElement();
		Node cPRNode = root.getFirstChild();
		String prodName = cPRNode.getAttributes().item(0).getNodeValue();
		return replier.createProdDataMineRep(prodName);
	}
	

	private String handleServDataMine(Document cMess) throws SQLException, TransformerException {
		Element root = cMess.getDocumentElement();
		Node cPRNode = root.getFirstChild();
		String servName = cPRNode.getAttributes().item(0).getNodeValue();
		return replier.createServDataMineRep(servName);
	}

	private void handleUserServWithdraw(Document cMess) throws TransformerException, IOException, SQLException {
		Element root = cMess.getDocumentElement();
		Node cPRNode = root.getFirstChild();
		String SNID = cPRNode.getAttributes().item(0).getNodeValue();
		String userID = cPRNode.getAttributes().item(1).getNodeValue();
		ArrayList<Integer> IDList = new ArrayList<Integer>();
		Node userList = cPRNode.getFirstChild();
		NamedNodeMap uList = userList.getAttributes();
		for(int i = 0; i < uList.getLength(); i++){
			IDList.add(Integer.parseInt(uList.item(i).getNodeValue()));
		}
		replier.createUserServWithdrawNotifications(SNID, userID, IDList);
		
	}

	private void handleUserProdWithdraw(Document cMess) throws TransformerException, IOException, SQLException {
		Element root = cMess.getDocumentElement();
		Node cPRNode = root.getFirstChild();
		String PNID = cPRNode.getAttributes().item(0).getNodeValue();
		String userID = cPRNode.getAttributes().item(1).getNodeValue();
		ArrayList<Integer> IDList = new ArrayList<Integer>();
		Node userList = cPRNode.getFirstChild();
		NamedNodeMap uList = userList.getAttributes();
		for(int i = 0; i < uList.getLength(); i++){
			IDList.add(Integer.parseInt(uList.item(i).getNodeValue()));
		}
		replier.createUserProdWithdrawNotifications(PNID, userID, IDList);
		
	}

	private void handleCreatorServWithdraw(Document cMess) throws TransformerException, IOException, SQLException {
		Element root = cMess.getDocumentElement();
		Node cPRNode = root.getFirstChild();
		String SNID = cPRNode.getAttributes().item(0).getNodeValue();
		String prodName = cPRNode.getAttributes().item(2).getNodeValue();
		String creatorName = cPRNode.getAttributes().item(1).getNodeValue();
		ArrayList<Integer> IDList = new ArrayList<Integer>();
		Node userList = cPRNode.getFirstChild();
		NamedNodeMap uList = userList.getAttributes();
		for(int i = 0; i < uList.getLength(); i++){
			IDList.add(Integer.parseInt(uList.item(i).getNodeValue()));
		}
		replier.createCreatorServWithdrawNotifications(SNID, prodName, creatorName, IDList);
		
	}

	private void handleCreatorProdWithdraw(Document cMess) throws TransformerException, IOException, SQLException {
		Element root = cMess.getDocumentElement();
		Node cPRNode = root.getFirstChild();
		String PNID = cPRNode.getAttributes().item(0).getNodeValue();
		String prodName = cPRNode.getAttributes().item(2).getNodeValue();
		String creatorName = cPRNode.getAttributes().item(1).getNodeValue();
		ArrayList<Integer> IDList = new ArrayList<Integer>();
		Node userList = cPRNode.getFirstChild();
		NamedNodeMap uList = userList.getAttributes();
		for(int i = 0; i < uList.getLength(); i++){
			IDList.add(Integer.parseInt(uList.item(i).getNodeValue()));
		}
		replier.createCreatorProdWithdrawNotifications(PNID, prodName, creatorName, IDList);
	}

	private void commitServNegotiation(Document cMess) throws IOException, TransformerException, SQLException {
		Element root = cMess.getDocumentElement();
		Node cPRNode = root.getFirstChild();
		String SNID = cPRNode.getAttributes().item(2).getNodeValue();
		String Price = cPRNode.getAttributes().item(1).getNodeValue();
		String timeReq = cPRNode.getAttributes().item(7).getNodeValue();
		String userID = cPRNode.getAttributes().item(3).getNodeValue();
		String userName = cPRNode.getAttributes().item(4).getNodeValue();
		String prodName = cPRNode.getAttributes().item(6).getNodeValue();
		String creatorID = cPRNode.getAttributes().item(0).getNodeValue();
		String creatorName = cPRNode.getAttributes().item(5).getNodeValue();
		Node userList = cPRNode.getFirstChild();
		NamedNodeMap uList = userList.getAttributes();
		ArrayList<Integer> IDList = new ArrayList<Integer>();
		for(int i = 0; i < uList.getLength(); i++){
			IDList.add(Integer.parseInt(uList.item(i).getNodeValue()));
		}
		replier.createCommitServNegotiationReply(SNID, userID, userName, Price, timeReq, prodName, creatorName, creatorID, IDList);
		
	}

	private String commitProdNegotiation(Document cMess) throws TransformerException, IOException, SQLException {
		Element root = cMess.getDocumentElement();
		Node cPRNode = root.getFirstChild();
		String PNID = cPRNode.getAttributes().item(1).getNodeValue();
		String Price = cPRNode.getAttributes().item(2).getNodeValue();
		String Quantity = cPRNode.getAttributes().item(3).getNodeValue();
		String ShipTime = cPRNode.getAttributes().item(4).getNodeValue();
		String Shipping = cPRNode.getAttributes().item(5).getNodeValue();
		String userID = cPRNode.getAttributes().item(6).getNodeValue();
		String userName = cPRNode.getAttributes().item(7).getNodeValue();
		String prodName = cPRNode.getAttributes().item(9).getNodeValue();
		String creatorID = cPRNode.getAttributes().item(0).getNodeValue();
		String creatorName = cPRNode.getAttributes().item(8).getNodeValue();
		Node userList = cPRNode.getFirstChild();
		NamedNodeMap uList = userList.getAttributes();
		ArrayList<Integer> IDList = new ArrayList<Integer>();
		for(int i = 0; i < uList.getLength(); i++){
			IDList.add(Integer.parseInt(uList.item(i).getNodeValue()));
		}
		
		replier.createCommitProdNegotiationReply(PNID, userID, userName, Price, Quantity, ShipTime, Shipping, prodName, creatorName, creatorID, IDList);
		
		return null;
	}

	private String handleServProdNegReq(Document cMess) throws NumberFormatException, SQLException, TransformerException, IOException {
		Element root = cMess.getDocumentElement();
		Node cPRNode = root.getFirstChild();
		String SNID = cPRNode.getAttributes().item(0).getNodeValue();
		String userID = cPRNode.getAttributes().item(1).getNodeValue();
		String userName = cPRNode.getAttributes().item(2).getNodeValue();
		Node userList = cPRNode.getFirstChild();
		NamedNodeMap uList = userList.getAttributes();
		ArrayList<Integer> IDList = new ArrayList<Integer>();
		for(int i = 0; i < uList.getLength(); i++){
			IDList.add(Integer.parseInt(uList.item(i).getNodeValue()));
		}
		return replier.createJoinServNegRep(SNID, userID, userName, IDList);
	}

	private String handleSearchServices(Document cMess) throws SQLException, TransformerException {
		Element root = cMess.getDocumentElement();
		Node searchNode = root.getFirstChild();
		String search = searchNode.getAttributes().item(0).getNodeValue().toString();
		String userID = searchNode.getAttributes().item(2).getNodeValue().toString();
		return replier.createServiceSearchReply(search, userID);
		
	}

	private String handleSearchProducts(Document cMess) throws SQLException, TransformerException {
		Element root = cMess.getDocumentElement();
		Node searchNode = root.getFirstChild();
		String search = searchNode.getAttributes().item(0).getNodeValue().toString();
		String userID = searchNode.getAttributes().item(2).getNodeValue().toString();
		return replier.createProductSearchReply(search, userID);
		
	}

	public void handleUpdateServNegotiation(Document cMess) throws NumberFormatException, DOMException, SQLException, TransformerException, IOException {
		Element root = cMess.getDocumentElement();
		   NodeList List = root.getChildNodes();
			
			int SNID = Integer.parseInt(List.item(1).getAttributes().item(1).getNodeValue());
			double Price = Double.parseDouble(List.item(1).getAttributes().item(0).getNodeValue());
			int timeReq = Integer.parseInt(List.item(1).getAttributes().item(2).getNodeValue());
			int userID = Integer.parseInt(List.item(1).getAttributes().item(3).getNodeValue());
			
			String globalReply = replier.createUpdateServNegotiationRep(SNID, Price, userID, timeReq);
		    ArrayList<Integer> IDList = new ArrayList<Integer>();
		    System.out.println(globalReply);
		    for(int i = 0; i < List.item(0).getAttributes().getLength(); i++){
		    	IDList.add(Integer.parseInt(List.item(0).getAttributes().item(i).getNodeValue()));
		        
		    }
			Server.sendToSeveral(IDList, globalReply);
		
	}

	public Document stringToDom(String message) throws SAXException, IOException{
	     System.out.println(message);
		return facBuild.parse(new InputSource(new StringReader(message)));
		
		
		
	}
	public String handleLoginRequest(Document cMess) throws NumberFormatException, IOException, SQLException{
		Element root = cMess.getDocumentElement();
		Node userIDNode = root.getFirstChild();
		String userID = userIDNode.getAttributes().item(0).getNodeValue();
		userID1 = Integer.parseInt(singleHandle.userExists(userID));
		return replier.loginReplyMessage(userID);
		 
	 }
	public String handleCreateProdNegRequest(Document cMess) throws SQLException, TransformerException{
		Element root = cMess.getDocumentElement();
		Node cPRNode = root.getFirstChild();
		cPNRInfo info = new cPNRInfo();
		info.price = Double.parseDouble(cPRNode.getAttributes().item(2).getNodeValue().toString());
		info.ship = Double.parseDouble(cPRNode.getAttributes().item(5).getNodeValue().toString());
		info.shipTime = Integer.parseInt(cPRNode.getAttributes().item(6).getNodeValue().toString());
		info.BS = Integer.parseInt(cPRNode.getAttributes().item(0).getNodeValue().toString());
		info.quant = Integer.parseInt(cPRNode.getAttributes().item(4).getNodeValue().toString());
		info.userID = Integer.parseInt(cPRNode.getAttributes().item(1).getNodeValue().toString());
		info.prodName = cPRNode.getAttributes().item(3).getNodeValue().toString();
		info.userName = cPRNode.getAttributes().item(7).getNodeValue().toString();
		return replier.createProdNegReply(info);
		 
	 }
	public String handleCreateServNegRequest(Document cMess) throws SQLException, TransformerException{
		Element root = cMess.getDocumentElement();
		Node cPRNode = root.getFirstChild();
		cPNRInfo info = new cPNRInfo();
		info.price = Double.parseDouble(cPRNode.getAttributes().item(2).getNodeValue().toString());
		info.BS = Integer.parseInt(cPRNode.getAttributes().item(0).getNodeValue().toString());
		info.userID = Integer.parseInt(cPRNode.getAttributes().item(1).getNodeValue().toString());
		info.prodName = cPRNode.getAttributes().item(3).getNodeValue().toString();
		info.timeReq = Integer.parseInt(cPRNode.getAttributes().item(4).getNodeValue().toString());
		info.userName = cPRNode.getAttributes().item(5).getNodeValue().toString();
		
		return replier.createServNegReply(info);
		
	}
	public String handleNegotiationsByPNID(Document cMess) throws SQLException, NumberFormatException, TransformerException, IOException{
		Element root = cMess.getDocumentElement();
		Node cPRNode = root.getFirstChild();
		
		String PNID = cPRNode.getAttributes().item(0).getNodeValue();
		
		return replier.createProdNegotiationsReply(PNID);
		
	}
	public String handleNegotiationInstanceByPNID(Document cMess) throws NumberFormatException, TransformerException, SQLException, IOException {
		Element root = cMess.getDocumentElement();
		Node cPRNode = root.getFirstChild();
		
		String PNID = cPRNode.getAttributes().item(0).getNodeValue();
		
		return replier.createProdNegotiationsInstanceReply(PNID);
	}
	public String handleNegotiationInstanceBySNID(Document cMess) throws NumberFormatException, TransformerException, SQLException, IOException {
		Element root = cMess.getDocumentElement();
		Node cPRNode = root.getFirstChild();
		
		String PNID = cPRNode.getAttributes().item(0).getNodeValue();
		
		return replier.createServNegotiationsInstanceReply(PNID);
	}
	public String handleNegotiationsBySNID(Document cMess) throws SQLException, NumberFormatException, TransformerException, IOException{
		Element root = cMess.getDocumentElement();
		Node cPRNode = root.getFirstChild();
		
		String PNID = cPRNode.getAttributes().item(0).getNodeValue();
		
		return replier.createServNegotiationsReply(PNID);
		
	}
   public void handleUpdateProdNegotiation(Document cMess) throws NumberFormatException, TransformerException, SQLException, IOException {
	   Element root = cMess.getDocumentElement();
	   NodeList List = root.getChildNodes();
		
		int PNID = Integer.parseInt(List.item(1).getAttributes().item(0).getNodeValue());
		double Price = Double.parseDouble(List.item(1).getAttributes().item(1).getNodeValue());
		int Quant = Integer.parseInt(List.item(1).getAttributes().item(2).getNodeValue());
		int ShipTime = Integer.parseInt(List.item(1).getAttributes().item(3).getNodeValue());
		double Shipp = Double.parseDouble(List.item(1).getAttributes().item(4).getNodeValue());
		int userID = Integer.parseInt(List.item(1).getAttributes().item(5).getNodeValue());
		
		String globalReply = replier.createUpdateProdNegotiationRep(PNID, Price, ShipTime, Shipp, userID, Quant);
	    ArrayList<Integer> IDList = new ArrayList<Integer>();
	    System.out.println(globalReply);
	    for(int i = 0; i < List.item(0).getAttributes().getLength(); i++){
	    	IDList.add(Integer.parseInt(List.item(0).getAttributes().item(i).getNodeValue()));
	        
	    }
		Server.sendToSeveral(IDList, globalReply);
	}
    public String handleJoinProdNegReq(Document cMess) throws NumberFormatException, TransformerException, SQLException, IOException {
		Element root = cMess.getDocumentElement();
		Node cPRNode = root.getFirstChild();
		String PNID = cPRNode.getAttributes().item(0).getNodeValue();
		String userID = cPRNode.getAttributes().item(1).getNodeValue();
		String userName = cPRNode.getAttributes().item(2).getNodeValue();
		Node userList = cPRNode.getFirstChild();
		NamedNodeMap uList = userList.getAttributes();
		ArrayList<Integer> IDList = new ArrayList<Integer>();
		for(int i = 0; i < uList.getLength(); i++){
			IDList.add(Integer.parseInt(uList.item(i).getNodeValue()));
		}
		return replier.createJoinProdNegRep(PNID, userID, userName, IDList);
	}
    public String handleRetCComProd(Document cMess) throws SQLException, TransformerException {
    	Element root = cMess.getDocumentElement();
    	Node cPRNode = root.getFirstChild();
    	String PNID = cPRNode.getAttributes().item(0).getNodeValue();
    	
    	return replier.createCComProdRep(PNID);
    }
   
   }



}
