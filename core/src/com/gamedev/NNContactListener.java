package com.gamedev;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class NNContactListener implements ContactListener {

    private int counter = 0;
    //private float correct = 0;

    private NNTraining nnt;

    public NNContactListener(NNTraining nnt) {
        this.nnt = nnt;
    }

    @Override
    public void beginContact(Contact contact) {



    }

    @Override
    public void endContact(Contact contact) {



    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        if (contact.getFixtureA() != null && contact.getFixtureA().getUserData() != null && contact.getFixtureA().getUserData().equals("PLATFORM"))
        {
            //correct = Math.abs(contact.getFixtureA().getBody().getPosition().x - contact.getFixtureB().getBody().getPosition().x) * 2;
            counter++;
            nnt.correctBall();
        }

        if (contact.getFixtureB() != null && contact.getFixtureB().getUserData() != null && contact.getFixtureB().getUserData().equals("PLATFORM")) {
            //correct = Math.abs(contact.getFixtureA().getBody().getPosition().x - contact.getFixtureB().getBody().getPosition().x) * 2;
            counter++;
            nnt.correctBall();
        }
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {



    }

    public int getCounter() {
        return counter;
    }

    public void zeroCounter() {
        this.counter = 0;
    }

}
