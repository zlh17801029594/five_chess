package com.zhoulihuang.net;

import javax.swing.*;

public class PanelTiming extends JPanel {
    JLabel myName;
    JLabel opName;

    public PanelTiming() {
        myName = new JLabel("My Name", SwingConstants.CENTER);
        opName = new JLabel("Op Name", SwingConstants.CENTER);

        this.add(myName);
        this.add(opName);
    }

    public void setMyName(String name) {
        this.myName.setText(name);
    }

    public void setOpName(String name) {
        this.opName.setText(name);
    }
}
