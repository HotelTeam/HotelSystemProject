package manager;

import main.MainFrame;

public class ChooseAnalysisFrame extends javax.swing.JFrame {

    public ChooseAnalysisFrame() {
        initComponents();
    }                       
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
      
        returnButton = new javax.swing.JButton();
        annualRadioButton = new javax.swing.JRadioButton();
        monthlyRadioButton = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        goButton = new javax.swing.JButton();
        buttonGroup1.add(annualRadioButton);
        buttonGroup1.add(monthlyRadioButton);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        annualRadioButton.setSelected(true);
        returnButton.setText("Return");
        returnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnButtonActionPerformed(evt);
            }
        });

        annualRadioButton.setText("Annual");

        monthlyRadioButton.setText("Monthly");

        jLabel1.setText("Manager Screen");

        goButton.setText("Go");
        goButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(returnButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(annualRadioButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addComponent(goButton, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(139, 139, 139))
            .addGroup(layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(monthlyRadioButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(annualRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(monthlyRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(returnButton)
                    .addComponent(goButton))
                .addGap(20, 20, 20))
        );

        pack();
    }                       

    private void goButtonActionPerformed(java.awt.event.ActionEvent evt) {                                         
        if(annualRadioButton.isSelected()){
        	new AnnualAnalysisFrame().setVisible(true);
        	this.dispose();
        }else{
        	new MonthlyAnalysisFrame().setVisible(true);
        	this.dispose();
        }
    }                                        

    private void returnButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
       new MainFrame().setVisible(true);
       this.dispose();
    }                                            


    // Variables declaration - do not modify                     
    private javax.swing.JRadioButton annualRadioButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton goButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JRadioButton monthlyRadioButton;
    private javax.swing.JButton returnButton;
    // End of variables declaration                   
}

