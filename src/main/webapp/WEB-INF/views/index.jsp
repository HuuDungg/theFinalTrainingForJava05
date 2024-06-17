<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <title>JSP - Hello World</title>
    <%@ page contentType="text/html; charset=UTF-8" %>
</head>
<body>
<form:form modelAttribute="gioHangRequest" action="/gioHang/save" method="post">
  id<form:input path="id" readonly="true"/>
  <form:errors path="id"/> <br>
  ten nguoi nhan<form:input path="tenNguoiNhan"/>
  <form:errors path="tenNguoiNhan"/> <br>
  ngay tao<form:input type="date" path="ngayTao"/>
  <form:errors path="ngayTao"/> <br>
  dia chi<form:input path="diaChi"/>
  <form:errors path="diaChi"/> <br>
  sdt<form:input path="sdt"/>
  <form:errors path="sdt"/> <br>
  khach hang<form:select path="khachHang">
  <option disabled hidden="true" selected>choose your choice</option>
    <form:options items="${listKH}" itemLabel="tenKhachHang"/>
  </form:select>
  <form:errors path="khachHang"/> <br>
  <button>Save</button>
</form:form>

<table>
  <tr>
    <td>ma</td>
    <td>ten nguoi nhan</td>
    <td>ngay tao</td>
    <td>dia chi</td>
    <td>sdt</td>
    <td>ma khach hang</td>
    <td>ten khach hang</td>
    <td>hanh dong</td>
  </tr>
  <c:forEach items="${listG.content}" var="l">
    <tr>
      <td>${l.id}</td>
      <td>${l.tenNguoiNhan}</td>
      <td>${l.ngayTao}</td>
      <td>${l.diaChi}</td>
      <td>${l.sdt}</td>
      <td>${l.khachHang.maKhachHang}</td>
      <td>${l.khachHang.tenKhachHang}</td>
      <td>
        <a href="/gioHang/update?id=${l.id}">Update</a>
      </td>
    </tr>
  </c:forEach>
</table>
<a class="btn btn-dark" href="/gioHang/list">First</a>
<a class="btn btn-dark" href="/gioHang/list?page=${listG.first? 0 : listG.number -1}">Privious</a>
<a class="btn btn-dark" href="/gioHang/list?page=${listG.last? listG.totalPages -1 : listG.number + 1}">Next</a>
<a class="btn btn-dark" href="/gioHang/list?page=${listG.totalPages -1}">Last</a>
</body>
</html>