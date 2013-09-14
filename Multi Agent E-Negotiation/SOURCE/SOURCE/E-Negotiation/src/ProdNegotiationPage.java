import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListDataListener;


public class ProdNegotiationPage extends JPanel {
	 JTextField creatPriceField;
	 JTextField creatQuantField;
	 JTextField creatShipField;
	 JTextField creatShippTimeField;
	 JTextField usr1PriceField;
	 JTextField usr1QuantField;
	 JTextField usr1ShippField;
	 JTextField usr1ShippTimeField;
	 JTextField usr2PriceField;
	 JTextField usr2QuantField;
	 JTextField usr2ShippField;
	 JTextField usr2ShippTimeField;
	 JTextField usr3PriceField;
	 JTextField usr3QuantField;
	 JTextField usr3ShippField;
	 JTextField usr3ShippTimeField;
	 JTextField usr4PriceField;
	 JTextField usr4QuantField;
	 JTextField usr4ShippField;
	 JTextField usr4ShippTimeField;
	 JTextField lowCurrPriceField;
	 JTextField lowCurrShippField;
	 JTextField lowCurrShippTimeField;
	 JLabel lblProductName;
	 JLabel lblUseridCreator;
	 JLabel lblUserID1;
	 JLabel lblUserID2;
	 JLabel lblUserID3;
	 JLabel lblUserID4;
	 JButton usr1CommitBtn;
	 JButton usr2CommitBtn;
	 JButton usr3CommitBtn;
	 JButton usr4CommitBtn;
	 JButton usr1SendBtn;
	 JButton usr2SendBtn;
	 JButton usr3SendBtn;
	 JButton usr4SendBtn;
	 JButton creatSendBtn;
	 JButton joinBtn;
	 JButton withdrawBtn;
	JButton backToMainBtn = new JButton("Back To Main Page");
	JList chatList;
	static JButton btnSendChat;
	JTextArea chatArea;
	ListSelectionModel lsm;
	JTextField chatField;
	JButton dataBtn;

