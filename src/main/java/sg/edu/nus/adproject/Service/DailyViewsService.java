package sg.edu.nus.adproject.Service;

import org.springframework.stereotype.Service;
import sg.edu.nus.adproject.Model.DailyViews;
import sg.edu.nus.adproject.Repository.DailyViewsRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class DailyViewsService {
    private final DailyViewsRepository dailyViewsRepository;

    public DailyViewsService(DailyViewsRepository dailyViewsRepository) {
        this.dailyViewsRepository = dailyViewsRepository;
    }

    public void recordLogin() {
        LocalDate today = LocalDate.now();
        Optional<DailyViews> optionalDailyViews = dailyViewsRepository.findByDate(today);
        DailyViews dailyViews = optionalDailyViews.orElseGet(() -> new DailyViews(today));
        dailyViews.setLoginCount(dailyViews.getLoginCount() + 1);
        dailyViewsRepository.save(dailyViews);
    }

    public void recordSearch() {
        LocalDate today = LocalDate.now();
        Optional<DailyViews> optionalDailyViews = dailyViewsRepository.findByDate(today);
        DailyViews dailyViews = optionalDailyViews.orElseGet(() -> new DailyViews(today));
        dailyViews.setSearchCount(dailyViews.getSearchCount() + 1);
        dailyViewsRepository.save(dailyViews);
    }
}

