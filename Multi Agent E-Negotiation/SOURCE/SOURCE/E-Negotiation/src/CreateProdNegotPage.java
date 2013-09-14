import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class CreateProdNegotPage extends JPanel {
	JTextField prodNameField;
	JTextField priceField;
	JTextField quantField;
	JTextField shippField;
	JTextField shippTimeField;
	JTextField descField;
	JRadioButton buyRadBtn;
	JRadioButton sellRadBtn;
	JButton cancelBtn = new JButton("Cancel");
	JButton createBtn = new JButton("Create");

	/**
	 * Create the panel.
	 */
	public CreateProdNegotPage() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Product Name");
		lblNewLabel.setBounds(10, 11, 101, 14);
		add(lblNewLabel);
		
		prodNameField = new JTextField();
		prodNameField.setBounds(121, 8, 258, 20);
		add(prodNameField);
		prodNameField.setColumns(10);
		
		buyRadBtn = new JRadioButton("Buying");
		buyRadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(buyRadBtn.isSelected())
					sellRadBtn.setSelected(false);
			}
		});
		buyRadBtn.setBounds(44, 56, 109, 23);
		add(buyRadBtn);
		
	    sellRadBtn = new JRadioButton("Selling");
	    sellRadBtn.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if(sellRadBtn.isSelected())
					buyRadBtn.setSelected(false);
	    	}
	    });
		sellRadBtn.setBounds(180, 56, 109, 23);
		add(sellRadBtn);
		
		JLabel lblNewLabel_1 = new JLabel("Price");
		lblNewLabel_1.setBounds(30, 93, 52, 20);
		add(lblNewLabel_1);
		
		priceField = new JTextField();
		priceField.setBounds(77, 93, 86, 20);
		add(priceField);
		priceField.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(30, 129, 52, 14);
		add(lblQuantity);
		
		quantField = new JTextField();
		quantField.setBounds(99, 126, 86, 20);
		add(quantField);
		quantField.setColumns(10);
		
		JLabel lblShipping = new JLabel("Shipping ");
		lblShipping.setBounds(30, 163, 52, 14);
		add(lblShipping);
		
		shippField = new JTextField();
		shippField.setBounds(99, 160, 86, 20);
		add(shippField);
		shippField.setColumns(10);
		
		JLabel lblShippingTimeIn = new JLabel("Shipping Time In Days");
		lblShippingTimeIn.setBounds(10, 200, 143, 14);
		add(lblShippingTimeIn);
		
		shippTimeField = new JTextField();
		shippTimeField.setBounds(163, 197, 86, 20);
		add(shippTimeField);
		shippTimeField.setColumns(10);
		
		
		cancelBtn.setBounds(118, 246, 86, 23);
		add(cancelBtn);
		
		
		
		createBtn.setBounds(234, 246, 89, 23);
		add(createBtn);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(390, 91, -101, 123);
		add(textArea);
		
		descField = new JTextField();
		descField.setBounds(299, 93, 109, 121);
		add(descField);
		descField.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(333, 60, 67, 14);
		add(lblDescription);

	}
}
