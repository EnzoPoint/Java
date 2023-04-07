<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>

<head>
    <title>Inscription</title>

    <script type="text/javascript">
        function close_window(){
            window.close();
        }

    </script>

    <link rel="stylesheet" type="text/css" href="style.css">

</head>

<body>
<div style = "padding-top:30px;" align="center">
    <div style = "color:tomato;font-size:14pt;font-family:consolas;letter-spacing:0.2em;padding:10px;border-bottom:3px dotted skyblue;border-top:3px dotted skyblue;width:300px;">
        Page d'inscription
    </div>
    <form name="member_check" method="post" action="inscription_check.jsp">
        <table class="table">
            <tr>
                <td class="td_text">
                    User:
                </td>
                <td class="td_input">
                    <input class="input" type="text" name = "name" required>
                </td>
            </tr>
            <tr>
                <td class="td_text">
                    Password:
                </td>
                <td class="td_input">
                    <input class="input" type="password" name = "passwd" required>
                </td>
            </tr>
            <tr>
                <td class="td_text">
                    Email:
                </td>
                <td class="td_input">
                    <input class="input" type="email" name = "email" required>
                </td>
            </tr>
            <tr>
                <td class="td_text">
                    Phone:
                </td>
                <td class="td_input">
                    <input class="input" type="tel" name = "phone" required>
                </td>
            </tr>
            <tr>
                <td class="td_text">
                    Address:
                </td>
                <td class="td_input">
                    <input class="input" type="text" name = "address" required>
                </td>
            </tr>
        </table>
        <input style = "margin-right:20px;" class="button" type="submit" value="Inscription"><input class="button" type="reset" value="Annuler" onclick="javascript:close_window()">
    </form>
</div>
</div>

</body>
</html>