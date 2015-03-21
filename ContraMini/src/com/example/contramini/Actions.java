package com.example.contramini;

import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.interval.CCJumpBy;
import org.cocos2d.actions.interval.CCMoveBy;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.types.CGPoint;

public class Actions {
	public static CCRepeatForever getRepeatJump(){
		CCMoveBy move1 = CCMoveBy.action(2, CGPoint.ccp(-800, 0));
		CCJumpBy jumpBack = CCJumpBy.action(2, CGPoint.ccp(800, 0), 500	, 4);
		CCSequence seqMove = CCSequence.actions(move1, jumpBack);
		CCRepeatForever repeatMove = CCRepeatForever.action(seqMove);
		return repeatMove;
	}

}
