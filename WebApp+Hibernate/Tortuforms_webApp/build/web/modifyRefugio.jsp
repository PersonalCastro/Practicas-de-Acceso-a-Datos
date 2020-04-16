<%@page import="java.util.List"%>
<%@page import="ORM.Refugio"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.Transaction"%>
<%@page import="org.hibernate.Query"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:useBean id="refugio" scope="request" class="ORM.Refugio"/>
<jsp:setProperty name="refugio" property="*"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificacion de Refugio</title>
    </head>
    <body>

        <%
        Transaction t = null;
        try {
            Session s = ORM.HibernateUtil.getInstance().getSessionFactory().openSession();
            t = s.beginTransaction();

            Refugio refugiosDb = new Refugio();
            List<Refugio> listaDep  = s.createQuery( "FROM Refugio WHERE id ="+request.getParameter("idRefugio")).list();
            for (Refugio axuRefugio: listaDep) { 
                refugiosDb = axuRefugio;
            }
            
            refugiosDb.setNombre(request.getParameter("nomRefugio"));
            refugiosDb.setCiudad(request.getParameter("ciudadRefugio"));
            refugiosDb.setAbierto(Boolean.valueOf(request.getParameter("isAbierto")));
            refugiosDb.setSucursales(Integer.valueOf(request.getParameter("sucursalesRefugio")));
            s.update(refugiosDb);
            t.commit();
            
            %>
        <h1 >Refugio Modificado</h1>
        Modificar Refugio: [<%=refugiosDb.getId()%>] <%=refugiosDb.getNombre()%><br/>
        <a href="controlador">Volver</a>
        <%
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                    if (t != null) {
                        t.rollback();
                    }
                }
        %>
    </body>
</html>
