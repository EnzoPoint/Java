<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<jsp:useBean id="dao" class="com.example.bookstore.member.MemberDAO" />

<%
    request.setCharacterEncoding("UTF-8");
    String id = request.getParameter("id");
    String passwd = request.getParameter("passwd");
    String memorizeId = request.getParameter("memorizeId");
    if(memorizeId == null || memorizeId.trim().equals("")){

        Cookie[] cks = request.getCookies();
        for(int i = 0; i < cks.length; i++){
            if(cks[i].getName().equals("memorizeId")){
                cks[i].setMaxAge(0);
                response.addCookie(cks[i]);
            }
        }

    }else if(memorizeId.equals("memorizeId")){

        Cookie ck = new Cookie("memorizeId", id);
        ck.setMaxAge(60*60*24);
        response.addCookie(ck);

    }
    boolean isMember = dao.checkId(id, passwd);
    if(isMember){
        session.setAttribute("id", id);

%>

<script type="text/javascript">
    alert("Connexion réussi ! ");
    location.href = "../index.jsp";
</script>

<%
    } else {
%>

<script type="text/javascript">
    alert("Échec de la connexion! Vérifiez votre identifiant et votre mot de passe.");
    location.href = "login.jsp";
</script>

<%
    }
%>
    