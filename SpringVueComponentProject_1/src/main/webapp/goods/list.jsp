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
<script src="page.js"></script>
<script src="list.js"></script>
<style type="text/css">
.container{
   margin-top: 50px;
}
.row{
   margin: 0px auto;
   width: 960px;
}
a.link:hover{
cursor: pointer;
}
</style>
</head>
<body>
<div class="container" id="gListApp">
<div class="row">
<glist v-bind:goods_list="goods_list"></glist>
</div>
<div class="row">
<div class="text-center">
<Pagination v-bind:page_list="page_list"></Pagination>
</div>
</div>
</div>
<script>

let gListApp=Vue.createApp({
   data(){
      return{
         goods_list:[],
         page_list:{},
         curpage:1,
         startpage:0,
         endpage:0,
         totalpage:0
      }
   },
   mounted(){
      this.dataRecv()
   },
   methods:{
      dataRecv(){
         axios.get("../goods/list_vue.do",{
            params:{
               page:this.curpage
            }
         }).then(res=>{
            console.log(res.data)
            this.goods_list=res.data
         })
         
         axios.get("../goods/page_vue.do",{
            params:{
               page:this.curpage
            }
         }).then(res=>{
            console.log(res.data)
            this.page_list=res.data
            this.curpage=res.data.curpage
            this.startpage=res.data.startpage
            this.endpage=res.data.endpage
            this.totalpage=res.data.totalpage
         })
         
      },
      prev(){
         this.curpage=this.startpage-1
         this.dataRecv()
      },
      next(){
         this.curpage=this.endpage+1
         this.dataRecv()
      },
      pagechange(page){
         this.curpage=page
         this.dataRecv()
      }
   },
   components:{
      'glist':GoodsList,
      'Pagination':pagination
   }
}).mount("#gListApp")
</script>
</body>
</html>