<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jstl/fn"%>
<!DOCTYPE html>
<html lang="zh-CN">
<jsp:include page="/include/head.jsp"></jsp:include>

  <body>

<jsp:include page="/include/header.jsp"></jsp:include>

    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span3">
         <jsp:include page="/include/left.jsp"></jsp:include>
        </div>
        <div class="span9">
          <ul class="breadcrumb">
			  <li><a href="#">数据</a> <span class="divider">/</span></li>
		  </ul>
          <div class="row-fluid">
            <table class="table table-hover">
              <thead>
                <tr>
                  <th><input type="checkbox"> 全选</th>
                  <th>id</th>
                  <th>Last Name</th>
                  <th>Username</th>
                </tr>
              </thead>
              <tbody>
              <c:if test="${listTaobaokeItem ne null}">
			  <c:forEach items="${listTaobaokeItem }" var="taobaokeItem">
                <tr>
                  <td><input type="checkbox"> </td>
                  <td>Mark</td>
                  <td>Otto</td>
                  <td>@mdo</td>
                </tr>
              </c:forEach>
			  </c:if>
              </tbody>
            </table>
          </div><!--/row-->
        </div><!--/span-->
      </div><!--/row-->

      <hr>

      <footer>
        <p>&copy; Company 2013</p>
      </footer>

    </div><!--/.fluid-container-->

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="//cdnjs.bootcss.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="//cdnjs.bootcss.com/ajax/libs/twitter-bootstrap/2.3.1/js/bootstrap.min.js"></script>

  </body>
</html>
