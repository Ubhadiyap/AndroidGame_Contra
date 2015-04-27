package minigame.liu.yulei.com.myapplication;

import java.util.Random;

import org.cocos2d.actions.base.CCAction;
import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCBlink;
import org.cocos2d.actions.interval.CCJumpBy;
import org.cocos2d.actions.interval.CCMoveBy;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCScaleBy;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.actions.interval.CCSpawn;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

public class Actions {
	public static CCRepeatForever getRepeatJump(){
		CCMoveBy move1 = CCMoveBy.action(6, CGPoint.ccp(-800, 0));
		CCJumpBy jumpBack = CCJumpBy.action(6, CGPoint.ccp(800, 0), 500	, 4);
		CCSequence seqMove = CCSequence.actions(move1, jumpBack);
		CCRepeatForever repeatMove = CCRepeatForever.action(seqMove);
		return repeatMove;
	}
	
	public static CCRepeatForever getMoveAr(float oneRoundTime, float distance){
		Random random = new Random();
		float delta = random.nextFloat()*800;
		CCMoveBy move1 = CCMoveBy.action(oneRoundTime,CGPoint.ccp(-1*distance+delta, 0));
		CCMoveBy move2 = CCMoveBy.action(oneRoundTime,CGPoint.ccp(distance-delta, 0));
		CCSequence seqMove = CCSequence.actions(move1, move2);
		CCRepeatForever repeatMove = CCRepeatForever.action(seqMove);
		return repeatMove;
	}
	
	public static CCSequence getOneRoundMove(float oneRoundTime, float distance){
		CCMoveBy move1 = CCMoveBy.action(oneRoundTime,CGPoint.ccp(-1*distance, 0));
		CCMoveBy move2 = CCMoveBy.action(oneRoundTime,CGPoint.ccp(distance, 0));
		CCSequence seqMove = CCSequence.actions(move1, move2);
		return seqMove;
	}
	
	public static CCMoveBy playerMoveForward(float speed){
		CGPoint moveForwardVetor = CGPoint.ccp(400*speed, 0);
		CCMoveBy forwardMove = CCMoveBy.action(2.0f, moveForwardVetor);
		forwardMove.setTag(1);
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
		backwardMove.setTag(1);
		
		return backwardMove;
	}

    public static CCJumpBy playerJump(int GAME_START_HEIGHT){
        //jump action

//        float playerX =player.getPosition().x;
//        CGPoint jumpUpVec = CGPoint.ccp(0, 200+GAME_START_HEIGHT);
//        CGPoint jumpDownDestination = CGPoint.ccp(playerX, 70+GAME_START_HEIGHT);
//        CCMoveBy moveUp = CCMoveBy.action(0.5f, jumpUpVec);
//        CCMoveTo moveDown = CCMoveTo.action(0.5f, jumpDownDestination);
//        CCSequence jumpSec = CCSequence.actions(moveUp, moveDown);
//        jumpSec.setTag(1);
        CCJumpBy jump = CCJumpBy.action(1.0f,CGPoint.ccp(0,0),300,1);
        return jump;
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
	
	public static void loseHealth(CCSprite player,int GAME_START_HEIGHT){
		//player.stopAllActions();
		CCAnimation animation = CCAnimation.animation("hurt", 0.1f);
		animation.addFrame("raw_bluePlayer5.png");
		animation.addFrame("raw_bluePlayer6.png");
		CCAnimate scrPprAction = CCAnimate.action(0.8f, animation, false);
		CCBlink blink = CCBlink.action(0.8f, 5);
		CCSpawn hurtSpawn = CCSpawn.actions(blink,scrPprAction);
		CCMoveTo moveToStartPoint = CCMoveTo.action(0.0f, CGPoint.ccp(100, 1100));
		CCMoveTo mt = CCMoveTo.action(0.5f, CGPoint.ccp(100, 90+GAME_START_HEIGHT));
		CCSequence seq = CCSequence.actions(hurtSpawn, moveToStartPoint,mt);
		player.runAction(seq);
		
	}
	
	// heathBar scale
	public static CCScaleBy adjustHealthBar(int delta,float health){
		CCScaleBy scale = CCScaleBy.action(0.2f, health/(health+1), 1.0f);
		return scale;
	}
	
	public static CCRepeatForever getpirateAnimation(){
		CCAnimation animation = CCAnimation.animation("pirate", 0.2f);
		
		for(int i =1 ; i<= 8; i++){
			animation.addFrame("priate"+i+".png");
		}


		CCAnimate scrPprAction = CCAnimate.action(2.0f, animation, false);
		//CCSequence moveAround = getOneRoundMove(3.0f, 800f);
		//CCSpawn spawn = CCSpawn.actions(scrPprAction, moveAround);
		CCRepeatForever repeatMove = CCRepeatForever.action(scrPprAction);
		return repeatMove;
	}
	
	public static CCRepeatForever getBlueZambieAnimation(){
		CCAnimation animation = CCAnimation.animation("blueZambie", 0.2f);
		
		for(int i =1 ; i<= 17; i++){
			animation.addFrame("BlueZambie"+i+".png");
		}


		CCAnimate scrPprAction = CCAnimate.action(2.0f, animation, false);
		//CCSequence moveAround = getOneRoundMove(3.0f, 800f);
		//CCSpawn spawn = CCSpawn.actions(scrPprAction, moveAround);
		CCRepeatForever repeatMove = CCRepeatForever.action(scrPprAction);
		return repeatMove;
	}
	
	public static CCRepeatForever getMS5Animation(){
		CCAnimation animation = CCAnimation.animation("blueZambie", 0.2f);
		
		for(int i =1 ; i<= 17; i++){
			animation.addFrame("MS5_"+i+".png");
		}


		CCAnimate scrPprAction = CCAnimate.action(2.0f, animation, false);
		//CCSequence moveAround = getOneRoundMove(3.0f, 800f);
		//CCSpawn spawn = CCSpawn.actions(scrPprAction, moveAround);
		CCRepeatForever repeatMove = CCRepeatForever.action(scrPprAction);
		return repeatMove;
	}

    public static CCRepeatForever getBoss1Animation(){
        CCAnimation animation = CCAnimation.animation("blueZambie", 0.2f);

        for(int i =1 ; i<= 17; i++){
            animation.addFrame("level1boss"+i+".png");
        }


        CCAnimate scrPprAction = CCAnimate.action(4.0f, animation, false);
        //CCSequence moveAround = getOneRoundMove(3.0f, 800f);
        //CCSpawn spawn = CCSpawn.actions(scrPprAction, moveAround);
        CCRepeatForever repeatMove = CCRepeatForever.action(scrPprAction);
        return repeatMove;
    }
}
