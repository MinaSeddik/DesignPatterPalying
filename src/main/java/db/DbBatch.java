package db;

import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class DbBatch {

    public static ExecutorService executor = Executors.newFixedThreadPool(10);


    /*
    1) LAST_INSERT_ID() returns last id inserted from the current connection so that's ok
    2) You need to little change your code to use public <T> int[][] batchUpdate(String sql, final Collection<T> batchArgs, final int batchSize, final ParameterizedPreparedStatementSetter<T> pss) method from JdbcTemplate.
    3) I recommend to set batchSize parameter to 1000
     */

    public static void multi_insert() {
        String sql = "insert into role(name, code) values(?,?);" +
                "insert into person(first_name, last_name, description, role_id) values(?,?,?,(SELECT LAST_INSERT_ID()));";

//        int[] arr = template.batchUpdate(sql, new BatchPreparedStatementSetter() {
//
//            @Override
//            public void setValues(PreparedStatement ps, int i) throws SQLException {
//                Role role = roles.get(i);
//                Person person = list.get(i);
//                ps.setObject(1, role.getName());
//                ps.setObject(2, role.getCode();
//                ps.setObject(3, person.getFirstName());
//                ps.setObject(4, person.getLastName());
//                ps.setObject(5, person.getDescription());
//            }
//
//            @Override
//            public int getBatchSize() {
//                return list.size();
//            }
//        });
    }




    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String sql = "INSERT INTO `USER` (USERNAME, PASSWORD, CREATEDTIME, UPDATEDTIME, USERTYPE, DATEOFBIRTH)"
                + " VALUES(?,?,?,?,?,?)";

        List<User> users = new ArrayList<>();
        int batchSize = 1000;
        StopWatch timer = new StopWatch();


        final AtomicInteger sublists = new AtomicInteger();

        Map<Integer, List<User>> partionedUsers = users.stream()
                .collect(Collectors.groupingBy(t -> sublists.getAndIncrement() / batchSize));

        CompletableFuture[] futures = users.stream()
                .collect(Collectors.groupingBy(t -> sublists.getAndIncrement() / batchSize))
                .values()
                .stream()
                .map(ul -> runBatchInsert(ul, sql))
                .toArray(CompletableFuture[]::new);
        CompletableFuture<Void> completableFuture = CompletableFuture.allOf(futures);


        timer.start();
        completableFuture.get();
        timer.stop();

        System.out.println("batchInsertAsync -> Total time in seconds: " + timer.getTotalTimeSeconds());
    }

    public static CompletableFuture<Void> runBatchInsert(List<User> users, String sql) {
        return CompletableFuture.runAsync(() -> {
//            jdbcTemplate.batchUpdate(sql, new UserBatchPreparedStatementSetter(users));
        }, executor);
    }
}
