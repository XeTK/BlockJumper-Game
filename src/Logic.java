import java.io.IOException;
import java.util.ArrayList;

import level.LevelLoad;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import entities.Block;
import entities.Killer;
import entities.Player;


public class Logic 
{
	public void logic(ArrayList<Block> blocks,Player player) throws IOException 
	{
		//Keyboard
	    if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) 
	      System.exit(1);
	   
	    if (Keyboard.isKeyDown(Keyboard.KEY_L))
	    	new LevelLoad().spawnlevel(blocks);
	    
	    if (Keyboard.isKeyDown(Keyboard.KEY_Z))
	    	new LevelLoad().savelevel(blocks);
	    
	    if (Keyboard.isKeyDown(Keyboard.KEY_C))
	    	blocks.clear();
	    
	    if (Keyboard.isKeyDown(Keyboard.KEY_R))
	    {
	    	player.setY(200);
	    	player.setHealth(1000);
	    	Execution.xorig = 0;
	    }
	    
	    if (Keyboard.isKeyDown(Keyboard.KEY_0))
	    	player.setY(player.getY() + 5);
	    
	    if (Keyboard.isKeyDown(Keyboard.KEY_1))
	    	player.setY(player.getY() - 5);
	    
	    //Mouse
	    while (Mouse.next())
	    {
	    	if (Mouse.isButtonDown(0))
    		{
	    		Block tblock = new Block(Mouse.getX() - (int)Execution.xorig,Mouse.getY());
	    		if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL))
	    		{
	    			tblock = new Killer(Mouse.getX() - (int)Execution.xorig,Mouse.getY());
	    		}

	    		for (int i = 0; i < blocks.size();i++)
	    		{
	    			if (blocks.get(i).getX() == tblock.getX())
	    			{
	    				if (blocks.get(i).getY() == tblock.getY())
	    				{
	    					tblock = null;
	    					break;
	    				}
	    			}
	    		}
	    		if (tblock != null)
	    			blocks.add(tblock);

    		}
	    	
	    	if (Mouse.getEventButtonState())
	    	{
	    		if (Mouse.isButtonDown(1))
	    		{
	    			double cL = 32000;
	    			Block closest = null;
	    			for (int i = 0;i < blocks.size();i++)
	    			{
	    				float xL = ((blocks.get(i).getX() - Mouse.getX()) + Execution.xorig),
	    						yL = (blocks.get(i).getY() - Mouse.getY());
	    				double tL = Math.sqrt(Math.pow(xL, 2) + Math.pow(yL,2));
	    				if (tL < cL)
	    				{
	    					cL = tL;
	    					closest = blocks.get(i);
	    				}
	    			}
	    			blocks.remove(closest);
	    		}
	    	}
	    }
	    
	    //Entities Life
	    for (int i = 0;i <blocks.size();i++)
	    	blocks.get(i).life();
	    
	    player.runtime();
	    /*
	     * [0][1][2]
	     * [3][P][4]
	     * [5][6][7]
	     */
	    Block[] arround = new Block[8];
	    for (int i = 0; i < blocks.size();i++)
	    {
	    	float disty = (blocks.get(i).getY() - player.getY());
	    	float distx = ((blocks.get(i).getX() + Execution.xorig)) - player.getX();
		    
		    if ((disty <=51 && disty >= -51)&&(distx <=51 && distx >= -51))
		    {
		    	if (disty > 0)
		    	{
		    		if (distx >0)
		    		{
		    			arround[2] = blocks.get(i);
		    		}
		    		else if (distx < 0)
		    		{
		    			arround[0] = blocks.get(i);
		    		}
		    		else if (distx == 0)
		    		{
		    			arround[1] = blocks.get(i);
		    		}
		    	}
		    	else if (disty < 0)
		    	{
		    		if (distx >0)
		    		{
		    			arround[7] = blocks.get(i);
		    		}
		    		else if (distx < 0)
		    		{
		    			arround[5] = blocks.get(i);
		    		}
		    		else if (distx == 0)
		    		{
		    			arround[6] = blocks.get(i);
		    		}
		    	}
		    	else if (disty == 0)
		    	{
		    		if (distx >0)
		    		{
		    			arround[4] = blocks.get(i);
		    		}
		    		else if (distx < 0)
		    		{
		    			arround[3] = blocks.get(i);
		    		}
		    		else 
		    		{	//[P]
		    			player.setY((int) blocks.get(i).getY());
		    		}
		    	}
		    }
	    }
	    
	    //ALL THE DEBUG
	    printgrid(arround);
	    
	    if (player.isFalling())
	    	if (arround[6] != null||arround[7] != null||arround[5] != null)
	    		player.setFalling(false);
	    if ((arround[6] == null&&arround[7] == null)||(arround[6] == null&&arround[5] == null))
	    	player.setFalling(true);
	    
	    if (arround[6] != null)
	    	player.setY((int) arround[6].getY()+ 50);
	    else if (arround[7] != null)
	    	player.setY((int) arround[7].getY() + 50);
	    else if (arround[5] != null)
	    	player.setY((int) arround[5].getY() + 50);
	    
	    float speed = 25;
	    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
	    	speed = 5;
	    	
	    if (Keyboard.isKeyDown(Keyboard.KEY_D))
	    	if (arround[4] == null&&arround[2] == null)
	    		Execution.xorig -= speed;

	    if (Keyboard.isKeyDown(Keyboard.KEY_A))
	    	if (arround[3] == null&&arround[0] == null)
	    		Execution.xorig += speed;
	    
	    if (Keyboard.isKeyDown(Keyboard.KEY_W))
	    	if (arround[1] == null)
	    		player.execJump();
	    
	    if (arround[1] != null)
	    	if (arround[1].getType() == 1)
	    		player.minushealth(2);
	    if (arround[3] != null)
	    	if (arround[3].getType() == 1)
	    		player.minushealth(2);
	    if (arround[4] != null)
	    	if (arround[4].getType() == 1)
	    		player.minushealth(2);
	    if (arround[6] != null)
	    	if (arround[6].getType() == 1)
	    		player.minushealth(2);
	    
	}
	private void printgrid(Block[] arround)
	{
		if (arround[0] != null)
	    	System.out.print("[0]");
	    else
	    	System.out.print("[ ]");
	    
	    if (arround[1] != null)
	    	System.out.print("[1]");
	    else
	    	System.out.print("[ ]");
	    
	    if (arround[2] != null)
	    	System.out.print("[2]");
	    else
	    	System.out.print("[ ]");
	    
	    System.out.println();
	    
	    if (arround[3] != null)
	    	System.out.print("[3]");
	    else
	    	System.out.print("[ ]");
	    
	    System.out.print("[P]");
	    
	    if (arround[4] != null)
	    	System.out.print("[4]");
	    else
	    	System.out.print("[ ]");
	    
	    System.out.println();
	    
	    if (arround[5] != null)
	    	System.out.print("[5]");
	    else
	    	System.out.print("[ ]");
	    
	    if (arround[6] != null)
	    	System.out.print("[6]");
	    else
	    	System.out.print("[ ]");
	    if (arround[7] != null)
	    	System.out.print("[7]");
	    else
	    	System.out.print("[ ]");
	    
	    System.out.println("\n---------");
	}
}
