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
<script src="card.js"></script>
<style type="text/css">
.container{
   margin-top: 50px;
}
.row{
   margin: 0px auto;
   width: 960px;
}
</style>
</head>
<body>
   <div class="container">
      <div class="row">
            <food_card v-bind:food_list="food_list"></food_card>
      </div>
      <div style="height: 10px"></div>
      <div class="row">
         <div class="text-center">
         <page v-bind:page_list="page_list" 
           ></page>
         </div>
      </div>
   </div>
   <script>
     const pagination={
    		 props:['page_list'],
    		 template:`<ul class="pagination">
						<li><a class="link" v-if="page_list.startpage>1" @click="prev()" >&laquo;</a></li>
						<li v-for="k in range(page_list.startpage,page_list.endpage)"  :class="k===page_list.curpage?'active':''" @click="move(k)"><a >{{k}}</a></li>
						<li><a class="link" v-if="page_list.endpage<page_list.totalpage" @click="next()">&raquo;</a></li>
					</ul>`,
					methods:{
						range(start,end){
						return this.$parent.range(start,end)
						},
						prev(){
							this.$parent.prev()
						},
						next(){
							this.$parent.next()
						},
						move(k){
							this.$parent.move(k)
						}
						
					}
     }
      let app=Vue.createApp({
         data(){
            return{
               food_list:[],
               curpage:1,
               totalpage:0,
	   			startpage:0,
	   			endpage:0,
	   			page_list:{}
            }
         },
         mounted(){
            this.dataRecv()
         },
         methods:{
            dataRecv(){
               // 서버 연결 (스프링)
               axios.get("../food/list_vue.do",{
                  params:{
                     page:this.curpage
                  }
               }).then(res=>{
                  console.log(res.data)
                  this.food_list=res.data
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
				this.page_list=response.data
			})
			
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
    		},
    		range(start,end){
				let size=end-start
				let arr=[]
				
				for(let i=0;i<=size;i++){
					arr[i]=start;
					start++;
				}
				return arr;
			}
        	
         },
         components:{
            "food_card":foodCard,
            "page":pagination
         }
      })
      app.mount(".container")
   </script>
</body>
</html>