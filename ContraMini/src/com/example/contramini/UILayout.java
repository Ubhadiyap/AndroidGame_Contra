package com.example.contramini;

import java.util.ArrayList;

import org.cocos2d.actions.CCProgressTimer;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

public class UILayout {
	
	public static ArrayList<CCSprite> getButtonsList(){
		
		ArrayList<CCSprite> buttons = new ArrayList<CCSprite>();
		//set control buttons
		CCSprite leftButton = CCSprite.sprite("leftbutton.png");
		CCSprite rightButton = CCSprite.sprite("rightbutton.png");
		CCSprite jumpButton = CCSprite.sprite("jumpW.png");
		CCSprite shootButton = CCSprite.sprite("shootW.png");
		
		CGPoint LBPosition = CGPoint.ccp(250, 150);
		CGPoint RBPosition = CGPoint.ccp(750, 150);
		CGPoint JBPosition = CGPoint.ccp(1500, 150);
		CGPoint SBPosition = CGPoint.ccp(1500, 600);
		
		leftButton.setPosition(LBPosition);
		rightButton.setPosition(RBPosition);
		jumpButton.setPosition(JBPosition);
		shootButton.setPosition(SBPosition);
		
		leftButton.setOpacity(100);
		rightButton.setOpacity(100);
		jumpButton.setOpacity(100);
		shootButton.setOpacity(100);
		
		buttons.add(leftButton);
		buttons.add(rightButton);
		buttons.add(jumpButton);
		buttons.add(shootButton);
		
		return buttons;
	}
	
	public static CCProgressTimer getHealthBar(){
		//set health bar
		CCProgressTimer healthBar = CCProgressTimer.progress("HealthBar.png");
		healthBar.setType(CCProgressTimer.kCCProgressTimerTypeHorizontalBarLR);
		healthBar.setPercentage(100);
		healthBar.setPosition(CGPoint.ccp(200, 1000));
		healthBar.setScale(2.0f);
		return healthBar;
	}
}
