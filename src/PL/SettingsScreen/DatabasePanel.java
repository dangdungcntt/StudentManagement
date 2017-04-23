package PL.SettingsScreen;

import DAL.Database;
import DAL.Env;
import PL.Tools.CreateComponent;
import PL.Tools.GridBagBuilder;
import PL.Tools.ShowDialog;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Created by Nguyen Dang Dung on 4/21/2017 4:23 PM
 * Project: BTLJavaDangDung
 */
public class DatabasePanel extends JPanel implements MenuSettingsPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ButtonGroup group;
    public JRadioButton radMysql, radSqlserver;
    public JTextField txtHostM, txtPortM, txtNameM, txtUsernameM, txtPasswordM;
    public JTextField txtHostS, txtPortS, txtInstance, txtNameS, txtUsernameS, txtPasswordS;
    public JButton btnTestConnectM, btnTestConnectS;
    public JLabel lblCheckMysql;
    public JLabel lblCheckSqlserver;
    public boolean isSuccessM;
    public boolean isSuccessS;
    //    public JTextField txtStudentTable;
//    public JTextField txtStudentId, txtStudentName, txtStudentCity, txtStudentBirth;
//    public JTextField txtStudentGender, txtStudentMath, txtStudentPhysical, txtStudentChemistry;
//
//    public JTextField txtCityTable;
//    public JTextField txtCityId, txtCityName, txtCityNameTxt;
//
//    JPanel studentTable;
//    JPanel cityTable;
    JPanel mysqlPanel;
    JPanel sqlserverPanel;
    private HashMap<String, String> mapDbMysql, mapDbSqlServer;
    private GridBagLayout gb;
    private GridBagConstraints gbc;

    public DatabasePanel() {
        super();
        init();
        initData();
        addEvents();
    }

    private void addEvents() {
        btnTestConnectM.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                testConnectMySql();
            }
        });
        btnTestConnectS.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                testConnectSqlServer();
            }
        });
        radMysql.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (radMysql.isSelected()) {
                    setEditable(mysqlPanel, true);
                    setEditable(sqlserverPanel, false);
                } else {
                    setEditable(sqlserverPanel, true);
                    setEditable(mysqlPanel, false);
                }
            }
        });
    }

    private void initData() {
        mapDbMysql = new HashMap<String, String>();
        readFile("MysqlConfig.xyz");
        txtHostM.setText(mapDbMysql.get("DB_HOST"));
        txtPortM.setText(mapDbMysql.get("DB_PORT"));
        txtNameM.setText(mapDbMysql.get("DB_NAME"));
        txtUsernameM.setText(mapDbMysql.get("DB_USERNAME"));
        txtPasswordM.setText(mapDbMysql.get("DB_PASSWORD"));

        mapDbSqlServer = new HashMap<String, String>();
        readFile("SqlserverConfig.xyz");
        txtHostS.setText(mapDbSqlServer.get("DB_HOST"));
        txtPortS.setText(mapDbSqlServer.get("DB_PORT"));
        txtInstance.setText(mapDbSqlServer.get("DB_INSTANCE"));
        txtNameS.setText(mapDbSqlServer.get("DB_NAME"));
        txtUsernameS.setText(mapDbSqlServer.get("DB_USERNAME"));
        txtPasswordS.setText(mapDbSqlServer.get("DB_PASSWORD"));

//        testConnectMySql();
//        testConnectSqlServer();
        if (Env.envVar.get("TYPE_DB").equalsIgnoreCase("mysql")) {
            radMysql.setSelected(true);
            setEditable(sqlserverPanel, false);
        } else {
            radSqlserver.setSelected(true);
            setEditable(mysqlPanel, false);

        }

    }

    private void testConnectMySql() {
        String host = txtHostM.getText() + "";
        String port = txtPortM.getText() + "";
        String dbName = txtNameM.getText() + "";
        String user = txtUsernameM.getText() + "";
        String pass = txtPasswordM.getText() + "";
        if (Database.MySql(host, port, dbName, user, pass)) {
            isSuccessM = true;
            lblCheckMysql.setIcon(new ImageIcon(this.getClass().getResource(Env.envVar.get("IMG_SUCCESS"))));
        } else {
            isSuccessM = false;
            lblCheckMysql.setIcon(new ImageIcon(this.getClass().getResource(Env.envVar.get("IMG_FAIL"))));
        }
    }

    private void testConnectSqlServer() {
        String host = txtHostS.getText() + "";
        String port = txtPortS.getText() + "";
        String instance = txtInstance.getText() + "";
        String dbName = txtNameS.getText() + "";
        String user = txtUsernameS.getText() + "";
        String pass = txtPasswordS.getText() + "";
        if (Database.SqlServer(host, port, instance, dbName, user, pass)) {
            isSuccessS = true;
            lblCheckSqlserver.setIcon(new ImageIcon(this.getClass().getResource(Env.envVar.get("IMG_SUCCESS"))));
        } else {
            isSuccessS = false;
            lblCheckSqlserver.setIcon(new ImageIcon(this.getClass().getResource(Env.envVar.get("IMG_FAIL"))));
        }
    }

    private void init() {
        gb = new GridBagLayout();
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 5, 5, 5);
        this.setLayout(gb);

        radMysql = new JRadioButton("MY SQL");
        radSqlserver = new JRadioButton("SQL SERVER");

        group = new ButtonGroup();
        group.add(radMysql);
        group.add(radSqlserver);
        lblCheckMysql = new JLabel();
        lblCheckMysql.setIcon(new ImageIcon(this.getClass().getResource(Env.envVar.get("IMG_FAIL"))));

        txtHostM = CreateComponent.createJTextField("", 100, 30, Font.PLAIN, 14, JTextField.LEFT);
        txtPortM = CreateComponent.createJTextField("", 100, 30, Font.PLAIN, 14, JTextField.LEFT);
        txtNameM = CreateComponent.createJTextField("", 100, 30, Font.PLAIN, 14, JTextField.LEFT);
        txtUsernameM = CreateComponent.createJTextField("", 100, 30, Font.PLAIN, 14, JTextField.LEFT);
        txtPasswordM = CreateComponent.createJTextField("", 100, 30, Font.PLAIN, 14, JTextField.LEFT);
        btnTestConnectM = CreateComponent.createJButton(Env.envVar.get("STR_TEST_CONNECT"), 100, 30, Font.PLAIN, 14, JButton.CENTER);

        lblCheckSqlserver = new JLabel();
        lblCheckSqlserver.setIcon(new ImageIcon(this.getClass().getResource(Env.envVar.get("IMG_FAIL"))));
        txtHostS = CreateComponent.createJTextField("", 100, 30, Font.PLAIN, 14, JTextField.LEFT);
        txtPortS = CreateComponent.createJTextField("", 100, 30, Font.PLAIN, 14, JTextField.LEFT);
        txtInstance = CreateComponent.createJTextField("", 100, 30, Font.PLAIN, 14, JTextField.LEFT);
        txtNameS = CreateComponent.createJTextField("", 100, 30, Font.PLAIN, 14, JTextField.LEFT);
        txtUsernameS = CreateComponent.createJTextField("", 100, 30, Font.PLAIN, 14, JTextField.LEFT);
        txtPasswordS = CreateComponent.createJTextField("", 100, 30, Font.PLAIN, 14, JTextField.LEFT);
        btnTestConnectS = CreateComponent.createJButton(Env.envVar.get("STR_TEST_CONNECT"), 100, 30, Font.PLAIN, 14, JButton.CENTER);


