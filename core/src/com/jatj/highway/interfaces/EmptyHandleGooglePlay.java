package com.jatj.highway.interfaces;

/**
 * Created by knepe on 2015-03-01.
 */
public class EmptyHandleGooglePlay implements IHandleGooglePlay{
    @Override
    public void login() {

    }

    @Override
    public void logout() {

    }

    @Override
    public boolean getSignedIn() {
        return false;
    }

    @Override
    public void submitScore(int score) {

    }

    @Override
    public void showLeaderboard() {

    }

    @Override
    public void unlockAchievement(String id) {

    }

    @Override
    public void showAchievements() {

    }
}
