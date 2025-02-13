package sg.edu.nus.adproject.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sg.edu.nus.adproject.Model.Feedback;
import sg.edu.nus.adproject.Service.FeedbackService;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    /**
     * ✅ Get feedback based on userId, sorted by date
     */
    @GetMapping("/{userId}")
    public ResponseEntity<List<Map<String, String>>> getFeedbacksByUserId(@PathVariable("userId") long userId) {
        List<Feedback> feedbacks = feedbackService.getFeedbacksByUserId(userId);

        // Convert to formatted response
        List<Map<String, String>> formattedFeedbacks = feedbacks.stream()
                .map(fb -> Map.of(
                        "time", fb.getTime().toString(), //Converts fb.getTime() to a string.
                        "context", fb.getContext(),//Gets the feedback text.
                        "username", fb.getUser().getUsername()
                        // Extracts the user’s name from the User object inside Feedback.
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(formattedFeedbacks);
    }

    /**
     * ✅ Get latest 50 feedbacks (all users), sorted by date
     */
    @GetMapping("/getAllFeedbacks")
    public ResponseEntity<List<Map<String, String>>> getAllFeedbacks() {
        List<Feedback> feedbacks = feedbackService.getAllFeedbacks();

        // Sort feedbacks first by date (descending), then by time (descending within the same day)
        List<Map<String, String>> formattedFeedbacks = feedbacks.stream()
                .sorted(Comparator.comparing(Feedback::getTime).reversed()) // Sort by date-time descending
                .limit(50) // Limit to top 50 results
                .map(fb -> Map.of(
                        "time", fb.getTime().toString(),// "2024-02-10T12:30".Retrieves the timestamp of when the feedback was submitted.
                        "context", fb.getContext(),//Retrieves the feedback text
                        "username", fb.getUser().getUsername()//Extracts the username from the User object
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(formattedFeedbacks);
    }

    /**
     * ✅ Get the latest feedback entry based on time
     */

//   1. Fetch Latest Feedback->	Calls feedbackService.getLatestFeedback().
//   2. Check if Feedback Exists->	If null, returns 404 Not Found.
//   3. Format Time ->	Converts time to "hh:mm AM/PM" format.
//  4. Extract Content ->Gets the feedback text (context).
//   5. Extract User Data ->Gets userid and username.
//  6. Return JSON Response -> Sends formatted feedback in HTTP 200 OK.

    @GetMapping("/latest")
    public ResponseEntity<Map<String, String>> getLatestFeedback() {
        Feedback feedback = feedbackService.getLatestFeedback(); //Fetch Latest Feedback
        //check if feedback is null, if null -> returns 404 not found
        if (feedback == null) {
            return ResponseEntity.notFound().build();
        }
        // Format response
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a"); // Format to "09:30 am"

        Map<String, String> formattedFeedback = Map.of(
                "time", feedback.getTime() != null ? feedback.getTime().toLocalTime().format(timeFormatter) : "N/A", //Converts LocalDateTime to a formatted time string ("09:30 AM").
                "content", feedback.getContext() != null ? feedback.getContext() : "No content",//Gets the feedback text (context).
                "userid", (feedback.getUser() != null && feedback.getUser().getId() != null) ? String.valueOf(feedback.getUser().getId()) : "N/A",//return userId of the person who submitted the feedback, if user is null return N/A
                "username", feedback.getUser() != null ? feedback.getUser().getUsername() : "Anonymous" //retrieve username, if user is null-> return anonymous
        );

        return ResponseEntity.ok(formattedFeedback);
    }

}

//.....
//   @GetMapping("/{userId}")
//   public List<Feedback> getFeedbacksByUserId(@PathVariable("userId") long userId) {
//       return feedbackService.getFeedbacksByUserId(userId);
//   }
//
//    @GetMapping("/getAllFeedbacks")
//    public List<Feedback> getAllFeedbacks() {
//        return feedbackService.getAllFeedbacks();
//    }


