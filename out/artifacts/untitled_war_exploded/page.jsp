<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<script type="text/javascript">
     //首页 0  上一页 -1   下一页 1  尾页 2   Go  3
     
     function $$(id){
    	 return document.getElementById(id);
     }
    	function formSbm(num){
    	 // 获取当前的页号
    		var pageNo = $$("pageNo").value;
    		if(num == 0){
    			$$("pageNo").value = 1;
    		}else if(num == -1){
    			$$("pageNo").value = parseInt(pageNo)-1;
    		}else if(num == 1){
    			$$("pageNo").value = parseInt(pageNo)+1;
    		}else if(num == 2){
    			$$("pageNo").value = $$("totalPage").value;
    		}else if(num == 3){
    			var toNum = parseInt($$("toNum").value);
    			if(isNaN(toNum)){
    				toNum=$$("pageNo").value;
    			}else{
    				var totalPage = parseInt($$("totalPage").value);
    				if(toNum > totalPage){
    					toNum = totalPage;
    				}else if(toNum <=0){
    					toNum=1;
    				}					   				
    			}
    			$$("pageNo").value = toNum;
    		} 
    		
    		$$("myForm").submit();
    	}  
    
    </script>
</head>
<body>

<table width="100%">
				  	<tr>
				  		<td>
				  		
				  		 <input type="hidden" name="pageNo" id="pageNo" value="${page.currentPage }"/>
  						 <input type="hidden" name="totalPage"  id="totalPage" value="${page.totalPage }"/>
  
				  		<input type="button" value="首页" onclick="formSbm(0)" />
				  		
				  		<c:if test="${page.currentPage>1 }">
				  		<input type="button" value="上一页" onclick="formSbm(-1)" />
				  		</c:if>
				  		
				  		<c:if test="${page.currentPage < page.totalPage }">
				  		<input type="button" value="下一页" onclick="formSbm(1)" />
				  		</c:if>
				  		
				  		
				  		<input type="button" value="尾页" onclick="formSbm(2)" />
				  		总记录数:${page.totalSize }条
     					每页显示${page.pageSize }条
     					共${page.totalPage }页 
     					当前第${page.currentPage }页
     					<input type="text" name="toNum" id="toNum"/>
     					<input type="button" value="GO" onclick="formSbm(3)"/>
				  		
				  		</td>				  	
				  	</tr>			  				  
				  </table>
</body>
</html>