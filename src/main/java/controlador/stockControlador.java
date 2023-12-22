/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import funcionalidades.Conexion;
import funcionalidades.Producto; 
import java.io.IOException; 
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author k3vin 
 */
@WebServlet(name = "stockControlador", urlPatterns = {"/stockControlador"})
public class stockControlador extends HttpServlet {
 
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accion=request.getParameter("accion");
        
        if ("agregar".equals(accion)) {
    // Recoge los datos del formulario add product
    String nombre = request.getParameter("add-nombre");
    String precio = request.getParameter("add-precioC");
    String cantidad = request.getParameter("add-cantidad");
    String porcentaje = request.getParameter("add-porcentaje");

    if (nombre == null || precio == null || cantidad == null || porcentaje == null ||
        nombre.isEmpty() || precio.isEmpty() || cantidad.isEmpty() || porcentaje.isEmpty()) {
        // Manejar el caso donde alguno de los parámetros es nulo o vacío
        // Puedes lanzar una excepción, enviar un mensaje de error, etc.
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Los parámetros no pueden ser nulos o vacíos");
        return;
    }
    
    try { 
        int cantidadInt = Integer.parseInt(cantidad);
        float precioF = Float.parseFloat(precio);
        float porcentajeF = Float.parseFloat(porcentaje);

        // Crear producto         
        Producto nuevoProd = new Producto(nombre, precioF, cantidadInt, porcentajeF);       

        // Insertar el nuevo producto en la base de datos 
        nuevoProd.setSql(nombre, precioF, cantidadInt, porcentajeF);
        // Actualizar la lista de productos con los datos de la base de datos
        List<Producto> productos = obtenerProductosDesdeBD();
        getServletContext().setAttribute("productos", productos);

        // Redirige de nuevo al jsp
        request.getRequestDispatcher("index.jsp").forward(request, response);
    } catch (NumberFormatException e) {
        // Manejar la excepción si no se pueden convertir las cadenas a números
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Error al convertir cadenas a números");
    } 
} 
    } 
    
}
