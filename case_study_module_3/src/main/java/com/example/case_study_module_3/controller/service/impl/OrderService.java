package com.example.case_study_module_3.controller.service.impl;

import com.example.case_study_module_3.controller.dto.OrderDTO;
import com.example.case_study_module_3.controller.repository.OrderRepository;
import com.example.case_study_module_3.controller.service.IOrderService;


import java.util.List;

public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderDTO> getAll() {
        return orderRepository.getAll();
    }

    @Override
    public OrderDTO findById(int id) {
        return orderRepository.findById(id);
    }

    @Override
    public void save(OrderDTO order) {
        orderRepository.save(order);
    }

    @Override
    public boolean update(OrderDTO order) {
        return orderRepository.update(order);
    }

    @Override
    public boolean deleteById(int id) {
        return orderRepository.deleteById(id);
    }

    @Override
    public List<OrderDTO> getAllDTO() {
        return orderRepository.getAllDTO();
    }

    @Override
    public List<OrderDTO> searchByCustomerName(String name) {
        return orderRepository.searchByCustomerName(name);
    }
}