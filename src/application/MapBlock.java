package application;
/**
 * 地形块
 */
public class MapBlock implements Cloneable{
	private double x;//坐标
	private double y;
	/*
	 * 地形类型     1.山   2.水  3.草地   4.石头地 5.沙漠   6.其他  
	 * 不同的地形可以操作不一样(角色行走在沙漠可能减速)  山的范围内不能创建角色等待
	 */
	private int type; 
	private double width;//占地宽度
	private double height;//占地高度
	private String imgPath;//图片路径
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
