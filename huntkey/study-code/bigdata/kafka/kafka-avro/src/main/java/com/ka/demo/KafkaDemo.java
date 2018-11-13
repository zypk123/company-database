package com.ka.demo;

import com.ka.consumer.Consumer;
import com.ka.entity.User;
import com.ka.producer.Producer;
import com.ka.topic.Topic;

import java.util.List;
import java.util.Scanner;

/**
 * Created by zhangyu1 on 2017/5/27.
 */
public class KafkaDemo {

    public static void main(String[] args) {

        Producer<User> producer = new Producer<>();
        Consumer<User> consumer = new Consumer<>();

        System.out.println("Please input 'send', 'receive', or 'exit'");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.next();

            switch (input) {
                case "send":
                    producer.sendData(Topic.USER, new User("张三", 20, "霍山"));
                    break;
                case "receive":
                    List<User> users = consumer.receive(Topic.USER);
                    if (users.isEmpty()) {
                        System.out.println("Received nothing");
                    } else {
                        users.forEach(user -> System.out.println("Received user: " + user));
                    }
                    break;
                case "exit":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please input 'send', 'receive', or 'exit'");
            }
        }
    }
}
