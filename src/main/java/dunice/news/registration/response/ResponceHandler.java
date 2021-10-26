package dunice.news.registration.response;

import dunice.news.commond.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

public class ResponceHandler {

    private UserRepository userRepository;

    public void ServRelease(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", responseObj);
        return new ResponseEntity<Object>(map, status);
    }



    public static ResponseEntity<Object> generateResponse2(HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", status.value());
        map.put("data", responseObj);
        return new ResponseEntity<Object>(map, status);
    }
}