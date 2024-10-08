package view.game;
import controller.*;
import controller.Threads.FrameResizeThread;
import controller.Threads.GameLoop;
import controller.actionlisteners.EpsilonAiming;
import controller.actionlisteners.EpsilonCirculation;
import controller.actionlisteners.EpsilonMovement;
import view.Abilities.RegularAbility;
import view.Abilities.SpecialAbility;
import view.PIG;
import view.objectsView.OIGView;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class WindowKill extends PIG {
    static GameLoop gameLoop;
    static FrameResizeThread frameResizeThread;
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
        Controller.startGame();
        GameFrame.windowKill.setVisible(true);
        frameResizeThread = new FrameResizeThread(Application.gameFrame);
        frameResizeThread.start();
        Controller.gameStartAnimation();
        this.grabFocus();
    }

    @Override
    public void end() {
        this.setVisible(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.MAGENTA);
        g.setFont(new Font(null,Font.BOLD ,10));
        g.drawString("XP: " + (int)GameState.xp ,3 ,20);
        g.setColor(Color.GREEN);
        g.drawString("HP: " + (int)GameState.hp ,58 ,20);
        g.setColor(Color.RED);
        g.drawString("Wave: " +(int)GameState.wave ,103 ,20);
        g.setColor(Color.WHITE);
        g.drawString("Time: " + (int)GameState.time ,151 ,20);
        Graphics2D g2d = (Graphics2D)g;
        for (int i = 0 ;i < OIGView.OIGs.size() ;i++){
            OIGView.OIGs.get(i).draw(g2d);
        }
    }

    public void startGame(){
        GameFrame.windowKill.setFocusable(true);
        GameFrame.windowKill.addKeyListener(new EpsilonMovement(Controller.getEpsilon()));
        GameFrame.windowKill.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == 'p' && !GameState.isPause && !GameState.hasWon){
                    ShopFrame shopFrame = new ShopFrame();
                    shop = new Shop(shopFrame);
                    shopFrame.add(shop);
                    GameState.isPause = true;
                }
                else if (e.getKeyChar() == 'q' && !GameState.isPause){
                    SpecialAbility.sendRequest(SpecialAbilitiesEnum.ares);
                }
                else if (e.getKeyChar() == 'w' && !GameState.isPause){
                    SpecialAbility.sendRequest(SpecialAbilitiesEnum.aceso);
                }
                else if (e.getKeyChar() == 'e' && !GameState.isPause){
                    SpecialAbility.sendRequest(SpecialAbilitiesEnum.proteus);
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
        GameFrame.windowKill.addMouseMotionListener(new EpsilonCirculation());
        GameFrame.windowKill.grabFocus();
        gameState = new GameState();
        gameLoop = new GameLoop(Application.gameFrame);
        gameLoop.start();
    }

}
