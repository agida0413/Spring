<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
 <!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>

<style type="text/css">



.findOpt{
border:1px black solid;
padding:20px 20px 3px 20px;
}
.optionTable td {
    border: 1px solid #ddd; /* 셀 간 경계선 스타일 */
    padding: 8px; /* 셀 내부 여백 설정 */
}


/* 프로그램 선택 바 스타일 */
.programSelectBar {	
    border: none;
    overflow-y: scroll;
    scrollbar-width: thin;
    scrollbar-color: transparent transparent;
    width: 120px;
    margin: 0;
}

.programSelectBar:hover {
    scrollbar-color: #999 transparent;
}
.findTable tr td {
    border: none !important;
}

.findNum{
padding-top:20px;
border-bottom:2.0px black solid;
  
}

.programSubinformAll{
font-size:13px;
 margin-right:15px;
}

.programSubinform{
margin-left:10px; 
opacity:0.8;
}

.nextline{
 border-bottom: 1px rgba(0, 0, 0, 0.5) solid; /* 투명도를 조절하는 부분: 0.5는 50% 투명 */
padding-bottom:10px;
}

a.link:hover{
  cursor: pointer;
}
/* 셀렉트 박스 스타일 */

  
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  
  <div class="container" id="optionSelect">
  		<form @submit.prevent="findClick">
        <div class="row">
            <!-- 왼쪽에 programAside.jsp 포함 -->
           
            
            <!-- 나머지 영역 -->
          
            <div class="row selectOpt">
                <table class="table optionTable">
                    <thead>
                        <th colspan="2" class="text-center">봉사지역</th>
                        <th colspan="2" class="text-center">봉사분야</th>
                        <th class="text-center">활동구분</th>
                        <th class="text-center">봉사대상</th>
                        <th class="text-center">모집상태</th>
                        
                    </thead>
					
                    <tbody>
                    <tr>
                    
                    <td>
                    	<div>
                    		<select size="4" class="programSelectBar" v-model="Option.state" @change="stateChange()">
                    					<option value="" selected>전체</option>
                    		   <c:forEach var="svo" items="${sList}">
                    		   			
                      					<option value="${svo.state }" ${ovo.state eq svo.state ? 'selected' : ''}>${svo.state }</option>
                    		   </c:forEach>
                    			
                    		</select>
                    	</div>
                    </td>
                    
                    
                    <td>
                    	<div>
                    		<select size="4" class="programSelectBar" v-model="Option.city">
                    						<option value="" ${ovo.major_option eq '' ? 'selected' : ''}>전체</option>
                    			
                      					<option :value="vo.city" v-for="vo in cityList">{{vo.city}}</option>
                    		  
                    		</select>
                    	</div>
                    </td>
                    
                    <td>
                    	<div>
                    		<select size="4" class="programSelectBar" v-model="Option.major_option" @change="major_fieldChange()">
                    				<option value="" ${ovo.major_option eq '' ? 'selected' : ''}>전체</option>
                    			<c:forEach var="mjvo" items="${mjList}">
                      					<option value="${mjvo.major_option }" ${ovo.major_option eq mjvo.major_option ? 'selected' : ''}>${mjvo.major_option }</option>
                    		   </c:forEach>
                    			
                    		</select>
                    	</div>
                    </td>
                    
                    <td>
                    	<div>
                    		<select size="4" class="programSelectBar" v-model="Option.minor_option">
                    					<option value="" ${ovo.minor_option eq '' ? 'selected' : ''}>전체</option>
                    					<option :value="vo.minor_option" v-for="vo in minor_fieldList">{{vo.minor_option}}</option>
                    		</select>
                    	</div>
                    </td>
                    
                    <td>
                    	<div>
                    		<select size="4" class="programSelectBar" v-model="Option.active_type">
                    			<option value="">전체</option>
                    			<option value="온라인">온라인</option>
                    			<option value="오프라인">오프라인</option>
                    		</select>
                    	</div>
                    </td>
                    
                    <td>
                    	<div>
                    		<select size="4" class="programSelectBar" v-model="Option.target_type">
                    			<option value="">전체</option>
                    			<option value="아동ㆍ청소년">아동ㆍ청소년</option>
                    			<option value="장애인">장애인</option>
                    			<option value="노인">노인</option>
                    			<option value="쪽방촌">쪽방촌</option>
                    			<option value="다문화가정">다문화가정</option>
                    			<option value="여성">여성</option>
                    			<option value="환경">환경</option>
                    			<option value="사회적기업">사회적기업</option>
                    			<option value="고향봉사">고향봉사</option>
                    			<option value="기타">기타</option>
                    		</select>
                    	</div>
                    </td>
                    
                    <td>
                    	<div>
                    		<select size="4" class="programSelectBar" v-model="Option.collect_state">
                    			<option value="">전체</option>
                    			<option value="모집중">모집중</option>
                    			<option value="모집완료">모집완료</option>
                    		</select>
                    	</div>
                    </td>
                    
                    </tr>
                    	
                    </tbody>
                    
                  
                </table>
               </div>
                
               <div class="row findOpt">
    		<table class="table findTable">
    			<tr>
    				<td style="width:50%;" >
    				<img src="../Projectimages/calender.png" style="width:20px; float:left;">
    				<dl>
    					<dt style="float:left;margin-left:10px; margin-right:40px;">봉사기간</dt>
    						<dd>
    						
    						<input type="date" v-model="Option.v_start" style="margin-right:10px;">~<input type="date" v-model="Option.v_end" style="margin-left:10px;">
    						
    						</dd>
    				</dl>
    				</td>
    				
    				
    				<td style="width:50%">
    					<img src="../Projectimages/calender.png" style="width:20px; float:left;">
    					<dl>
    					<dt style="float:left;margin-left:10px; margin-right:30px;">모집기간</dt>
    						<dd>
    						
    						<input type="date" v-model="Option.collect_start" style="margin-right:10px;">~<input type="date" v-model="Option.collect_end" style="margin-left:10px;">
    						
    						</dd>
    				</dl>
    				</td>
    			</tr>
    			<tr>
    				<td><img src="../Projectimages/check.png" style="width:20px; float:left;">
    					 <dl>
    					  <dt style="float:left;margin-left:10px; margin-right:30px;">봉사자유형</dt>
    						<dd>
    							
    						  <input type="checkbox" v-model="volunteerCondition.A" checked>&nbsp;성인&nbsp;
			                <input type="checkbox" v-model="volunteerCondition.C" >&nbsp;청소년&nbsp;
			               
    						</dd>
    				   </dl>
    				</td>
    				<td><img src="../Projectimages/check.png" style="width:20px; float:left;">
    					<dl>
    					  <dt style="float:left;margin-left:10px; margin-right:60px;">요일</dt>
    						<dd>
    							
			                <input type="checkbox" v-model="weekCondition.weekday">&nbsp;평일&nbsp;
			                <input type="checkbox" v-model="weekCondition.weekend">&nbsp;주말&nbsp;
			                
    						</dd>
    				   </dl>
    				
    				</td>
    			</tr>
    			<tr>
    				<td><img src="../Projectimages/searchIcon.png" style="width:20px; float:left;">
    					<dl>
    						 <dt style="float:left;margin-left:10px; margin-right:55px;">검색어</dt>
    						 
    						 <dd><input type="text" v-model="Option.ss" size=30></dd>
    					</dl>
    				</td>
    				<td><img src="../Projectimages/check.png" style="width:20px; float:left;">
    				<dl>
    					  <dt style="float:left;margin-left:10px; margin-right:20px;">키워드조건</dt>
    						<dd>
    							
    						  <input type="checkbox" v-model="ssCondition.N" checked>&nbsp;이름&nbsp;
			                <input type="checkbox" v-model="ssCondition.C" checked>&nbsp;봉사수요처&nbsp;
			                <input type="checkbox" v-model="ssCondition.L" checked>&nbsp;주소&nbsp;
			                <input type="checkbox" v-model="ssCondition.F" checked>&nbsp;봉사분야&nbsp;
    						</dd>
    				   </dl>
    				
    				</td>
    			</tr>
    			<tr class="text-center">
    			<td colspan=2>
    			<span style="margin-right:15px;"><input type="submit" class="btn btn-lg btn-danger" value="검색" style="width: 200px;"></span>
    				<span><input type="button" class="btn btn-lg btn-primary" value="초기화" style="width: 200px;" @click="optionReset()"></span>
    			</td>
    			</tr>
    		</table>
    		</div>
    		
    		
    	
    	<!-- 검색결과갯수정보 -->	
    	<div class="row findNum">	
				<!-- 검색 결과 숫자 카운트 -->
				<div class="" v-if="size!=0">
					<p>[전체 <em>{{size}}</em>건, 현재페이지 <em>{{curpage}}</em>/{{totalpage}}]</p>
				</div>
				<!-- 검색결과 없을시 -->
				<div class="" v-if="size==0">
					<p>[전체 <em>{{size}}</em>건]</p>
				</div>
				<!--// 검색 결과 숫자 카운트 -->
  		</div>
  	
  		<!-- 검색결과갯수정보 끝  -->	
  		
  		
  		<!-- 검색리스트 -->
  		<div class="row nextline" v-for="vo in programList">
  			<a :href="'../program/detail.do?vno='+vo.vno" style="color:black">
  			<div class="findList">
  				
	  			<div class="col-sm-10">
	  				<div class="" style="margin-top:15px; margin-bottom:10px;">
						<!-- 봉사 분야 -->	
						<span class="">
						{{vo.active_type}} &nbsp;&nbsp;|&nbsp;&nbsp;{{vo.major_field}}&nbsp;&gt;&nbsp;{{vo.minor_field}}
						</span>
					</div>
					<!-- 봉사프로그램정보 -->
					<div class="">
						<div class="programName">
							<span style="font-size:20px; font-weight:bold;">{{vo.title}}</span>
						</div>
						
						<div style="margin-top:7px;">
						<span class="programSubinformAll"><span style="font-weight:bold;">[봉사기간]</span><span class="programSubinform">{{vo.dbV_start}}~{{vo.dbV_end}}</span></span>
						 <span class="programSubinformAll"><span style="font-weight:bold;">[모집기간]</span><span class="programSubinform">{{vo.dbCollect_start}} ~ {{vo.dbCollect_end}}</span></span> 
						  <span class="programSubinformAll"><span style="font-weight:bold;">[봉사시간]</span><span class="programSubinform">{{vo.runtime}}</span></span>
						</div>
						
						<div style="margin-top:7px;">
						<span class="programSubinformAll"><span style="font-weight:bold;">[지역]</span><span class="programSubinform">{{vo.si}}&nbsp;{{vo.gu}} </span></span> 
						<span class="programSubinformAll"><span style="font-weight:bold;">[모집기관]</span><span class="programSubinform">{{vo.centername}}</span></span>
						</div>
					</div>
				</div>
				
				<div class="col-sm-2">
					<!-- 모집상태 -->
					<div style="margin:30px;">
						<div class="closeBox" style="height:60px; width:60px; border:1px black solid;">
						<div style="padding:5px;" class="text-center">금일<br> 마감</div>
						</div>
							<div style="margin-top:5px;">모집완료</div>
					</div>
					
				</div>				
				
  		   </div>
  		</a>
  		</div>
  		<!-- 검색리스트 끝-->
  		
  		<!-- 검색결과없음 -->
  		<div class="row" v-if="size==0" style="margin-top:20px;">
  		<span style="font-size:25px; opacity:0.8;">검색 결과가 없습니다.</span>
  		</div>
  		
  		
  		<!-- 페이징 -->
  			<div class="row text-center">
  			 <ul class="pagination" v-if="totalpage!=0">
  			 		 <li @click="firstpage"><a v-if="curpage>1" class="link">&lt;&lt;</a></li>
				  <li @click="prev()"><a v-if="start>1" class="link">&lt;</a></li>
				  <li v-for="i in range(start,end)" :class="curpage===i?'active':''" @click="move(i)"><a class="link">{{i}}</a></li>
				 
				  <li @click="next()"><a v-if="end<totalpage" class="link">&gt;</a></li>
				   <li @click="lastpage"><a v-if="curpage!==totalpage" class="link">&gt;&gt;</a></li>
				   
				   
				</ul> 
  			</div>
  	
  	
  		
            
        </div>
          
          </form>
          
          
    </div>
 
  
  
  
    
