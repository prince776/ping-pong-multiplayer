package dev.dilip.main.gfx;
import static org.lwjgl.opengl.GL11.*;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;


public class Renderer {
	private static int displayListId;
	
	private static Font awtFont,awtFont2;
	public static TrueTypeFont fontSmall,fontBig;
	
	
	public Renderer(){
		
	}
	
	public static void init(){
		displayListId=glGenLists(1);
		awtFont = new Font("Verdana",Font.PLAIN,24);
		awtFont2 = new Font("Verdana",Font.PLAIN,14);
		fontSmall =new TrueTypeFont(awtFont,true);
		fontBig =new TrueTypeFont(awtFont2,true);

	}
	
	public static void renderFont(float x, float y,String text,boolean big){
		glEnable(GL_TEXTURE_2D);
		if(big)
			fontBig.drawString(x, y, text,Color.black);
		else
			fontSmall.drawString(x, y, text,Color.black);
		glDisable(GL_TEXTURE_2D);
	}
	
	public static void renderQuad(float x,float y,float width,float height,float[] color){
		glDisable(GL_TEXTURE_2D);

		glColor4f(color[0],color[1],color[2],color[3]);
		glNewList(displayListId, GL_COMPILE);
		glBegin(GL_QUADS);
			glVertex2f(x,y);
			glVertex2f(x+width,y);
			glVertex2f(x+width,y+height);
			glVertex2f(x,y+height);
		
		
		
		glEnd();
		glEndList();
		glCallList(displayListId);
	}
	
	
	public static void renderRect(float x,float y,float width,float height,float[] color){
		glDisable(GL_TEXTURE_2D);

		glColor4f(color[0],color[1],color[2],color[3]);
		glNewList(displayListId, GL_COMPILE);
		glBegin(GL_LINE_STRIP);
			glVertex2f(x,y);
			glVertex2f(x+width,y);
			glVertex2f(x+width,y+height);
			glVertex2f(x,y+height);
			glVertex2f(x,y);
		glEnd();
		glEndList();
		glCallList(displayListId);
	}
	
	public static void renderLine(float x1,float y1,float x2,float y2,float[] color){
		glDisable(GL_TEXTURE_2D);

		glColor4f(color[0],color[1],color[2],color[3]);
		glNewList(displayListId, GL_COMPILE);
		glBegin(GL_LINES);
			glVertex2f(x1,y1);
			glVertex2f(x2,y2);
		glEnd();
		glEndList();
		glCallList(displayListId);
		
	}
	
	public static void destroy(){
		
		glDeleteLists(displayListId,1);
	}
	
}
