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
    <title>社员信息</title>
    <link href="css/index.css" rel="stylesheet" type="text/css">
    <script src="js/jquery-3.4.1.js"></script>
    <script language="javascript">

        function delcfm() {
            if (!confirm("确认要删除？")) {
                window.event.returnValue = false;
            }
        }

        function disp_prompt(result) {
            $.getJSON(
                "servlet/CMemberServlet?action=update&mno=" + a,
                {
                    "mname": $("#upmname").value,
                    "maddress": $("#"),
                    "mphone": "",
                    "mbirth": "",
                    "mssex": "",
                    "munit": "",
                    "mhobby": "",
                    "ishead": ""
                },

                function (result) {
                    alert("修改成功");
                }
            )
        }

        function addfm(a) {
            $.getJSON(
                "servlet/CMemberServlet?action=preUpdate&mno=" + a
                , {},
                function (result) {
                    var json = eval(result.cMembers);
                    var mname = document.getElementById("upmname");
                    var mno = document.getElementById("mno");
                    var maddress = document.getElementById("upmaddress");
                    var mphone = document.getElementById("upmphone");
                    var mbirth = document.getElementById('upmbirth');
                    var munit = document.getElementsByName('upmunit');
                    var mhobby = document.getElementById("upmhobby");
                    alert(json.mno);
                    mname.value = json.mname;
                    mno.value = json.mno;
                    maddress.value = json.maddress;
                    mphone.value = json.mphone;
                    mbirth.value = json.mbirth;
                    mhobby.value = json.mhobby + " ";
                    if(json.mssex=="女"){
                        $("input:radio[value='女']").attr('checked','true');
                    }else {
                        $("input:radio[value='男']").attr('checked', 'true');
                    }
                    if(json.ishead=="0"){
                        $("input:radio[value='0']").attr('checked','true');
                    }else {
                        $("input:radio[value='1']").attr('checked', 'true');
                    }
                    var all_select=$("#upmunit > option");
                    for(var i=0;i<all_select.length;i++){
                        var svalue=all_select[i].value;
                        if(json.munit==svalue){
                            $("#upmunit option[value='"+svalue+"']").attr("selected","selected");
                        }
                    }

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
                            <input type="button" value="添加社员">
                        </div>


                        <div id="form"
                             style="width: 100%;display: none;	position: fixed;right: -50px;top: 300px;background: white;z-index:99;">
                            <form name="addForm" action="servlet/addCMemberServlet" method="post" id="myform">
                                <table border="0" width="800" cellspacing="0" cellpadding="0">
                                    <input type="hidden" name="flag" value="<%=flag%>">
                                    <tr>
                                        <td class="lalel">姓名：</td>
                                        <td class="controler"><input type="text" name="mname" id="mname" required></td>
                                    </tr>
                                    <tr>
                                        <td class="label">性别：</td>
                                        <td class="controler"><input type="radio" name="mssex" checked="checked"
                                                                     value="男">男
                                            <input type="radio" name="mssex" value="女">女
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="label">出生日期：</td>
                                        <td class="controler">
                                            <input name="mbirth" type="date" id="mbirth" required>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="label">电话号码：</td>
                                        <td class="controler"><input type="text" name="mphone" id="mphone" required>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td class="label">爱好：</td>
                                        <td class="controler">
                                            <input type="text" name="mhobby" value="" required>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="label">学院：</td>
                                        <td class="controler">
                                            <select name="munit">
                                                <option value="软件">软件</option>
                                                <option value="经管">经管</option>
                                                <option value="交运">交运</option>
                                                <option value="电气">电气</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="label">住处：</td>
                                        <td class="controler">
                                            <input type="text" name="maddress" id="maddress" required>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="label">是否负责人：</td>
                                        <td class="controler">
                                            <input type="radio" name="isHead" value="1">是
                                            <input type="radio" name="isHead" value="0" checked="checked">否
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
                            <form name="upForm" id="upmyform" action="servlet/CMemberServlet?action=update" method="post">
                                <table border="0" width="800" cellspacing="0" cellpadding="0">
                                    <input type="hidden" id="mno" name="mno" value="">
                                    <tr>
                                        <td class="lalel">姓名：</td>
                                        <td class="controler"><input type="text" name="upmname" id="upmname" required>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="label">性别：</td>
                                        <td class="controler">
                                            <input type="radio" name="mssex" value="男" checked>男
                                            <input type="radio" name="mssex" value="女">女
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="label">出生日期：</td>
                                        <td class="controler">
                                            <input name="upmbirth" type="date" id="upmbirth" required>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="label">电话号码：</td>
                                        <td class="controler"><input type="text" name="upmphone" id="upmphone" required>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td class="label">爱好：</td>
                                        <td class="controler">
                                            <input type="text" name="upmhobby" id="upmhobby" required>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="label">学院：</td>
                                        <td class="controler">
                                            <select name="upmunit" id="upmunit">
                                                <option value="软件">软件</option>
                                                <option value="经管">经管</option>
                                                <option value="交运">交运</option>
                                                <option value="电气">电气</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="label">住处：</td>
                                        <td class="controler">
                                            <input type="text" name="upmaddress" id="upmaddress" required>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="label">是否负责人：</td>
                                        <td class="controler">
                                            <input type="radio" name="upisHead" value="1">是
                                            <input type="radio" name="upisHead" value="0" checked>否
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
                                <th>姓名</th>
                                <th>性别</th>
                                <th>学院</th>
                                <th>爱好</th>
                                <th>电话号码</th>
                                <th>操作</th>
                                <th>操作</th>
                            </tr>
                            <%
                                List<CMember> cMembers = new ArrayList<>();
                                cMembers = Dao.cMemberFindAll();
                                if (cMembers != null && cMembers.size() > 0) {
                                    for (int i = 0; i < cMembers.size(); i++) {
                                        CMember cMember = cMembers.get(i);
                            %>

                            <tr>
                                <td><%=cMember.getMno() %>
                                </td>
                                <td><%=cMember.getMname() %>
                                </td>
                                <td><%=cMember.getMssex() %>
                                </td>
                                <td><%=cMember.getMunit() %>
                                </td>
                                <td><%=cMember.getMhobby() %>
                                </td>
                                <td><%=cMember.getMphone() %>
                                </td>
                                <td class="updateqqtr">
                                    <input type="button" name="updateqq" id="updateqq" value="修改"
                                           onclick="addfm(<%=cMember.getMno()%>)"></input>
                                </td>
                                <td>
                                    <a href="servlet/CMemberServlet?action=delete&mno=<%=cMember.getMno()%>"
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
