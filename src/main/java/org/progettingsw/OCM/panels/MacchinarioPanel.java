package org.progettingsw.OCM.panels;

import javax.swing.JLabel;
import javax.swing.JPanel;

abstract class MacchinarioPanel {
	
	public JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Da implementare"));
        return panel;
    }

}
