package PL.SettingsScreen;

import DAL.Env;
import PL.Tools.CreateComponent;
import PL.Tools.GridBagBuilder;
import PL.Tools.ShowDialog;
import PL.Tools.Update;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Created by dangd on 4/21/2017 3:19 PM
 * Project: BTLJavaDangDung
 */
public class UpdatePanel extends JPanel implements MenuSettingsPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JTextField txtLinkCheckUpdate;
    public HashMap<String, String> mapSettings;
    public JLabel lblCheck;
    public Update update;
    public boolean isSuccess;
    private GridBagLayout gb;
    private GridBagConstraints gbc;

    public UpdatePanel() {
        super();
        init();
        initData();
        addEvents();
    }

    private void addEvents() {
        txtLinkCheckUpdate.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                txtLinkChange();
            }
        });
    }

    private void txtLinkChange() {
        if (update.testUpdate(txtLinkCheckUpdate.getText().trim())) {
            lblCheck.setIcon(new ImageIcon(this.getClass().getResource(Env.envVar.get("IMG_SUCCESS"))));
            isSuccess = true;
        } else {
            lblCheck.setIcon(new ImageIcon(this.getClass().getResource(Env.envVar.get("IMG_FAIL"))));
            isSuccess = false;
        }
    }

    private void initData() {
        update = new Update();
        isSuccess = true;
        mapSettings = new HashMap<String, String>();
        mapSettings.put("UPDATE_LINKCHECKUPDATE", Env.envVar.get("UPDATE_LINKCHECKUPDATE"));
        //add Language
        txtLinkCheckUpdate.setText(Env.envVar.get("UPDATE_LINKCHECKUPDATE"));
        txtLinkChange();
    }

    private void init() {
        gb = new GridBagLayout();
        gbc = new GridBagConstraints();
        this.setLayout(gb);

        txtLinkCheckUpdate = CreateComponent.createJTextField(
                "", 150, 30,
                Font.PLAIN, 14, JTextField.LEFT);
        lblCheck = new JLabel("");
        lblCheck.setIcon(new ImageIcon(this.getClass().getResource(Env.envVar.get("IMG_SUCCESS"))));

        gbc.insets = new Insets(5, 5, 5, 5);
        GridBagBuilder.addComponent(
                gb, gbc, this, lblCheck, 0, 0, 0, 0);
        GridBagBuilder.addComponent(
                gb, gbc, this, new JLabel(Env.envVar.get("STR_LINK_CHECK_UP_DATE")), 0, 1, 20, 0);
        GridBagBuilder.addComponent(
                gb, gbc, this, txtLinkCheckUpdate, 0, 2, 190, 0);
        gbc.gridwidth = 3;
        JLabel note = new JLabel(Env.envVar.get("STR_NOTE"));
        note.setFont(new Font(null, Font.ITALIC, 14));
        note.setForeground(Color.RED);
        GridBagBuilder.addComponent(
                gb, gbc, this, note, 1, 0, 190, 0);
        gbc.gridwidth = 1;
    }

    public void getData() {
        if (isSuccess) {
            mapSettings.replace("UPDATE_LINKCHECKUPDATE", txtLinkCheckUpdate.getText().trim());
        }
    }

    public boolean saveData() {
        try {
            URL fileUrl = this.getClass().getResource("/config/UpdateConfig.xyz");
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
            ShowDialog.fileConfigNotFound("UpdateConfig.xyz");
        } catch (IOException e) {
            ShowDialog.cannotOpenFileConfig("UpdateConfig.xyz");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }
}