<script>
let programList=Vue.createApp({
	data(){
		return{
			  initialState:[], 
			stateList:[],
			cityList:[],
			major_filedList:[],
			minor_fieldList:[],
			programList:[],
			curpage:1,
			totalpage:0,
			start:0,
			end:0,
			size:0,
			Option:{
			state:'',
			city:'',
			major_option:'',
			minor_option:'',
			active_type:'',
			target_type:'',
			collect_state:'모집중',
			ss:'',
			v_start: new Date().toISOString().split('T')[0], // 오늘 날짜
	        v_end: new Date(new Date().setMonth(new Date().getMonth() + 8)).toISOString().split('T')[0],// 오늘 날짜로부터 1달 후
	        collect_start: new Date().toISOString().split('T')[0], // 오늘 날짜
	        collect_end: new Date(new Date().setMonth(new Date().getMonth() + 8)).toISOString().split('T')[0]// 오늘 날짜로부터 1달 후
	       
			},
			 ssCondition: {
	                N: true,
	                C: false,
	                L: false,
	                F: false
	            },
	            volunteerCondition:{
	            	A:true,
	            	C:true
	            },
	            weekCondition:{
	            
	            	weekday:true,
	            	weekend:true
	            },
	            ssConditionString:'',
	            vtConditionString:'',
	            weekString:''
	            
		}
		
	},
	mounted(){
		this.findClick()
	},
	methods:{
		stateChange(){
			axios.get("../program/stateChange_vue.do",{params:this.Option}).then(response=>{
				this.Option.city=''
				this.cityList=response.data
				
			})
		},
		major_fieldChange(){
			axios.get("../program/major_fieldChange_vue.do",{params:this.Option}).then(response=>{
				this.Option.minor_option=''
				this.minor_fieldList=response.data
				
			})
		},
		findClick(){
			
			
				this.curpage=1
			 
	            if (this.ssCondition.N) this.ssConditionString += 'N';
	            if (this.ssCondition.C) this.ssConditionString += 'C';
	            if (this.ssCondition.L) this.ssConditionString += 'L';
	            if (this.ssCondition.F) this.ssConditionString += 'F';
			
	         
	           if (this.volunteerCondition.A) this.vtConditionString+='A';
	            if (this.volunteerCondition.C) this.vtConditionString+='C';
	           
				
				 
				 if (this.weekCondition.weekday) this.weekString+='D';
				 if (this.weekCondition.weekend) this.weekString+='E';
			
			if(this.ssConditionString===''){
				alert('검색키워드조건을 하나이상 체크하세요')
				return;
			}
    		
			if(this.vtConditionString===''){
				alert('봉사자 유형을 선택하세요')
				return;
			}
			
			if(this.weekString===''){
				alert('요일을 선택해주세요')
				return;
			}
			
			this.initialState = {
				    Option:{...this.Option},
				    ssCondition: {...this.ssCondition},
				    volunteerCondition: {...this.volunteerCondition},
				    weekCondition: {...this.weekCondition},
				    ssConditionString:this.ssConditionString,
		            vtConditionString:this.vtConditionString,
		            weekString:this.weekString
				    
				};
		    
		   this.callList()
			
		   this.ssConditionString=''
		   this.vtConditionString=''
		   this.weekString=''
		   
		},
		callList(){
			axios.post("../program/find_vue.do", this.initialState.Option, {
			    params: {
			        ssConditionString: this.initialState.ssConditionString,
			        vtConditionString:this.initialState.vtConditionString,
			        weekString:this.initialState.weekString,
			        page:this.curpage
			    },
			    headers: {
			        'Content-Type': 'application/json;charset=UTF-8'
			    }
			}).then(response => {
			    this.programList=response.data
			   
			   
			}).catch(error => {
			   
			});
			
			this.paging()
		},
		paging(){
			axios.post("../program/listPage_vue.do", this.initialState.Option, {
			    params: {
			        ssConditionString: this.initialState.ssConditionString,
			        vtConditionString:this.initialState.vtConditionString,
			        weekString:this.initialState.weekString,
			        page:this.curpage
			    },
			    headers: {
			        'Content-Type': 'application/json;charset=UTF-8'
			    }
			}).then(response => {
			 this.totalpage=response.data.totalpage
			 this.start=response.data.start
			 this.end=response.data.end
			 this.curpage=response.data.curpage
			 this.size=response.data.size
			 
			
			
			}).catch(error => {
			   
			});
			
		},
		
		range(start,end){
			let arr=[]
			console.log(start)
			console.log(end)
			let size=end-start;
			for(let i=0;i<=size;i++){
				arr[i]=start;
				start++;
			}
			return arr;
		},
		next(){
			this.curpage=this.end+1
			this.callList()
		},
		prev(){
			this.curpage=this.start-1
			this.callList()
		},
		move(page){
			this.curpage=page
			this.callList()
		},
		firstpage(){
			this.curpage=1
			this.callList()
		},
		lastpage(){
			this.curpage=this.totalpage
			this.callList()
		},
		optionReset(){
			this.Option.state=''
			this.Option.city=''
			this.Option.major_option=''
			this.Option.minor_option=''
			this.Option.active_type=''
			this.Option.target_type=''
			this.Option.collect_state='모집중'
			this.Option.ss=''
			this.Option.v_start= new Date().toISOString().split('T')[0] // 오늘 날짜
			this.Option.v_end= new Date(new Date().setMonth(new Date().getMonth() + 8)).toISOString().split('T')[0]// 오늘 날짜로부터 1달 후
			this.Option.collect_start= new Date().toISOString().split('T')[0] // 오늘 날짜
			this.Option.collect_end= new Date(new Date().setMonth(new Date().getMonth() + 8)).toISOString().split('T')[0]// 오늘 날짜로부터 1달 후
			
			    
			this.ssCondition.N= true
			this.ssCondition.C= false
			this.ssCondition.L= false
			this.ssCondition.F= false
			
			this.volunteerCondition.A=true
			this.volunteerCondition.C=true
			
			this.weekCondition.weekday=true
			this.weekCondition.weekend=true
			
			this.stateChange()
			this.major_fieldChange()
		}

	}
}).mount('#optionSelect')
</script>
</body>
</html>