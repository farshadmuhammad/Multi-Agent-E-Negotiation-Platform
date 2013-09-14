import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MyNegotiationsPage extends JPanel {
	JButton backBtn = new JButton("Back");
	JList myNegList;
	DefaultListModel<String> myListModel;
	JButton enterBtn = new JButton("Enter");;
	JComboBox choserBox;
	JButton updateBtn;
	String currState;
	/**
	 * Create the panel.
	 */
	public MyNegotiationsPage() {
		setLayout(null);
		
		JLabel lblMyNegotiations = new JLabel("My Negotiations");
		lblMyNegotiations.setBounds(98, 11, 112, 17);
		lblMyNegotiations.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblMyNegotiations);
		
		myListModel = new DefaultListModel<String>();
		
		
		backBtn.setBounds(45, 264, 89, 23);
		add(backBtn);
		
		
		enterBtn.setBounds(157, 264, 89, 23);
		add(enterBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 39, 248, 197);
		add(scrollPane);
		
		myNegList = new JList(myListModel);
		scrollPane.setViewportView(myNegList);
		
		 choserBox = new JComboBox();
		choserBox.setBounds(314, 103, 271, 20);
		add(choserBox);
		choserBox.addItem("My Created Prod Negotiations");
		choserBox.addItem("My Created Serv Negotiations");
		choserBox.addItem("My Joined Prod Negotiations");
		choserBox.addItem("My Joined Serv Negotiations");
		
		
		JLabel lblChoseListType = new JLabel("Choose List Type");
		lblChoseListType.setBounds(350, 76, 97, 14);
		add(lblChoseListType);
		
		updateBtn = new JButton("Update");
		updateBtn.setBounds(397, 150, 118, 23);
		add(updateBtn);
		
		
	}
    public void setMyProdNegList(ArrayList<ProdNegInstList> prodNegotiations){
    	myListModel.clear();
    	if(prodNegotiations.size() != 0){
    	for(int i = 0; i < prodNegotiations.size(); i++){
    	    if(prodNegotiations.get(i).Type == 1)
    		myListModel.addElement(new String(prodNegotiations.get(i).prodName +", Buying, " + prodNegotiations.get(i).PNID));
    	    else
    	    myListModel.addElement(new String(prodNegotiations.get(i).prodName +", Selling, " + prodNegotiations.get(i).PNID));	
    	    currState = "PNID";
    	}
    	}
    }
   
    public void setMyServNegList(ArrayList<ServNegInstList> servNegotiations){
    	myListModel.clear();
    	if(servNegotiations.size() != 0){
    	for(int i = 0; i < servNegotiations.size(); i++){
    		 if(servNegotiations.get(i).Type == 1)
    		myListModel.addElement(new String(servNegotiations.get(i).servName +", Buying, " + servNegotiations.get(i).SNID));
    		 else
    		myListModel.addElement(new String(servNegotiations.get(i).servName +", Selling, " + servNegotiations.get(i).SNID));	 
    		currState = "SNID";
    	}
    	}
    }
   
}
