package PL.Tools;

import DAL.Env;
import DAL.Student;

import javax.swing.*;
import java.net.URL;
import java.text.SimpleDateFormat;

/**
 * Created by Nguyá»…n Ä�Äƒng DÅ©ng on 4/11/2017 8:08 AM
 * Project: BaiTapLonJava
 */
public class ShowDialog {
    public static void cannotConnectDB() {
        JOptionPane.showMessageDialog(
                null,
                Env.envVar.get("MESS_CANNOT_CONNECT_DATABASE") + "\n" +
                        Env.envVar.get("MESS_PLEASE_CHECK_CONNECTION") + "\n" +
                        Env.envVar.get("MESS_CHOOSE_FILE_REFRESH"),
                Env.envVar.get("TIT_ERROR"), JOptionPane.ERROR_MESSAGE);
    }

    public static void added() {
        JOptionPane.showMessageDialog(
                null,
                Env.envVar.get("MESS_STUDENT_ADDED"),
                Env.envVar.get("TIT_SUCCESS"), JOptionPane.INFORMATION_MESSAGE);
    }

    public static void updated() {
        JOptionPane.showMessageDialog(
                null,
                Env.envVar.get("MESS_STUDENT_UPDATED"),
                Env.envVar.get("TIT_SUCCESS"), JOptionPane.INFORMATION_MESSAGE);
    }

    public static void deleted() {
        JOptionPane.showMessageDialog(
                null,
                Env.envVar.get("MESS_STUDENT_DELETED"),
                Env.envVar.get("TIT_SUCCESS"),
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void studenIdExists() {
        JOptionPane.showMessageDialog(
                null,
                Env.envVar.get("MESS_STUDENT_ID_EXISTS"),
                Env.envVar.get("TIT_ERROR"),
                JOptionPane.ERROR_MESSAGE);
    }

    public static void studenIdIsInteger() {
        JOptionPane.showMessageDialog(
                null,
                Env.envVar.get("MESS_STUDENID_IS_INT"),
                Env.envVar.get("TIT_FORMAT_ERROR"),
                JOptionPane.ERROR_MESSAGE);
    }

    public static boolean confirmDeleteStudent(Student student, String cityName) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String mess =
                Env.envVar.get("MESS_CONFIRM_DELETE_STUDENT") + "\n\n" +
                        Env.envVar.get("STR_STUDENT_ID") + ": " + student.getStudentId() + "\n" +
                        Env.envVar.get("STR_STUDENT_NAME") + ": " + student.getStudentName() + "\n" +
                        Env.envVar.get("STR_STUDENT_CITY") + ": " + cityName + "\n" +
                        Env.envVar.get("STR_STUDENT_BIRTH") + ": " + format.format(student.getBirth()) + "\n" +
                        Env.envVar.get("STR_STUDENT_GENDER") + ": " + (student.isGender() ?
                        Env.envVar.get("STR_STUDENT_MALE") : Env.envVar.get("STR_STUDENT_FEMALE")) + "\n" +
                        Env.envVar.get("STR_STUDENT_MATH") + ": " + student.getMath() + "\n" +
                        Env.envVar.get("STR_STUDENT_PHYSICAL") + ": " + student.getPhysical() + "\n" +
                        Env.envVar.get("STR_STUDENT_CHEMISTRY") + ": " + student.getChemistry();

        return JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(
                null,
                mess,
                Env.envVar.get("TIT_CONFIRM_DELETE_STUDENT"),
                JOptionPane.YES_NO_OPTION);
    }

    public static void confirmExit() {
        int confirm = JOptionPane.showConfirmDialog(
                null,
                Env.envVar.get("MESS_CONFIRM_EXIT"),
                Env.envVar.get("TIT_CONFIRM_EXIT"),
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public static void fileConfigNotFound(String fileName) {
        JOptionPane.showMessageDialog(
                null,
                Env.envVar.get("MESS_FILE") + fileName + Env.envVar.get("MESS_NOT_FOUND"));
    }

    public static void cannotOpenFileConfig(String fileName) {
        JOptionPane.showMessageDialog(
                null,
                Env.envVar.get("MESS_CANNOT_OPEN_FILE") + fileName + "!");
    }

    public static void somethingWentWrong() {
        JOptionPane.showMessageDialog(
                null,
                Env.envVar.get("MESS_STUDENID_IS_INT"),
                Env.envVar.get("TIT_FORMAT_ERROR"),
                JOptionPane.ERROR_MESSAGE);
    }


    public static int confirmUpdate(URL iconPath) {
        String[] options = new String[]{Env.envVar.get("STR_UPDATE"), Env.envVar.get("STR_LATER")};
        return JOptionPane.showOptionDialog(
                null,
                Env.envVar.get("MESS_UPDATE_AVAILABLE"),
                Env.envVar.get("TIT_CHECK_UPDATE"),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                new ImageIcon(iconPath), options, options[0]);
    }

    public static void cityNotExists() {
        JOptionPane.showMessageDialog(
                null,
                Env.envVar.get("MESS_CITY_NOT_EXISTS"),
                Env.envVar.get("TIT_SYSTEM_ERROR"),
                JOptionPane.ERROR_MESSAGE);
    }

    public static boolean confirmAddStudent(Student student, String cityName) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String mess =
                Env.envVar.get("MESS_CONFIRM_ADD_STUDENT") + "\n\n" +
                        Env.envVar.get("STR_STUDENT_ID") + ": " + student.getStudentId() + "\n" +
                        Env.envVar.get("STR_STUDENT_NAME") + ": " + student.getStudentName() + "\n" +
                        Env.envVar.get("STR_STUDENT_CITY") + ": " + cityName + "\n" +
                        Env.envVar.get("STR_STUDENT_BIRTH") + ": " + format.format(student.getBirth()) + "\n" +
                        Env.envVar.get("STR_STUDENT_GENDER") + ": " + (student.isGender() ?
                        Env.envVar.get("STR_STUDENT_MALE") : Env.envVar.get("STR_STUDENT_FEMALE")) + "\n" +
                        Env.envVar.get("STR_STUDENT_MATH") + ": " + student.getMath() + "\n" +
                        Env.envVar.get("STR_STUDENT_PHYSICAL") + ": " + student.getPhysical() + "\n" +
                        Env.envVar.get("STR_STUDENT_CHEMISTRY") + ": " + student.getChemistry();

        return JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(
                null,
                mess,
                Env.envVar.get("TIT_CONFIRM_ADD_STUDENT"),
                JOptionPane.YES_NO_OPTION);
    }

    public static void pageNumberIsInteger() {
        JOptionPane.showMessageDialog(
                null,
                Env.envVar.get("MESS_PAGE_NUMBER_IS_INTEGER"),
                Env.envVar.get("TIT_FORMAT_ERROR"),
                JOptionPane.ERROR_MESSAGE);
    }
    
    public static void messWhenSaveSettings() {
        JOptionPane.showMessageDialog(
                null,
                Env.envVar.get("MESS_SAVE_SETTINGS"),
                Env.envVar.get("TIT_SAVE_SETTINGS"),
                JOptionPane.INFORMATION_MESSAGE);
    }
}
