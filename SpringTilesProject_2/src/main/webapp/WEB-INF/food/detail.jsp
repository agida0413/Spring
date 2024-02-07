<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container" id="detailApp">
		
		<div class="row">
			<table class="table">
				<tr>
					<td width=30% class="text-center" rowspan="9">
						<img :src="'https://www.menupan.com/'+detail_data.poster" style="width:100%">
					</td>
					<td colspan="2">
					<h3>{{detail_data.name}}&nbsp;<span style="color:orange;">{{detail_data.score}}</span></h3>
					</td>
				</tr>
				<tr>
					<td width="10%">주소</td>
					<td wudth="60%">{{detail_data.address}}</td>
					
				</tr>
				
					<tr>
					<td width="10%">전화</td>
					<td wudth="60%">{{detail_data.phone}}</td>
					
				</tr>
				
					<tr>
					<td width="10%">음식종류</td>
					<td wudth="60%">{{detail_data.type}}</td>
					
				</tr>
				
					<tr>
					<td width="10%">가격대</td>
					<td wudth="60%">{{detail_data.price}}</td>
					
				</tr>
				
					<tr>
					<td width="10%">영업시간</td>
					<td wudth="60%">{{detail_data.time}}</td>
					
				</tr>
				
					<tr>
					<td width="10%">좌석</td>
					<td wudth="60%">{{detail_data.seat}}</td>
					
				</tr>
				
					<tr>
					<td width="10%">테마</td>
					<td wudth="60%">{{detail_data.theme}}</td>
					
				</tr>
				
				<tr>
					<td colspan="3" class="text-center">
						<a href="javascript:history.back()" class="btn btn-sm btn-danger">목록</a>
					</td>
				</tr>
			</table>
		</div>
	</div>
	
		
		<%--component --%>
	<div class="container" id="replyApp">
      <div class="row">
         <table class="table">
            <tr>
               <td>
                  <table class="table" v-for="vo in replyList">
                     <tr>
                        <td class="text-left">{{vo.name}}({{vo.dbday}})</td>
                        <td class="text-right">
                        	<span v-if="vo.id===sessionId">
                        		<input type="button" value="수정" class="btn-xs btn-success" @click="updateShow(vo.rno)" :id="'up'+vo.rno">
                        		<input type="button" value="삭제" class="btn-xs btn-info" @click="replyDelete(vo.rno)">
                        	</span>
                        </td>
                     </tr>
                     <tr>
                        <td colspan="2">
                           <pre style="white-space: pre-wrap; background-color: white; border: none">{{vo.msg}}</pre>
                        </td>
                     </tr>
                     
                      <tr v-if="isShow===true && rno===vo.rno">
		               <td colspan="2">
		                  <textarea rows="5" cols="96" style="float:left" ref="umsg" :id="'msg'+vo.rno">{{vo.msg}}</textarea>
		                  <input type="button" value="댓글수정" style="float:left;height: 104px;" class="btn-danger btn-sm" @click="replyUpdate(vo.rno)">
		               </td>
		            </tr>
                  </table>
               </td>
            </tr>
         </table>
         <table class="table" v-show="sessionId!==''">
            <tr>
               <td class="text-left">
                  <textarea rows="5" cols="96" style="float:left" v-model="msg" ref="msg"></textarea>
                  <input type="button" value="댓글쓰기" style="float:left;height: 104px;" class="btn-danger btn-sm" @click="replyInsert()">
               </td>
            </tr>
         </table>
      </div>
   </div>
</body>
<script>
let detailApp=Vue.createApp({
	data(){
		return{
			fno:${fno},
			detail_data:{}
		}	
	},
	mounted(){
		axios.get('../food/detail_vue.do',{
			params:{
				fno:this.fno
			}
		})
		.then(res=>{
			this.detail_data=res.data
		})
	}
}).mount('#detailApp')

let replyApp=Vue.createApp({
	data(){
		return{
			fno:${fno},
			sessionId:'${id}',
			replyList:[],
			rno:0,
			msg:'',
			umsg:'',
			isShow:false,
			bCheck:true
		}	
	},
	mounted(){
		axios.get('../reply/list_vue.do',{
			params:{
				fno:this.fno
			}
		})
		.then(res=>{
			this.replyList=res.data
		})
	},
	methods:{
		//수정
		//삭제
		//추가
		replyInsert(){
			if(this.msg.trim()===''){
					$refs.msg.focus();
					alert('내용입력')
					return;
			}
			axios.get('../reply/insert_vue.do',{
				params:{
					fno:this.fno,
					msg:this.msg
				}
			}).then(res=>{
				this.replyList=res.data
				this.msg=''
			})
		},
		replyDelete(rno){
			axios.get('../reply/delete_vue.do',{
				params:{
					rno:rno,
					fno:this.fno
				}
			})
			.then(res=>{
				this.replyList=res.data
			})
		},
		updateShow(rno){
				if(this.bCheck===true){
				
					this.isShow=true;
					this.rno=rno
					this.bCheck=false;
					
					
					$('#up'+rno).val('취소')
				}else{
					this.isShow=false;
					
					this.bCheck=true;
					$('#up'+rno).val('수정')
				}
				
			
			
				
		},
		replyUpdate(rno){
			let msg=$('#msg'+rno).val();
			axios.get('../reply/update_vue.do',{
				params:{
					rno:rno,
					fno:this.fno,
					msg:msg
				}
			})
			.then(res=>{
				this.replyList=res.data
				this.isShow=false;
				$('#up'+rno).val('수정')
			})
		}
	}
}).mount('#replyApp')
</script>
</html>