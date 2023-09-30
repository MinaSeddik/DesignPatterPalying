package hibernate;


import net.sf.ehcache.CacheManager;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Employee1 emp = new Employee1();
        emp.setName("John");
        emp.setRole("Designer");
        emp.setInsertTime(new Date());

        //Get Session
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.getCurrentSession();

//        session.createQuery("");
//        session.createNamedQuery("");
//        Query query = session.createNativeQuery("");
//        query.addEntity(Employee1.class);
//        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

        // Create CriteriaBuilder
//        CriteriaBuilder builder = session.getCriteriaBuilder();
        // Create CriteriaQuery
//        CriteriaQuery<Employee1> criteriaQuery = builder.createQuery(Employee1.class);
//        List results = criteriaQuery.getOrderList();

//
//        Root<Employee1> root = criteriaQuery.from(Employee1.class);
//        criteriaQuery.select(root);
//        criteriaQuery.where(builder.equal(root.get("salary"), 20000));

//        Query<Employee1> query = session.createQuery(criteriaQuery);




        //start transaction
        session.beginTransaction();

        //Save the Model object
        session.save(emp);

        //Commit transaction
        session.getTransaction().commit();
        System.out.println("Employee ID="+emp.getId());

        //terminate session factory, otherwise program won't end
        sessionFactory.close();

        int len = CacheManager.ALL_CACHE_MANAGERS.size();
        System.out.println("all cache size: " + len);

        int size = CacheManager.ALL_CACHE_MANAGERS.get(0)
                .getCache("employee").getSize();

        System.out.println("employee cache size: " + size);
    }


}
