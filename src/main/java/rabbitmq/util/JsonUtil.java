package rabbitmq.util;

import com.google.gson.Gson;

import java.util.HashMap;

public class JsonUtil {
	private int retCode;
	private String retMsg;
	private Object object;

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

	public JsonUtil(int retCode, String retMsg, HashMap<String, Object> result) {
		super();
		this.retCode = retCode;
		this.retMsg = retMsg;
	}

	public JsonUtil() {}

	public static String switchToJsonObj(int rc, String rm, Object rs) {
		JsonUtil jmodel = new JsonUtil();
		jmodel.setRetCode(rc);
		jmodel.setRetMsg(rm);
		jmodel.setObject(rs);
		return new Gson().toJson(jmodel);
	}
}
