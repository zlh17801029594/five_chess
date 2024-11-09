package com.zhoulihuang.net;

import java.awt.*;

public class PanelUserList extends Panel {
    public List userList = new List(6);

    public PanelUserList() {
        this.setLayout(new BorderLayout());
        this.add(userList, BorderLayout.CENTER);
    }
}
