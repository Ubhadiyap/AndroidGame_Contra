package com.example.contramini;

import java.util.ArrayList;
import java.util.Iterator;

import org.cocos2d.actions.base.CCAction;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCMoveBy;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCParallaxNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrameCache;
import org.cocos2d.nodes.CCTextureCache;
import org.cocos2d.particlesystem.CCParticleSystem;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;
import org.cocos2d.utils.CCFormatter;

import android.view.MotionEvent;

/**
 * @author alex
 *
 */
public class GameLayer2 extends CCLayer{
	CCSprite player;
	CCSprite landMonster1;
	CCSprite landMonster2;
	CCSprite back;
	CCParallaxNode backgroundNode;
	CCSprite healthBar;
	
	
	//projectile array
	ArrayList<CCSprite> projectileArray;
	
	//monster array
	ArrayList<CCSprite> monsterArray;
	
	//control buttons
	CCSprite leftButton;
	CCSprite rightButton;
	CCSprite jumpButton;
	CCSprite shootButton;
	
	final int GAME_START_HEIGHT = 300;
	
	
	public GameLayer2(){
		// enable touch operation
		this.setIsTouchEnabled(true);
		//set control buttons
		leftButton = CCSprite.sprite("leftbutton.png");
		rightButton = CCSprite.sprite("rightbutton.png");
		jumpButton = CCSprite.sprite("jumpW.png");
		shootButton = CCSprite.sprite("shootW.png");
		
		projectileArray = new ArrayList<CCSprite>();
		monsterArray = new ArrayList<CCSprite>();
		
		CGPoint LBPosition = CGPoint.ccp(250, 150);
		CGPoint RBPosition = CGPoint.ccp(750, 150);
		CGPoint JBPosition = CGPoint.ccp(1500, 150);
		CGPoint SBPosition = CGPoint.ccp(1500, 600);
		
		leftButton.setPosition(LBPosition);
		rightButton.setPosition(RBPosition);
		jumpButton.setPosition(JBPosition);
		shootButton.setPosition(SBPosition);
		
		leftButton.setOpacity(100);
		rightButton.setOpacity(100);
		jumpButton.setOpacity(100);
		shootButton.setOpacity(100);
		
		//add control buttons
		
		this.addChild(leftButton,2);
		this.addChild(rightButton,2);
		this.addChild(jumpButton,2);
		this.addChild(shootButton,2);
		// set the player
		player = CCSprite.sprite("contrachar.png");
		player.setPosition(100, 90+GAME_START_HEIGHT);
		player.setScale(0.5f);
		player.runAction(Actions.player1Animation());

		//weather is rain
		CCParticleSystem emitter11 = ParticleSystem.getRain(1100, 1000);
		addChild(emitter11,9);
		
		//set health bar
		healthBar = CCSprite.sprite("HealthBar.png");
		healthBar.setPosition(200, 1000);
		
		// set monster
		landMonster1 = CCSprite.sprite("goomba2.png");
		landMonster1.setScale(0.3f);
		landMonster1.runAction(Actions.getMoveAr());
		landMonster2 = CCSprite.sprite("goomba2.png");
		landMonster2.setScale(0.3f);
		back = CCSprite.sprite("long.jpg");
		back.setScale(2.3f);
		backgroundNode = CCParallaxNode.node();
		CGSize winsize = CCDirector.sharedDirector().winSize();
		
		
		float backHeight = back.getBoundingBox().size.height;
		float backWidth = back.getBoundingBox().size.width;
		
		float landMonsterWidth = landMonster1.getBoundingBox().size.width;
		float landMonsterHeight =landMonster1.getBoundingBox().size.height;
		
		// 3) Determine relative movement speeds for space dust and background
		CGPoint monsterSpeed = CGPoint.ccp(0.1f, 0.1f);
		CGPoint bgSpeed = CGPoint.ccp(0.05f, 0.05f);
		
		backgroundNode.addChild(back, -1, 0.1f, 0.1f, backWidth/2, backHeight/2+GAME_START_HEIGHT);
		backgroundNode.addChild(landMonster1, 0, 0.1f, 0.1f, 800f, landMonsterHeight/2+GAME_START_HEIGHT);
		backgroundNode.addChild(landMonster2, 0, 0.1f, 0.1f, 2200f, landMonsterHeight/2+GAME_START_HEIGHT);
		this.addChild(player, 0);
		this.addChild(backgroundNode, -1);
		
		//add health bar
		this.addChild(healthBar,0);
		
		//add fly octor
		CCSprite flyoctor = MonsterFactory.getFlyOctor();
		backgroundNode.addChild(flyoctor,0,0.1f,0.1f,1500f,flyoctor.getBoundingBox().size.height/2+GAME_START_HEIGHT);
		
		//add banshou
		CCSprite banshou = MonsterFactory.getBanshou();
		backgroundNode.addChild(banshou,0,0.1f,0.1f,4000f,banshou.getBoundingBox().size.height/2+GAME_START_HEIGHT);
		
		CCSprite fly2 = MonsterFactory.getFlyOctor();
		backgroundNode.addChild(fly2,0,0.1f,0.1f,18000f,fly2.getBoundingBox().size.height/2+GAME_START_HEIGHT);
		monsterArray.add(fly2);
		//add monsters to monster array.   
		monsterArray.add(landMonster1);
		monsterArray.add(landMonster2);
		monsterArray.add(flyoctor);
		monsterArray.add(banshou);
		
		
		
		this.schedule("update");
		
		
	}
	
