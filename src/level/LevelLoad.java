package level;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import entities.Block;
import entities.Killer;


public class LevelLoad 
{
	public void spawnlevel(ArrayList<Block> blocks) throws IOException
	{
		ArrayList<String> lines = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader("E:\\Windows\\Desktop\\blah.bj"));
		String line = br.readLine();
		while (line != null)
		{
		    lines.add(line);
		    line = br.readLine();
		}
		br.close();
		
		for (int i = 0;i < lines.size();i++)
		{
			String[] parts = lines.get(i).split("\\|");
			if (parts.length < 3||Integer.valueOf(parts[2]) == 0)
			{
				Block tblock = new Block(Integer.valueOf(parts[0]),Integer.valueOf(parts[1]));
	    		for (int j = 0; j < blocks.size();j++)
	    		{
	    			if (blocks.get(j).getX() == tblock.getX())
	    			{
	    				if (blocks.get(j).getY() == tblock.getY())
	    				{
	    					tblock = null;
	    					break;
	    				}
	    			}
	    		}
	    		if (tblock != null)
	    			blocks.add(tblock);
			}
			else if (Integer.valueOf(parts[2]) == 1)
			{
				Killer tblock = new Killer(Integer.valueOf(parts[0]),Integer.valueOf(parts[1]));
	    		for (int j = 0; j < blocks.size();j++)
	    		{
	    			if (blocks.get(j).getX() == tblock.getX())
	    			{
	    				if (blocks.get(j).getY() == tblock.getY())
	    				{
	    					tblock = null;
	    					break;
	    				}
	    			}
	    		}
	    		if (tblock != null)
	    			blocks.add(tblock);
			}
		}
		br.close();
	}
	public void savelevel(ArrayList<Block> blocks) throws IOException
	{
		BufferedWriter out = new BufferedWriter(new FileWriter("E:\\Windows\\Desktop\\blah.bj"));
		for (int i = 0; i < blocks.size();i++)
			out.write((int)blocks.get(i).getX() + "|" + (int)blocks.get(i).getY() + "|" + (int)blocks.get(i).getType() + "\n");
		out.close();
	}
}
