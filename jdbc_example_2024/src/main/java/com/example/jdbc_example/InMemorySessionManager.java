package com.example.jdbc_example;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

//@Service
public class InMemorySessionManager {
    private final Map<String, Session> sessions = new ConcurrentHashMap<>();

    public Session createSession(String userId, String userName) {
        Session session = new Session(userId, userName, System.currentTimeMillis());
        String sessionId = generateSessionId();
        session.setSessionId(sessionId);
        sessions.put(sessionId, session);
        return session;
    }

    public Session getSession(String sessionId) {
        return sessions.get(sessionId);
    }

    public void invalidateSession(String sessionId) {
        sessions.remove(sessionId);
    }

    private String generateSessionId() {
        // Implement a secure random string generation logic for production
        return UUID.randomUUID().toString();
    }
}
