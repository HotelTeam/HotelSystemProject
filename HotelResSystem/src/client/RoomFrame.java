package client;

import internal.Room;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class RoomFrame extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel masterPanel, northPanel, eastPanel, southPanel,centerPanel,westPanel;
	private JLabel titleText,facilitiesText,priceText;
	private List facilitiesList;
	private JLabel roomImageLabel;
	private ImageIcon image;
	private JTextArea priceArea;
	private JButton reservationButton,backButton;
	
	public RoomFrame(Room room){

		masterPanel = new JPanel(new BorderLayout());
		northPanel = new JPanel();
		eastPanel = new JPanel();
		centerPanel = new JPanel();
		southPanel = new JPanel();
		westPanel = new JPanel();
				
		titleText = new JLabel(""+room.getId_room());
		facilitiesList = new List();
		image = new ImageIcon("images/single.jpg");
		roomImageLabel = new JLabel(image);
		priceArea = new JTextArea("40");
		facilitiesText = new JLabel("Facilities");
		reservationButton = new JButton("Reservation");
		backButton = new JButton("Back");
		priceText = new JLabel("Price");
		
		reservationButton.addActionListener(this);
		backButton.addActionListener(this);
		
		priceArea.setEditable(false);
		
		northPanel.add(titleText);

		centerPanel.add(roomImageLabel);
		
		facilitiesList.add("WiFi");
		facilitiesList.add("TV");
		facilitiesList.add("Air Condition");
		eastPanel.setLayout(new BorderLayout());
		eastPanel.add(facilitiesText, BorderLayout.NORTH);
		eastPanel.add(facilitiesList, BorderLayout.CENTER);

		southPanel.setLayout(new GridLayout(1,6));
		southPanel.add(new JLabel());
		southPanel.add(new JLabel());
		southPanel.add(new JLabel());
		southPanel.add(new JLabel());
		southPanel.add(backButton);
		southPanel.add(reservationButton);
		
		westPanel.add(priceText);
		westPanel.add(priceArea);
		
		masterPanel.add(northPanel, BorderLayout.NORTH);
		masterPanel.add(centerPanel, BorderLayout.CENTER);
		masterPanel.add(eastPanel, BorderLayout.EAST);
		masterPanel.add(southPanel, BorderLayout.SOUTH);
		masterPanel.add(westPanel, BorderLayout.WEST);

		
		this.setContentPane(masterPanel);
		this.pack();
		this.setLocation(100,100);
		this.setTitle("Room Details");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == reservationButton) {
			ValidationFormFrame vf = new ValidationFormFrame();
		}else if (e.getSource() == backButton){
			this.dispose();
		}		
	}

}
