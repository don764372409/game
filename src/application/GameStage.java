package application;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GameStage extends Stage{
	public GameStage() {
		try {
			BorderPane root = new BorderPane();
			//调用方法添加组件
			addComp(root);
			//设置鼠标样式
			String path = this.getClass().getResource("/application/img/cursor.png").toString();
			root.setCursor(Cursor.cursor(path));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(this.getClass().getResource("game.css").toExternalForm());
			setResizable(true);
			setScene(scene);
			setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent event) {
					System.err.println("退出游戏");
					System.exit(1);
				}
			});
			show();
			//最大化
			setMaximized(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addComp(BorderPane root) {
		//头顶信息栏
		GridPane infoPane = new GridPane();
		infoPane.setPrefHeight(30);
		infoPane.setStyle("-fx-background-image:url("+getClass().getResource("/application/img/top/bg.png").toString()+");-fx-background-size:100% 100%;-fx-background-color: none;");
		root.setTop(infoPane);
		//游戏操作界面
		AnchorPane gamePane = new AnchorPane();
		//画地图
		/**
		 * 需要多个画板
		 * 1.地形层       地表     山     水    沙漠     
		 * 2.角色层	建筑    土地  军队  农夫
		 * 3.操作层	划线    事件
		 * 4.小地图
		 */
		Canvas dibiao_canvas = new Canvas();
		GameData.dibiaoG2D = dibiao_canvas.getGraphicsContext2D();
		root.getChildren().add(dibiao_canvas);
		
		Canvas roles_canvas = new Canvas();
		GameData.rolesG2D = roles_canvas.getGraphicsContext2D();
		root.getChildren().add(roles_canvas);
		
		Canvas caozuo_canvas = new Canvas();
		GameData.caozuoG2D = caozuo_canvas.getGraphicsContext2D();
		root.getChildren().add(caozuo_canvas);
		
		Canvas small_canvas = new Canvas();
		GameData.smallMapG2D = small_canvas.getGraphicsContext2D();
		root.getChildren().add(small_canvas);
		//调用方法  画地形
		drawMap();
		root.setCenter(gamePane);
		
		//左边菜单栏
		BorderPane menuPane = new BorderPane();
		menuPane.setPrefWidth(200);
		menuPane.setStyle("-fx-background-image:url("+getClass().getResource("/application/img/left/bg.png").toString()+");-fx-background-size:100% 100%;-fx-background-color: none;");
		root.setLeft(menuPane);
	}
	/**
	 * 画普通地形
	 */
	private void drawMap() {
		
	}
}
