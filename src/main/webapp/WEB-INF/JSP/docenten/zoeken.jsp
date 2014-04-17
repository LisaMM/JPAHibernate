<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<c:set var='contextPath'
	value='${pageContext.servletContext.contextPath}' />
<!DOCTYPE html>
<html lang='nl'>
	<head>
		<title>Docent zoeken</title>
		<link rel='stylesheet' href='${contextPath}/styles/default.css'>
	</head>
	<body>
		<h1>Docent zoeken</h1>
		<form action="<c:url value='/docenten/zoeken.htm'/>" method='get'>
			<label>Nummer: <input name='docentNr' value='${param.docentNr}' 
			autofocus size='6' type='number'></label> 
			<input type='submit' value='Zoeken'>
		</form>
		<c:if test='${not empty docent}'>
			<div>${docent.naam},
			wedde:
			<fmt:formatNumber value='${docent.wedde}' />
			<img src='${contextPath}/images/${docent.geslacht}.png' alt='${docent.geslacht}'
				title='${docent.geslacht}'>
			</div>
		</c:if>
		<c:import url='/WEB-INF/JSP/fouten.jsp' />
	</body>
</html>