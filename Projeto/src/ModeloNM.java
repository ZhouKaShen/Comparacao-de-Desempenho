//simulaçao do modelo n pra m

import java.util.concurrent.*;

public class ModeloNM {
    public static void main(String[] args) throws InterruptedException {
        int[] quantidades = {10, 100, 500, 1000};

        int M = Runtime.getRuntime().availableProcessors();

        long iteracoes = 500_000;

        System.out.println("=== MODELO N:M ===\n");

        System.out.println("threads do sistema(pool): " + M + " \n");

        for (int N : quantidades) {
            ExecutorService pool = Executors.newFixedThreadPool(M);
            CountDownLatch latch = new CountDownLatch(N);

            long inicio = System.nanoTime();

            for (int i = 0; i < N; i++) {
                pool.submit(new Runnable() {
                    @Override
                    public void run() {
                        long soma = 0;
                        for (long k = 0; k < iteracoes; k++) {
                            soma += k;
                        }
                        if (soma == -1) System.out.println("erro");
                        latch.countDown();
                    }
                });
            }

            latch.await();
            long fim = System.nanoTime();
            pool.shutdown();

            long tempo = (fim - inicio) / 1000000;

            System.out.printf("N=%d tarefas -> tempo total: %d ms%n", N, tempo);
        }

        System.out.println("\nfim dos teste do modelo N:M");
    }
}

