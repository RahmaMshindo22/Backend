package com.example.seafood;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order-payment")
public class OrderPaymentController {

    @Autowired
    private OrderPaymentService orderPaymentService;

    @PostMapping("/order")
    public ResponseEntity<?> placeOrder(@RequestBody OrderPayment orderPayment) {
        orderPayment.setBankName(null);
        orderPayment.setCardNumber(null);
        orderPayment.setEnterAmount(null);
        OrderPayment savedOrder = orderPaymentService.saveOrderPayment(orderPayment);
        return ResponseEntity.ok(savedOrder);
    }

    @PostMapping("/payment")
    public ResponseEntity<?> makePayment(@RequestBody OrderPayment orderPayment) {
        orderPayment.setProduct(null);
        orderPayment.setQuantity(0);
        OrderPayment savedPayment = orderPaymentService.saveOrderPayment(orderPayment);
        return ResponseEntity.ok(savedPayment);
    }

    // New GET method to retrieve all order payments
    @GetMapping
    public ResponseEntity<List<OrderPayment>> getAllOrderPayments() {
        List<OrderPayment> orderPayments = orderPaymentService.getAllOrderPayments();
        return ResponseEntity.ok(orderPayments);
    }

    // New GET method to retrieve a single order payment by ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderPayment> getOrderPaymentById(@PathVariable Long id) {
        Optional<OrderPayment> orderPayment = orderPaymentService.getOrderPaymentById(id);
        return orderPayment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // New PUT method to update an existing order payment
    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderPayment(@PathVariable Long id, @RequestBody OrderPayment orderPayment) {
        Optional<OrderPayment> existingOrderPayment = orderPaymentService.getOrderPaymentById(id);
        if (existingOrderPayment.isPresent()) {
            OrderPayment updatedOrderPayment = orderPaymentService.updateOrderPayment(id, orderPayment);
            return ResponseEntity.ok(updatedOrderPayment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // New DELETE method to delete an existing order payment by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderPayment(@PathVariable Long id) {
        Optional<OrderPayment> existingOrderPayment = orderPaymentService.getOrderPaymentById(id);
        if (existingOrderPayment.isPresent()) {
            orderPaymentService.deleteOrderPayment(id);
            return ResponseEntity.noContent().build();  // 204 No Content
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
