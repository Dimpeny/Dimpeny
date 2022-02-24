/*
 * Classe usada para receber a conexão do banco
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Dimpeny
 */
public class Conexao {
    public static Connection conectarBD(){
        Connection conexao;
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/gestao_vacinacao";
        String user = "adminVacinacaoGuiZ";
        String pass = "#adminVacina2021";
        try{
            Class.forName(driver);
            conexao = DriverManager.getConnection(url,user,pass);
            return conexao;
        } catch (SQLException | ClassNotFoundException e) {
            
        }
        return null;
    }
}
