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
 * ��Ϸ������
 */
public class GameData {
	public static int width;
	public static int height;
	public static Stage loginStage = null;
	public static Stage gameStage = null;
	public static GraphicsContext dibiaoG2D;//��һ�� ����
	public static GraphicsContext rolesG2D;//�ڶ��� ����
	public static GraphicsContext caozuoG2D;//������ ����
	public static GraphicsContext smallMapG2D;//���Ĳ� ����
	//�˸�����ĳ���
	public static String left = "left";
	public static String right = "right";
	public static String top = "top";
	public static String bottom = "bottom";
	public static String left_top = "left_top";
	public static String left_bottom = "left_bottom";
	public static String right_top = "right_top";
	public static String right_bottom = "right_bottom";
	
	//��ǰ������,���еĽ�ɫ
	public static HashSet<Role> roles = new HashSet<>();
	//װ��ѡ�еĽ�ɫ
	public static HashSet<Role> selectedRoles = new HashSet<>();
	//��һ����ο�
	public static List<MapBlock> dibiao = new ArrayList<>();
	
	static {
		width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	}
	//��ʱ������
	public static boolean empFlag = false;
	//��קʱ,��ʱ����ͼƬ·��
	public static Image empImg;
	//��קʱ,��Ӧ����ʱ�ؿ����
	public static MapBlock empMb = new MapBlock();
	//��ʱ��ͼ�鼯��
	public static LinkedList<MapBlock> empMbs= new LinkedList<>();
	public static double empWidth;//��ʱͼƬ�Ŀ�
	public static double empHeight;//��ʱͼƬ�Ŀ�
}
