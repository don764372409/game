package application;

import java.util.List;

/**
 * 角色类  画板第二层的所有物体   树木   农田  矿  建筑  农民等
 */
public abstract class Role {
	private int x;//数组中的x坐标
	private int y;//数组中的y坐标
	private int sceneX;//窗口中的x坐标
	private int sceneY;//窗口中的y坐标
	private int width;//宽度
	private int height;//高度
	private int type = 0;//角色类型  0-能动的   1-建筑等不能动的 默认能动
	private String state = "wait";//当前角色的当前动作状态  wait--等待   move--移动   挖矿等待..
	private String action;//当前动作(左走/右走)  比如升级  产兵  生产 等待  消失/变小/变大/显示
	//动作对应的图片
	public abstract List<String> getActionImgs();
	
	
	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getSceneX() {
		return sceneX;
	}
	public void setSceneX(int sceneX) {
		this.sceneX = sceneX;
	}
	public int getSceneY() {
		return sceneY;
	}
	public void setSceneY(int sceneY) {
		this.sceneY = sceneY;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
}
