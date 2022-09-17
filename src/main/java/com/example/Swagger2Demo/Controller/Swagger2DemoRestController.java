package com.example.Swagger2Demo.Controller;

import com.example.Swagger2Demo.Model.Student;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Api(value = "Swagger2DemoRestController", description = "REST Apis related to Student Entity")
@RestController
public class Swagger2DemoRestController {

    List<Student> students = new ArrayList<>();

    {
        students.add(new Student("Emina", "5", "Uk"));
        students.add(new Student("Ayo", "15", "Japan"));
        students.add(new Student("Lola", "11", "Norway"));
        students.add(new Student("Liv", "2", "France"));
    }

    @ApiOperation(value = "Get list of Students in the System", response = Iterable.class, tags = "getStudents")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success || OK!"),
            @ApiResponse(code = 401, message = "not Authorized"),
            @ApiResponse(code = 403, message = "forbidden!"),
            @ApiResponse(code = 404, message = "not found!")})

    @RequestMapping(value = "/getStudents")
    public List<Student> getStudents() {
        return students;
    }

    @ApiOperation(value = "Get Specific Students in the System", response = Student.class, tags = "getStudent")
    @RequestMapping(value = "/getStudent/{name}")
    public Student getStudent(@PathVariable(value = "name") String name) {

        return students.stream().filter(x -> x.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList()).get(0);
    }

    @ApiOperation(value = "Get specific Student By Country in the System ", response = Student.class, tags = "getStudentByCountry")
    @RequestMapping(value = "/getStudentByCountry/{country}")
    public List<Student> getStudentByCountry(@PathVariable(value = "country") String country) {
        System.out.println("Searching Student in country : " + country);
        List<Student> studentsByCountry = students.stream().filter(x -> x.getCountry().equalsIgnoreCase(country))
                .collect(Collectors.toList());
        System.out.println(studentsByCountry);
        return studentsByCountry;
    }


    @ApiOperation(value = "Get specific Student By Class in the System ",response = Student.class,tags="getStudentByClass")
    @RequestMapping(value = "/getStudentByClass/{cls}")
    public List<Student> getStudentByClass(@PathVariable(value = "cls") String cls) {
        return students.stream().filter(x -> x.getCls().equalsIgnoreCase(cls)).collect(Collectors.toList());
    }
}
                                                                                                                                                