import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;


public class CreateServNegotPage extends JPanel {
	JTextField servNameField;
	JTextField priceField;
	JTextField timeReqField;
	JTextField descField;
	JRadioButton buyRadBtn;
	JRadioButton sellRadBtn;
	JButton cancelBtn = new JButton("Cancel");
	JButton createBtn = new JButton("Create");
	/**
	 * Create the panel.
	 */
	public CreateServNegotPage() {
		setLayout(null);
		
		JLabel lblServiceName = new JLabel("Service Name");
		lblServiceName.setBounds(43, 11, 89, 14);
		add(lblServiceName);
		
		servNameField = new JTextField();
		servNameField.setBounds(151, 8, 204, 20);
		add(servNameField);
		servNameField.setColumns(10);
		
		buyRadBtn = new JRadioButton("Buying");
		buyRadBtn.setBounds(56, 56, 109, 23);
		add(buyRadBtn);
		
		sellRadBtn = new JRadioButton("Selling");
		sellRadBtn.setBounds(170, 56, 109, 23);
		add(sellRadBtn);
		
		JLabel lblPricePerHour = new JLabel("Price");
		lblPricePerHour.setBounds(43, 98, 42, 14);
		add(lblPricePerHour);
		
		priceField = new JTextField();
		priceField.setBounds(79, 95, 86, 20);
		add(priceField);
		priceField.setColumns(10);
		
		JLabel lblTimeRequired = new JLabel("Time Required");
		lblTimeRequired.setBounds(43, 143, 89, 14);
		add(lblTimeRequired);
		
		timeReqField = new JTextField();
		timeReqField.setBounds(136, 140, 86, 20);
		add(timeReqField);
		timeReqField.setColumns(10);
		
		
		cancelBtn.setBounds(118, 241, 89, 23);
		add(cancelBtn);
		
		
		createBtn.setBounds(235, 241, 89, 23);
		add(createBtn);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(318, 82, 65, 14);
		add(lblDescription);
		
		descField = new JTextField();
		descField.setBounds(276, 107, 142, 111);
		add(descField);
		descField.setColumns(10);

	}

}
