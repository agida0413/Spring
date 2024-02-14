<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

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
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container" id="foodListApp">
	<div class="row">
			
     <div class="col-md-3" v-for="vo in food_list">
	    <div class="thumbnail">
	      <a href="#">
	        <img :src="'http://www.menupan.com'+vo.poster" style="width:100%">
	        <div class="caption">
	          <p style="font-size: 8px">{{vo.name}}</p>
	        </div>
	      </a>
	    </div>
	  </div>
 
	</div>
	
	<div style="height:20px;"></div>
	
	<div class="row">
		<div class="text-center">
			<ul class="pagination">
				<li><a class="link" v-if="startpage>1" @click="prev()" >&laquo;</a></li>
				<li v-for="i in range(startpage,endpage)" :class="i===curpage?'active':' '" @click="move(i)" ><a class="link">{{i}}</a></li>
				<li><a class="link" v-if="endpage<totalpage" @click="next()">&raquo;</a></li>
			</ul>
		</div>
	</div>
</div>

<script>
let foodListApp=Vue.createApp({
	data(){
		return{
			food_list:[],
			curpage:1,
			totalpage:0,
			startpage:0,
			endpage:0
			
		}	
	},
	updated(){
		
	},
	mounted(){
		this.dataRecv()
	},
	methods:{
		//중복코딩이 있는 경우 
		dataRecv(){
			//서버 연결(스프링)
			axios.get('../food/list_vue.do',{
				params:{
					page:this.curpage //list_vue.do?page=1
				}
			}).then(response=>{
				console.log(response.data)
				this.food_list=response.data
			})
			
			axios.get('../food/page_vue.do',{
				params:{
					page:this.curpage //list_vue.do?page=1
				}
			}).then(response=>{
				console.log(response.data)
				this.curpage=response.data.curpage
				this.totalpage=response.data.totalpage
				this.startpage=response.data.startpage
				this.endpage=response.data.endpage
			})
		},
		range(start,end){
			let size=end-start
			let arr=[]
			
			for(let i=0;i<=size;i++){
				arr[i]=start;
				start++;
			}
			return arr;
		},
		next()
		{		this.curpage=this.endpage+1
			this.dataRecv()
		},
		prev(){
			this.curpage=this.startpage-1
			this.dataRecv()
		},
		move(page){
		this.curpage=page
		this.dataRecv()
		}
	},
	components:{
					
	}
	//computed,watch,filter = > router(React) 
}).mount('#foodListApp')
</script>
</body>
</html>