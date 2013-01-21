import java.io.IOException;
import java.util.ArrayList;

import level.LevelLoad;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import entities.Block;
import entities.Player;
import entities.Point;


public class Execution 
{
	private boolean finished = false;
	private ArrayList<Block> blocks = new ArrayList<Block>();
	public static float xorig = 0;
	private Player player = new Player(400,200);
	public Execution() throws Exception
	{
		Screen scr = new Screen();
		scr.init();	
		new LevelLoad().spawnlevel(blocks);
		runtime();
	}
	public void runtime() throws IOException
	{
		Logic logic = new Logic();
		while (!finished) 
		{
			Display.update();

			if (Display.isCloseRequested())
				finished = true;

			else if (Display.isActive())
				logic.logic(blocks,player);

			if (Display.isVisible() || Display.isDirty())
				globaldraw();

		}
	}

	private void globaldraw()
	{
		GL11.glMatrixMode(GL11.GL_PROJECTION);
	    GL11.glLoadIdentity();
	    GL11.glOrtho(0, Display.getDisplayMode().getWidth(), 0, Display.getDisplayMode().getHeight(), -1, 1);
	    GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_STENCIL_BUFFER_BIT);
        for (int i = 0; i < blocks.size();i++)
        	blocks.get(i).onDraw(xorig);
        player.onDraw();
        Display.sync(120);
        setPoints();
	}
	private void setPoints()
	{
		for (int i = 0; i < 20;i++)
		{
			for (int j = 0; j < 20;j++)
			{
				GL11.glColor3d(255, 255, 255);
			    GL11.glPushMatrix();
			    GL11.glTranslatef((i * 50), (j *50), 0.5f);
			 
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
	}
	
}
