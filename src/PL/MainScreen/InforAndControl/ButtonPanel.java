package PL.MainScreen.InforAndControl;

import DAL.Env;
import PL.MainScreen.MainPanel;
import PL.Tools.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * Created by Nguyen Dang Dung on 4/5/2017 1:54 PM
 * Project: BaiTapLonJava
 */
public class ButtonPanel extends CustomPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String STR_ADD = Env.envVar.get("STR_ADD");
    private String STR_EDIT = Env.envVar.get("STR_EDIT");
    private String STR_DELETE = Env.envVar.get("STR_DELETE");
    private String STR_OK = Env.envVar.get("STR_OK");
    private String STR_EXIT = Env.envVar.get("STR_EXIT");
    private String STR_CANCEL = Env.envVar.get("STR_CANCEL");

    private URL IMG_ADD = this.getClass().getResource(Env.envVar.get("IMG_ADD"));
    private URL IMG_EDIT = this.getClass().getResource(Env.envVar.get("IMG_EDIT"));
    private URL IMG_DELETE = this.getClass().getResource(Env.envVar.get("IMG_DELETE"));
    private URL IMG_OK = this.getClass().getResource(Env.envVar.get("IMG_OK"));
    private URL IMG_EXIT = this.getClass().getResource(Env.envVar.get("IMG_EXIT"));
    private URL IMG_CANCEL = this.getClass().getResource(Env.envVar.get("IMG_CANCEL"));

    private JButton btnAdd, btnEdit, btnDelete, btnOk, btnExit;
    private GridBagLayout gb;
    private GridBagConstraints gbc;

    public ButtonPanel() {
        super();
        initButtonPanel();
        addEvents();
    }

    private void addEvents() {
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addHandle();
            }
        });
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainPanel.executeCommand(Commands.EDIT_STUDENT);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainPanel.executeCommand(Commands.DELETE_STUDENT);
            }
        });
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainPanel.executeCommand(Commands.OK);
            }
        });
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (InforPanel.STATUS == InforPanel.FREE) {
                    ShowDialog.confirmExit();
                } else if (InforPanel.STATUS != InforPanel.FREE) {
                    MainPanel.executeCommand(Commands.CANCEL);
                }
            }
        });

    }

    private void addHandle() {
        MainPanel.executeCommand(Commands.ADD_STUDENT);
    }

    private void initButtonPanel() {
        //setLayout
        gb = new GridBagLayout();
        gbc = new GridBagConstraints();
        this.setLayout(gb);

        //createComponent
        btnAdd = CreateComponent.createJButton(STR_ADD, 130, 40, Font.BOLD, 16, JButton.LEFT);
        btnAdd.setIcon(new ImageIcon(IMG_ADD));
        btnEdit = CreateComponent.createJButton(STR_EDIT, 130, 40, Font.BOLD, 16, JButton.LEFT);
        btnEdit.setIcon(new ImageIcon(IMG_EDIT));
        btnDelete = CreateComponent.createJButton(STR_DELETE, 130, 40, Font.BOLD, 16, JButton.LEFT);
        btnDelete.setIcon(new ImageIcon(IMG_DELETE));
        btnOk = CreateComponent.createJButton(STR_OK, 130, 40, Font.BOLD, 16, JButton.LEFT);
        btnOk.setIcon(new ImageIcon(IMG_OK));
        btnExit = CreateComponent.createJButton(STR_EXIT, 130, 40, Font.BOLD, 16, JButton.LEFT);
        btnExit.setIcon(new ImageIcon(IMG_EXIT));

        // addComponent
        gbc.insets = new Insets(0, 5, 0, 5);
        GridBagBuilder.addComponent(gb, gbc, this, btnAdd, 0, 0, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, this, btnEdit, 0, 1, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, this, btnDelete, 0, 2, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, this, btnOk, 0, 3, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, this, btnExit, 0, 4, 0, 0);

        //initStatus
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);
        btnOk.setEnabled(false);
    }

    public void executeCommand(int command) {
        if (command == Commands.SHOW_STUDENT_DETAIL) {
            btnEdit.setEnabled(true);
            btnDelete.setEnabled(true);
        } else if (command == Commands.FILTER_STUDENT) {
            btnEdit.setEnabled(false);
            btnDelete.setEnabled(false);
        } else if (command == Commands.ADD_STUDENT) {
            btnEdit.setEnabled(false);
            btnDelete.setEnabled(false);
            btnAdd.setEnabled(false);
            btnOk.setEnabled(true);
            btnExit.setText(STR_CANCEL);
            btnExit.setIcon(new ImageIcon(IMG_CANCEL));
        } else if (command == Commands.EDIT_STUDENT) {
            btnAdd.setEnabled(false);
            btnEdit.setEnabled(false);
            btnDelete.setEnabled(false);
            btnOk.setEnabled(true);
            btnExit.setIcon(new ImageIcon(IMG_CANCEL));
            btnExit.setText(STR_CANCEL);
        } else if (command == Commands.OK) {
            btnAdd.setEnabled(true);
            btnEdit.setEnabled(true);
            btnDelete.setEnabled(true);
            btnOk.setEnabled(false);
            btnExit.setText(STR_EXIT);
            btnExit.setIcon(new ImageIcon(IMG_EXIT));
        } else if (command == Commands.CANCEL) {
            if (InforPanel.STATUS == InforPanel.EDITING) {
                btnEdit.setEnabled(true);
                btnDelete.setEnabled(true);
            } else {
                btnEdit.setEnabled(false);
                btnDelete.setEnabled(false);
            }
            btnAdd.setEnabled(true);
            btnOk.setEnabled(false);
            btnExit.setText(STR_EXIT);
            btnExit.setIcon(new ImageIcon(IMG_EXIT));
        }
    }
}
