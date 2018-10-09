package application;
	
import application.comp.MyButton;
import application.util.JAVAFXUtil;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class Main extends Application {
	
	public void start(Stage stage) {
		GameData.loginStage = stage;
		try {
			BorderPane root = new BorderPane();
			//设置布局的背景图片
			String bgImg = getClass().getResource("/application/img/bg/bg_index.jpg").toString();
			JAVAFXUtil.INSTANCE.setBackGroundImgAndSize(root,bgImg, "100%","108%");
			Scene scene = new Scene(root,400,400);
			stage.setTitle("乱世大陆");
			//不能改变大小
			stage.setResizable(false);
			stage.setScene(scene);
			
			//调用方法添加组件
			addComp(root);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 添加三个按钮
	 * @param root 布局容器
	 */
	private void addComp(BorderPane root) {
		//创建一个网格布局
		GridPane gp = new GridPane();
		gp.setMaxWidth(150);
		gp.setMaxHeight(200);
		gp.setVgap(10);
		root.setCenter(gp);
		Button enterLocalGameBtn = new MyButton("进入单机游戏",150,30);
		enterLocalGameBtn.setOnMouseClicked(new EventHandler<Event>() {
			public void handle(Event event) {
				//更换场景
				//隐藏登陆场景
				GameData.loginStage.hide();
				//显示游戏场景 默认显示
				GameStage gameStage = new GameStage();
				GameData.gameStage = gameStage;
				
			}
		});
		gp.add(enterLocalGameBtn,0,0);
		Button enterInterGameBtn = new MyButton("进入联网游戏",150,30);
		enterInterGameBtn.setOnMouseClicked(new EventHandler<Event>() {
			public void handle(Event event) {
				//更换场景
			}
		});
		
		gp.add(enterInterGameBtn,0,1);
		Button LoadGameBtn = new MyButton("读取单机游戏",150,30);
		LoadGameBtn.setOnMouseClicked(new EventHandler<Event>() {
			public void handle(Event event) {
				//更换场景
			}
		});
		gp.add(LoadGameBtn,0,2);
		Button exitGameBtn = new MyButton("退出游戏",150,30);
		exitGameBtn.setOnMouseClicked(new EventHandler<Event>() {
			public void handle(Event event) {
				//退出程序
				System.exit(1);
			}
		});
		gp.add(exitGameBtn,0,3);
		//创建LOGO
		Label logo = new Label("乱世大陆");
		logo.setPrefSize(400, 150);
		logo.setAlignment(Pos.CENTER);
		Font font = Font.font("楷体",FontWeight.BOLD, FontPosture.ITALIC, 50);
		logo.setFont(font);
		JAVAFXUtil.INSTANCE.setBackGroundImgAndSize(logo, getClass().getResource("/application/img/bg/logo.png").toString(), "100%", "100%");
		logo.setTextFill(Color.RED);
		root.setTop(logo);
	}
	public static void main(String[] args) {
		launch(args);
	}
}
