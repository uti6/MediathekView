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
package mediathek.gui.beobachter;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import mediathek.Log;
import mediathek.daten.DDaten;
import mediathek.daten.DatenPgruppe;
import mediathek.tool.GuiKonstanten;

public class CellRendererPguppen extends DefaultTableCellRenderer {

    DDaten daten;

    public CellRendererPguppen(DDaten d) {
        daten = d;
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) {
        setBackground(null);
        setForeground(null);
        setFont(null);
        setIcon(null);
        super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);
        try {
            int r = table.convertRowIndexToModel(row);
            int c = table.convertColumnIndexToModel(column);
            DatenPgruppe gruppe = daten.listePgruppe.get(r);
            if (c == DatenPgruppe.PROGRAMMGRUPPE_NAME_NR) {
                setForeground(gruppe.getFarbe(daten));
            }
            if (c == DatenPgruppe.PROGRAMMGRUPPE_IST_ABSPIELEN_NR) {
                if (gruppe.istAbspielen()) {
                    if (isSelected) {
                        setBackground(GuiKonstanten.ABO_SEL);
                    } else {
                        setBackground(GuiKonstanten.ABO);
                    }
                    setIcon(new javax.swing.ImageIcon(getClass().getResource("/mediathek/res/ja_16.png")));
                } else {
                    if (isSelected) {
                        setBackground(GuiKonstanten.FARBE_GRAU_SEL);
                    } else {
                        setBackground(GuiKonstanten.FARBE_GRAU);
                    }
                    setIcon(new javax.swing.ImageIcon(getClass().getResource("/mediathek/res/nein_12.png")));
                }
            }
            if (c == DatenPgruppe.PROGRAMMGRUPPE_IST_SPEICHERN_NR) {
                if (gruppe.istSpeichern()) {
                    if (isSelected) {
                        setBackground(GuiKonstanten.ABO_SEL);
                    } else {
                        setBackground(GuiKonstanten.ABO);
                    }
                    setIcon(new javax.swing.ImageIcon(getClass().getResource("/mediathek/res/ja_16.png")));
                } else {
                    if (isSelected) {
                        setBackground(GuiKonstanten.FARBE_GRAU_SEL);
                    } else {
                        setBackground(GuiKonstanten.FARBE_GRAU);
                    }
                    setIcon(new javax.swing.ImageIcon(getClass().getResource("/mediathek/res/nein_12.png")));
                }
            }
            if (c == DatenPgruppe.PROGRAMMGRUPPE_IST_BUTTON_NR) {
                if (gruppe.istButton()) {
                    if (isSelected) {
                        setBackground(GuiKonstanten.ABO_SEL);
                    } else {
                        setBackground(GuiKonstanten.ABO);
                    }
                    setIcon(new javax.swing.ImageIcon(getClass().getResource("/mediathek/res/ja_16.png")));
                } else {
                    if (isSelected) {
                        setBackground(GuiKonstanten.FARBE_GRAU_SEL);
                    } else {
                        setBackground(GuiKonstanten.FARBE_GRAU);
                    }
                    setIcon(new javax.swing.ImageIcon(getClass().getResource("/mediathek/res/nein_12.png")));
                }
            }
            if (c == DatenPgruppe.PROGRAMMGRUPPE_IST_ABO_NR) {
                if (gruppe.istAbo()) {
                    if (isSelected) {
                        setBackground(GuiKonstanten.ABO_SEL);
                    } else {
                        setBackground(GuiKonstanten.ABO);
                    }
                    setIcon(new javax.swing.ImageIcon(getClass().getResource("/mediathek/res/ja_16.png")));
                } else {
                    if (isSelected) {
                        setBackground(GuiKonstanten.FARBE_GRAU_SEL);
                    } else {
                        setBackground(GuiKonstanten.FARBE_GRAU);
                    }
                    setIcon(new javax.swing.ImageIcon(getClass().getResource("/mediathek/res/nein_12.png")));
                }
            }
        } catch (Exception ex) {
            Log.fehlerMeldung(this.getClass().getName(), ex);
        }
        return this;
    }
}
