


import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;
import org.apache.lucene.queryParser.ParseException;
import org.hibernate.LazyInitializationException;

/*
 * PeerInterface.java
 *
 * Created on 01 16, 10, 7:28:01 PM
 */
/**
 *
 * @author kashif
 */
public class PeerInterface extends javax.swing.JFrame {

    DefaultTableModel dtm = null;
    DefaultTableModel cdtm = null;
    HibernateActions ha = null;
    SearchLanguage sl = null;
    List<Annotation> universalList = null;
    Long time1 = null;
    Long time2 = null;
    Long minTime = null;
    Long maxTime = null;
    int counter = 0;
    boolean remoteSearch = true;

    /** Creates new form PeerInterface */
    public PeerInterface() throws NoSuchAlgorithmException, Exception {
        initComponents();

        ha = new HibernateActions();
        dtm = new DefaultTableModel();
        sl = new SearchLanguage();
        dtm.addColumn("Text");
        dtm.addColumn("Concept");
        jTable1.setModel(dtm);

        SpinnerDateModel model1 = new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY);
        jSpinner1.setModel(model1);
        JSpinner.DateEditor editor1 = new JSpinner.DateEditor(jSpinner1, "MMMMM dd, yyyy");//new JSpinner.DateEditor(jSpinner1, "MMMMM dd, yyyy HH:mm:ss");
        jSpinner1.setEditor(editor1);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        description = new javax.swing.JTextField();
        resourceURL = new javax.swing.JTextField();
        annAuthor = new javax.swing.JTextField();
        save = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        text = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        concept = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        search = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jSpinner1 = new javax.swing.JSpinner();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        peerInformationTextArea = new javax.swing.JTextArea();
        clearLog = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        resourceURL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resourceURLActionPerformed(evt);
            }
        });

        annAuthor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annAuthorActionPerformed(evt);
            }
        });

        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Text", "Concept"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jInternalFrame1.setVisible(true);

        jButton2.setText("Add Values");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Text");

        jLabel3.setText("Concept");

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGap(28, 28, 28)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(text, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                    .addComponent(concept, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                .addGap(41, 41, 41))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(concept, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(29, 29, 29))
        );

        jLabel1.setText("Annotation Author");

        jLabel5.setText("Resource URL");

        jLabel7.setText("Description");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(110, 110, 110)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(description, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .addComponent(resourceURL, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .addComponent(annAuthor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
                .addGap(105, 105, 105)
                .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(189, 189, 189))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 885, Short.MAX_VALUE)
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(375, Short.MAX_VALUE)
                .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(341, 341, 341))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(annAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(resourceURL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(description, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(214, 214, 214)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(271, 271, 271))
        );

        jTabbedPane1.addTab("Enter Annotation", jPanel2);

        jLabel4.setText("Search Query");

        search.setText("Search");
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        jScrollPane3.setViewportView(jTable2);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTable3);

        jButton1.setText("Get Time");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jButton3.setText("Delete Annotation");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 877, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 877, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(174, 174, 174)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(159, Short.MAX_VALUE)
                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(285, 285, 285))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(search)
                            .addComponent(jButton3))))
                .addGap(67, 67, 67)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(220, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Search Annotation", jPanel1);

        peerInformationTextArea.setColumns(20);
        peerInformationTextArea.setRows(5);
        jScrollPane5.setViewportView(peerInformationTextArea);

        clearLog.setText("Clear Log");
        clearLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearLogActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 871, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(421, Short.MAX_VALUE)
                .addComponent(clearLog)
                .addGap(407, 407, 407))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(clearLog)
                .addContainerGap(152, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Peer Information", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 910, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(93, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
public void setMessage(String msg){
        peerInformationTextArea.append(msg);
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String tempQuery = jTextArea1.getText().trim();
        int a = jTable2.getSelectedRow();
        if (a == -1) {
            JOptionPane.showMessageDialog(rootPane, "Please Select a record first");
            return;
        }
        int b = jTable2.getSelectedRowCount();
        int option = JOptionPane.showConfirmDialog(rootPane, "Sure you want to delete " + b + " record/s");
        if (option == 0) {
            String idToDelete = null;
            try {

                for (int i = 0; i < b; i++) {
                    idToDelete = (String) jTable2.getValueAt(i, 0);
                    //JOptionPane.showMessageDialog(rootPane, "going to delete "+ idToDelete);
                    ha.deleteAnnotation(idToDelete); ///////////////////////////////////////////////////////////////////
                }

            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(PeerInterface.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(PeerInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Entry/ies deleted");
            searchActionPerformed(evt);

        } else {
            return;
        }
}//GEN-LAST:event_jButton3ActionPerformed
public void saveRDFtoDB(LinkedList list) throws Exception{
     //ha.populateviaRDF(list);

}
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        Date newDate = (Date) jSpinner1.getValue();
        GregorianCalendar gc1 = new GregorianCalendar();
        gc1.setTime(newDate);
        SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy");
        String strDate = sdf.format(gc1.getTime());
        jTextArea1.append(strDate + " ");
}//GEN-LAST:event_jButton1ActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
        remoteSearch = true;
        String searchQuery = null;
        String[] checkSyntax = {"author","time","description","url","phrase","term","prefix", "deprecated"};
        boolean status = false;
        boolean status1 = false;
        List<Annotation> results = null;
        if (jTextArea1.getText().equals(null) || jTextArea1.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Enter Search Query please");
            return;
        }
        if(jTextArea1.getText().contains(",")){
            String[] fQuery = jTextArea1.getText().split(",");
            for(int i = 0; i < fQuery.length;i++){
                fQuery[i] = fQuery[i].trim();}

            for(int i = 0; i < fQuery.length; i++){
                status = false;
                for(int j = 0; j < checkSyntax.length; j++){
                    if(fQuery[i].startsWith(checkSyntax[j])){
                        System.out.println("query "+fQuery[i]);
                        System.out.println("field "+checkSyntax[j]);
                        status = true;
                        break;
                        //continue;
                    }
                }
                if(!status){
                    JOptionPane.showMessageDialog(rootPane, "Not a valid multiple query");
                    return;
                }


            }//end of outer for loop
            
        } else{
            for(int i = 0; i < checkSyntax.length; i++){
                if(jTextArea1.getText().startsWith(checkSyntax[i])){//donothing
                    status = true;
                    break;
                } else{   status = false;}
            }
        }
        if(!status){
            JOptionPane.showMessageDialog(rootPane, "Not a valid query");
            return;
        }
        if (jTextArea1.getText().startsWith("time")) {
            if (jTextArea1.getText().contains("to")) {
                String[] arr = jTextArea1.getText().split("=");
                String[] newarr = arr[1].split("to");
                Date[] dateArr = new Date[2];
                dateArr[0] = new Date(newarr[0].trim());
                dateArr[1] = new Date(newarr[1].trim());
                dateHandler(dateArr);
                searchQuery = "time=" + time1.toString() + "between" + time2.toString();
            } else {
                String[] arr = jTextArea1.getText().split("=");
                String[] newarr = arr[1].split("to");
                Date[] dateArr = new Date[1];
                dateArr[0] = new Date(newarr[0].trim());
                dateHandler(dateArr);
                searchQuery = "time =" + minTime + "between" + maxTime;
            }
            try {
                results = sl.searchExecute(searchQuery);
            } catch (java.text.ParseException ex) {
                Logger.getLogger(PeerInterface.class.getName()).log(Level.SEVERE, null, ex);

            } catch (IOException ex) {
                Logger.getLogger(PeerInterface.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(PeerInterface.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(PeerInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            searchQuery = new String(jTextArea1.getText().trim());
            try {
                results = sl.searchExecute(searchQuery);
            } catch (java.text.ParseException ex) {
                Logger.getLogger(PeerInterface.class.getName()).log(Level.SEVERE, null, ex);

            } catch (IOException ex) {
                Logger.getLogger(PeerInterface.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(PeerInterface.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(PeerInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        print(results);
        counter = 0;
        TestPeer peer = new TestPeer(5);
        peer.doSomething(searchQuery);

}//GEN-LAST:event_searchActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Object[] oarr = {text.getText().trim(), concept.getText().trim()};
        dtm.addRow(oarr);
        text.setText("");
        concept.setText("");
}//GEN-LAST:event_jButton2ActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        // TODO add your handling code here:
        if (resourceURL.getText().equals(null) || resourceURL.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Enter Resource URL ");
            return;
        }
        if (annAuthor.getText().equals(null) || annAuthor.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Enter Email of Annotation Author ");
            return;
        }
        if (!annAuthor.getText().contains("@")) {
            JOptionPane.showMessageDialog(rootPane, "Author email is invalid ");
            return;
        }
        if (description.getText().equals(null) || description.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Enter the Description for Annotation ");
            return;
        }
        LinkedList dataList = new LinkedList();
        dataList.add(new String(resourceURL.getText().trim()));
        dataList.add(new String(description.getText().trim()));
        dataList.add(new String(annAuthor.getText().trim()));
        LinkedList tableList = new LinkedList();
        if(jTable1.getRowCount()<1){
            JOptionPane.showMessageDialog(rootPane, "Enter the Information about Semantics");
            return;
        }
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            tableList.add(jTable1.getValueAt(i, 0));
            tableList.add(jTable1.getValueAt(i, 1));
        }
        for (int i = 0; i < tableList.size(); i++) {
            System.out.println(tableList.get(i));
        }
        try {
            ha.populateAnnotationTable(dataList, tableList);

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PeerInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PeerInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(rootPane, "Data Saved succesfully");
        resourceURL.setText("");
        annAuthor.setText("");
        description.setText("");

        for (int i = jTable1.getRowCount() - 1; i > -1; i--) {
            dtm.removeRow(i);
        }
}//GEN-LAST:event_saveActionPerformed

    private void annAuthorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annAuthorActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_annAuthorActionPerformed

    private void resourceURLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resourceURLActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_resourceURLActionPerformed

    private void clearLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearLogActionPerformed
        // TODO add your handling code here:
        File file = new File("rdvlog.txt");
        try {
            FileOutputStream fos = new FileOutputStream(file, true);

            byte[] data = peerInformationTextArea.getText().getBytes();
            String sep = "///////////////////////////////////////////////////////////////////"+"\n";
            byte[] separator = sep.getBytes();
            try {
                fos.write(new Date().toString().getBytes());
                fos.write(data);
                fos.write(separator);
                peerInformationTextArea.setText("");
            } catch (IOException ex) {
                Logger.getLogger(PeerInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PeerInterface.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_clearLogActionPerformed

    public void print(List<Annotation> results) {

        universalList = results;
        int k = results.size();
        String[] pColumnNames = {"Annotation ID", "Resource URL", "Description", "Creation Time", "Annotation Author", "Deprecated","File Location"};
        String[][] pRowData = new String[k][8];
        cdtm = new DefaultTableModel();
        cdtm.addColumn("Annotation Ref");
        cdtm.addColumn("Text");
        cdtm.addColumn("Concept");
        cdtm.addColumn("Annotation TermID");
        jTable3.setModel(cdtm);
        try {
            int i = 0;
            for (Annotation ann : results) {
                Set<AnnotationChild> myset = ann.getQueryData();
                pRowData[i][0] = ann.getAnnID();
                pRowData[i][1] = ann.getResourceURL();
                pRowData[i][2] = ann.getDescription();
                Date myTime = new Date();
                myTime.setTime(ann.getCreationTime());
                pRowData[i][3] = myTime.toString();
                pRowData[i][4] = ann.getAnnotationAuthor();
                boolean isDeprecated = ann.isDeprecated();
                if(isDeprecated){
                    pRowData[i][5] = "Deprecated";
                }
                else{
                    pRowData[i][5] = "Not Deprecated";
                }
                pRowData[i][6] = ann.getFileLocation();
                pRowData[i][7] = ann.getDigitalSignatures().toString();
                for (AnnotationChild annChild : myset) {
                    Object[] oarr = {ann.getAnnID(), annChild.getText(), annChild.getConcept(), annChild.getChildId()};
                    cdtm.addRow(oarr);
                }
                i++;
            }
        } catch (LazyInitializationException e) {
            JOptionPane.showMessageDialog(rootPane, "Lazy initialization exception");
        }
        DefaultTableModel dtm1 = new DefaultTableModel(pRowData, pColumnNames);
        jTable2.setModel(dtm1);

    }
public void addRowsInTables(Annotation ann){
    DefaultTableModel newDTM = (DefaultTableModel) jTable2.getModel();
    String[] pRowData = new String[7];
    Set<AnnotationChild> myset = ann.getQueryData();

    pRowData[0] = ann.getAnnID();
    pRowData[1] = ann.getResourceURL();
    pRowData[2] = ann.getDescription();
    Long creationTime = ann.getCreationTime();
    Date myTime = new Date();
    myTime.setTime(creationTime);
    pRowData[3] = myTime.toString();
    pRowData[4] = ann.getAnnotationAuthor();
    boolean isDeprecated = ann.isDeprecated();
    if(isDeprecated){
           pRowData[5] = "Deprecated";
     }
     else{
              pRowData[5] = "Not Deprecated";
      }

    pRowData[6] = ann.getFileLocation();
    for (AnnotationChild annChild : myset) {
                    Object[] oarr = {ann.getAnnID(), annChild.getText(), annChild.getConcept(), annChild.getChildId()};
                    cdtm.addRow(oarr);
                }

    newDTM.addRow(pRowData);
    
}
   
public void dateHandler(Date[] newDates){
            String strDate = null;

    ////////////////////////////////Truncating time from Date element/////////////////////
            if(newDates.length == 1){
            GregorianCalendar gc1 = new GregorianCalendar();
            gc1.clear();
            gc1.setTime(newDates[0]);
            int year = gc1.get(Calendar.YEAR), month = gc1.get(Calendar.MONTH), day = gc1.get(Calendar.DAY_OF_MONTH);
            GregorianCalendar gc2 = new GregorianCalendar(year, month, day);
            Date trunDate = gc2.getTime();
            minTime = trunDate.getTime();
///////////////////// adding 23 hours 59 mins and 59 secs///////////////////////////
            Calendar calendar = gc2;
            calendar.add(Calendar.HOUR, 23);
            calendar.add(Calendar.MINUTE, 59);
            calendar.add(Calendar.SECOND, 59);
            SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy HH:mm:ss");
            strDate = sdf.format(calendar.getTime());
            Date maxDate = new Date(strDate);
            maxTime = maxDate.getTime();
            }

            if(newDates.length == 2){
            GregorianCalendar gc1 = new GregorianCalendar();
            gc1.clear();
            gc1.setTime(newDates[0]);
            int year = gc1.get(Calendar.YEAR), month = gc1.get(Calendar.MONTH), day = gc1.get(Calendar.DAY_OF_MONTH);
            GregorianCalendar gc2 = new GregorianCalendar(year, month, day);
            Date trunDate = gc2.getTime();
            time1 = trunDate.getTime();//date with start time
///////////////////// adding 23 hours 59 mins and 59 secs///////////////////////////
            gc1.clear();
            gc1.setTime(newDates[1]);
            year = gc1.get(Calendar.YEAR);
            month = gc1.get(Calendar.MONTH);
            day = gc1.get(Calendar.DAY_OF_MONTH);
            gc2 = new GregorianCalendar(year, month, day);
            Calendar calendar = gc2;
            calendar.add(Calendar.HOUR, 23);
            calendar.add(Calendar.MINUTE, 59);
            calendar.add(Calendar.SECOND, 59);
            SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy HH:mm:ss");
            strDate = sdf.format(calendar.getTime());
            Date maxDate = new Date(strDate);
            time2 = maxDate.getTime();
            }
}

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//
//            public void run() {
//                try {
//                    new PeerInterface().setVisible(true);
//                } catch (NoSuchAlgorithmException ex) {
//                    Logger.getLogger(PeerInterface.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (Exception ex) {
//                    Logger.getLogger(PeerInterface.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField annAuthor;
    private javax.swing.JButton clearLog;
    private javax.swing.JTextField concept;
    private javax.swing.JTextField description;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea peerInformationTextArea;
    private javax.swing.JTextField resourceURL;
    private javax.swing.JButton save;
    private javax.swing.JButton search;
    private javax.swing.JTextField text;
    // End of variables declaration//GEN-END:variables
}
