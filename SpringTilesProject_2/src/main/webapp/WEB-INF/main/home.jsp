<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.aLink:hover{
cursor:pointer;
}
.imglink:hover{
cursor:pointer;
}
</style>
</head>
<body>
<div class="container" id="food">
	<div class="row">
		 <div class="col-md-4" v-for="vo in foodList">
   			 <div class="thumbnail">
  		<a :href="'../food/detail.do?fno='+vo.fno">
        <img :src="'https://www.menupan.com/'+vo.poster"  style="width:100%" class="imglink">
        <div class="caption">
          <p style="font-size:8px;">{{vo.name}}</p>
        </div>
     </a>
    </div>
  </div>
	</div>
	
	<div style="height:20px;"></div>
	
	
	<div class="row">
		<div class="text-center">
			 <ul class="pagination">
			 <li><a v-if="startpage>1" @click="firstPage()" class="aLink">&laquo;</a></li>
				  <li><a v-if="startpage>1" @click="prev()" class="aLink">&lt;</a></li>
				 
				  <li v-for="i in range(startpage,endpage)" @click="move(i)" :class="curpage===i?'active':''"><a class="aLink">{{i}}</a></li>
				  <li><a v-if="endpage<totalpage" @click="next()" class="aLink">&gt;</a></li>
				  
				  <li><a v-if="endpage<totalpage" @click="lastPage()" class="aLink">&raquo;</a></li>
			</ul> 		
		</div>
	</div>
	
</div>
<script>
let foodApp=Vue.createApp({
	data(){
		return{
			foodList:[],
			curpage:1,
			totalpage:0,
			startpage:0,
			endpage:0
		}
	},
	mounted(){
		this.dataRecv()
	},
	methods: {
		   dataRecv() {
		      axios.get('../food/list_vue.do', {
		         params: {
		            page: this.curpage
		         }
		      })
		      .then(res => {
		         this.foodList = res.data;
		         console.log(this.foodList);
		      })
		      .catch(error => {
		         console.error('Error fetching data:', error);
		      });
		      
		      
		      axios.get('../food/page_vue.do', {
			         params: {
			            page: this.curpage
			         }
			      })
			      .then(res => {
			        this.curapge=res.data.curpage
			        this.totalpage=res.data.totalpage
			        this.startpage=res.data.startpage
			        this.endpage=res.data.endpage
			        
			      })
			      .catch(error => {
			         console.error('Error fetching data:', error);
			      });
		      
		     
		   },
		   range(start,end){
			   let leng= end-start
			   let arr=[]
			   for(let i=0;i<=leng;i++){
				   arr[i]=start
				   start++;
			   }
			   return arr
			   
		   },
		   move(page){
			   this.curpage=page
			   this.dataRecv()
		   },
		   next(){
			   this.curpage=this.endpage+1
			   this.dataRecv()
		   },
		   prev(){
			   this.curpage=this.startpage-1
			   this.dataRecv()
		   },
		   firstPage(){
			   this.curpage=1
			   this.dataRecv()
		   },
		   lastPage(){
			 this.curpage=this.totalpage  
			 this.dataRecv()
		   }
		}
}).mount('#food')
</script>
</body>
</html>