/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

/**
 *
 * @author w200843582
 */
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
@Component
public class StudentDao {
    // Injected database connection:
    @PersistenceContext(unitName = "student-jpa") 
    private EntityManager em;
 
    public EntityManager getEm() {
return em;
}

public void setEm(EntityManager em) {
this.em = em;
}
    // Stores a new guest:
    @Transactional
    public void persist(Student student) {
        em.persist(student);
    }
    
    @Transactional
    public void remove(short id){
        Student student = em.find(Student.class, id);
        em.remove(student);
    }
    
    @Transactional
    public void update(short id, String name){
        Student student = em.find(Student.class, id);
        student.setFirstname(name);
        em.merge(student);
    }
    
    public Student get(short id){
        Student student = em.find(Student.class, id);
        return student;
    }
    // Retrieves all the guests:
    public List<Student> getAllStudents(int id) {
        TypedQuery<Student> query = em.createQuery(
            "SELECT s FROM Student s WHERE s.id="+id, Student.class);
        return query.getResultList();
    }
}
