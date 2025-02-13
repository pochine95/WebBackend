package sg.edu.nus.adproject.serviceImpls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.edu.nus.adproject.Model.DailyView;
import sg.edu.nus.adproject.Repository.DailyViewRepository;
import sg.edu.nus.adproject.Service.DailyViewService;
import sg.edu.nus.adproject.dtos.ChartDTO;
import sg.edu.nus.adproject.utils.DateGenerator;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DailyViewServiceImpl implements DailyViewService {

    @Autowired
    DailyViewRepository dailyViewRepository;

    @Override
    public List<DailyView> getAllDailyViews() {
        return dailyViewRepository.findAll();
    }

    @Override
    public ChartDTO pastSixMonthsDailyView(YearMonth userInputMonth) {

        Map<YearMonth, Integer> loginCountsInLastSixMonths = new HashMap<>();
        Map<YearMonth, Integer> searchCountsInLastSixMonths = new HashMap<>();

        List<YearMonth> lastSixMonths = DateGenerator.getPastSixMonths(userInputMonth);

        for (YearMonth yearMonth : lastSixMonths) {
            int year = yearMonth.getYear();
            int month = yearMonth.getMonthValue();

            int totalLoginCount = 0;
            int totalSearchCount = 0;

            List<LocalDate> allDaysInCurrentMonth = DateGenerator.getDatesForMonth(year, month);

            for (LocalDate day : allDaysInCurrentMonth) {
                DailyView dailyView = dailyViewRepository.findByDate(day);

                if (dailyView != null) {
                    totalLoginCount += dailyView.getLoginCount();
                    totalSearchCount += dailyView.getSearchCount();
                }

            }

            loginCountsInLastSixMonths.put(yearMonth, totalLoginCount);
            searchCountsInLastSixMonths.put(yearMonth, totalSearchCount);

        }

        ChartDTO chartDTO = new ChartDTO();
        chartDTO.setLoginCountsInLastSixMonths(loginCountsInLastSixMonths);
        chartDTO.setSearchCountsInLastSixMonths(searchCountsInLastSixMonths);
        chartDTO.setLastSixMonths(lastSixMonths);

        return chartDTO;
    }

    @Override
    public Map<String, Object> getDailyLoginStats() {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        LocalDate sevenDaysAgo = today.minusDays(6); // Last 7 days including today
        List<DailyView> dailyViews = dailyViewRepository.findByMonth(today);


        // Fetch today's and yesterday's login count
        DailyView todayData = dailyViewRepository.findByDate(today);
        DailyView yesterdayData = dailyViewRepository.findByDate(yesterday);

        int todayLogin = (todayData != null) ? todayData.getLoginCount() : 0;
        int yesterdayLogin = (yesterdayData != null) ? yesterdayData.getLoginCount() : 0;

        // Fetch login counts for the last 7 days
        List<DailyView> last7DaysData = dailyViewRepository.findLast7DaysData(sevenDaysAgo, today);
        List<Integer> past7DaysLogin = last7DaysData.stream()
                .map(DailyView::getLoginCount)
                .collect(Collectors.toList());

        // Construct response
        Map<String, Object> response = new HashMap<>();
        response.put("todayLogin", todayLogin);
        response.put("yesterdayLogin", yesterdayLogin);
        response.put("past7DaysLogin", past7DaysLogin);

        return response;
    }
}


//    @Override
//    public ChartDTO pastSixMonthsDailyView() {
//
//        Map<YearMonth, Integer> loginCountsInLastSixMonths = new HashMap<>();
//        Map<YearMonth, Integer> searchCountsInLastSixMonths = new HashMap<>();
//
//        List<YearMonth> lastSixMonths = DateGenerator.getPastSixMonths();
//
//
//        for (YearMonth yearMonth : lastSixMonths) {
//            int year = yearMonth.getYear();
//            int month = yearMonth.getMonthValue();
//
//            int totalLoginCount = 0;
//            int totalSearchCount = 0;
//
//            List<LocalDate> allDaysInCurrentMonth = DateGenerator.getDatesForMonth(year, month);
//
//            for (LocalDate day : allDaysInCurrentMonth) {
//                DailyView dailyView = dailyViewRepository.findByDate(day);
//                if(dailyView != null)
//                {
//                    totalLoginCount += dailyView.getLoginCount();
//                    totalSearchCount += dailyView.getSearchCount();
//                }
//            }
//
//            loginCountsInLastSixMonths.put(yearMonth, totalLoginCount);
//            searchCountsInLastSixMonths.put(yearMonth, totalSearchCount);
//
//        }
//
//        ChartDTO chartDTO = new ChartDTO();
//        chartDTO.setLoginCountsInLastSixMonths(loginCountsInLastSixMonths);
//        chartDTO.setSearchCountsInLastSixMonths(searchCountsInLastSixMonths);
//        chartDTO.setLastSixMonths(lastSixMonths);
//
//        return chartDTO;
//    }


    /**
     * Get chart data for a selected month
     */
//    @Override
//    public Map<String, Object> getChartData(String selectedMonth) {
//        // Parse the month input (e.g., "02/2024")
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
//        LocalDate date = LocalDate.parse("01/" + selectedMonth, formatter); // Use first day of the month
//
//        // Fetch daily data from the database
//        List<DailyView> dailyViews = dailyViewRepository.findByMonth(date);
//
//        // Prepare response data
//        List<String> categories = new ArrayList<>();
//        List<Integer> loginData = new ArrayList<>();
//        List<Integer> searchData = new ArrayList<>();
//
//        for (DailyView dailyView : dailyViews) {
//            categories.add(dailyView.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
//            loginData.add(dailyView.getLoginCount());
//            searchData.add(dailyView.getSearchCount());
//        }
//
//        // Construct API response
//        Map<String, Object> response = new HashMap<>();
//        response.put("ok", true);
//        response.put("categories", categories);
//        response.put("series", Arrays.asList(
//                Map.of("name", "Logins", "data", loginData),
//                Map.of("name", "Searches", "data", searchData)
//        ));
//
//        return response;
//    }
//}
//    @Override
//    public List<DailyView> getAllDailyViews() {
//
//        return dailyViewRepository.findAll();
//    }

