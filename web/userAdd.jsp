<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="common.jsp" %>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>用户管理页面 >> 用户添加页面</span>
        </div>
        <div class="providerAdd">
            <!-- <form action="user?method=save" method="post"> -->
            <form action="user?method=save1" method="post" enctype="multipart/form-data">
                <!--div的class 为error是验证错误，ok是验证成功-->
                <div class="">
                    <label for="username">用户账号：</label>
                    <input type="text" name="username" id="username"/>
                    <span>*请输入用户账号，且不能重复</span>
                </div>
                <div>
                    <label for="realname">真实姓名：</label>
                    <input type="text" name="realname" id="realname"/>
                    <span >*请输入用真实姓名</span>
                </div>
                <div>
                    <label for="password">用户密码：</label>
                    <input type="text" name="password" id="password"/>
                    <span>*密码长度必须大于6位小于20位</span>

                </div>
                             
                <div>
                    <label >用户性别：</label>
                    <c:forEach items="${sexs }" var="s">
                    <input type="radio" name="sex" value="${s.id }"/>${s.name }                  
                    </c:forEach>                    
                    <span></span>
                </div>
                <div>
                    <label for="data">出生日期：</label>
                   <input type="text" id="data" name="birthday" value="" readonly="readonly" onClick="SelectDate(this,'yyyy-MM-dd')"/><br/>
                    
                    <span >*</span>
                </div>
                <div>
                    <label for="tel">用户电话：</label>
                    <input type="text" name="tel" id="tel"/>
                    <span >*</span>
                </div>
                <div>
                    <label for="address">用户地址：</label>
                    <input type="text" name="address" id="address"/>
                </div>
                <div>
                    <label >用户类别：</label>
                    <c:forEach items="${types }" var="t">
                    <input type="radio" name="type" value="${t.id }"/>${t.name }                  
                    </c:forEach> 

                </div>
                
                
                <div>
                    <label >头像：</label>                    
                    <input type="file" name="photo" />                                   
                </div>
                
                
                <div class="providerAddBtn">
                    <!--<a href="#">保存</a>-->
                    <!--<a href="userList.html">返回</a>-->
                    <input type="submit" value="保存" />
                    <input type="button" value="返回" onclick="history.back(-1)"/>
                </div>
            </form>
        </div>

    </div>
</section>
<footer class="footer">
</footer>
<script src="js/time.js"></script>

</body>
</html>