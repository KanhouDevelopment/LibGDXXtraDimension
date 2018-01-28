package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.*;

public class Character {
	private int x;
	private int y;
	private Body body;
	
	private float width = 0.5f;
	private float height = 1.0f;
	
	private boolean right;
	private boolean down;
	
	private float vY;
	Character(int sx, int sy, World world, float width, float height) {
		x = sx;
		y = sy;
		
		this.width = width/2;
		this.height = height/2;
		
		//Creating body
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(((float)x)/96 + this.width, ((float)y)/96 + this.height);
		this.body = world.createBody(bodyDef);
		body.setFixedRotation(true);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(this.width/2, this.height);
		
		FixtureDef fixt = new FixtureDef();
		fixt.shape = shape;
		fixt.density = 1.f;
		
		this.body.createFixture(fixt);
		
		//this.body.createFixture(shape, 1.f);
		shape.dispose(); //Remove shape
		
		//body.setLinearVelocity(0.1f, 0.0f);
	}
	
	public void moveX(float mx){
		body.setLinearVelocity(mx, body.getLinearVelocity().y);
		
		if(mx > 0)
			right = true;
		else
			right = false;
	}
	
	public void moveY(float my) {
		//body.setLinearVelocity(body.getLinearVelocity().x, my);
		if(body.getLinearVelocity().y == 0)
			body.applyLinearImpulse(0, my, body.getPosition().x, body.getPosition().y, true);
		if(my < 0)
			down = true;
		else
			down = false;
	}
	
	public Body getBody() {
		return body;
	}
	
	public int getX() {
		return x;		
	}
		
	public int getY() {
		return y;
	}
		
	public void setX(int sx) {
		x = sx;
	
	}
	
	public void setY(int sy) {
		y = sy;
	
	}
	
	public boolean goingRight() {
		return right;
	}
	
	public boolean goingDown() {
		return down;
	}
	
	public float getBoxX() {
		return body.getPosition().x - this.width;
	}
	
	public float getBoxY() {
		return body.getPosition().y - this.height;
	}
		
}
