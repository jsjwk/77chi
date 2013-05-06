package com.bb.neighbor.renren.home;

import org.json.simple.JSONArray;
import org.junit.Test;

import com.renren.api.client.RenrenApiClient;
import com.renren.api.client.param.impl.SessionKey;

public class TestRenrenAPIHome {
    @Test
    public void testGetUserInfo() {
        RenrenApiClient apiClient = RenrenApiClient.getInstance();
        
        String sessionKey = "2.72c94e8fdd9b3aabcc59f8dfdfe7d73d.3600.1341910800-105622312";
        String renrenUserId = "105622312";
        String fields = "uid,name,sex,star,zidou,vip,birthday,tinyurl,headurl,mainurl,hometown_location,work_history,university_history";
        
        JSONArray userInfo = apiClient.getUserService().getInfo(renrenUserId, fields, new SessionKey(sessionKey));
        
        System.out.println(userInfo);
    }
    
    @Test
    public void testGetFriends() {
        RenrenApiClient apiClient = RenrenApiClient.getInstance();
        
        String sessionKey = "2.72c94e8fdd9b3aabcc59f8dfdfe7d73d.3600.1341910800-105622312";
//        String renrenUserId = "105622312";
//        String fields = "uid,name,sex,star,zidou,vip,birthday,tinyurl,headurl,mainurl,hometown_location,work_history,university_history";
        
        JSONArray userInfo = apiClient.getFriendsService().getFriends(1, 500, new SessionKey(sessionKey));
        
        System.out.println(userInfo);
    }
}
