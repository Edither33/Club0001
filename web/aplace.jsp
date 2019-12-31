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




        function addfm(a) {
            $.getJSON(
                "servlet/APlaceServlet?action=preUpdate&pno=" + a
                , {},
                function (result) {
                    var json = eval(result.aplace);
                    var pname = document.getElementById("uppname");
                    var pno = document.getElementById("uppno");
                    var pphone = document.getElementById("uppphone");
                    var phead = document.getElementById("upphead");
                    pname.value = json.pname;
                    pno.value = json.pno;
                    pphone.value = json.pphone;
                    phead.value = json.phead;
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
            $("#phead").blur(function () {
                alert("111");
                $.getJSON(
                    "servlet/APlaceServlet?action=check"
                    , {"mno":$("#phead").val()},
                    function (result) {
                        alert("123");
                        var json = eval(result.cmember);
                        alert(result.mes);
                        if(!result.mes){
                            var  a = document.getElementById("phead");
                            a.value="";
                        }
                    }
                )
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
                            <input type="button" value="添加活动地点信息">
                        </div>


                        <div id="form"
                             style="width: 100%;display: none;	position: fixed;right: -50px;top: 300px;background: white;z-index:99;">
                            <form name="addForm" action="servlet/addAPlaceServlet" method="post" id="myform">
                                <table border="0" width="800" cellspacing="0" cellpadding="0">
                                    <input type="hidden" name="flag" value="<%=flag%>">
                                    <tr>
                                        <td class="lalel">活动地点名称：</td>
                                        <td class="controler"><input type="text" name="pname" id="pname" required></td>
                                    </tr>

                                    <tr>
                                        <td class="label">活动地点负责人编号</td>
                                        <td class="controler">
                                            <input name="phead" type="text" id="phead" required>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td class="label">负责人联系方式</td>
                                        <td class="controler">
                                            <input type="text" required id="pphone" name="pphone">
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
                            <form name="upForm" id="upmyform" action="servlet/APlaceServlet?action=update"
                                  method="post">
                                <table border="0" width="800" cellspacing="0" cellpadding="0">
                                    <input type="hidden" id="pno" name="pno" value="">
                                    <tr>
                                        <td class="lalel">活动地点名称：</td>
                                        <td class="controler"><input type="text" name="uppname" id="uppname" required>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td class="label">活动地点负责人编号</td>
                                        <td class="controler">
                                            <input name="upphead" type="date" id="upphead" required>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="label">负责人联系方式</td>
                                        <td class="controler">
                                            <input type="text" required id="uppphone" name="uppphone">
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
                                <th>地点编号</th>
                                <th>地点名称</th>
                                <th>负责人编号</th>
                                <th>负责人电话</th>
                            </tr>
                            <%
                                List<APlace> aPlaces = new ArrayList<>();
                                aPlaces = Dao.APlaceFindAll();
                                if (aPlaces != null && aPlaces.size() > 0) {
                                    for (int i = 0; i < aPlaces.size(); i++) {
                                        APlace aPlace = aPlaces.get(i);
                            %>

                            <tr>
                                <td><%=aPlace.getPno() %>
                                </td>
                                <td><%=aPlace.getPname() %>
                                </td>
                                <td><%=aPlace.getPhead()%>
                                </td>
                                <td><%=aPlace.getPphone()%>
                                </td>

                                <td class="updateqqtr">
                                    <input type="button" name="updateqq" id="updateqq" value="修改"
                                           onclick="addfm(<%=aPlace.getPno()%>)"></input>
                                </td>
                                <td>
                                    <a href="servlet/APlaceServlet?action=delete&mno=<%=aPlace.getPno()%>"
                                       onclick="delcfm();">删除</a>
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
