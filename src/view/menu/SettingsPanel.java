package view.menu;

import controller.Configs;
import controller.Constants;
import view.PIG;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsPanel extends PIG {

    JSlider soundSlider;
    JSlider difficultySlider;
    JSlider sensitivitySlider;
    JButton back;

    public SettingsPanel(){
        this.setLayout(null);
        this.setBounds(0,0, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        this.setBackground(Color.BLACK);
        this.setVisible(false);

        initSoundSlider();
        initDifficultySlider();
        initSensitivitySlider();
        initBack();
        initChangeListeners();
        initAL();
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

    private void initChangeListeners() {
        soundSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Configs.SOUND = soundSlider.getValue();
            }
        });
        difficultySlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Configs.DIFFICULTY = difficultySlider.getValue();
            }
        });
        sensitivitySlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Configs.SENSITIVITY = sensitivitySlider.getValue();
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

    private void initSensitivitySlider() {
        sensitivitySlider = new JSlider(1 ,4);
        sensitivitySlider.setBounds(Constants.GAME_WIDTH / 5 ,Constants.GAME_HEIGHT / 9 * 5 ,Constants.GAME_WIDTH / 5 * 3 ,Constants.GAME_HEIGHT / 9);
        sensitivitySlider.setPaintTicks(true);
        sensitivitySlider.setMinorTickSpacing(1);
        sensitivitySlider.setPaintTrack(true);
        sensitivitySlider.setMajorTickSpacing(1);
        sensitivitySlider.setPaintLabels(true);
        sensitivitySlider.setFont(new Font("MV Boli" ,Font.PLAIN ,15));

        this.add(sensitivitySlider);
    }

    private void initDifficultySlider() {
        difficultySlider = new JSlider(1 ,3);
        difficultySlider.setBounds(Constants.GAME_WIDTH / 5 ,Constants.GAME_HEIGHT / 9 * 3 ,Constants.GAME_WIDTH / 5 * 3 ,Constants.GAME_HEIGHT / 9);
        difficultySlider.setPaintTicks(true);
        difficultySlider.setMinorTickSpacing(1);
        difficultySlider.setPaintTrack(true);
        difficultySlider.setMajorTickSpacing(1);
        difficultySlider.setPaintLabels(true);
        difficultySlider.setFont(new Font("MV Boli" ,Font.PLAIN ,15));

        this.add(difficultySlider);
    }

    private void initSoundSlider() {
        soundSlider = new JSlider(0 ,100);
        soundSlider.setBounds(Constants.GAME_WIDTH / 5 ,Constants.GAME_HEIGHT / 9 ,Constants.GAME_WIDTH / 5 * 3 ,Constants.GAME_HEIGHT / 9);
        soundSlider.setPaintTicks(true);
        soundSlider.setMinorTickSpacing(1);
        soundSlider.setPaintTrack(true);
        soundSlider.setMajorTickSpacing(10);
        soundSlider.setPaintLabels(true);
        soundSlider.setFont(new Font("MV Boli" ,Font.PLAIN ,15));

        this.add(soundSlider);
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
