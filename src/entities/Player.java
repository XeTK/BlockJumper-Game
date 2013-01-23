package entities;

import org.lwjgl.opengl.GL11;

public class Player 
{
	private int x = 0, y = 0, jumpTick = 0, volocity = 0, health = 100;
	private boolean jumping = false, falling = false;
	public Player(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	private void jump()
	{
		if (jumpTick >= 20)
		{
			falling = true;
			jumping = false;
		}
		jumpTick += 1;
		y += 3;

	}
	private void fall()
	{
		if (volocity < 50)
			volocity++;
		y -= volocity;
	}
	public void runtime()
	{
		if (jumping)
			jump();
		if (falling)
			fall();
		else
			volocity = 0;
		
	}
	public void execJump()
	{
		jumping = true;
	}
	public void onDraw()
	{
		int size = 25;
		GL11.glColor3d(0, 255, 255);
	    GL11.glPushMatrix();
	    GL11.glTranslatef(x - size, (y - size), 0.5f);
	 
	      // rotate square according to angle
	      GL11.glRotatef(0, 0, 0, 1.0f);
	      GL11.glBegin(GL11.GL_QUADS);
	        GL11.glVertex2i(-size, -size);
	        GL11.glVertex2i(size, -size);
	        GL11.glVertex2i(size, size);
	        GL11.glVertex2i(-size, size);
	      GL11.glEnd();
	 
	    GL11.glPopMatrix();
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public boolean isFalling() {
		return falling;
	}
	public void setFalling(boolean falling) {
		this.falling = falling;
	}
	public boolean isJumping() {
		return jumping;
	}
	
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public void minushealth(int remove)
	{
		y += 100;
		health -= remove;
		if (health <=0)
			y = -9999;
	}
}
