package com.codeline.demo.service;

import com.codeline.demo.dto.CourseResponseDTO;
import com.codeline.demo.dto.CourseCreateRequest;
import com.codeline.demo.dto.CourseCreateResponse;
import com.codeline.demo.entity.Course;
import com.codeline.demo.entity.Instructor;
import com.codeline.demo.entity.Marks;
import com.codeline.demo.helper.Constants;
import com.codeline.demo.helper.HelperUtils;
import com.codeline.demo.repositories.CourseRepository;
import com.codeline.demo.repositories.DepartmentRepository;
import com.codeline.demo.repositories.InstructorRepository;
import com.codeline.demo.repositories.MarksRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    InstructorRepository instructorRepository;

    @Autowired
    MarksRepository marksRepository;


    public CourseCreateResponse createCourse(CourseCreateRequest request) throws Exception {
        Course course = CourseCreateRequest.convertToCourse(request);
        course.setIsActive(Boolean.TRUE);


        Instructor instructor = instructorRepository.findInstructorById(request.getInstructorId());
        if (HelperUtils.isNotNull(instructor)) {
            course.setInstructor(instructor);
        } else {
            throw new Exception(Constants.COURSE_CREATE_REQUEST_INSTRUCTOR_ID_NOT_VALID);
        }

        List<Marks> marks = marksRepository.findMarkById(request.getMarksId());
        if (HelperUtils.isNotNull(marks) && HelperUtils.isListNotEmpty(marks)) {
            course.setMarks(marks);
        } else {
            throw new Exception(Constants.COURSE_CREATE_REQUEST_MARK_ID_NOT_VALID);
        }

        return CourseCreateResponse.convertToCourse(courseRepository.save(course));
    }


    public List<Course> getAllCourses() {
        return courseRepository.findByIsActiveTrue();
    }

    public List<CourseCreateResponse> getAllCoursesDTO() {
        List<Course> courses = courseRepository.findByIsActiveTrue();
        List<CourseCreateResponse> result = new ArrayList<>();
        for (Course c : courses) {
            result.add(CourseCreateResponse.convertToCourse(c));
        }
        return result;
    }


    public Course getCourseById(int id) throws Exception {
        Course existingCourse = courseRepository.findById(id).orElseThrow(() -> new Exception("Course not found"));
        if (existingCourse.getIsActive()) {
            return existingCourse;
        } else {
            throw new Exception("BAD REQUEST");
        }

    }

    public Course updateCourse(Course course) throws Exception {

        Course existingCourse = courseRepository.findById(course.getId()).orElseThrow(() -> new Exception("Course not found"));
        if (existingCourse.getIsActive()) {
            course.setCreatedDate(existingCourse.getCreatedDate());
            course.setIsActive(existingCourse.getIsActive());
            return courseRepository.save(course);
        } else {
            throw new Exception("BAD REQUEST");
        }
    }

    public void deleteCourse(int id) throws Exception {
        Course existingCourse = courseRepository.findById(id).orElseThrow(() -> new Exception("Course not found"));
        if (existingCourse.getIsActive()) {
            existingCourse.setIsActive(Boolean.FALSE);
            courseRepository.save(existingCourse);
        } else {
            throw new Exception("BAD REQUEST");
        }
    }

    private CourseResponseDTO mapToResponse(Course course) {
        // Instructor
        CourseResponseDTO.InstructorSummary instructorSummary = null;
        if (course.getInstructor() != null) {
            Instructor inst = course.getInstructor();

            CourseResponseDTO.DepartmentSummary deptSummary = null;
            if (inst.getDepartment() != null) {
                deptSummary = CourseResponseDTO.DepartmentSummary.builder()
                        .id(inst.getDepartment().getId())
                        .name(inst.getDepartment().getName())
                        .build();
            }

            instructorSummary = CourseResponseDTO.InstructorSummary.builder()
                    .id(inst.getId())
                    .name(inst.getInstructorName())
                    .department(deptSummary)
                    .build();
        }

        // Marks
        List<CourseResponseDTO.MarkSummary> marks = new ArrayList<>();
        if (course.getMarks() != null) {
            course.getMarks().forEach(m -> {
                marks.add(CourseResponseDTO.MarkSummary.builder()
                        .id(m.getId())
                        .studentName(m.getStudentName())
                        .score(m.getScore())
                        .createdDate(m.getCreatedDate())
                        .build());
            });
        }

        return CourseResponseDTO.builder()
                .id(course.getId())
                .courseName(course.getCourseName())
                .createdDate(course.getCreatedDate())
                .updatedDate(course.getUpdatedDate())
                .instructor(instructorSummary)
                .marks(marks)
                .build();
    }

}
