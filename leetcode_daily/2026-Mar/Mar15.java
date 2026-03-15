class Fancy {
    private static final int M = 1000000007;
    private static final int[] INV = new int[101];
    
    static {
        for (int i = 1; i <= 100; i++) {
            long res = 1;
            long base = i;
            long exp = M - 2;
            while (exp > 0) {
                if ((exp & 1) == 1) res = (res * base) % M;
                base = (base * base) % M;
                exp >>= 1;
            }
            INV[i] = (int) res;
        }
    }

    private int[] vals;
    private int size;
    private long A;
    private long B;
    private long invA;

    public Fancy() {
        vals = new int[100005];
        size = 0;
        A = 1;
        B = 0;
        invA = 1;
    }
    
    public void append(int val) {
        long v = (val - B) % M;
        if (v < 0) v += M;
        v = (v * invA) % M;
        vals[size++] = (int) v;
    }
    
    public void addAll(int inc) {
        B = (B + inc) % M;
    }
    
    public void multAll(int m) {
        A = (A * m) % M;
        B = (B * m) % M;
        invA = (invA * INV[m]) % M;
    }
    
    public int getIndex(int idx) {
        if (idx >= size) return -1;
        return (int) ((A * vals[idx] + B) % M);
    }
}