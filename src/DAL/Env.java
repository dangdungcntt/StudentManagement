package DAL;

import PL.Tools.ShowDialog;

import java.io.*;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by Nguyá»…n Ä�Äƒng DÅ©ng on 4/11/2017 1:39 PM
 * Project: BTLJavaDangDung
 */
public class Env {
    public static HashMap<String, String> envVar;

    public Env() {
        envVar = new HashMap<String, String>();
        readConfig("SystemConfig.xyz", "config", false);
        readConfig("UpdateConfig.xyz", "config", false);
        readConfig("DbConfig.xyz", "config", false);
        readConfig("MysqlConfig.xyz", "config", false);
        if (envVar.get("TYPE_DB").equalsIgnoreCase("sqlserver")) {
            readConfig("SqlserverConfig.xyz", "config", true);
        }
        String dir = "values_en";
        readConfig("Strings.xyz", dir, false);
        readConfig("Messages.xyz", dir, false);
        readConfig("ImgUrl.xyz", dir, false);
        if (!envVar.get("SYSTEM_LANGUAGE").equalsIgnoreCase("en")) {
            dir = "values_" + envVar.get("SYSTEM_LANGUAGE");
            readConfig("Strings.xyz", dir, true);
            readConfig("Messages.xyz", dir, true);
            readConfig("ImgUrl.xyz", dir, true);
        }
    }

    private void readConfig(String fileName, String dir, boolean update) {
        try {
            URL fileUrl = this.getClass().getResource("/" + dir + "/" + fileName);
            InputStream isr = fileUrl.openStream();
            BufferedReader bfr = new BufferedReader(new InputStreamReader(isr));
            String line = bfr.readLine();
            while (line != null) {
                String[] res = line.split(":");
                if (res.length > 1) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 1; i < res.length - 1; i++) {
                        stringBuilder.append(res[i]).append(':');
                    }
                    stringBuilder.append(res[res.length - 1]);
                    if (update) {
                        envVar.replace(res[0], stringBuilder.toString());
                    } else {
                        envVar.put(res[0], stringBuilder.toString());
                    }
                } else {
                    envVar.put(res[0], "");
                }
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
}
