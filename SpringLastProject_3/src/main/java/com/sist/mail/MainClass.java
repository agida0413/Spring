package com.sist.mail;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
/*
 *    private String style="<style>" + 
    		"body {" + 
    		"	  padding:1.5em;" + 
    		"	  background: #f5f5f5" + 
    		"	}" + 
    		"	table {" + 
    		"	  border: 1px #a39485 solid;" + 
    		"	  font-size: .9em;" + 
    		"	  box-shadow: 0 2px 5px rgba(0,0,0,.25);" + 
    		"	  width: 100%;" + 
    		"	  border-collapse: collapse;" + 
    		"	  border-radius: 5px;" + 
    		"	  overflow: hidden;" + 
    		"	}" + 
    		"	th {" + 
    		"	  text-align: left;" + 
    		"	}" + 
    		"	thead {" + 
    		"	  font-weight: bold;" + 
    		"	  color: #fff;" + 
    		"	  background: #73685d;" + 
    		"	}" + 
    		"	 td, th {" + 
    		"	  padding: 1em .5em;" + 
    		"	  vertical-align: middle;" + 
    		"	}" + 
    		"	 td {" + 
    		"	  border-bottom: 1px solid rgba(0,0,0,.1);" + 
    		"	  background: #fff;" + 
    		"	}" + 
    		"	a {" + 
    		"	  color: #73685d;" + 
    		"	}" + 
    		"	 @media all and (max-width: 768px) {" + 
    		"	  table, thead, tbody, th, td, tr {" + 
    		"	    display: block;" + 
    		"	  }" + 
    		"	  th {" + 
    		"	    text-align: right;" + 
    		"	  }" + 
    		"	  table {" + 
    		"	    position: relative; " + 
    		"	    padding-bottom: 0;" + 
    		"	    border: none;" + 
    		"	    box-shadow: 0 0 10px rgba(0,0,0,.2);" + 
    		"	  }" + 
    		"	  thead {" + 
    		"	    float: left;" + 
    		"	    white-space: nowrap;" + 
    		"	  }" + 
    		"	  tbody {" + 
    		"	    overflow-x: auto;" + 
    		"	    overflow-y: hidden;" + 
    		"	    position: relative;" + 
    		"	    white-space: nowrap;" + 
    		"	  }" + 
    		"	  tr {" + 
    		"	    display: inline-block;" + 
    		"	    vertical-align: top;" + 
    		"	  }" + 
    		"	  th {" + 
    		"	    border-bottom: 1px solid #a39485;" + 
    		"	  }" + 
    		"	  td {" + 
    		"	    border-bottom: 1px solid #e5e5e5;" + 
    		"	  }" + 
    		"	  }" + 
    		"</style>";
 */
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String temp="";
	
		try
		{ 
           FileReader fr=new FileReader("C:\\SpringDev\\mail.txt");
            System.out.println("실행");
            int i=0;
            while((i=fr.read())!=-1)
            { 
            	temp+=String.valueOf((char)i);
            }
         
            System.out.println(temp);
		}catch(Exception ex) {}
        String host="smtp.naver.com";
        String user="agida0413@naver.com";
        String password=temp;
        System.out.println(password);
        System.out.println(user);
        Properties props=new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.auth", "true");
        
        Session session=Session.getDefaultInstance(props,new javax.mail.Authenticator() {
        	  protected PasswordAuthentication getPasswordAuthentication() {
        		  return new PasswordAuthentication(user, password);
        	  }
        });
        try
        {
        	MimeMessage message=new MimeMessage(session);
        	message.setFrom(new InternetAddress(user));
        	
        	message.setContent("안녕하세요","text/html;charset=UTF-8");
        	message.addRecipient(Message.RecipientType.TO, 
        			new InternetAddress("agida0413@naver.com"));
        	message.setSubject("회원을 축하합니다");
        	Transport.send(message);
        	System.out.println("메일 전송 완료!!");
        }catch(Exception ex) {ex.printStackTrace();}
	}

}