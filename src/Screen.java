import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
public class Screen 
{
	public static void main(String[] args)
	{
		try 
		{
			new Execution();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	public void init() throws LWJGLException
	{
		Display.setTitle("BlockJumper");
		Display.setFullscreen(false);
		Display.setVSyncEnabled(true);
		Display.setDisplayMode(new DisplayMode(800,600));
		Display.create();
	}
}
