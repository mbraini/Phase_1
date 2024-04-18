package view.menu;

import controller.Constants;

import javax.swing.*;

public class MainFrame extends JFrame {
    public static MainPanel mainPanel;
    public static MenuPanel menuPanel;
    public MainFrame(){
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(700 + Constants.barD.width ,700 + Constants.barD.height);
        this.setLocationRelativeTo(null);

        mainPanel = new MainPanel();
        this.setContentPane(mainPanel);

        menuPanel = new MenuPanel();

        mainPanel.add(menuPanel);

        this.setVisible(true);
    }
}
