package application;
/**
 * ���ο�
 */
public class MapBlock implements Cloneable{
	private double x;//����
	private double y;
	/*
	 * ��������     1.ɽ   2.ˮ  3.�ݵ�   4.ʯͷ�� 5.ɳĮ   6.����  
	 * ��ͬ�ĵ��ο��Բ�����һ��(��ɫ������ɳĮ���ܼ���)  ɽ�ķ�Χ�ڲ��ܴ�����ɫ�ȴ�
	 */
	private int type; 
	private double width;//ռ�ؿ��
	private double height;//ռ�ظ߶�
	private String imgPath;//ͼƬ·��
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
}
