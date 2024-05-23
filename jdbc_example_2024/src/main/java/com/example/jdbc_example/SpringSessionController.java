package com.example.jdbc_example;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SpringSessionController {

    private final InMemorySessionManager sessionManager = new InMemorySessionManager();

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String userId, @RequestParam String userName) {
        Session session = sessionManager.createSession(userId, userName);
        // You might want to return the session ID or a JWT token here
        return ResponseEntity.ok("Session created successfully" + session.getSessionId());
    }

    @GetMapping("/protected")
    public ResponseEntity<String> getProtectedData(@RequestParam("sessionId") String sessionId) {
        Session session = sessionManager.getSession(sessionId);
        if (session != null) {
            return ResponseEntity.ok("Hello, " + session.getUserName() + "!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid session ID");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("sessionId") String sessionId) {
        sessionManager.invalidateSession(sessionId);
        return ResponseEntity.ok("Successfully logged out");
    }

    @GetMapping("/session")
    public String home(Model model, HttpSession session) {
        @SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

        if (messages == null) {
            messages = new ArrayList<>();
        }
        model.addAttribute("sessionMessages", messages);
        messages.stream().forEach(x-> System.out.println(x));
        System.out.println("MESSAGES");
        return "index";
    }

    @PostMapping("/persistMessage")
    public String persistMessage(@RequestParam("msg") String msg, HttpServletRequest request) {
        @SuppressWarnings("unchecked")
        List<String> msgs = (List<String>) request.getSession().getAttribute("MY_SESSION_MESSAGES");
        if (msgs == null) {
            msgs = new ArrayList<>();
            request.getSession().setAttribute("MY_SESSION_MESSAGES", msgs);
        }
        msgs.add(msg);
        request.getSession().setAttribute("MY_SESSION_MESSAGES", msgs);
        return "redirect:/";
    }

    @PostMapping("/destroy")
    public String destroySession(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }

}
