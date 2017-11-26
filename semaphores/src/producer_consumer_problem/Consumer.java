/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producer_consumer_problem;
import static producer_consumer_problem.Producer_consumer_problem.*;
/**
 *
 * @author Pascal
 */
public class Consumer extends Thread{
    @Override
    public void run(){
        try {
            consumer();
        } catch (InterruptedException ex) {
            System.err.println("Fehler");
        }
    }
    void consumer() throws InterruptedException {
        Boolean acquired = false;
        int item; 
        while (true) { 
            
            Producer_consumer_problem.available.acquire();
            acquired = true;
            if (Producer_consumer_problem.count <= 0) { 
                acquired = false;
                Producer_consumer_problem.available.release(); 
                synchronized(this){this.wait();}
            } 
            
            if(acquired)
                Producer_consumer_problem.available.release();
            
           
            Producer_consumer_problem.available.acquire();
            item = remove(); 
            Producer_consumer_problem.count--; 
            Producer_consumer_problem.available.release();
            
            Producer_consumer_problem.available.acquire();
            acquired = true;
            
            if (Producer_consumer_problem.count <= Producer_consumer_problem.N-1) {
                Producer_consumer_problem.available.release();
                acquired = false;
                synchronized(Producer_consumer_problem.p){Producer_consumer_problem.p.notify();}
                
            } 
            if(acquired){
                    Producer_consumer_problem.available.release();
                }
            consume(item); 
            
            
        } 
       
    }

    private int remove() {
        return 1;
    }

    private void consume(int item) {
        System.out.println("consume");
    }
}
