<%@ page import="com.urase.webapp.model.ListSimpleSection" %>
<%@ page import="com.urase.webapp.model.OrganizationSection" %>
<%@ page import="com.urase.webapp.model.SimpleSection" %>
<%@ page import="com.urase.webapp.util.HtmlUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <base href="${pageContext.request.contextPath}/"/>
  <link rel="stylesheet" href="css/theme/${theme}.css">
  <link rel="stylesheet" href="css/styles.css">
  <link rel="stylesheet" href="css/view-resume-styles.css">
  <jsp:useBean id="resume" type="com.urase.webapp.model.Resume" scope="request"/>
  <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header2.jsp"/>

<div class="scrollable-panel">
  <div class="form-wrapper">
    <div class="full-name">${resume.fullName}
      <a class="no-underline-anchor" href="resume?uuid=${resume.uuid}&action=edit&theme=${theme}">
        <img src="img/${theme}/edit.svg" alt="">
      </a>
    </div>
    <div class="contacts">
      <c:forEach var="contactEntry" items="${resume.contacts}">
        <jsp:useBean id="contactEntry"
                     type="java.util.Map.Entry<com.urase.webapp.model.ContactType, java.lang.String>"/>

        <div><%=contactEntry.getKey().toHtml(contactEntry.getValue())%>
        </div>
      </c:forEach>
    </div>

    <div class="spacer"></div>

    <c:forEach var="sectionEntry" items="${resume.sections}">
      <jsp:useBean id="sectionEntry"
                   type="java.util.Map.Entry<com.urase.webapp.model.SectionType, com.urase.webapp.model.AbstractSection>"/>
      <c:set var="type" value="${sectionEntry.key}"/>
      <c:set var="section" value="${sectionEntry.value}"/>
      <jsp:useBean id="section" type="com.urase.webapp.model.AbstractSection"/>
      <div class="section">${type.title}</div>
      <c:choose>
        <c:when test="${type=='OBJECTIVE'}">
          <div class="position"><%=((SimpleSection) section).getText()%>
          </div>
        </c:when>
        <c:when test="${type=='PERSONAL'}">
          <div class="qualities"><%=((SimpleSection) section).getText()%>
          </div>
        </c:when>
        <c:when test="${type=='QUALIFICATIONS' || type=='ACHIEVEMENT'}">
          <ul class="list">
            <c:forEach var="item" items="<%=((ListSimpleSection) section).getItems()%>">
              <li>${item}</li>
            </c:forEach>
          </ul>
        </c:when>
        <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
          <c:forEach var="org" items="<%=((OrganizationSection) section).getOrganizations()%>">
            <div class="section-wrapper">
              <c:choose>
                <c:when test="${empty org.nameEmployer}">
                  <div class="job-name">${org.nameEmployer}</div>
                </c:when>
                <c:otherwise>
                  <div class="job-name"><a class="contact-link"
                                           href="${org.linkEmployer}">${org.linkEmployer}</a></div>
                </c:otherwise>
              </c:choose>
              <c:forEach var="position" items="${org.periods}">
                <jsp:useBean id="position" type="com.urase.webapp.model.Period"/>
                <div class="period-position">
                  <div class="period"><%=HtmlUtil.formatDates(position)%>
                  </div>
                  <div class="position">${position.title}</div>
                </div>
                <c:choose>
                  <c:when test="${empty position.description}">
                  </c:when>
                  <c:otherwise>
                    <div class="description">${position.description}</div>
                  </c:otherwise>
                </c:choose>
              </c:forEach>
            </div>
          </c:forEach>
        </c:when>
      </c:choose>
    </c:forEach>

    <div class="footer-spacer"></div>
  </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
