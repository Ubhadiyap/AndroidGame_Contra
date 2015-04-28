package minigame.liu.yulei.com.myapplication;

import android.view.MotionEvent;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItem;
import org.cocos2d.menus.CCMenuItemFont;
import org.cocos2d.menus.CCMenuItemImage;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.ccColor3B;

/**
 * Created by alex on 2015/4/27.
 */
public class GameMenu extends CCLayer{


    private GameMenu(){
        CCSprite backGroud = CCSprite.sprite("menu_background.png");
        this.addChild(backGroud);
        CCDirector director = CCDirector.sharedDirector();
        float x = director.winSize().getWidth()/2;
        float y = director.winSize().getHeight()/2;
        backGroud.setPosition(CGPoint.ccp(x,y));

    }

    public static GameMenu getEntryMenu(){
        GameMenu gameMenu = new GameMenu();
        CCMenuItemFont itemLabel = CCMenuItemFont.item("Contra", gameMenu,"menuCallbackEnable");
        itemLabel.setScale(6.0f);
        itemLabel.setColor(ccColor3B.ccORANGE);

        CCMenuItem item1 = CCMenuItemFont.item("New Game", gameMenu, "enterGame1");
        item1.setScale(4.0f);
        CCMenuItem item2 = CCMenuItemFont.item("Load Game", gameMenu, "menuCallbackEnable");
        item2.setScale(4.0f);
        CCMenu menu = CCMenu.menu(itemLabel,item1, item2);
        menu.alignItemsVertically();
        gameMenu.addChild(menu);
        return gameMenu;
    }

    public static GameMenu getLoseGameMenu(int level){
        GameMenu gameMenu = new GameMenu();
        CCMenuItemFont itemLabel = CCMenuItemFont.item("You Lose", gameMenu,"menuCallbackEnable");
        itemLabel.setScale(4.0f);
        itemLabel.setColor(ccColor3B.ccRED);
        itemLabel.setIsEnabled(false);
        CCMenuItem item1;
        if(level == 1) item1 = CCMenuItemFont.item("Restart Game", gameMenu, "enterGame1");
        else if(level == 2) item1 = CCMenuItemFont.item("Restart Game", gameMenu, "enterGame2");
        else if(level == 3) item1 = CCMenuItemFont.item("Restart Game", gameMenu, "enterGame3");
        else  item1 =  CCMenuItemFont.item("Restart Game", gameMenu, "enterGame4");
        CCMenuItem item2 = CCMenuItemFont.item("Back To Main Menu", gameMenu, "backToMainMenu");
        CCMenu menu = CCMenu.menu(itemLabel,item1, item2);
        menu.alignItemsVertically();
        gameMenu.addChild(menu);
        return gameMenu;
    }

    public static GameMenu getWinGameMenu(int level){
        GameMenu gameMenu = new GameMenu();
        CCMenuItemFont itemLabel = CCMenuItemFont.item("Victory", gameMenu,"menuCallbackEnable");
        itemLabel.setScale(5.0f);
        itemLabel.setColor(ccColor3B.ccGREEN);
        itemLabel.setIsEnabled(false);
        CCMenuItem item1;
        if(level == 1) item1 = CCMenuItemFont.item("Next Level", gameMenu, "enterGame2");
        else if(level == 2) item1 = CCMenuItemFont.item("Next Level", gameMenu, "enterGame3");
        else if(level == 3) item1 = CCMenuItemFont.item("Next Level", gameMenu, "enterGame4");
        else {
            item1 =  CCMenuItemFont.item("You Pass All Levels", gameMenu, "enterGame4");
            item1.setIsEnabled(false);
        }
        item1.setScale(3.0f);
        CCMenuItem item2 = CCMenuItemFont.item("Back To Main Menu", gameMenu, "backToMainMenu");
        item2.setScale(3.0f);
        CCMenu menu = CCMenu.menu(itemLabel,item1, item2);
        menu.alignItemsVertically();
        gameMenu.addChild(menu);
        return gameMenu;
    }
    @Override
    public boolean ccTouchesEnded(MotionEvent event) {
        this.setIsTouchEnabled(true);
        return super.ccTouchesEnded(event);
    }

    public static void enterGame1(Object sender){
        CCDirector director =CCDirector.sharedDirector();
        CCScene scene = CCScene.node();
        //create layer
        GameLayer1 gameLayer = new GameLayer1();
        //add game layer to game scene;
        scene.addChild(gameLayer);
        director.replaceScene(scene);
    }

    public static void enterGame2(Object sender){
        CCDirector director =CCDirector.sharedDirector();
        CCScene scene = CCScene.node();
        //create layer
        GameLayer2 gameLayer = new GameLayer2();
        //add game layer to game scene;
        scene.addChild(gameLayer);
        director.replaceScene(scene);
    }

    public static void enterGame3(Object sender){
        CCDirector director =CCDirector.sharedDirector();
        CCScene scene = CCScene.node();
        //create layer
        GameLayer3 gameLayer = new GameLayer3();
        //add game layer to game scene;
        scene.addChild(gameLayer);
        director.replaceScene(scene);
    }

    public static void enterGame4(Object sender){
        CCDirector director =CCDirector.sharedDirector();
        CCScene scene = CCScene.node();
        //create layer
        GameLayer4 gameLayer = new GameLayer4();
        //add game layer to game scene;
        scene.addChild(gameLayer);
        director.replaceScene(scene);
    }

    public static void backToMainMenu(Object sender){
        CCDirector director =CCDirector.sharedDirector();
        CCScene scene = CCScene.node();
        //create layer
        GameMenu gameLayer = GameMenu.getEntryMenu();
        //add game layer to game scene;
        scene.addChild(gameLayer);
        director.replaceScene(scene);
    }
}
