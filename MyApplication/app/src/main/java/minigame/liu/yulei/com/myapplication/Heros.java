package minigame.liu.yulei.com.myapplication;

import org.cocos2d.nodes.CCSprite;

public class Heros {
	
	public static CCSprite getHero(int GAME_START_HEIGHT){
		// set the player
		CCSprite player = CCSprite.sprite("contrachar.png");
		player.setPosition(100, 90+GAME_START_HEIGHT);
		player.setScale(0.5f);
		player.runAction(Actions.player1Animation());
		return player;
	}

}
