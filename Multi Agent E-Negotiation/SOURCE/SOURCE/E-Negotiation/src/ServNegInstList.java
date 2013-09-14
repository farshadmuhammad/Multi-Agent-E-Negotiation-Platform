
public class ServNegInstList {
    String servName;
    int creatorID;
    String userName;
    int SNID;
    int Type;
    
    public ServNegInstList(){
    	servName = " ";
    	creatorID = 0;
        userName = " ";
        SNID = 0;
        Type = 0;
    }
    public ServNegInstList(String sN, int CID, String uN, int SID, int tY){
    	servName = sN;
    	creatorID = CID;
    	 userName = uN;
    	SNID = SID;
    	Type = tY;
    }
}
