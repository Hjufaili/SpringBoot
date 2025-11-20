package com.codeline.demo.service;

import com.codeline.demo.controllers.CourseController;
import com.codeline.demo.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseService {



    private Map<Integer, String> courses = new HashMap<>();
    private List<Course> coursesList = new ArrayList<>();
    private Integer courseID = 1;


    public void createCourse(Course requestObj) {
        requestObj.setCourseId(courseID);
        requestObj.setCreatedDate(new Date());
        requestObj.setIsActive(true);

        coursesList.add(requestObj);

        courseID++;
    }

/*    public String createCourse(@RequestParam String name) {
        courses.put(courseID, name);
        return "Course create (name: " + name + " ID: " + courseID++ + ")";
    }*/

    /*public String saveCourse(@RequestBody String name) {
        courses.put(courseID, name);
        return "Course create (name: " + name + " ID: " + courseID++ + ")";
    }*/

    public List<Course> getAll() {
        purgeOldInactiveCourses();
        return coursesList;
    }

//    @GetMapping("/getAll")
//    public List<Course> getAllCourses() {
//        List<Course> responseList = new ArrayList<>();
//        for (Course s : coursesList) {
//            if (s.getIsActive()) {
//                responseList.add(s);
//            }
//        }
//        return responseList;
//    }
//    public Map<Integer, String> getAllCourses() {
//        return courses;
//    }

    public ResponseEntity getCourse(int id) {
        for (Course s : coursesList) {
            if (s.getCourseId() == id && s.getIsActive()) {
                return ResponseEntity.ok(s);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Course not found");

    }
//    public String getCourseByID(@RequestParam int id) {
//        return courses.getOrDefault(id, "course not found");
//    }

//    public String getCourseById(int id) {
//        return courses.getOrDefault(id, "course not found");
//    }

    public ResponseEntity getCourseByCourseName(String name) {
        for (Course course : coursesList) {
            if (course.getCourseName().equals(name) && course.getIsActive() ) {
                return ResponseEntity.status(HttpStatus.OK).body(course);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Course.builder().build());
    }

//    public String getCourseByName(String name) {
//        for (Map.Entry<Integer, String> entry : courses.entrySet()) {
//            if (entry.getValue().equalsIgnoreCase(name)) {
//                return "Course found: ID = " + entry.getKey() + ", Name = " + entry.getValue();
//            }
//        }
//        return "Course not found";
//    }

    public String updateCourse(Course updateObjFromUser) {
        if (updateObjFromUser != null && updateObjFromUser.getCourseId() != null) {

            Course existingStudentToUpdate = findCourseById(updateObjFromUser.getCourseId());

            coursesList.remove(existingStudentToUpdate);

            existingStudentToUpdate.setCourseName(updateObjFromUser.getCourseName());
            existingStudentToUpdate.setInstructor(updateObjFromUser.getInstructor());
            existingStudentToUpdate.setUpdateDate(new Date());

            coursesList.add(existingStudentToUpdate);

            return "Course updated successfully";
        }
        return "Course not found";
    }
//    public String updateCourse(@RequestParam int id, @RequestParam String name) {
//        if (courses.containsKey(id)) {
//            courses.put(id, name);
//            return "Course updated successfully";
//        }
//        return "Course not found";
//    }

    public String deleteCourse(int id) {
        Course existingCourseToUpdate = findCourseById(id);

        if (existingCourseToUpdate.getCourseId() > 0) {
            // Remove from current list
            coursesList.remove(existingCourseToUpdate);

            existingCourseToUpdate.setIsActive(false);
            existingCourseToUpdate.setUpdateDate(new Date());
            existingCourseToUpdate.setDeletedAt(new Date()); // mark deletion time

            coursesList.add(existingCourseToUpdate);

            return "Course deleted successfully (moved to inactive list)";
        } else {
            return "Invalid id";
        }
    }

    public String restoreCourse(int id) {
        Course existing = findCourseById(id);

        // Not found (your findStudentById returns courseId = -1)
        if (existing == null || existing.getCourseId() <= 0) {
            return "Course not found";
        }

        // Remove old copy
        coursesList.remove(existing);

        // Restore it
        existing.setIsActive(true);
        existing.setUpdateDate(new Date());
        existing.setDeletedAt(null); // very important

        // Add back as active
        coursesList.add(existing);

        return "Course restored successfully";
    }


    //    public String deleteCourse(@PathVariable int id) {
//        if (courses.remove(id) != null) {
//            return "Course deleted successfully";
//        }
//        return "Course not found";
//    }
//
//
    public Course findCourseById(int id) {
        for (Course s : coursesList) {
            if (s.getCourseId() == id) {
                return s;
            }
        }
        return Course.builder().courseId(-1).build();
    }

    public void purgeOldInactiveCourses() {
        Date now = new Date();
        long oneDayMillis = 24L * 60 * 60 * 1000;

        coursesList.removeIf(course ->
                Boolean.FALSE.equals(course.getIsActive()) &&
                        course.getDeletedAt() != null &&
                        (now.getTime() - course.getDeletedAt().getTime()) > oneDayMillis
        );
    }
}
