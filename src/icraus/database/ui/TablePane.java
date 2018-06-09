/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.database.ui;

import icraus.Components.Component;
import icraus.Components.DraggableComponent;
import icraus.Components.IllegalComponent;
import icraus.Components.Selectable;
import icraus.database.DatabaseColumnComponent;
import icraus.database.DatabaseTableComponent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Shoka
 */
public class TablePane extends BorderPane implements Selectable, DraggableComponent {

    @FXML
    private Label tableNameLabel;
    @FXML
    private VBox tableColumnsNameVBox;
    @FXML
    private VBox tableColumnsTypeVBox;
    @FXML
    private VBox tableColumnsSizeVBox;
    @FXML
    private HBox headerHBox;
    @FXML
    private VBox tablePropertiesVBox;
    private DatabaseTableComponent parent;

    public TablePane(DatabaseTableComponent comp) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TablePane.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        parent = comp;
        setId("GUI" + parent.getUUID());
        tableNameLabel.setText("insert here");
        tableNameLabel.textProperty().bind(parent.tableNameProperty());
        parent.getChildern().addListener((Observable e) -> {
            columnsChanged();
        });
        
        headerHBox.setOnMouseClicked(e ->{
            TextInputDialog d = new TextInputDialog("");
            d.showAndWait().ifPresent(v -> {
                parent.setTableName(v);
            });
        });

    }

    @Override
    public String getParentComponentUuid() {
        return parent.getUUID();
    }

    private void columnsChanged() {
        ObservableList<Component> childern = parent.getChildern();
        tableColumnsNameVBox.getChildren().clear();
        tablePropertiesVBox.getChildren().clear();
        tableColumnsTypeVBox.getChildren().clear();

        for (Component c : childern) {
            DatabaseColumnComponent temp = (DatabaseColumnComponent) c;
            addColumn(temp);
        }
    }

    @FXML
    private void addColumnAction() {
        DatabaseColumnComponent component = new DatabaseColumnComponent();
        component.setColumnName("col");
        component.setColumnProperties("col");
        component.setColumnType("col");
        try {
            parent.addComponent(component);
        } catch (IllegalComponent ex) {
            Logger.getLogger(TablePane.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void addColumn(DatabaseColumnComponent component) {
        
        TextField type = createTextField(component.typeColumnProperty());
        TextField properties = createTextField(component.columnPropertiesProperty());
        TextField name = createTextField(component.columnNameProperty());
//        StringProperty s = new SimpleStringProperty();
//        s.addListener(e -> {
//            component.setSize(Integer.getInteger(s.get()));
//        });
//
//        TextField size = createTextField(s);

        tableColumnsNameVBox.getChildren().add(name);
        tableColumnsTypeVBox.getChildren().add(type);
        tablePropertiesVBox.getChildren().add(properties);
//        tableColumnsSizeVBox.getChildren().add(size);
    }

    private TextField createTextField(StringProperty s) {
        TextField f = new TextField();
        s.bind(f.textProperty());
        f.setPromptText("insert here");
        f.setPrefColumnCount(20);
        f.setEditable(true);
        f.setMinWidth(50);
        return f;
    }
}
