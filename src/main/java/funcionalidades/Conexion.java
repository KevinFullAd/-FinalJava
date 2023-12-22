/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */ 
package funcionalidades;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author k3vin
 */
public class Conexion {
    
    public static final String URL="jdbc:mysql://localhost:3306/integrador_cac";
    public static final String usuario="root";
    public static final String contraseña="";
     
    public Connection getConnection(){
        Connection conexion=null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conexion = (Connection)DriverManager.getConnection(URL, usuario, contraseña); 
            
        }catch(Exception ex){
            System.out.println("error: "+ex);
        }
        
        return conexion;
        
    }
    
}