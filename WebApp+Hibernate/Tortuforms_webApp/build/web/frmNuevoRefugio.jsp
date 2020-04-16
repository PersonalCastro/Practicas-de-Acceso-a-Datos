<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:useBean id="refugio" scope="request" class="ORM.Refugio"/>
<jsp:setProperty name="refugio" property="*"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alta de Refugio</title>
                        
        <style>
            h1 {color: green;
                text-align: center;
            }
                
            div {
                margin: 20px;
                
            }    
            
            #izquierda {
                margin-left: 400px;
                width: 400px;
                height: 300px;
                float:left;
            }

            #derecha {
                margin-top: 60px;
                margin-right: 400px;
                width: 400px;
                height: 300px;
                float:right;
            }
            
            #izquierda_2 {
                margin: 0px;
                width: 100px;
                height: 278px;
                float:left;
            }
            
            #derecha_2 {
                margin: 0px;
                width: 100px;
                height: 278px;
                float:right;
            }
            
            #div_1{
                height: 30%;
            }
            
            h3{
         
                height: 22px;
                text-align: center;
            }

            p{
                text-align: center;
            }
            input{
                width: 100%;
            }
            #input{
                margin: 30px;
            }
            #divo{
                float:bottom;
            }
            
            a:link, a:visited {

                width: 100%;

                background-color: green;
                color: white;
                padding: 5px 0px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
            }

            a:hover, a:active {

                background-color: limegreen;
            }

        </style>
    </head>
    <body>
        <h1>Crear Nuevo Refugio</h1>

        
        <form method="post" action="controlador">
            <input type="hidden" name="op" value="insertRefugio"/>
            <div id="izquierda">
                <div>
                    <h3>Nombre</h3>
                    <input name="nomRefugio" required type="text" size="20" maxlength="20"/>

                </div>
                <div>
                    <h3>Ciudad</h3>
                    <input name="ciudadRefugio" required type="text" size="20" maxlength="20"/>
                </div>
                <div>
                    <h3>Sucursales (int)</h3>
                    <input name="sucursalesRefugio" required type="text" size="20" maxlength="20"/>
                </div>
                <div>
                     <div>
                         <h3>Apertura</h3>

                         <div id="izquierda_2">
                             <p>Avierto</p>
                             <input type="radio" name="isAbierto" value="true" >
                         </div>

                         <div id="derecha_2">
                             <p>Cerrado</p>
                             <input type="radio" name="isAbierto" value="false" >
                         </div>

                     </div>
                 </div>
            </div>
           

                
            <div id="derecha">
                <input id="input" type="submit" value="Crear"/>
                <input id="input" type="reset" name="cancelar" value="Cancelar"/>
		<a id="input" href="controlador">Inicio</a> 
            </div>
        </form>
    </body>
</html>
