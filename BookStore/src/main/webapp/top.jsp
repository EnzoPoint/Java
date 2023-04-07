<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title>Book Store</title>
    <link rel = "stylesheet" type = "text/css" href = "<%=request.getContextPath() %>/style.css?ver=1">

    <script type="text/javascript">
        function open_member_check(){
            window.name = "mainWindow";
            window.open("<%=request.getContextPath() %>/Inscription/inscription.jsp", "inscription", "width=500, height=300, resizable=no, left=600, top=300");
        }
        function member_delete(num){
            if(confirm("Etes-vous sûr que vous voulez supprimer ?")){
                location.href = "admin_memberDelete.jsp?no="+num;
            }

        }
        function onUpdate(){
            if(confirm("Souhaitez-vous modifier ?")){
                document.updateMember.submit();
            }
        }
        function delConfirm(num){
            if(confirm("Etes-vous sûr que vous voulez supprimer ?")){
                location.href = "board_delete.jsp?no="+num;
            }
        }
        function updateCheck(){
            if(confirm("Souhaitez-vous modifier ? ")){
                document.board_update.submit();
            }
        }

    </script>

</head>

<body>

<div align = "center" vertical-align = "middle">
    <table  class = "main" width = "800" height = "600">
        <tr height = "5%">
            <td colspan = "2" align = "center">
                <menu>
                    <a href = "<%=request.getContextPath() %>/index.jsp">Home</a> |
                    <%
                        String id = (String)session.getAttribute("id");
                        if(id == null || id.trim().equals("")){
                    %>
                    <a href = "<%=request.getContextPath() %>/Connexion/login.jsp">Login</a> |
                    <a href = "javascript:open_member_check()">Inscription</a> |
                    <%
                    }else{
                    %>
                    <a href = "<%=request.getContextPath() %>/Connexion/logout.jsp">Logout</a> |
                    <a href = "">Mon Panier</a> |
                    <%
                        }
                    %>
                    <a href = "<%=request.getContextPath() %>/bookstore/catalogue.jsp?page=1">Catalogue</a> |
                </menu>
            </td>
        </tr>