	@Override
	public boolean ccTouchesBegan(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		
		CGPoint p1 = CGPoint.ccp(x, y);
		CGPoint p2 = CCDirector.sharedDirector().convertToGL(p1);
		float realX = p2.x;
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
		
		
		CGPoint deltaLeft = CGPoint.ccp(-50, 0);
		CGPoint updateLeft = CGPoint.ccpAdd(backgroundNode.getPosition(), deltaLeft);

		if(realX <= 500){   //back move
			player.runAction(Actions.playerMoveBackward());
			
		}else if(realX<= 1000){   //forward move
			float playerPositionX = player.getPosition().x;
			if(playerPositionX < 1000){
				player.runAction(Actions.playerMoveForward());
			}else{
				backgroundNode.setPosition(updateLeft);
			}
		}
		
		if(x > 1000 && y > 500){   //jump
			player.runAction(jumpSec);
			

		}else if(x >1000 && y < 500){   //shoot
			this.shoot();
		}
		return super.ccTouchesBegan(event);
	}
	


	@Override
	public boolean ccTouchesMoved(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		
		CGPoint p1 = CGPoint.ccp(x, y);
		CGPoint p2 = CCDirector.sharedDirector().convertToGL(p1);

		float realX = p2.x;
		float realY = p2.y;
				
		CGPoint deltaLeft = CGPoint.ccp(-100, 0);
		CGPoint updateLeft = CGPoint.ccpAdd(backgroundNode.getPosition(), deltaLeft);

		if(realX <= 500){   //back move
			player.runAction(Actions.playerMoveBackward());
			
		}else if(realX<= 1000){   //forward move
			float playerPositionX = player.getPosition().x;
			if(playerPositionX < 1000){
				player.runAction(Actions.playerMoveForward());
			}else{
				backgroundNode.setPosition(updateLeft);
			}
		}

		return super.ccTouchesMoved(event);
	}
	
	public void shoot(){
		float x =player.getPosition().x;
		float y =player.getPosition().y;
		CCSprite projectile = CCSprite.sprite("bluebullet2.png");
		this.addChild(projectile);
		CGPoint ini = CGPoint.ccp(x+30, y+30);
		projectile.setPosition(ini);
		this.projectileArray.add(projectile);
		
		CGPoint target = CGPoint.ccp(1950, y);
		CCMoveTo moveProjec = CCMoveTo.action(0.5f, target);
		projectile.runAction(moveProjec);

		
		
	}
	
	public void update(float dt){
		Iterator<CCSprite> proIterator = this.projectileArray.iterator();
		while(proIterator.hasNext()){
			CCSprite projectile = proIterator.next();
			if(projectile.getPosition().x > 1900){
				this.removeChild(projectile, true);
				proIterator.remove();
			}
		}
		
		// shoot monster detection
		Iterator<CCSprite> monIterator = this.monsterArray.iterator();
		while(monIterator.hasNext()){
			CCSprite monster = monIterator.next();
			CGPoint monsterAbsoPosition = monster.convertToWorldSpace(0, 0);
			CGRect monsterRect = CGRect.make(monsterAbsoPosition.x - (monster.getContentSize().width / 2.0f),
					monsterAbsoPosition.y - (monster.getContentSize().height / 2.0f),
					monster.getContentSize().width,
					monster.getContentSize().height);
//			for(CCSprite projectile:this.projectileArray){
//				CGRect projectileRect = CGRect.make(projectile.getPosition().x - (projectile.getContentSize().width / 2.0f),
//	                    projectile.getPosition().y - (projectile.getContentSize().height / 2.0f),
//	                    projectile.getContentSize().width,
//	                    projectile.getContentSize().height);
//			
//
//				if(CGRect.intersects(monsterRect, projectileRect)){
//					System.out.println("SSSSSSSSSSSSSSSS");
//					CGPoint position = monster.getPosition();
//					this.addChild(ParticleSystem.getFire(position.x, position.x), 5);
//					monster.removeSelf();
//					monIterator.remove();
//				
//			}
//		}
			Iterator<CCSprite> projectIterator = this.projectileArray.iterator();
			while(projectIterator.hasNext()){
				CCSprite projectile = projectIterator.next();
				CGRect projectileRect = CGRect.make(projectile.getPosition().x - (projectile.getContentSize().width / 2.0f),
                projectile.getPosition().y - (projectile.getContentSize().height / 2.0f),
                projectile.getContentSize().width,
                projectile.getContentSize().height);
	

			if(CGRect.intersects(monsterRect, projectileRect)){
			
				CGPoint position = monster.convertToWorldSpace(0, 0);
				this.addChild(ParticleSystem.getFire(position.x, position.x), 5);
				System.out.println(position.x+"_______"+ position.y);
				//monster.removeSelf();
				backgroundNode.removeChild(monster, true);
				monIterator.remove();
				projectile.removeSelf();
				projectIterator.remove();
			}
		  }			
		}
		
		// player hurt detection
		CGRect playerRect = CGRect.make(player.getPosition().x - (player.getContentSize().width / 2.0f),
				player.getPosition().y - (player.getContentSize().height / 2.0f),
				player.getContentSize().width,
				player.getContentSize().height);
		
		for(CCSprite monster: monsterArray){
			CGPoint monsterAbsoPosition = monster.convertToWorldSpace(0, 0);
			CGRect monsterRect = CGRect.make(monsterAbsoPosition.x - (monster.getContentSize().width / 2.0f),
					monsterAbsoPosition.y - (monster.getContentSize().height / 2.0f),
					monster.getContentSize().width/4,
					monster.getContentSize().height/4);
			if(CGRect.intersects(monsterRect, playerRect)){
				//player.stopAllActions();
				player.runAction(Actions.playerHurtBack());
				//player.runAction(Actions.playerHurtBack());
				//player.runAction(Actions.player1Animation());
				
			}
			
		}

	

	}
}
