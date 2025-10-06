package com.example.airport;

class Airport {
    Object airline=new Object();

    void flightUp(Plane plane) {
        synchronized (airline) {
            System.out.println(plane.getName() + "success flight up from airport");
            airline.notifyAll();
        }
    }

    void flightDown(Plane plane) {
        synchronized (airline) {
            System.out.println(plane.getName() + "success flight down to airport");
            airline.notifyAll();
        }
    }
}
