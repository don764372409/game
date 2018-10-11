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
			//设置鼠标样式
			String path = this.getClass().getResource("/application/img/cursor.png").toString();
			root.setCursor(Cursor.cursor(path));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(this.getClass().getResource("game.css").toExternalForm());
			scene.setOnKeyPressed(new EventHandler<Event>() {
				public void handle(Event event) {
					//如果临时开启的,并且按了C或[       ] 
					KeyEvent ke = (KeyEvent)event;
					KeyCode code = ke.getCode();
					if (GameData.empFlag) {
						if (code.equals(KeyCode.OPEN_BRACKET)) {
							GameData.empWidth*=0.9;
							GameData.empHeight*=0.9;
							System.err.println("缩小图片");
						}
						if (code.equals(KeyCode.CLOSE_BRACKET)) {
							System.err.println("放大图片");
							GameData.empWidth*=1.1;
							GameData.empHeight*=1.1;
						}
						
					}else {
						if (code.equals(KeyCode.C)) {
							//删除最后一个
							if (GameData.empMbs.size()>0) {
								GameData.empMbs.removeLast();
								reDrawMap(GameData.caozuoG2D);
							}
							//将最新地图保存
							FileUtil.writeMap(GameData.empMbs);
							System.err.println("删除图片");
						}
					}
					//按了上下左右
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
		ScrollPane  menu_bottom_pane = new ScrollPane();
		//滚动条
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
					//落笔时,获取图片信息
					MyLabel label = (MyLabel)event.getSource();
					GameData.empImg =  new Image(label.getPath());
					GameData.empWidth = GameData.empImg.getWidth();
					GameData.empHeight = GameData.empImg.getHeight();
					//临时画开关打开
					GameData.empFlag = true;
				}
			});
			//鼠标经过图片
			label.setOnMouseDragged(new EventHandler<Event>() {
				public void handle(Event event) {
					if (GameData.empFlag) {
						MouseEvent me = (MouseEvent)event;
						double x = me.getSceneX();
						double y = me.getScreenY();
						//获取地形画笔
						GraphicsContext g2d = GameData.caozuoG2D;
						//重绘地图
						reDrawMap(g2d);
						//画当前的
						g2d.drawImage(GameData.empImg, x-GameData.empWidth/2, y-GameData.empHeight/2,GameData.empWidth,GameData.empHeight);
						//保存起笔时的位置信息
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
					//转存一次  因为静态对象中的数据会被多次更改,就算装进数组也会
					MapBlock mapBlock = new MapBlock();
					mapBlock.setWidth(GameData.empMb.getWidth());
					mapBlock.setHeight(GameData.empMb.getHeight());
					mapBlock.setX(GameData.empMb.getX());
					mapBlock.setY(GameData.empMb.getY());
					mapBlock.setImgPath(GameData.empMb.getImgPath());
					GameData.empMbs.add(mapBlock);
					FileUtil.writeMap(GameData.empMbs);
					//起笔时结束临时画
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
	private void reDrawMap(GraphicsContext g2d) {
		//清除地图
		g2d.clearRect(0, 0, GameData.width, GameData.height);
		//重绘地图
		LinkedList<MapBlock> mbs = GameData.empMbs;
		for (MapBlock mb : mbs) {
			g2d.drawImage(new Image(mb.getImgPath()), mb.getX(), mb.getY(), mb.getWidth(), mb.getHeight());
		}
	}
}
