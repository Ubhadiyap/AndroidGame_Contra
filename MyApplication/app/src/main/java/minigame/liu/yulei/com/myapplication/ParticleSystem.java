package minigame.liu.yulei.com.myapplication;

import org.cocos2d.nodes.CCTextureCache;
import org.cocos2d.particlesystem.CCParticleExplosion;
import org.cocos2d.particlesystem.CCParticleFire;
import org.cocos2d.particlesystem.CCParticleFireworks;
import org.cocos2d.particlesystem.CCParticleFlower;
import org.cocos2d.particlesystem.CCParticleGalaxy;
import org.cocos2d.particlesystem.CCParticleMeteor;
import org.cocos2d.particlesystem.CCParticleRain;
import org.cocos2d.particlesystem.CCParticleSmoke;
import org.cocos2d.particlesystem.CCParticleSnow;
import org.cocos2d.particlesystem.CCParticleSpiral;
import org.cocos2d.particlesystem.CCParticleSun;
import org.cocos2d.particlesystem.CCParticleSystem;
import org.cocos2d.particlesystem.CCQuadParticleSystem;

import org.cocos2d.types.CGPoint;
import org.cocos2d.types.ccColor4F;

public class ParticleSystem {
	  public static CCParticleFire getFire(float positionX, float positionY) {
		  		
		  		CCParticleFire emitter = CCParticleFire.node();
		  		emitter.setScale(2.0f);
	    		emitter.setPosition(CGPoint.ccp(positionX, positionY));	    		
	    		emitter.setTexture(CCTextureCache.sharedTextureCache().addImage("fire.png"));
	    		emitter.setDuration(0.5f);
	    		return emitter;
	    	}


	  public static CCParticleSystem getExplosion(float positionX, float positionY) {
	  		
	  		    CCParticleSystem emitter = CCParticleExplosion.node();
	            emitter.setPosition(CGPoint.ccp(positionX, positionY));
	            emitter.setTexture(CCTextureCache.sharedTextureCache().addImage("stars_grayscale.png"));
	    		emitter.setAutoRemoveOnFinish(true);
	    		return emitter;
	        }
	  
	  
	  public static CCParticleSystem getFirework(float positionX, float positionY) {
	    
	            CCParticleSystem emitter = CCParticleFireworks.node();	 
	            emitter.setTexture(CCTextureCache.sharedTextureCache().addImage("stars.png"));
	            emitter.setPosition(CGPoint.ccp(positionX, positionY));
	            emitter.setBlendAdditive(true);
	            return emitter;
	        }


	  public static CCParticleSystem getSun(float positionX, float positionY) {
		  		
	    		CCParticleSystem emitter = CCParticleSun.node();
	    		emitter.setPosition(CGPoint.ccp(positionX, positionY));
	    		emitter.setTexture(CCTextureCache.sharedTextureCache().addImage("fire.png"));
	    		return emitter;
	    }

	  public static CCParticleSystem getGalaxy(float positionX, float positionY) {
	    		CCParticleSystem emitter = CCParticleGalaxy.node();
	    		emitter.setPosition(CGPoint.ccp(positionX, positionY));
	    		emitter.setTexture(CCTextureCache.sharedTextureCache().addImage("fire.png"));
	    		return emitter;
	    	}

	  public static CCParticleSystem getFlower(float positionX, float positionY) {
  				CCParticleSystem emitter = CCParticleFlower.node();
  				emitter.setPosition(CGPoint.ccp(positionX, positionY));
	    		emitter.setTexture(CCTextureCache.sharedTextureCache().addImage("stars_grayscale.png"));
	    		return emitter;
	    	}


