package sg.edu.nus.adproject.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;



@Entity
@Table(name = "daily_view")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Integer loginCount;

    @Column(nullable = false)
    private Integer searchCount;

    // Getters and Setters
}
