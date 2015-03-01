package com.jatj.highway.gameobjects;

import com.jatj.highway.gameworld.GameWorld;

/**
 * Created by knepe on 2015-03-01.
 */
public class ScrollHandler {
    private RoadLine roadLine;
    public static final int SCROLL_SPEED = -150;
    public static final int PIPE_GAP = 49;
    private float yPos;

    private GameWorld gameWorld;

    public ScrollHandler(GameWorld gameWorld, float yPos) {
        this.gameWorld = gameWorld;
        this.yPos = yPos;
        roadLine = new RoadLine(193, yPos, 53, 28, SCROLL_SPEED);
    }

    public void updateReady(float delta) {

        roadLine.update(delta);

        // Same with sand
        if (roadLine.isScrolledLeft()) {
            roadLine.reset(193, yPos);

        }

    }

    public void update(float delta) {
        // Update our objects
        roadLine.update(delta);

        //update gameobjects here (like trees, cars etc)

        //check if they are scrolled left, if so .reset

        // Same with sand
        if (roadLine.isScrolledLeft()) {
            roadLine.reset(193, yPos);

        }
    }

    public void stop() {
        roadLine.stop();
        //stop all objects
    }

    private void addScore(int increment) {
        gameWorld.addScore(increment);
    }

    public RoadLine getRoadLine() {
        return roadLine;
    }

    public void onRestart() {
        roadLine.onRestart(193, yPos, SCROLL_SPEED);
    }
}
