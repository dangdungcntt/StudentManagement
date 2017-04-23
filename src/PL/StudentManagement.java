package PL;

import DAL.Env;
import PL.MainScreen.MainFrame;
import PL.Tools.Update;

/**
 * Created by Nguyen Dang Dung on 4/6/2017 1:55 PM
 * Project: BTLJavaDangDung
 */
public class StudentManagement {
    private final static int WIDTH = 900;
    private final static int HEIGHT = 750;

    public static void main(String[] args) {
        new Env();
        Update update = new Update();
        update.checkUpdate(update.NOTSHOWMESS);
        new MainFrame(Env.envVar.get("STR_TITLE") + " - " + Update.VER, WIDTH, HEIGHT);
    }
}