//        txtStudentTable = CreateComponent.createJTextField("", 100, 30, Font.PLAIN, 12, JTextField.LEFT);
//        txtStudentId = CreateComponent.createJTextField("", 60, 30, Font.PLAIN, 12, JTextField.LEFT);
//        txtStudentName = CreateComponent.createJTextField("", 60, 30, Font.PLAIN, 12, JTextField.LEFT);
//        txtStudentCity = CreateComponent.createJTextField("", 60, 30, Font.PLAIN, 12, JTextField.LEFT);
//        txtStudentBirth = CreateComponent.createJTextField("", 60, 30, Font.PLAIN, 12, JTextField.LEFT);
//        txtStudentGender = CreateComponent.createJTextField("", 60, 30, Font.PLAIN, 12, JTextField.LEFT);
//        txtStudentMath = CreateComponent.createJTextField("", 60, 30, Font.PLAIN, 12, JTextField.LEFT);
//        txtStudentPhysical = CreateComponent.createJTextField("", 60, 30, Font.PLAIN, 12, JTextField.LEFT);
//        txtStudentChemistry = CreateComponent.createJTextField("", 60, 30, Font.PLAIN, 12, JTextField.LEFT);
//
//        txtCityTable = CreateComponent.createJTextField("", 100, 30, Font.PLAIN, 14, JTextField.LEFT);
//        txtCityId = CreateComponent.createJTextField("", 100, 30, Font.PLAIN, 14, JTextField.LEFT);
//        txtCityName = CreateComponent.createJTextField("", 100, 30, Font.PLAIN, 14, JTextField.LEFT);
//        txtCityNameTxt = CreateComponent.createJTextField("", 100, 30, Font.PLAIN, 14, JTextField.LEFT);

        mysqlPanel = new JPanel();
        mysqlPanel.setLayout(gb);
        GridBagBuilder.addComponent(gb, gbc, mysqlPanel, radMysql, 0, 0, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, mysqlPanel, lblCheckMysql, 0, 1, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, mysqlPanel, CreateComponent.createJLabel("Host", JLabel.LEFT), 1, 0, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, mysqlPanel, CreateComponent.createJLabel("Port", JLabel.LEFT), 2, 0, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, mysqlPanel, CreateComponent.createJLabel("Database name", JLabel.LEFT), 3, 0, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, mysqlPanel, CreateComponent.createJLabel("Username", JLabel.LEFT), 4, 0, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, mysqlPanel, CreateComponent.createJLabel("Password", JLabel.LEFT), 5, 0, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, mysqlPanel, txtHostM, 1, 1, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, mysqlPanel, txtPortM, 2, 1, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, mysqlPanel, txtNameM, 3, 1, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, mysqlPanel, txtUsernameM, 4, 1, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, mysqlPanel, txtPasswordM, 5, 1, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, mysqlPanel, CreateComponent.createJLabel("", 10, 30, 0), 6, 0, 0, 0);
        gbc.gridwidth = 2;
        GridBagBuilder.addComponent(gb, gbc, mysqlPanel, btnTestConnectM, 7, 0, 0, 0);
        gbc.gridwidth = 1;
        mysqlPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.GRAY));

        sqlserverPanel = new JPanel();
        sqlserverPanel.setLayout(gb);
        GridBagBuilder.addComponent(gb, gbc, sqlserverPanel, radSqlserver, 0, 0, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, sqlserverPanel, lblCheckSqlserver, 0, 1, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, sqlserverPanel, CreateComponent.createJLabel("Host", JLabel.LEFT), 1, 0, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, sqlserverPanel, CreateComponent.createJLabel("Port", JLabel.LEFT), 2, 0, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, sqlserverPanel, CreateComponent.createJLabel("Instance", JLabel.LEFT), 3, 0, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, sqlserverPanel, CreateComponent.createJLabel("Database name", JLabel.LEFT), 4, 0, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, sqlserverPanel, CreateComponent.createJLabel("Username", JLabel.LEFT), 5, 0, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, sqlserverPanel, CreateComponent.createJLabel("Password", JLabel.LEFT), 6, 0, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, sqlserverPanel, txtHostS, 1, 1, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, sqlserverPanel, txtPortS, 2, 1, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, sqlserverPanel, txtInstance, 3, 1, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, sqlserverPanel, txtNameS, 4, 1, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, sqlserverPanel, txtUsernameS, 5, 1, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, sqlserverPanel, txtPasswordS, 6, 1, 0, 0);
        gbc.gridwidth = 2;
        GridBagBuilder.addComponent(gb, gbc, sqlserverPanel, btnTestConnectS, 7, 0, 0, 0);
        gbc.gridwidth = 1;
