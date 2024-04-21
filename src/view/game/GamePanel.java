package view.game;

import view.PIG;

import java.awt.*;

public class GamePanel extends PIG {

    public GamePanel(){
        this.setLayout(null);
        this.setBackground(Color.RED);
        this.setBounds(-1000 ,-1000 ,3000 ,3000);
    }

    @Override
    public void start() {
        this.setVisible(true);
    }

    @Override
    public void end() {
        this.setVisible(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

    }
}
