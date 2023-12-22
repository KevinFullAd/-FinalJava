 
package funcionalidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.util.ArrayList;
import java.util.List;
 

public class Producto {
    private int id;
    private String nombre;
    private float precioC;
    private int cantidad;
    private float porcentaje;  

    public Producto(int id,String nombre, float precioC, int cantidad, float porcentaje) {
        this.id = id;
        this.nombre = nombre;
        this.precioC = precioC;
        this.cantidad = cantidad;
        this.porcentaje = porcentaje;
    }
 
    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecioC() {
        return precioC;
    }
    public void setPrecioC(float precioC) {
        this.precioC = precioC;
    }

    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPorcentaje() {
        return porcentaje;
    }
    public void setPorcentaje(float porcentaje) {
        this.porcentaje = porcentaje;
    } 
     
    public void setSql(String nombre,float precioC,int cantidad,float porcetaje ){
        Conexion con=new Conexion();
        Connection conexion=con.getConnection();
        
        PreparedStatement ps;
        ResultSet rs;
         
        try{
        ps = conexion.prepareStatement("INSERT INTO producto (nombre, "
                + "precio_compra, cantidad, porcentaje_ganancia) "
                + "VALUES (?, ?, ?, ?)");

             
        ps.setString(1, nombre);
        ps.setFloat(2, precioC);
        ps.setInt(3,  cantidad);
        ps.setFloat(4,  porcentaje);
        
        int filasAfectadas = ps.executeUpdate();

        if (filasAfectadas > 0) {
            System.out.println("Inserción exitosa");
        } else {
            System.out.println("No se pudo insertar el producto");
        }
        }catch(SQLException ex){
            System.out.println("Error:"+ex);
        }
    }

    public int getId() {
        return id;
    }
    
    public static List<Producto> obtenerProductos() {
        Conexion con = new Conexion();
        Connection conexion = con.getConnection();
        List<Producto> productos = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
    try {  
        ps = conexion.prepareStatement("SELECT * FROM producto");
        rs = ps.executeQuery();
 
        while (rs.next()) {
            int id = rs.getInt("id_prod");
            String nombre = rs.getString("nombre");
            float precioC = rs.getFloat("precio_compra");
            int cantidad = rs.getInt("cantidad");
            float porcentaje = rs.getFloat("porcentaje_ganancia");
 
            Producto producto = new Producto(id,nombre, precioC, cantidad, porcentaje);
            productos.add(producto);
        }

    } catch (SQLException ex) {
        System.out.println("Error:" + ex);
    }  finally {
        // Cerrar la conexión, ResultSet y PreparedStatement aquí
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {               
                System.out.println("error"+e);
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                System.out.println("error"+e);
            }
        }
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.out.println("error"+e);
            }
        }
    }

    return productos;
}
    
    public static void  eliminarProducto(int var){
        Conexion con=new Conexion();
        Connection conexion=con.getConnection();
        
        PreparedStatement ps;
        
        
        try{
        ps =conexion.prepareStatement("DELETE FROM producto WHERE id_prod=?");
        ps.setInt(1, var);
        ps.executeUpdate();   
        
        List<Producto> productos;
        
        }catch (Exception ex){
            System.out.println("Error: " +ex);
        }
        
    }
} 
 
