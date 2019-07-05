<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>登录页</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrapValidator0.4.5.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/signin.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/self-defined.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/jquery.dataTables.min.css" rel="stylesheet">
  </head>

  <body class="text-center">
    <%
      String[] s = {"", ""};
      Cookie[] cookies = request.getCookies();
      if (cookies != null) {
        for (Cookie co:cookies) {
          if (co.getName().equals("userInfo")) {
            String str = co.getValue();
            s = str.split("-");
          }
        }
      }
    %>
    <form class="form-signin form-horizontal" id="checkform" action="/LoginServlet" method="post">
      <div class="row row-margin-bottom">
        <img class="d-block mx-auto mb-4" src="${pageContext.request.contextPath}/images/Blogo.png" alt="" width="72" height="72">
      </div>
      <div class="row row-margin-bottom form-group">
        <label>用户名：</label>
        <input type="text" class="form-control" name="user.username" id="username" value="<%=s[0]%>" required>
        <p id="nameP"></p>
      </div>
      <div class="row row-margin-bottom form-group">
        <label>密码：</label><br/>
          <input type="password" class="form-control" name="user.password" id="password" value="<%=s[1]%>" required>
      </div>
      <div class="row row-margin-bottom">
        <button class="btn btn-lg btn-primary" type="submit">登录</button>
        <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
          注册
        </button>
      </div>
      <p class="mt-5 mb-3 text-muted">&copy; Designed By 七哥</p>
    </form>
    <!-- Modal -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="myModalLabel">欢迎注册</h4>
          </div>
          <form class="form-inline" action="/RegisterServlet" method="post" id="zhuce">
            <div class="modal-body">
              <div class="form-group">
                <label for="uname">用户名</label>
                <input type="text" class="form-control" name="uname" id="uname" placeholder="Jane Doe">
              </div>
              <div class="form-group">
                <label for="psw">密码</label>
                <input type="password" class="form-control" name="psw" id="psw">
              </div>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
              <button type="submit" class="btn btn-primary">提交</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  <script src="js/jquery-3.3.1.min.js"></script>
  <script src="js/jquery.dataTables.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/bootstrap-datetimepicker.min.js"></script>
  <script src="js/bootstrap-paginator.min.js"></script>
  <script src="js/bootstrapValidator0.4.5.min.js"></script>
  <script src="js/self-defined.js"></script>

  <script type="text/javascript">

    $("#zhuce").bootstrapValidator({
      feedbackIcons : {
        valid : 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
      },
      fields: {
        psw: {
          validators:{
            notEmpty: {
              message: "密码不能为为空！"
            },
            stringLength: {
              min: 6,
              max: 12,
              message: "密码长度不能小于6"
            }
          }
        }
      }
    })

    if ("${tips}") {
      alert("${tips}");
    }
    //用户输入时验证用户输入的用户名是否已存在
    $(document).ready(function () {
        $("#username").blur(function () {
            var name = $("#username").val();
            if (name == null || name == "") {
                $("#nameP").html("用户名不能为空！");
            } else {
                $.ajax({
                "url":"CheckLoginServlet",
                "type":"post",
                "data":"name=" + name,
                "dataType":"text",
                "success":function (data) {
                  if (data == "true") {
                    $("#nameP").html("用户名存在，请输入密码！");
                  } else {
                    $("#nameP").html("用户名不存在，请先注册！");
                  }
                },
                "error":function () {
                  alert("请稍等！")
                }
              })
            }
        })
    })
  </script>
  </body>
</html>
    