package PL.MainScreen;

import DAL.Env;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class About extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    public About(String title, Component c) {
        super(title);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(640, 430);
        this.setLocationRelativeTo(c);
        this.setResizable(false);
//
        this.setType(Type.UTILITY);
        this.add(new JLabel("", new ImageIcon(this.getClass().getResource(Env.envVar.get("IMG_ABOUTPANEL"))), JLabel.CENTER));
        this.setIconImage(new ImageIcon(this.getClass().getResource(Env.envVar.get("IMG_ITEMABOUT"))).getImage());
        this.addWindowFocusListener(new WindowFocusListener() {
            public void windowGainedFocus(WindowEvent e) {

            }

            public void windowLostFocus(WindowEvent e) {
                dispose();
            }
        });
        this.setVisible(true);
    }

}
