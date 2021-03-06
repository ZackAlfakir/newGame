package com.ham.game;

import java.awt.Rectangle;
import java.util.ArrayList;

import org.lwjgl.opengl.Display;

import com.ham.engine.GameObject;
import com.ham.engine.Physics;
import com.ham.gameobject.CookieMonster;
import com.ham.gameobject.Player;
import com.ham.gameobject.item.Cube;

public class Game 
{
	public static Game game;
	
	private ArrayList<GameObject> objects;
	private ArrayList<GameObject> remove;
	
	private Player player;

	
	public Game()
	{
		remove = new ArrayList<GameObject>();
		objects = new ArrayList<GameObject>();
		player = new Player(Display.getWidth()/2 - Player.SIZE/2, Display.getHeight()/2 - Player.SIZE/2);
		objects.add(player);
		objects.add(new Cube(32,32));
		objects.add(new CookieMonster(300,500,1));
	}
	public ArrayList<GameObject> getObjects()
	{
		return objects;
	}
	public void getInput()
	{
		player.getInput();
	}
	public void update()
	{
		
		for (GameObject go : objects)
		{
			if (!go.getRemove())
				go.update();
			else
			{
				remove.add(go);
			}
		}
		for (GameObject go: remove)
			objects.remove(go);
	}
	public void render()
	{
		for (GameObject go : objects)
			go.render();
	}
	
	public static ArrayList<GameObject> sphereCollide(float x, float y, float radius)
	{
		ArrayList<GameObject> res = new ArrayList<GameObject>();
		
		for (GameObject go : game.getObjects())
		{
			if(Util.dist(go.getX(), go.getY(), x, y)< radius)
				res.add(go);
		}
		return res;
	}
	public static ArrayList<GameObject> rectangleCollide (float x1, float y1, float x2, float y2)
	{
		ArrayList<GameObject> res = new ArrayList<GameObject>();
		
		float sx = x2-x1;
		float sy = y2-y1;
		
		Rectangle collider = new Rectangle((int)x1,(int)y1,(int)sx,(int)sy);
		
		for (GameObject go : game.getObjects())
		{
			if(Physics.checkCollision(collider, go) != null)
				res.add(go);
		}
		
		return res;
	}
	
}
