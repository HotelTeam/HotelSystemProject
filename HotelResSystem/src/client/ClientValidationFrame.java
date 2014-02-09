package client;

import internal.Client;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class ClientValidationFrame extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JRadioButton newUserButton, logInButton;
	private ButtonGroup group;
	private JPanel southPanel, westPanel, centerPanel, masterPanel;
	private JTextField usernameField;
	private JLabel usernameLabel,passwordLabel;
	private JPasswordField passwordField;
	private JButton submitButton,cancelButton;
	
	public ClientValidationFrame(){
		
		usernameLabel = new JLabel("username");
		passwordLabel = new JLabel("password");
		usernameField = new JTextField();
		usernameField.setEditable(false);
		passwordField = new JPasswordField();
		passwordField.setEditable(false);
		
		newUserButton = new JRadioButton("New user");
		newUserButton.setSelected(true);
		newUserButton.addActionListener(this);
		logInButton = new JRadioButton("Log in");
		logInButton.addActionListener(this);
		
		group = new ButtonGroup();
		group.add(newUserButton);
		group.add(logInButton);

		masterPanel = new JPanel(new BorderLayout());
		westPanel = new JPanel();
		centerPanel = new JPanel();
		southPanel = new JPanel();
		
		submitButton = new JButton("Continue");
		submitButton.addActionListener(this);
		cancelButton = new JButton("Back");
		cancelButton.addActionListener(this);
		
		westPanel.setLayout(new GridLayout(2,1));
		westPanel.add(newUserButton);
		westPanel.add(logInButton);
		
		centerPanel.setLayout(new GridLayout(2,2));
		centerPanel.add(usernameLabel);
		centerPanel.add(usernameField);
		centerPanel.add(passwordLabel);
		centerPanel.add(passwordField);
		
		southPanel.setLayout(new GridLayout(1,5));
		southPanel.add(new JPanel());
		southPanel.add(new JPanel());
		southPanel.add(new JPanel());
		southPanel.add(cancelButton);
		southPanel.add(submitButton);
		
		masterPanel.add(westPanel, BorderLayout.NORTH);
		masterPanel.add(centerPanel, BorderLayout.CENTER);
		masterPanel.add(southPanel, BorderLayout.SOUTH);
		
		this.setContentPane(masterPanel);
		this.pack();
		this.setLocation(100,100);
		this.setTitle("Welcome");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == newUserButton){
			usernameField.setEditable(false);
			passwordField.setEditable(false);
		}else if(e.getSource() == logInButton){
			usernameField.setEditable(true);
			passwordField.setEditable(true);
		}else if(e.getSource() == submitButton){
			if(newUserButton.isSelected()){
				new AvailableRoomsFrame();
			}else{
				if((usernameField.getText().isEmpty()) || (passwordField.getPassword().length == 0)){
					JOptionPane.showMessageDialog(null, "Please fill in all fields", "Wait",JOptionPane.INFORMATION_MESSAGE);
				}else{
					Client client = new Client();
					client.getClientFromDB(usernameField.getText(), String.copyValueOf(passwordField.getPassword()));
					if(client.getId_client() == 0){
						JOptionPane.showMessageDialog(null, "Wrong username or password! Try again", "Wait",JOptionPane.INFORMATION_MESSAGE);
					}else{
						new PersonalReservationFrame(client);
					}
				}
			}
		}else if(e.getSource() == cancelButton){
			this.dispose();
		}
	}

}
