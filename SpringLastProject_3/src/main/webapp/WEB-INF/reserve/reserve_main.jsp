<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<style type="text/css">
#reserveApp {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
 .rounded {
      -moz-border-radius:20px 20px 20px 20px; 
      border-radius:20px 20px 20px 20px;
      border:solid 1px #ffffff;
      background-color:#2b6bd1;
      padding:10px;
      color:#ffffff;
    }
  td.link:hover{
    cursor: pointer;
  }
 
</style>
</head>
<body>
<div class="wrapper row3" id="reserveApp">
  <main class="container clear"> 
   <h2 class="sectiontitle">맛집 예약</h2>
   <table class="table">
     <tr>
       <td class="text-center" rowspan="4" width=40%>
         <table class="table">
           <caption><h3 class="text-center">맛집 정보</h3></caption>
           <tr>
              <td colspan="2" class="text-center inline">
                 <input type="button" class="btn-xs btn-success" value="한식" @click="foodSelect('한식')">&nbsp;
                 <input type="button" class="btn-xs btn-info" value="양식" @click="foodSelect('양식')">&nbsp;
                 <input type="button" class="btn-xs btn-warning" value="중식" @click="foodSelect('중식')">&nbsp;
                 <input type="button" class="btn-xs btn-danger" value="일식" @click="foodSelect('일식')">
              </td>
           </tr>
           
         </table>
         <div style="overflow-y:auto;height:700px; ">
         <table class="table">
         	<tr v-for="vo in food_list" style="cursor:pointer;" @click="foodNumber(vo.fno)">
         		<td><img :src="'http://www.menupan.com'+vo.poster"  style="width:30px;height:30px;"></td>
         		<td>{{vo.name}}</td>
         	</tr>
         </table>
         </div>
       </td>
       <td class="text-center" width="60%">
         <table class="table" style="width:450px;">
           <caption><h3 class="text-center">예약일 정보</h3></caption>
           <tr>
             <td>
                <div class="calendar">
                  <h2>
                    <a style="cursor:pointer;" v-on:click="onClickPrev(currentMonth)">◀</a>
                    {{currentYear}}년 {{currentMonth}}월
                    <a style="cursor:pointer;" v-on:click="onClickNext(currentMonth)">▶</a>
                  </h2>
                  <table class="table table-hover">
                      <thead>
                        <tr>
                          <td v-for="(weekName, index) in weekNames" v-bind:key="index">
                            {{weekName}}
                          </td>
                        </tr>
                      </thead>
                      <tbody>
                        <tr v-for="(row, index) in currentCalendarMatrix" :key="index">
                          <td style= "padding:8px;" v-for="(day, index2) in row" :key="index2" style="padding:20px;" :class="(day>=realDay &&  sysMonth===currentMonth && sysYear===currentYear) || (sysMonth<currentMonth && sysYear===currentYear)?'link':''">
                            <span v-if="(day>=realDay &&  sysMonth===currentMonth && sysYear===currentYear) || (sysMonth<currentMonth && sysYear===currentYear)" @click="change(day) " style="color:black; font-weight:bold; ">
                               <span v-if="day===currentDay &&  sysMonth<= currentMonth" class="rounded" >
                                 {{day}}
                               </span>
                               
                              
                               <span v-else >
                                 {{day}}
                               </span>
                            </span>
                            <span v-else style="color:gray; ">
                               {{day}}
                            </span>
                          </td>
                        </tr>
                      </tbody>
                  </table>    
              </div>
             </td>
           </tr>
         </table>
       </td>
     </tr>
     <tr>
      <td class="text-center" width=60%>
        <table class="table">
          <caption><h3 class="text-center">시간 정보</h3></caption>
          <tr v-show="tShow">
            <td class="text-center">
            <span class="btn btn-xs btn-success" v-for="time in time_list" style="margin-left:2px;" @click="timeSelect(time)">{{time}}</span>
            </td>
          </tr>
        </table>
      </td>
     </tr>
     <tr>
      <td class="text-center" width=60%>
        <table class="table">
          <caption><h3 class="text-center">인원 정보</h3></caption>
          <tr v-show=iShow>
            <td class="text-center">
            	<span class="btn btn-warning btn-xs" v-for="inwon in inwon_list" style="margin-left:2px;" @click="inwonSelect(inwon)">{{inwon}}</span>
            </td>
          </tr>
        </table>
      </td>
     </tr>
     
     <tr>
     	<td class="text-center" width="60%" v-show=rShow>
     		<input type="button" class="btn-lg btn-primary" value="예약하기" click="reserveOk()">
     	</td>
     </tr>
   </table>
  </main>
