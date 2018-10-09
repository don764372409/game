package application;

import java.util.List;

/**
 * ��ɫ��  ����ڶ������������   ��ľ   ũ��  ��  ����  ũ���
 */
public abstract class Role {
	private int x;//�����е�x����
	private int y;//�����е�y����
	private int sceneX;//�����е�x����
	private int sceneY;//�����е�y����
	private int width;//���
	private int height;//�߶�
	private int type = 0;//��ɫ����  0-�ܶ���   1-�����Ȳ��ܶ��� Ĭ���ܶ�
	private String state = "wait";//��ǰ��ɫ�ĵ�ǰ����״̬  wait--�ȴ�   move--�ƶ�   �ڿ�ȴ�..
	private String action;//��ǰ����(����/����)  ��������  ����  ���� �ȴ�  ��ʧ/��С/���/��ʾ
	//������Ӧ��ͼƬ
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
