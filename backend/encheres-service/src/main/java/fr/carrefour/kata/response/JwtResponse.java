package fr.carrefour.kata.response;

public class JwtResponse {
    public String token;
    public String type = "Bearer";
    public JwtResponse(String token) { this.token = token; }
}
