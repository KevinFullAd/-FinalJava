<%@page import="java.util.List"%>
<%@page import="funcionalidades.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Final Java - CaC</title>
    <link rel="stylesheet" href="style.css">
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
                <span class="product-counter">0 Productos nuevos registrados</span>
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

        <div class="intermediate-container">
            <div class="intermediate-top-container">
                <div class="search-bar">
                    <form action="#">
                        <input type="text" placeholder="Busca un producto">
                        <button class="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
                    </form>
                </div>
                <div class="filter">
                    <i class="fa-solid fa-filter"></i>
                    <span class="filter-txt">Filtro</span>
                </div>
                <div class="category">
                    <i class="fa-solid fa-tag"></i>
                    <span class="filter-txt">Categorias</span>
                </div>
                <div class="add-product">
                    <button id="add-prod" class="add-prod">
                        <i class="fa-solid fa-plus"></i>
                        Producto
                    </button>

                </div>
            </div>
            <div class="intermediate-bottom-container">
                <div class="total-earning inter-container">
                    <span class="top">VEF $<span class="earging" id="earging"><%=Producto.calcularCostoTotal()%></span></span>
                    <span class="bottom">Valor en stock</span>
                </div>
                <div class="cost-value inter-container">
                    <span class="top">VEF $<span class="earging" id="earging"><%=Producto.calcularCosto()%></span></span>
                    <span class="bottom">Costo de stock</span>
                </div>
                <div class="total-value inter-container">
                    <span class="top">VEF $<span class="earging" id="earging"><%=Producto.calcularCostoTotal()-Producto.calcularCosto()%></span></span>
                    <span class="bottom">Ganancia Neto</span> 
                </div>
                <div class="total-value inter-container">
                    <span class="top"><%= Producto.contarProdBajo()%></span>
                    <span class="bottom"> Stock bajo<span class="point oranged">•</span></span>
                </div>
                <div class="empty-stock inter-container">
                    <span class="top"><%= Producto.contarProdSin()%></span>
                    <span class="bottom"> Sin Stock<span class="point red">•</span></span>
                </div>
                <div class="total-value inter-container">
                    <span class="top"><%= Producto.contarProd()%></span>
                    <span class="bottom">Elementos en stock</span>
                </div>
            </div>

        </div>

        <div class="bottom-container">
            <div class="left">

                <table >
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th width="250px">Nombre</th>  
                            <th>Precio venta</th>
                            <th>Precio compra</th>
                            <th>Cantidad</th> 
                            <th width="200px">Porcentaje Ganancia</th> 
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%  
                            List<Producto> productos = Producto.obtenerProductos();
                            System.out.println("Productos en JSP: " + productos);
                            if (productos != null) {
                                for (int i = 0; i < productos.size(); i++) {
                                    Producto producto = productos.get(i);
                        %>
                        <tr>
                            <td><%= producto.getId() %></td>
                            <td><%= producto.getNombre() %></td>
                            <td><%= (producto.getPrecioC()*(producto.getPorcentaje() /100)+producto.getPrecioC()) %></td>
                            <td><%= producto.getPrecioC() %></td>
                            <td><%= producto.getCantidad() %></td>
                            <td>%<%= producto.getPorcentaje() %></td>
                            <!-- Agrega aquí las columnas adicionales que deseas mostrar -->

                            <td class="acciones">
                                <div class="acciones-cont">
                                    <form action="stockControlador" method="post">
                                        <input type="hidden" name="accion" value="eliminar">
                                        <input type="hidden" name="indice" value="<%= producto.getId() %>">
                                        <button type="submit" class="borrar">Eliminar</button>
                                    </form>
                                    <form action="stockControlador" method="post">
                                        <input type="hidden" name="accion" value="editar">
                                        <input type="hidden" name="indice" value="<%= producto.getId() %>">
                                        <button type="submit" class="editar" id="editarProd">Editar</button>
                                    </form>
                                </div>
                            </td>
                        </tr>
                        <%
                            }
                        }
                        %>
                    </tbody>

                </table>
            </div> 
        </div>

        <div class="add-prod-view" id="add-prod-view">
            <div class="view"> 
                <span class="close-view" id="close-view" ></span>
                <form method="post" action="stockControlador">
                    <h3 class="add-titulo">Añadir Producto</h3>
                    <input type="hidden" name="accion" value="agregar">
                    <span class="line"></span>
                    <div class="add-cont">   
                        <div class="sector">
                            <label for="">Nombre:</label>
                            <input type="text" name="add-nombre" id="add-nombre">
                        </div>
                        <div class="sector">
                            <label for="">Precio de compra:</label>
                            <input type="number" name="add-precioC" id="add-precioC">
                        </div>
                        <div class="sector">
                            <label for="">Porcentaje Ganancia</label>
                            <input type="number" name="add-porcentaje" id="add-porcentaje">
                        </div>
                        <div class="sector">
                            <label for="">Cantidad disponible:</label>
                            <input type="number" name="add-cantidad" id="add-cantidad">
                        </div>   
                    </div>
                    <div class="button">
                        <button type="submit">Añadir</button>
                    </div>
                </form>
            </div>
            
        </div>
    </div> 
</body>    

<script src="https://kit.fontawesome.com/5d13b729b2.js" crossorigin="anonymous"></script>
<script src="script.js"></script>        
</html>