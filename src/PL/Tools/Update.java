package PL.Tools;

import DAL.Env;
import PL.MainScreen.MainFrame;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * Created by Nguyen Dang Dung on 4/6/2017 9:20 PM
 * Project: BTLJavaDangDung
 */
public class Update {
    public static String VER = "1.0.0";
    public final int NOTSHOWMESS = -1;
    public final int SHOWMESS = 1;
    private final int CHOOSEUPDATE = 0;
    //constant for error
    private final int NOINTERNET = -1;
    private final int UPTODATE = 0;
    private final int UPDATEFAILED = 1;
    private final int CANNOTCHECK = 2;
    private final int UNDEFINED = 3;
    private String LINKCHECKUPDATE = Env.envVar.get("UPDATE_LINKCHECKUPDATE");

    public Update() {

    }

    private static Path download(String sourceURL, String targetDirectory) throws IOException {
        URL url = new URL(sourceURL);
        String fileName = sourceURL.substring(sourceURL.lastIndexOf('/') + 1, sourceURL.length());
        Path targetPath = new File(targetDirectory + File.separator + fileName).toPath();
        Files.copy(url.openStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

        return targetPath;
    }

    private boolean netIsAvailable() {
        try {
            final URL url = new URL("http://www.google.com");
            final URLConnection conn = url.openConnection();
            conn.connect();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean testUpdate(String link) {
        if (netIsAvailable()) {
            try {
                Document doc = Jsoup.connect(link).get();
                doc.getElementsByTag("ver").first().text();
                doc.getElementsByTag("linkk").first().text();
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, Env.envVar.get("MESS_CANNOT_CONNECT_INTERNET"));
            return false;
        }
    }

    public void checkUpdate(int type) {
        int error = UNDEFINED;
        String ver = "";
        if (netIsAvailable()) {
            try {
                Document doc = Jsoup.connect(LINKCHECKUPDATE).get();
                ver = doc.getElementsByTag("ver").first().text();
                if (ver.equalsIgnoreCase(VER)) {
                    error = UPTODATE;
                } else {
                    int confirm = ShowDialog.confirmUpdate(this.getClass().getResource(Env.envVar.get("IMG_UPDATE")));
                    if (confirm == CHOOSEUPDATE) {
//                        JSONObject json = new JSONObject(IOUtils.toString(new URL("https://graph.facebook.com/me"), Charset.forName("UTF-8")));
                        String urlDownload = doc.getElementsByTag("linkk").first().text();
//                        System.out.println(urlDownload);
                        Path targetFile = download("http://" + urlDownload, System.getProperty("user.dir") + "/update");
                        if (!Files.exists(targetFile)) {
                            error = UPDATEFAILED;
                        } else {
                            JOptionPane.showMessageDialog(
                                    null,
                                    Env.envVar.get("MESS_UPDATED_TO") + " " + ver,
                                    Env.envVar.get("TIT_CHECK_UPDATE"), JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                error = CANNOTCHECK;
            }
        } else {
            error = NOINTERNET;
        }
        String mess = "", title = Env.envVar.get("TIT_CHECK_UPDATE");
        int typemess = JOptionPane.INFORMATION_MESSAGE;
        switch (error) {
            case UPTODATE:
                mess = Env.envVar.get("MESS_UPTODATE");
                break;
            case UPDATEFAILED:
                mess = Env.envVar.get("MESS_UPDATE_FAILED");
                typemess = JOptionPane.ERROR_MESSAGE;
                break;
            case CANNOTCHECK:
                mess = Env.envVar.get("MESS_CANNOT_CHECK_UPDATE");
                typemess = JOptionPane.ERROR_MESSAGE;
                break;
            case NOINTERNET:
                mess = Env.envVar.get("MESS_CANNOT_CONNECT_INTERNET");
                typemess = JOptionPane.ERROR_MESSAGE;
                break;
            default:
                mess = Env.envVar.get("MESS_UNDEFINED");
                typemess = JOptionPane.ERROR_MESSAGE;
                break;
        }
        if (type == SHOWMESS) JOptionPane.showMessageDialog(MainFrame.mainPanel, mess, title, typemess);
    }
}
