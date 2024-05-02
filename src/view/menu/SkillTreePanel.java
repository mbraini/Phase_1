package view.menu;

import controller.Constants;
import controller.Controller;
import controller.GameState;
import controller.RegularAbilitiesEnum;
import view.Abilities.Aceso;
import view.Abilities.Ares;
import view.Abilities.Proteus;
import view.Abilities.RegularAbility;
import view.PIG;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SkillTreePanel extends PIG {
    JButton back;
    JPanel ares;
    JPanel aceso;
    JPanel proteus;

    public SkillTreePanel(){
        this.setLayout(null);
        this.setBounds(0,0, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        this.setBackground(Color.BLACK);
        this.setVisible(false);

        initContainers();

        initAres();
        initAceso();
        initProteus();
        initBack();
        initAL();
    }

    private void initContainers() {
        ares = new JPanel();
        aceso = new JPanel();
        proteus = new JPanel();
        ares.setOpaque(false);
        aceso.setOpaque(false);
        proteus.setOpaque(false);
        this.add(ares);
        this.add(aceso);
        this.add(proteus);
    }

    private void initAres() {
        ares.setBounds(getWidth() / 10 ,getHeight() / 10 * 3 ,getWidth() / 10 * 2 ,getHeight() / 10 * 2);
        ares.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (GameState.xp >= 750) {
                    GameState.xp -= 750;
                    Controller.updateConfigs();
                    Ares.isAvailable = true;
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    private void initAceso() {
        aceso.setBounds(getWidth() / 10 * 4 ,getHeight() / 10 * 3 ,getWidth() / 10 * 2 ,getHeight() / 10 * 2);
        aceso.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (GameState.xp >= 500) {
                    GameState.xp -= 500;
                    Controller.updateConfigs();
                    Aceso.isAvailable = true;
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
    private void initProteus() {
        proteus.setBounds(getWidth() / 10 * 7 ,getHeight() / 10 * 3 ,getWidth() / 10 * 2 ,getHeight() / 10 * 2);
        proteus.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (GameState.xp >= 1000) {
                    GameState.xp -= 1000;
                    Controller.updateConfigs();
                    Proteus.isAvailable = true;
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    private void initBack() {
        back = new JButton();
        back.setBounds(Constants.GAME_WIDTH / 3 ,Constants.GAME_HEIGHT / 9 * 7 ,Constants.GAME_WIDTH / 3 ,Constants.GAME_HEIGHT / 9);
        back.setText("back");
        back.setBackground(Color.WHITE);
        back.setOpaque(true);
        back.setHorizontalTextPosition(JLabel.RIGHT);
        back.setVerticalTextPosition(JLabel.TOP);
        back.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        back.setFocusable(false);
        this.add(back);
    }
    private void initAL() {
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                end();
                MainFrame.menuPanel.start();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Constants.heal ,getWidth() / 10 ,getHeight() / 10 * 3 ,getWidth() / 10 * 2 ,getHeight() / 10 * 2 ,null);
        g.drawImage(Constants.empower ,getWidth() / 10 * 4 ,getHeight() / 10 * 3 ,getWidth() / 10 * 2 ,getHeight() / 10 * 2 ,null);
        g.drawImage(Constants.banish ,getWidth() / 10 * 7 ,getHeight() / 10 * 3 ,getWidth() / 10 * 2 ,getHeight() / 10 * 2 ,null);
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
