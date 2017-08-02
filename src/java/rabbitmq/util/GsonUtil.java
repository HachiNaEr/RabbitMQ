package rabbitmq.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GsonUtil {
	private static Gson gson = new Gson();
	
	public static String toJson(Object src){
		return gson.toJson(src);
	}
	 
	public static <T> T fromJson(String json, TypeToken<T> typeToken){
		return gson.fromJson(json, typeToken.getType());
	}
}
