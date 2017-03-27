import java.util.*;

// Problem Source: https://www.codewars.com/kata/k-primes/train/java

public class KPrimes {

	/* ------------------------------------------------- */
	/* ---------------- CODEWAR METHODS ---------------- */
	/* ------------------------------------------------- */
	
	
	// Pre: s is not null, and is an integer greater than 0
	// Post: Given positive integers s and a, b, c where a is 1-prime, b 3-prime, c 7-prime, 
	//       finds the number of solutions of a + b + c = s
    public static int puzzle(int s) {       
    	long[] sevenPrimes = countKprimes(7, 2, s);
    	System.out.println(Arrays.toString(countKprimes(7,1,s)));
    	int count = 0;
    	for(int i=0; i<sevenPrimes.length; i++) {
    		System.out.println("-------------------------------------------");
    		int sevP = (int)sevenPrimes[i];
    		System.out.println("Working with: " + sevP);
    		int remaining = s - sevP;
    		long[] threePrimes = countKprimes(3, 2, remaining);
    		long[] onePrimes = countKprimes(1, 2, remaining);
    		count += countSolutions(remaining, threePrimes, onePrimes);
    		System.out.println("count: " + count); 
    	}
    	return count;
    	
    }
    

	// Pre: k, start, end are not null. 
	//      k is an integer greater than 0.
	//      start, end are greater than 0.
	//      end is larger than or equal to start
	// Post: Returns a list of k primes between start and end
	public static long[] countKprimes(int k, long start, long end) {	    
	    long[] tempResult = new long[(int)end-(int)start+1];
	    int count=0;
	    for(int i=(int)start; i<=(int)end; i++) {
	         if(isKPrime(i,k)) {
	            tempResult[count] = i;
	            count ++;
	         }
	    }
	    long[] result = new long[count];
	    for(int i=0; i<count; i++) {
	        result[i] = tempResult[i];
	    }	    
	    return result;
	}
	
	/* ------------------------------------------------- */
	/* ----------------- HELPER METHODS ---------------- */
	/* ------------------------------------------------- */

	// Pre: n, a, b not null
	//		n is an integer greater than or equal to 0
	// Post: Returns the number of solutions ax + bx = n, 
	//       where ax is in a, and bx is in b
	private static int countSolutions(int n, long[] a, long[] b) {
		int count = 0;
		for(int i=0; i< a.length; i++) {
			for(int j=0; j<b.length; j++) {
				if(a[i] + b[j] == n) {
						count++;
				}
			}
		}
		return count;
	}
	
	
	// Pre:  n and k are not null
	// 	     n,k are integers greater than 0
	// Post: Returns true if n is kprime, false otherwise
	private static boolean isKPrime(int n, int k) {
		if(countPrimeFactors(n) == k) {
			return true;
		}
		return false;
	}
		
	
	// Pre: n is an integer greater than 0, not null
	// Post: Returns the number of prime factors that n has
	private static int countPrimeFactors(int n) {
		int count = 0;
		if(isPrime(n)) {
			return 1;
		}
		for(int i=2; i<=Math.ceil(n/2); i++) {
			if(n%i == 0 && isPrime(i)) {
				count = count + 1 + countPrimeFactors(n/i); 
				break;
			}
		}
		return count;
	}
		
	
		
	// Pre:  n is not null and is an integer greater than 0
	// Post: Returns true if n is prime. Returns false otherwise
	private static boolean isPrime(int n) {
		if(n == 1 || n == 2) {
			return true;
		}
		int m = (int) Math.ceil(Math.sqrt(n));
		for(int i=2; i<=m; i++) {
			if( n%i == 0 ) {
				return false;
			}
		}
		return true;
	}
    

}
    
    
