/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.database.plugin;

import com.plugins.plugin.PluginList;
import icraus.database.DatabaseFactory;

/**
 *
 * @author Shoka
 */
public class DatabaseComponentPlugin extends PluginList{

    public DatabaseComponentPlugin() {
        super();
        add(DatabaseFactory.createInsertPlugin());
        add(DatabaseFactory.createDatabaseTablePlugin());
        add(DatabaseFactory.createDeletePlugin());
        add(DatabaseFactory.createConnectPlugin());
    }
    
}
