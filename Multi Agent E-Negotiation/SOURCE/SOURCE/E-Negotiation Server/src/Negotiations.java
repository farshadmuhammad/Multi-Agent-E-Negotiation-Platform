
public class Negotiations {
User user;
double Price;
double Shipping;
int Quantity;
int timeReq;
boolean isCommited;
int shipTime;


	public Negotiations(){
		user = new User();
		Price = 0;
		Shipping = 0;
		Quantity = 0;
		timeReq = 0;
		isCommited = false;
		shipTime = 0;
	}
	
	public Negotiations(User user1, double pR,double sH, int qR, int tR, boolean iC, int sT){
		user = user1;
		Price = pR;
		Shipping = sH;
		Quantity = qR;
		timeReq = tR;
		isCommited = iC;
		shipTime = sT;
	}
}
