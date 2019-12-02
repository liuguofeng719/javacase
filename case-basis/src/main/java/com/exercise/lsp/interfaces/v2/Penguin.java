package com.exercise.lsp.interfaces.v2;

import com.exercise.lsp.interfaces.EggLayable;
import com.exercise.lsp.interfaces.Tweetable;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public class Penguin implements Tweetable, EggLayable {

    private EggLayableActivity layableActivity = new EggLayableActivity();
    private TweetableActivity tweetableActivity = new TweetableActivity();

    @Override
    public void layEgg() {
        layableActivity.layEgg();
    }

    @Override
    public void tweet() {
        tweetableActivity.tweet();
    }
}
