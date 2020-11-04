package com.backend.listener;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;

public class OnlineSessionBindingListener implements HttpSessionBindingListener {

  // 保存一個ServletContext, 將用它的日誌log()方法
  ServletContext context;
  EmpVO loginEmp;

  public OnlineSessionBindingListener(ServletContext context, EmpVO loginEmp) {
    this.context = context;
    this.loginEmp = loginEmp;
  }
  
  @Override
  public void valueBound(HttpSessionBindingEvent event) {
	System.out.println("valueBound()方法-自動啟動");
	EmpService es = new EmpService();
	es.updateEmpOnline(loginEmp, "Y");
    context.log("[" + new Date() + "-日誌記錄-繫結session] BOUND as " + event.getName() + " to " + event.getSession().getId());
  }
  
  @Override
  public void valueUnbound(HttpSessionBindingEvent event) {
	System.out.println("valueUnbound()方法-自動啟動");
	EmpService es = new EmpService();
	es.updateEmpOnline(loginEmp, "N");
    context.log("[" + new Date() + "-日誌記錄-脫離session] UNBOUND as " + event.getName() + " from " + event.getSession().getId());
  }
}