package com.kh.bvengers.user.member.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/broadcasting")
public class Broadsocket {

	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
	private static Map<String, Session> client = Collections.synchronizedMap(new HashMap<String, Session>());

	@OnMessage
	public void onMessage(String message, Session session) {
		synchronized (clients) {
			for (Session client : clients) {
				if (!client.equals(session)) {
					try {
						client.getBasicRemote().sendText(message);

					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	@OnOpen
	public void onOpen(Session session) {
		clients.add(session);
	}

	@OnClose
	public void onClose(Session session) {
		clients.remove(session);
	}
}
