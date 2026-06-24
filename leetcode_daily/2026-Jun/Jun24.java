class Solution {
    long[][] multiply(long[][] A, long[][] B) {
        int size = A.length;
        long[][] C = new long[size][size];
        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                if (A[i][k] != 0) {
                    for (int j = 0; j < size; j++) {
                        C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % 1000000007;
                    }
                }
            }
        }
        return C;
    }

    long[] multiply(long[] V, long[][] A) {
        int size = V.length;
        long[] nextV = new long[size];
        for (int k = 0; k < size; k++) {
            if (V[k] != 0) {
                for (int j = 0; j < size; j++) {
                    nextV[j] = (nextV[j] + V[k] * A[k][j]) % 1000000007;
                }
            }
        }
        return nextV;
    }

    long[][] power(long[][] base, long exp) {
        int size = base.length;
        long[][] res = new long[size][size];
        for (int i = 0; i < size; i++) res[i][i] = 1;
        long[][] a = new long[size][size];
        for(int i = 0; i < size; i++) System.arraycopy(base[i], 0, a[i], 0, size);
        
        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = multiply(res, a);
            }
            a = multiply(a, a);
            exp >>= 1;
        }
        return res;
    }

    public int zigZagArrays(int n, int l, int r) {
        int k = r - l + 1;
        if (k < 2) return 0;
        
        long[][] U = new long[k][k];
        long[][] D = new long[k][k];
        for (int i = 0; i < k; i++) {
            for (int j = i + 1; j < k; j++) {
                U[i][j] = 1;
            }
            for (int j = 0; j < i; j++) {
                D[i][j] = 1;
            }
        }
        
        long[][] M = multiply(U, D);
        int m = (n - 1) / 2;
        long[][] Mm = power(M, m);
        
        long[] V = new long[k];
        for (int i = 0; i < k; i++) {
            V[i] = 1;
        }
        
        V = multiply(V, Mm);
        if ((n - 1) % 2 == 1) {
            V = multiply(V, U);
        }
        
        long sum = 0;
        for (int i = 0; i < k; i++) {
            sum = (sum + V[i]) % 1000000007;
        }
        
        return (int) ((sum * 2) % 1000000007);
    }
}