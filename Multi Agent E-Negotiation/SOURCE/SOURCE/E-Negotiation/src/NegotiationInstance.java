import java.util.ArrayList;


public class NegotiationInstance {
int creatorID;
String creatorName;
String prodOrServName;
int type;
int PNID;
int SNID;
int currentPart;
Negotiations creator;
Negotiations User1;
Negotiations User2;
Negotiations User3;
Negotiations User4;

 public NegotiationInstance(){
	 creatorID = 0;
	 creatorName = " ";
	 prodOrServName = " ";
	 type = 0;
	 PNID = 0;
	 SNID = 0;
	 creator = new Negotiations();
	 User1 = new Negotiations();
	 User2 = new Negotiations();
	 User3 = new Negotiations();
	 User4 = new Negotiations();
	 currentPart = 0;
 }

 public NegotiationInstance(int CID, String uN, String pSName, int type1, int PNID1){
	 creatorID = CID;
	 creatorName = uN;
	 prodOrServName = pSName;
	 type = type1;
	 PNID = PNID1;  
	 creator = new Negotiations();
	 User1 = new Negotiations();
	 User2 = new Negotiations();
	 User3 = new Negotiations();
	 User4 = new Negotiations();
	 currentPart = 0;
 }
 public void setCreator(Negotiations cret){
	 creator = cret;
 }
 public void setUser1 (Negotiations user){
	 User1 = user;
 }
 public void setUser2 (Negotiations user){
	 User2 = user;
 }
 public void setUser3 (Negotiations user){
	 User3 = user;
 }
 public void setUser4 (Negotiations user){
	 User4 = user;
 }

public void resolveProdUpdate(int userID, double price,
		double shipping, int quantity, int shippTime) {
	if(userID == creatorID){
		creator.Price = price;
		creator.Quantity = quantity;
		creator.Shipping = shipping;
		creator.shipTime = shippTime;
		MainPage.updateProdCreateView();
	}
	if(userID == User1.user.getUserID()){
		User1.Price = price;
		User1.Quantity = quantity;
		User1.Shipping = shipping;
		User1.shipTime = shippTime;
		MainPage.updateProdUser1View();
	}
	if(userID == User2.user.getUserID()){
		User2.Price = price;
		User2.Quantity = quantity;
		User2.Shipping = shipping;
		User2.shipTime = shippTime;
		MainPage.updateProdUser2View();
	}
	if(userID == User3.user.getUserID()){
		User3.Price = price;
		User3.Quantity = quantity;
		User3.Shipping = shipping;
		User3.shipTime = shippTime;
		MainPage.updateProdUser3View();
	}
	if(userID == User4.user.getUserID()){
		User4.Price = price;
		User4.Quantity = quantity;
		User4.Shipping = shipping;
		User4.shipTime = shippTime;
		MainPage.updateProdUser4View();
	}
	
}
public void resolveServUpdate(int userID, double price,
		 int timeReq) {
	if(userID == creatorID){
		creator.Price = price;
		creator.timeReq = timeReq;
		MainPage.updateServCreateView();
	}
	if(userID == User1.user.getUserID()){
		User1.Price = price;
		User1.timeReq = timeReq;
		MainPage.updateServUser1View();
	}
	if(userID == User2.user.getUserID()){
		User2.Price = price;
		User2.timeReq = timeReq;
		MainPage.updateServUser2View();
	}
	if(userID == User3.user.getUserID()){
		User3.Price = price;
		User3.timeReq = timeReq;
		MainPage.updateServUser3View();
	}
	if(userID == User4.user.getUserID()){
		User4.Price = price;
		User4.timeReq = timeReq;
		MainPage.updateServUser4View();
	}
	
}
}
