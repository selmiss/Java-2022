package com.example.calendar.components.notes;

import com.example.calendar.Entity.Idea;
import com.example.calendar.components.todo.TodoDetail;
import com.example.calendar.utils.MyShadow;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

/**
 * 笔记详细类.
 * @author 郭一帆
 * @version 1.0
 * @since 2022-6-16
 */
public class NoteDetail extends AnchorPane {
    private AnchorPane noteContent = new AnchorPane();
    private Label title = new Label();
    private Idea idea = new Idea();

    public Idea getIdea() {
        return idea;
    }

    public void setIdea(Idea idea) {
        this.idea = idea;
    }

    public NoteDetail(){
        //基础设置
        noteContent.setPrefWidth(280);
        noteContent.setPrefHeight(380);
        noteContent.setLayoutX(10);
        noteContent.setLayoutY(10);
        noteContent.setStyle("-fx-background-color: #FFFFFF;" +
                "-fx-background-radius: 40");

        //添加阴影
        DropShadow shadow = new DropShadow ();
        shadow.setBlurType(BlurType.GAUSSIAN);
        shadow.setColor(Color.rgb(0,0,0,0.1));
        shadow.setRadius(8);
        noteContent.setEffect(shadow);

        //标题
        title.setPrefWidth(240);
        title.setPrefHeight(40);
        title.setLayoutX(20);
        title.setLayoutY(20);
        title.setAlignment(Pos.CENTER);
        title.addEventHandler(MouseDragEvent.MOUSE_CLICKED,new TitleEventHandler());
        title.setStyle("-fx-background-color: #F1F1F1;" +
                "-fx-background-radius: 15;" +
                "-fx-font-size: 14;");

        //md编辑器
        WebView webView = new WebView();
        webView.getEngine().load("");
        webView.setPrefWidth(240);
        webView.setPrefHeight(300);
        webView.addEventHandler(MouseDragEvent.MOUSE_CLICKED,new MdEventHandler());

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.getStylesheets().add("file:src/main/resources/com/example/calendar/css/scroll.css");
        scrollPane.setBackground(new Background(new BackgroundFill(Color.web("#F1F9EE"), CornerRadii.EMPTY, Insets.EMPTY)));
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setContent(webView);
        scrollPane.setLayoutX(20);
        scrollPane.setLayoutY(70);

        noteContent.getChildren().addAll(title,scrollPane);

        getChildren().add(noteContent);
    }

    private class MdEventHandler implements  EventHandler{
        @Override
        public void handle(Event event) {
            Object source = event.getSource();
            WebView src = (WebView)source;
            TextArea textArea = new TextArea();
            //基础设置
            textArea.setWrapText(true);
            //用输入框覆盖标签
            textArea.setMinSize(90,20);
            textArea.setPrefWidth(src.getPrefWidth()-12);
            textArea.setPrefHeight(src.getPrefHeight()-12);
            textArea.setLayoutY(65);
            textArea.setLayoutX(26);
            textArea.setStyle(
                    src.getStyle());
            textArea.selectAll();

            //失去焦点时消失事件
            textArea.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
                    if(oldValue&&!newValue)
                    {
                        Parser parser = Parser.builder().build();
                        Node document = parser.parse(textArea.getText());
                        idea.setContent(textArea.getText());
                        HtmlRenderer renderer = HtmlRenderer.builder().build();
                        src.getEngine().loadContent(renderer.render(document));
                        noteContent.getChildren().remove(textArea);
                    }

                }
            });

            noteContent.getChildren().add(textArea);
        }
    }

    /**
     * 文本标签点击事件处理类.
     * <p>点击标签后出现文本输入框，将标签文本改变为输入框内容.</p>
     * @author 郭一帆
     * @version 1.0
     * @since 2022-6-16
     */
    private class TextEventHandler implements EventHandler {

        @Override
        public void handle(Event event) {
            Object source = event.getSource();
            Label src = (Label)source;
            TextArea textArea = new TextArea();
            //基础设置
            textArea.setWrapText(true);
            //用输入框覆盖标签
            textArea.setMinSize(90,20);
            textArea.setPrefWidth(src.getPrefWidth()-12);
            textArea.setPrefHeight(src.getPrefHeight()-12);
            textArea.setLayoutY(src.getLayoutY()+6);
            textArea.setLayoutX(src.getLayoutX()+6);
            textArea.setStyle(
                    src.getStyle());
            textArea.setText(src.getText());
            textArea.selectAll();

            //失去焦点时消失事件
            textArea.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
                    if(oldValue&&!newValue)
                    {
                        src.setText(textArea.getText());
                        idea.setContent(src.getText());
                        noteContent.getChildren().remove(textArea);
                    }

                }
            });

            noteContent.getChildren().add(textArea);
        }
    }
    private class TitleEventHandler implements EventHandler {

        @Override
        public void handle(Event event) {
            Object source = event.getSource();
            Label src = (Label)source;
            TextArea textArea = new TextArea();
            //基础设置
            textArea.setWrapText(true);
            //用输入框覆盖标签
            textArea.setMinSize(90,20);
            textArea.setPrefWidth(src.getPrefWidth()-12);
            textArea.setPrefHeight(src.getPrefHeight()-12);
            textArea.setLayoutY(src.getLayoutY()+6);
            textArea.setLayoutX(src.getLayoutX()+6);
            textArea.setStyle(
                    src.getStyle());
            textArea.setText(src.getText());
            textArea.selectAll();

            //失去焦点时消失事件
            textArea.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
                    if(oldValue&&!newValue)
                    {
                        src.setText(textArea.getText());
                        idea.setTitle(src.getText());
                        noteContent.getChildren().remove(textArea);
                    }

                }
            });

            noteContent.getChildren().add(textArea);
        }
    }
}
