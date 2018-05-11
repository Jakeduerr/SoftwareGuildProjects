package com.sg.todolistjfx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class FXMLController implements Initializable {
    
    ObservableList<ToDoItem> items = FXCollections.observableArrayList();
    
    @FXML
    ListView list;
    
    @FXML
    TextField text;
    
    public void addItem() {
        items.add(new ToDoItem(text.getText()));
        text.setText("");
    }
    
    public void removeItem() {
        ToDoItem item = (ToDoItem) list.getSelectionModel().getSelectedItem();
        items.remove(item);
    }
    
    public void toggleItem() {
        ToDoItem item = (ToDoItem) list.getSelectionModel().getSelectedItem();
        if (item != null) {
            item.isDone = !item.isDone;
            list.refresh();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list.setItems(items);
    }    
}
