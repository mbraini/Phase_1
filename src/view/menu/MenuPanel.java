package view.menu;

import controller.Application;
import controller.Constants;
import view.PIG;
import view.game.GameFrame;
import view.game.WindowKill;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MenuPanel extends PIG {
    JButton exit;
    JButton start;
    JButton settings;
    JButton tutorial;
    JButton skillTree;
    public MenuPanel(){
        this.setLayout(null);
        this.setBounds(0,0, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        this.setBackground(Color.BLACK);

        initExit();
        initSettings();
        initStart();
        initSkillTree();
        initTutorial();
        initAL();
    }

    private void initAL() {
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Robot robot = new Robot();
                    robot.keyPress(KeyEvent.VK_WINDOWS);
                    robot.keyPress(KeyEvent.VK_D);
                    robot.keyRelease(KeyEvent.VK_WINDOWS);
                    robot.keyRelease(KeyEvent.VK_D);
                } catch (AWTException ex) {
                    throw new RuntimeException(ex);
                }
                Application.mainFrame.dispose();
                Application.gameFrame = new GameFrame();
                WindowKill.startGame();
            }
        });
    }

    private void initExit(){
        exit = new JButton();
        exit.setBounds(0 ,Constants.GAME_HEIGHT - Constants.GAME_HEIGHT / 5 ,Constants.GAME_WIDTH/5,Constants.GAME_HEIGHT / 5);
        exit.setText("exit");
        exit.setBackground(Color.WHITE);
        exit.setOpaque(true);
        exit.setHorizontalTextPosition(JLabel.RIGHT);
        exit.setVerticalTextPosition(JLabel.TOP);
        exit.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        exit.setFocusable(false);
        this.add(exit);
    }

    private void initStart(){
        start = new JButton();
        start.setBounds(Constants.GAME_WIDTH / 5 ,Constants.GAME_HEIGHT - Constants.GAME_HEIGHT / 5 ,Constants.GAME_WIDTH/5,Constants.GAME_HEIGHT / 5);
        start.setText("start");
        start.setBackground(Color.WHITE);
        start.setOpaque(true);
        start.setHorizontalTextPosition(JLabel.RIGHT);
        start.setVerticalTextPosition(JLabel.TOP);
        start.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        start.setFocusable(false);
        this.add(start);
    }

    private void initSettings(){
        settings = new JButton();
        settings.setBounds(Constants.GAME_WIDTH / 5 * 2 ,Constants.GAME_HEIGHT - Constants.GAME_HEIGHT / 5 ,Constants.GAME_WIDTH/5,Constants.GAME_HEIGHT / 5);
        settings.setText("settings");
        settings.setBackground(Color.WHITE);
        settings.setOpaque(true);
        settings.setHorizontalTextPosition(JLabel.RIGHT);
        settings.setVerticalTextPosition(JLabel.TOP);
        settings.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        settings.setFocusable(false);
        this.add(settings);
    }

    private void initTutorial(){
        tutorial = new JButton();
        tutorial.setBounds(Constants.GAME_WIDTH / 5 * 3 ,Constants.GAME_HEIGHT - Constants.GAME_HEIGHT / 5 ,Constants.GAME_WIDTH/5,Constants.GAME_HEIGHT / 5);
        tutorial.setText("tutorial");
        tutorial.setBackground(Color.WHITE);
        tutorial.setOpaque(true);
        tutorial.setHorizontalTextPosition(JLabel.RIGHT);
        tutorial.setVerticalTextPosition(JLabel.TOP);
        tutorial.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        tutorial.setFocusable(false);
        this.add(tutorial);
    }

    private void initSkillTree(){
        skillTree = new JButton();
        skillTree.setBounds(Constants.GAME_WIDTH / 5 * 4 ,Constants.GAME_HEIGHT - Constants.GAME_HEIGHT / 5 ,Constants.GAME_WIDTH/5,Constants.GAME_HEIGHT / 5);
        skillTree.setText("skillTree");
        skillTree.setBackground(Color.WHITE);
        skillTree.setOpaque(true);
        skillTree.setHorizontalTextPosition(JLabel.RIGHT);
        skillTree.setVerticalTextPosition(JLabel.TOP);
        skillTree.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        skillTree.setFocusable(false);
        this.add(skillTree);
    }

    @Override
    public void start() {
        this.setVisible(true);
    }

    @Override
    public void end() {
        this.setVisible(false);
    }
}
