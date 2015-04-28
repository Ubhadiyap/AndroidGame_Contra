package minigame.liu.yulei.com.myapplication;

import org.cocos2d.actions.CCProgressTimer;
import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.interval.CCJumpBy;
import org.cocos2d.actions.interval.CCMoveBy;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCScaleTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.nodes.CCParallaxNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.particlesystem.CCParticleSystem;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.ccColor3B;

import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GameLayer extends CCLayer{

    //projectile array
    ArrayList<CCSprite> projectileArray;
    CCLabel scoreLabel;
    CCLabel levelLabel;
    int score = 0;

    public GameLayer(){
        // enable touch operation
        this.levelLabel = CCLabel.makeLabel("Current Level:  ","DroidSans",50);
        levelLabel.setColor(ccColor3B.ccBLACK);
        levelLabel.setPosition(CGPoint.ccp(600,1100));
        addChild(levelLabel);
        this.scoreLabel = CCLabel.makeLabel("Score:  ","DroidSans",50 );
        scoreLabel.setColor(ccColor3B.ccBLACK);
        scoreLabel.setPosition(CGPoint.ccp(1100, 1100));
        addChild(scoreLabel);
        this.setIsTouchEnabled(true);
        projectileArray = new ArrayList<CCSprite>();
    }

    public ArrayList<CCSprite> getProjectileArray(){
        return projectileArray;
    }

}
