package org.juandavyc;

import javax.swing.*;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.lang.Thread.sleep;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        comparatorExample();
        // runnableExample();
        // callableExample();
        // actionListenerExample();
    }


    private static void comparatorExample() {
        //var tree = new TreeSet<>((Integer a,Integer b)->Integer.compare(b,a)); // mayor a menor
        var tree = new TreeSet<>(Integer::compare); // menor a mayor

        tree.add(6);
        tree.add(1);
        tree.add(2);
        tree.add(5);
        tree.add(6);
        tree.add(2);

        tree.forEach(System.out::println);
    }



    private static void runnableExample() {
        var thread = new Thread(Main::runBehavior);
        thread.start();
    }

    private static void runBehavior() {
        int i = 0;
        while (i++ != 100) {
            System.out.println("Running " + i);
        }
    }

    private static void callableExample() {

        try {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Future<String> future = executor.submit(() -> operation());
            if (future.isDone()) {
                System.out.println(future.get());
            }
            executor.shutdown();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

    private static String operation() {

        Character[] charArray = new Character[]{'S', '0', ' ', 'P', 'B', '1', '3'};
        var bufferString = new StringBuffer("Armando cadena -> ");

        Arrays.stream(charArray)
                .forEach(character -> {
                    System.out.println(character);
                    bufferString.append(character);
                    sleep(1000);
                });
        System.out.println(Thread.currentThread().getName());
        return bufferString.toString();
    }

    private static void sleep(int millis) {
        try {

            Thread.sleep(millis);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void actionListenerExample() {

        var frame = new JFrame();
        var btn = new JButton("Exit");

        frame.getContentPane().add(btn);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);

        btn.addActionListener(event -> System.exit(0));

//        btn.addActionListener(event->{
//            System.out.println("Saliendo...");
//            System.exit(0);
//        });
//        btn.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//               System.exit(0);
//            }
//        });


    }
}