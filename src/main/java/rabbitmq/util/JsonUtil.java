package rabbitmq.util;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;


public class JsonUtil {
	private int retCode;
	private String retMsg;
	private Object object;
	private Map<String, Object> result;

	public void setRetCode(int retCode) {
		this.retCode = retCode;
	}

	public int getRetCode() {
		return retCode;
	}
	
	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public Object getObject() {
		return object;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public JsonUtil(int retCode, String retMsg, HashMap<String, Object> result) {
		super();
		this.retCode = retCode;
		this.retMsg = retMsg;
	}

	public JsonUtil() {}

	public static String switchToJson(int rc, String rm, Map<String, Object> rs) {
		JsonUtil jmodel = new JsonUtil();
		jmodel.setRetCode(rc);
		jmodel.setRetMsg(rm);
		jmodel.setResult(rs);
		return new Gson().toJson(jmodel);
	}

	public static String switchToJsonObj(int rc, String rm, Object rs) {
		JsonUtil jmodel = new JsonUtil();
		jmodel.setRetCode(rc);
		jmodel.setRetMsg(rm);
		jmodel.setObject(rs);
		return new Gson().toJson(jmodel);
	}
	
	/**
	 * 仅仅返回 retCode, retMsg
	 * @return {retCode:1, retMsg:成功/失败}
	 */
	public static String switchToJsonNull(int rc, String rm){
		JsonUtil jmodel = new JsonUtil();
		jmodel.setRetCode(rc);
		jmodel.setRetMsg(rm);
		return new Gson().toJson(jmodel);
	}
}
