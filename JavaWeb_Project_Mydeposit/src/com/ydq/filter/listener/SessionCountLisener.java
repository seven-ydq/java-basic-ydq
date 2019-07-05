package com.ydq.filter.listener;
/**
 * 监听器：用来统计网站访问人数
 */

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
@WebListener
public class SessionCountLisener implements HttpSessionListener {
    private static int count = 0;

    public static int getSessionCount() {//计算session的和
        return count;
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        count++;
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        count--;

    }
}
