package tn.esprit.smartfood;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.smartfood.Client.OrderClient;
import tn.esprit.smartfood.Dto.OrderDTO;

import java.util.List;

@Service
public class Userservice {
    @Autowired
    private UserRepository userRepository;
    public List<User> getAll(){
        return userRepository.findAll();
    }
    public User addUser(User user) {
        return userRepository.save(user);
    }
    public User updateUser(int id, User newUser) {
        if (userRepository.findById(id).isPresent()) {
            User existingUser = userRepository.findById(id).get();
            existingUser.setName(newUser.getName());
            existingUser.setPhone(newUser.getPhone());
            existingUser.setAddress(newUser.getAddress());
            return userRepository.save(existingUser);

        } else
            return null;
    }
    public String deleteUser(int id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return "user supprimé";
        } else
            return "user non supprimé";
    }

    public User getById(int id) {
        return userRepository.findById(id).orElse(null);
    }


    @Autowired
    private OrderClient orderClient;

    public List<OrderDTO> getOrdersOfUser(Long userId) {
        return orderClient.getOrdersByUser(userId);
    }
}
