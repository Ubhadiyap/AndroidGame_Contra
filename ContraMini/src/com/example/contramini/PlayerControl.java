package com.example.contramini;

import java.util.ArrayList;

import org.cocos2d.actions.interval.CCMoveBy;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCParallaxNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

import android.view.MotionEvent;

public class PlayerControl {
	public static void touchBegin(CCSprite player, CCParallaxNode backgroundNode, CCDirector director, MotionEvent event, int GAME_START_HEIGHT, GameLayer2 gameLayer,ArrayList<CCSprite> buttons){
		float x = event.getX();
		float y = event.getY();
		
		CGPoint p1 = CGPoint.ccp(x, y);
		CGPoint p2 = CCDirector.sharedDirector().convertToGL(p1);
		float realX = p2.x;
		float realY = p2.y;
		System.out.println("p1.x:"+ x + ".p1.y:" + y);
		System.out.println("p2.x:"+ p2.x + ".p2.y:" + p2.y);
		System.out.println("began");
		
		float playerX =player.getPosition().x;
		//jump action
		CGPoint jumpUpVec = CGPoint.ccp(0, 200+GAME_START_HEIGHT);
		CGPoint jumpDownDestination = CGPoint.ccp(playerX, 70+GAME_START_HEIGHT);
		CCMoveBy moveUp = CCMoveBy.action(0.5f, jumpUpVec);
		CCMoveTo moveDown = CCMoveTo.action(0.5f, jumpDownDestination);
		CCSequence jumpSec = CCSequence.actions(moveUp, moveDown);
		jumpSec.setTag(1);
		
		CGPoint deltaLeft = CGPoint.ccp(-5, 0);
		CGPoint updateLeft = CGPoint.ccpAdd(backgroundNode.getPosition(), deltaLeft);
		
		float playerPositionX = player.getPosition().x;
		
		if(buttons.get(0).getBoundingBox().contains(realX, realY)){
			if(playerPositionX > 400){
				System.out.println("player_________Xis"+playerPositionX);
				player.runAction(Actions.playerMoveBackward());
			}
		}else if(buttons.get(1).getBoundingBox().contains(realX, realY)){	
			
			if(playerPositionX < 500){
				player.runAction(Actions.playerMoveForward());
			}else{
				backgroundNode.setPosition(updateLeft);
				//backgroundNode.runAction(CCMoveBy.action(2.0f, CGPoint.ccp(-400, 0)));
			}
		}else if(buttons.get(2).getBoundingBox().contains(realX, realY)){
			player.runAction(jumpSec);
		}else if(buttons.get(3).getBoundingBox().contains(realX, realY)){
			shoot(player,gameLayer);
		}else if(buttons.get(4).getBoundingBox().contains(realX, realY)){
			if(director.getIsPaused() == false) director.pause();
			else director.resume();
		}
		

//		if(realX <= 500){   //back move
//			player.runAction(Actions.playerMoveBackward());
//			
//		}else if(realX<= 1000){   //forward move
//			float playerPositionX = player.getPosition().x;
//			if(playerPositionX < 500){
//				player.runAction(Actions.playerMoveForward());
//			}else{
//				backgroundNode.setPosition(updateLeft);
//				//backgroundNode.runAction(CCMoveBy.action(2.0f, CGPoint.ccp(-400, 0)));
//			}
//		}
//		
//		if(x > 1000 && y > 500){   //jump
//			player.runAction(jumpSec);
//			
//
//		}else if(x >1000 && y < 500){   //shoot
//			shoot(player,gameLayer);
//			if(director.getIsPaused() == false) director.pause();
//			else director.resume();
			
//		}
	}
	
	public static void shoot(CCSprite player, GameLayer2 gameLayer){
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
