package sg.edu.nus.adproject.Model;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Apply to all endpoints (or specify "/administrator/**")
                .allowedOrigins("http://localhost:3000") // Allow calls from your React app
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // List the allowed HTTP methods
                .allowedHeaders("*")    // Headers you allow the client to send
                .exposedHeaders("Authorization") // Headers that the client can read from the response
                .allowCredentials(true); // If your app needs cookies or authentication tokens
    }
}
