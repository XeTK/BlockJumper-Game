package entities;
import org.lwjgl.opengl.GL11;

public class Block 
{
	protected int size = 25, x = 0, y = 0, type = 0, red = 255, blue = 255, green = 255;
	public Block(int x, int y)
	{
		int ix = 0, iy = 0;
		while (true)
		{
			ix++;
			if ((ix * (2 * size)) >= x)
			{
				x = (ix * (2 * size)); 
				break;
			}
		}
		while (true)
		{
			iy++;
			if ((iy * (2 * size)) >= y)
			{
				y = (iy * (2 * size)); 
				break;
			}
		}
		setXY(x,y);
	}
	public void setXY(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	public void onDraw(float xorig)
	{
	    GL11.glColor3d(red, blue, green);
	    GL11.glPushMatrix();
	    GL11.glTranslatef(x - size + xorig, y - size, 0.5f);
	 
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
	public void life(){}
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getType() {
		return type;
	}
	
}
