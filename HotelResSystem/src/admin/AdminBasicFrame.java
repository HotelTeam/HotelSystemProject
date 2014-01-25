package admin;

import main.MainFrame;


public class AdminBasicFrame extends javax.swing.JFrame {

    
    public AdminBasicFrame() {
        initComponents();
    }                   
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        insertRoomButton = new javax.swing.JButton();
        changeRoomButton = new javax.swing.JButton();
        deleteRoomButton = new javax.swing.JButton();
        InsertDiscountButton = new javax.swing.JButton();
        resCheckButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Admin Monitor");

        insertRoomButton.setText("Insert Room");
        insertRoomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertRoomButtonActionPerformed(evt);
            }
        });

        changeRoomButton.setText("Change Room");
        changeRoomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeRoomButtonActionPerformed(evt);
            }
        });

        deleteRoomButton.setText("Delete Room");
        deleteRoomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteRoomButtonActionPerformed(evt);
            }
        });

        InsertDiscountButton.setText("Insert Discount ");
        InsertDiscountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InsertDiscountButtonActionPerformed(evt);
            }
        });

        resCheckButton.setText("ReservationCheck");
        resCheckButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resCheckButtonActionPerformed(evt);
            }
        });

        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(148, Short.MAX_VALUE))
            .addComponent(insertRoomButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(changeRoomButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(deleteRoomButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(InsertDiscountButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(resCheckButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(insertRoomButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(changeRoomButton)
                .addGap(18, 18, 18)
                .addComponent(deleteRoomButton)
                .addGap(18, 18, 18)
                .addComponent(InsertDiscountButton)
                .addGap(18, 18, 18)
                .addComponent(resCheckButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(exitButton)
                .addContainerGap())
        );

        pack();
    }                    

    private void insertRoomButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                 
     new InsertRoomFrame().setVisible(true);
     this.dispose();
    }                                                

    private void changeRoomButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        new ChangeRoomFrame().setVisible(true);
        this.dispose();
    }                                                

    private void deleteRoomButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        new DeleteRoomFrame().setVisible(true);
        this.dispose();
    }                                                

    private void InsertDiscountButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                     
        new InsertDiscountFrame().setVisible(true);
        this.dispose();
    }                                                    

    private void resCheckButtonActionPerformed(java.awt.event.ActionEvent evt) {                                               
        new ReservationCheckFrame().setVisible(true);
        this.dispose();
    }                                              

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
       new MainFrame().setVisible(true);
       this.dispose();
    }                                                              
    private javax.swing.JButton InsertDiscountButton;
    private javax.swing.JButton changeRoomButton;
    private javax.swing.JButton deleteRoomButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JButton insertRoomButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton resCheckButton;                   
}
