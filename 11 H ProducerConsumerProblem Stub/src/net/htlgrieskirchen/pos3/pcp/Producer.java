/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.pos3.pcp;

import java.util.ArrayList;
import java.util.List;

public class Producer implements Runnable {
    private final String name;
    private final Storage storage;
    private final int sleepTime;
    
    private final List<Integer> sent;
    private final int numberOfItems;
    
    public Producer(String name, Storage storage, int sleepTime, int numberOfItems) {
       this.name = name;
       this.storage = storage;
       this.sleepTime = sleepTime;
       this.numberOfItems = numberOfItems;
       sent = new ArrayList<>();
    }

    @Override
    public void run() {
        for(Integer i = 0; i < numberOfItems; i++) {
            try {
                if(storage.put(i)) {
                    sent.add(i);
                } else {
                    i--;
                    Thread.sleep(sleepTime);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        storage.setProductionComplete();
    }

    public List<Integer> getSent() {
        return sent;
    }
}
