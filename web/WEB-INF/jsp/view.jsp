<%@ page import="com.urase.webapp.model.SectionType" %>
<%@ page import="com.urase.webapp.model.AbstractSection" %>
<%@ page import="com.urase.webapp.util.HtmlConverter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <jsp:useBean id="resume" type="com.urase.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img
            src="${pageContext.request.contextPath}/img/pencil.png"></a></h2>
    <c:forEach var="contactEntry" items="${resume.contacts}">
        <jsp:useBean id="contactEntry"
                     type="java.util.Map.Entry<com.urase.webapp.model.ContactType, java.lang.String>"/>
        <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>
    </c:forEach>

    <c:forEach var="sectionEntry" items="${resume.sections}">
    <jsp:useBean id="sectionEntry"
                 type="java.util.Map.Entry<com.urase.webapp.model.SectionType, com.urase.webapp.model.AbstractSection>"/>
    <h3><%=sectionEntry.getKey().getTitle()%><br/>
                <%=HtmlConverter.toHtml(sectionEntry.getKey(), sectionEntry.getValue())%><br/>
        </c:forEach>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
