package PL.Tools;

import com.sun.istack.internal.Nullable;

import java.awt.*;

/**
 * Created by Nguyễn Đăng Dũng on 4/4/2017 1:49 PM
 * Project: BaiTapLonJava
 */
public class GridBagBuilder {
    public static void addComponent(GridBagLayout gb, GridBagConstraints gbc, Container ct, Component c, int row, int col, int ncol, int nrow, @Nullable Insets insets, Integer fill) {
        gbc.gridx = col;
        gbc.gridy = row;
        gbc.ipadx = ncol;
        gbc.ipady = nrow;
        if (insets != null) {
            gbc.insets = insets;
        }
        gbc.fill = fill;
        gb.setConstraints(c, gbc);
        ct.add(c);
    }

    public static void addComponent(GridBagLayout gb, GridBagConstraints gbc, Container ct, Component c, int row, int col, int ncol, int nrow, Insets insets) {
        gbc.gridx = col;
        gbc.gridy = row;
        gbc.ipadx = ncol;
        gbc.ipady = nrow;
        gbc.insets = insets;
        gb.setConstraints(c, gbc);
        ct.add(c);
    }

    public static void addComponent(GridBagLayout gb, GridBagConstraints gbc, Container ct, Component c, int row, int col, int ncol, int nrow) {
        gbc.gridx = col;
        gbc.gridy = row;
        gbc.ipadx = ncol;
        gbc.ipady = nrow;
        gb.setConstraints(c, gbc);
        ct.add(c);
    }
}
