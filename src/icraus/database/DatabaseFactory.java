/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.database;

import icraus.Components.Component;
import icraus.Components.ComponentPlugin;
import icraus.Components.SimpleComponent;
import icraus.Components.SimpleComponentPlugin;
import icraus.database.ui.InsertComponentUi;

/**
 *
 * @author Shoka
 */
public class DatabaseFactory {
    public static final String INSTERT_TYPE = "INSERT_DATABASE";
    public static ComponentPlugin createInsertPlugin() {

        SimpleComponentPlugin p = new SimpleComponentPlugin("Insert", "Database", null,() -> {
            SimpleComponent t = new SimpleComponent(new InsertStatement(), null, INSTERT_TYPE);
            t.setUiDelegate(new InsertComponentUi(t));
            return t;
        });
        return p;
    }
    public static ComponentPlugin createDeletePlugin() {

        SimpleComponentPlugin p = new SimpleComponentPlugin("Delete", "Database", null,() -> {
            SimpleComponent t = new DeleteComponent();
            return t;
        });
        return p;
    }
    public static ComponentPlugin createConnectPlugin() {

        SimpleComponentPlugin p = new SimpleComponentPlugin("Connect", "Database", null,() -> {
            Component t = new ConnectDatabaseComponent();
            return t;
        });
        return p;
    }
    public static ComponentPlugin createDatabaseTablePlugin() {

        SimpleComponentPlugin p = new SimpleComponentPlugin("Table", "Database", null,() -> {
            Component t = new DatabaseTableComponent();
            return t;
        });
        return p;
    }
}
