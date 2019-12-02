package com.exercise.lsp.interfaces.v3;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public class Parrot implements EggLayFlyTweetable {

    private EggLayableActivity layableActivity = new EggLayableActivity();
    private FlyableActivity flyableActivity = new FlyableActivity();
    private TweetableActivity tweetableActivity = new TweetableActivity();

    @Override
    public void layEgg() {
        layableActivity.layEgg();
    }

    @Override
    public void fly() {
        flyableActivity.fly();
    }

    @Override
    public void tweet() {
        tweetableActivity.tweet();
    }
}
