/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.database;

import com.icraus.vpl.codegenerator.ErrorGenerateCodeException;
import com.icraus.vpl.codegenerator.Statement;

/**
 *
 * @author Shoka
 */
public class ConnectStatement extends Statement {

    public static final String HOST_VAR = "$$HOST";
    public static final String PORT_VAR = "$$PORT";
    public static final String DATABASE_NAME_VAR = "$$DATABASENAME";
    public static final String USER_VAR = "$$USER";
    public static final String PASSWORD_VAR = "$$PASSWORD";

    public static final String CONNECT_STATEMENT_TEMPLATE = "\"//" +
            HOST_VAR +
            ":" + 
            PORT_VAR + 
            "/" +
            DATABASE_NAME_VAR +
            "\"" + ", " + "\"" + 
            USER_VAR + 
            "\"" + ", " + "\"" + 
            PASSWORD_VAR + "\"";
    private static ConnectStatement instance = new ConnectStatement();
    private String databaseName = "";
    private String userName = "";
    private String password = "";
    private String databaseHost = "";
    private String databasePort = "";

    public static ConnectStatement getInstance(){
        return instance;
    }

    private ConnectStatement(){
        
    }
    @Override
    public String toText() throws ErrorGenerateCodeException {
        String s = CONNECT_STATEMENT_TEMPLATE;
        s = s.replace(HOST_VAR, getDatabaseHost().trim());
        s = s.replace(USER_VAR, getUserName().trim());
        s = s.replace(DATABASE_NAME_VAR, getDatabaseName().trim());
        s = s.replace(PORT_VAR, getDatabasePort().trim());
        s = s.replace(PASSWORD_VAR, getPassword().trim());
        return s.trim();
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabaseHost() {
        return databaseHost;
    }

    public void setDatabaseHost(String databaseHost) {
        this.databaseHost = databaseHost;
    }

    public String getDatabasePort() {
        return databasePort;
    }

    public void setDatabasePort(String databasePort) {
        this.databasePort = databasePort;
    }

}
