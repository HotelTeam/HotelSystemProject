package client;
import internal.Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

public class ValidationFormFrame extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel southPanel, centerPanel, masterPanel;
	private JLabel firstNameLabel,lastNameLabel,userNameLabel,passwordLabel,repeatPasswordLabel, emailLabel,creditCardLabel,ageLabel;
	private JTextField firstNameField, lastNameField, usernameField, emailField;
	private JPasswordField passwordField, repeatPasswordField;
	private JButton submitButton,cancelButton;
	private JFormattedTextField creditCardField,ageField;
	private Border blackline;
	private TitledBorder title1;
	
	public ValidationFormFrame(){

	masterPanel = new JPanel(new BorderLayout());
	centerPanel = new JPanel();
	southPanel = new JPanel();
	
	firstNameLabel = new JLabel("first name");
	lastNameLabel = new JLabel("last name");
	ageLabel = new JLabel("age");
	userNameLabel = new JLabel("username");
	passwordLabel = new JLabel("password");
	repeatPasswordLabel = new JLabel("repeat password");
	emailLabel = new JLabel("e-mail");
	creditCardLabel = new JLabel("credit card num.");
	firstNameField = new JTextField();
	lastNameField = new JTextField();
	ageField = new JFormattedTextField(createFormatter("##"));
	emailField = new JTextField();
	usernameField = new JTextField();
	passwordField = new JPasswordField();
	repeatPasswordField = new JPasswordField();
	creditCardField = new JFormattedTextField(createFormatter("####-####-####-####"));
	submitButton = new JButton("Submit");
	cancelButton = new JButton("Cancel");
	
	blackline = BorderFactory.createLineBorder(Color.black);
	title1 = BorderFactory.createTitledBorder(blackline,"Validation Form");
	
	submitButton.addActionListener(this);
	cancelButton.addActionListener(this);
	
	centerPanel.setLayout(new GridLayout(8,2));
	centerPanel.add(firstNameLabel);
	centerPanel.add(firstNameField);
	centerPanel.add(lastNameLabel);
	centerPanel.add(lastNameField);
	centerPanel.add(ageLabel);
	centerPanel.add(ageField);
	centerPanel.add(emailLabel);
	centerPanel.add(emailField);
	centerPanel.add(userNameLabel);
	centerPanel.add(usernameField);
	centerPanel.add(passwordLabel);
	centerPanel.add(passwordField);
	centerPanel.add(repeatPasswordLabel);
	centerPanel.add(repeatPasswordField);
	centerPanel.add(creditCardLabel);
	centerPanel.add(creditCardField);
	centerPanel.setBorder(title1);
	
	southPanel.setLayout(new GridLayout(1,5));
	southPanel.add(new JPanel());
	southPanel.add(new JPanel());
	southPanel.add(new JPanel());
	southPanel.add(cancelButton);
	southPanel.add(submitButton);

	masterPanel.add(centerPanel, BorderLayout.CENTER);
	masterPanel.add(southPanel, BorderLayout.SOUTH);

	this.setContentPane(masterPanel);
	this.setSize(400, 300);
	this.setLocation(100,100);
	this.setTitle("Client Registration Form");
	this.setVisible(true);
	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CharSequence inputStr = emailField.getText();
		Pattern pattern = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z]{2,}){1}$)",Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(inputStr);

		if(e.getSource() == submitButton) {
			if((firstNameField.getText().isEmpty()) || (lastNameField.getText().isEmpty()) || (emailField.getText().isEmpty()) || (ageField.getText().isEmpty()) || (usernameField.getText().isEmpty()) || (passwordField.getPassword().length == 0) || (repeatPasswordField.getPassword().length == 0) || (creditCardField.getText().isEmpty())){
				JOptionPane.showMessageDialog(null, "Please fill in all fields", "Wait",JOptionPane.INFORMATION_MESSAGE);
			}else if(!Arrays.equals(passwordField.getPassword(),repeatPasswordField.getPassword())){
				JOptionPane.showMessageDialog(null, "Please retype your password correctly", "Wait",JOptionPane.INFORMATION_MESSAGE);
			}else if(!matcher.matches()){
				JOptionPane.showMessageDialog(null, "Please type a valid e-mail address", "Wait",JOptionPane.INFORMATION_MESSAGE);
			}else{
				Client client = new Client();
				client.setFirstname(firstNameField.getText());
				client.setLastname(lastNameField.getText());
				client.setAge(Integer.parseInt(ageField.getText()));
				client.setEmail(emailField.getText());
				client.setUsername(usernameField.getText());
				client.setPassword(passwordField.getPassword());
				client.setCreditCard(creditCardField.getText());
				
				client.addClientToDB();
				
				if(client.getClientAddedDB()){
					JOptionPane.showMessageDialog(null, "Mr/Ms " + client.getFirstname() + " " + client.getLastname() + " your room has successfully been reserved.", "Success",JOptionPane.INFORMATION_MESSAGE);
					this.dispose();
				}else{
					JOptionPane.showMessageDialog(null, "Mr/Ms " + client.getFirstname() + " " + client.getLastname() + " there has been an error while trying to connect to DataBase", "Error",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}else if (e.getSource() == cancelButton){
			this.dispose();
		}
	}
	
	protected MaskFormatter createFormatter(String s) {
	    MaskFormatter formatter = null;
	    try {
	        formatter = new MaskFormatter(s);
	    } catch (java.text.ParseException exc) {
	        System.err.println("formatter is bad: " + exc.getMessage());
	        System.exit(-1);
	    }
	    return formatter;
	}
	
	
}