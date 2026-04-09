package tn.esprit.smartfood.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.smartfood.Dto.OrderDTO;

import java.util.List;


@FeignClient(name = "ORDERMANAGEMENT")
public interface OrderClient {

    @GetMapping("/orders/user/{userId}")
    List<OrderDTO> getOrdersByUser(@PathVariable("userId") Long userId);

}