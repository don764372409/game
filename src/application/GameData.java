package application;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
/**
 * 游戏数据类
 */
public class GameData {
	public static int width;
	public static int height;
	public static Stage loginStage = null;
	public static Stage gameStage = null;
	public static GraphicsContext dibiaoG2D;//第一层 画布
	public static GraphicsContext rolesG2D;//第二层 画布
	public static GraphicsContext caozuoG2D;//第三层 画布
	public static GraphicsContext smallMapG2D;//第四层 画布
	//八个方向的常量
	public static String left = "left";
	public static String right = "right";
	public static String top = "top";
	public static String bottom = "bottom";
	public static String left_top = "left_top";
	public static String left_bottom = "left_bottom";
	public static String right_top = "right_top";
	public static String right_bottom = "right_bottom";
	
	//当前场景中,所有的角色
	public static HashSet<Role> roles = new HashSet<>();
	//装被选中的角色
	public static HashSet<Role> selectedRoles = new HashSet<>();
	//第一层地形块
	public static List<MapBlock> dibiao = new ArrayList<>();
	
	static {
		width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	}
	//临时画开关
	public static boolean empFlag = false;
	//拖拽时,临时画的图片路径
	public static Image empImg;
	//拖拽时,对应的临时地块对象
	public static MapBlock empMb = new MapBlock();
	//临时地图块集合
	public static LinkedList<MapBlock> empMbs= new LinkedList<>();
	public static double empWidth;//临时图片的宽
	public static double empHeight;//临时图片的宽
}
