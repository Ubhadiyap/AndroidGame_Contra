package com.example.contramini;

import org.cocos2d.actions.base.CCAction;
import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCJumpBy;
import org.cocos2d.actions.interval.CCMoveBy;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.actions.interval.CCSpawn;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.types.CGPoint;

public class Actions {
	public static CCRepeatForever getRepeatJump(){
		CCMoveBy move1 = CCMoveBy.action(6, CGPoint.ccp(-800, 0));
		CCJumpBy jumpBack = CCJumpBy.action(6, CGPoint.ccp(800, 0), 500	, 4);
		CCSequence seqMove = CCSequence.actions(move1, jumpBack);
		CCRepeatForever repeatMove = CCRepeatForever.action(seqMove);
		return repeatMove;
	}
	
	public static CCRepeatForever getMoveAr(){
		CCMoveBy move1 = CCMoveBy.action(2,CGPoint.ccp(-800, 0));
		CCMoveBy move2 = CCMoveBy.action(2,CGPoint.ccp(800, 0));
		CCSequence seqMove = CCSequence.actions(move1, move2);
		CCRepeatForever repeatMove = CCRepeatForever.action(seqMove);
		return repeatMove;
	}
	
	public static CCMoveBy playerMoveForward(){
		CGPoint moveForwardVetor = CGPoint.ccp(400, 0);
		CCMoveBy forwardMove = CCMoveBy.action(2.0f, moveForwardVetor);
		return forwardMove;
	}
	
	public static CCMoveBy playerHurtBack(){
		CGPoint moveBackVetor = CGPoint.ccp(-400, 0);
		CCMoveBy hurtBackMove = CCMoveBy.action(0.3f, moveBackVetor);
		return hurtBackMove;
	}
	
	public static CCMoveBy playerMoveBackward(){
		CGPoint moveBackwardVector = CGPoint.ccp(-400, 0);
		CCMoveBy backwardMove = CCMoveBy.action(2.0f, moveBackwardVector);
		return backwardMove;
	}
	
	public static CCRepeatForever player1Animation(){
		CCAnimation animation = CCAnimation.animation("move", 0.2f);
		
		animation.addFrame("raw_bluePlayer1.png");
		animation.addFrame("raw_bluePlayer2.png");
		animation.addFrame("raw_bluePlayer3.png");
		animation.addFrame("raw_bluePlayer4.png");
		
		CCAnimate scrPprAction = CCAnimate.action(1.0f, animation, false);
		CCRepeatForever repeatMove = CCRepeatForever.action(scrPprAction);
		return repeatMove;
	}
	
	public static CCSpawn player1Hurt(){
		
		CCAnimation animation = CCAnimation.animation("hurt", 0.5f);
			
		animation.addFrame("raw_bluePlayer5.png");
		animation.addFrame("raw_bluePlayer6.png");
		CCAnimate scrPprAction = CCAnimate.action(1.0f, animation, false);
		CCSpawn hurtBack = CCSpawn.actions(Actions.playerHurtBack(),scrPprAction);
		return hurtBack;
	}
}
