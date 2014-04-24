<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<c:set var='contextPath' value='${pageContext.servletContext.contextPath}' />
<!doctype html>
<html lang='nl'>
	<head>
		<title>Docenten van tot wedde</title>
		<link rel='stylesheet' href='${contextPath}/styles/default.css'>
	</head>
	<body>
		<h1>Docenten van tot wedde</h1>
		<form action="<c:url value='docenten/vantotwedde.htm'/>" method='get'>
			<label>Van:
				<input name='van' value='${param.van}' type='number' autofocus>
			</label>
			<label>Tot:
				<input name='tot' value='${param.tot}' type='number' autofocus>
			</label>
			<input type='submit' value='Zoeken'>
		</form>
		<c:import url='/WEB-INF/JSP/fouten.jsp' />
		<c:if test="${not empty docenten}">
			<table>
				<thead>
					<tr>
						<th>Nummer</th>
						<th>Naam</th>
						<th>Wedde</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items='${docenten}' var='docent'>
						<tr class='wedde'>
							<td>${docent.docentNr}</td>
							<td>${docent.naam}</td>
							<td><fmt:formatNumber value='${docent.docentNr}' 
								minFractionDigits='2' maxFractionDigits='2'/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</body>
</html>