	  public static CCParticleSystem getBigFlower(float positionX, float positionY) {
				CCParticleSystem emitter = new CCQuadParticleSystem(50);
				emitter.setPosition(CGPoint.ccp(positionX, positionY));
	    		emitter.setTexture(CCTextureCache.sharedTextureCache().addImage("stars_grayscale.png"));
	    		// duration
	    		emitter.setDuration(CCParticleSystem.kCCParticleDurationInfinity);
	    		// Gravity Mode: gravity
	    		emitter.setGravity(CGPoint.zero());
	    		// Set "Gravity" mode (default one)
	    		emitter.setEmitterMode(CCParticleSystem.kCCParticleModeGravity);

	    		// Gravity Mode: speed of particles
	    		emitter.setSpeed(160);
	    		emitter.setSpeedVar(20);

	    		// Gravity Mode: radial
	    		emitter.setRadialAccel(-120);
	    		emitter.setRadialAccelVar(0);

	    		// Gravity Mode: tagential
	    		emitter.setTangentialAccel(30);
	    		emitter.setTangentialAccelVar(0);

	    		// angle
	    		emitter.setAngle(90);
	    		emitter.setAngleVar(360);

	    		// emitter position
	    		emitter.setPosition(CGPoint.ccp(160,240));
	    		emitter.setPosVar(CGPoint.zero());

	    		// life of particles
	    		emitter.setLife(4);
	    		emitter.setLifeVar(1);

	    		// spin of particles
	    		emitter.setStartSpin(0);
	    		emitter.setStartSpinVar(0);
	    		emitter.setEndSpin(0);
	    		emitter.setEndSpinVar(0);

	    		// color of particles
	    		ccColor4F startColor = new ccColor4F(0.5f, 0.5f, 0.5f, 1.0f);
	    		emitter.setStartColor(startColor);

	    		ccColor4F startColorVar = new ccColor4F(0.5f, 0.5f, 0.5f, 1.0f);
	    		emitter.setStartColorVar(startColorVar);

	    		ccColor4F endColor = new ccColor4F(0.1f, 0.1f, 0.1f, 0.2f);
	    		emitter.setEndColor(endColor);

	    		ccColor4F endColorVar = new ccColor4F(0.1f, 0.1f, 0.1f, 0.2f);	
	    		emitter.setEndColorVar(endColorVar);

	    		// size, in pixels
	    		emitter.setStartSize(80.0f);
	    		emitter.setStartSizeVar(40.0f);
	    		emitter.setEndSize(CCParticleSystem.kCCParticleStartSizeEqualToEndSize);

	    		// emits per second
	    		emitter.setEmissionRate( emitter.getTotalParticles()/emitter.getLife());

	    		// additive
	    		emitter.setBlendAdditive(true);

	    		return emitter;
	    	}


