package application;

import java.io.File;

import application.util.JAVAFXUtil;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GameStage extends Stage{
	public GameStage() {
		try {
			BorderPane root = new BorderPane();
			//���������ʽ
			String path = this.getClass().getResource("/application/img/cursor.png").toString();
			root.setCursor(Cursor.cursor(path));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(this.getClass().getResource("game.css").toExternalForm());
			setResizable(true);
			setScene(scene);
			//�����ޱ߿�
			initStyle(StageStyle.UNDECORATED);
			//���÷���������
			addComp(root);
//			setOnShown(new EventHandler<WindowEvent>() {
//				public void handle(WindowEvent event) {
//				}
//			});
			show();
			//���
			setMaximized(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void addComp(BorderPane root) {
		//��Ϸ��������
		AnchorPane gamePane = new AnchorPane();
//		JAVAFXUtil.INSTANCE.setBackGroundImg(gamePane, getClass().getResource("/application/img/map/dibiao.jpg").toString());
		//����ͼ
		/**
		 * ��Ҫ�������
		 * 1.���β�       �ر�     ɽ     ˮ    ɳĮ     
		 * 2.��ɫ��	����    ����  ����  ũ��
		 * 3.������	����    �¼�
		 * 4.С��ͼ
		 */
		Canvas dibiao_canvas = new Canvas(GameData.width,GameData.height);
		
		//�����ñ���ͼƬ
		GameData.dibiaoG2D = dibiao_canvas.getGraphicsContext2D();
		root.getChildren().add(dibiao_canvas);
		
		Canvas roles_canvas = new Canvas(GameData.width,GameData.height);
		GameData.rolesG2D = roles_canvas.getGraphicsContext2D();
		root.getChildren().add(roles_canvas);
		
		Canvas caozuo_canvas = new Canvas(GameData.width,GameData.height);
		GameData.caozuoG2D = caozuo_canvas.getGraphicsContext2D();
		root.getChildren().add(caozuo_canvas);
		
		Canvas small_canvas = new Canvas(GameData.width,GameData.height);
		GameData.smallMapG2D = small_canvas.getGraphicsContext2D();
		root.getChildren().add(small_canvas);
		drawMap();
		root.setCenter(gamePane);
		//ͷ����Ϣ��
		GridPane infoPane = new GridPane();
		infoPane.setPrefHeight(30);
		infoPane.setStyle("-fx-background-image:url("+getClass().getResource("/application/img/top/bg.png").toString()+");-fx-background-size:100% 100%;-fx-background-color: none;");
		root.setTop(infoPane);
		//���÷�����ӽ�����߲�������
		addMenuPane(root);
		//���÷��� ���С��ͼ
		addSmallMap();
	}
	/**
	 * ��߲�������
	 */
	private void addMenuPane(BorderPane root) {
		//��߲˵���
		BorderPane menuPane = new BorderPane();
		menuPane.setPrefWidth(200);
		menuPane.setStyle("-fx-background-image:url("+getClass().getResource("/application/img/left/bg.png").toString()+");-fx-background-size:100% 100%;-fx-background-color: none;");
		//��������
		FlowPane menu_bottom_pane = new FlowPane();
//		menu_bottom_pane.setPrefHeight(GameData.height*0.6);
		
//		JAVAFXUtil.INSTANCE.setBackGroundColor(menu_bottom_pane, "red");
		for (int i = 1; i < 146; i++) {
			
			Label label = new Label();
			label.setPrefHeight(100);
			label.setPrefWidth(100);
			
			JAVAFXUtil.INSTANCE.setBackGroundImgAndSize(label,getClass().getResource("/application/img/dixing/dibiao"+i+".png").toString(),"100%","100%");
			menu_bottom_pane.getChildren().add(label);
		}
		
		
		
		menuPane.setBottom(menu_bottom_pane);
		root.setLeft(menuPane);
	}
	/**
	 * С��ͼ
	 * @param menuPane 
	 */
	private void addSmallMap() {
		GraphicsContext smallMapG2D = GameData.smallMapG2D;
		smallMapG2D.setStroke(Color.BLACK);
		smallMapG2D.strokeRect(GameData.width-200-1, 30, 202, 203);
		smallMapG2D.restore();
		smallMapG2D.drawImage(new Image(getClass().getResourceAsStream("/application/img/map/dibiao.jpg")), GameData.width-200, 30+2,200,200);
		
	}
	/**
	 * ��ͼ����
	 */
	private void drawMap() {
		GraphicsContext dibiaoG2D = GameData.dibiaoG2D;
		Image img = new Image(getClass().getResource("/application/img/map/dibiao.jpg").toString());
		dibiaoG2D.drawImage(img, 0, 0);
		//����
//		Image img2 = new Image(getClass().getResource("/application/img/dixing/shanya001.png").toString());
//		dibiaoG2D.drawImage(img2, 180, -20);
//		img2 = new Image(getClass().getResource("/application/img/dixing/shanya002.png").toString());
//		dibiaoG2D.drawImage(img2, 180, 0);
//		img2 = new Image(getClass().getResource("/application/img/dixing/shanya23[1].png").toString());
//		dibiaoG2D.drawImage(img2, 180, 0);
//		img2 = new Image(getClass().getResource("/application/img/dixing/shanya23[1].png").toString());
//		dibiaoG2D.drawImage(img2, 180, 0);
//		img2 = new Image(getClass().getResource("/application/img/dixing/shanya23[1].png").toString());
//		dibiaoG2D.drawImage(img2, 180, 0);
//		img2 = new Image(getClass().getResource("/application/img/dixing/shanya23[1].png").toString());
//		dibiaoG2D.drawImage(img2, 180, 0);
//		img2 = new Image(getClass().getResource("/application/img/dixing/shanya23[1].png").toString());
//		dibiaoG2D.drawImage(img2, 180, 0);
//		img2 = new Image(getClass().getResource("/application/img/dixing/shanya23[1].png").toString());
//		dibiaoG2D.drawImage(img2, 180, 0);
//		img2 = new Image(getClass().getResource("/application/img/dixing/shanya23[1].png").toString());
//		dibiaoG2D.drawImage(img2, 180, 0);
//		img2 = new Image(getClass().getResource("/application/img/dixing/shanya23[1].png").toString());
//		dibiaoG2D.drawImage(img2, 180, 0);
	}
}
