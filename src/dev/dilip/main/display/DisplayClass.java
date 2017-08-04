package dev.dilip.main.display;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;


public class DisplayClass {
	
	private int width,height;
	private String title;
	
	public DisplayClass(String title,int width,int height){
		this.title=title;
		this.height=height;
		this.width=width;
		init();
	}
	
	
	public void init(){
		try {
			Display.setDisplayMode(new DisplayMode(width,height));
			Display.setTitle(title);
			Display.create();
			
			
			
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		
		glEnable(GL_BLEND);
		glDisable(GL_DEPTH_TEST);
		glDisable(GL_LIGHTING); 
		glBlendFunc(GL_SRC_ALPHA , GL_ONE_MINUS_SRC_ALPHA);
		glViewport(0,0,width,height);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0,width,height,0,1,-1);
		glMatrixMode(GL_MODELVIEW);
		
		
	}
	
}
