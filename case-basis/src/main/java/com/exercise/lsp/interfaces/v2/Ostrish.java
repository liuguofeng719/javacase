package com.exercise.lsp.interfaces.v2;

import com.exercise.lsp.interfaces.EggLayable;
import com.exercise.lsp.interfaces.Tweetable;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public class Ostrish implements EggLayable, Tweetable {

    // 组合
    private EggLayableActivity eggLayableActivity = new EggLayableActivity();
    // 组合
    private TweetableActivity tweetableActivity = new TweetableActivity();

    @Override
    public void layEgg() {
        // 委托
        eggLayableActivity.layEgg();
    }

    @Override
    public void tweet() {
        // 委托
        tweetableActivity.tweet();
    }
}
