package com.example.codeclan.CourseBookingSystem.controllers;
import com.example.codeclan.CourseBookingSystem.models.Course;
import com.example.codeclan.CourseBookingSystem.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/courses")
public class CourseController {

    @Autowired
    CourseRepository courseRepository;

    @GetMapping
    public ResponseEntity getAllCoursesAndFilters(
            @RequestParam(required = false, name = "stars") Integer stars,
            @RequestParam(required = false, name = "name") String name
    ){
        if(stars != null){
            return new ResponseEntity(courseRepository.findByStars(stars), HttpStatus.OK);
        }
//        if(name != null){
//            return new ResponseEntity(courseRepository.findByCustomerNameIgnoreCase(name), HttpStatus.OK);
//        }
        return new ResponseEntity(courseRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getCourse(@PathVariable Long id){
        return new ResponseEntity<>(courseRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Course> postCourse(@RequestBody Course course){
        courseRepository.save(course);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    void deleteCourse(@PathVariable Long id) {
        courseRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    Course replaceCourse(@RequestBody Course newCourse, @PathVariable Long id) {

        return courseRepository.findById(id)
                .map(course -> {
                    course.setName(newCourse.getName());
                    course.setTown(newCourse.getTown());
                    course.setStars(newCourse.getStars());
                    course.setBookings(newCourse.getBookings());
                    return courseRepository.save(course);
                })
                .orElseGet(() -> {
                    newCourse.setId(id);
                    return courseRepository.save(newCourse);
                });
    }
}
