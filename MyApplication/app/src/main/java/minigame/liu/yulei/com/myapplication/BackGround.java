package minigame.liu.yulei.com.myapplication;

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
        CCParallaxNode backgroundNode = getSingleBackNode(GAME_START_HEIGHT,monsterArray);
        backgroundNode.addChild(getSingleBackNode(GAME_START_HEIGHT,monsterArray),-1,1.0f,1.0f,3072*2.3f-30,0);

		

		return new BackGround(monsterArray,backgroundNode);
	}

    public static CCParallaxNode getSingleBackNode(int GAME_START_HEIGHT,ArrayList<CCSprite> monsterArray){
        CCParallaxNode backgroundNode = CCParallaxNode.node();
        CCSprite back = CCSprite.sprite("long.jpg");
        back.setScale(2.3f);
        float backHeight = back.getBoundingBox().size.height;
        float backWidth = back.getBoundingBox().size.width;
        backgroundNode.addChild(back, -1, 1.0f, 1.0f, backWidth/2, backHeight/2+GAME_START_HEIGHT);

        CCSprite MS5_1 = MonsterFactory.getMS5();
        CCSprite MS5_2 = MonsterFactory.getMS5();
        float MS5Height =MS5_1.getBoundingBox().size.height;

        backgroundNode.addChild(MS5_1, 0, 1.0f, 1.0f, 4100f, MS5Height/2+GAME_START_HEIGHT);
        backgroundNode.addChild(MS5_2, 0, 1.0f, 1.0f, 2000f, MS5Height/2+GAME_START_HEIGHT);

        monsterArray.add(MS5_1);
        monsterArray.add(MS5_2);
        return backgroundNode;
    }

	public ArrayList<CCSprite> getMonsterArray() {
		return monsterArray;
	}

	public CCParallaxNode getBackgroundNode() {
		return backgroundNode;
	}
	
}
