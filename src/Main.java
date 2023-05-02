public class Main {
    public static void main(String[] args) {
        BloomFilter<String> bloomFilter = new BloomFilter<>(3, 0.03);

        bloomFilter.add("192.170.0.1");
        bloomFilter.add("75.245.10.1");
        bloomFilter.add("10.125.22.20");

        System.out.println(bloomFilter.test("192.170.0.1"));
        System.out.println(bloomFilter.test("75.245.10.1"));
        System.out.println(bloomFilter.test("10.125.22.20"));

        System.out.println(bloomFilter.test("191.170.0.1"));
        System.out.println(bloomFilter.test("75.244.10.1"));
        System.out.println(bloomFilter.test("11.125.22.20"));
    }
}