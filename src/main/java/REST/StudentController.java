/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REST;

/**
 *
 * @author W200843582
 */

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import student.Student;
import student.StudentDao;

@RestController
public class StudentController {
           ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring-config-dao.xml"});

        StudentDao dao = (StudentDao) context.getBean("studentDao");

    @RequestMapping("/GET")
    public String get(@RequestParam(value="id") short id) {
        return dao.get(id).toString();
    }
    
    @RequestMapping("/DEL")
    public String delete(@RequestParam(value="id") short id) {
        dao.remove(id);
        return"Student "+id+" deleted.";
    }
    
    @RequestMapping("/UPDATE")
    public String update(@RequestParam(value="id") short id,@RequestParam(value="firstname") String firstname) {
        dao.update(id,firstname);
        return "Changed Student "+id+" name to "+firstname;
    }
    
    @RequestMapping("/POST")
    public String put(@RequestParam(value="firstname") String firstname,@RequestParam(value="lastname") String lastname,@RequestParam(value="DOB") String dob) {
        Student s = new Student(firstname,lastname,dob);
        dao.persist(s);
        return "Posted new student: +"+s.getFirstname();
    }
}