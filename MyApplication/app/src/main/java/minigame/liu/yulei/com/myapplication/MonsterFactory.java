package minigame.liu.yulei.com.myapplication;

import org.cocos2d.nodes.CCSprite;

public class MonsterFactory {
	
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
	
	public static CCSprite getMS5(){
		CCSprite ms5 = CCSprite.sprite("MS5_1.png");
		
		ms5.runAction(Actions.GetMS5Action());
		return ms5;
	}

}
