<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <jsp:useBean id="actor" class="com.techdev.pagila.dto.Actor" scope="request" />
 
 <h4>Actor Details:</h4>
<% if (actor == null || actor.getActorid() == null) { %>
  <form action = "/actor" method = "POST">
     <p>First Name: <input type = "text" name = "firstname"/></p>
     <p>Last Name: <input type = "text" name = "lastname"/></p>
     <input type = "submit" value = "Submit" />
    
       </form> 
<% } else { %>
  <form action = "/actor/<jsp:getProperty name="actor" property="actorid" />" method = "POST">
     <p>First Name: <input type = "text" name = "firstname"  value="<jsp:getProperty name="actor" property="firstname" />" /></p>
     <p>Last Name: <input type = "text" name = "lastname"   value="<jsp:getProperty name="actor" property="lastname" />" /></p>
     <input type = "submit" value = "Update" />    
  </form>
  <form action = "/actor/<jsp:getProperty name="actor" property="actorid" />" method = "DELETE">
  <input type = "submit" value = "Delete" />   
  </form>
<% } %>
