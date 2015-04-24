package com.example.contramini;

import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCGLSurfaceView;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {
	
	//Cocos2d Game Engine will draw picture in this view object.
	private CCGLSurfaceView view = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        view = new CCGLSurfaceView(this);
        
        setContentView(view);
        
        //get CCDirector instance
        CCDirector director = CCDirector.sharedDirector();
        
        //set properties of the game.
        // set view object of current game;
        director.attachInView(view);
        //set whether display FPS value;
        director.setDisplayFPS(true);
        //set FPS value, time need to per zhen;
        director.setAnimationInterval(1/100.0);

        
        //create scene object
        CCScene scene = CCScene.node();
        
        //create layer
        GameLayer2 gameLayer = new GameLayer2();
        //add game layer to game scene;
        scene.addChild(gameLayer);
        //run game scene
        director.runWithScene(scene);
        
        //setContentView(R.layout.activity_main); not use this, we should use our view.
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    

    
    @Override
    public void onPause()
    {
        super.onPause();
     
        CCDirector.sharedDirector().pause();
    }
     
    @Override
    public void onResume()
    {
        super.onResume();
     
        CCDirector.sharedDirector().resume();
    }
     
    @Override
    public void onStop()
    {
        super.onStop();
     
        CCDirector.sharedDirector().end();
    }
}
