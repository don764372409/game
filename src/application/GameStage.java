package application;

import java.util.LinkedList;

import application.comp.MyLabel;
import application.util.FileUtil;
import application.util.JAVAFXUtil;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
			scene.setOnKeyPressed(new EventHandler<Event>() {
				public void handle(Event event) {
					//�����ʱ������,���Ұ���C��[       ] 
					KeyEvent ke = (KeyEvent)event;
					KeyCode code = ke.getCode();
					if (GameData.empFlag) {
						if (code.equals(KeyCode.OPEN_BRACKET)) {
							GameData.empWidth*=0.9;
							GameData.empHeight*=0.9;
							System.err.println("��СͼƬ");
						}
						if (code.equals(KeyCode.CLOSE_BRACKET)) {
							System.err.println("�Ŵ�ͼƬ");
							GameData.empWidth*=1.1;
							GameData.empHeight*=1.1;
						}
						
					}else {
						if (code.equals(KeyCode.C)) {
							//ɾ�����һ��
							if (GameData.empMbs.size()>0) {
								GameData.empMbs.removeLast();
								reDrawMap(GameData.caozuoG2D);
							}
							//�����µ�ͼ����
							FileUtil.writeMap(GameData.empMbs);
							System.err.println("ɾ��ͼƬ");
						}
					}
					//������������
					if (code.equals(KeyCode.W)) {
						for (MapBlock mb : GameData.empMbs) {
							double y = mb.getY();
							y+=10;
							mb.setY(y);	
						}
						reDrawMap(GameData.caozuoG2D);
						FileUtil.writeMap(GameData.empMbs);
					}
					if (code.equals(KeyCode.S)) {
						for (MapBlock mb : GameData.empMbs) {
							double y = mb.getY();
							
							y-=10;
							mb.setY(y);
						}
						reDrawMap(GameData.caozuoG2D);
						FileUtil.writeMap(GameData.empMbs);
					}
					if (code.equals(KeyCode.A)) {
						for (MapBlock mb : GameData.empMbs) {
							double x = mb.getX();
							x+=10;
							mb.setX(x);
						}
						reDrawMap(GameData.caozuoG2D);
						FileUtil.writeMap(GameData.empMbs);
					}
					if (code.equals(KeyCode.D)) {
						for (MapBlock mb : GameData.empMbs) {
							double x = mb.getX();
							x-=10;
							mb.setX(x);
						}
						reDrawMap(GameData.caozuoG2D);
						FileUtil.writeMap(GameData.empMbs);
					}
				}
			});
			
			
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
		ScrollPane  menu_bottom_pane = new ScrollPane();
		//������
//		menu_bottom_pane.setVbarPolicy(ScrollBarPolicy.NEVER);
		
		menu_bottom_pane.setPrefHeight(GameData.height);
		
		FlowPane pane = new FlowPane();
//		JAVAFXUtil.INSTANCE.setBackGroundColor(menu_bottom_pane, "red");
		for (int i = 1; i < 626; i++) {
			String path = getClass().getResource("/application/img/dixing/"+i+".png").toString();
			Label label = new MyLabel(path);
			label.setPrefHeight(100);
			label.setPrefWidth(100);
			label.setOnMousePressed(new EventHandler<Event>() {
				public void handle(Event event) {
					//���ʱ,��ȡͼƬ��Ϣ
					MyLabel label = (MyLabel)event.getSource();
					GameData.empImg =  new Image(label.getPath());
					GameData.empWidth = GameData.empImg.getWidth();
					GameData.empHeight = GameData.empImg.getHeight();
					//��ʱ�����ش�
					GameData.empFlag = true;
				}
			});
			//��꾭��ͼƬ
			label.setOnMouseDragged(new EventHandler<Event>() {
				public void handle(Event event) {
					if (GameData.empFlag) {
						MouseEvent me = (MouseEvent)event;
						double x = me.getSceneX();
						double y = me.getScreenY();
						//��ȡ���λ���
						GraphicsContext g2d = GameData.caozuoG2D;
						//�ػ��ͼ
						reDrawMap(g2d);
						//����ǰ��
						g2d.drawImage(GameData.empImg, x-GameData.empWidth/2, y-GameData.empHeight/2,GameData.empWidth,GameData.empHeight);
						//�������ʱ��λ����Ϣ
						GameData.empMb.setHeight(GameData.empHeight);
						GameData.empMb.setImgPath(path);
						GameData.empMb.setWidth(GameData.empWidth);
						GameData.empMb.setX(x-GameData.empWidth/2);
						GameData.empMb.setY(y-GameData.empHeight/2);
					}
				}
			});
			label.setOnMouseReleased(new EventHandler<Event>() {
				public void handle(Event event) {
					//ת��һ��  ��Ϊ��̬�����е����ݻᱻ��θ���,����װ������Ҳ��
					MapBlock mapBlock = new MapBlock();
					mapBlock.setWidth(GameData.empMb.getWidth());
					mapBlock.setHeight(GameData.empMb.getHeight());
					mapBlock.setX(GameData.empMb.getX());
					mapBlock.setY(GameData.empMb.getY());
					mapBlock.setImgPath(GameData.empMb.getImgPath());
					GameData.empMbs.add(mapBlock);
					FileUtil.writeMap(GameData.empMbs);
					//���ʱ������ʱ��
					GameData.empFlag = false;
				}
			});
			JAVAFXUtil.INSTANCE.setBackGroundImgAndSize(label,path,"100%","100%");
			pane.getChildren().add(label);
		}
		
		
		menu_bottom_pane.setContent(pane);
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
	private void reDrawMap(GraphicsContext g2d) {
		//�����ͼ
		g2d.clearRect(0, 0, GameData.width, GameData.height);
		//�ػ��ͼ
		LinkedList<MapBlock> mbs = GameData.empMbs;
		for (MapBlock mb : mbs) {
			g2d.drawImage(new Image(mb.getImgPath()), mb.getX(), mb.getY(), mb.getWidth(), mb.getHeight());
		}
	}
}
