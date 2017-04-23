package PL.MainScreen.InforAndControl;

import DAL.City;
import DAL.Env;
import DAL.Student;
import PL.Tools.CreateComponent;
import PL.Tools.CustomPanel;
import PL.Tools.GridBagBuilder;
import PL.Tools.RoundedBorder;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Nguyen Dang Dung on 4/4/2017 3:19 PM
 * Project: BaiTapLonJava
 */
public class DetailPanel extends CustomPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String STR_STUDENT_ID = Env.envVar.get("STR_STUDENT_ID");
    private String STR_STUDENT_NAME = Env.envVar.get("STR_STUDENT_NAME");
    private String STR_STUDENT_CITY = Env.envVar.get("STR_STUDENT_CITY");
    private String STR_STUDENT_BIRTH = Env.envVar.get("STR_STUDENT_BIRTH");
    private String STR_STUDENT_GENDER = Env.envVar.get("STR_STUDENT_GENDER");
    private String STR_STUDENT_MALE = Env.envVar.get("STR_STUDENT_MALE");
    private String STR_STUDENT_FEMALE = Env.envVar.get("STR_STUDENT_FEMALE");
    private String STR_STUDENT_MATH = Env.envVar.get("STR_STUDENT_MATH");
    private String STR_STUDENT_PHYSICAL = Env.envVar.get("STR_STUDENT_PHYSICAL");
    private String STR_STUDENT_CHEMISTRY = Env.envVar.get("STR_STUDENT_CHEMISTRY");
    private String STR_STUDENT_SUM = Env.envVar.get("STR_STUDENT_SUM");

    private JLabel lblStudentId, lblName, lblCity, lblBirth, lblGender;
    private JLabel lblMath, lblPhysical, lblChemistry, lblSum;
    private JTextField txtStudentId, txtName, txtMath, txtPhysical, txtChemistry, txtSum;
    private JDateChooser txtBirth;
    private JComboBox<String> cbCity;
    private ButtonGroup groupGender;
    private JRadioButton radMale, radFemale;
    private GridBagLayout gb;
    private GridBagConstraints gbc;
    private Student preStudent;
    private String preCity;

    private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    private DecimalFormat decimalFormat = new DecimalFormat("#.#");

    public DetailPanel() {
        super();
        initDetailPanel();
    }

    private void initDetailPanel() {
        //setLayout
        gb = new GridBagLayout();
        gbc = new GridBagConstraints();
        this.setLayout(gb);

        //createComponent
        lblStudentId = CreateComponent.createJLabel(STR_STUDENT_ID, 80, 30, Font.BOLD, 14, JLabel.LEFT);
        lblName = CreateComponent.createJLabel(STR_STUDENT_NAME, 80, 30, Font.BOLD, 14, JLabel.LEFT);
        lblCity = CreateComponent.createJLabel(STR_STUDENT_CITY, 80, 30, Font.BOLD, 14, JLabel.LEFT);
        lblBirth = CreateComponent.createJLabel(STR_STUDENT_BIRTH, 80, 30, Font.BOLD, 14, JLabel.LEFT);
        lblGender = CreateComponent.createJLabel(STR_STUDENT_GENDER, 80, 30, Font.BOLD, 14, JLabel.LEFT);
        lblMath = CreateComponent.createJLabel(STR_STUDENT_MATH, 80, 30, Font.BOLD, 14, JLabel.LEFT);
        lblPhysical = CreateComponent.createJLabel(STR_STUDENT_PHYSICAL, 80, 30, Font.BOLD, 14, JLabel.LEFT);
        lblChemistry = CreateComponent.createJLabel(STR_STUDENT_CHEMISTRY, 80, 30, Font.BOLD, 14, JLabel.LEFT);
        lblSum = CreateComponent.createJLabel(STR_STUDENT_SUM, 80, 30, Font.BOLD, 14, JLabel.LEFT);

        txtStudentId = CreateComponent.createJTextField("", 200, 30, Font.PLAIN, 14, JTextField.LEFT);
        txtName = CreateComponent.createJTextField("", 200, 30, Font.PLAIN, 14, JTextField.LEFT);
        txtBirth = CreateComponent.createJDateChooser("dd/MM/yyyy", 200, 30, Font.PLAIN, 14);
        txtMath = CreateComponent.createJTextField("", 110, 30, Font.PLAIN, 14, JTextField.LEFT);
        txtPhysical = CreateComponent.createJTextField("", 110, 30, Font.PLAIN, 14, JTextField.LEFT);
        txtChemistry = CreateComponent.createJTextField("", 110, 30, Font.PLAIN, 14, JTextField.LEFT);
        txtSum = CreateComponent.createJTextField("", 110, 30, Font.PLAIN, 14, JTextField.LEFT);

        cbCity = new JComboBox<String>();
        cbCity.setPreferredSize(new Dimension(200, 30));
        cbCity.setFont(new Font(null, Font.PLAIN, 14));
        cbCity.setBorder(new RoundedBorder(3));

        groupGender = new ButtonGroup();
        radMale = new JRadioButton(STR_STUDENT_MALE);
        radFemale = new JRadioButton(STR_STUDENT_FEMALE);
        groupGender.add(radMale);
        groupGender.add(radFemale);

        //addComponent
        //col 0
        gbc.insets = new Insets(0, 0, 4, 0);
        GridBagBuilder.addComponent(gb, gbc, this, lblStudentId, 0, 0, 50, 0);
        GridBagBuilder.addComponent(gb, gbc, this, lblName, 1, 0, 50, 0);
        GridBagBuilder.addComponent(gb, gbc, this, lblCity, 2, 0, 50, 0);
        GridBagBuilder.addComponent(gb, gbc, this, lblBirth, 3, 0, 50, 0);
        GridBagBuilder.addComponent(gb, gbc, this, lblGender, 4, 0, 50, 0);

        //col 1
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 4, 40);
        GridBagBuilder.addComponent(gb, gbc, this, txtStudentId, 0, 1, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, this, txtName, 1, 1, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, this, cbCity, 2, 1, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, this, txtBirth, 3, 1, 0, 0);
        gbc.gridwidth = 1;
        GridBagBuilder.addComponent(gb, gbc, this, radMale, 4, 1, 10, 0);
        GridBagBuilder.addComponent(gb, gbc, this, radFemale, 4, 2, 10, 0);

        //col 2
        gbc.insets = new Insets(0, 0, 4, 0);
        GridBagBuilder.addComponent(gb, gbc, this, lblMath, 0, 3, 50, 0);
        GridBagBuilder.addComponent(gb, gbc, this, lblPhysical, 1, 3, 50, 0);
        GridBagBuilder.addComponent(gb, gbc, this, lblChemistry, 2, 3, 50, 0);
        GridBagBuilder.addComponent(gb, gbc, this, lblSum, 3, 3, 50, 0);

        //col 3
        GridBagBuilder.addComponent(gb, gbc, this, txtMath, 0, 4, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, this, txtPhysical, 1, 4, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, this, txtChemistry, 2, 4, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, this, txtSum, 3, 4, 0, 0);

        //initStatus
        preStudent = new Student();
        setEditable(false);
        ((JTextField) txtBirth.getDateEditor().getUiComponent()).setEditable(false);
        cbCity.setEditable(false);
        txtSum.setEditable(false);

    }

    public void showDetail(String[] student) {
        txtStudentId.setText(student[0]);
        preStudent.setStudentId(Integer.parseInt(student[0]));

        txtName.setText(student[1]);
        preStudent.setStudentName(student[1]);

        cbCity.removeAllItems();
        cbCity.addItem(student[2]);
        preCity = student[2];

        try {
            Date curDate = format.parse(student[3]);
            txtBirth.setDate(curDate);
            preStudent.setBirth(curDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        preStudent.setGender(false);
        if (student[4].equalsIgnoreCase(STR_STUDENT_MALE)) {
            preStudent.setGender(true);
            radMale.setSelected(true);
        } else {
            radFemale.setSelected(true);
        }

        txtMath.setText(student[5]);
        preStudent.setMath(Float.parseFloat(student[5]));

        txtPhysical.setText(student[6]);
        preStudent.setPhysical(Float.parseFloat(student[6]));

        txtChemistry.setText(student[7]);
        preStudent.setChemistry(Float.parseFloat(student[7]));

        float sum = preStudent.getMath() + preStudent.getPhysical() + preStudent.getChemistry();
        txtSum.setText(String.valueOf(decimalFormat.format(sum)));

    }

    public void hideDetail() {
        txtStudentId.setText("");
        txtName.setText("");
        ((JTextField) txtBirth.getDateEditor().getUiComponent()).setText("");
        txtMath.setText("");
        txtPhysical.setText("");
        txtChemistry.setText("");
        txtSum.setText("");

        cbCity.removeAllItems();

        groupGender.clearSelection();
    }

    public void setEditable(boolean aFlag) {
        if (InforPanel.STATUS == InforPanel.EDITING) {
            txtStudentId.setEditable(false);
        } else {
            txtStudentId.setEditable(aFlag);
        }
        txtName.setEditable(aFlag);
        txtMath.setEditable(aFlag);
        txtPhysical.setEditable(aFlag);
        txtChemistry.setEditable(aFlag);

        radFemale.setEnabled(aFlag);
        radMale.setEnabled(aFlag);
        radMale.setEnabled(aFlag);

        txtSum.setVisible(!aFlag);
        lblSum.setVisible(!aFlag);
    }

    public String[] getData() {
        String[] student = new String[8];
        student[0] = txtStudentId.getText();
        student[1] = txtName.getText();
        student[2] = (String) cbCity.getSelectedItem();
        student[3] = format.format(txtBirth.getDate());
        student[4] = radMale.isSelected() ? "Male" : "Female";
        student[5] = txtMath.getText();
        student[6] = txtPhysical.getText();
        student[7] = txtChemistry.getText();
        return student;
    }

    public void addCityData(HashMap<Integer, City> mapCity) {
        String pCity = (String) cbCity.getSelectedItem();
        cbCity.removeAllItems();
        for (City city : mapCity.values()) {
            cbCity.addItem(city.getCityName());
        }
        if (pCity != null) {
            cbCity.setSelectedItem(pCity);
        } else cbCity.setSelectedIndex(0);
        if (InforPanel.STATUS == InforPanel.ADDING) {
            ((JTextField) txtBirth.getDateEditor().getUiComponent()).setText("11/11/1997");
            radMale.setSelected(true);
        }

    }

    public void rollBackData() {
        if (InforPanel.STATUS == InforPanel.FREE || InforPanel.STATUS != InforPanel.EDITING) return;
        txtStudentId.setText(String.valueOf(preStudent.getStudentId()));
        txtName.setText(preStudent.getStudentName());
        radMale.setSelected(preStudent.isGender());
        txtMath.setText(String.valueOf(preStudent.getMath()));
        txtPhysical.setText(String.valueOf(preStudent.getPhysical()));
        txtChemistry.setText(String.valueOf(preStudent.getChemistry()));
        cbCity.removeAllItems();
        cbCity.addItem(preCity);
        txtBirth.setDate(preStudent.getBirth());
    }

    public void updateRollBack() {
        preStudent.setStudentId(Integer.parseInt(txtStudentId.getText()));

        preStudent.setStudentName(txtName.getText());

        preCity = (String) cbCity.getSelectedItem();

        preStudent.setBirth(txtBirth.getDate());

        preStudent.setGender(false);
        if (radMale.isSelected()) {
            preStudent.setGender(true);
        }

        preStudent.setMath(Float.parseFloat(txtMath.getText()));

        preStudent.setPhysical(Float.parseFloat(txtPhysical.getText()));

        preStudent.setChemistry(Float.parseFloat(txtChemistry.getText()));

        updateSum();
    }

    public void updateSum() {
        float math = Float.parseFloat(txtMath.getText());
        float physical = Float.parseFloat(txtPhysical.getText());
        float chemistry = Float.parseFloat(txtChemistry.getText());
        txtSum.setText(String.valueOf(decimalFormat.format(math + physical + chemistry)));
    }
}
