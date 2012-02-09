/*    
 *    MediathekView
 *    Copyright (C) 2008   W. Xaver
 *    W.Xaver[at]googlemail.com
 *    http://zdfmediathk.sourceforge.net/
 *    
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package mediathek.gui.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mediathek.gui.beobachter.EscBeenden;
import mediathek.daten.DDaten;

public class DialogHinweisUpdate extends javax.swing.JDialog {

    private String text = "";
    private int sekunden = 20;
    private String titel = "";
    private DDaten daten;

    /**
     * 
     * @param parent
     * @param modal
     * @param ddaten
     * @param ttext
     * @param uurl
     * @param ttextCheck
     * @param ccheck
     * @param dialogTitel
     */
    public DialogHinweisUpdate(java.awt.Frame parent, boolean modal, String ttext, String dialogTitel, DDaten ddaten) {
        super(parent, modal);
        text = ttext;
        daten = ddaten;
        this.setTitle(dialogTitel);
        initComponents();
        initBeob();
        if (daten.mediathekGui != null) {
            //nicht in der Autofunktion
            titel = daten.mediathekGui.getTitle();
            daten.mediathekGui.setTitle("Programmversion ist aktuell");
        }
        new Thread(new Zeit()).start();
        this.dispose();
    }

    private void initBeob() {
        jButtonOk.addActionListener(new BeobBeenden());
        jTextArea1.setText(text);
        new EscBeenden(this) {

            @Override
            public void beenden_() {
                beenden();
            }
        };
    }

    private void beenden() {
        this.dispose();
    }

    private class Zeit implements Runnable {
        // warten sekunden Sekunden

        @Override
        public synchronized void run() {
            try {
                for (int i = sekunden; i > 0; --i) {
                    if (i % 3 == 0) {
                        if (daten.mediathekGui != null) {
                            daten.mediathekGui.setTitle(" * " + daten.mediathekGui.getTitle() + " * ");
                        }
                    }
                    jLabel1.setText("Schließen in " + i + " Sekunden");
                    this.wait(1000);
                }
            } catch (InterruptedException ex) {
            }
            if (daten.mediathekGui != null) {
                daten.mediathekGui.setTitle(titel);
            }
            beenden();
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButtonOk = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setRows(5);
        jTextArea1.setText("\n\n");
        jScrollPane1.setViewportView(jTextArea1);

        jButtonOk.setText("OK");

        jLabel1.setText("                                        ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonOk, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(jButtonOk))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonOk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables

    private class BeobBeenden implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            beenden();
        }
    }
}
