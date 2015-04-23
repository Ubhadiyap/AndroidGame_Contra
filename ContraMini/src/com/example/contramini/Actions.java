package com.example.contramini;

import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.interval.CCJumpBy;
import org.cocos2d.actions.interval.CCMoveBy;
import org.cocos2d.actions.interval.CCSequence;
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
		CGPoint moveForwardVetor = CGPoint.ccp(200, 0);
		CCMoveBy forwardMove = CCMoveBy.action(1.0f, moveForwardVetor);
		return forwardMove;
	}
	
	public static CCMoveBy playerMoveBackward(){
		CGPoint moveBackwardVector = CGPoint.ccp(-200, 0);
		CCMoveBy backwardMove = CCMoveBy.action(1.0f, moveBackwardVector);
		return backwardMove;
	}
}
