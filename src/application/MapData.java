package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;

import application.thread.RoleThread;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

public class MapData{
	private int jiaodu = 20;
	private int bianchang = 15;
	private Random rd = new Random();
	private Farmer farmer;
	public void drawMap(AnchorPane acp) {
		//�ر�㻭��
		Canvas canvas = new Canvas(1920,1080);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		//��ɫ�㻭��  ��ɫ ��  ������
		Canvas canvas2 = new Canvas(1920,1080);
		//�����㻭��  ���ߵ�
		Canvas canvas3 = new Canvas(1920,1080);
		farmer = new Farmer();
		farmer.setWidth(60);
		farmer.setHeight(60);
		//��ũ����ӵ���ɫ������
		GameData.roles.add(farmer);
		//�õ���ͼ
		int[][] map = getMap();
		//���һ����ҳ�ʼ����
		int x = farmer.getX();
		int y = farmer.getY();
		while(true) {
			x= rd.nextInt(map.length);
			y = rd.nextInt(map[0].length);
			if (map[x][y]==1) {
					break;
			}
		}
		
		farmer.setX(x);
		farmer.setY(y);
		//��ʼ������x �� y�����ڴ�����λ��
		farmer.setSceneX(1920/2);
		farmer.setSceneY(1080/2);
		
		Image diImg;
		Image shanImg;
		Image shuiImg;
		{
			diImg = new Image(getClass().getResourceAsStream("/application/img/map/di.png"),40,40,false,false);
			shanImg = new Image(getClass().getResourceAsStream("/application/img/map/shan.png"),40,40,false,false);
			shuiImg = new Image(getClass().getResourceAsStream("/application/img/map/shui.png"),40,40,false,false);
		}
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				//�õ�x��� ��x-i��
				//�ó����������ڴ��ڵ�λ��
				int newX = farmer.getSceneX()+jiaodu*(j-y)-jiaodu*(i-x);
				int newY = farmer.getSceneY()+(bianchang+1)*(j-y)+(bianchang+1)*(i-x);
				if (newX<-40||newY<-40||newX>1960||newY>1120) {
					continue;
				}
				//ɽ
				if (map[i][j] ==2) {
					gc.drawImage(shanImg, newX, newY);
				//ˮ
				}else if(map[i][j] ==3) {
					gc.drawImage(shuiImg, newX, newY);
				//�յ�
				}else {
					gc.drawImage(diImg, newX, newY);
				}
				//���λ��
				if (x==i&&y==j) {
					farmer.setSceneX(newX);
					farmer.setSceneY(newY);
				}
			}
		}
		//������ɫ�����߳�
		new RoleThread().start();
		//��������ӵ�acp������
		acp.getChildren().add(canvas);
		acp.getChildren().add(canvas2);
		acp.getChildren().add(canvas3);
	}
	public void drawRect(GraphicsContext gc,int x,int y) {
		if (gc!=null) {
			gc.strokeLine(x, y,x+jiaodu, y+bianchang);
			gc.strokeLine(x+jiaodu, y+bianchang,x, y+bianchang*2);
			gc.strokeLine(x, y+bianchang*2,x-jiaodu, y+bianchang);
			gc.strokeLine(x-jiaodu, y+bianchang,x,y);
		}
	}
	public int[][] getMap(){
		String path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		path = path+"/application/img/map/map.jpg";
		File file = new File(path);
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int width = bi.getWidth();
		int height = bi.getHeight();
		int minx = bi.getMinX();
		int miny = bi.getMinY();
		
		//��ʼ������
		int[][] map = new int[width][height];
		for (int i = minx; i < width; i++) {
			for (int j = miny; j < height; j++) {
				int pixel = bi.getRGB(i, j); // �������д��뽫һ������ת��ΪRGB����
				int rgb0 = (pixel & 0xff0000) >> 16;
				//ˮ
				if (rgb0==0) {
					map[i][j] = 3;
				//ɽ��
				}else if(rgb0==111) {
					map[i][j] = 2;
				//�յ�
				}else {
					//Ĭ��0
					map[i][j] = 1;
				}
			}
		}
		return map;
	}
}
