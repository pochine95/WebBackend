package sg.edu.nus.adproject.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import sg.edu.nus.adproject.Model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}