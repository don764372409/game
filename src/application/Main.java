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
			//���ò��ֵı���ͼƬ
			String bgImg = getClass().getResource("/application/img/bg/bg_index.jpg").toString();
			JAVAFXUtil.INSTANCE.setBackGroundImgAndSize(root,bgImg, "100%","108%");
			Scene scene = new Scene(root,400,400);
			stage.setTitle("������½");
			//���ܸı��С
			stage.setResizable(false);
			stage.setScene(scene);
			
			//���÷���������
			addComp(root);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * ���������ť
	 * @param root ��������
	 */
	private void addComp(BorderPane root) {
		//����һ�����񲼾�
		GridPane gp = new GridPane();
		gp.setMaxWidth(150);
		gp.setMaxHeight(200);
		gp.setVgap(10);
		root.setCenter(gp);
		Button enterLocalGameBtn = new MyButton("���뵥����Ϸ",150,30);
		enterLocalGameBtn.setOnMouseClicked(new EventHandler<Event>() {
			public void handle(Event event) {
				//��������
				//���ص�½����
				GameData.loginStage.hide();
				//��ʾ��Ϸ���� Ĭ����ʾ
				GameStage gameStage = new GameStage();
				GameData.gameStage = gameStage;
				
			}
		});
		gp.add(enterLocalGameBtn,0,0);
		Button enterInterGameBtn = new MyButton("����������Ϸ",150,30);
		enterInterGameBtn.setOnMouseClicked(new EventHandler<Event>() {
			public void handle(Event event) {
				//��������
			}
		});
		
		gp.add(enterInterGameBtn,0,1);
		Button LoadGameBtn = new MyButton("��ȡ������Ϸ",150,30);
		LoadGameBtn.setOnMouseClicked(new EventHandler<Event>() {
			public void handle(Event event) {
				//��������
			}
		});
		gp.add(LoadGameBtn,0,2);
		Button exitGameBtn = new MyButton("�˳���Ϸ",150,30);
		exitGameBtn.setOnMouseClicked(new EventHandler<Event>() {
			public void handle(Event event) {
				//�˳�����
				System.exit(1);
			}
		});
		gp.add(exitGameBtn,0,3);
		//����LOGO
		Label logo = new Label("������½");
		logo.setPrefSize(400, 150);
		logo.setAlignment(Pos.CENTER);
		Font font = Font.font("����",FontWeight.BOLD, FontPosture.ITALIC, 50);
		logo.setFont(font);
		JAVAFXUtil.INSTANCE.setBackGroundImgAndSize(logo, getClass().getResource("/application/img/bg/logo.png").toString(), "100%", "100%");
		logo.setTextFill(Color.RED);
		root.setTop(logo);
	}
	public static void main(String[] args) {
		launch(args);
	}
}
