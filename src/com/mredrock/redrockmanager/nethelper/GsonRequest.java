package com.mredrock.redrockmanager.nethelper;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

public class GsonRequest<T> extends Request<T> {
	private final Gson gson=new Gson();
	private final Class<T> clazz;
	private final Listener<T> listener;
	private final Map<String,String> headers;
	
	public GsonRequest(int method, String url,Class<T> clazz,Map<String,String> headers,Listener listener, ErrorListener errorlistener) {
		super(method, url, errorlistener);
		this.clazz=clazz;
		this.listener=listener;
		this.headers=headers;
		
	}

	@Override
	protected void deliverResponse(T arg0) {
		listener.onResponse(arg0);
		
	}

	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		// TODO Auto-generated method stub
		return headers!=null?headers:super.getHeaders();
	}

	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse arg0) {
		try {
			String json=new String(arg0.data,HttpHeaderParser.parseCharset(arg0.headers));
			return Response.success(gson.fromJson(json, clazz), HttpHeaderParser.parseCacheHeaders(arg0));
		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
		}
		

	}

}
