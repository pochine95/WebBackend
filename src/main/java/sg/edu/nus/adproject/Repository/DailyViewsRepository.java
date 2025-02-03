package sg.edu.nus.adproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sg.edu.nus.adproject.Model.DailyViews;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface DailyViewsRepository extends JpaRepository<DailyViews, Integer> {
    Optional<DailyViews> findByDate(LocalDate date);
}
