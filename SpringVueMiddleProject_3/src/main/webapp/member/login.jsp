<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://unpkg.com/vue@3"></script>
<script src="http://unpkg.com/axios/dist/axios.min.js"></script>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container" id="lg">
<c:if test="${sessionScope.id==null }">
	<div class="row">
		<div class="text-right">
			
			ID:<input type="text" ref="id" v-model="id" class="input-sm">&nbsp;
			PWD:<input type="password" ref="pwd" v-model="pwd" class="input-sm">&nbsp;
			<input type="button" value="로그인" class="btn btn-danger" @click="login()">
		</div>
	</div>
	</c:if>
	<c:if test="${sessionScope.id!=null }">
	
	<div class="row">
		<div class="text-right">
			${sessionScope.name }님로그인 되었습니다.	
		<input type="button" value="로그아웃" class="btn btn-danger" @click="logout">
		</div>
	</div>
	
	</c:if>
</div>
</body>
<script>
let app3=Vue.createApp({
	data(){
		return{
			id:'',
			pwd:''
			
		}	
	},
	methods:{
		login(){
			
			if(this.id===''){
			this.$refs.id.focus()	
			return;
			}
			if(this.pwd===''){
			this.$refs.pwd.focus()
			return;
			}
			
			axios.get('../member/login_vue.do',{params:{id:this.id,pwd:this.pwd}})
			.then(response=>{
				let result = response.data
				if(result==='OK'){
					location.href="../food/list.do"	
				}
				else if(result==='NOID'){
					this.id=''
					this.pwd=''
					this.$refs.id.focus()
					alert('아이디가없습니다.')	
				}
				else{
					this.id=''
						this.pwd=''
							this.$refs.pwd.focus()
					alert('비밀번호가 틀립니다.')	
				}
			})
		},
		logout(){
			location.href="../member/logout.do"
		}
	}
}).mount('#lg')
</script>
</html>