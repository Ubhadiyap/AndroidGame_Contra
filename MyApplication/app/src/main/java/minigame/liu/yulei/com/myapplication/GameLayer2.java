package minigame.liu.yulei.com.myapplication;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
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
    CCSprite level1Boss;
    CCProgressTimer bossHealthBar;
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
	//buttons Array
	ArrayList<CCSprite> buttons;
    //boss projectile
    ArrayList<CCSprite> bossProjectiles;
	
	boolean invulnerable = false;
	
	final int GAME_START_HEIGHT = 300;
		
	public GameLayer2(){
		// enable touch operation
		this.setIsTouchEnabled(true);
		
		projectileArray = new ArrayList<CCSprite>();
		monsterArray = new ArrayList<CCSprite>();
        bossProjectiles = new ArrayList<>();
				
		
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
		

		healthBar =UILayout.getHealthBar(200,1000);
		//add health bar
		addChild(healthBar,17);
		
		BackGround background = BackGround.getLevel1Background(GAME_START_HEIGHT);
		backgroundNode = background.getBackgroundNode();
		this.addChild(backgroundNode, -1);
		
		monsterArray = background.getMonsterArray();
		
		director = CCDirector.sharedDirector();

		this.schedule("update");
        this.schedule("addDynamicMonster",5);

		this.schedule("addStaticMonster",5);
        this.schedule("addBoss",3);

	}
	
	@Override
	public boolean ccTouchesBegan(MotionEvent event) {
		PlayerControl.touchBegin(player, backgroundNode, director, event, GAME_START_HEIGHT, this,buttons);
		return super.ccTouchesBegan(event);
	}
	


	@Override
	public boolean ccTouchesMoved(MotionEvent event) {

        PlayerControl.touchMoved(player,backgroundNode,event,buttons);
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
				monster.removeSelf();
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
				Actions.loseHealth(player,GAME_START_HEIGHT);
				healthBar.setPercentage(healthBar.getPercentage()-0.3f);
				
				System.out.println(monsterRect.size.height+"||||"+monsterRect.size.width+"  player  "+playerRect.size.height+"||"+playerRect.size.width   );
				//

				if(healthBar.getPercentage() < 1){
					
				}
			}
		}
	}

    public void addDynamicMonster(float dt) {
        CCSprite prite = MonsterFactory.getPirate(this.GAME_START_HEIGHT);
        this.addChild(prite);
        monsterArray.add(prite);
        CCSprite zambie = MonsterFactory.getBlueZambie(this.GAME_START_HEIGHT);
        this.addChild(zambie);
        monsterArray.add(zambie);
    }
    public void addBoss(float dt){
        if(backgroundNode.getPosition().x < -10000){
            level1Boss = MonsterFactory.getLevel1Boss(this.GAME_START_HEIGHT);
            this.addChild(level1Boss);
            this.unschedule("addBoss");
            this.unschedule("addDynamicMonster");
            bossHealthBar = UILayout.getHealthBar(1500,1000);
            this.schedule("bossAttack",3);
            this.schedule("bossCollisionDetection");
        }
    }

    public void bossAttack(float dt){
        float x =level1Boss.getPosition().x;
        float y =level1Boss.getPosition().y;
        CCSprite projectile = CCSprite.sprite("A_missle_1_left.png");
        this.addChild(projectile);
        Random random = new Random();
        float r = random.nextFloat();
        float randomDeltaY = 2*(r-0.5f)*(level1Boss.getContentSize().height / 2.0f);
        float iniY = level1Boss.getPosition().y - randomDeltaY;
        CGPoint ini = CGPoint.ccp(level1Boss.getPosition().x - (level1Boss.getContentSize().width / 2.0f),
                iniY);
        projectile.setPosition(ini);
        this.bossProjectiles.add(projectile);
        CGPoint target = CGPoint.ccp(0, iniY);
        CCMoveTo moveProjec = CCMoveTo.action(2.0f, target);
        projectile.runAction(moveProjec);
    }

    public void bossCollisionDetection(float dt){

        Iterator<CCSprite> proIterator = this.bossProjectiles.iterator();
        while(proIterator.hasNext()){
            CCSprite projectile = proIterator.next();
            if(projectile.getPosition().x <100){
                this.removeChild(projectile, true);
                proIterator.remove();
            }
        }
        // player hurt detection
        CGRect playerRect = CGRect.make(player.getPosition().x - (player.getContentSize().width / 2.0f),
                player.getPosition().y - (player.getContentSize().height / 2.0f),
                player.getContentSize().width/2,
                player.getContentSize().height/2);

        for(CCSprite monster: bossProjectiles){
            CGPoint monsterAbsoPosition = monster.convertToWorldSpace(0, 0);
            CGRect monsterRect = CGRect.make(monsterAbsoPosition.x - (monster.getContentSize().width / 2.0f),
                    monsterAbsoPosition.y - (monster.getContentSize().height / 2.0f),
                    monster.getContentSize().width/2,
                    monster.getContentSize().height/2);
            if(CGRect.intersects(monsterRect, playerRect) ){

                this.invulnerable = true;
                player.stopAction(1);
                Actions.loseHealth(player,GAME_START_HEIGHT);
                healthBar.setPercentage(healthBar.getPercentage()-0.3f);

                System.out.println(monsterRect.size.height+"||||"+monsterRect.size.width+"  player  "+playerRect.size.height+"||"+playerRect.size.width   );
                //

                if(healthBar.getPercentage() < 1){

                }
            }
        }
    }
}
