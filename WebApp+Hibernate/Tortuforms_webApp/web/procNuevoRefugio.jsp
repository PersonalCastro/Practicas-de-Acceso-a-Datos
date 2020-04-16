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
        <title>Creación de Refugio</title>
    </head>
    <body>
        <%
            ORM.Refugio refugioBean = (ORM.Refugio) request.getAttribute("refugio");
            if (refugioBean == null) {
        %>
        ERROR: no se proporcionaron datos de refugio para crear.
        <%
        } else {
            Transaction t = null;
            try{
                Session s = ORM.HibernateUtil.getInstance().getSessionFactory().openSession();
                t = s.beginTransaction();
                refugioBean.setNombre(request.getParameter("nomRefugio"));
                refugioBean.setCiudad(request.getParameter("ciudadRefugio"));
                refugioBean.setAbierto(Boolean.valueOf(request.getParameter("isAbierto")));
                refugioBean.setSucursales(Integer.valueOf(request.getParameter("sucursalesRefugio")));
                s.save(refugioBean);
                t.commit();
        %>
        <h1 >Nuevo Refugio Creado</h1>
        Creada nuevo Refugio: [<%=refugioBean.getId()%>] <%=refugioBean.getNombre()%><br/>
        <a href="controlador">Volver</a>
        <%
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                    if (t != null) {
                        t.rollback();
                    }
                }
            }
        %>
    </body>
</html>