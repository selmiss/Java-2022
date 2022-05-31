package com.example.calendar.components;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class CircleButton extends Button {
    public CircleButton(String url){
        Image image = new Image(url);
        setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,new BackgroundSize(25,25,false,false,true,false))));
        setStyle("-fx-pref-height: 20;-fx-pref-width: 20");
        setCursor(Cursor.HAND);
    }
}
