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
			//设置鼠标样式
			String path = this.getClass().getResource("/application/img/cursor.png").toString();
			root.setCursor(Cursor.cursor(path));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(this.getClass().getResource("game.css").toExternalForm());
			setResizable(true);
			setScene(scene);
			//设置无边框
			initStyle(StageStyle.UNDECORATED);
			//调用方法添加组件
			addComp(root);
//			setOnShown(new EventHandler<WindowEvent>() {
//				public void handle(WindowEvent event) {
//				}
//			});
			show();
			//最大化
			setMaximized(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void addComp(BorderPane root) {
		//游戏操作界面
		AnchorPane gamePane = new AnchorPane();
//		JAVAFXUtil.INSTANCE.setBackGroundImg(gamePane, getClass().getResource("/application/img/map/dibiao.jpg").toString());
		//画地图
		/**
		 * 需要多个画板
		 * 1.地形层       地表     山     水    沙漠     
		 * 2.角色层	建筑    土地  军队  农夫
		 * 3.操作层	划线    事件
		 * 4.小地图
		 */
		Canvas dibiao_canvas = new Canvas(GameData.width,GameData.height);
		
		//先设置背景图片
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
		//头顶信息栏
		GridPane infoPane = new GridPane();
		infoPane.setPrefHeight(30);
		infoPane.setStyle("-fx-background-image:url("+getClass().getResource("/application/img/top/bg.png").toString()+");-fx-background-size:100% 100%;-fx-background-color: none;");
		root.setTop(infoPane);
		//调用方法添加界面左边操作区域
		addMenuPane(root);
		//调用方法 添加小地图
		addSmallMap();
	}
	/**
	 * 左边操作区域
	 */
	private void addMenuPane(BorderPane root) {
		//左边菜单栏
		BorderPane menuPane = new BorderPane();
		menuPane.setPrefWidth(200);
		menuPane.setStyle("-fx-background-image:url("+getClass().getResource("/application/img/left/bg.png").toString()+");-fx-background-size:100% 100%;-fx-background-color: none;");
		//创建容器
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
	 * 小地图
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
	 * 地图地形
	 */
	private void drawMap() {
		GraphicsContext dibiaoG2D = GameData.dibiaoG2D;
		Image img = new Image(getClass().getResource("/application/img/map/dibiao.jpg").toString());
		dibiaoG2D.drawImage(img, 0, 0);
		//地形
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
