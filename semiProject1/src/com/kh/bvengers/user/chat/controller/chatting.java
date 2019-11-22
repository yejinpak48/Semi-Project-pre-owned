package com.kh.bvengers.user.chat.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chatting")
public class chatting {

	private static Map<String, Session> client = Collections.synchronizedMap(new HashMap<String, Session>());
	private static Map<String, Session> admin = Collections.synchronizedMap(new HashMap<String, Session>());

	public chatting() {
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		int idx2 = message.indexOf("*");
		String mId = message.substring(0, idx2);
		int idx = message.indexOf(":");
		String id = message.substring(idx2+1, idx);
		String messages = message.substring(idx+1);
		synchronized (client) {
			try {
				Iterator<String> keySetIterator = client.keySet().iterator();
				while (keySetIterator.hasNext()) {
					String key = keySetIterator.next();
					if (key.equals(id)) {
						try {
							admin.get(key).getBasicRemote().sendText(mId+":"+messages);
						} catch (Exception e) {
							client.get(key).getBasicRemote().sendText("상담원과 연결되지 않았습니다");
						}
					} else {
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		synchronized (admin) {
			try {
				Iterator<String> keySetIterator = admin.keySet().iterator();
				while (keySetIterator.hasNext()) {
					String key = keySetIterator.next();
					if (key.equals(id)) {
						try {
							client.get(key).getBasicRemote().sendText(mId+":"+messages);
						} catch (Exception e) {
							admin.get(key).getBasicRemote().sendText("고객과 연결되지 않았습니다");
						}
					} else {

					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@OnOpen
	public void onOpen(Session session) {
		String params = session.getQueryString();
		String ids[] = params.split("=");
		String id = ids[1];
		int idx = id.indexOf("&");
		id = id.substring(0, idx);
		String sub = ids[2];
		if (sub.equals("admin")) {
			admin.put(id, session);
		} else {
			client.put(id, session);
		}
	}

	@OnClose
	public void onClose(Session session) {
		String params = session.getQueryString();
		String ids[] = params.split("=");
		String id = ids[1];
		int idx = id.indexOf("&");
		id = id.substring(0, idx);
		String sub = ids[2];
		if (sub.equals("admin")) {
			admin.remove(id, session);
		} else {
			client.remove(id, session);
		}
	}
}