	  public static CCParticleSystem getRotFlower(float positionX, float positionY) {
				CCParticleSystem emitter = new CCQuadParticleSystem(300);
				emitter.setPosition(CGPoint.ccp(positionX, positionY));	   
				
	    		emitter.setTexture(CCTextureCache.sharedTextureCache().addImage("stars2_grayscale.png"));

	    		// duration
	    		emitter.setDuration(CCParticleSystem.kCCParticleDurationInfinity);

	    		// Set "Gravity" mode (default one)
	    		emitter.setEmitterMode(CCParticleSystem.kCCParticleModeGravity);

	    		// Gravity mode: gravity
	    		emitter.setGravity(CGPoint.zero());

	    		// Gravity mode: speed of particles
	    		emitter.setSpeed(160);
	    		emitter.setSpeedVar(20);

	    		// Gravity mode: radial
	    		emitter.setRadialAccel(-120);
	    		emitter.setRadialAccelVar(0);

	    		// Gravity mode: tagential
	    		emitter.setTangentialAccel(30);
	    		emitter.setTangentialAccelVar(0);

	    		// emitter position
	    		emitter.setPosition(CGPoint.ccp(160,240));
	    		emitter.setPosVar(CGPoint.zero());

	    		// angle
	    		emitter.setAngle(90);
	    		emitter.setAngleVar(360);

	    		// life of particles
	    		emitter.setLife(3);
	    		emitter.setLifeVar(1);

	    		// spin of particles
	    		emitter.setStartSpin(0);
	    		emitter.setStartSpinVar(0);
	    		emitter.setEndSpin(0);
	    		emitter.setEndSpinVar(2000);

	    		// color of particles
	    		ccColor4F startColor = new ccColor4F(0.5f, 0.5f, 0.5f, 1.0f);
	    		emitter.setStartColor(startColor);

	    		ccColor4F startColorVar = new ccColor4F(0.5f, 0.5f, 0.5f, 1.0f);
	    		emitter.setStartColorVar(startColorVar);

	    		ccColor4F endColor = new ccColor4F(0.1f, 0.1f, 0.1f, 0.2f);
	    		emitter.setEndColor(endColor);

	    		ccColor4F endColorVar = new ccColor4F(0.1f, 0.1f, 0.1f, 0.2f);	
	    		emitter.setEndColorVar(endColorVar);

	    		// size, in pixels
	    		emitter.setStartSize(30.0f);
	    		emitter.setStartSizeVar(00.0f);
	    		emitter.setEndSize(CCParticleSystem.kCCParticleStartSizeEqualToEndSize);

	    		// emits per second
	    		emitter.setEmissionRate(emitter.getTotalParticles()/emitter.getLife());

	    		// additive
	    		emitter.setBlendAdditive(false);

	    		return emitter;
	    	}

	  public static CCParticleSystem getSmoke(float positionX, float positionY) {
				CCParticleSystem emitter = CCParticleSmoke.node();
				emitter.setPosition(CGPoint.ccp(positionX, positionY));	   	    		
	    		return emitter;
	    	}   	

	  public static CCParticleSystem getSnow(float positionX, float positionY) {
				CCParticleSystem emitter = CCParticleSnow.node();
				emitter.setPosition(CGPoint.ccp(positionX, positionY));	 
	    		emitter.setLife(3);
	    		emitter.setLifeVar(1);
	    		// gravity
	    		emitter.setGravity(CGPoint.ccp(0,-10));

	    		// speed of particles
	    		emitter.setSpeed(130);
	    		emitter.setSpeedVar(30);


	    		ccColor4F startColor = emitter.getStartColor();
	    		startColor.r = 0.9f;
	    		startColor.g = 0.9f;
	    		startColor.b = 0.9f;
	    		emitter.setStartColor(startColor);

	    		ccColor4F startColorVar = emitter.getStartColorVar();
	    		startColorVar.b = 0.1f;
	    		emitter.setStartColorVar(startColorVar);

	    		emitter.setEmissionRate(emitter.getTotalParticles()/emitter.getLife());

	    		emitter.setTexture(CCTextureCache.sharedTextureCache().addImage("snow.png"));

	    		return emitter;
	    	}

	    

		public static CCParticleSystem getRain(float positionX, float positionY) {
				CCParticleSystem emitter = CCParticleRain.node();
				emitter.setPosition(CGPoint.ccp(positionX, positionY));	 
	    		emitter.setLife(4);
	    		emitter.setTexture(CCTextureCache.sharedTextureCache().addImage("fire.png"));
	    		emitter.setScaleY(7.0f);
	    		return emitter;
	    	}
	    	

	    
		public static CCParticleSystem getRing(float positionX, float positionY) {
				CCParticleSystem emitter = CCParticleFlower.node(500);
				emitter.setPosition(CGPoint.ccp(positionX, positionY));	 

	    		emitter.setTexture(CCTextureCache.sharedTextureCache().addImage("stars_grayscale.png"));
	    		emitter.setLifeVar(0);
	    		emitter.setLife(10);
	    		emitter.setSpeed(100);
	    		emitter.setSpeedVar(0);
	    		emitter.setEmissionRate(10000);

	    		return emitter;
	    	}

}
