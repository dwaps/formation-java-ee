<!DOCTYPE html>
<html lang="fr">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Calculatrice</title>
	<link rel="stylesheet" href="<c:out value="${ pageContext.request.contextPath }"/>/style.css" />
	</head>
	
	<body>
	
	   <%-- Affichage du résultat de l'opération --%>
	   <c:choose>
           <c:when test="${ empty error && !empty resultat }">
               <p><c:out value="Résultat de l'opération : ${ nb1 } ${ typeOperation } ${ nb2 } = ${ resultat }"></c:out></p>
           </c:when>
           <c:when test="${ !empty error }"><p class="error">${ error }</p></c:when>
	   </c:choose>
	   
	   <%-- Formulaire calculatrice --%>
	   <form action="calcul" method="POST">
           <input type="number" name="nb1" autofocus />
           
           <select name="typeOperation">
            <option value="additionner" selected>+</option>
            <option value="soustraire">-</option>
            <option value="diviser">/</option>
            <option value="multiplier">x</option>
           </select>
           
           <input type="number" name="nb2" />
           <input type="submit" value="Envoyer" />
	   </form>
        
	</body>
</html>