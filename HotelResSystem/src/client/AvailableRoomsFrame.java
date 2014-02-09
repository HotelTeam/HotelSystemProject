package client;


import internal.Hotel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AvailableRoomsFrame extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel masterPanel, northPanel, centerPanel, southPanel;
	private JLabel titleText,arrivalText,departureText;
	private JTextField arrivalField, departureField;
	private JButton pickDateButton,selectRoomButton,backButton;
	private List simpleRoomsList;
	private List suiteRoomsList;
	private Hotel hotel;
	private String selection;
	
	public AvailableRoomsFrame(){
				
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		dateFormat.format(date);
		
		masterPanel = new JPanel(new BorderLayout());
		northPanel = new JPanel();
		centerPanel = new JPanel();
		southPanel = new JPanel();
		
		titleText = new JLabel("Available Rooms");
		arrivalText = new JLabel("Arrival");
		departureText = new JLabel("Departure");
		arrivalField = new JTextField(dateFormat.format(date));
		departureField = new JTextField(dateFormat.format(date));
		pickDateButton = new JButton("Show Rooms");
		selectRoomButton = new JButton("Select");
		backButton = new JButton("Back");
		simpleRoomsList = new List();
		suiteRoomsList = new List();

		selectRoomButton.setEnabled(false);
		selectRoomButton.addActionListener(this);
		backButton.addActionListener(this);
		simpleRoomsList.addActionListener(this);
		suiteRoomsList.addActionListener(this);
		
		northPanel.setLayout(new GridLayout(2,5));
		northPanel.add(new JLabel());
		northPanel.add(new JLabel());
		northPanel.add(titleText);
		northPanel.add(new JLabel());
		northPanel.add(new JLabel());
		northPanel.add(arrivalText);
		northPanel.add(arrivalField);
		northPanel.add(departureText);
		northPanel.add(departureField);
		northPanel.add(pickDateButton);

		hotel = new Hotel();
		
		addRoomsToList();
		
		centerPanel.setLayout(new FlowLayout());
		centerPanel.add(simpleRoomsList);
		centerPanel.add(suiteRoomsList);

		southPanel.setLayout(new GridLayout(1,5));
		southPanel.add(new JLabel());
		southPanel.add(new JLabel());
		southPanel.add(new JLabel());
		southPanel.add(backButton);
		southPanel.add(selectRoomButton);
		
		masterPanel.add(northPanel, BorderLayout.NORTH);
		masterPanel.add(centerPanel, BorderLayout.CENTER);
		masterPanel.add(southPanel, BorderLayout.SOUTH);

		this.setContentPane(masterPanel);
		this.pack();
		this.setLocation(100,100);
		this.setTitle("Available Rooms");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	private void addRoomsToList(){

		
		for(int i = 0; i < hotel.getSuiteRooms().size(); i++) {   
			suiteRoomsList.add("Suite: " + hotel.getSuiteRooms().get(i).getId_room() + "  |  number of beds: " + hotel.getSuiteRooms().get(i).getNumberBeds() + "  |  price: " + hotel.getSuiteRooms().get(i).getPrice() + "  |  offer: " + hotel.getSuiteRooms().get(i).getOffer());
		
		}  
		
		for(int i = 0; i < hotel.getSimpleRooms().size(); i++) {   
			simpleRoomsList.add("Simple: " + hotel.getSimpleRooms().get(i).getId_room() + "  |  number of beds: " + hotel.getSimpleRooms().get(i).getNumberBeds() + "  |  price: " + hotel.getSimpleRooms().get(i).getPrice() + "  |  offer: " + hotel.getSimpleRooms().get(i).getOffer());
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == simpleRoomsList) {
			selectRoomButton.setEnabled(true);
			selection = "simple";
			suiteRoomsList.deselect(suiteRoomsList.getSelectedIndex());
		}else if (e.getSource() == suiteRoomsList){
			selectRoomButton.setEnabled(true);
			selection = "suite";
			simpleRoomsList.deselect(simpleRoomsList.getSelectedIndex());
		}else if (e.getSource() == selectRoomButton){
			if(selection == "suite"){
				RoomFrame rf = new RoomFrame(hotel.getSuiteRooms().get(suiteRoomsList.getSelectedIndex()));
			}else if(selection == "simple"){
				RoomFrame rf = new RoomFrame(hotel.getSimpleRooms().get(simpleRoomsList.getSelectedIndex()));
			}
		}else if (e.getSource() == backButton){
			this.dispose();
		}
	}
}
