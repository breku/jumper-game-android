package com.brekol.scene;


import com.badlogic.gdx.math.Vector2;
import com.brekol.manager.SceneManager;
import com.brekol.util.SceneType;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.extension.physics.box2d.FixedStepPhysicsWorld;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.color.Color;

/**
 * User: Breku
 * Date: 01.07.13
 */
public class GameScene extends BaseScene {

    private HUD gameHUD;
    private Text scoreText;
    private PhysicsWorld physicsWorld;
    private int score = 0;

    @Override
    public void createScene() {
        createBackground();
        createHUD();
        createPhysics();
    }

    @Override
    public void onBackKeyPressed() {
        SceneManager.getInstance().loadMenuScene();
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.GAME;
    }

    @Override
    public void disposeScene() {
        camera.setHUD(null);
        camera.setCenter(400,240);
    }

    private void createBackground(){
        setBackground(new Background(Color.BLUE));
    }

    private void createHUD(){
        gameHUD = new HUD();

        scoreText = new Text(20,20, resourcesManager.getMediumFont(), "Score: 0123456789",new TextOptions(HorizontalAlign.LEFT),vertexBufferObjectManager);
        scoreText.setText("Score: 0");
        gameHUD.attachChild(scoreText);

        camera.setHUD(gameHUD);
    }

    private void addToScore(int i){
        score +=i;
        scoreText.setText("Score: " + score);
    }


    private void createPhysics(){
        physicsWorld = new FixedStepPhysicsWorld(60, new Vector2(0,-17),false);
        registerUpdateHandler(physicsWorld);
    }
}