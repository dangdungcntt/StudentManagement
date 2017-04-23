package PL.SettingsScreen;

import DAL.Env;
import PL.Tools.GridBagBuilder;
import PL.Tools.ShowDialog;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Nguyen Dang Dung on 4/21/2017 3:19 PM
 * Project: BTLJavaDangDung
 */
public class SystemPanel extends JPanel implements MenuSettingsPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JComboBox<String> cbLang;
    public JComboBox<String> cbStudetnPerPage;
    public HashMap<String, String> mapSettings;
    List<String> listLanguage, listLanguageCode;
    private GridBagLayout gb;
    private GridBagConstraints gbc;

    public SystemPanel() {
        super();
        init();
        initData();
    }

    private void initData() {
        mapSettings = new HashMap<String, String>();
        mapSettings.put("SYSTEM_LANGUAGE", "");
        mapSettings.put("LIST_NUMBER_PER_PAGE", "");
        listLanguage = new ArrayList<String>();
        listLanguageCode = new ArrayList<String>();
        //add Language
        cbLang.removeAllItems();
        readFile("LanguageSupport.xyz");
        for (String s : listLanguage) {
            cbLang.addItem(s);
        }
        cbLang.setSelectedItem(listLanguage.get(listLanguageCode.indexOf(Env.envVar.get("SYSTEM_LANGUAGE"))));
        String[] listStudentPerPage = {"10", "20", "50", "100", "200", "500"};
        //set student per page
        cbStudetnPerPage.removeAllItems();
        for (String s : listStudentPerPage) {
            cbStudetnPerPage.addItem(s);
        }
        cbStudetnPerPage.setSelectedItem(Env.envVar.get("LIST_NUMBER_PER_PAGE"));
    }

    private void init() {
        gb = new GridBagLayout();
        gbc = new GridBagConstraints();
        this.setLayout(gb);

        cbLang = new JComboBox<String>();
        cbLang.setPreferredSize(new Dimension(150, 30));
        cbStudetnPerPage = new JComboBox<String>();
        cbStudetnPerPage.setPreferredSize(new Dimension(150, 30));


        gbc.insets = new Insets(5, 5, 5, 5);
        GridBagBuilder.addComponent(
                gb, gbc, this,
                new JLabel(Env.envVar.get("STR_SYSTEM_LANGUAGE")),
                0, 0, 50, 0);
        GridBagBuilder.addComponent(
                gb, gbc, this, cbLang, 0, 1, 0, 0);
        GridBagBuilder.addComponent(
                gb, gbc, this, new JLabel(""), 0, 2, 160, 0);
        GridBagBuilder.addComponent(
                gb, gbc, this, new JLabel(Env.envVar.get("STR_STUDENT_PER_PAGE")), 1, 0, 50, 0);
        GridBagBuilder.addComponent(
                gb, gbc, this, cbStudetnPerPage, 1, 1, 0, 0);

    }

    private void readFile(String fileName) {
        try {
            URL fileUrl = this.getClass().getResource("/config/" + fileName);
            InputStream isr = fileUrl.openStream();
            BufferedReader bfr = new BufferedReader(new InputStreamReader(isr));
            String line = bfr.readLine();
            while (line != null) {
                String[] res = line.split(":");
                listLanguageCode.add(res[0]);
                listLanguage.add(res[1]);
                line = bfr.readLine();
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
        mapSettings.replace("SYSTEM_LANGUAGE", listLanguageCode.get(cbLang.getSelectedIndex()));
        mapSettings.replace("LIST_NUMBER_PER_PAGE", (String) cbStudetnPerPage.getSelectedItem());
    }

    public boolean saveData() {
        try {
            URL fileUrl = this.getClass().getResource("/config/SystemConfig.xyz");
            URI fileUri = fileUrl.toURI();
            Path path = Paths.get(fileUri);
            BufferedWriter bfw = Files.newBufferedWriter(path);
            for (String key : mapSettings.keySet()) {
                bfw.write(key + ":" + mapSettings.get(key));
                bfw.newLine();
            }
            bfw.close();
            return true;
        } catch (FileNotFoundException e) {
            ShowDialog.fileConfigNotFound("SystemConfig.xyz");
        } catch (IOException e) {
            ShowDialog.cannotOpenFileConfig("SystemConfig.xyz");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }
}
