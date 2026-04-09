package tn.esprit.smartfood;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.smartfood.Dto.OrderDTO;

import javax.sql.DataSource;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestAPI {

    private String title="Hello, i'm the User Micro-Service";
    @RequestMapping("/hello")
    public String sayHello(){
        System.out.println(title);
        return title;
    }



    @Autowired
    DataSource dataSource;

    @GetMapping("/db-info")
    public String getDbInfo() throws Exception {
        return dataSource.getConnection().getMetaData().getDatabaseProductName();
    }


    @Autowired
    private Userservice userService;
    @GetMapping
    public ResponseEntity<List<User>> getListUser() {
        List<User> user = userService.getAll();
        if (user.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(user);
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") int id,
                                           @RequestBody User user){
        return ResponseEntity.ok(userService.updateUser(id, user));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id){
        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }




    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getById(id);
    }



    @GetMapping("/{id}/orders")
    public List<OrderDTO> getOrdersOfUser(@PathVariable Long id) {
        return userService.getOrdersOfUser(id);
    }

}
