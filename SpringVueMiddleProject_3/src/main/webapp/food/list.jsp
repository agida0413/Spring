<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://unpkg.com/vue@3"></script>
<script src="http://unpkg.com/axios/dist/axios.min.js"></script>

<style type="text/css">
.container{
margin-top: 50px;
}
.row{
margin: 0px auto;
width: 960px;
}
a.link:hover,.images:hover{
cursor:pointer
}
</style>
</head>
<body>
	<jsp:include page="${login_jsp }"></jsp:include>
   <div class="container" id="listApp">
      <div class="row">
         <div class="col-md-3" v-for="vo in food_list">
         <a :href="'../food/detail.do?fno='+vo.fno">
            <div class="thumbnail">
                <img :src="'https://www.menupan.com/'+vo.poster" alt="Lights" style="width: 100%">
               <div class="caption">
                  <p style="font-size:8px;">{{vo.name}}</p>
               </div>
            </div>
            </a>
         </div>
      </div>
      
      <div class="row text-center">
       <ul class="pagination">
		  <li v-if="startpage>1"><a href="" @click="prev()" class="link">&lt;</a></li>
		  <li v-for="i in paging(startpage,endpage)" :class="i===curpage?'active':''"><a href="#" @click="move(i)" class="link" >{{i}}</a></li>
		  <li v-if="endpage<totalpage"><a href="#" @click="next()" class="link">&gt;</a></li>
		</ul> 
      </div>
   </div>
</body>
<script>
let app=Vue.createApp({
	data(){
		//Model = > 변경된 내용view(mount에 지정된 html)로 전송
		return{
			food_list:[],
			curpage:1,
			totalpage:0,
			startpage:0,
			endpage:0
			
			
		}
	},
	//viewmodel = > 기능처리 === > 모델에 있는 데이터값(상태)에 변경
	mounted(){
		//시작과 동시에 데이터 처리
		this.list()
	},
	methods:{
		//사용자정의
		
		list(){
			axios.get('../food/list_vue.do',{params:{page:this.curpage}}).then(response=>{
				this.food_list=response.data
			})
			
			axios.get('../food/page_vue.do',{params:{page:this.curpage}}).then(response=>{
				this.curpage=response.data.curpage
				this.totalpage=response.data.totalpage
				this.startpage=response.data.startpage
				this.endpage=response.data.endpage
				
				
			})
		},
		paging(start,end){
			let arr=[]
			let size=end-start;
			for(let i=0;i<=size;i++){
				arr[i]=start;
				start++;
			}
			return arr;
		},
		move(page){
			this.curpage=page
			this.list()
		},
		next(){
			this.curpage=this.endpage+1
			this.list()
		},
		prev(){
			this.curpage=this.startpage-1
			this.list()
		}
		
	},
	updated(){
		//업데이트후 
	}
}).mount('#listApp')


</script>
</html>