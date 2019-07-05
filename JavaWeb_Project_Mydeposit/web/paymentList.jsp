
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>支出记录查询页</title>
    <%@include file="link.jsp"%>
  </head>

  <body>
    <div class="container">
      <div class="py-5 text-center">
        <img class="d-block mx-auto mb-4" src="images/Blogo.png" alt="" width="72" height="72">
        <h3>支出统计</h3>
      </div>

      <div class="row">
        <div class="col-md-8">
          <h4>
            <span class="text-muted">查询列表</span>
          </h4>

          <form id="checkform" method="get" action="/PaymentListQueryServlet">
            <div class="row">
              <div class="col-md-6 form-group">
                <label>开始日期</label>
                <input type="text" class="form-control" name="startDate" id="startDate" value=""  />
              </div>
              <div class="col-md-6 form-group">
                <label>结束日期</label>
                <input type="text" class="form-control" name="endDate" id="endDate" value="" />
              </div>
              <div class="col-md-1 pull-center"></div>
            </div>

            <div class="row">
              <div class="col-md-6 form-group">
                <label>最小金额</label>
                <input type="text" class="form-control" name="minAmount" id="minAmount" value="" />
              </div>
              <div class="col-md-6 form-group">
                <label>最大金额</label>
                <input type="text" class="form-control" name="maxAmount" id="maxAmount" value="" />
              </div>
            </div>

            <hr/>
            <div class="row">
              <div class="col-md-12">
                <button class="btn btn-primary btn-block" type="submit" onclick="">查询</button>
              </div>
            </div>
          </form>
          
          <hr/>
          <h4>
            <span class="text-muted">数据列表</span>
          </h4>
          <form  id="updateForm">
          <div class="pull-right">
          	<p class="text-danger" id="residueHTML"></p>
                <div class="pull-right row-margin-bottom">
                    <button type="button" id="updateButton"  disabled="disabled" class="btn btn-warning" data-toggle="modal" data-target="#updateModal" onclick="">修改</button>

                    <button type="button" class="btn btn-danger" onclick="deleteCheck()">删除</button>
                </div>
          </div>
          <%--修改的模态框--%>
          <div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
              <div class="modal-dialog" role="document">
                  <div class="modal-content">
                      <div class="modal-header">
                          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                          <h4 class="modal-title" id="myModalLabel">修改支出页面</h4>
                      </div>
                      <form >
                          <div class="modal-body">
                              <%--隐藏域--%>
                              <input type="hidden" name="modify_id" value="" id="modify_id">
                              用途：<input class="upDate" id="modify_usage" type="text" value="" name="modify_usage"/><br /><br />
                              支出金额：<input class="upDate" id="modify_amount" type="text" value="" name="modify_amount"/><br /><br />
                              支出日期：<input class="upDate" id="modify_date" type="text" value="" name="modify_date" readonly/><br /><br />
                          </div>
                          <div class="modal-footer">
                              <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                              <button type="button" class="btn btn-primary" onclick="modify()">提交</button>
                          </div>
                      </form>
                  </div>
              </div>
          </div>

          <table class="table table-striped table-bordered bgcolorWhite" id="data-table">
              <thead>
                <tr>
                  <th>#</th>
                  <th>序号</th>
                  <th>支出金额</th>
                  <th>用途</th>
                  <th>支出日期</th>
                </tr>
              </thead>
              <tbody id="paymentList_tr">
                <c:forEach items="${page.pageDataList}" var="payment" varStatus="status">
                    <tr>
                        <td><input type="checkbox" name="pid" value="${payment.id}" id="pid"/></td>
                        <td>${status.count}</td>
                        <td>${payment.pay_amount}</td>
                        <td>${payment.pay_usage}</td>
                        <td ><fmt:formatDate value="${payment.pay_date}" pattern="yyyy-MM-dd日"/></td>
                    </tr>
                </c:forEach>
              </tbody>
            </table>
          </form>

          
		  <ul class="pagination pull-right">
            <c:if test="${page.pageNumber > 1}">
                <li><a onclick="pageFunction(${page.pageNumber - 1})">&laquo;</a></li>
            </c:if>
            <c:forEach begin="1" end="${page.totalPageNumber}"  varStatus="status">
                <c:choose>
                    <c:when test="${page.pageNumber == status.index}">
                        <li class="active"><a onclick="pageFunction(${status.index})">${status.index}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a onclick="pageFunction(${status.index})">${status.index}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${page.pageNumber < page.totalPageNumber}">
                  <li><a onclick="pageFunction(${page.pageNumber + 1})">&raquo;</a></li>
            </c:if>
              <li></li>
          </ul>

      </div>

        <div class="col-md-4">
          <h4>
              <span class="text-muted">菜单</span>
          </h4>
          <ul class="list-group mb-3">
              <li class="list-group-item d-flex justify-content-between lh-condensed">
                  <div>
                      <h5>收入登记</h5>
                      <ul>
                          <li>
                              <a href="">
                                  <small class="text-muted">新增收入</small>
                              </a>
                          </li>
                          <li>
                              <a href="">
                                  <small class="text-muted">查询收入</small>
                              </a>
                          </li>
                      </ul>
                  </div>
              </li>
              <li class="list-group-item d-flex justify-content-between lh-condensed">
                  <div>
                      <h5>支出登记</h5>
                      <ul>
                          <li>
                              <a href="addPayment.jsp">
                                  <small class="text-muted">新增支出</small>
                              </a>
                          </li>
                          <li>
                              <a href="">
                                  <small class="text-muted">查询支出</small>
                              </a>
                          </li>
                      </ul>
                  </div>
              </li>
          </ul>
        </div>
        
      </div>

      <footer class="text-muted text-center text-small">
        <p>&copy; Designed By 七哥</p>
      </footer>
    </div>

    
  </body>
  <script src="js/jquery-3.3.1.min.js"></script>
  <script src="js/jquery.dataTables.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/bootstrap-datetimepicker.min.js"></script>
  <script src="js/bootstrap-paginator.min.js"></script>
  <script src="js/self-defined.js"></script>

  <script type="text/javascript">
      getReady();

      function pageFunction(pageNumber){
          var url = "PaymentListQueryServlet?pageNumber=" + pageNumber;
          url += "&startDate=" + $("#startDate").val();
          url += "&endDate=" + $("#endDate").val();
          url += "&minAmount=" + $("#minAmount").val();
          url += "&maxAmount=" + $("#maxAmount").val();

          $(this).click(function () {
              location.href = url;
          })
      }


      $(function(){
          $("#startDate,#endDate").datetimepicker({
              format: 'yyyy-mm-dd',
              language: 'zh-CN',
              autoclose: 1,
              minView: 'month',
              todayBtn: true
          })
      });

      /*//查询功能
      function query() {
          $.ajax({
              url: "PaymentListQueryServlet",
              type: "post",
              dataType: "json",
              data: {
                  startDate : $("#startDate").val(),
                  endDate: $("#endDate").val(),
                  minAmount: $("#minAmount").val(),
                  maxAmount: $("#maxAmount").val()
              },
              success: function (data) {
                  if (data) {
                      var tbhtml = "";
                      $(data).each(function (index,item) {
                          tbhtml += "<tr>";
                          tbhtml += "<td><input type='checkbox' name='pid' id='pid' value='"+ item.id +"'/></td>";
                          tbhtml += "<td>"+ (index + 1) +"</td>";
                          tbhtml += "<td>"+ item.pay_amount +"</td>";
                          tbhtml += "<td>"+ item.pay_usage +"</td>";
                          tbhtml += "<td>"+ getDate(new Date(item.pay_date)) + "</td>";
                          tbhtml += "</tr>";
                      });
                      $("#paymentList_tr").html(tbhtml);
                      getReady();
                  }
              },
              error:function (data) {
                  alert("出错了，请稍等！")
              }

          })

      }*/

      //删除功能
      function deleteCheck(){
          //验证复选框
          var lines = $("[name = 'pid']:checked");
          if (lines == null || lines.length == 0) {
              alert("请至少选择一条数据！");
              return;
          }
          if (!confirm("确定删除吗？")) {
              return;
          }
          var idArray = [];
          lines.each(function () {
              idArray.push($(this).val());
          });
          //提交数据
          $.ajax({
              "url":"DeleteServlet",
              "type":"post",
              "data": {
                  pid: idArray.toString()
              },
              "dataType":"text",
              "success":function (data) {
                  debugger;

                  if (data == "true") {
                      alert("删除成功！");
                      location.reload();

                  } else {
                  }
              },
              "error":function () {
                  alert("出错了，请稍等！")
              }
          })
      }

      function getReady() {
          //监听修改按钮的启用与禁用
          $(":checkbox").on("change",function () {
              var checkedLines = $("[name='pid']:checked");
              if (checkedLines == null || checkedLines.length != 1) {
                  $("#updateButton").attr("disabled",true);//jQuery设置disabled属性值为true(按钮隐藏)
              } else {
                  $("#updateButton").attr("disabled",false);//按钮显示
                  $("[name='pid']:checked").parents("tr").each(function() {
                      $("#modify_id").val(checkedLines.val());
                      $("#modify_amount").val($(this).children().eq(2).text());
                      $("#modify_usage").val($(this).children().eq(3).text());
                      $("#modify_date").val($(this).children().eq(4).text())

                  });
              }
          })
      }

      //修改功能
      function modify() {
          $.ajax({
              url:"ModifyServlet",
              type:"post",
              dataType:"text",
              data:{
                  modify_id:$("#modify_id").val(),
                  modify_usage:$("#modify_usage").val(),
                  modify_amount:$("#modify_amount").val(),
                  modify_date:$("#modify_date").val()
              },
              success:function (data) {
                  if (data == "true") {
                      $("#updateModal").modal('hide');
                      location.reload();
                      alert("修改成功！");

                  }
              },
              error:function () {
                  alert("请稍等！")
              }
          })
      }
  </script>
</html>
