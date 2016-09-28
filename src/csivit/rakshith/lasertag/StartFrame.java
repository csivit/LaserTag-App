package csivit.rakshith.lasertag;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class StartFrame extends JFrame {

    DefaultTableModel tableModel;
    
    public StartFrame() {
        initComponents();
        tableModel = (DefaultTableModel) jTable1.getModel();
        jTextField1.setText("");
        jTextField1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent de) {
                search();
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                search();
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                search();
            }
        });
        for(int i = 0; i < Data.UnassignedPlayers.size(); i++) {
            tableModel.addRow(new Object[]{
                i + 1,
                Data.UnassignedPlayers.get(i).getName(),
                Data.UnassignedPlayers.get(i).getRegistrationNumber(),
                Data.UnassignedPlayers.get(i).getEmail(),
                Data.UnassignedPlayers.get(i).getPhoneNumber(),
                Data.UnassignedPlayers.get(i).getGender()
            });
        }
        jLabel2.setText("Team count: " + Data.AllTeams.size());
    }

    protected void openForm(JFrame jFrame) {
        JDialog frame = new JDialog(this, jFrame.getTitle(), true);
        frame.getContentPane().add(jFrame.getContentPane());
        jFrame.getContentPane().addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent ce) {
                
            }

            @Override
            public void componentMoved(ComponentEvent ce) {
                
            }

            @Override
            public void componentShown(ComponentEvent ce) {
                
            }

            @Override
            public void componentHidden(ComponentEvent ce) {
                frame.setVisible(false);
                System.out.println("Shit");
            }
        });
        frame.pack();
        frame.setVisible(true);
    }
    
    private void search() {
        System.out.println(jTextField1.getText().length());
        if("".equals(jTextField1.getText()) || jTextField1.getText() == null) {
            while(tableModel.getRowCount() > 0) {
                tableModel.removeRow(0);
            }
            for(int i = 0; i < Data.UnassignedPlayers.size(); i++) {
                tableModel.addRow(new Object[]{
                    i + 1,
                    Data.UnassignedPlayers.get(i).getName(),
                    Data.UnassignedPlayers.get(i).getRegistrationNumber(),
                    Data.UnassignedPlayers.get(i).getEmail(),
                    Data.UnassignedPlayers.get(i).getPhoneNumber(),
                    Data.UnassignedPlayers.get(i).getGender()
                });
            }
        } else {
            int i = 1;
            while(tableModel.getRowCount() > 0) {
                tableModel.removeRow(0);
            }
            for(Player player : Data.UnassignedPlayers) {
                if(player.getName().toLowerCase().contains(jTextField1.getText().toLowerCase()) || player.getRegistrationNumber().toLowerCase().contains(jTextField1.getText().toLowerCase()) || player.getPhoneNumber().toLowerCase().contains(jTextField1.getText().toLowerCase())) {
                    tableModel.addRow(new Object[] {
                        i,
                        player.getName(),
                        player.getRegistrationNumber(),
                        player.getEmail(),
                        player.getPhoneNumber(),
                        player.getGender()
                    });
                    i++;
                }
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("<html><b><h1>Laser Tag app</h1></b></html>");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Name", "Reg. No", "Email", "Phone No", "Gender"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setColumnSelectionAllowed(true);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jButton1.setText("Form Team");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jTextField1InputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jButton2.setText("Form Schedule");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Team count: 0");

        jToggleButton1.setText("View Teams");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jToggleButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jToggleButton1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Form Schedule
        openForm(new ScheduleFrame());
        Data.save();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        // search table
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Form team
        CreateTeamFrame createTeamFrame = new CreateTeamFrame(Data.UnassignedPlayers.get(jTable1.getSelectedRow()));
        openForm(createTeamFrame);
        while(tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }
        for(int i = 0; i < Data.UnassignedPlayers.size(); i++) {
            tableModel.addRow(new Object[]{
                i + 1,
                Data.UnassignedPlayers.get(i).getName(),
                Data.UnassignedPlayers.get(i).getRegistrationNumber(),
                Data.UnassignedPlayers.get(i).getEmail(),
                Data.UnassignedPlayers.get(i).getPhoneNumber(),
                Data.UnassignedPlayers.get(i).getGender()
            });
        }
        jLabel2.setText("Team count: " + Data.AllTeams.size());
        Data.save();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // View teams
        openForm(new ViewTeamsFrame(Data.AllTeams));
        Data.save();
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jTextField1InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jTextField1InputMethodTextChanged
        // Text changed
        System.out.println("Text changed");
    }//GEN-LAST:event_jTextField1InputMethodTextChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
}
