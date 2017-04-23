package PL.MainScreen;

import DAL.Env;
import PL.SettingsScreen.Settings;
import PL.Tools.ShowDialog;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Nguyen Dang Dung on 4/5/2017 2:21 PM
 * Project: BaiTapLonJava
 */

public class MainFrame extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
        attr
     */
    public static MainPanel mainPanel;

    /*
        constructor
     */

    public MainFrame(String title, int width, int height) {
        super(title);
        init(width, height);
        mainPanel.mainScreenMenuBar.itemSettings.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                showSettings();
            }
        });
    }

    private void showSettings() {
        Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
        final JDialog d1 = new JDialog(this);
        final JDialog d2 = new JDialog(d1, Env.envVar.get("STR_SETTINGS"), Dialog.ModalityType.DOCUMENT_MODAL);
        d2.setIconImage(new ImageIcon(this.getClass().getResource(Env.envVar.get("IMG_SETTINGS"))).getImage());
        d2.setBounds(ss.width / 2 - 355,
                ss.height / 2 - 280, 710, 560);
        final Settings settings = new Settings(700, 550);
        settings.btnCancel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                d2.dispose();
                d1.dispose();
            }
        });
        settings.btnOk.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                settings.systemPanel.getData();
                settings.systemPanel.saveData();
                settings.databasePanel.getData();
                settings.databasePanel.saveData();
                settings.updatePanel.getData();
                settings.updatePanel.saveData();
                d2.dispose();
                d1.dispose();
                ShowDialog.messWhenSaveSettings();
            }


        });

        settings.btnSystem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                settings.changeMenuSettings.actionPerformed(e);
                d2.repaint();
            }
        });
        settings.btnDatabase.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                settings.changeMenuSettings.actionPerformed(e);
                d2.repaint();
            }
        });
        settings.btnUpdate.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                settings.changeMenuSettings.actionPerformed(e);
                d2.repaint();
            }
        });
        d2.setContentPane(settings);
        d2.setVisible(true);
    }

    private void init(int width, int height) {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        //createComponent
        mainPanel = new MainPanel();

        //addComponent
        this.setJMenuBar(mainPanel.mainScreenMenuBar);
        this.setContentPane(mainPanel);
        this.setIconImage(new ImageIcon(this.getClass().getResource(Env.envVar.get("IMG_ICON"))).getImage());
        //show
        this.setVisible(true);
    }
}
