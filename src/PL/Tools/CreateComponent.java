package PL.Tools;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by Nguyễn Đăng Dũng on 4/4/2017 1:34 PM
 * Project: BaiTapLonJava
 */
public class CreateComponent {
    public static JLabel createJLabel(String title, int width, int height, int style, int size, int align) {
        JLabel label = new JLabel(title);
        label.setPreferredSize(new Dimension(width, height));
        label.setFont(new Font(null, style, size));
        label.setHorizontalAlignment(align);
        return label;
    }

    public static JLabel createJLabel(String title, int width, int height, int align) {
        JLabel label = new JLabel(title);
        label.setPreferredSize(new Dimension(width, height));
        label.setHorizontalAlignment(align);
        return label;
    }

    public static JLabel createJLabel(String title, int align) {
        JLabel label = new JLabel(title);
        label.setPreferredSize(new Dimension(100, 30));
        label.setHorizontalAlignment(align);
        return label;
    }

    public static JTextField createJTextField(String title, int width, int height, int style, int size, int align) {
        JTextField textField = new JTextField(title);
        textField.setPreferredSize(new Dimension(width, height));
        textField.setFont(new Font(null, style, size));
        textField.setHorizontalAlignment(align);
        textField.setBorder(new RoundedBorder(5));

        return textField;
    }

    public static JDateChooser createJDateChooser(String format, int width, int height, int style, int size) {
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setDateFormatString(format);
        dateChooser.setPreferredSize(new Dimension(width, height));
        dateChooser.setFont(new Font(null, style, size));
        dateChooser.setBorder(new RoundedBorder(3));

//        dateChooser.setHorizontalAlignment(algin);
        return dateChooser;
    }

    public static JButton createJButton(String title, int width, int height, int style, int size, int align) {
        JButton button = new JButton(title);
        button.setPreferredSize(new Dimension(width, height));
        button.setFont(new Font(null, style, size));
        button.setHorizontalAlignment(align);
        button.setBorder(new RoundedBorder(5));
        return button;
    }

    public static JButton createFlatJButton(String title, int width, int height, Color color) {
        JButton button = new JButton(title);
        button.setPreferredSize(new Dimension(width, height));
        button.setBackground(color);
        Border margin = new EmptyBorder(0, 0, 0, 0);
        button.setBorder(margin);
        return button;
    }

    public static TitledBorder createBorder(String title) {
        return BorderFactory.createTitledBorder(
                null,
                title,
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font(null, Font.BOLD, 15),
                Color.BLUE
        );
    }
}