</div>
<script>
let rApp=Vue.createApp({
   data(){
      return {
         weekNames: ["월요일", "화요일", "수요일","목요일", "금요일", "토요일", "일요일"],
            rootYear: 1904,
            rootDayOfWeekIndex: 4, // 2000년 1월 1일은 토요일
            currentYear: new Date().getFullYear(),
            currentMonth: new Date().getMonth()+1,
            sysMonth:new Date().getMonth()+1,
            sysYear:new Date().getFullYear(),
            currentDay: new Date().getDate(),
            currentMonthStartWeekIndex: null,
            currentCalendarMatrix: [],
            endOfDay: null,
            memoDatas: [],
            realDay:new Date().getDate(),
            type:'한식',
            food_list:[],
            fno:0,
            time_list:['12:00','13:00','14:00','17:00','18:00','19:00','20:00','21:00'],
     		tShow:false,
     		time:'',
     		inwon_list:['2명','3명','4명','5명','6명','7명','8명','9명','10명','단체'],
     		iShow:false,
     		inwon:'',
     		rShow:false
      }
   },
   mounted(){
      this.init()
      this.dataSend()
   },
   methods:{
	   reserveOk(){
		 axios.post('../reserve_ok.do',null,{
			 params:{
				 fno:this.fno,
				 Date:this.currentYear+"년도"+this.currentMonth+"월"+this.currentDay+"일",
				 Time:this.time,
				 inwon:this.inwon
			 }
		 })  
	   },
	   inwonSelect(inwon){
		   this.inwon=inwon
		   this.rShow=true
	   },
	   timeSelect(time){
		   this.time=time;
		 this.iShow=true;  
	   },
        init(){
           this.currentMonthStartWeekIndex = this.getStartWeek(this.currentYear, this.currentMonth);
           this.endOfDay = this.getEndOfDay(this.currentYear, this.currentMonth);
           this.initCalendar();
         },
         initCalendar(){
           this.currentCalendarMatrix = [];
           let day=1;
           for(let i=0; i<6; i++){
             let calendarRow = [];
             for(let j=0; j<7; j++){
               if(i==0 && j<this.currentMonthStartWeekIndex){
                 calendarRow.push("");
               }
               else if(day<=this.endOfDay){
                 calendarRow.push(day);
                 day++;
               }
               else{
                 calendarRow.push("");
               }
             }
             this.currentCalendarMatrix.push(calendarRow);
           }
         },
         getEndOfDay(year, month){
             switch(month){
                 case 1:
                 case 3:
                 case 5:
                 case 7:
                 case 8:
                 case 10:
                 case 12:
                   return 31;
                   break;
                 case 4:
                 case 6:
                 case 9:
                 case 11:
                   return 30;
                   break;
                 case 2:
                   if( (year%4 == 0) && (year%100 != 0) || (year%400 == 0) ){
                   return 29;   
                   }
                   else{
                       return 28;
                   }
                   break;
                 default:
                   console.log("unknown month " + month);
                   return 0;
                   break;
             }
         },
         getStartWeek(targetYear, targetMonth){
           let year = this.rootYear;
           let month = 1;
           let sumOfDay = this.rootDayOfWeekIndex;
           while(true){
             if(targetYear > year){
               for(let i=0; i<12; i++){
                 sumOfDay += this.getEndOfDay(year, month+i);
               }
               year++;
             }
             else if(targetYear == year){
               if(targetMonth > month){
                 sumOfDay += this.getEndOfDay(year, month);
                 month++;
               }
               else if(targetMonth == month){
                 return (sumOfDay)%7;
               }
             }
           }
         },
         onClickPrev(month){
           month--;
           if(month<=0){
             this.currentMonth = 12;
             this.currentYear -= 1;
           }
           else{
             this.currentMonth -= 1;
           }
           this.init();
         },
         onClickNext(month){
        	 this.currentDay=1;
           month++;
           if(month>12){
             this.currentMonth = 1;
             this.currentYear += 1;
           }
           else{
             this.currentMonth += 1;
           }
           this.init();
         },
         isToday: function(year, month, day){
           let date = new Date();
           return year == date.getFullYear() && month == date.getMonth()+1 && day == day; 
         },
         change(day){
           this.currentDay=day;
           this.tShow=true;
           //this.isToday(this.currentYear,.this.currentMonth,this.currentDay)
         },
         // 맛집
         dataSend(){
          axios.get("../reserve/reserve_food_list_vue.do",{
             params:{
                type:this.type
             }
          }).then(res=>{
             console.log(res.data)
             this.food_list=res.data
          })  
         },
         foodSelect(type){
            this.type=type
            this.dataSend()
         },
         foodNumber(fno){
        	 this.fno=fno;
         }
         // 시간
         // 인원
         // 데이터 전송 (예약처리) => 화면변경 (Mypage)
     }
}).mount("#reserveApp")
</script>
</body>
</html>





