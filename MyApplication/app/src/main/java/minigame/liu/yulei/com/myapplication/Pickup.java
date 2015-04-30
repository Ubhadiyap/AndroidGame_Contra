package minigame.liu.yulei.com.myapplication;

import org.cocos2d.nodes.CCParallaxNode;
import org.cocos2d.nodes.CCSprite;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by alex on 2015/4/28.
 */
public class Pickup {
    public static CCSprite getCoin(){
        CCSprite coin = CCSprite.sprite("coin.png");
        return coin;
    }
}
