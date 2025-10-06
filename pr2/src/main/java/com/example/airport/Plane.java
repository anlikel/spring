package com.example.airport;

class Plane implements Runnable{
    private String name;
    private Airport airport;

    Plane(String name,Airport airport){
        this.name=name;
        this.airport=airport;
    }

    String getName() {
        return name;
    }

    @Override
    public void run() {
        flightUp();
        System.out.println(name+" flying");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        flightDown();
        System.out.println(name+" end working");
    }

void flightUp(){
        System.out.println(name+" waiting for flight up");
        airport.flightUp(this);
}

    void flightDown(){
        System.out.println(name+" waiting for flight down");
        airport.flightDown(this);
    }
}
