package application;

import java.net.URL;
import java.util.ResourceBundle;

import application.thread.MoveRoleThread;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class GameController implements Initializable{
	@FXML
	private Label shiliao_number;
	@FXML
	private Label liangcao_number;
	@FXML
	private Label tie_number;
	@FXML
	private Label tong_number;
	@FXML
	private Label qian_number;
	@FXML 
	private AnchorPane acp;
	
	private double mouseClickX = 0;//����������ǵ�λ��
	private double mouseClickY = 0;//����������ǵ�λ��
	private boolean flag = true;//��һ����갴�¿���
	
	private double currentX = 0;//���ĵ�ǰλ��
	private double currentY = 0;//���ĵ�ǰλ��
	
	/**
	 * ��갴��
	 */
	public void mousePressed(MouseEvent e) {
		//�ж��Ƿ����������
		if ("PRIMARY".equals(e.getButton().toString())) {
			//����б�ѡ��Ľ�ɫ
			if(GameData.selectedRoles.size()>0) {
				for (Role role : GameData.selectedRoles) {
					//�ж��Ƿ��ܶ�
					//type - 0 �ܶ�  1-���ܶ�
					if (role.getType()==0&&"wait".equals(role.getState())) {
						//����ɫ������Ϊ����
						role.setState("move");
						//�ƶ�����ǰ����
						double x = e.getX();
						double y = e.getY();
						MoveRoleThread mrt = new MoveRoleThread();
						mrt.setRole(role);
						mrt.setX(x);
						mrt.setY(y);
						mrt.start();
						//���ѡ�п�
						GraphicsContext gc = GameData.caozuoG2D;
						gc.clearRect(mouseClickX, mouseClickY, currentX-mouseClickX+5,currentY-mouseClickY+5);
					}
				}
			}
		}
	}
	
	/**
	 * ����ƶ��¼�
	 */
	public void selectRoles(MouseEvent e) {
		//�����갴��
		double x = e.getX();
		double y = e.getY();
		if (flag) {
			mouseClickX = x;
			mouseClickY = y;
			flag = false;
		}
		//������
		drawRect(acp,x,y);
	}
	/**
	 * ��굯��
	 */
	public void mouseReleased() {
		if(!flag) {
			flag = true;
		}
		//��굯��ʱ��֮ǰ�����߶����
		GraphicsContext gc = GameData.caozuoG2D;
		gc.clearRect(mouseClickX, mouseClickY, currentX-mouseClickX+5,currentY-mouseClickY+5);
		//����֮���ж���û��Ȧס��ɫ
		for (Role role : GameData.roles) {
			int roleX = role.getSceneX();
			int roleY = role.getSceneY();
			int roleWidth = role.getWidth();
			int roleHeight = role.getHeight();
			//�ж�Ȧס��
			if (roleX>=mouseClickX&&roleX+roleWidth<=currentX&&roleY>=mouseClickY&&roleY+roleHeight<=currentY) {
				//����ɫ����Ȧ
				gc.strokeRect(roleX, roleY,roleWidth, roleHeight);
				//����Ȧס�Ľ�ɫ����ӵ�������
				GameData.selectedRoles.add(role);
			}
		}
	}
	/**
	 * ������
	 * @param acp_top2
	 * @param y 
	 * @param x 
	 */
	private void drawRect(AnchorPane acp_top2, double x, double y) {
		GraphicsContext gc = GameData.caozuoG2D;
		gc.setStroke(Color.YELLOW); 
		//�����ǰ���λ��С�������һ��λ��(Ҳ������һ�μ�¼���������λ�ã�����ڻ���),�ͽ����꽻�����
		if (x<currentX||y<currentY) {
			gc.clearRect(mouseClickX, mouseClickY, currentX-mouseClickX+5,currentY-mouseClickY+5);
		}else {
			//���λ�����ʱ���������һ�ε�
			gc.clearRect(mouseClickX, mouseClickY, x-mouseClickX,y-mouseClickY);
		}
		gc.clearRect(x, y, mouseClickX-x+5,mouseClickY-y+5);
		gc.strokeRect(mouseClickX, mouseClickY, x-mouseClickX, y-mouseClickY);
		//����ƶ�һ�ξ͸���һ�����µ�ǰ����λ��
		currentX = x;
		currentY = y;
	}
	public void initialize(URL location, ResourceBundle resources) {
		Image shiliao = new Image(getClass().getResourceAsStream("/application/img/top/shiliao.png"),30,30,false,false);
		shiliao_number.setGraphic(new ImageView(shiliao));
		Tooltip shiliaotip = new Tooltip();
		shiliaotip.setText("ʯ��:"+shiliao_number.getText());
		shiliao_number.setTooltip(shiliaotip);
		
		Image liangcao = new Image(getClass().getResourceAsStream("/application/img/top/liangcao.png"),30,30,false,false);
		liangcao_number.setGraphic(new ImageView(liangcao));
		Tooltip liangcaotip = new Tooltip();
		liangcaotip.setText("����:"+liangcao_number.getText());
		liangcao_number.setTooltip(liangcaotip);
		
		Image tie = new Image(getClass().getResourceAsStream("/application/img/top/tie.png"),30,30,false,false);
		tie_number.setGraphic(new ImageView(tie));
		Tooltip tietip = new Tooltip();
		tietip.setText("����:"+tie_number.getText());
		tie_number.setTooltip(tietip);
		
		Image tong = new Image(getClass().getResourceAsStream("/application/img/top/tong.png"),30,30,false,false);
		tong_number.setGraphic(new ImageView(tong));
		Tooltip tongtip = new Tooltip();
		tongtip.setText("ͭ��:"+tong_number.getText());
		tong_number.setTooltip(tongtip);
		
		Image qian = new Image(getClass().getResourceAsStream("/application/img/top/qian.png"),30,30,false,false);
		qian_number.setGraphic(new ImageView(qian));
		Tooltip qiantip = new Tooltip();
		qiantip.setText("���:"+qian_number.getText());
		qian_number.setTooltip(qiantip);
		
		new MapData().drawMap(acp);
	}
}
