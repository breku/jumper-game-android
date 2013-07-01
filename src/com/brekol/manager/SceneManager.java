package com.brekol.manager;

import com.brekol.scene.BaseScene;
import com.brekol.scene.LoadingScene;
import com.brekol.scene.MainMenuScene;
import com.brekol.scene.SplashScene;
import com.brekol.util.SceneType;
import org.andengine.engine.Engine;
import org.andengine.ui.IGameInterface;

/**
 * User: Breku
 * Date: 30.06.13
 */
public class SceneManager {

    private static final SceneManager INSTANCE = new SceneManager();
    private SceneType currentSceneType = SceneType.SPLASH;
    private Engine engine = ResourcesManager.getInstance().getEngine();
    private BaseScene currentScene;

    private BaseScene splashScene;
    private BaseScene menuScene;
    private BaseScene gameScene;
    private BaseScene loadingScene;

    public void setScene(BaseScene scene) {
        engine.setScene(scene);
        currentScene = scene;
        currentSceneType = scene.getSceneType();
    }


    public void setScene(SceneType sceneType) {
        switch (sceneType) {
            case MENU:
                setScene(menuScene);
                break;
            case GAME:
                setScene(gameScene);
                break;
            case SPLASH:
                setScene(splashScene);
                break;
            case LOADING:
                setScene(loadingScene);
                break;
            default:
                break;
        }
    }

    public void createSplashScene(IGameInterface.OnCreateSceneCallback onCreateSceneCallback){
        ResourcesManager.getInstance().loadSplashScreen();
        splashScene = new SplashScene();
        currentScene = splashScene;
        onCreateSceneCallback.onCreateSceneFinished(splashScene);
    }

    public void createMenuScene(){
        ResourcesManager.getInstance().loadMenuResources();
        menuScene = new MainMenuScene();
        loadingScene = new LoadingScene();
        setScene(menuScene);
        disposeSplashScene();
    }


    private void disposeSplashScene()
    {
        ResourcesManager.getInstance().unloadSplashScreen();
        splashScene.disposeScene();
        splashScene = null;
    }



    public SceneType getCurrentSceneType() {
        return currentSceneType;
    }

    public BaseScene getCurrentScene() {
        return currentScene;
    }

    public static SceneManager getInstance() {
        return INSTANCE;
    }
}
