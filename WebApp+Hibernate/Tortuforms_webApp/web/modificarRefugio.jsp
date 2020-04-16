<%-- 
    Document   : modificarRefugio
    Created on : 07-mar-2020, 17:35:47
    Author     : PersonalCastro
--%>

<%@page import="org.hibernate.Transaction"%>
<%@page import="java.util.List"%>
<%@page import="ORM.Refugio"%>
<%@page import="org.hibernate.Session"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar Refugio</title>
                        
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
        <h1>Modificar Nuevo Refugio</h1>
        <%
        try {
            Session s = ORM.HibernateUtil.getInstance().getSessionFactory().openSession();
            s.beginTransaction();

            Refugio refugiosDb = new Refugio();
            List<Refugio> listaDep  = s.createQuery( "FROM Refugio WHERE id ="+request.getParameter("idRefugio")).list();
            for (Refugio axuRefugio: listaDep) { 
                refugiosDb = axuRefugio;
        }

    %>
        
        <form method="post" action="controlador">
            <input type="hidden" name="op" value="modifyRefugio"/>
            <input type="hidden" name="idRefugio" value="<%=refugiosDb.getId()%>"/>
            <div id="izquierda">
                <div>
                    <h3>Nombre</h3>
                    <input name="nomRefugio" required type="text" size="20" maxlength="20" value="<%=refugiosDb.getNombre()%>"/>

                </div>
                <div>
                    <h3>Ciudad</h3>
                    <input name="ciudadRefugio" required type="text" size="20" maxlength="20" value="<%=refugiosDb.getCiudad()%>"/>
                </div>
                <div>
                    <h3>Sucursales (int)</h3>
                    <input name="sucursalesRefugio" required type="text" size="20" maxlength="20" value="<%=refugiosDb.getSucursales()%>"/>
                </div>
                <div>
                     <div>
                         <h3>Apertura</h3>

                         <div id="izquierda_2">
                            <p>Avierto</p>

                            <input type="radio" name="isAbierto" value="true" <%
                            if(refugiosDb.getAbierto()){
                                %>
                                checked="true"
                                <%
                            }
                            %>>
                             
                         </div>

                         <div id="derecha_2">
                             <p>Cerrado</p>
                             <input type="radio" name="isAbierto" value="false"  <%
                            if(!refugiosDb.getAbierto()){
                                %>
                                checked="true"
                                <%
                            }
                            %>>
                         </div>

                     </div>
                 </div>
            </div>
           

                
            <div id="derecha">
                <input id="input" type="submit" value="Modificar"/>
                <input id="input" type="reset" name="cancelar" value="Cancelar"/>
		<a id="input" href="controlador">Inicio</a> 
            </div>
        </form>
        
        <%
        s.close();

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        %>
        
    </body>
</html>