//        studentTable = new JPanel();
//        studentTable.setLayout(gb);
//        gbc.insets = new Insets(0, 2, 5, 2);
//        gbc.gridwidth = 2;
//        GridBagBuilder.addComponent(gb, gbc, studentTable, new JLabel("Table name"), 0, 0, 0, 0);
//        GridBagBuilder.addComponent(gb, gbc, studentTable, txtStudentTable, 0, 2, 0, 0);
//        gbc.gridwidth = 1;
//        GridBagBuilder.addComponent(gb, gbc, studentTable, new JLabel("ID"), 1, 0, 0, 0);
//        GridBagBuilder.addComponent(gb, gbc, studentTable, new JLabel("Name"), 1, 1, 0, 0);
//        GridBagBuilder.addComponent(gb, gbc, studentTable, new JLabel("City ID"), 1, 2, 0, 0);
//        GridBagBuilder.addComponent(gb, gbc, studentTable, new JLabel("Birth"), 1, 3, 0, 0);
//        GridBagBuilder.addComponent(gb, gbc, studentTable, new JLabel("Gender"), 1, 4, 0, 0);
//        GridBagBuilder.addComponent(gb, gbc, studentTable, new JLabel("Math"), 1, 5, 0, 0);
//        GridBagBuilder.addComponent(gb, gbc, studentTable, new JLabel("Physical"), 1, 6, 0, 0);
//        GridBagBuilder.addComponent(gb, gbc, studentTable, new JLabel("Chemistry"), 1, 7, 0, 0);
//
//        GridBagBuilder.addComponent(gb, gbc, studentTable, txtStudentId, 2, 0, 0, 0);
//        GridBagBuilder.addComponent(gb, gbc, studentTable, txtStudentName, 2, 1, 0, 0);
//        GridBagBuilder.addComponent(gb, gbc, studentTable, txtStudentCity, 2, 2, 0, 0);
//        GridBagBuilder.addComponent(gb, gbc, studentTable, txtStudentBirth, 2, 3, 0, 0);
//        GridBagBuilder.addComponent(gb, gbc, studentTable, txtStudentGender, 2, 4, 0, 0);
//        GridBagBuilder.addComponent(gb, gbc, studentTable, txtStudentMath, 2, 5, 0, 0);
//        GridBagBuilder.addComponent(gb, gbc, studentTable, txtStudentPhysical, 2, 6, 0, 0);
//        GridBagBuilder.addComponent(gb, gbc, studentTable, txtStudentChemistry, 2, 7, 0, 0);
//        studentTable.setBorder(BorderFactory.createTitledBorder("Student table"));
//
//
//        cityTable = new JPanel();
//        cityTable.setLayout(gb);
//        GridBagBuilder.addComponent(gb, gbc, cityTable, new JLabel("Table name"), 0, 0, 0, 0);
//        GridBagBuilder.addComponent(gb, gbc, cityTable, txtCityTable, 0, 1, 0, 0);
//        GridBagBuilder.addComponent(gb, gbc, cityTable, new JLabel("City ID"), 1, 0, 0, 0);
//        GridBagBuilder.addComponent(gb, gbc, cityTable, new JLabel("City Name"), 1, 1, 0, 0);
//        GridBagBuilder.addComponent(gb, gbc, cityTable, new JLabel("Without accent marks"), 1, 2, 0, 0);
//
//        gbc.insets = new Insets(0, 5, 5, 12);
//        GridBagBuilder.addComponent(gb, gbc, cityTable, txtCityId, 2, 0, 10, 0);
//        GridBagBuilder.addComponent(gb, gbc, cityTable, txtCityName, 2, 1, 10, 0);
//        GridBagBuilder.addComponent(gb, gbc, cityTable, txtCityNameTxt, 2, 2, 10, 0);
//        GridBagBuilder.addComponent(gb, gbc, cityTable, CreateComponent.createJLabel("", 110, 30, 0), 2, 3, 0, 0);
//        cityTable.setBorder(BorderFactory.createTitledBorder("City table"));

        gbc.insets = new Insets(0, 5, 2, 5);
        GridBagBuilder.addComponent(gb, gbc, this, mysqlPanel, 0, 0, 0, 0);
        GridBagBuilder.addComponent(gb, gbc, this, sqlserverPanel, 0, 1, 0, 0);
        JLabel note = new JLabel(Env.envVar.get("STR_NOTE"));
        note.setFont(new Font(null, Font.ITALIC, 14));
        note.setForeground(Color.RED);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        GridBagBuilder.addComponent(gb, gbc, this, note, 1, 0, 0, 0);

