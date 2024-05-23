package com.example.jdbc_example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    private String userId;
    private String userName;
    private long createdAt;
    private String sessionId;

    public Session(String userId, String userName, long currentTimeMillis) {
        this.userId= userId;
        this.userName = userName;
        this.createdAt = currentTimeMillis;
    }
}

