package com.example.codeclan.CourseBookingSystem.controllers;
import com.example.codeclan.CourseBookingSystem.models.Booking;
import com.example.codeclan.CourseBookingSystem.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/bookings")
public class BookingController {

        @Autowired
        BookingRepository bookingRepository;

        @GetMapping
        public ResponseEntity<List<Booking>> getAllBookings(){
            return new ResponseEntity<>(bookingRepository.findAll(), HttpStatus.OK);
        }

        @GetMapping(value = "/{id}")
        public ResponseEntity getBooking(@PathVariable Long id){
            return new ResponseEntity<>(bookingRepository.findById(id), HttpStatus.OK);
        }

        @PostMapping
        public ResponseEntity<Booking> postBooking(@RequestBody Booking booking){
            bookingRepository.save(booking);
            return new ResponseEntity<>(booking, HttpStatus.CREATED);
        }

        @DeleteMapping("/{id}")
        void deleteBooking(@PathVariable Long id) {
            bookingRepository.deleteById(id);
        }

        @PutMapping("/{id}")
        Booking replaceBooking(@RequestBody Booking newBooking, @PathVariable Long id) {

            return bookingRepository.findById(id)
                    .map(booking -> {
                        booking.setDate(newBooking.getDate());
                        booking.setCustomer(newBooking.getCustomer());
                        booking.setCourse(newBooking.getCourse());
                        return bookingRepository.save(booking);
                    })
                    .orElseGet(() -> {
                        newBooking.setId(id);
                        return bookingRepository.save(newBooking);
                    });
        }


}
