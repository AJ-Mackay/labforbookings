package com.example.codeclan.CourseBookingSystem.components;

import com.example.codeclan.CourseBookingSystem.models.Booking;
import com.example.codeclan.CourseBookingSystem.models.Course;
import com.example.codeclan.CourseBookingSystem.models.Customer;
import com.example.codeclan.CourseBookingSystem.repositories.BookingRepository;
import com.example.codeclan.CourseBookingSystem.repositories.CourseRepository;
import com.example.codeclan.CourseBookingSystem.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CustomerRepository customerRepository;

    public DataLoader(){}

    public void run(ApplicationArguments args) {

        Course course1 = new Course("Intro to Python", "Edinburgh", 2);
        courseRepository.save(course1);
        Course course2 = new Course("Intro to DevOps", "Livingston", 3);
        courseRepository.save(course2);
        Course course3 = new Course("Testing for Beginners", "Glasgow", 3);
        courseRepository.save(course3);

        Customer customer1 = new Customer("Michał", "Edinburgh", 28);
        customerRepository.save(customer1);
        Customer customer2 = new Customer("Paul", "Armadale", 35);
        customerRepository.save(customer2);

        Booking booking1 = new Booking("22-11-2020", course1, customer1);
        bookingRepository.save(booking1);
        Booking booking2 = new Booking("22-11-2020", course1, customer2);
        bookingRepository.save(booking2);
        Booking booking3 = new Booking("29-02-2020", course2, customer1);
        bookingRepository.save(booking3);
        Booking booking4 = new Booking("15-04-2020", course3, customer2);
        bookingRepository.save(booking4);

        course1.addBooking(booking1);
        customer1.addBooking(booking1);
        course3.addBooking(booking4);
        customer2.addBooking(booking4);
        course1.addBooking(booking2);
        customer1.addBooking(booking2);
        course2.addBooking(booking3);
        customer2.addBooking(booking3);
    }
}
