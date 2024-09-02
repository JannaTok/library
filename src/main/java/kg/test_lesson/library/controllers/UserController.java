package kg.test_lesson.library.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kg.test_lesson.library.entities.Users;
import kg.test_lesson.library.services.UserService;
import kg.test_lesson.library.services.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            Model model,
            HttpServletRequest request,
            HttpServletResponse response) {

        UsernamePasswordAuthenticationToken authRequest =
                new UsernamePasswordAuthenticationToken(username, password);

        try {
            Authentication authentication = authenticationManager.authenticate(authRequest);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            HttpSession session = request.getSession();
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

            String token = jwtTokenProvider.createToken(username, List.of("ROLE_ADMIN"));
            response.setHeader("Authorization", "Bearer " + token);

            return "redirect:/home";
        } catch (AuthenticationException e) {
            model.addAttribute("error", "Invalid username or password.");
            System.out.println("Authentication failed: " + e.getMessage());
            return "login";
        }
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> login(
//            @RequestParam String username,
//            @RequestParam String password,
//            HttpServletRequest request,
//            HttpServletResponse response) {
//
//        UsernamePasswordAuthenticationToken authRequest =
//                new UsernamePasswordAuthenticationToken(username, password);
//
//        try {
//            Authentication authentication = authenticationManager.authenticate(authRequest);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            HttpSession session = request.getSession();
//            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
//
//            String token = jwtTokenProvider.createToken(username, List.of("ROLE_ADMIN"));
//            response.setHeader("Authorization", "Bearer " + token);
//
//            return ResponseEntity.ok(Collections.singletonMap("token", token));
//        } catch (AuthenticationException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                    .body(Collections.singletonMap("error", "Invalid username or password."));
//        }
//    }

    @GetMapping("/home")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String index(Model model, Principal principal) {

        Users user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "home";
    }
}