//        gbc.gridwidth = 2;
//        GridBagBuilder.addComponent(gb, gbc, this, studentTable, 1, 0, 0, 0);
//        GridBagBuilder.addComponent(gb, gbc, this, cityTable, 2, 0, 0, 0);
//        gbc.gridwidth = 1;


    }

    private void setEditable(Container component, boolean aFlag) {
        for (Component c : component.getComponents()) {
            try {
                ((JTextField) c).setEditable(aFlag);
            } catch (ClassCastException e) {
                try {
                    ((JButton) c).setEnabled(aFlag);
                } catch (ClassCastException ex) {

                }
            }
        }
    }

    private void readFile(String fileName) {
        try {
            URL fileUrl = this.getClass().getResource("/config/" + fileName);
            InputStream isr = fileUrl.openStream();
            BufferedReader bfr = new BufferedReader(new InputStreamReader(isr));
            String line = bfr.readLine();
            if (fileName.equalsIgnoreCase("MysqlConfig.xyz")) {
                while (line != null) {
                    String[] res = line.split(":");
                    if (res.length < 2) {
                        mapDbMysql.put(res[0], "");
                    } else {
                        mapDbMysql.put(res[0], res[1]);
                    }

                    line = bfr.readLine();
                }
            } else {
                while (line != null) {
                    String[] res = line.split(":");
                    mapDbSqlServer.put(res[0], res[1]);
                    line = bfr.readLine();
                }
            }
            bfr.close();
            isr.close();
        } catch (FileNotFoundException e) {
            ShowDialog.fileConfigNotFound(fileName);
        } catch (IOException e) {
            ShowDialog.cannotOpenFileConfig(fileName);
        }
    }

    public void getData() {
        testConnectMySql();
        testConnectSqlServer();
        if (isSuccessM) {
            mapDbMysql.replace("DB_HOST", txtHostM.getText().trim());
            mapDbMysql.replace("DB_PORT", txtPortM.getText().trim());
            mapDbMysql.replace("DB_NAME", txtNameM.getText().trim());
            mapDbMysql.replace("DB_USERNAME", txtUsernameM.getText().trim());
            mapDbMysql.replace("DB_PASSWORD", txtPasswordM.getText().trim());
        }
        if (isSuccessS) {
            mapDbSqlServer.replace("DB_HOST", txtHostS.getText().trim());
            mapDbSqlServer.replace("DB_PORT", txtPortS.getText().trim());
            mapDbSqlServer.replace("DB_INSTANCE", txtInstance.getText().trim());
            mapDbSqlServer.replace("DB_NAME", txtNameS.getText().trim());
            mapDbSqlServer.replace("DB_USERNAME", txtUsernameS.getText().trim());
            mapDbSqlServer.replace("DB_PASSWORD", txtPasswordS.getText().trim());
        }
    }

    public boolean saveData() {
        HashMap<String, String> mapDbConfig = new HashMap<String, String>();
        mapDbConfig.put("TYPE_DB", radMysql.isSelected() ? "mysql" : "sqlserver");
        saveFile("DbConfig.xyz", mapDbConfig);
        boolean resM = !isSuccessM || saveFile("MysqlConfig.xyz", mapDbMysql);
        boolean resS = !isSuccessS || saveFile("SqlserverConfig.xyz", mapDbSqlServer);
        return resM && resS;
    }

    private boolean saveFile(String fileName, HashMap<String, String> map) {
        try {
            URL fileUrl = this.getClass().getResource("/config/" + fileName);
            URI fileUri = fileUrl.toURI();
            Path path = Paths.get(fileUri);
            BufferedWriter bfw = Files.newBufferedWriter(path);
            for (String key : map.keySet()) {
                bfw.write(key + ":" + map.get(key));
                bfw.newLine();
            }
            bfw.close();
            return true;
        } catch (FileNotFoundException e) {
            ShowDialog.fileConfigNotFound(fileName);
        } catch (IOException e) {
            ShowDialog.cannotOpenFileConfig(fileName);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }
}
