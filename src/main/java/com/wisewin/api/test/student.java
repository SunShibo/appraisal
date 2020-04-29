package com.wisewin.api.test;

import com.wisewin.api.util.DateUtils;

import java.util.Date;

public class student {
   public static void main(String[]args){
       Integer result=DateUtils.getRemainSecondsOneDay(new Date());
       System.out.println(result);
   }
}
