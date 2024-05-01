package controller.Threads;

import controller.Constants;
import controller.GameState;
import view.game.GameFrame;

public class FrameResizeThread extends Thread{

    GameFrame gameFrame;

    public FrameResizeThread(GameFrame gameFrame){
        this.gameFrame = gameFrame;
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 1000;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (!GameState.isPause && !GameState.isOver){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= Constants.UPS){
                Resize();
                delta = 0;
            }
        }
    }

    private void Resize() {
//        FramePressure();
        FrameResize();
    }
    private void FramePressure() {
        if (!gameFrame.isResizing()){
            gameFrame.setUpDownV(-Constants.FRAME_PRESSURE_VELOCITY ,-Constants.FRAME_PRESSURE_VELOCITY);
            gameFrame.setLeftRightV(-Constants.FRAME_PRESSURE_VELOCITY ,-Constants.FRAME_PRESSURE_VELOCITY);
        }
    }

    private void FrameResize() {
        gameFrame.move();
        gameFrame.Update();
    }
}
