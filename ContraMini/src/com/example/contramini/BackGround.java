package com.example.contramini;

import java.util.ArrayList;

import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCParallaxNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;

public class BackGround {
	private ArrayList<CCSprite> monsterArray;
	private CCParallaxNode backgroundNode;
	
	private BackGround(ArrayList<CCSprite> monsterArray, CCParallaxNode backgroundNode){
		this.monsterArray = monsterArray;
		this.backgroundNode = backgroundNode;
	}
	
	public static BackGround getLevel1Background(int GAME_START_HEIGHT){
		ArrayList<CCSprite> monsterArray = new ArrayList<CCSprite>();
		CCParallaxNode backgroundNode = CCParallaxNode.node();
		CCSprite back = CCSprite.sprite("long.jpg");
		back.setScale(2.3f);
		float backHeight = back.getBoundingBox().size.height;
		float backWidth = back.getBoundingBox().size.width;
		backgroundNode.addChild(back, -1, 1.0f, 1.0f, backWidth/2, backHeight/2+GAME_START_HEIGHT);
		

		
		CCSprite pirate1 = MonsterFactory.getPirate();
		CCSprite pirate2 = MonsterFactory.getPirate();
		CCSprite pirate3 = MonsterFactory.getPirate();
		CCSprite pirate4 = MonsterFactory.getPirate();
		CCSprite pirate5 = MonsterFactory.getPirate();
		float pirate1Width = pirate1.getBoundingBox().size.width;
		float pirate1Height =pirate1.getBoundingBox().size.height;
		
		backgroundNode.addChild(pirate1, 0, 1.0f, 1.0f, 2000f, pirate1Height/2+GAME_START_HEIGHT);
		backgroundNode.addChild(pirate2, 0, 1.0f, 1.0f, 2400f, pirate1Height/2+GAME_START_HEIGHT);
		backgroundNode.addChild(pirate3, 0, 1.0f, 1.0f, 2500f, pirate1Height/2+GAME_START_HEIGHT);
		backgroundNode.addChild(pirate4, 0, 1.0f, 1.0f, 2800f, pirate1Height/2+GAME_START_HEIGHT);
		backgroundNode.addChild(pirate5, 0, 1.0f, 1.0f, 3000f, pirate1Height/2+GAME_START_HEIGHT);
		
		monsterArray.add(pirate1);
		monsterArray.add(pirate2);
		monsterArray.add(pirate3);
		monsterArray.add(pirate4);
		monsterArray.add(pirate5);
		
		CCSprite zambie1 = MonsterFactory.getBlueZambie();
		CCSprite zambie2 = MonsterFactory.getBlueZambie();
		float zambie1Width = pirate1.getBoundingBox().size.width;
		float zambie1Height =pirate1.getBoundingBox().size.height;
		
		backgroundNode.addChild(zambie1, 0, 1.0f, 1.0f, 3500f, zambie1Height/2+GAME_START_HEIGHT);
		backgroundNode.addChild(zambie2, 0, 1.0f, 1.0f, 2400f, zambie1Height/2+GAME_START_HEIGHT);
		
		monsterArray.add(zambie2);
		monsterArray.add(zambie1);
		
//		for(CCSprite priate:monsterArray){
//			priate.runAction(Actions.getMoveAr(3.0f, 800));
//			priate.runAction(Actions.GetpirateAction());
//		}
		//
//		CCSprite landMonster1 = CCSprite.sprite("goomba2.png");
//		landMonster1.setScale(3.0f);
//		landMonster1.runAction(Actions.getMoveAr());
//		CCSprite landMonster2 = CCSprite.sprite("goomba2.png");
//		landMonster2.setScale(0.3f);

		
//		CGSize winsize = CCDirector.sharedDirector().winSize();
//		

//		

		
		// 3) Determine relative movement speeds for space dust and background
//		CGPoint monsterSpeed = CGPoint.ccp(0.1f, 0.1f);
//		CGPoint bgSpeed = CGPoint.ccp(0.05f, 0.05f);

//		backgroundNode.addChild(landMonster1, 0, 0.1f, 0.1f, 2000f, landMonsterHeight/2+GAME_START_HEIGHT);
//		backgroundNode.addChild(landMonster2, 0, 0.1f, 0.1f, 2200f, landMonsterHeight/2+GAME_START_HEIGHT);
//		CCSprite banshou = MonsterFactory.getBanshou();
//		backgroundNode.addChild(banshou,0,0.1f,0.1f,4000f,banshou.getBoundingBox().size.height/2+GAME_START_HEIGHT);
//		
//		CCSprite fly2 = MonsterFactory.getFlyOctor();
//		backgroundNode.addChild(fly2,0,0.1f,0.1f,18000f,fly2.getBoundingBox().size.height/2+GAME_START_HEIGHT);
//		monsterArray.add(fly2);
//		//add monsters to monster array.   
//		monsterArray.add(landMonster1);
//		monsterArray.add(landMonster2);
//		//monsterArray.add(flyoctor);
//		monsterArray.add(banshou);
		return new BackGround(monsterArray,backgroundNode);
	}

	public ArrayList<CCSprite> getMonsterArray() {
		return monsterArray;
	}

	public CCParallaxNode getBackgroundNode() {
		return backgroundNode;
	}
	
}
