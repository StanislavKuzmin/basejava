<%@ page import="com.urase.webapp.model.ContactType" %>
<%@ page import="com.urase.webapp.Config" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/resume-list-styles.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/theme/light.css">
  <title>Список всех резюме</title>
</head>
<body>

<jsp:include page="fragments/header2.jsp"/>
<div class="scrollable-panel">
  <div class="table-wrapper">
    <div class="add-resume">
      <a class="no-underline-anchor" href="resume?action=add">
        <img src="${pageContext.request.contextPath}/img/light/add-person.svg" alt="">
      </a>
      <a class="text-anchor" href="resume?action=add">
        <p class="add-resume-title">Добавить резюме</p>
      </a>
    </div>
    <div class="resumes-list">
      <table>
        <tr class="t-header">
          <th class="name-column">Имя</th>
          <th class="info-column">Контакты</th>
          <th class="img-column">Редактировать</th>
          <th class="img-column">Удалить</th>
        </tr>
        <c:forEach items="${resumes}" var="resume">
          <jsp:useBean id="resume" type="com.urase.webapp.model.Resume"/>
          <tr class="t-body">
            <td class="name-column">
              <a class="contact-link"
                 href="resume?uuid=${resume.uuid}&action=view">${resume.fullName}</a>
            </td>
            <td class="info-column">
              <%=ContactType.MAIL.toLink(resume.getContact(ContactType.MAIL))%>
            </td>
            <td class="img-column">
              <a class="no-underline-anchor" href="resume?uuid=${resume.uuid}&action=edit">
                <img src="${pageContext.request.contextPath}/img/light/edit.svg" alt="">
              </a>
            </td>
            <td class="img-column">
              <c:if test="<%=!Config.get().isImmutable(resume.getUuid())%>">
                <a class="no-underline-anchor" href="resume?uuid=${resume.uuid}&action=delete">
                  <img src="${pageContext.request.contextPath}/img/light/remove.svg" alt="">
                </a>
              </c:if>
            </td>
          </tr>
        </c:forEach>
      </table>
    </div>
  </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>


