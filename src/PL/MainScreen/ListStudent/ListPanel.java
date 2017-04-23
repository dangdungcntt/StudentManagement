package PL.MainScreen.ListStudent;

import DAL.City;
import DAL.Env;
import DAL.Student;
import PL.MainScreen.MainPanel;
import PL.Tools.CreateComponent;
import PL.Tools.CustomPanel;
import PL.Tools.GridBagBuilder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.HashMap;

/**
 * Created by Nguyen Dang Dung on 4/4/2017 2:19 PM
 * Project: BaiTapLonJava
 */
public class ListPanel extends CustomPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int TOTAL_PAGE = 1;
    public static int CUR_PAGE = 1;
    public static int NUMBER_PERPAGE = 100;
    public static int STATUS = 1;
    public JButton btnNext, btnPrev, btnGo;
    public JLabel lblGoTo, lblCurPage, lblTotalPage, lblSpace;
    public JTextField txtPageNumber;
    private JTable table;
    private GridBagLayout gb;
    private GridBagConstraints gbc;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    public ListPanel() {
        super();
        initListPanel();
        addEvents();
        NUMBER_PERPAGE = Integer.parseInt(Env.envVar.get("LIST_NUMBER_PER_PAGE"));
    }

    private void addEvents() {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                rowClick();
            }
        });
    }

    private void rowClick() {
        int row = table.getSelectedRow();
        if (row < 0) return;
        String[] student = new String[8];
        student[0] = (String) tableModel.getValueAt(row, 1);
        student[1] = (String) tableModel.getValueAt(row, 2);
        student[2] = (String) tableModel.getValueAt(row, 3);
        student[3] = (String) tableModel.getValueAt(row, 4);
        student[4] = (String) tableModel.getValueAt(row, 5);
        student[5] = (String) tableModel.getValueAt(row, 6);
        student[6] = (String) tableModel.getValueAt(row, 7);
        student[7] = (String) tableModel.getValueAt(row, 8);
        MainPanel.showDetailHandle(student);
    }

    private void initListPanel() {
        //setLayout
        gb = new GridBagLayout();
        gbc = new GridBagConstraints();
        this.setLayout(gb);

        //createComponent
        table = newJTable();
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800, 275));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel panel = new CustomPanel();
        panel.setLayout(gb);
        btnPrev = CreateComponent.createJButton("", 80, 30, Font.BOLD, 14, JButton.CENTER);
        btnNext = CreateComponent.createJButton("", 80, 30, Font.BOLD, 14, JButton.CENTER);
        btnGo = CreateComponent.createJButton("", 50, 30, Font.BOLD, 14, JButton.CENTER);
        lblCurPage = CreateComponent.createJLabel("", 30, 30, Font.BOLD, 14, JButton.CENTER);
        lblTotalPage = CreateComponent.createJLabel("", 30, 30, Font.BOLD, 14, JButton.CENTER);
        lblGoTo = CreateComponent.createJLabel(Env.envVar.get("STR_GO_TO_PAGE"), 80, 30, Font.BOLD, 14, JButton.CENTER);
        lblSpace = CreateComponent.createJLabel("", 290, 30, Font.BOLD, 14, JButton.CENTER);
        txtPageNumber = CreateComponent.createJTextField("", 50, 30, Font.BOLD, 14, JButton.CENTER);
        btnPrev.setIcon(new ImageIcon(this.getClass().getResource(Env.envVar.get("IMG_PREV"))));
        btnNext.setIcon(new ImageIcon(this.getClass().getResource(Env.envVar.get("IMG_NEXT"))));
        btnGo.setIcon(new ImageIcon(this.getClass().getResource(Env.envVar.get("IMG_GO"))));
        gbc.insets = new Insets(3, 5, 2, 5);
        GridBagBuilder.addComponent(gb, gbc, panel, lblGoTo, 0, 0, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, panel, txtPageNumber, 0, 1, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, panel, btnGo, 0, 2, 0, 0);

        GridBagBuilder.addComponent(gb, gbc, panel, lblSpace, 0, 3, 0, 0);

        GridBagBuilder.addComponent(gb, gbc, panel, btnPrev, 0, 4, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, panel, lblCurPage, 0, 5, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, panel, new JLabel("/"), 0, 6, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, panel, lblTotalPage, 0, 7, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, panel, btnNext, 0, 8, 0, 0);


        GridBagBuilder.addComponent(gb, gbc, this, scrollPane, 0, 0, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, this, panel, 1, 0, 0, 0);


