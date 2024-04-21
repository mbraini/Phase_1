package view.game;
import controller.*;
import controller.actionlisteners.EpsilonAiming;
import controller.actionlisteners.EpsilonMovement;
import view.PIG;
import view.menu.MainFrame;
import view.objectsView.OIGView;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class WindowKill extends PIG {
    static GameLoop gameLoop;
    static GameState gameState;
    static Shop shop;

    public WindowKill(){
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.setBounds(0 ,0 , Constants.GAME_WIDTH ,Constants.GAME_HEIGHT);
        this.setVisible(false);
    }

    @Override
    public void start() {

    }

    @Override
    public void end() {
        this.setVisible(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        for (int i = 0 ;i < OIGView.OIGs.size() ;i++){
            OIGView.OIGs.get(i).draw(g2d);
        }
    }

    public void startGame(){
        Controller.startGame();
        GameFrame.windowKill.setVisible(true);
        Controller.gameStartAnimation();
        GameFrame.windowKill.addKeyListener(new EpsilonMovement());
        GameFrame.windowKill.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == 'p' && !GameState.isPause){
                    shop = new Shop();
                    GameFrame.gamePanel.add(shop);
                    GameState.isPause = true;
                    GameFrame.windowKill.setVisible(false);
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        GameFrame.windowKill.addMouseListener(new EpsilonAiming());
        GameFrame.windowKill.setFocusable(true);
        GameFrame.windowKill.grabFocus();
        gameState = new GameState();
        gameLoop = new GameLoop();
        gameLoop.start();
    }

    public void AddSize(int x, int y) {
        this.setBounds(this.getX() ,this.getY() ,this.getWidth() + x ,this.getHeight() + y);
    }
}
