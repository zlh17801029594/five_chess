package com.zhoulihuang.net;

import java.awt.*;

public class PanelMessage extends Panel {
    public TextArea messageArea;

    public PanelMessage() {
        this.setLayout(new BorderLayout());
        messageArea = new TextArea("", 6, 20, TextArea.SCROLLBARS_VERTICAL_ONLY);
        this.add(messageArea, BorderLayout.CENTER);
    }
}
