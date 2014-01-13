package internal;

import java.awt.BorderLayout;
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
	private List roomsList;
	
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
		roomsList = new List();

		selectRoomButton.setEnabled(false);
		selectRoomButton.addActionListener(this);
		backButton.addActionListener(this);
		roomsList.addActionListener(this);
		
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

		roomsList.add("Room 1             Single");
		roomsList.add("Room 2             Double");
		roomsList.add("Room 3             Double Bed");
		roomsList.add("Room 4             Suite");
		centerPanel.setLayout(new BorderLayout());
		centerPanel.add(roomsList,BorderLayout.CENTER);
		
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

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == roomsList) {
			selectRoomButton.setEnabled(true);
		}else if (e.getSource() == selectRoomButton){
			RoomFrame rf = new RoomFrame();
		}else if (e.getSource() == backButton){
			this.dispose();
		}
	}
}
