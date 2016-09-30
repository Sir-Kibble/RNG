package rng;
public class UGen {
    double w1;
    
    public UGen(){
        w1 = 1;
    }
    
    public double u16807(int seed, int iterations,boolean isInitial){
        //if first call to UGen
        if(isInitial)
            w1 = seed;//,w2;
        //double u = 0.0;
        final int FFF = 16807;
        //performing iterations... can't iterate less than 0
        for(int x = 0; x != iterations && iterations > 0; x++){
                w1 = (w1*FFF) % ((int)(Math.pow(2, 31) -1));
            }//end for
        return (w1/((int)Math.pow(2, 31) -1));
    }//end RNG
}//end ugen