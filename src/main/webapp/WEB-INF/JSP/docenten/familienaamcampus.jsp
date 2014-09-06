<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<c:set var='contextPath'
	value='${pageContext.servletContext.contextPath}' />
<!doctype html>
<html lang='nl'>
	<head>
		<title>Docenten zoeken op familienaam en campus</title>
		<link rel='stylesheet' href='${contextPath}/styles/default.css'>
	</head>
	<body>
		<form action="<c:url value='/docenten/familienaamcampus.htm'/>" method='get'>
			<h1>Docenten zoeken op familienaam en campus</h1>
			<label> Begin familienaam: 
				<input name='familienaam' value='${param.familienaam}' autofocus>
			</label>
			<label> Campus: 
				<select name='campussen' size='6'>
					<c:forEach items='${campussen}' var='campus'>
						<option value='${campus.campusNr}' ${campus.campusNr==param.campussen? 
							'selected' : ''}>
							${campus.naam} (${campus.adres.gemeente})
						</option>
					</c:forEach>
				</select>
			</label>
			<input type='submit' value='Zoeken'>
		</form>
		<c:if test="${not empty docenten}">
			<ul>
				<c:forEach items='${docenten}' var='docent'>
					<li>${docent.familienaam} ${docent.voornaam}</li>
				</c:forEach>
			</ul>
		</c:if>
		<c:import url='/WEB-INF/JSP/fouten.jsp' />
	</body>
</html>