package com.jatj.highway.gameobjects;

/**
 * Created by knepe on 2015-03-01.
 */
public class RoadLine extends Scrollable {
    public RoadLine(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);
    }

    public void onRestart(float x, float y, float scrollSpeed) {
        position.x = x;
        position.y = y;
        velocity.x = scrollSpeed;
    }
}
