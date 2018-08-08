using System;
using System.Collections.Generic;

namespace PrimeGenerator
{
    class Program
    {
        
        public static List<UInt32> primes = new List<UInt32>();

        static void Main(string[] args)
        {
            
            UInt32 i = 2;

            if(isPrime(i)){
                    primes.Add(i);
                    //Console.WriteLine(i);
                }
            for(i++; i<16777216; i+=2){
                if(isPrime(i)){
                    primes.Add(i);
                    //Console.WriteLine(i);
                }
            }
        }

        private static bool isPrime(UInt32 x)
        {
            foreach (UInt32 n in primes) {
            if (x % n == 0) {
                return false;
            }
            if (n*n > x) {
                return true;
            }
        }
        return true;
        }
    }
}
