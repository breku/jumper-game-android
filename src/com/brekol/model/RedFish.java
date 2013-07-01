package com.brekol.model;

import com.brekol.util.FishType;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * User: Breku
 * Date: 29.06.13
 */
public class RedFish extends Fish{


    public RedFish(final float pX, final float pY, final ITextureRegion pTextureRegion, final VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
    }
}
