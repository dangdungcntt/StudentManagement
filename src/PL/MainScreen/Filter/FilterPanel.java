package PL.MainScreen.Filter;

import DAL.Env;
import PL.MainScreen.ListStudent.ListPanel;
import PL.MainScreen.MainPanel;
import PL.Tools.Commands;
import PL.Tools.CreateComponent;
import PL.Tools.CustomPanel;
import PL.Tools.GridBagBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;

/**
 * Created by Nguyen Dang Dung on 4/4/2017 1:21 PM
 * Project: BaiTapLonJava
 */
public class FilterPanel extends CustomPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static int FILTERING = 1;
    public final static int NOFILTER = 0;


    public static int STATUS; //Ä‘Ã¡nh dáº¥u tráº¡ng thÃ¡i cá»§a panel (Ä‘ang lá»�c hay khÃ´ng lá»�c)

    private String TIT_FILTER = Env.envVar.get("TIT_FILTER");
    private String STR_STUDENT_CITY = Env.envVar.get("STR_STUDENT_CITY");
    private String STR_STUDENT_ID = Env.envVar.get("STR_STUDENT_ID");
    private String STR_FILTER = Env.envVar.get("STR_FILTER");
    private String STR_CANCEL = Env.envVar.get("STR_CANCEL");
    private URL IMG_FILTER = this.getClass().getResource(Env.envVar.get("IMG_FILTER"));
    private URL IMG_CANCEL = this.getClass().getResource(Env.envVar.get("IMG_CANCEL"));


    private JLabel lblCity, lblStudentId;
    private JTextField txtCity, txtStudentId;
    private JButton btnFilter;
    private GridBagLayout gb;
    private GridBagConstraints gbc;

    public FilterPanel() {
        super();
        initFilterPanel();
        STATUS = NOFILTER;
        addEvents();
    }

    private void addEvents() {
        btnFilter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterHandle();
            }
        });
        KeyAdapter enterInTextField = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    filterHandle();
                }
            }
        };
        txtCity.addKeyListener(enterInTextField);
        txtStudentId.addKeyListener(enterInTextField);
    }

    private void filterHandle() {
        String city = txtCity.getText().trim();
        String studentId = txtStudentId.getText().trim();
        MainPanel.filterHandle(city, studentId, 1, ListPanel.NUMBER_PERPAGE, true);
    }

    private void initFilterPanel() {
        //setLayout
        gb = new GridBagLayout();
        gbc = new GridBagConstraints();
        this.setLayout(gb);

        //createComponent
        lblCity = CreateComponent.createJLabel(STR_STUDENT_CITY, 100, 30, Font.BOLD, 14, JLabel.CENTER);
        lblStudentId = CreateComponent.createJLabel(STR_STUDENT_ID, 100, 30, Font.BOLD, 14, JLabel.CENTER);
        txtCity = CreateComponent.createJTextField("", 60, 30, Font.PLAIN, 14, JTextField.LEFT);
        txtStudentId = CreateComponent.createJTextField("", 90, 30, Font.PLAIN, 14, JTextField.LEFT);
        btnFilter = CreateComponent.createJButton(STR_FILTER, 130, 40, Font.BOLD, 16, JButton.LEFT);
        btnFilter.setIcon(new ImageIcon(IMG_FILTER));

        //addComponent
        GridBagBuilder.addComponent(gb, gbc, this, lblCity, 0, 0, 50, 0);
        GridBagBuilder.addComponent(gb, gbc, this, txtCity, 0, 1, 100, 0);
        GridBagBuilder.addComponent(gb, gbc, this, lblStudentId, 0, 2, 50, 0);
        GridBagBuilder.addComponent(gb, gbc, this, txtStudentId, 0, 3, 100, 0);
        gbc.insets = new Insets(3, 10, 10, 10);
        GridBagBuilder.addComponent(gb, gbc, this, btnFilter, 0, 4, 10, 0);

        this.setBorder(CreateComponent.createBorder(TIT_FILTER));
    }

    public void executeCommand(int command) {
        if (command == Commands.FILTER_STUDENT) {
            if (STATUS == NOFILTER) { //báº¥m nÃºt Filter
                txtStudentId.setEditable(false);
                txtCity.setEditable(false);
                btnFilter.setIcon(new ImageIcon(IMG_CANCEL));
                btnFilter.setText(STR_CANCEL);
                STATUS = FILTERING; //Ä‘Ã¡nh dáº¥u tráº¡ng thÃ¡i Ä‘ang lá»�c
            } else if (STATUS == FILTERING) { // báº¥m nÃºt Cancel
                txtStudentId.setEditable(true);
                txtStudentId.setText("");
                txtCity.setEditable(true);
                txtCity.setText("");
                btnFilter.setText(STR_FILTER);
                btnFilter.setIcon(new ImageIcon(IMG_FILTER));
                STATUS = NOFILTER;  //Ä‘Ã¡nh dáº¥u tráº¡ng thÃ¡i khÃ´ng lá»�c
            }
        }

    }

    public String[] getData() {
        return new String[]{txtCity.getText().trim(), txtStudentId.getText().trim()};
    }
}
