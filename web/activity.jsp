<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dao.Dao" %>
<%@ page import="entity.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!doctype html>
<html>

<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <title>活动信息</title>
    <link href="css/index.css" rel="stylesheet" type="text/css">
    <script src="js/jquery-3.4.1.js"></script>
    <script language="javascript">

        function delcfm() {
            if (!confirm("确认要删除？")) {
                window.event.returnValue = false;
            }
        }
        function formatDate(longDate){
            var date = new Date(longDate);
            var yyyy = date.getFullYear();
            var mm = date.getMonth() + 1;
            if (mm < 10) {
                mm = "0" + mm;
            }
            var dd = date.getDate();
            if (dd < 10) {
                dd = "0" + dd;
            }
            return yyyy + "-" + mm + "-" + dd;
        }

        function addfm(a) {
            $.getJSON(
                "servlet/ActivityServlet?action=preUpdate&ano=" + a
                , {},
                function (result) {
                    var json = eval(result.activity);
                    var aname = document.getElementById("upaname");
                    var atime = document.getElementById('upatime');
                    var mno = document.getElementById('upmno');
                    var aprice = document.getElementById("upaprice");
                    var time = formatDate(json.atime);
                    alert(time);
                    aname.value = json.aname;
                    mno.value = json.mno;
                    atime.value = time;
                    aprice.value = json.aprice;
                }
            )
        }

        $(document).ready(function () {
            $(".add").click(function () {
                $("#form").show();
                $("#form").addClass("hide");
            });
            $("#show").click(function () {
                $("#form").hide();
                $("#form").removeClass("hide");
            });
            $(".updateqqtr").click(function () {
                $("#upform").show();
                $("#upform").addClass("hide");
            });
            $("#upshow").click(function () {
                $("#upform").hide();
                $("#upform").removeClass("hide");
            });
        });


    </script>
    <%
        Object message = session.getAttribute("message");
        if (message != null && !"".equals(message)) {
    %>
    <script type="text/javascript">
        alert("<%=message%>");
    </script>
    <%} %>
</head>

<body>

<div class="h" style="position: relative;">
    <div class="wrap-h">
        <div class="wrap"><img src="image/speac.png"></div>
        <div class="wrap-bottom">
            <div class="wh-in">
                <a href="news.jsp">社员信息</a>
                <a href="activity.jsp">活动信息</a>
                <a href="aplace.jsp">活动地点信息</a>

                <a href="myspeac.html" style="float: right;">.....</a>
            </div>
        </div>
    </div>
</div>

<div class="wrap-h">
    <div class="content">
        <%
            double d = Math.random();
            String flag = Double.toString(d);
            session.setAttribute("flag", flag);
        %>
        <div class="centercolumn" style="margin: 10px 0;">
            <div class="card">
                <div class="card-bottom">
                    <div class="card">

                        <div class="add">
                            <input type="button" value="添加活动信息">
                        </div>


                        <div id="form"
                             style="width: 100%;display: none;	position: fixed;right: -50px;top: 300px;background: white;z-index:99;">
                            <form name="addForm" action="servlet/addActivityServlet" method="post" id="myform">
                                <table border="0" width="800" cellspacing="0" cellpadding="0">
                                    <input type="hidden" name="flag" value="<%=flag%>">
                                    <tr>
                                        <td class="lalel">活动名称：</td>
                                        <td class="controler"><input type="text" name="aname" id="aname" required></td>
                                    </tr>

                                    <tr>
                                        <td class="label">活动时间</td>
                                        <td class="controler">
                                            <input name="atime" type="date" id="atime" required>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="label">负责人姓名：</td>
                                        <td class="controler">
                                            <input type="text" name="mno" id="mno" required>
                                        </td>
                                    </tr>
                                    </tr>
                                    <tr>
                                        <td class="label">活动经费：</td>
                                        <td class="controler">
                                            <input type="text" name="aprice" id="aprice" required>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <input id="submit" type="submit" value="提交"/>&nbsp;&nbsp;
                                            <input id="show" type="reset" value="取消"/>&nbsp;&nbsp;
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </div>
                        <div id="upform"
                             style="width: 100%;display: none;	position: fixed;right: -50px;top: 300px;background: white;z-index:99;">
                            <form name="upForm" id="upmyform" action="servlet/ActivityServlet?action=update" method="post">
                                <table border="0" width="800" cellspacing="0" cellpadding="0">
                                    <input type="hidden" id="ano" name="ano" value="">
                                    <tr>
                                        <td class="lalel">活动名称</td>
                                        <td class="controler"><input type="text" name="upaname" id="upaname" required>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td class="label">活动时间：</td>
                                        <td class="controler">
                                            <input name="upaime" type="date" id="upatime" required>
                                        </td>
                                    </tr>
                                    <tr>
                                    <td class="label">负责人姓名：</td>
                                    <td class="controler">
                                        <input type="text" name="upmno" id="upmno" required>
                                    </td>
                                    </tr>
                                    <tr>
                                        <td class="label">活动经费：</td>
                                        <td class="controler">
                                            <input type="text" name="upaprice" id="upaprice" required>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <input id="upsubmit" type="submit" value="提交"/>&nbsp;&nbsp;
                                            <input id="upshow" type="reset" value="取消"/>&nbsp;&nbsp;
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </div>

                        <table>
                            <tr>
                                <th>编号</th>
                                <th>活动名称</th>
                                <th>活动时间</th>
                            </tr>
                            <%
                                List<Activity> activities = new ArrayList<>();
                                activities = Dao.ActivityFindAll();
                                if (activities != null && activities.size() > 0) {
                                    for (int i = 0; i < activities.size(); i++) {
                                        Activity activity = activities.get(i);
                            %>

                            <tr>
                                <td><%=activity.getAno() %>
                                </td>
                                <td><%=activity.getAname() %>
                                </td>
                                <td><%=activity.getAtime() %>
                                </td>

                                <td class="updateqqtr">
                                    <input type="button" name="updateqq" id="updateqq" value="修改"
                                           onclick="addfm(<%=activity.getAno()%>)"></input>
                                </td>
                                <td>
                                    <a href="servlet/ActivityServlet?action=delete&ano=<%=activity.getAno()%>" onclick="delcfm();">删除</a>
                                </td>
                            </tr>


                            <%
                                    }
                                }
                            %>
                        </table>


                    </div>
                </div>
            </div>
        </div>

    </div>
</div>

<div class="footer">
    <p>丁鑫</p>
</div>
</body>

</html>
