package com.brekol.scene;

import com.brekol.manager.SceneManager;
import com.brekol.util.SceneType;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;

/**
 * User: Breku
 * Date: 30.06.13
 */
public class MainMenuScene extends BaseScene implements MenuScene.IOnMenuItemClickListener{

    private MenuScene menuChildScene;
    private final int MENU_PLAY = 0;
    private final int MENU_OPTIONS = 1;

    @Override
    public void createScene() {
        createBackground();
        createMenuChildScene();
    }

    @Override
    public void onBackKeyPressed() {
        System.exit(0);
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.MENU;
    }

    @Override
    public void disposeScene() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    private void createBackground()
    {
        attachChild(new Sprite(0, 0, resourcesManager.getMenuBackgroundRegion(), vertexBufferObjectManager)
        {
            @Override
            protected void preDraw(GLState pGLState, Camera pCamera)
            {
                super.preDraw(pGLState, pCamera);
                pGLState.enableDither();
            }
        });
    }

    private void createMenuChildScene(){
        menuChildScene = new MenuScene(camera);
        menuChildScene.setPosition(0,0);

        final IMenuItem playMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_PLAY, resourcesManager.getPlayButtonRegion(), vertexBufferObjectManager), 1.2f, 1);
        final IMenuItem optionsMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_OPTIONS, resourcesManager.getOptionsButtonRegion(), vertexBufferObjectManager), 1.2f, 1);

        menuChildScene.addMenuItem(playMenuItem);
        menuChildScene.addMenuItem(optionsMenuItem);

        menuChildScene.buildAnimations();
        menuChildScene.setBackgroundEnabled(false);

        playMenuItem.setPosition(playMenuItem.getX(), playMenuItem.getY() + 10);
        optionsMenuItem.setPosition(optionsMenuItem.getX(), optionsMenuItem.getY()+30);

        menuChildScene.setOnMenuItemClickListener(this);

        setChildScene(menuChildScene);
    }


    @Override
    public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX, float pMenuItemLocalY) {
        switch (pMenuItem.getID()){
            case MENU_PLAY:
                SceneManager.getInstance().loadGameScene();
                return true;
            case MENU_OPTIONS:
                return true;
            default:
                return false;
        }
    }
}
