package com.brekol.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.brekol.manager.ResourcesManager;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * User: Breku
 * Date: 16.07.13
 */
public abstract class Player extends AnimatedSprite {

    private Body body;
    private boolean canRun = false;

    public Player(float pX, float pY, VertexBufferObjectManager vertexBufferObjectManager, Camera camera, PhysicsWorld physicsWorld) {
        super(pX, pY, ResourcesManager.getInstance().getPlayerRegion(), vertexBufferObjectManager);
        createPhysics(camera, physicsWorld);
        camera.setChaseEntity(this);
    }

    public abstract void onDie();

    private void createPhysics(final Camera camera, PhysicsWorld physicsWorld) {
        body = PhysicsFactory.createBoxBody(physicsWorld, this, BodyDef.BodyType.DynamicBody, PhysicsFactory.createFixtureDef(0, 0, 0));
        body.setUserData("player");
        body.setFixedRotation(true);

        physicsWorld.registerPhysicsConnector(new PhysicsConnector(this, body, true, false) {
            @Override
            public void onUpdate(float pSecondsElapsed) {
                super.onUpdate(pSecondsElapsed);
                camera.onUpdate(pSecondsElapsed);
                if (getX() <= 0) {
                    onDie();
                }

                if (canRun) {
                    body.setLinearVelocity(new Vector2(5, body.getLinearVelocity().y));
                }
            }
        });
    }

    public void setRunning() {
        canRun = true;
        final long[] PLAYER_ANIMATE = new long[]{100, 100, 100};
        animate(PLAYER_ANIMATE, 0, 2, true);
    }

    public void jump() {
        body.setLinearVelocity(new Vector2(body.getLinearVelocity().x, 12));
    }
}
