package ru.netology.courseProject.data;

import lombok.SneakyThrows;
import lombok.Value;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqlHelper {
    public static QueryRunner runner = new QueryRunner();
//    public static Connection conn = connection();

    @Value
    public static class CreditRequestEntity {
        String id;
        String bankId;
        String created;
        String status;
    }
    @Value
    public static class OrderEntity {
        String id;
        String created;
        String creditId;
        String paymentId;
    }
    @Value
    public static class PaymentEntityInfo {
        String id;
        int amount;
        String created;
        String status;
        String transactionId;
    }

    @SneakyThrows
    private static Connection connection() {
        return  DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "user", "password");
    }

    @SneakyThrows
    public static long countOfRecordsFromPaymentEntity() {
        return runner.query(connection(), "SELECT count(*) FROM payment_entity", new ScalarHandler<>());
    }


    @SneakyThrows
    public static long countOfRecordsFromCreditRequestEntity() {
        return runner.query(connection(), "SELECT count(*) FROM credit_request_entity", new ScalarHandler<>());
    }

    @SneakyThrows
    public static int amountFromPaymentEntity() {
        return runner.query(connection(), "SELECT amount FROM payment_entity", new ScalarHandler<>());
    }
//
//    @SneakyThrows
//    public static int numberOfRecordsFromOrderEntity() {
//        return runner.query(connection(), "SELECT count(*) FROM order_entity", new ScalarHandler<>());
//    }

    @SneakyThrows
    public static String statusFromPaymentEntity() {
        return runner.query(connection(), "SELECT status FROM payment_entity", new ScalarHandler<>());
    }

    @SneakyThrows
    public static String statusFromCreditRequestEntity() {
        return runner.query(connection(), "SELECT status FROM credit_request_entity", new ScalarHandler<>());
    }
    @SneakyThrows
    public static void cleanDataBase() {
        var runner = new QueryRunner();
        runner.update(connection(), "DELETE  FROM credit_request_entity");
        runner.update(connection(), "DELETE  FROM order_entity");
        runner.update(connection(), "DELETE  FROM payment_entity");
    }
}
