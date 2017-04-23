package PL.MainScreen;

import DAL.Env;
import PL.Tools.ShowDialog;
import PL.Tools.Update;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by Nguyá»…n Ä�Äƒng DÅ©ng on 4/4/2017 12:59 PM
 * Project: BaiTapLonJava
 */
public class MainScreenMenuBar extends JMenuBar {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public JMenuItem itemSettings, itemRefresh, itemExit, itemUpdate, itemAbout;
    private String STR_FILE = Env.envVar.get("STR_FILE");
    private String STR_HELP = Env.envVar.get("STR_HELP");
    private String STR_SETTINGS = Env.envVar.get("STR_SETTINGS");
    private String STR_REFRESH = Env.envVar.get("STR_REFRESH");
    private String STR_EXIT_MENU = Env.envVar.get("STR_EXIT_MENU");
    private String STR_CHECK_UP_DATE = Env.envVar.get("STR_CHECK_UP_DATE");
    private String STR_ABOUT = Env.envVar.get("STR_ABOUT");
    private JMenu menuFile, menuHelp;

    public MainScreenMenuBar() {
        super();
        initMenuBar();
        addEvents();
    }

    private void addEvents() {
        itemExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exitHandle();
            }
        });
        itemUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkUpdate();
            }
        });
        itemAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new About(Env.envVar.get("STR_ABOUT"), null);
            }
        });
    }

    private void checkUpdate() {
        Update update = new Update();
        update.checkUpdate(update.SHOWMESS);
    }

    private void exitHandle() {
        ShowDialog.confirmExit();
    }

    private void initMenuBar() {
        //setLayout

        //createComponent
        menuFile = new JMenu(STR_FILE);
        itemSettings = new JMenuItem(STR_SETTINGS);
        itemSettings.setIcon(new ImageIcon(this.getClass().getResource(Env.envVar.get("IMG_SETTINGS"))));
        itemRefresh = new JMenuItem(STR_REFRESH);
        itemRefresh.setIcon(new ImageIcon(this.getClass().getResource(Env.envVar.get("IMG_REFRESH"))));
        itemExit = new JMenuItem(STR_EXIT_MENU);
        itemExit.setIcon(new ImageIcon(this.getClass().getResource(Env.envVar.get("IMG_ITEMEXIT"))));

        menuHelp = new JMenu(STR_HELP);
        itemUpdate = new JMenuItem(STR_CHECK_UP_DATE);
        itemUpdate.setIcon(new ImageIcon(this.getClass().getResource(Env.envVar.get("IMG_ITEMUPDATE"))));
        itemAbout = new JMenuItem(STR_ABOUT);
        itemAbout.setIcon(new ImageIcon(this.getClass().getResource(Env.envVar.get("IMG_ITEMABOUT"))));


        //addComponent
        menuFile.add(itemRefresh);
        menuFile.add(itemSettings);
        menuFile.addSeparator();
        menuFile.add(itemExit);

        menuHelp.add(itemUpdate);
        menuHelp.add(itemAbout);

        this.add(menuFile);
        this.add(menuHelp);
    }
}