//        this.add(panel);
        this.setBorder(CreateComponent.createBorder(Env.envVar.get("TIT_LIST")));


    }

    private JTable newJTable() {
        String[] colName = {
                Env.envVar.get("STR_NO"),
                Env.envVar.get("STR_STUDENT_ID"),
                Env.envVar.get("STR_STUDENT_NAME"),
                Env.envVar.get("STR_STUDENT_CITY"),
                Env.envVar.get("STR_STUDENT_BIRTH"),
                Env.envVar.get("STR_STUDENT_GENDER"),
                Env.envVar.get("STR_STUDENT_MATH"),
                Env.envVar.get("STR_STUDENT_PHYSICAL"),
                Env.envVar.get("STR_STUDENT_CHEMISTRY")
        };

        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(colName);

        JTable tb = new JTable() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        tb.setModel(tableModel);
        tb.setRowHeight(20);
        tb.setFillsViewportHeight(true);
        tb.setColumnSelectionAllowed(false);
        tb.setSelectionBackground(Color.cyan);
        tb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tb.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //Ä‘áº·t thuá»™c tÃ­nh cho cÃ¡c cá»™t
        tb.getTableHeader().setResizingAllowed(false); //khÃ´ng thay Ä‘á»•i kÃ­ch thÆ°á»›c
        tb.getTableHeader().setReorderingAllowed(false); //khÃ´ng sáº¯p sáº¿p láº¡i vá»‹ trÃ­ cÃ¡c cá»™t
        tb.getTableHeader().setForeground(Color.BLUE); //mÃ u chá»¯ cá»§a tiÃªu Ä‘á»�
        tb.getTableHeader().setFont(new Font(null, Font.BOLD, 14)); //font, style

        //Ä‘áº·t chiá»�u rá»™ng cho cÃ¡c cá»™t
        tb.getColumnModel().getColumn(0).setPreferredWidth(45);
        tb.getColumnModel().getColumn(1).setPreferredWidth(95);
        tb.getColumnModel().getColumn(2).setPreferredWidth(167);
        tb.getColumnModel().getColumn(3).setPreferredWidth(95);
        tb.getColumnModel().getColumn(4).setPreferredWidth(85);
        tb.getColumnModel().getColumn(5).setPreferredWidth(80);
        tb.getColumnModel().getColumn(6).setPreferredWidth(65);
        tb.getColumnModel().getColumn(7).setPreferredWidth(70);
        tb.getColumnModel().getColumn(8).setPreferredWidth(80);

        return tb;
    }

    public void initDataTable(HashMap<Integer, Student> mapStudent, HashMap<Integer, City> mapCity) {
        int i = 1;
        if (ListPanel.CUR_PAGE > 1) {
            i = ListPanel.NUMBER_PERPAGE * (CUR_PAGE - 1) + 1;
        }
        tableModel.setRowCount(0); //xÃ³a táº¥t cáº£ ná»™i dung hiá»‡n táº¡i
        for (Student student : mapStudent.values()) { //thÃªm danh sÃ¡ch vÃ o báº£ng
            String[] row = new String[9];
            row[0] = String.valueOf(i++);
            row[1] = String.valueOf(student.getStudentId());
            row[2] = student.getStudentName();
            row[3] = mapCity.get(student.getCityId()).getCityName();
            row[4] = format.format(student.getBirth());
            row[5] = student.isGender() ? Env.envVar.get("STR_STUDENT_MALE") : Env.envVar.get("STR_STUDENT_FEMALE");
            row[6] = String.valueOf(student.getMath());
            row[7] = String.valueOf(student.getPhysical());
            row[8] = String.valueOf(student.getChemistry());
            tableModel.addRow(row);
        }
    }

    public void updateNextPrev() {
        if (TOTAL_PAGE == 0) {
            lblCurPage.setText(String.valueOf(TOTAL_PAGE));
            lblTotalPage.setText(String.valueOf(TOTAL_PAGE));
            btnPrev.setEnabled(false);
            btnNext.setEnabled(false);
        } else {
            lblCurPage.setText(String.valueOf(CUR_PAGE));
            lblTotalPage.setText(String.valueOf(TOTAL_PAGE));
            btnPrev.setEnabled(true);
            btnNext.setEnabled(true);
            if (CUR_PAGE == 1) btnPrev.setEnabled(false);
            if (CUR_PAGE == TOTAL_PAGE) btnNext.setEnabled(false);
        }

    }
}
