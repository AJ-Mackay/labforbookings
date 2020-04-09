package com.example.codeclan.CourseBookingSystem.controllers;

import com.example.codeclan.CourseBookingSystem.models.Customer;
import com.example.codeclan.CourseBookingSystem.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping(value = "/customers")
public class BookingController {

    public class CustomerController {

        @Autowired
        CustomerRepository customerRepository;

        @GetMapping
        public ResponseEntity<List<Customer>> getAllCustomers(){
            return new ResponseEntity<>(customerRepository.findAll(), HttpStatus.OK);
        }

        @GetMapping(value = "/{id}")
        public ResponseEntity getCustomer(@PathVariable Long id){
            return new ResponseEntity<>(customerRepository.findById(id), HttpStatus.OK);
        }
    }

}
