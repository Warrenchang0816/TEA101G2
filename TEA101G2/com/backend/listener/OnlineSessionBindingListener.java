package com.backend.listener;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class OnlineSessionBindingListener implements HttpSessionBindingListener {

  // 保存一個ServletContext, 將用它的日誌log()方法
  ServletContext context;

  public OnlineSessionBindingListener(ServletContext context) {
    this.context = context;
  }
  
  public void valueBound(HttpSessionBindingEvent event) {
	System.out.println("valueBound()方法-自動啟動");
    context.log("[" + new Date() + "-日誌記錄-繫結session] BOUND as " + event.getName() + " to " + event.getSession().getId());
  }
  
  public void valueUnbound(HttpSessionBindingEvent event) {
	System.out.println("valueUnbound()方法-自動啟動");
    context.log("[" + new Date() + "-日誌記錄-脫離session] UNBOUND as " + event.getName() + " from " + event.getSession().getId());
  }
}