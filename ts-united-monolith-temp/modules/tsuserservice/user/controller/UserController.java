package modules.tsuserservice.user.controller;
import modules.tscommon.edu.fudan.common.util.Response;
import modules.tsuserservice.user.dto.UserDto;
import modules.tsuserservice.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.ResponseEntity.ok;
/**
 *
 * @author fdse
 */
@RestController
@RequestMapping("/api/v1/userservice/users")
public class UserController {
    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/hello")
    public String testHello() {
        return "Hello";
    }

    @GetMapping
    public ResponseEntity<Response> getAllUser(@RequestHeader
    HttpHeaders headers) {
        UserController.LOGGER.info("[getAllUser][Get all user]");
        return ok(userService.getAllUsers(headers));
    }

    @GetMapping("/{userName}")
    public ResponseEntity<Response> getUserByUserName(@PathVariable
    String userName, @RequestHeader
    HttpHeaders headers) {
        UserController.LOGGER.info("[getUserByUserName][Get user by user name][UserName: {}]", userName);
        return ok(userService.findByUserName(userName, headers));
    }

    @GetMapping("/id/{userId}")
    public ResponseEntity<Response> getUserByUserId(@PathVariable
    String userId, @RequestHeader
    HttpHeaders headers) {
        UserController.LOGGER.info("[getUserByUserId][Get user by user id][UserId: {}]", userId);
        return ok(userService.findByUserId(userId, headers));
    }

    @PostMapping("/register")
    public ResponseEntity<Response> registerUser(@RequestBody
    UserDto userDto, @RequestHeader
    HttpHeaders headers) {
        UserController.LOGGER.info("[registerUser][Register user][UserName: {}]", userDto.getUserName());
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userDto, headers));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Response> deleteUserById(@PathVariable
    String userId, @RequestHeader
    HttpHeaders headers) {
        // only admin token can delete
        UserController.LOGGER.info("[deleteUserById][Delete user][UserId: {}]", userId);
        return ok(userService.deleteUser(userId, headers));
    }

    @PutMapping
    public ResponseEntity<Response> updateUser(@RequestBody
    UserDto user, @RequestHeader
    HttpHeaders headers) {
        UserController.LOGGER.info("[updateUser][Update user][UserId: {}]", user.getUserId());
        return ok(userService.updateUser(user, headers));
    }
}