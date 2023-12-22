<%-- 
    Document   : editar
    Created on : 22 dic 2023, 16:40:53
    Author     : k3vin
--%>

<%@page import="funcionalidades.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Final Java - CaC</title>
    <link rel="stylesheet" href="editar.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Belleza&family=Open+Sans:wght@300;400;600&display=swap" rel="stylesheet">
</head>
<body>
    <aside>
        <div class="aside-first-container">
            <h3 class="shop-name">Local 1</h3>

            <div class="disponibility">
                <button class="button-one">Local</button>
                <button class="button-two active">En linea</button>
            </div>
        </div>

        <span class="line"></span>

        <nav class="section-container">
            <ul class="section-list">
                
                <li class="section active">
                    <i class="fa-solid fa-database"></i>
                    <h2 class="sec-name">Producto </h2>
                </li>     
            </ul>

        </nav>

        <div class="footer-aside">
            <span>Version 0.1</span>
        </div>
    </aside>
    <div class="primary-container">
        <div class="top-container">
            <div class="top-rigth-container">
                <h1 class="titulo-container">Productos</h1>    
            </div>
    
            <div class="top-left-container">
                <div class="user-img-container">
                    <img src="img/IMG_20221225_002230386_MFNR.jpg" alt="" class="user-img">
                </div>
                <div class="user-data">
                    <div class="user-name">Kevin Paniagua</div>
                    <div class="user-email">pepito123@gmail.com</div>
                </div>
                <div class="logout">
                    <button><i class="fa-solid fa-chevron-down"></i></button>
                </div>
            </div>
    
        </div>
        
        <div class="form-container ">
            <form method="post" action="stockControlador">
                <h3 class="ed-titulo">Editar Producto</h3>
                <input type="hidden" name="accion" value="actualizar">
                <input type="text" name="indiceEditado" value="<%=request.getAttribute("indice")%>">
                <span class="line"></span>
                <% 
                    // Obtén el producto a editar del request
                    Producto productoAEditar = (Producto) request.getAttribute("productoAEditar");

                    // Verifica si el producto a editar está presente
                    if (productoAEditar != null) { 
                %>
                <div class="ed-cont">   
                    <div class="sector">
                        <label for="">Nombre:</label>
                        <input type="text" name="ed-nombre" value="<%=productoAEditar.getNombre() %>" > 

                    </div>
                    <div class="sector">
                        <label for="">Precio de compra:</label>
                        <input type="number" name="ed-precioC" value="<%=productoAEditar.getPrecioC() %>" >
                    </div>
                    <div class="sector">
                        <label for="">Porcentaje Ganancia</label>
                        <input type="number" name="ed-porcentaje" value="<%=productoAEditar.getPorcentaje() %>" >
                    </div>
                    <div class="sector">
                        <label for="">Cantidad disponible:</label>
                        <input type="number" name="ed-cantidad" value="<%=productoAEditar.getCantidad() %>" >
                    </div>   
                </div>
                <% 
                    }
                %>
                <div class="button">
                    <button type="submit">Actualizar </button>
                </div>
            </form>
        </div>
    </div>








</body>    
<script src="https://kit.fontawesome.com/5d13b729b2.js" crossorigin="anonymous"></script>
<script src="script.js"></script>  
</html>