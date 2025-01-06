package com.example.case_study_module_3.service;

import com.example.case_study_module_3.dto.OrderDTO;

import java.util.List;


public interface IOrderService extends IService<OrderDTO> {
    OrderDTO findById(int OrderId);
    List<OrderDTO> getAllDTO();
    List<OrderDTO> searchByCustomerName(String name);
}
