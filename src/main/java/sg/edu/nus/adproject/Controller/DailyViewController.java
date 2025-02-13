package sg.edu.nus.adproject.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sg.edu.nus.adproject.Model.DailyView;
import sg.edu.nus.adproject.Service.DailyViewService;
import sg.edu.nus.adproject.dtos.ChartDTO;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class DailyViewController {

    @Autowired
    private DailyViewService dailyViewService;
    private Object formattedMonths;

    @GetMapping("/getAllDailyViews")
    public List<DailyView> getAllDailyViews() {
        return dailyViewService.getAllDailyViews();

    }

//    Receives a request with a year and month (e.g., /getLastSixMonthsData/2024/6).
//    Retrieves login & search data for the past six months from dailyViewService.
//    Formats the data into a response suitable for a chart.
//    Returns the response to the frontend.
    @GetMapping("/getLastSixMonthsData/{year}/{month}")
    public ResponseEntity<Map<String, Object>> getLastSixMonthsData(@PathVariable int year, @PathVariable int month) {
        YearMonth yearMonth = YearMonth.of(year, month);

        // Get data from service
        //Fetches login & search counts for the last six months.
        //Returns a ChartDTO object that holds this data.
//       1. Receive Request	Accepts GET /getLastSixMonthsData/{year}/{month}.
//       2. Extract Data	Extracts year & month from the URL.
//       3. Fetch Data	Calls dailyViewService to get login & search data for the past 6 months.
//       4. Process Data	Converts the data into a format suitable for charts.
//       5. Return Response	Sends login & search trends in JSON format.
        ChartDTO chartDTO = dailyViewService.pastSixMonthsDailyView(yearMonth);


        // Extract last six months
        List<YearMonth> lastSixMonths = chartDTO.getLastSixMonths();

        // Format months for categories
        List<String> formattedMonths = lastSixMonths.stream()
                .map(ym -> String.format("%02d %d", ym.getMonthValue(),ym.getYear())) // Example: "01 2024"
                .collect(Collectors.toList());


        // Prepare series data
        List<Map<String, Object>> series = new ArrayList<>();

        series.add(Map.of(
                "name", "Logins",
                "data", lastSixMonths.stream()
                        .map(chartDTO.getLoginCountsInLastSixMonths()::get) // Get values using YearMonth keys
                        .collect(Collectors.toList())
        ));

        series.add(Map.of(
                "name", "Searches",
                "data", lastSixMonths.stream()
                        .map(chartDTO.getSearchCountsInLastSixMonths()::get) // Get values using YearMonth keys
                        .collect(Collectors.toList())
        ));

        // Construct final response
        Map<String, Object> response = new HashMap<>();
        response.put("ok", true);
        response.put("categories", formattedMonths);
        response.put("series", series);

        return ResponseEntity.ok(response);
    }
    /**
     * Fetch today's, yesterday's, and past 7 days' login counts.
     */
    //Handles GET requests at /loginStats.
    //Retrieves login statistics from dailyViewService.
    //Returns the data if successful.
    //Handles errors and sends an error response if something goes wrong.
//    Handles GET requests at /loginStats.
//    Retrieves login statistics from dailyViewService.
//    Returns the data if successful.
//    Handles errors and sends an error response if something goes wrong.

    @GetMapping("/loginStats") //Maps this method to the /loginStats endpoint.
    public ResponseEntity<Map<String, Object>> getDailyLoginStats() {
    //Defines the function to return login stats as JSON.
        try {
            Map<String, Object> trafficData = dailyViewService.getDailyLoginStats();
            //Calls the dailyViewService.getDailyLoginStats() method to fetch login statistics.
            return ResponseEntity.ok(trafficData);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of(
              //Sends a 500 Internal Server Error if something goes wrong.
                    "ok", false,
                    "statusText", "Network response error"
            ));
        }
    }
}


    //TNS....
//    @GetMapping("/getLastSixMonthsData/{year}/{month}")
//    public ChartDTO getLastSixMonthsData(@PathVariable int year, @PathVariable int month) {
//        ChartDTO chartDTO = new ChartDTO();
//
//        YearMonth yearMonth = YearMonth.of(year, month);
//
//        chartDTO = dailyViewService.pastSixMonthsDailyView(yearMonth);
//
//        return chartDTO;
//    }
//......


//    @GetMapping("/chart")
//    public ResponseEntity<Map<String, Object>> getChartData(@RequestParam String month) {
//        try {
//            Map<String, Object> chartData = dailyViewService.getChartData(month);
//            return ResponseEntity.ok(chartData);
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body(Map.of(
//                    "ok", false,
//                    "statusText", "Network response error"
//            ));
//        }
//    }

//    @GetMapping
//    public ResponseEntity<TrafficDataResponse> getTrafficData() {
//        return ResponseEntity.ok(dailyViewService.getTrafficData());
//    }

//    @GetMapping("/getAllDailyViews")
//    public List<DailyView> getAllDailyViews() {
//        return dailyViewService.getAllDailyViews();
//
//    }

