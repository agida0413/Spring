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
a.link:hover,img.img_click:hover{
  cursor: pointer;
}
</style>
</head>
<body>
<div class="wrapper row3">
  <main class="container clear"> 
    <h2 class="sectiontitle">레시피 목록</h2>
    <!-- main body --> 
    <div class="content" id="recipeApp"> 
      <div id="gallery">
        <figure>
          <header class="heading">총 <span style="font-size: 30px;color:green">{{count}}</span>개의 맛있는 레시피가 있습니다.</header>
          <ul class="nospace clear">
            <li v-for="(vo,index) in recipe_list" :class="index%4==0?'one_quarter first':'one_quarter'"><a :href="'../recipe/recipe_detail.do?no='+vo.no"><img :src="vo.poster" :title="vo.title"></a></li>
          </ul>
          <figcaption></figcaption>
        </figure>
      </div>
      <nav class="pagination">
        <ul>
          <li v-if="startPage>1"><a @click="prev()" class="link">&laquo; Previous</a></li>
          <li v-for="i in range(startPage,endPage)" :class="i==curpage?'current':''"><a @click="pageChange(i)" class="link">{{i}}</a></li>
          <li v-if="endPage<totalpage"><a @click="next()" class="link">Next &raquo;</a></li>
        </ul>
      </nav>
      </div>
    <!-- / main body -->
    <div class="clear"></div>
  </main>
</div>
<script>
  let foodApp=Vue.createApp({
	  // 데이터 관리 => 멤버변수 => this.
	  data(){
		  return {
			  recipe_list:[],
			  curpage:1,
			  totalpage:0,
			  startPage:0,
			  endPage:0,
			  count:''
		  }
	  }/* ,
	  computed:{
		 currency:function(value){
			 let num=new Number(value);
			 return num.toFixed(0).replace(/(\d)(?=(\d{3})+(?:\.\d+)?$)/g, "$1,")
		 } 
	  } */,
	  mounted(){
		  // 브라우저에 화면에 HTML이 실행된 경우에 처리 => 자동 호출 함수 
		  /*
		      자동 호출 함수 => Vue 생명주기
		      beforeCreate() 
		      created()
		      ------------------ Vue 객체 생성 
		      beforeMount() => mount : 가상 메모리에  HTML을 올리는 경우 
		      ***mounted() => window.onload , $(function(){}) , componentDidMount()
		                                                     => HOOKS
		                                                     => useEffect()
		                                                     => class / function = 권장
		      beforeUpdate()
		      ***updated()
		      
		      
		  */
		  this.dataRecv()
	  },
	  updated(){
		  
	  },
	  methods:{
		  // 공통으로 사용되는 함수 => 반복제거 
		  dataRecv(){
			  axios.get('../recipe/recipe_test_vue.do',{
				  params:{
					  page:this.curpage
				  }
			  }).then(response=>{
				  console.log(response.data)
				  this.recipe_list=response.data.list
				  this.curpage=response.data.pages.curpage
				  this.totalpage=response.data.pages.totalpage
				  this.startPage=response.data.pages.startPage
				  this.endPage=response.data.pages.endPage
				  this.count=response.data.pages.count
			  })
		  },
		  range(start,end){
			  let arr=[]
			  let lang=end-start
			  for(let i=0;i<=lang;i++)
			  {
				  arr[i]=start
				  start++
			  }
			  return arr
		  },
		  prev(){
			  this.curpage=this.startPage-1
			  this.dataRecv()
		  },
		  next(){
			  this.curpage=this.endPage+1
			  this.dataRecv()
		  },
		  pageChange(page){
			  this.curpage=page;
			  this.dataRecv()
		  }
	  }
  }).mount("#recipeApp")
</script>
</body>
</html>