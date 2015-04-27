package minigame.liu.yulei.com.myapplication;

import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

public class MonsterFactory {
	
	public static CCSprite getPirate(int GAME_START_HEIGHT){
		CCSprite pirate = CCSprite.sprite("priate1.png");
		pirate.runAction(Actions.getMoveAr(3.0f, 900));
        float pirate1Height =pirate.getBoundingBox().size.height;
        pirate.setPosition( 2000f, pirate1Height/2+GAME_START_HEIGHT);
		pirate.runAction(Actions.getpirateAnimation());
		return pirate;
	}
	
	public static CCSprite getBlueZambie(int GAME_START_HEIGHT){
		CCSprite zambie = CCSprite.sprite("BlueZambie1.png");		
		zambie.runAction(Actions.getMoveAr(3.0f, 1600));
        float pirate1Height =zambie.getBoundingBox().size.height;
        zambie.setPosition( 2000f, pirate1Height/2+GAME_START_HEIGHT);
		zambie.runAction(Actions.getBlueZambieAnimation());
		return zambie;
	}
	
	public static CCSprite getMS5(){
		CCSprite ms5 = CCSprite.sprite("MS5_1.png");
		ms5.runAction(Actions.getMS5Animation());
		return ms5;
	}

    public static CCSprite getLevel1Boss(int GAME_START_HEIGHT){
        CCSprite boss = CCSprite.sprite("level1boss1.png");
        boss.runAction(Actions.getMoveAr(3.0f, 800));
        float bossHeight =boss.getBoundingBox().size.height;
        boss.setPosition( 2000f, bossHeight/2+GAME_START_HEIGHT);
        boss.runAction(Actions.getBoss1Animation());
        return boss;
    }

}
