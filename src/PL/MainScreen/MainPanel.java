package PL.MainScreen;

import BLL.CityBLL;
import BLL.StudentBLL;
import DAL.Env;
import DAL.Student;
import PL.MainScreen.Filter.FilterPanel;
import PL.MainScreen.InforAndControl.InforPanel;
import PL.MainScreen.ListStudent.ListPanel;
import PL.Tools.Commands;
import PL.Tools.CustomPanel;
import PL.Tools.GridBagBuilder;
import PL.Tools.ShowDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.ConnectException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Nguyen Dang Dung on 4/4/2017 1:10 PM
 * Project: BaiTapLonJava
 */
public class MainPanel extends CustomPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static FilterPanel filterPanel;
    private static ListPanel listPanel;
    private static InforPanel inforPanel;
    private static StudentBLL studentBLL;
    private static CityBLL cityBLL;
    public MainScreenMenuBar mainScreenMenuBar;
    private GridBagLayout gb;
    private GridBagConstraints gbc;

    public MainPanel() {
        super();
        initMainPanel();
        initData();
        addEvents();

    }

    public static void filterHandle(String cityName, String studentId, int page, int number, boolean clickCanel) {
        listPanel.txtPageNumber.setText("");
        listPanel.btnGo.setEnabled(false);
        if (cityName.length() == 0 && studentId.length() == 0) {//náº¿u danh sÃ¡ch khÃ´ng Ä‘áº§y Ä‘á»§ thÃ¬ má»›i ghi Ä‘áº§y Ä‘á»§
            try {
                studentBLL.readData(page, number);
            } catch (ConnectException e) {
                ShowDialog.cannotConnectDB();
            }
            listPanel.initDataTable(studentBLL.getMapStudent(), cityBLL.getMapCity());
            listPanel.updateNextPrev();
        } else {
            if (FilterPanel.STATUS == FilterPanel.FILTERING && clickCanel) { //báº¥m nÃºt canel
                try {
                    studentBLL.readData(page, number);
                } catch (ConnectException e) {
                    ShowDialog.cannotConnectDB();
                }
                listPanel.initDataTable(studentBLL.getMapStudent(), cityBLL.getMapCity());
                listPanel.updateNextPrev();
                executeCommand(Commands.FILTER_STUDENT);
            } else {
                if (!clickCanel) { //báº¥m nÃºt next or prev trong khi Ä‘ang lá»�c
                    FilterPanel.STATUS = FilterPanel.NOFILTER; //Ä‘á»•i tráº¡ng thÃ¡i vá»� khÃ´ng lá»�c Ä‘á»ƒ lá»�c tiáº¿p tá»¥c
                    //nhÆ°ng láº¥y á»Ÿ trang tiáº¿p theo
                }
                executeCommand(Commands.FILTER_STUDENT); //Ä‘iá»�u chá»‰nh tráº¡ng thÃ¡i cÃ¡c component trÃªn FilterPanel
                int stId = -1;
                if (studentId.length() > 0) {
                    try {
                        stId = Integer.parseInt(studentId);
                    } catch (NumberFormatException e) {
                        stId = -1;
                        ShowDialog.studenIdIsInteger();
                    }
                }
                try {
                    studentBLL.filterStudent(cityName, stId, page, number);
                } catch (ConnectException e) {
                    ShowDialog.cannotConnectDB();
                }
                listPanel.initDataTable(studentBLL.getMapStudent(), cityBLL.getMapCity());
                listPanel.updateNextPrev();
            }
        }
    }

    public static void showDetailHandle(String[] student) {
        if (InforPanel.STATUS == InforPanel.FREE) {
            inforPanel.showDetail(student);
        }
    }

    private static void refreshTable() {
        try {
            ListPanel.CUR_PAGE = 1;
            studentBLL.readData(ListPanel.CUR_PAGE, ListPanel.NUMBER_PERPAGE);
            listPanel.initDataTable(studentBLL.getMapStudent(), cityBLL.getMapCity());
            listPanel.updateNextPrev();
            if (FilterPanel.STATUS == FilterPanel.FILTERING) {
                filterPanel.executeCommand(Commands.FILTER_STUDENT);
            }
        } catch (ConnectException e) {
            ShowDialog.cannotConnectDB();
        }
    }

    public static void executeCommand(int command) {
        if (command == Commands.FILTER_STUDENT) {
            filterPanel.executeCommand(command);
        } else if (command == Commands.OK) {
            String[] student = inforPanel.getData();
            int confirm = okHandle(student);
            if (confirm == 1) {
                inforPanel.executeCommand(command);
                refreshTable();
            }
        } else if (command == Commands.ADD_STUDENT) {
            inforPanel.executeCommand(command);
            inforPanel.addCityData(cityBLL.getMapCity());
        } else if (command == Commands.EDIT_STUDENT) {
            inforPanel.executeCommand(command);
            inforPanel.addCityData(cityBLL.getMapCity());
        } else if (command == Commands.DELETE_STUDENT) {
            String[] student = inforPanel.getData();
            int confirm = deleteHandle(student);
            if (confirm == 1) {
                inforPanel.executeCommand(command);
                refreshTable();
            }
        } else {
            inforPanel.executeCommand(command);
        }
    }

    private static int deleteHandle(String[] student) {
        Student st = new Student();
        if (!checkStudent(student, st)) return -1;
        if (ShowDialog.confirmDeleteStudent(st, student[2])) {
            try {
                if (!studentBLL.delete(st.getStudentId())) {
                    ShowDialog.cannotConnectDB();
                    return -1;
                }
                ShowDialog.deleted();
                return 1;
            } catch (ConnectException e) {
                ShowDialog.cannotConnectDB();
            }
        }
        return -1;
    }

    private static int okHandle(String[] student) {
        Student st = new Student();
        if (!checkStudent(student, st)) return -1;
        if (InforPanel.STATUS == InforPanel.ADDING) {
            if (ShowDialog.confirmAddStudent(st, student[2])) {
                try {
                    if (!studentBLL.add(st)) {
                        ShowDialog.studenIdExists();
                        return -1;
                    }
                    ShowDialog.added();
                    return 1;
                } catch (ConnectException e) {
                    ShowDialog.cannotConnectDB();
                }
            }
        } else if (InforPanel.STATUS == InforPanel.EDITING) {
            try {
                if (!studentBLL.update(st)) {
                    ShowDialog.cannotConnectDB();
                    return -1;
                }
                inforPanel.updateRollBack();
                ShowDialog.updated();
                return 1;
            } catch (ConnectException e) {
                ShowDialog.cannotConnectDB();
            }
        }
        return -1;
    }

    private static boolean checkStudent(String[] student, Student st) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int cityId = cityBLL.getIdByName(student[2]);
        if (cityId < 0) {
            ShowDialog.cityNotExists();
            return false;
        }
        List<String> listError = new ArrayList<String>();
        int studentId = -1;
        Date birth = new Date();
        float math = -1, physical = -1, chemistry = -1;
        try {
            studentId = Integer.parseInt(student[0]);
        } catch (NumberFormatException nex) {
            listError.add(Env.envVar.get("MESS_STUDENID_IS_INT"));
        }
        //start check name
        int doDai = student[1].length();
        for (int i = 0; i < doDai; i++) {
            if (!Character.isLetter(student[1].charAt(i)) && (student[1].charAt(i) != ' ')) {
                listError.add(Env.envVar.get("MESS_NAME_INVALID"));
                break;
            }
        }
        //end check name
        try {
            birth = sdf.parse(student[3]);
        } catch (ParseException e) {
            listError.add(Env.envVar.get("MESS_BIRTH_INVALID"));
        }
        try {
            math = Float.parseFloat(student[5]);
            if (math < 0.0f || math > 10.0f) {
                System.out.println(math);
                listError.add(Env.envVar.get("MESS_MATH_INVALID"));
            }
        } catch (NumberFormatException e) {
            listError.add(Env.envVar.get("MESS_MATH_INVALID"));
        }
        try {
            physical = Float.parseFloat(student[6]);
            if (physical < 0.0f || physical > 10.0f) {
                listError.add(Env.envVar.get("MESS_PHYSICAL_INVALID"));
            }
        } catch (NumberFormatException e) {
            listError.add(Env.envVar.get("MESS_PHYSICAL_INVALID"));
        }
        try {
            chemistry = Float.parseFloat(student[7]);
            if (chemistry < 0.0f || chemistry > 10.0f) {
                listError.add(Env.envVar.get("MESS_CHEMISTRY_INVALID"));
            }
        } catch (NumberFormatException e) {
            listError.add(Env.envVar.get("MESS_CHEMISTRY_INVALID"));
        }
        if (listError.size() > 0) {
            StringBuilder errorMess = new StringBuilder();
            for (String s : listError) {
                errorMess.append(s).append("\n");
            }
            JOptionPane.showMessageDialog(null, errorMess.toString(), Env.envVar.get("TIT_ERROR"), JOptionPane.ERROR_MESSAGE);
            return false;
        }
        //Ä‘áº¿n Ä‘Ã¢y lÃ  má»�i thá»© Ä‘á»�u Ä‘Ãºng cáº£ rá»“i
        st.setStudentId(studentId);
        st.setStudentName(student[1]);
        st.setCityId(cityId);
        st.setBirth(birth);
        st.setGender(student[4].equalsIgnoreCase(Env.envVar.get("STR_STUDENT_MALE")));
        st.setMath(math);
        st.setPhysical(physical);
        st.setChemistry(chemistry);
        return true;

    }

    private void addEvents() {
        mainScreenMenuBar.itemRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                initData();
            }
        });
        listPanel.btnPrev.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String[] data = filterPanel.getData();
                if (FilterPanel.STATUS == FilterPanel.FILTERING) {
                    filterHandle(data[0], data[1], --ListPanel.CUR_PAGE, ListPanel.NUMBER_PERPAGE, false);
                } else {
                    try {
                        studentBLL.readData(--ListPanel.CUR_PAGE, ListPanel.NUMBER_PERPAGE);
                        listPanel.initDataTable(studentBLL.getMapStudent(), cityBLL.getMapCity());
                        listPanel.updateNextPrev();
                    } catch (ConnectException ex) {
                        ShowDialog.cannotConnectDB();
                    }
                }
            }
        });
        listPanel.btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (FilterPanel.STATUS == FilterPanel.FILTERING) {
                    String[] data = filterPanel.getData();
                    filterHandle(data[0], data[1], ++ListPanel.CUR_PAGE, ListPanel.NUMBER_PERPAGE, false);
                } else {
                    try {
                        studentBLL.readData(++ListPanel.CUR_PAGE, ListPanel.NUMBER_PERPAGE);
                        listPanel.initDataTable(studentBLL.getMapStudent(), cityBLL.getMapCity());
                        listPanel.updateNextPrev();
                    } catch (ConnectException ex) {
                        ShowDialog.cannotConnectDB();
                    }
                }
            }
        });
        listPanel.txtPageNumber.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (listPanel.btnGo.isEnabled()) {
                        goToPage();
                    }

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

                super.keyReleased(e);
                if (e.getKeyCode() != KeyEvent.VK_ENTER) {
                    try {
                        String textPage = listPanel.txtPageNumber.getText();
                        int numPage = 0;
                        if (textPage.length() > 0) {
                            numPage = Integer.parseInt(textPage);
                            if (numPage > ListPanel.TOTAL_PAGE || numPage < 1) {
                                listPanel.btnGo.setEnabled(false);
                            } else {
                                listPanel.btnGo.setEnabled(true);
                            }
                        } else {
                            listPanel.btnGo.setEnabled(false);
                        }
//                        System.out.println(numPage + " " + ListPanel.TOTAL_PAGE);
                    } catch (NumberFormatException ex) {
                        listPanel.btnGo.setEnabled(false);
                    }
                }
            }
        });
        listPanel.btnGo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goToPage();
            }
        });

    }

    private void goToPage() {
        try {
            int pageNum = Integer.parseInt(listPanel.txtPageNumber.getText());
            if (pageNum <= ListPanel.TOTAL_PAGE) {
                if (FilterPanel.STATUS == FilterPanel.FILTERING) {
                    String[] data = filterPanel.getData();
                    ListPanel.CUR_PAGE = pageNum;
                    filterHandle(data[0], data[1], ListPanel.CUR_PAGE, ListPanel.NUMBER_PERPAGE, false);
                } else {
                    try {
                        ListPanel.CUR_PAGE = pageNum;
                        studentBLL.readData(ListPanel.CUR_PAGE, ListPanel.NUMBER_PERPAGE);
                        listPanel.initDataTable(studentBLL.getMapStudent(), cityBLL.getMapCity());
                        listPanel.updateNextPrev();
                    } catch (ConnectException ex) {
                        ShowDialog.cannotConnectDB();
                    }
                }
            }
        } catch (NumberFormatException ex) {
            ShowDialog.pageNumberIsInteger();
        }
    }

    private void initMainPanel() {
        gb = new GridBagLayout();
        gbc = new GridBagConstraints();
        this.setLayout(gb);

        mainScreenMenuBar = new MainScreenMenuBar();
        filterPanel = new FilterPanel();
        listPanel = new ListPanel();
        inforPanel = new InforPanel();

        GridBagBuilder.addComponent(gb, gbc, this, filterPanel, 0, 0, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, this, listPanel, 1, 0, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, this, inforPanel, 2, 0, 0, 0);

    }

    private void initData() {
        try {
            studentBLL = new StudentBLL();
            cityBLL = new CityBLL();
            studentBLL.readData(1, ListPanel.NUMBER_PERPAGE);
            cityBLL.readData();
            listPanel.initDataTable(studentBLL.getMapStudent(), cityBLL.getMapCity());
            this.setEnabled(true);
            listPanel.updateNextPrev();
            listPanel.btnGo.setEnabled(false);
            executeCommand(Commands.CANCEL); //báº¥m nÃºt Cancel Ä‘á»ƒ Ä‘Æ°a inforPanel vá»� tráº¡ng thÃ¡i ban Ä‘áº§u
        } catch (ConnectException e) {
            ShowDialog.cannotConnectDB();
            this.setEnabled(false); //náº¿u lá»—i káº¿t ná»‘i csdl thÃ¬ vÃ´ hiá»‡u hÃ³a frame
        }
    }
}
