package de.victorswelt;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class World {
	Camera cam;
	ArrayList vertexes;
	ArrayList triangles;
	
	
	public World() {
		vertexes = new ArrayList();
		triangles = new ArrayList();
		cam = new Camera();
		
		createTriangle(
				new Vertex(0.0f, 0f, 1.0f),
				new Vertex(-100.0f, -100f, -1.2f),
				new Vertex(100.0f, -100f, 1.2f));
	}
	
	public void render(Graphics g, int width, int height) {
		
		// transform every vertex
		// TODO transform the vertexes based on the camera coordinates
		// TODO do something if the z coordinate is 0
		for(int i = 0; i<vertexes.size(); i++) {
			Vertex vert = (Vertex) vertexes.get(i);
			
			vert.tx = (int) (vert.x / vert.z) + width / 2;
			vert.ty = (int) (vert.y / vert.z) + height / 2;
		}
		
		// draw every triangle
		g.setColor(Color.WHITE);
		for(int i = 0; i<triangles.size(); i++) {
			Triangle t = (Triangle) triangles.get(i);
			g.drawLine(t.v1.tx, t.v1.ty, t.v2.tx, t.v2.ty);
			g.drawLine(t.v1.tx, t.v1.ty, t.v3.tx, t.v3.ty);
			g.drawLine(t.v2.tx, t.v2.ty, t.v3.tx, t.v3.ty);
		}
	}
	
	void createTriangle(Vertex v1, Vertex v2, Vertex v3) {
		vertexes.add(v1);
		vertexes.add(v2);
		vertexes.add(v3);
		
		triangles.add(new Triangle(v1, v2, v3));
		
	}
	
}
