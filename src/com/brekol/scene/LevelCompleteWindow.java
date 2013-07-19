package com.brekol.scene;

import com.brekol.manager.ResourcesManager;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.sprite.vbo.ISpriteVertexBufferObject;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObject;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * User: Breku
 * Date: 19.07.13
 */
public class LevelCompleteWindow extends Sprite {

    private TiledSprite star1;
    private TiledSprite star2;
    private TiledSprite star3;


    public enum StarsCount{
        ONE,TWO,THREE
    }

    public LevelCompleteWindow() {
        super(0, 0, 650,400, ResourcesManager.getInstance().getCompleteWindowRegion(), ResourcesManager.getInstance().getVertexBufferObjectManager());
        attachStars();
    }

    private void attachStars(){
        star1 = new TiledSprite(150,150,ResourcesManager.getInstance().getCompleteStarRegion(),ResourcesManager.getInstance().getVertexBufferObjectManager());
        star2 = new TiledSprite(325,150,ResourcesManager.getInstance().getCompleteStarRegion(),ResourcesManager.getInstance().getVertexBufferObjectManager());
        star3 = new TiledSprite(500,150,ResourcesManager.getInstance().getCompleteStarRegion(),ResourcesManager.getInstance().getVertexBufferObjectManager());
        attachChild(star1);
        attachChild(star2);
        attachChild(star3);

    }


    public void display(StarsCount starsCount, Scene scene, Camera camera){
        switch (starsCount){
            case ONE:
                star1.setCurrentTileIndex(0);
                star2.setCurrentTileIndex(1);
                star3.setCurrentTileIndex(1);
                break;
            case TWO:
                star1.setCurrentTileIndex(0);
                star2.setCurrentTileIndex(0);
                star3.setCurrentTileIndex(1);
                break;
            case THREE:
                star1.setCurrentTileIndex(0);
                star2.setCurrentTileIndex(0);
                star3.setCurrentTileIndex(0);
                break;
        }

        camera.getHUD().setVisible(false);
        camera.setChaseEntity(null);
        setPosition(camera.getCenterX(),camera.getCenterY());
        scene.attachChild(this);
    }
}
