package sg.edu.nus.adproject.Model;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "dailyviews")
public class DailyViews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private LocalDate date;

    private int searchCount = 0;
    private int loginCount = 0;

    public DailyViews() {}

    public DailyViews(LocalDate date) {
        this.date = date;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public int getSearchCount() { return searchCount; }
    public void setSearchCount(int searchCount) { this.searchCount = searchCount; }

    public int getLoginCount() { return loginCount; }
    public void setLoginCount(int loginCount) { this.loginCount = loginCount; }

}
