import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;


public class Program {

    public static List<BigInteger> primes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        File source = new File("prime.txt");
        BigInteger x = BigInteger.valueOf(2);

        List<BigInteger> tmp = new ArrayList<BigInteger>();

        if (source.exists()) {
            System.out.println("Il file esiste");
            //primes= Files.readAllLines(source.toPath(), StandardCharsets.UTF_8).stream().map(Integer::parseInt).collect(Collectors.toList());
            primes = fileReader(Files.readAllBytes(source.toPath()));

            x = primes.get(primes.size() - 1);
        } else if (source.createNewFile()) {
            System.out.println("Il file è stato creato");

            if (isPrime(x)) {
                primes.add(x);
                tmp.add(x);
                System.out.println(x.toString());

            }
            x = x.add(BigInteger.ONE);
        }


        for (; x.compareTo(BigInteger.valueOf(1000000)) < 0; x = x.add(BigInteger.valueOf(2))){
            if (isPrime(x)) {
                primes.add(x);
                tmp.add(x);
                System.out.println(x);

            }
            if (tmp.size() == 10000) {
                fileWriter(tmp);
                tmp = new ArrayList<>();
            }
        }
    }


    public static boolean isPrime(BigInteger x) {
        //System.out.println("Verifing " + x);
        for (BigInteger n : primes) {
            if (x.mod(n).compareTo(BigInteger.ZERO) == 0) {
                return false;
            }
            if (n.multiply(n).compareTo(x)==0) {
                return true;
            }
        }
        return true;
    }

    public static List<BigInteger> fileReader(byte[] bytes) {

        List<BigInteger> res = new ArrayList<>();
        List<UByte> tmp = new ArrayList<>();
        //List<byte> tmp = new ArrayList<byte>();

        for (Byte s : bytes) {

            if(s == '\0'){
                try {
                    res.add(new BigInteger(toByteArray(tmp)));
                }catch (NumberFormatException e){
                    //e.printStackTrace();
                }
                tmp = new ArrayList<>();
                System.out.println(res.get(res.size()-1));

            }else{
                tmp.add(0, s);
            }

        }

        return res;
    }

    public static void fileWriter(List<BigInteger> input) {
        try {
            for (BigInteger bint : input) {

                Files.write(Paths.get("prime.txt"), bint.toByteArray(), StandardOpenOption.APPEND);
                Files.write(Paths.get("prime.txt"), new byte[]{'\0'}, StandardOpenOption.APPEND);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] toByteArray(List<Byte> in) {
        final int n = in.size();
        byte ret[] = new byte[n];
        for (int i = 0; i < n; i++) {
            ret[i] = in.get(i);
        }
        return ret;
    }
}