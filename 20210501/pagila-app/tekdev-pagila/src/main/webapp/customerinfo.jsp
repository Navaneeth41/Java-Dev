<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <jsp:useBean id="customer" class="com.techdev.pagila.dto.Customer" scope="request" />

<h4>Customer Details:</h4>
<% if (customer == null || customer.getCustomerId() == null) { %>
  <form action = "/customer" method = "POST">
     <p>First Name: <input type = "text" name = "firstName"/></p>
     <p>Last Name: <input type = "text" name = "lastName"/></p>
     <p>Store Id: <input type = "text" name = "storeId"/></p>
     <p>Address Id: <input type = "text" name = "addressId"/></p>
     <input type = "submit" value = "Submit" />
  </form> 
<% } else { %>
  <form action = "/customer/<jsp:getProperty name="customer" property="customerId" />" method = "POST">
     <p>First Name: <input type = "text" name = "firstName"  value="<jsp:getProperty name="customer" property="firstName" />" /></p>
     <p>Last Name: <input type = "text" name = "lastName"   value="<jsp:getProperty name="customer" property="lastName" />" /></p>
     <p>Store Id: <input type = "text" name = "storeId"   value="<jsp:getProperty name="customer" property="storeId" />" /></p>
     <p>Store Id: <input type = "text" name = "addressId"   value="<jsp:getProperty name="customer" property="addressId" />" /></p>
     <input type = "submit" value = "Update" />
  </form>
<% } %>
  