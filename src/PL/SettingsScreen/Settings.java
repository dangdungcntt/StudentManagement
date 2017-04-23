package PL.SettingsScreen;

import DAL.Env;
import PL.Tools.CreateComponent;
import PL.Tools.GridBagBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Nguyen Dang Dung on 4/21/2017 2:24 PM
 * Project: BTLJavaDangDung
 */
public class Settings extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JPanel leftPanel, rightPanel, bottomPanel;
    public JButton btnSystem, btnDatabase, btnUpdate;
    public JButton btnOk, btnCancel;
    public SystemPanel systemPanel;
    public DatabasePanel databasePanel;
    public UpdatePanel updatePanel;
    public ActionListener changeMenuSettings;
    GridBagLayout gb;
    GridBagConstraints gbc;
    Color unSelectedColor;
    Color selectedColor;

    public Settings(int width, int height) {
        this.setLayout(new FlowLayout());
        this.setSize(width, height);
        init();
        addEvents();
    }

    private void addEvents() {
        changeMenuSettings = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                Object btn = e.getSource();
                change((Component) btn);
                rightPanel.removeAll();
                if (btn == btnSystem) {
                    rightPanel.add(systemPanel);
                } else if (btn == btnDatabase) {
                    rightPanel.add(databasePanel);
                } else if (btn == btnUpdate) {
                    rightPanel.add(updatePanel);
                }
                rightPanel.revalidate();
            }
        };
    }

    private void init() {
        unSelectedColor = new Color(230, 235, 240);
        selectedColor = new Color(184, 207, 229);
        gb = new GridBagLayout();
        gbc = new GridBagConstraints();
        this.setLayout(gb);

        //left panel
        leftPanel = new JPanel();
        leftPanel.setLayout(new FlowLayout());
        leftPanel.setPreferredSize(new Dimension(160, 480));
        leftPanel.setBackground(unSelectedColor);
        leftPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 1, Color.GRAY));
        btnSystem = CreateComponent.createFlatJButton(
                Env.envVar.get("STR_SYSTEM"),
                158, 50, selectedColor);
        btnDatabase = CreateComponent.createFlatJButton(
                Env.envVar.get("STR_DATABASE"),
                158, 50, unSelectedColor);
        btnUpdate = CreateComponent.createFlatJButton(
                Env.envVar.get("STR_UPDATE"),
                158, 50, unSelectedColor);
        leftPanel.add(btnSystem);
        leftPanel.add(btnDatabase);
        leftPanel.add(btnUpdate);

        //right panel
        rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(530, 480));
        rightPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY));
        //bottom panel
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.setPreferredSize(new Dimension(690, 40));
        bottomPanel.setBackground(unSelectedColor);
        bottomPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY));
        btnOk = new JButton(Env.envVar.get("STR_OK"));
        btnOk.setPreferredSize(new Dimension(100, 30));
        btnCancel = new JButton(Env.envVar.get("STR_CANCEL"));
        btnCancel.setPreferredSize(new Dimension(100, 30));
        JLabel lblSpace = new JLabel("");
        lblSpace.setPreferredSize(new Dimension(470, 30));
        bottomPanel.add(lblSpace);
        bottomPanel.add(btnOk);
        bottomPanel.add(btnCancel);
        //addComponent
        GridBagBuilder.addComponent(gb, gbc, this, leftPanel, 0, 0, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, this, rightPanel, 0, 1, 0, 0);
        gbc.gridwidth = 2;
        GridBagBuilder.addComponent(gb, gbc, this, bottomPanel, 1, 0, 0, 0);

//        this.setContentPane(panel);
//        this.setIconImage(new ImageIcon(this.getClass().getResource(Env.envVar.get("IMG_SETTINGS"))).getImage());
        systemPanel = new SystemPanel();
        databasePanel = new DatabasePanel();
        updatePanel = new UpdatePanel();
        rightPanel.add(systemPanel);
    }

    private void change(Component c) {
        btnSystem.setBackground(unSelectedColor);
        btnDatabase.setBackground(unSelectedColor);
        btnUpdate.setBackground(unSelectedColor);
        c.setBackground(selectedColor);
    }
}
