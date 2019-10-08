package tools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ErrorJSON {
	public static JSONObject serviceRefused(String message, int codeErreur ){
		try{
		JSONObject obj = new JSONObject();
		
		obj.put("code", codeErreur);
		obj.put("message", message);		
	
		return obj;
		}catch (JSONException e ){
			System.out.println("Erreur" + e.getMessage());
		}
		return null;
	}
	
	
	public static JSONObject buildJSON(String cle, String valeur ){
		//try{
		JSONObject obj = new JSONObject();
		
		try {
			obj.put(cle, valeur);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return obj;
	}
	
	public static JSONObject serviceAccepted(String Code) throws JSONException{
		try{
			JSONObject obj = new JSONObject();
			obj.put("Ok", Code);
			return obj;
		}catch (JSONException e ){
			System.out.println("Erreur" + e.getMessage());
		}
		return null;
	}
	public static JSONObject serviceA(JSONArray Code) throws JSONException{
		try{
			JSONObject obj = new JSONObject();
			obj.put("ok" ,Code);
			return obj;
		}catch (JSONException e ){
			System.out.println("Erreur" + e.getMessage());
		}
		return null;
	}
	
}
