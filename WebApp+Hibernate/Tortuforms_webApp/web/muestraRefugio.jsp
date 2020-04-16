<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="ORM.Refugio"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.Query"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:useBean id="refugio" scope="request" class="ORM.Refugio"/>
<jsp:setProperty name="refugio" property="*"/>



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
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Refugio <%=refugiosDb.getId()%> - <%=refugiosDb.getNombre()%></title>
        
        <style>
            h1 {color: green;
                text-align: center;
                margin-bottom: 30px;
            }
            p {
                text-align: center;
            }  
            a{
                text-decoration: none;
            }

        </style>
        
    </head>
    <body>
        <h1 >Refugio - <%=refugiosDb.getId()%></h1>
        
        <p>Nombre: <%=refugiosDb.getNombre()%></p>
        
        <p>Ciudad: <%=refugiosDb.getCiudad()%></p>
        
        <p>Sucursales: <%=refugiosDb.getSucursales()%></p>

        <%
        if(refugiosDb.getAbierto()){
        %>
        <p>Apertura: Abierto</p>
        <%
        }else{
        %>
        <p>Apertura: Cerrado</p>        
        <%
        }
        %>

        <a href="controlador"><p> Volver </p></a>

    </body>
</html>
<%
    
        s.close();

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
%>
