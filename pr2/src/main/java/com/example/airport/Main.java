package com.example.airport;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//simple multithread example
public class Main {
    public static void main(String args[]) throws InterruptedException {
        Airport airport=new Airport();
        ExecutorService ex= Executors.newFixedThreadPool(3);
        CountDownLatch cd=new CountDownLatch(3);
        for(int i=1;i<=10;i++){
            ex.execute(new Plane("Plane"+i,airport));
            cd.countDown();
        }
        ex.shutdown();
    }
}
