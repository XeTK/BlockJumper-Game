package entities;

import org.lwjgl.opengl.GL11;

public class Point extends Block
{
	public Point(int x, int y) {
		super(x, y);
	}
	@Override 
	public void onDraw(float xorig)
	{
	    GL11.glColor3d(255, 255, 255);
	    GL11.glPushMatrix();
	    GL11.glTranslatef(x + xorig, y + xorig, 0.5f);
	 
	      // rotate square according to angle
	      GL11.glRotatef(0, 0, 0, 1.0f);
	      GL11.glBegin(GL11.GL_QUADS);
	        GL11.glVertex2i(-1, -1);
	        GL11.glVertex2i(+1, -1);
	        GL11.glVertex2i(+1, +1);
	        GL11.glVertex2i(-1, +1);
	      GL11.glEnd();
	 
	    GL11.glPopMatrix();
	}
}
