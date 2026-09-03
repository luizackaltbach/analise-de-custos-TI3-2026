package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DataSource {
   
private String hostname;
private int porta;
private String database;
private String username;
private String password;
    
private Connection connection;

public DataSource (){
    try{
        
        hostname = "127.0.0.1";
        porta = 3306;
        database = "analise_custos";
        username = "root";
        password = "";
        
        
        String url = "jdbc:mysql://"+hostname+":"+porta+"/"+database+"?useTimezone=true&serverTimezone=UTC";
        
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        
        connection = DriverManager.getConnection(url, username, password);
        
    }
    catch (SQLException ex){
         JOptionPane.showMessageDialog(null, "ERRO na conexão"+ex.getMessage());
    }
    catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "ERRO geral"+ex.getMessage());
}

}

public Connection getConnection(){
 return this.connection;   
}

public void CloseDataSource(){
 try{
     connection.close();
 }   
    catch (Exception ex){
        JOptionPane.showMessageDialog(null, "ERRO ao desconectar"+ex.getMessage());
    }
}

}
