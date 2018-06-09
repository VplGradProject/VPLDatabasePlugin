/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.database;

import com.icraus.vpl.codegenerator.ErrorGenerateCodeException;
import com.icraus.vpl.codegenerator.GrammerConstants;
import com.icraus.vpl.codegenerator.Statement;

/**
 *
 * @author Shoka
 */
public class JavaDatabaseExecutorStatement extends Statement{
    public static String DATABASE_STATMENT_VAR = "$$DATABASE_STATMENT";
    public static final String DATABASE_URL_VAR = "$$DATABASE_URL";
    public static String JAVA_EXECUTOR_TEMPLATE = "try" + GrammerConstants.OP_BLOCK_START
            + "Class.forName(\"com.mysql.jdbc.Driver\");\n"
            + "DriverManager.getConnection(" + DATABASE_URL_VAR + ").createStatemnt().executeQueury(" + "\"" + DATABASE_STATMENT_VAR + "\"" + ");\n"
            + GrammerConstants.OP_BLOCK_END + "catch(Exception ex)" + GrammerConstants.OP_BLOCK_START
            + GrammerConstants.OP_BLOCK_END;
    private Statement databaseStatment;
    private ConnectStatement url;
    public JavaDatabaseExecutorStatement(Statement databaseStatment) {
        this.databaseStatment = databaseStatment;
        url = ConnectStatement.getInstance();
    }
    
    @Override
    public String toText() throws ErrorGenerateCodeException {
            String s = JAVA_EXECUTOR_TEMPLATE;
            s = s.replace(DATABASE_STATMENT_VAR, databaseStatment.toText());
            s = s.replace( DATABASE_URL_VAR, "\"jdbc:mysql://\"" +  url.toText());
            return s;
    }

    public Statement getDatabaseStatment() {
        return databaseStatment;
    }

    public void setDatabaseStatment(Statement databaseStatment) {
        this.databaseStatment = databaseStatment;
    }

    public ConnectStatement getUrl() {
        return url;
    }

    public void setUrl(ConnectStatement url) {
        this.url = url;
    }
    
}
