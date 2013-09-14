import java.util.ArrayList;


public class ObjectWrapper {
   User mainUser;
   ArrayList<ProdNegInstList> myProdNegotiations;
   ArrayList<ServNegInstList> myServNegotiations;
   ArrayList<ProdNegInstList> myJProdNegotiations;
   ArrayList<ServNegInstList> myJServNegotiations;
   ArrayList<ProdNegInstList> mySProdNegotiations;
   ArrayList<ServNegInstList> mySServNegotiations;
   NegotiationInstance mainNegInst;
   ArrayList<String> user1Chat;
   ArrayList<String> user2Chat;
   ArrayList<String> user3Chat;
   ArrayList<String> user4Chat;
   ArrayList<String> createChat;
   ArrayList<ProdNegInstList> myCComProdNegotiations;
   ArrayList<ProdNegInstList> myJComProdNegotiations;
   ArrayList<ServNegInstList> myCComServNegotiations;
   ArrayList<ServNegInstList> myJComServNegotiations;
   public ObjectWrapper(){
	   mainUser = new User();
	   myProdNegotiations = new ArrayList<ProdNegInstList>();
	   myJProdNegotiations = new ArrayList<ProdNegInstList>();
	   mySProdNegotiations = new ArrayList<ProdNegInstList>();
	   myServNegotiations = new ArrayList<ServNegInstList>();
	   myJServNegotiations = new ArrayList<ServNegInstList>();
	   mySServNegotiations = new ArrayList<ServNegInstList>();
	   mainNegInst = new NegotiationInstance();
	   myCComProdNegotiations = new ArrayList<ProdNegInstList>();
	   myJComProdNegotiations = new ArrayList<ProdNegInstList>();
	   myCComServNegotiations = new ArrayList<ServNegInstList>();
	   myJComServNegotiations = new ArrayList<ServNegInstList>();
	 
   }
 public void addMyCComProdNegotiation(ProdNegInstList prodNeg){
		myCComProdNegotiations.add(prodNeg);
		
	}
 public void addMJComProdNegotiation(ProdNegInstList prodNeg){
		myJComProdNegotiations.add(prodNeg);
		
	}
 public void addMyCComServNegotiation(ServNegInstList servNeg) {
		myCComServNegotiations.add(servNeg);
		
	}
 public void addMyJComServNegotiation(ServNegInstList servNeg) {
		myJComServNegotiations.add(servNeg);
		
	}
 
public void addMyProdNegotiation(ProdNegInstList prodNeg){
	myProdNegotiations.add(prodNeg);
	
}
public void addMyJProdNegotiation(ProdNegInstList prodNeg){
	myJProdNegotiations.add(prodNeg);
	
}
public void addMyServNegotiation(ServNegInstList servNeg){
	myServNegotiations.add(servNeg);
	
}
public void addMyJServNegotiation(ServNegInstList servNeg){
	myJServNegotiations.add(servNeg);
	
}
public void setMainNegInst(NegotiationInstance nI){
	mainNegInst = nI;
}
public void addMySServNegotiation(ServNegInstList servNeg) {
	mySServNegotiations.add(servNeg);
	
}
public void addMySProdNegotiation(ProdNegInstList prodNeg) {
	mySProdNegotiations.add(prodNeg);
	
}

}
