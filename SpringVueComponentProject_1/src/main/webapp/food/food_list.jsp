<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<style type="text/css">
.container{
  margin-top: 50px;
}
.row {
  margin: 0px auto;
  width:960px;
}
.link:hover{
cursor:pointer;
}
</style>
</head>
<body>
<div class="container">
	<div class="row">
		<div class="text-center">
		<my-btn1 v-bind:aa="btn1"></my-btn1>
		
		</div>
	</div>
	
	<div class="row">
		<div class="text-center">
		<my-btn2 v-bind:btn2="btn2"/>
		
		</div>
	</div>
	
	<div class="row">
		<div class="text-center">
		<my-btn3 v-bind:btn3="btn3"/>
		
		</div>
	</div>
	
</div>
</body>

<script>
const mybtn1={
	props:['aa'],	
	template:`<button class="btn-sm btn-danger" @click="btnClick()">{{aa}}</button>`,
	methods:{
		btnClick(){
			this.$parent.btnClick()
		}
	}
}
const mybtn2={
		props:['btn2'],	
	template:`<button class="btn-sm btn-success">{{btn2}}</button>`		
}
const mybtn3={
		props:['btn3'],	
	template:`<button class="btn-sm btn-info">{{btn3}}</button>`								
}

let app=Vue.createApp({
	data(){
		return{
			btn1:'1번버튼',
			btn2:'2번버튼',
			btn3:'3번버튼'
		}
	},
	methods:{
		btnClick(){
			alert('1번버튼클릭')
		}
	},
	components:{
		'my-btn1':mybtn1,<!--태그이름 :  정의한 컴포넌트 이름-->
		'my-btn2':mybtn2,
		'my-btn3':mybtn3
	}
}).mount('.container')
</script>
</html>