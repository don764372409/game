package application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
/**
 * ��Ϸ������
 */
public class GameData {
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
	public static List<MapBlock> diaobiao = new ArrayList<>();
}
