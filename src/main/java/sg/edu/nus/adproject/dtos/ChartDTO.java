package sg.edu.nus.adproject.dtos;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChartDTO {
    private Map<YearMonth, Integer> loginCountsInLastSixMonths;
    private Map<YearMonth, Integer> searchCountsInLastSixMonths;
    private List<YearMonth> lastSixMonths;

    public ChartDTO()
    {
        loginCountsInLastSixMonths = new HashMap<>();
        searchCountsInLastSixMonths = new HashMap<>();
        lastSixMonths = new ArrayList<>();
    }

    public Map<YearMonth, Integer> getLoginCountsInLastSixMonths() {
        return loginCountsInLastSixMonths;
    }

    public void setLoginCountsInLastSixMonths(Map<YearMonth, Integer> loginCountsInLastSixMonths) {
        this.loginCountsInLastSixMonths = loginCountsInLastSixMonths;
    }

    public Map<YearMonth, Integer> getSearchCountsInLastSixMonths() {
        return searchCountsInLastSixMonths;
    }

    public void setSearchCountsInLastSixMonths(Map<YearMonth, Integer> searchCountsInLastSixMonths) {
        this.searchCountsInLastSixMonths = searchCountsInLastSixMonths;
    }

    public List<YearMonth> getLastSixMonths() {
        return lastSixMonths;
    }

    public void setLastSixMonths(List<YearMonth> lastSixMonths) {
        this.lastSixMonths = lastSixMonths;
    }
}
