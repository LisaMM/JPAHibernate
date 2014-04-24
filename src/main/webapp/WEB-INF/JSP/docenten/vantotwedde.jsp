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
	</body>
</html>