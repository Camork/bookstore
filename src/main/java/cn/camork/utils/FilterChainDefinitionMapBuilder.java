package cn.camork.utils;

import java.util.LinkedHashMap;

public class FilterChainDefinitionMapBuilder {

	public LinkedHashMap<String, String> buildFilterChainDefinitionMap(){
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		
		map.put("/login.jsp", "anon");
        map.put("/index", "anon");
        map.put("/logout", "logout");
        map.put("/order/getMyOrders", "authc");
        map.put("/admin/adminCenter", "authc");

		return map;
	}
	
}
