package admin;

import java.util.Vector;

import internal.Hotel;
import internal.Room;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;


public class ChangeRoomFrame extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;
	private Hotel hotel;
	private Vector<Room> rooms;   
    public ChangeRoomFrame() {
    	hotel=new Hotel();
        initComponents();
    }
                         
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<String>();
        returnButton = new javax.swing.JButton();
        changeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Update Room Monitor");

    	rooms = hotel.getRooms();
		DefaultListModel<String> lm = new DefaultListModel<String>();
		int i = 0;
		for (Room room : rooms) {
			lm.add(i, String.valueOf(room.getNumber()));
		}
		jList1.setModel(lm);
        jScrollPane1.setViewportView(jList1);

        returnButton.setText("Return");
        returnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnButtonActionPerformed(evt);
            }
        });

        changeButton.setText("Change");
        changeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(117, 117, 117))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(returnButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(changeButton)
                .addGap(81, 81, 81))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(changeButton)
                .addGap(22, 22, 22))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(returnButton)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }                 

    private void changeButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
      	if(!(jList1.getSelectedValue()==null)){
      		rooms=hotel.getRooms();
      		Room changeRoom=null;
      		for (Room room : rooms) {
				if((Integer.parseInt(jList1.getSelectedValue())==room.getNumber())){
					changeRoom=room;					
				}
			}
      		new ChangeSpecificRoomFrame(changeRoom).setVisible(true);
      		this.dispose();
      		
      	}else{
      		JOptionPane.showMessageDialog(null, "You must select a room first!!!");
      	}
    }                                            

    private void returnButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        new AdminBasicFrame().setVisible(true);
        this.dispose();
    }                                            


                     
    private javax.swing.JButton changeButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton returnButton;
                   
}
