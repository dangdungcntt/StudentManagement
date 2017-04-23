package PL.Tools;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Nguyen Dang Dung on 4/6/2017 10:21 PM
 * Project: BTLJavaDangDung
 */
public class CustomPanel extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomPanel() {
        super();
    }

    public void setEnabled(boolean aFlag) {
        for (Component component : this.getComponents()) {
            component.setEnabled(aFlag);
        }
    }
}
