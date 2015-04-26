package com.example.contramini;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.cocos2d.actions.CCProgressTimer;
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
	CCProgressTimer healthBar;	
	CCDirector director;
	
	//projectile array
	ArrayList<CCSprite> projectileArray;
	
	//monster array
	ArrayList<CCSprite> monsterArray;
	
	ArrayList<CCSprite> buttons;
	
	boolean invulnerable = false;
	
	final int GAME_START_HEIGHT = 300;
		
	public GameLayer2(){
		// enable touch operation
		this.setIsTouchEnabled(true);
		
		projectileArray = new ArrayList<CCSprite>();
		monsterArray = new ArrayList<CCSprite>();
				
		
		buttons = UILayout.getButtonsList();
		//add control buttons
		for(CCSprite button: buttons){
			this.addChild(button, 20);
		}

		//weather is rain
		CCParticleSystem emitter11 = ParticleSystem.getRain(1100, 1000);
		addChild(emitter11,9);
		
		player = Heros.getHero(GAME_START_HEIGHT);
		this.addChild(player, 0);
		

		healthBar =UILayout.getHealthBar();
		//add health bar
		addChild(healthBar,17);
		
		BackGround background = BackGround.getLevel1Background(GAME_START_HEIGHT);
		backgroundNode = background.getBackgroundNode();
		this.addChild(backgroundNode, -1);
		
		monsterArray = background.getMonsterArray();
		
		director = CCDirector.sharedDirector();

		this.schedule("update");

		
	}
	
	@Override
	public boolean ccTouchesBegan(MotionEvent event) {
		PlayerControl.touchBegin(player, backgroundNode, director, event, GAME_START_HEIGHT, this,buttons);
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
				
		CGPoint deltaLeft = CGPoint.ccp(-10, 0);
		CGPoint updateLeft = CGPoint.ccpAdd(backgroundNode.getPosition(), deltaLeft);

		if(realX <= 500){   //back move
			//player.runAction(Actions.playerMoveBackward());
			
		}else if(realX<= 1000){   //forward move
			float playerPositionX = player.getPosition().x;
			if(playerPositionX < 800){
				player.runAction(Actions.playerMoveForward());
			}else{
				backgroundNode.setPosition(updateLeft);
			}
		}

		return super.ccTouchesMoved(event);
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

			Iterator<CCSprite> projectIterator = this.projectileArray.iterator();
			while(projectIterator.hasNext()){
				CCSprite projectile = projectIterator.next();
				CGRect projectileRect = CGRect.make(projectile.getPosition().x - (projectile.getContentSize().width / 2.0f),
                projectile.getPosition().y - (projectile.getContentSize().height / 2.0f),
                projectile.getContentSize().width,
                projectile.getContentSize().height);
	

			if(CGRect.intersects(monsterRect, projectileRect)){
			
				CGPoint position = monster.convertToWorldSpace(0, 0);
				this.addChild(ParticleSystem.getFire(position.x, position.y), 5);
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
				player.getContentSize().width/2,
				player.getContentSize().height/2);
		
		for(CCSprite monster: monsterArray){
			CGPoint monsterAbsoPosition = monster.convertToWorldSpace(0, 0);
			CGRect monsterRect = CGRect.make(monsterAbsoPosition.x - (monster.getContentSize().width / 2.0f),
					monsterAbsoPosition.y - (monster.getContentSize().height / 2.0f),
					monster.getContentSize().width/2,
					monster.getContentSize().height/2);
			if(CGRect.intersects(monsterRect, playerRect) ){
				
				this.invulnerable = true;
				player.stopAction(1);				
				Actions.loseHealth(player);
				healthBar.setPercentage(healthBar.getPercentage()-0.3f);
				
				System.out.println(monsterRect.size.height+"||||"+monsterRect.size.width+"  player  "+playerRect.size.height+"||"+playerRect.size.width   );
				//

				if(healthBar.getPercentage() < 1){
					
				}
				
			}
			
		}

	

	}
}
