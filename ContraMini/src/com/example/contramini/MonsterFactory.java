package com.example.contramini;

import org.cocos2d.nodes.CCSprite;

public class MonsterFactory {
	public static CCSprite getFlyOctor(){
		CCSprite landMonster1 = CCSprite.sprite("flyoctor.png");
		landMonster1.setScale(0.3f);
		landMonster1.runAction(Actions.getRepeatJump());
		return landMonster1;
	}
	
//	public static CCSprite getBanshou(){
//		CCSprite banshou = CCSprite.sprite("banshou.png");
//		banshou.runAction(Actions.getMoveAr());
//		return banshou;
//	}
	
	public static CCSprite getPirate(){
		CCSprite pirate = CCSprite.sprite("priate1.png");		
		pirate.runAction(Actions.getMoveAr(3.0f, 800));
		pirate.runAction(Actions.GetpirateAction());
		return pirate;
	}
	
	public static CCSprite getBlueZambie(){
		CCSprite zambie = CCSprite.sprite("BlueZambie1.png");		
		zambie.runAction(Actions.getMoveAr(3.0f, 800));
		zambie.runAction(Actions.GetBlueZambieAction());
		return zambie;
	}

}
