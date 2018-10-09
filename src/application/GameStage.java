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
			//���÷���������
			addComp(root);
			//���������ʽ
			String path = this.getClass().getResource("/application/img/cursor.png").toString();
			root.setCursor(Cursor.cursor(path));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(this.getClass().getResource("game.css").toExternalForm());
			setResizable(true);
			setScene(scene);
			setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent event) {
					System.err.println("�˳���Ϸ");
					System.exit(1);
				}
			});
			show();
			//���
			setMaximized(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addComp(BorderPane root) {
		//ͷ����Ϣ��
		GridPane infoPane = new GridPane();
		infoPane.setPrefHeight(30);
		infoPane.setStyle("-fx-background-image:url("+getClass().getResource("/application/img/top/bg.png").toString()+");-fx-background-size:100% 100%;-fx-background-color: none;");
		root.setTop(infoPane);
		//��Ϸ��������
		AnchorPane gamePane = new AnchorPane();
		//����ͼ
		/**
		 * ��Ҫ�������
		 * 1.���β�       �ر�     ɽ     ˮ    ɳĮ     
		 * 2.��ɫ��	����    ����  ����  ũ��
		 * 3.������	����    �¼�
		 * 4.С��ͼ
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
		//���÷���  ������
		drawMap();
		root.setCenter(gamePane);
		
		//��߲˵���
		BorderPane menuPane = new BorderPane();
		menuPane.setPrefWidth(200);
		menuPane.setStyle("-fx-background-image:url("+getClass().getResource("/application/img/left/bg.png").toString()+");-fx-background-size:100% 100%;-fx-background-color: none;");
		root.setLeft(menuPane);
	}
	/**
	 * ����ͨ����
	 */
	private void drawMap() {
		
	}
}
