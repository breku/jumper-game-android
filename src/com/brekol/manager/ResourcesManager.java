package com.brekol.manager;

import android.app.Activity;
import android.graphics.Color;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.BaseGameActivity;


/**
 * User: Breku
 * Date: 30.06.13
 */
public class ResourcesManager {

    private static final ResourcesManager INSTANCE = new ResourcesManager();

    private BaseGameActivity activity;
    private Engine engine;
    private Camera camera;
    private VertexBufferObjectManager vertexBufferObjectManager;
    private Font mediumFont;

    private ITextureRegion splashTextureRegion, menuBackgroundRegion, playButtonRegion, optionsButtonRegion;
    private BitmapTextureAtlas splashTextureAtlas;

    private BuildableBitmapTextureAtlas menuTextureAtlas;


    public static void prepareManager(Engine engine, BaseGameActivity activity, Camera camera, VertexBufferObjectManager vertexBufferObjectManager) {
        getInstance().engine = engine;
        getInstance().activity = activity;
        getInstance().camera = camera;
        getInstance().vertexBufferObjectManager = vertexBufferObjectManager;
    }

    public void loadMenuResources() {
        loadMenuGraphics();
        loadMenuAudio();
        loadMenuFonts();
    }

    private void loadMenuFonts() {
        FontFactory.setAssetBasePath("font/");
        final ITexture mediumFontTexture = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        mediumFont = FontFactory.createStrokeFromAsset(activity.getFontManager(), mediumFontTexture, activity.getAssets(), "mediumFont.ttf", 50, true, Color.WHITE, 2, Color.WHITE);
        mediumFont.load();
    }

    public void loadGameResources() {
        loadGameGraphics();
        loadGameFonts();
        loadGameAudio();
    }

    public void loadMenuTextures(){
        menuTextureAtlas.load();
    }

    private void loadMenuGraphics() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu/");
        menuTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
        menuBackgroundRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "menu_background.png");
        playButtonRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "play.png");
        optionsButtonRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "options.png");

        try {
            menuTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 1));
            menuTextureAtlas.load();
        } catch (ITextureAtlasBuilder.TextureAtlasBuilderException e) {
            e.printStackTrace();
        }


    }

    private void loadMenuAudio() {

    }

    private void loadGameGraphics() {

    }

    private void loadGameFonts() {

    }

    private void loadGameAudio() {

    }

    public void loadSplashScreen() {

        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        splashTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
        splashTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(splashTextureAtlas, activity, "splash.png", 0, 0);
        splashTextureAtlas.load();


    }

    public void unloadGameTextures(){

    }

    public void unloadMenuTextures(){
        menuTextureAtlas.unload();
    }

    public void unloadSplashScreen() {
        splashTextureAtlas.unload();
        splashTextureRegion = null;
    }


    public static ResourcesManager getInstance() {
        return INSTANCE;
    }

    public Engine getEngine() {
        return engine;
    }

    public Activity getActivity() {
        return activity;
    }

    public Camera getCamera() {
        return camera;
    }

    public VertexBufferObjectManager getVertexBufferObjectManager() {
        return vertexBufferObjectManager;
    }

    public ITextureRegion getSplashTextureRegion() {
        return splashTextureRegion;
    }

    public ITextureRegion getMenuBackgroundRegion() {
        return menuBackgroundRegion;
    }

    public ITextureRegion getPlayButtonRegion() {
        return playButtonRegion;
    }

    public ITextureRegion getOptionsButtonRegion() {
        return optionsButtonRegion;
    }

    public Font getMediumFont() {
        return mediumFont;
    }
}
