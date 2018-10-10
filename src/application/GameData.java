package application;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
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
}
