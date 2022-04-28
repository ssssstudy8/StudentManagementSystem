<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2022/4/12
  Time: 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@include file="common.jsp"%>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>用户管理页面</span>
        </div>
        <div class="search">
            <span>用户名：</span>
            <input type="text" placeholder="请输入用户名"/>
            <input type="button" value="查询"/>
            <a href="userAdd.html">添加用户</a>
        </div>
        <!--用户-->
        <table class="providerTable" cellpadding="0" cellspacing="0">
            <tr class="firstTr">
                <th width="10%">用户账号</th>
                <th width="20%">真实姓名</th>
                <th width="10%">性别</th>
                <th width="10%">年龄</th>
                <th width="10%">电话</th>
                <th width="10%">用户类型</th>
                <th width="30%">操作</th>
            </tr>
            <c:forEach items="${users}" var="us">
            <tr>
                <td>${us.username}</td>
                <td>${us.realname}</td>
                <td>${us.sex}</td>
                <td>${us.birthday}</td>
                <td>${us.tel}</td>
                <td>${us.type}</td>
                <td>
                    <a href="userView.html"><img src="img/read.png" alt="查看" title="查看"/></a>
                    <a href="userUpdate.html"><img src="img/xiugai.png" alt="修改" title="修改"/></a>
                    <a href="#" class="removeUser"><img src="img/schu.png" alt="删除" title="删除"/></a>
                </td>
            </tr>
            </c:forEach>

            <tr>
                <td>PRO-COD1</td>
                <td>陈晨</td>
                <td>男</td>
                <td>30</td>
                <td>15918230478</td>
                <td>老师</td>
                <td>
                    <a href="userView.html"><img src="img/read.png" alt="查看" title="查看"/></a>
                    <a href="userUpdate.html"><img src="img/xiugai.png" alt="修改" title="修改"/></a>
                    <a href="#" class="removeUser"><img src="img/schu.png" alt="删除" title="删除"/></a>
                </td>
            </tr>

            <tr>
                <td>admin</td>
                <td>冯小刚</td>
                <td>男</td>
                <td>40</td>
                <td>15918230478</td>
                <td>管理员</td>
                <td>
                    <a href="userView.html"><img src="img/read.png" alt="查看" title="查看"/></a>
                    <a href="userUpdate.html"><img src="img/xiugai.png" alt="修改" title="修改"/></a>
                    <a href="#" class="removeUser"><img src="img/schu.png" alt="删除" title="删除"/></a>
                </td>
            </tr>
            <tr>
                <td colspan="7">
                    当前第1页，6条数据&nbsp;&nbsp;&nbsp;&nbsp;共2页
                    首页
                    尾页
                    上一页
                    下一页
                </td>
            </tr>
        </table>

    </div>
</section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeUse">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该用户吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

<footer class="footer">
</footer>

<script src="js/jquery.js"></script>
<script src="js/js.js"></script>
<script src="js/time.js"></script>

</body>
</html>
