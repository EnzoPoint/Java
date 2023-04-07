<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<link rel="stylesheet" type="text/css" href="style.css">

<div align = "center">
    <div style = "padding-top:30px;" align="center">
        <div style = "color:tomato;font-size:14pt;font-family:consolas;letter-spacing:0.2em;padding:10px;border-bottom:3px dotted skyblue;border-top:3px dotted skyblue;width:300px;">
            Page de Connexion
        </div>


    <form name="login" method="post" action="login_check.jsp">
            <%
                        String memId = "";
                        Cookie[] cks = request.getCookies();
                        for(int i = 0; i < cks.length; i++){
                            if(cks[i].getName().equals("memorizeId")) {
                                memId = cks[i].getValue();
                                System.out.println("Cookie : "+memId);
                            }
                        }
                        if(memId == null || memId.trim().equals("")) {
                    %>
        <table class="table">
                <tr>
                    <td class="td_text">
                        Email:
                    </td>
                    <td class="td_input">
                        <input class="input" type = "text" name="id" value="">
                    </td>
                    <%
                    } else{
                    %>
                    <td class="td_text">
                        Email:
                    </td>
                    <td class="td_input">
                        <input class="input" type = "text" name="id" value="<%= memId %>">
                    </td>
                    <%
                        }
                    %>
                </tr>
                <tr>
                    <td class="td_text">
                        Password:
                    </td>
                    <td class="td_input">
                        <input class="input" type = "password" name="passwd" value="">
                    </td>
                </tr>
                <tr>
                    <td class="td_text">
                        <label for="memorizeId">Save ID:</label>
                    </td>
                    <td class="td_input">
                        <input type="checkbox" name="memorizeId" value="memorizeId">
                    </td>
                </tr>
        </table>
            <div class = "button" >
                <input type = "submit" value = "Connexion">
                <input type = "button" value = "Inscription" onclick="javascript:open_member_check()">
            </div>
    </form>
</div>