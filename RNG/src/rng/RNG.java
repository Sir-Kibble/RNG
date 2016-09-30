package rng;
import java.util.Scanner;
public class RNG {
    public static void main(String[] args) {
        //w[i+1] = (w[i] * 16807) % (2^31-1)
        //u[i] = w[i]/m = w[i]/2^31-1
        Scanner S = new Scanner(System.in);
        int count, iteration, seed;
        //final int FFF = 16807; 
        
        String s = "asd&245";
        
        //s.matches("");
        //s.contains("ads");
            System.out.println("1b)W0 = 1, U[10000] : "+u16807(1,0,10000));
            System.out.println("2b)W0 = 1, m = 2500 : "+u16807(1,0,2500));
            System.out.println("2c) ^p = 0");pFinder(1,2500,0,1,1,1);
            System.out.println("2d) ^p = 1");pFinder(1,2500,1,1,1,0);
            //System.out.println("2d) See attached paper");
            System.out.println("2e) m = 2500, W0 = 1: P(A) = P(B) = P(C) = .5; P(D ) = 0.  p = "+Circuit(1, 2500, .5,.5,.5,0));pFinder(1,2500,.5,.5,.5,0);
            System.out.println("2f) Standard error using swing digit method : "+Math.sqrt(1*(1-1)/2500));
            //System.out.println("Enter # interations: ");
            //iteration = S.nextInt();
            //System.out.println("Random number = "+u16807(seed, 0,iteration));
            //System.out.println("Circuit? " +Circuit(seed, iteration,.8,.9,.7,.5));
            
            
            
        
        
    }//end main
    //A = .8, b = .9, C = .7, D = .6
    private static double u16807(int seed, int c, int iterations){
        double w1 = seed;//,w2;
        //double u = 0.0;
        final int FFF = 16807;
        for(int x = 0; x != iterations && iterations > 0; x++){
                w1 = (w1*FFF + c) % ((int)(Math.pow(2, 31) -1));
                //w1 = w2;
            }//end for
        return (w1/((int)Math.pow(2, 31) -1));
    }//end RNG
    
    private static boolean Circuit(int seed, int iterations,double a, double b, double c, double d){
        UGen U = new UGen();
        boolean A,B,C,D;
        A = false;
        B = false;
        C = false;
        D = false;
        if(u16807(seed, 0, iterations) <= a){
            A = true;
        }
        if(u16807(seed, 0, iterations) <= b){
            B = true;
        }
        else if(u16807(seed, 0, iterations) <= c){
            C = true;
        }//end if
        else if(u16807(seed, 0, iterations) <= d){
            D = true;
        }//end if
        if((A&&D) || (A&&B&&C))
            return true;
        else
            return false;
    }//end circuit
    
    private static void pFinder(int seed, int iterations,double a, double b, double c, double d){
        UGen U = new UGen();
        boolean A,B,C,D;
        boolean flag = true;
        A = false;
        B = false;
        C = false;
        D = false;
        int count = 0,kount = 0,ccc = iterations;
        while(kount < iterations){
            if(U.u16807(seed, iterations, flag) <= a){
                A = true;
            }//end if
            if(U.u16807(seed, iterations, false) <= b){
                B = true;
            }//end if
            if(U.u16807(seed, iterations, false) <= c){
                C = true;
            }//end if
            if(U.u16807(seed, iterations, false) <= d){
                D = true;
            }//end if
            if((A&&D) || (A&&B&&C)){
                count++;
            }//end if
            kount++;
            A = false;
            B = false;
            C = false;
            D = false;
            flag = false;
        }//end while
        double pHat = (double)count / (double)iterations;
        //p 1-p / n sqrt
        double ste = Math.sqrt((pHat*(1.0-pHat)/(double)iterations));
        System.out.println("^p = "+pHat);
        System.out.println("Standard error = "+ste);
    }//end circuit
}//end class