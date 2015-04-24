package com.example.contramini;

import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.interval.CCIntervalAction;
import org.cocos2d.actions.interval.CCJumpBy;
import org.cocos2d.actions.interval.CCMoveBy;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCScaleTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCParallaxNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCTextureCache;
import org.cocos2d.particlesystem.CCParticleExplosion;
import org.cocos2d.particlesystem.CCParticleFire;
import org.cocos2d.particlesystem.CCParticleSystem;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;

import android.view.MotionEvent;

public class GameLayer3 extends CCLayer{
	//declare a sprite;
	CCSprite player;
	CCSprite landMonster;
	
	CCSprite leftButton;
	CCSprite rightButton;
	CCSprite jumpButton;
	CCSprite shootButton;
	
	CCSprite background;
	
//	CCParticleFire emitter = CCParticleFire.node();
	
	
	// to receive user touch event , you should set this layer first.
	public GameLayer3(){
		CGSize s = CCDirector.sharedDirector().winSize();
        background = CCSprite.sprite("long.jpg");
        addChild(background, 0);
        background.setPosition(CGPoint.make(s.width/2, s.height-180));

        CCIntervalAction move = CCMoveBy.action(4, CGPoint.ccp(300, 0));
        CCIntervalAction move_back = move.reverse();
        CCIntervalAction seq = CCSequence.actions(move, move_back);
        background.runAction(CCRepeatForever.action(seq));
        
        CCParticleFire emitter = ParticleSystem.getFire(700, 500);
		addChild(emitter,6);
		emitter.setTexture(CCTextureCache.sharedTextureCache().addImage("fire.png"));
		CGPoint p = emitter.getPosition();
		emitter.setPosition(CGPoint.ccp(500, 500));
		
		CCParticleSystem emitter2 = CCParticleExplosion.node();
		addChild(emitter2,6);
		
		emitter2.setScale(3.5f);
		emitter2.setTexture(CCTextureCache.sharedTextureCache().addImage("stars_grayscale.png"));
		emitter2.setPosition(CGPoint.ccp(800, 500));
		
		CCParticleSystem emitter3 = ParticleSystem.getSnow(1000, 1100);
		//addChild(emitter3,3);
		
		CCParticleSystem emitter4 = ParticleSystem.getFirework(300,100);
		addChild(emitter4,3);
		
		CCParticleSystem emitter5 = ParticleSystem.getFlower(500, 300);
		addChild(emitter5,3);
		
		CCParticleSystem emitter6 = ParticleSystem.getGalaxy(700,300);
		addChild(emitter6,3);
		
		CCParticleSystem emitter7 = ParticleSystem.getRing(1100,300);
		addChild(emitter7,3);
		
		CCParticleSystem emitter8 = ParticleSystem.getRotFlower(1300,300);
		addChild(emitter8,3);
		
		CCParticleSystem emitter9 = ParticleSystem.getSmoke(1500,300);
		addChild(emitter9,3);
		
		CCParticleSystem emitter10 = ParticleSystem.getSun(1700,300);
		addChild(emitter10,3);
		
		CCParticleSystem emitter11 = ParticleSystem.getRain(1100, 1000);
		addChild(emitter11,9);
		
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

		
    	return true;
		
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