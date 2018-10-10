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
	
	private double mouseClickX = 0;//当次鼠标点击是的位置
	private double mouseClickY = 0;//当次鼠标点击是的位置
	private boolean flag = true;//第一次鼠标按下开关
	
	private double currentX = 0;//鼠标的当前位置
	private double currentY = 0;//鼠标的当前位置
	
	/**
	 * 鼠标按下
	 */
	public void mousePressed(MouseEvent e) {
		//判断是否是左键单击
		if ("PRIMARY".equals(e.getButton().toString())) {
			//如果有被选择的角色
			if(GameData.selectedRoles.size()>0) {
				for (Role role : GameData.selectedRoles) {
					//判断是否能动
					//type - 0 能动  1-不能动
					if (role.getType()==0&&"wait".equals(role.getState())) {
						//将角色动作改为行走
						role.setState("move");
						//移动到当前坐标
						double x = e.getX();
						double y = e.getY();
						MoveRoleThread mrt = new MoveRoleThread();
						mrt.setRole(role);
						mrt.setX(x);
						mrt.setY(y);
						mrt.start();
						//清除选中框
						GraphicsContext gc = GameData.caozuoG2D;
						gc.clearRect(mouseClickX, mouseClickY, currentX-mouseClickX+5,currentY-mouseClickY+5);
					}
				}
			}
		}
	}
	
	/**
	 * 鼠标移动事件
	 */
	public void selectRoles(MouseEvent e) {
		//如果鼠标按下
		double x = e.getX();
		double y = e.getY();
		if (flag) {
			mouseClickX = x;
			mouseClickY = y;
			flag = false;
		}
		//画矩形
		drawRect(acp,x,y);
	}
	/**
	 * 鼠标弹起
	 */
	public void mouseReleased() {
		if(!flag) {
			flag = true;
		}
		//鼠标弹起时，之前画的线都清除
		GraphicsContext gc = GameData.caozuoG2D;
		gc.clearRect(mouseClickX, mouseClickY, currentX-mouseClickX+5,currentY-mouseClickY+5);
		//画完之后判断有没有圈住角色
		for (Role role : GameData.roles) {
			int roleX = role.getSceneX();
			int roleY = role.getSceneY();
			int roleWidth = role.getWidth();
			int roleHeight = role.getHeight();
			//判断圈住了
			if (roleX>=mouseClickX&&roleX+roleWidth<=currentX&&roleY>=mouseClickY&&roleY+roleHeight<=currentY) {
				//给角色画个圈
				gc.strokeRect(roleX, roleY,roleWidth, roleHeight);
				//将被圈住的角色，添加到集合中
				GameData.selectedRoles.add(role);
			}
		}
	}
	/**
	 * 画矩形
	 * @param acp_top2
	 * @param y 
	 * @param x 
	 */
	private void drawRect(AnchorPane acp_top2, double x, double y) {
		GraphicsContext gc = GameData.caozuoG2D;
		gc.setStroke(Color.YELLOW); 
		//如果当前鼠标位置小于鼠标上一次位置(也就是上一次记录的鼠标最终位置，鼠标在回拉),就将坐标交换清除
		if (x<currentX||y<currentY) {
			gc.clearRect(mouseClickX, mouseClickY, currentX-mouseClickX+5,currentY-mouseClickY+5);
		}else {
			//当次画矩形时，先清除上一次的
			gc.clearRect(mouseClickX, mouseClickY, x-mouseClickX,y-mouseClickY);
		}
		gc.clearRect(x, y, mouseClickX-x+5,mouseClickY-y+5);
		gc.strokeRect(mouseClickX, mouseClickY, x-mouseClickX, y-mouseClickY);
		//鼠标移动一次就更新一次最新当前鼠标的位置
		currentX = x;
		currentY = y;
	}
	public void initialize(URL location, ResourceBundle resources) {
		Image shiliao = new Image(getClass().getResourceAsStream("/application/img/top/shiliao.png"),30,30,false,false);
		shiliao_number.setGraphic(new ImageView(shiliao));
		Tooltip shiliaotip = new Tooltip();
		shiliaotip.setText("石料:"+shiliao_number.getText());
		shiliao_number.setTooltip(shiliaotip);
		
		Image liangcao = new Image(getClass().getResourceAsStream("/application/img/top/liangcao.png"),30,30,false,false);
		liangcao_number.setGraphic(new ImageView(liangcao));
		Tooltip liangcaotip = new Tooltip();
		liangcaotip.setText("粮草:"+liangcao_number.getText());
		liangcao_number.setTooltip(liangcaotip);
		
		Image tie = new Image(getClass().getResourceAsStream("/application/img/top/tie.png"),30,30,false,false);
		tie_number.setGraphic(new ImageView(tie));
		Tooltip tietip = new Tooltip();
		tietip.setText("铁矿:"+tie_number.getText());
		tie_number.setTooltip(tietip);
		
		Image tong = new Image(getClass().getResourceAsStream("/application/img/top/tong.png"),30,30,false,false);
		tong_number.setGraphic(new ImageView(tong));
		Tooltip tongtip = new Tooltip();
		tongtip.setText("铜矿:"+tong_number.getText());
		tong_number.setTooltip(tongtip);
		
		Image qian = new Image(getClass().getResourceAsStream("/application/img/top/qian.png"),30,30,false,false);
		qian_number.setGraphic(new ImageView(qian));
		Tooltip qiantip = new Tooltip();
		qiantip.setText("金币:"+qian_number.getText());
		qian_number.setTooltip(qiantip);
		
		new MapData().drawMap(acp);
	}
}
