 
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

    public Producto() {
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
    public void updateSql(Producto producto){
        Conexion con=new Conexion();
        Connection conexion=con.getConnection();
        
        PreparedStatement ps;
        ResultSet rs;
         
        try{
        ps = conexion.prepareStatement("UPDATE producto SET nombre=?, "
                + "precio_compra=?, cantidad=?, porcentaje_ganancia=? "
                + "where id_prod=?");

             
        ps.setString(1, producto.getNombre());
        ps.setFloat(2, producto.getPrecioC());
        ps.setInt(3,  producto.getCantidad());
        ps.setFloat(4,  producto.getPorcentaje());
        ps.setInt(5,  producto.getId());
        
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

    public static boolean existeProductoConIndice(int indice) {
    Conexion con = new Conexion();
    try (Connection conexion = con.getConnection()) {
        String sql = "SELECT 1 FROM producto WHERE id_prod = ? LIMIT 1";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, indice);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public static Producto obtenerProductoPorIndice(int indice) {
        Conexion con = new Conexion();
        Producto producto = null;
        try (Connection conexion = con.getConnection()) {
            String sql = "SELECT * FROM producto WHERE id_prod = ?";
            try (PreparedStatement ps = conexion.prepareStatement(sql)) {
                ps.setInt(1, indice);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        producto = new Producto();
                        producto.setId(rs.getInt("id_prod"));
                        producto.setNombre(rs.getString("nombre")); 
                        producto.setPrecioC(rs.getFloat("precio_compra")); 
                        producto.setCantidad(rs.getInt("cantidad")); 
                        producto.setPorcentaje(rs.getFloat("porcentaje_ganancia")); 
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return producto;
    }

    public void setId(int id) {
        this.id = id;
    }
    public static int contarProdBajo(){
        
        Conexion con=new Conexion();
        Connection conexion=con.getConnection();
        
        PreparedStatement ps;
        ResultSet rs;
        int cantidad = 0;
        try{
            ps=conexion.prepareStatement("Select COUNT(*) FROM producto where cantidad<=3 and cantidad>=1 ");
            rs=ps.executeQuery();
            
            if(rs.next()){
                cantidad=rs.getInt(1);
            }
        }catch(SQLException ex){
            System.out.println("Error: " +ex);
        }
        return cantidad;
    }
    public static int contarProd(){
        
        Conexion con=new Conexion();
        Connection conexion=con.getConnection();
        
        PreparedStatement ps;
        ResultSet rs;
        int cantidad = 0;
        try{
            ps=conexion.prepareStatement("Select COUNT(*) FROM producto ");
            rs=ps.executeQuery();
            
            if(rs.next()){
                cantidad=rs.getInt(1);
            }
        }catch(SQLException ex){
            System.out.println("Error: " +ex);
        }
        return cantidad;
    }
 
    public static int contarProdSin(){
        
        Conexion con=new Conexion();
        Connection conexion=con.getConnection();
        
        PreparedStatement ps;
        ResultSet rs;
        int cantidad = 0;
        try{
            ps=conexion.prepareStatement("Select COUNT(*) FROM producto where cantidad=0");
            rs=ps.executeQuery();
            
            if(rs.next()){
                cantidad=rs.getInt(1);
            }
        }catch(SQLException ex){
            System.out.println("Error: " +ex);
        }
        return cantidad;
    }
    
    public static float calcularCosto() {
    float costo = 0;
                Conexion con=new Conexion();
                Connection conexion=con.getConnection();
        try ( 
             PreparedStatement ps = conexion.prepareStatement("SELECT SUM(precio_compra*cantidad) FROM producto");
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                costo = rs.getFloat(1);
            }

        } catch (SQLException ex) { 
            System.out.println("Error al calcular el costo: " + ex);
        }

        return costo;
    }
    public static float calcularCostoTotal() {
        float costoTotal = 0;
                Conexion con = new Conexion();
            Connection conexion = con.getConnection();
        try (
            PreparedStatement ps = conexion.prepareStatement("SELECT SUM(precio_compra * (1 + porcentaje_ganancia / 100) * cantidad) AS total FROM producto");
            ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                costoTotal = rs.getFloat("total");
            }

          } catch (SQLException ex) {
              // Manejo de excepciones según tus necesidades.
              System.out.println("Error al calcular el costo total: " + ex);
          }

          return costoTotal;
      }
    
}
 
 
