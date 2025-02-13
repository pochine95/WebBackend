package sg.edu.nus.adproject.serviceImpls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.edu.nus.adproject.Model.Feedback;
import sg.edu.nus.adproject.Model.User;
import sg.edu.nus.adproject.Repository.FeedbackRepository;
import sg.edu.nus.adproject.Repository.UserRepository;
import sg.edu.nus.adproject.Service.FeedbackService;

import java.util.List;


@Service
public class FeedbackServiceImpl implements FeedbackService { // ✅ Correctly implements FeedbackService

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findTop50ByOrderByTimeDesc();
}

    @Override
    public List<Feedback> getFeedbacksByUserId(long userId) {  // ✅ Correctly overriding
        return feedbackRepository.findTop50ByUserIdOrderByTimeDesc(userId);
    }

    // Retrieve the most recent feedback_new
    public Feedback getLatestFeedback() {
        return feedbackRepository.findTopByOrderByTimeDesc();
    }
}
//
//@Service
//public class FeedbackServiceImpl implements FeedbackService {
//
//
//    private FeedbackRepository feedbackRepository;
//
//
//    public List<Feedback> getAllFeedbacks() {
//
//        return feedbackRepository.findTop50ByOrderByTimeDesc();
//    }
//
//
//    public List<Feedback> getFeedbacksByUserId(Long userId) {
//        return feedbackRepository.findTop50ByUserIdOrderByTimeDesc(userId);
//    }
//}

//    @Autowired
//    private UserRepository userRepository;

//    @Override
//    public List<Feedback> getAllFeedbacks() {
//        return feedbackRepository.findTop50ByOrderByTimeDesc();
//    }

//..........
//    @Override
//    public List<Feedback>getFeedbacksByUserId(long userId){
//        UserServiceImpl userService = null;
//        Optional<User> user = Optional.ofNullable(userService.getUserById(userId));
//
////        Optional<User> user = userService.getUserById(userId); // Fetch user
//        if (user.isPresent()) {
//            return feedbackRepository.findByUser(user.get()); // Fetch feedbacks from repository
//        } else {
//            throw new RuntimeException("User not found with ID: " + userId);
//        }
//    }
//    @Override
//    public Feedback addFeedback(Long userId, String context) {
//        Optional<User> user = userRepository.findById(userId);
//        if (user.isPresent()) {
//            Feedback feedback = new Feedback(user.get(), context);
//            return feedbackRepository.save(feedback);
//        }
//        throw new RuntimeException("User not found");
//    }
//}
//.........
//    @Override
//    public List<Feedback> getAllFeedbacks() {
//
//        return feedbackRepository.findAll();
//    }


