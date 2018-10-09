package application.comp;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;

public class MyButton extends Button{
	/**
	 * 创建按钮时直接赋值高宽
	 * @param text
	 * @param width
	 * @param height
	 */
	public MyButton(String text,double width,double height) {
		super(text);
		setPrefSize(width, height);
		setMaxSize(width, height);
		setMinSize(width, height);
		String path = getClass().getResource("/application/img/bg/btn.png").toString();
		setStyle("-fx-background-image:url("+path+");-fx-background-size:100% 100%;-fx-background-color: none;");
		//添加事件 --鼠标移入更换鼠标图标
		setOnMouseMoved(new EventHandler<Event>() {
			public void handle(Event event) {
				setCursor(Cursor.cursor(getClass().getResource("/application/img/cursor.png").toString()));
			}
		});
	}
}
