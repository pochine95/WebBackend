package sg.edu.nus.adproject.Controller;
import org.springframework.web.bind.annotation.*;
import sg.edu.nus.adproject.Service.DailyViewsService;



@RestController
@RequestMapping("/daily-views")
public class DailyViewsController {

    private final DailyViewsService dailyViewsService;

    public DailyViewsController(DailyViewsService dailyViewsService) {
        this.dailyViewsService = dailyViewsService;
    }

    @PostMapping("/login")
    public void recordLogin() {
        dailyViewsService.recordLogin();
    }

    @PostMapping("/search")
    public void recordSearch() {
        dailyViewsService.recordSearch();
    }
}