	/**
	 * Create the panel.
	 */
	public ProdNegotiationPage() {
		setLayout(null);
		
		lblUseridCreator = new JLabel("userID");
		lblUseridCreator.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUseridCreator.setBounds(89, 89, 102, 14);
		add(lblUseridCreator);
		
		lblProductName = new JLabel("Product Name");
		lblProductName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProductName.setBounds(320, 27, 173, 31);
		add(lblProductName);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(60, 114, 46, 14);
		add(lblPrice);
		
		creatPriceField = new JTextField();
		creatPriceField.setBounds(37, 132, 80, 20);
		add(creatPriceField);
		creatPriceField.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(145, 114, 54, 14);
		add(lblQuantity);
		
		creatQuantField = new JTextField();
		creatQuantField.setBounds(127, 132, 86, 20);
		add(creatQuantField);
		creatQuantField.setColumns(10);
		
		JLabel lblShipping = new JLabel("Shipping");
		lblShipping.setBounds(235, 114, 61, 14);
		add(lblShipping);
		
		creatShipField = new JTextField();
		creatShipField.setBounds(222, 132, 86, 20);
		add(creatShipField);
		creatShipField.setColumns(10);
		
		JLabel lblShippTime = new JLabel("Shipp. Time");
		lblShippTime.setBounds(331, 114, 84, 14);
		add(lblShippTime);
		
		creatShippTimeField = new JTextField();
		creatShippTimeField.setBounds(320, 132, 95, 20);
		add(creatShippTimeField);
		creatShippTimeField.setColumns(10);
		
		lblUserID1 = new JLabel("");
		lblUserID1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUserID1.setBounds(477, 89, 102, 14);
		add(lblUserID1);
		
		JLabel label_1 = new JLabel("Price");
		label_1.setBounds(521, 114, 46, 14);
		add(label_1);
		
		usr1PriceField = new JTextField();
		usr1PriceField.setColumns(10);
		usr1PriceField.setBounds(509, 139, 80, 20);
		add(usr1PriceField);
		
		JLabel label_2 = new JLabel("Quantity");
		label_2.setBounds(610, 114, 61, 14);
		add(label_2);
		
		usr1QuantField = new JTextField();
		usr1QuantField.setColumns(10);
		usr1QuantField.setBounds(599, 139, 86, 20);
		add(usr1QuantField);
		
		JLabel label_3 = new JLabel("Shipping");
		label_3.setBounds(701, 114, 61, 14);
		add(label_3);
		
		usr1ShippField = new JTextField();
		usr1ShippField.setColumns(10);
		usr1ShippField.setBounds(690, 139, 86, 20);
		add(usr1ShippField);
		
		JLabel label_4 = new JLabel("Shipp. Time");
		label_4.setBounds(793, 114, 88, 14);
		add(label_4);
		
		usr1ShippTimeField = new JTextField();
		usr1ShippTimeField.setColumns(10);
		usr1ShippTimeField.setBounds(786, 139, 95, 20);
		add(usr1ShippTimeField);
	
		lblUserID2 = new JLabel("");
		lblUserID2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUserID2.setBounds(477, 192, 102, 14);
		add(lblUserID2);
		
		JLabel label_6 = new JLabel("Price");
		label_6.setBounds(529, 217, 46, 14);
		add(label_6);
		
		usr2PriceField = new JTextField();
		usr2PriceField.setColumns(10);
		usr2PriceField.setBounds(509, 242, 80, 20);
		add(usr2PriceField);
		
		JLabel label_7 = new JLabel("Quantity");
		label_7.setBounds(610, 217, 61, 14);
		add(label_7);
		
		usr2QuantField = new JTextField();
		usr2QuantField.setColumns(10);
		usr2QuantField.setBounds(599, 242, 86, 20);
		add(usr2QuantField);
		
		JLabel label_8 = new JLabel("Shipping");
		label_8.setBounds(701, 217, 61, 14);
		add(label_8);
		
		usr2ShippField = new JTextField();
		usr2ShippField.setColumns(10);
		usr2ShippField.setBounds(690, 242, 86, 20);
		add(usr2ShippField);
		
		JLabel label_9 = new JLabel("Shipp. Time");
		label_9.setBounds(793, 217, 73, 14);
		add(label_9);
		
		usr2ShippTimeField = new JTextField();
		usr2ShippTimeField.setColumns(10);
		usr2ShippTimeField.setBounds(786, 242, 95, 20);
		add(usr2ShippTimeField);
		
		lblUserID3 = new JLabel("");
		lblUserID3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUserID3.setBounds(477, 293, 102, 14);
		add(lblUserID3);
		
		JLabel label_11 = new JLabel("Price");
		label_11.setBounds(529, 318, 46, 14);
		add(label_11);
		
		usr3PriceField = new JTextField();
		usr3PriceField.setColumns(10);
		usr3PriceField.setBounds(509, 343, 80, 20);
		add(usr3PriceField);
		
		JLabel label_12 = new JLabel("Quantity");
		label_12.setBounds(610, 318, 61, 14);
		add(label_12);
		
		usr3QuantField = new JTextField();
		usr3QuantField.setColumns(10);
		usr3QuantField.setBounds(599, 343, 86, 20);
		add(usr3QuantField);
		
		JLabel label_13 = new JLabel("Shipping");
		label_13.setBounds(701, 318, 61, 14);
		add(label_13);
		
		usr3ShippField = new JTextField();
		usr3ShippField.setColumns(10);
		usr3ShippField.setBounds(690, 343, 86, 20);
		add(usr3ShippField);
		
		JLabel label_14 = new JLabel("Shipp. Time");
		label_14.setBounds(793, 318, 73, 14);
		add(label_14);
		
		usr3ShippTimeField = new JTextField();
		usr3ShippTimeField.setColumns(10);
		usr3ShippTimeField.setBounds(786, 343, 95, 20);
		add(usr3ShippTimeField);
		
		lblUserID4 = new JLabel("");
		lblUserID4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUserID4.setBounds(477, 391, 102, 14);
		add(lblUserID4);
		
		JLabel label_16 = new JLabel("Price");
		label_16.setBounds(529, 420, 46, 14);
		add(label_16);
		
		usr4PriceField = new JTextField();
		usr4PriceField.setColumns(10);
		usr4PriceField.setBounds(509, 445, 80, 20);
		add(usr4PriceField);
		
		JLabel label_17 = new JLabel("Quantity");
		label_17.setBounds(610, 421, 61, 14);
		add(label_17);
		
		usr4QuantField = new JTextField();
		usr4QuantField.setColumns(10);
		usr4QuantField.setBounds(599, 445, 86, 20);
		add(usr4QuantField);
		
		JLabel label_18 = new JLabel("Shipping");
		label_18.setBounds(701, 420, 61, 14);
		add(label_18);
		
		usr4ShippField = new JTextField();
		usr4ShippField.setColumns(10);
		usr4ShippField.setBounds(690, 445, 86, 20);
		add(usr4ShippField);
		
		JLabel label_19 = new JLabel("Shipp. Time");
		label_19.setBounds(793, 420, 73, 14);
		add(label_19);
		
		usr4ShippTimeField = new JTextField();
		usr4ShippTimeField.setColumns(10);
		usr4ShippTimeField.setBounds(786, 445, 95, 20);
		add(usr4ShippTimeField);
		
		usr1CommitBtn = new JButton("Commit");
		usr1CommitBtn.setBounds(650, 85, 89, 23);
		add(usr1CommitBtn);
		
		usr2CommitBtn = new JButton("Commit");
		usr2CommitBtn.setBounds(650, 180, 89, 23);
		add(usr2CommitBtn);
		
		usr3CommitBtn = new JButton("Commit");
		usr3CommitBtn.setBounds(650, 284, 89, 23);
		add(usr3CommitBtn);
		
		usr4CommitBtn = new JButton("Commit");
		usr4CommitBtn.setBounds(650, 382, 89, 23);
		add(usr4CommitBtn);
		
		JLabel lblLowestCurrentPrice = new JLabel("Lowest Current Price");
		lblLowestCurrentPrice.setBounds(37, 217, 125, 14);
		add(lblLowestCurrentPrice);
		
		JLabel lblLowestCurrentShipping = new JLabel("Lowest Current Shipping");
		lblLowestCurrentShipping.setBounds(37, 256, 142, 14);
		add(lblLowestCurrentShipping);
		
		JLabel lblLowestCurrentShipping_1 = new JLabel("Lowest Current Shipping Time");
		lblLowestCurrentShipping_1.setBounds(37, 293, 176, 14);
		add(lblLowestCurrentShipping_1);
		
		lowCurrPriceField = new JTextField();
		lowCurrPriceField.setBounds(164, 214, 102, 20);
		add(lowCurrPriceField);
		lowCurrPriceField.setColumns(10);
		
		lowCurrShippField = new JTextField();
		lowCurrShippField.setBounds(189, 253, 107, 20);
		add(lowCurrShippField);
		lowCurrShippField.setColumns(10);
		
		lowCurrShippTimeField = new JTextField();
		lowCurrShippTimeField.setBounds(234, 290, 102, 20);
		add(lowCurrShippTimeField);
		lowCurrShippTimeField.setColumns(10);
		
		withdrawBtn = new JButton("Withdraw From Negotiation");
		withdrawBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		withdrawBtn.setBounds(60, 387, 191, 23);
		add(withdrawBtn);
		
		
		backToMainBtn.setBounds(60, 416, 191, 23);
		add(backToMainBtn);
		
		usr1SendBtn = new JButton("Send");
		usr1SendBtn.setBounds(775, 85, 89, 23);
		add(usr1SendBtn);
		
		usr2SendBtn = new JButton("Send");
		usr2SendBtn.setBounds(775, 180, 89, 23);
		add(usr2SendBtn);
		
		usr3SendBtn = new JButton("Send");
		usr3SendBtn.setBounds(775, 284, 89, 23);
		add(usr3SendBtn);
		
		usr4SendBtn = new JButton("Send");
		usr4SendBtn.setBounds(775, 382, 89, 23);
		add(usr4SendBtn);
		
		creatSendBtn = new JButton("Send");
		creatSendBtn.setBounds(89, 165, 89, 23);
		add(creatSendBtn);
		
		JButton creatChatBtn = new JButton("Chat");
		creatChatBtn.setBounds(202, 165, 89, 23);
		add(creatChatBtn);
		
		joinBtn =  new JButton("Join Negotiation");
		joinBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		joinBtn.setBounds(60, 353, 191, 23);
		add(joinBtn);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(37, 503, 690, 121);
		add(scrollPane_1);
		
		 chatArea = new JTextArea();
		scrollPane_1.setViewportView(chatArea);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(761, 503, 132, 125);
		add(scrollPane);
		
		chatList = new JList();
		scrollPane.setViewportView(chatList);
		
		chatField = new JTextField();
		chatField.setBounds(105, 658, 566, 20);
		add(chatField);
		chatField.setColumns(10);
		
		JLabel lblChat = new JLabel("Chat:");
		lblChat.setBounds(60, 661, 46, 14);
		add(lblChat);
		
		 btnSendChat = new JButton("Send Chat");
		btnSendChat.setBounds(725, 657, 89, 23);
		add(btnSendChat);
		
		dataBtn = new JButton("Data Mine");
		dataBtn.setBounds(60, 444, 191, 23);
		add(dataBtn);

	}
 public void disableFields(NegotiationInstance i, int participants, User mainUser){
	 if(mainUser.getUserID() != i.creatorID){
		 creatPriceField.setEnabled(false);
		 creatQuantField.setEnabled(false);
		 creatShipField.setEnabled(false);
		 creatShippTimeField.setEnabled(false);
		 creatSendBtn.setEnabled(false);
		 usr1CommitBtn.setEnabled(false);
		 usr2CommitBtn.setEnabled(false);
		 usr3CommitBtn.setEnabled(false);
		 usr4CommitBtn.setEnabled(false);
		 
	 }
     if((mainUser.getUserID() == i.creatorID || !(i.User1.user.getUserID() == mainUser.getUserID()))){
    	 usr1PriceField.setEnabled(false);
		 usr1QuantField.setEnabled(false);
		 usr1ShippField.setEnabled(false);
		 usr1ShippTimeField.setEnabled(false);
		 usr1SendBtn.setEnabled(false);
		 
		 if(mainUser.getUserID() != i.creatorID){
			 usr1PriceField.setText("");
			 usr1QuantField.setText("");
			 usr1ShippField.setText("");
			 usr1ShippTimeField.setText(""); 
		 }
     }
     if((mainUser.getUserID() == i.creatorID || !(i.User2.user.getUserID() == mainUser.getUserID()))){
    	 usr2PriceField.setEnabled(false);
		 usr2QuantField.setEnabled(false);
		 usr2ShippField.setEnabled(false);
		 usr2ShippTimeField.setEnabled(false);
		 usr2SendBtn.setEnabled(false);
		 if(mainUser.getUserID() != i.creatorID){
			 usr2PriceField.setText("");
			 usr2QuantField.setText("");
			 usr2ShippField.setText("");
			 usr2ShippTimeField.setText(""); 
		 }
     }
     if((mainUser.getUserID() == i.creatorID || !(i.User3.user.getUserID() == mainUser.getUserID()))){
    	 usr3PriceField.setEnabled(false);
		 usr3QuantField.setEnabled(false);
		 usr3ShippField.setEnabled(false);
		 usr3ShippTimeField.setEnabled(false);
		 usr3SendBtn.setEnabled(false);
		 if(mainUser.getUserID() != i.creatorID){
			 usr3PriceField.setText("");
			 usr3QuantField.setText("");
			 usr3ShippField.setText("");
			 usr3ShippTimeField.setText(""); 
		 }
     }
     if((mainUser.getUserID() == i.creatorID || !(i.User4.user.getUserID() == mainUser.getUserID()))){
    	 usr4PriceField.setEnabled(false);
		 usr4QuantField.setEnabled(false);
		 usr4ShippField.setEnabled(false);
		 usr4ShippTimeField.setEnabled(false);
		 usr4SendBtn.setEnabled(false);
		 if(mainUser.getUserID() != i.creatorID){
			 usr4PriceField.setText("");
			 usr4QuantField.setText("");
			 usr4ShippField.setText("");
			 usr4ShippTimeField.setText(""); 
		 }
     if(mainUser.getUserID() == i.creatorID || mainUser.getUserID() == i.User1.user.getUserID() || mainUser.getUserID() == i.User2.user.getUserID()  
    		 || mainUser.getUserID() == i.User3.user.getUserID() || mainUser.getUserID() == i.User4.user.getUserID() || participants > 4){
    	 joinBtn.setEnabled(false);
     }
    	 
     }
    	 
 }
public void purgeAllFields() {
	 usr1PriceField.setEnabled(true);
	 usr1QuantField.setEnabled(true);
	 usr1ShippField.setEnabled(true);
	 usr1ShippTimeField.setEnabled(true);
	 usr1SendBtn.setEnabled(true);
	 
	 usr2PriceField.setEnabled(true);
	 usr2QuantField.setEnabled(true);
	 usr2ShippField.setEnabled(true);
	 usr2ShippTimeField.setEnabled(true);
	 usr2SendBtn.setEnabled(true);
	 
	 usr3PriceField.setEnabled(true);
	 usr3QuantField.setEnabled(true);
	 usr3ShippField.setEnabled(true);
	 usr3ShippTimeField.setEnabled(true);
	 usr3SendBtn.setEnabled(true);
	 
	 usr4PriceField.setEnabled(true);
	 usr4QuantField.setEnabled(true);
	 usr4ShippField.setEnabled(true);
	 usr4ShippTimeField.setEnabled(true);
	 usr4SendBtn.setEnabled(true);
	
	 lblUserID1.setText("");
	 usr1PriceField.setText("");
	
	 usr1QuantField.setText("");
	 usr1ShippField.setText("");
	 usr1ShippTimeField.setText(""); 
	 lblUserID2.setText("");
	 usr2PriceField.setText("");
	 usr2QuantField.setText("");
	 usr2ShippField.setText("");
	 usr2ShippTimeField.setText(""); 
	 lblUserID3.setText("");
	 usr3PriceField.setText("");
	 usr3QuantField.setText("");
	 usr3ShippField.setText("");
	 usr3ShippTimeField.setText(""); 
	 lblUserID4.setText("");
	 usr4PriceField.setText("");
	 usr4QuantField.setText("");
	 usr4ShippField.setText("");
	 usr4ShippTimeField.setText(""); 
	 joinBtn.setEnabled(true);
	 lowCurrPriceField.setText("");
	 lowCurrShippField.setText("");
	 lowCurrShippTimeField.setText("");
	 usr1CommitBtn.setEnabled(true);
	 usr2CommitBtn.setEnabled(true);
	 usr3CommitBtn.setEnabled(true);
	 usr4CommitBtn.setEnabled(true);
}
public void setUserList(ArrayList<String> userNames){
	DefaultListModel<String> myListModel = new DefaultListModel<String>();
	myListModel.clear();
	if(userNames.size() != 0){
	for(int i = 0; i < userNames.size(); i++){
	    
		myListModel.addElement(new String(userNames.get(i)));
		
	}
	}
	chatList.setModel(myListModel);
	lsm = chatList.getSelectionModel();
	lsm.addListSelectionListener(new SharedListSelectionHandler());
	
}
public static void enableChat(){
	btnSendChat.setEnabled(true);
}
public static void disableChat(){
	btnSendChat.setEnabled(false);
}
}
