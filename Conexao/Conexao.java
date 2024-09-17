package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    
    public static Connection getConexao() {
        Connection conn = null;
        try {
            
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/dbAula?useTimezone=true&serverTimezone=UTC", 
                "root", 
                "laboratorio"
            );
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
