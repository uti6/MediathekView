/*    
 *    MediathekView
 *    Copyright (C) 2012   W. Xaver
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
package mediathek.gui.dialogEinstellungen;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import mediathek.Daten;
import mediathek.Log;
import mediathek.controller.filme.filmeImportieren.MediathekListener;
import mediathek.controller.io.IoXmlLesen;
import mediathek.controller.io.ListeProgrammgruppenVorlagen;
import mediathek.daten.DDaten;
import mediathek.daten.DatenPgruppe;
import mediathek.daten.ListePgruppe;
import mediathek.gui.PanelVorlage;
import mediathek.tool.GuiFunktionen;

public class PanelImportPgruppe extends PanelVorlage {

    public PanelImportPgruppe(DDaten d) {
        super(d);
        initComponents();
        init();
    }

    private void init() {
        jButtonImportDatei.setEnabled(false);
        jButtonImportText.setEnabled(false);
        jButtonPfad.addActionListener(new BeobPfad());
        jTextFieldDatei.getDocument().addDocumentListener(new BeobPfadDoc());
        jTextAreaImport.getDocument().addDocumentListener(new BeobTextArea());
        jButtonImportVorlage.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!jTextFieldUrl.getText().equals("")) {
                    importDatei(jTextFieldUrl.getText());
                }
            }
        });
        jButtonImportDatei.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                importDatei(jTextFieldDatei.getText());
            }
        });
        jButtonImportText.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                importText();
            }
        });
        jButtonAktualisieren.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tabelleLaden();
            }
        });
        jTableVorlagen.getSelectionModel().addListSelectionListener(new BeobTableSelect());
        tabelleLaden();
    }

    private void importDatei(String datei) {
        DatenPgruppe[] pGruppe;
        pGruppe = IoXmlLesen.importPgruppe(datei, true);
        if (pGruppe != null) {
            if (ddaten.listePgruppe.addPgruppe(pGruppe)) {
                Daten.notifyMediathekListener(MediathekListener.EREIGNIS_LISTE_PGRUPPE, PanelImportPgruppe.class.getSimpleName());
                JOptionPane.showMessageDialog(null, pGruppe.length + " Programmgruppe(n) importiert!",
                        "Ok", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Die Datei konnte nicht importiert werden!",
                    "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void importText() {
        DatenPgruppe[] pGruppe;
        pGruppe = IoXmlLesen.importPgruppeText(jTextAreaImport.getText(), true);
        if (pGruppe != null) {
            if (ddaten.listePgruppe.addPgruppe(pGruppe)) {
                Daten.notifyMediathekListener(MediathekListener.EREIGNIS_LISTE_PGRUPPE, PanelImportPgruppe.class.getSimpleName());
                JOptionPane.showMessageDialog(null, pGruppe.length + " Programmgruppe(n) importiert!",
                        "Ok", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Der Import war nicht möglich!",
                    "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void tabelleLaden() {
        jTableVorlagen.setModel(new ListeProgrammgruppenVorlagen().getTModel());
//        for (int i = 0; i < jTableVorlagen.getColumnCount(); ++i) {
//            if (i == FilmUpdateServer.FILM_UPDATE_SERVER_URL_NR) {
//                jTableVorlagen.getColumnModel().getColumn(i).setMinWidth(10);
//                jTableVorlagen.getColumnModel().getColumn(i).setMaxWidth(3000);
//                jTableVorlagen.getColumnModel().getColumn(i).setPreferredWidth(350);
//            } else {
//                jTableVorlagen.getColumnModel().getColumn(i).setMinWidth(10);
//                jTableVorlagen.getColumnModel().getColumn(i).setMaxWidth(3000);
//                jTableVorlagen.getColumnModel().getColumn(i).setPreferredWidth(100);
//            }
//        }
    }

    private void table1Select() {
        String[] vorlage = new String[ListeProgrammgruppenVorlagen.PGR_MAX_ELEM];
        for (int i = 0; i < ListeProgrammgruppenVorlagen.PGR_MAX_ELEM; i++) {
            vorlage[i] = "";
        }
        int selectedTableRow = jTableVorlagen.getSelectedRow();
        if (selectedTableRow >= 0) {
            int selectedModelRow = jTableVorlagen.convertRowIndexToModel(selectedTableRow);
            for (int i = 0; i < ListeProgrammgruppenVorlagen.PGR_MAX_ELEM; ++i) {
                vorlage[i] = jTableVorlagen.getModel().getValueAt(selectedModelRow, i).toString();
            }
        }
        jTextFieldName.setText(vorlage[ListeProgrammgruppenVorlagen.PGR_NAME_NR]);
        jTextFieldUrl.setText(vorlage[ListeProgrammgruppenVorlagen.PGR_URL_NR]);
        jTextAreaBeschreibung.setText(vorlage[ListeProgrammgruppenVorlagen.PGR_BESCHREIBUNG_NR]);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableVorlagen = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldUrl = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaBeschreibung = new javax.swing.JTextArea();
        jButtonImportVorlage = new javax.swing.JButton();
        jButtonAktualisieren = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTextFieldDatei = new javax.swing.JTextField();
        jButtonPfad = new javax.swing.JButton();
        jButtonImportDatei = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaImport = new javax.swing.JTextArea();
        jButtonImportText = new javax.swing.JButton();

        jTableVorlagen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTableVorlagen);

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Name:");

        jTextFieldName.setEditable(false);

        jLabel2.setText("URL:");

        jTextFieldUrl.setEditable(false);

        jLabel3.setText("Beschreibung:");

        jTextAreaBeschreibung.setColumns(20);
        jTextAreaBeschreibung.setEditable(false);
        jTextAreaBeschreibung.setRows(5);
        jScrollPane3.setViewportView(jTextAreaBeschreibung);

        jButtonImportVorlage.setText("Importieren");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldName)
                            .addComponent(jTextFieldUrl)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonImportVorlage)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldUrl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonImportVorlage)
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButtonImportVorlage, jTextFieldName, jTextFieldUrl});

        jButtonAktualisieren.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mediathek/res/view-refresh_16.png"))); // NOI18N
        jButtonAktualisieren.setToolTipText("Neu laden");

        jLabel4.setText("Tabelle aktualisieren:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAktualisieren)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4)
                    .addComponent(jButtonAktualisieren))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Vorlagen", jPanel3);

        jButtonPfad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mediathek/res/fileopen_16.png"))); // NOI18N

        jButtonImportDatei.setText("Importieren");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextFieldDatei)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonPfad))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 471, Short.MAX_VALUE)
                        .addComponent(jButtonImportDatei)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonPfad)
                    .addComponent(jTextFieldDatei, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonImportDatei)
                .addContainerGap(428, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButtonPfad, jTextFieldDatei});

        jTabbedPane1.addTab("Aus Datei imporieren", jPanel1);

        jTextAreaImport.setColumns(20);
        jTextAreaImport.setRows(5);
        jScrollPane1.setViewportView(jTextAreaImport);

        jButtonImportText.setText("Importieren");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonImportText)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonImportText)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Als Text importieren", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAktualisieren;
    private javax.swing.JButton jButtonImportDatei;
    private javax.swing.JButton jButtonImportText;
    private javax.swing.JButton jButtonImportVorlage;
    private javax.swing.JButton jButtonPfad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableVorlagen;
    private javax.swing.JTextArea jTextAreaBeschreibung;
    private javax.swing.JTextArea jTextAreaImport;
    private javax.swing.JTextField jTextFieldDatei;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldUrl;
    // End of variables declaration//GEN-END:variables

    private class BeobPfadDoc implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent arg0) {
            eingabe();
        }

        @Override
        public void removeUpdate(DocumentEvent arg0) {
            eingabe();
        }

        @Override
        public void changedUpdate(DocumentEvent arg0) {
            eingabe();
        }

        private void eingabe() {
            jButtonImportDatei.setEnabled(!jTextFieldDatei.getText().equals(""));
            if (jTextFieldDatei.getText().equals("")) {
                jTextFieldDatei.setBackground(javax.swing.UIManager.getDefaults().getColor("TextField.background"));
            } else {
                if (IoXmlLesen.importPgruppe(jTextFieldDatei.getText(), false) != null) {
                    jTextFieldDatei.setBackground(javax.swing.UIManager.getDefaults().getColor("TextField.background"));
                } else {
                    jTextFieldDatei.setBackground(new Color(255, 200, 200));
                }
            }
        }
    }

    private class BeobTextArea implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent arg0) {
            eingabe();
        }

        @Override
        public void removeUpdate(DocumentEvent arg0) {
            eingabe();
        }

        @Override
        public void changedUpdate(DocumentEvent arg0) {
            eingabe();
        }

        private void eingabe() {
            jButtonImportText.setEnabled(!jTextAreaImport.getText().equals(""));
            if (jTextAreaImport.getText().equals("")) {
                jTextAreaImport.setBackground(javax.swing.UIManager.getDefaults().getColor("TextArea.background"));
            } else {
                if (IoXmlLesen.importPgruppeText(jTextAreaImport.getText(), false) != null) {
                    jTextAreaImport.setBackground(javax.swing.UIManager.getDefaults().getColor("TextArea.background"));
                    jButtonImportText.setEnabled(true);
                } else {
                    jTextAreaImport.setBackground(new Color(255, 200, 200));
                    jButtonImportText.setEnabled(false);
                }
            }
        }
    }

    private class BeobPfad implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int returnVal;
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.setFileHidingEnabled(false);
            if (jTextFieldDatei.getText().equals("")) {
                chooser.setCurrentDirectory(new File(GuiFunktionen.getHomePath()));
            } else {
                chooser.setCurrentDirectory(new File(jTextFieldDatei.getText()));
            }
            returnVal = chooser.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    jTextFieldDatei.setText(chooser.getSelectedFile().getAbsolutePath());
                } catch (Exception ex) {
                    Log.fehlerMeldung("PanelImportProgramme.BeobPfad", ex);
                }
            }
        }
    }

    private class BeobTableSelect implements ListSelectionListener {

        public int selectedModelRow = -1;

        @Override
        public void valueChanged(ListSelectionEvent event) {
            if (!event.getValueIsAdjusting()) {
                table1Select();
            }
        }
    }
}