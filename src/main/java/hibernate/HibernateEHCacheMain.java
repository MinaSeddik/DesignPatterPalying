package hibernate;

import net.sf.ehcache.CacheManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.model.Caching;
import org.hibernate.stat.Statistics;


public class HibernateEHCacheMain {

    public static void main(String[] args) {

        System.out.println("Temp Dir:" + System.getProperty("java.io.tmpdir"));

        //Initialize Sessions
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Statistics stats = sessionFactory.getStatistics();
        System.out.println("Stats enabled=" + stats.isStatisticsEnabled());
        stats.setStatisticsEnabled(true);
        System.out.println("Stats enabled=" + stats.isStatisticsEnabled());

        Session session = sessionFactory.openSession();
        Session otherSession = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Transaction otherTransaction = otherSession.beginTransaction();

        printStats(stats, 0);

        Employee1 emp = session.load(Employee1.class, 21);
        printData(emp, stats, 1);

        emp = session.load(Employee1.class, 21);
        printData(emp, stats, 2);

        //clear first level cache, so that second level cache is used
        session.evict(emp);
        emp = session.load(Employee1.class, 21);
        printData(emp, stats, 3);

        emp = session.load(Employee1.class, 22);
        printData(emp, stats, 4);

        emp = otherSession.load(Employee1.class, 21);
        printData(emp, stats, 5);

        //Release resources
        transaction.commit();
        otherTransaction.commit();
        sessionFactory.close();


        int len = CacheManager.ALL_CACHE_MANAGERS.size();
        System.out.println("all cache size: " + len);

        int size = CacheManager.ALL_CACHE_MANAGERS.get(0)
                .getCache("employee").getSize();

        System.out.println("employee cache size: " + size);


    }

    private static void printStats(Statistics stats, int i) {
        System.out.println("***** " + i + " *****");
        System.out.println("Fetch Count=" + stats.getEntityFetchCount());
        System.out.println("Second Level Hit Count=" + stats.getSecondLevelCacheHitCount());
        System.out.println("Second Level Miss Count=" + stats.getSecondLevelCacheMissCount());
        System.out.println("Second Level Put Count=" + stats.getSecondLevelCachePutCount());
    }

    private static void printData(Employee1 emp, Statistics stats, int count) {
//        System.out.println(count+":: Name="+emp.getName()+", Zipcode="+emp.getAddress().getZipcode());
        System.out.println(count + ":: Name=" + emp.getName());
        printStats(stats, count);
    }

}