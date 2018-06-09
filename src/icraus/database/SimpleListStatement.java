/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.database;

import com.icraus.vpl.codegenerator.ErrorGenerateCodeException;
import com.icraus.vpl.codegenerator.ErrorGenerateCodeException;
import com.icraus.vpl.codegenerator.SimpleStatement;
import com.icraus.vpl.codegenerator.SimpleStatement;
import com.icraus.vpl.codegenerator.Statement;
import com.icraus.vpl.codegenerator.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Shoka
 */
public class SimpleListStatement extends Statement{
    private Statement mainStatemnt; 
    private List<Statement> childern;
    public SimpleListStatement(String statement){
        this(new SimpleStatement(statement));
        childern = new ArrayList<>();
    }
    public SimpleListStatement(Statement s){
        super();
        mainStatemnt = s;
        childern = new ArrayList<>();
    }
    @Override
    public String toText() throws ErrorGenerateCodeException {
        String str = mainStatemnt.toText() + "\n";
        for(Statement s : childern){
            str += s.toText() + "\n";
        }
        return str.trim();
    }

    public List<Statement> getChildern() {
        return childern;
    }

    public Statement getMainStatemnt() {
        return mainStatemnt;
    }
    
}
