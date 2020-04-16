<%-- 
    Document   : Home
    Created on : 13-feb-2020, 22:24:58
    Author     : PersonalCastro
--%>

<%@page import= "org.hibernate.Session" %>
<%@page import= "java.util.List" %>
<%@page import= "org.hibernate.Query" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Refugios</title>
        
        <link rel="shortcut icon" href="<%=request.getContextPath()%>/turtle.ico" type="image/x-icon" />        
                
        <style>
            h1 {color: green;
                text-align: center;
            }
                
            table {
                border-collapse: collapse;
                width: 60%;
                margin-top: 5%;
                margin-left: 20%;
                margin-right: 20%;
            }

            th, td {
              padding: 8px;
              text-align: center;
              border-bottom: 1px solid #ddd;
            }
            
        </style>
        
    </head>
    <body>
        <h1 >Control de Refugios</h1>
        
        <form name="frm_muestra_refugio" method="post" action="controlador">
            <input type="hidden" name="op" value="muestraRefugio">
            <input type="hidden" name="idRefugio">
            <table border="1">
                <div align="center"> Opciones [<a href="javascript:void(0)" onclick="javascript:document.frm_muestra_refugio.op.value='altaRefugio'; document.frm_muestra_refugio.submit();">Crear nueva refugio </a>]</div>
                <%
                    Session s = ORM.HibernateUtil.getInstance().getSessionFactory().openSession();
                    try{
                        Query q = s.createQuery("FROM Refugio ORDER BY id").setReadOnly(true);
                        List<ORM.Refugio> listaRefugios = (List<ORM.Refugio>) 
                                q.list();
                        if(listaRefugios.isEmpty()) { %>
                        <tr><td colspan="2">No existen refugios</td></tr>
                        <%
                    } else {
                        for(ORM.Refugio unRefugio : listaRefugios){%>
                        
                        <tr>
                            <td><%=unRefugio.getId()%></td>
                            <td><a href="javascript:void(0)" 
                                onclick="javascript:document.frm_muestra_refugio.idRefugio.value = '<%=unRefugio.getId()%>';
                                document.frm_muestra_refugio.op.value = 'muestraRefugio';
                                document.frm_muestra_refugio.submit()"><%=unRefugio.getNombre()%></a></td>
                            <td><a href="javascript:void(0)" 
                                onclick="javascript:document.frm_muestra_refugio.idRefugio.value = '<%=unRefugio.getId()%>';
                                document.frm_muestra_refugio.op.value = 'eliminateRefugio';
                                document.frm_muestra_refugio.submit()">Borrar</a></td>
                            <td><a href="javascript:void(0)" 
                                onclick="javascript:document.frm_muestra_refugio.idRefugio.value = '<%=unRefugio.getId()%>';
                                document.frm_muestra_refugio.op.value = 'modificarRefugio';
                                document.frm_muestra_refugio.submit()">Modificar</a></td>

                        </tr>
                    <%    
                }
            }
        }catch (Exception e){
            e.printStackTrace(System.err);
        }
        %>
                    
            </table>
    </body>
</html>