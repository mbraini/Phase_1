package model.interfaces;

import controller.helper.Vector;

public interface HasVertices {
    void UpdateVertices(double xMoved ,double yMoved ,double theta);
    void MoveVertices(Vector trans);
}
