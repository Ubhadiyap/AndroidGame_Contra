package com.example.contramini;

import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.interval.CCJumpBy;
import org.cocos2d.actions.interval.CCMoveBy;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCScaleTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCParallaxNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

import android.view.MotionEvent;

public class GameLayer3 extends CCLayer{
	//declare a sprite;
	CCSprite player;
	CCSprite landMonster;
	
	CCSprite leftButton;
	CCSprite rightButton;
	CCSprite jumpButton;
	CCSprite shootButton;
	
	
	// to receive user touch event , you should set this layer first.
	public GameLayer3(){
		
		
	}

	// when user begin to touch, execute this method.
	@Override
	public boolean ccTouchesBegan(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		
		CGPoint p1 = CGPoint.ccp(x, y);
		CGPoint p2 = CCDirector.sharedDirector().convertToGL(p1);
		System.out.println("p1.x:"+ x + ".p1.y:" + y);
		System.out.println("p2.x:"+ p2.x + ".p2.y:" + p2.y);
		System.out.println("began");
		
//		float playerX =player.getPosition().x;
//		//jump action
//		CGPoint jumpUpVec = CGPoint.ccp(0, 200);
//		CGPoint jumpDownDestination = CGPoint.ccp(playerX, 500);
//		CCMoveBy moveUp = CCMoveBy.action(0.5f, jumpUpVec);
//		CCMoveTo moveDown = CCMoveTo.action(0.5f, jumpDownDestination);
//		CCSequence jumpSec = CCSequence.actions(moveUp, moveDown);
//		
//		
//		if(x > 1000 && y > 500){
//			//player.runAction(jumpSec);
//		}else if(x >1000 && y < 500){
//			//this.shoot();
//		}
		return super.ccTouchesBegan(event);
	}

	
	// when user finger leave screen, execute this method.
	@Override
	public boolean ccTouchesEnded(MotionEvent event) {
		// TODO Auto-generated method stub

		System.out.println("end");
		return super.ccTouchesEnded(event);
	}
	
	//when user finger moved in screen, execute this method.
	@Override
	public boolean ccTouchesMoved(MotionEvent event) {
		// TODO Auto-generated method stub
		System.out.println("move");
//		float x = event.getX();
//		float y = event.getY();
//		
//		CGPoint p1 = CGPoint.ccp(x, y);
//		CGPoint p2 = CCDirector.sharedDirector().convertToGL(p1);
//		float realX = p2.x;
//		float realY = p2.y;
//		
//		CGPoint moveForwardVetor = CGPoint.ccp(50, 0);
//		CGPoint moveBackwardVector = CGPoint.ccp(-50, 0);
//		
//		
//		CCMoveBy forwardMove = CCMoveBy.action(0.2f, moveForwardVetor);
//		CCMoveBy backwardMove = CCMoveBy.action(0.2f, moveBackwardVector);
//		
//		
//		if(realX <= 500){
//			player.runAction(backwardMove);
//			
//		}else if(realX<= 1000){
//			
//			player.runAction(forwardMove);
//		}

		
		return super.ccTouchesMoved(event);
	}
	


}