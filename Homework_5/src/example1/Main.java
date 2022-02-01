package example1;

public class Main {
    public static void main(String[] args){
        double[] input = new double[10001];

        for(int i = 0; i < input.length; i++) {
            input[i] = (double) 3 + i;
        }

        System.out.println(ReciprocalArraySum.seqArraySum(input));
        System.out.println(ReciprocalArraySum.parManyTaskArraySum(input, 2));
    }
}
