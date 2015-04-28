package minigame.liu.yulei.com.myapplication;

import java.util.ArrayList;

import org.cocos2d.actions.interval.CCMoveBy;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCParallaxNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

import android.view.MotionEvent;

public class PlayerControl {
	public static void touchBegin(CCSprite player, CCParallaxNode backgroundNode, CCDirector director, MotionEvent event, int GAME_START_HEIGHT, GameLayer gameLayer,ArrayList<CCSprite> buttons){
		float x = event.getX();
		float y = event.getY();
		
		CGPoint p1 = CGPoint.ccp(x, y);
		CGPoint p2 = CCDirector.sharedDirector().convertToGL(p1);
		float realX = p2.x;
		float realY = p2.y;
		System.out.println("p1.x:"+ x + ".p1.y:" + y);
		System.out.println("p2.x:"+ p2.x + ".p2.y:" + p2.y);
		System.out.println("began");
		


		
		CGPoint deltaLeft = CGPoint.ccp(-5, 0);
		CGPoint updateLeft = CGPoint.ccpAdd(backgroundNode.getPosition(), deltaLeft);
		
		float playerPositionX = player.getPosition().x;
        float playerPositionY = player.getPosition().y;
		
		if(buttons.get(0).getBoundingBox().contains(realX, realY) && playerPositionY == 390.0f){  //press left button
			if(playerPositionX > 400){
				System.out.println("player_________Xis"+playerPositionX);
                System.out.println("player_________Yis"+playerPositionY);
                System.out.println("backNode__x is " + backgroundNode.getPosition().x);
				player.runAction(Actions.playerMoveBackward());
			}
		}else if(buttons.get(1).getBoundingBox().contains(realX, realY) && playerPositionY == 390.0f){	 //press right button
			
			if(playerPositionX < 500){
				player.runAction(Actions.playerMoveForward(1.0f));
			}else{
                if(backgroundNode.getPosition().x > -10500)
				backgroundNode.runAction(CCMoveBy.action(2.0f, CGPoint.ccp(-2000, 0)));
                player.runAction(Actions.playerMoveForward(0.5f));
			}
		}else if(buttons.get(2).getBoundingBox().contains(realX, realY) && playerPositionY == 390.0f){ // press jump button

            player.runAction(Actions.playerJump(GAME_START_HEIGHT));

		}else if(buttons.get(3).getBoundingBox().contains(realX, realY)){  // press shoot button
			shoot(player,gameLayer);
		}else if(buttons.get(4).getBoundingBox().contains(realX, realY)){  // press pause
			if(director.getIsPaused() == false) director.pause();
			else director.resume();
		}
	}

    public static void touchMoved(CCSprite player, CCParallaxNode backgroundNode, MotionEvent event,ArrayList<CCSprite> buttons) {
        float x = event.getX();
        float y = event.getY();

        CGPoint p1 = CGPoint.ccp(x, y);
        CGPoint p2 = CCDirector.sharedDirector().convertToGL(p1);

        float realX = p2.x;
        float realY = p2.y;

        CGPoint deltaLeft = CGPoint.ccp(-10, 0);
        CGPoint updateLeft = CGPoint.ccpAdd(backgroundNode.getPosition(), deltaLeft);
        float playerPositionY = player.getPosition().y;
       if(buttons.get(1).getBoundingBox().contains(realX, realY)&& playerPositionY == 390.0f){   //forward move
            float playerPositionX = player.getPosition().x;
                player.runAction(Actions.playerMoveForward(0.5f));
        }
    }
	
	public static void shoot(CCSprite player, GameLayer gameLayer){
		float x =player.getPosition().x;
		float y =player.getPosition().y;
		CCSprite projectile = CCSprite.sprite("bluebullet2.png");
		gameLayer.addChild(projectile);
		CGPoint ini = CGPoint.ccp(x+30, y+30);
		projectile.setPosition(ini);
		gameLayer.projectileArray.add(projectile);
		
		CGPoint target = CGPoint.ccp(1950, y);
		CCMoveTo moveProjec = CCMoveTo.action(0.5f, target);
		projectile.runAction(moveProjec);
		
	}

}
