package de.victorswelt;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Scanner;

public class World {
	private static double scale = 0.005;// 1.0 / Math.tan(Math.PI * 0.99 / 2.0);
	
	Camera cam;
	ArrayList vertexes;
	ArrayList triangles;
	
	
	public World() {
		vertexes = new ArrayList();
		triangles = new ArrayList();
		cam = new Camera();
		System.out.println("FOV: " + scale);
		
		new Thread(new Runnable() {

			public void run() {
				Scanner scan = new Scanner(System.in);
				while(true) {
					if(scan.hasNextLine()) {
						double d = Double.parseDouble(scan.nextLine());
						scale = d;//1.0 / Math.tan(d / 2.0);
					}
				}
			}
			
		}).start();
		
		/*createTriangle(
				new Vertex(0.0f, 0f, 1.0f),
				new Vertex(-100.0f, -100f, -1.2f),
				new Vertex(100.0f, -100f, 1.2f));*/
		
		
		// create a plane
		for(int nx = -9; nx < 9; nx++) {
			for(int ny = 3; ny<13; ny++) {
				
				// scale the coordinates
				float sx = nx * 2f;
				float sy = 5f;
				float sz = ny * 2f + 0.1f;
				
				System.out.println(nx + " " + sy + " " + sz);
				
				// create the vertexes
				Vertex v1, v2, v3, v4;
				v1 = new Vertex(sx,       sy, sz);
				v2 = new Vertex(sx + 2f, sy, sz);
				v3 = new Vertex(sx,       sy, sz + 2f);
				v4 = new Vertex(sx + 2f, sy, sz + 2f);
				vertexes.add(v1);
				vertexes.add(v2);
				vertexes.add(v3);
				vertexes.add(v4);
				
				// create the triangles
				triangles.add(new Triangle(v1, v4, v3));
				triangles.add(new Triangle(v1, v2, v4));
			}
		}
	}
	
	public void render(Graphics g, int width, int height) {
		
		// transform every vertex
		// TODO transform the vertexes based on the camera coordinates
		// TODO do something if the z coordinate is 0
		for(int i = 0; i<vertexes.size(); i++) {
			Vertex vert = (Vertex) vertexes.get(i);
			
			vert.tx = (int) (vert.x) + width / 2;
			vert.ty = (int) (vert.y) + height / 2;
			vert.tx = (int) (vert.x / (vert.z * scale)) + width / 2;
			vert.ty = (int) (vert.y / (vert.z * scale)) + height / 2;
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
