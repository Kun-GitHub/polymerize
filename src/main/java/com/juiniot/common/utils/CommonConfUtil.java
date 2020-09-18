package com.juiniot.common.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 读取xml配置公共类
 */
public final class CommonConfUtil {

	private Map<String, Map<String, String>> values = new HashMap<String, Map<String, String>>();
	private Map<String, String> global_param = new HashMap<String, String>();

	private static final String NODE_GLOBAL = "global";
	private static final String NODE_SERVIE = "servie";
	private static final String NODE_PARAM = "param";
	private static final String ATTR_KEY = "name";

	private static final String CONFIG_PATH = "/common.conf.xml";
	private static CommonConfUtil instance;

	private CommonConfUtil() {
		this.parse();
	}

	public static CommonConfUtil getInstance() {
		if (instance == null) {
			synchronized (CommonConfUtil.class) {
				if (instance == null) {
					instance = new CommonConfUtil();
				}
			}
		}
		return instance;
	}

	public void reload() {
		this.parse();
	}

	public String getParam(String serviceName, String paramName) {		
		return getParams(serviceName).get(paramName);
	}

	public Map<String, String> getParams(String serviceName) {		
		return this.values.get(serviceName);
	}

	public String getGlobalParams(String name) {
		return this.global_param.get(name);
	}

	private void parse() {
		SAXReader reader = new SAXReader();
		InputStream in = null;
		try {
			in = CommonConfUtil.class.getResourceAsStream(CONFIG_PATH);
			Document document = reader.read(in);

			Element root = document.getRootElement();
			for (Iterator<?> globalIt = root.elementIterator(NODE_GLOBAL); globalIt.hasNext();) {
				Element global = (Element) globalIt.next();
				global_param.put(trim(global.attribute(ATTR_KEY).getValue()), trim(global.getText()));
			}

			for (Iterator<?> servieIt = root.elementIterator(NODE_SERVIE); servieIt.hasNext();) {
				Element service = (Element) servieIt.next();
				String serviceName = trim(service.attribute(ATTR_KEY).getValue());
				Map<String, String> params = new HashMap<String, String>();
				for (Iterator<?> paramIt = service.elementIterator(NODE_PARAM); paramIt.hasNext();) {
					Element param = (Element) paramIt.next();
					params.put(trim(param.attribute(ATTR_KEY).getValue()), trim(param.getText()));
				}
				values.put(serviceName, params);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} finally{
			if(in!= null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}


	private static final String trim(String src) {
		return src != null ? src.trim() : src;
	}

	public static void main(String[] args) throws UnknownHostException {
		System.out.println(CommonConfUtil.getInstance().getGlobalParams("test"));
		System.out.println(CommonConfUtil.getInstance().getParam("email", "email1"));
		System.out.println(CommonConfUtil.getInstance().getParam("email", "email2"));
	}

}
