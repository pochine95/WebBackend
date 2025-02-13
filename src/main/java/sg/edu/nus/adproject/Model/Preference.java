package sg.edu.nus.adproject.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "preference")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Preference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String dietPreference;
    private String cuisinesPreference;

    @Enumerated(EnumType.STRING)
    private PricePreference pricePreference;

    public enum PricePreference {
        LOW, MEDIUM, HIGH
    }

    // Getters and Setters
}
