public class BloomFilter<T> {

    private final int hashesPerElement;
    private final int bitsNumber;
    private final boolean[] contents;

    public BloomFilter(int expected, double desiredProbability) {
        bitsNumber = (int) -Math.ceil(
                (expected * Math.log(desiredProbability))
                        / (Math.log(2) * Math.log(2)));

        hashesPerElement = (int) -Math.ceil(logB(desiredProbability, 2));

        contents = new boolean[bitsNumber];
    }

    public void add(T obj) {
        int[] set = getObjectMask(obj);
        for (int index : set) {
            contents[index] = true;
        }
    }

    public boolean test(T obj) {
        int[] set = getObjectMask(obj);
        for (int index : set) {
            if (!contents[index])
                return false;
        }
        return true;
    }

    private int[] getObjectMask(T obj) {
        int[] indices = new int[hashesPerElement];
        for (int hashIndex = 0; hashIndex < hashesPerElement; hashIndex++) {
            indices[hashIndex] = getHashBit(obj, hashIndex);
        }
        return indices;
    }

    private int getHashBit(T obj, int hashIndex) {
        return indexedHash(obj, hashIndex) % bitsNumber;
    }

    private int indexedHash(T obj, int index) {
        return Math.abs((obj.hashCode() >>> index) ^ (obj.hashCode() << index));
    }

    private static double logB(double val, double base) {
        return Math.log(val) / Math.log(base);
    }
}
