package PL.MainScreen.InforAndControl;

import DAL.City;
import DAL.Env;
import PL.Tools.Commands;
import PL.Tools.CreateComponent;
import PL.Tools.CustomPanel;
import PL.Tools.GridBagBuilder;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by Nguyen Dang Dung on 4/5/2017 2:01 PM
 * Project: BaiTapLonJava
 */
public class InforPanel extends CustomPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int FREE = 0;
    public static int ADDING = 1;
    public static int EDITING = 2;
    public static int STATUS = 0;
    private String TIT_INFOR = Env.envVar.get("TIT_INFOR");
    private String TIT_ADD = Env.envVar.get("TIT_ADD");
    private String TIT_EDIT = Env.envVar.get("TIT_EDIT");
    private DetailPanel detailPanel;
    private ButtonPanel buttonPanel;
    private GridBagLayout gb;
    private GridBagConstraints gbc;

    public InforPanel() {
        super();
        initInforPanel();
    }

    private void initInforPanel() {
        //setLayout
        gb = new GridBagLayout();
        gbc = new GridBagConstraints();
        this.setLayout(gb);

        //createComponent
        detailPanel = new DetailPanel();
        buttonPanel = new ButtonPanel();

        //addcomponent
        gbc.fill = GridBagConstraints.HORIZONTAL;
        GridBagBuilder.addComponent(gb, gbc, this, detailPanel, 0, 0, 200, 0);
        gbc.insets = new Insets(10, 0, 10, 0);
        GridBagBuilder.addComponent(gb, gbc, this, buttonPanel, 1, 0, 0, 0);

        this.setBorder(CreateComponent.createBorder(TIT_INFOR));
    }

    public void showDetail(String[] student) {
        detailPanel.showDetail(student);
        buttonPanel.executeCommand(Commands.SHOW_STUDENT_DETAIL);
    }

    public void executeCommand(int command) {
        if (command == Commands.FILTER_STUDENT && STATUS == FREE) {
            detailPanel.hideDetail();
            buttonPanel.executeCommand(command);
        } else if (command == Commands.ADD_STUDENT) {
            detailPanel.hideDetail();
            buttonPanel.executeCommand(command);
            detailPanel.setEditable(true);
            this.setBorder(CreateComponent.createBorder(TIT_ADD));
            STATUS = ADDING;
        } else if (command == Commands.EDIT_STUDENT) {
            buttonPanel.executeCommand(command);
            STATUS = EDITING;
            detailPanel.rollBackData();
            detailPanel.setEditable(true);
            this.setBorder(CreateComponent.createBorder(TIT_EDIT));
        } else if (command == Commands.DELETE_STUDENT) {
            detailPanel.hideDetail();
            buttonPanel.executeCommand(Commands.CANCEL);
        } else if (command == Commands.OK) {
            detailPanel.setEditable(false);
            detailPanel.updateSum();
            buttonPanel.executeCommand(command);
            STATUS = FREE;
        } else if (command == Commands.CANCEL) {
            if (InforPanel.STATUS != InforPanel.EDITING) {
                detailPanel.hideDetail();
            }
            detailPanel.setEditable(false);
            buttonPanel.executeCommand(command);
            this.setBorder(CreateComponent.createBorder(TIT_INFOR));
            detailPanel.rollBackData();
            STATUS = FREE;


        }
    }

    public String[] getData() {

        return detailPanel.getData();
    }

    public void addCityData(HashMap<Integer, City> mapCity) {
        detailPanel.addCityData(mapCity);
    }

    public void updateRollBack() {
        detailPanel.updateRollBack();
    }
}
