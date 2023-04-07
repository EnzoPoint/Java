<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%!
    int res = 0;
%>

<jsp:useBean id="dao" class="com.example.bookstore.member.MemberDAO"/>
<jsp:useBean id="dto" class="com.example.bookstore.member.MemberDTO"/>
<jsp:setProperty property="*" name="dto"/>

<%
    request.setCharacterEncoding("UTF-8");
    String name = request.getParameter("name");

    boolean isMember = dao.checkMember(name);
    if(isMember){
%>

<script type="text/javascript">
    alert("Vous êtes déjà enregistré !");
    history.back();
</script>

<%
    } else {
        res = dao.newMembership(dto);
    }

    System.out.println("res : " + res);

    if(res>0) {
%>

<script type="text/javascript">

    alert("Inscription validé !");
    location.href="../Connexion/login.jsp";

</script>

<%
    } else {
%>

<script type="text/javascript">

    alert("Inscripton non validé, merci de réessayer !");
    history.back();

</script>

<%
    }
%>