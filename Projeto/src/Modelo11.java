//simulaçao do modelo 1 pra 1

public class Modelo11 {
    public static void main(String[] args) throws InterruptedException {
        int[] quantidades = {10, 100, 500, 1000}; //quantidade de threads que vai testa
        long iteracoes = 500000; //as threads vai executar aq

        System.out.println("=== MODELO 1:1 ===\n");

        //vai testa quantidade diferentes de threads
        for (int N : quantidades) {
            Thread[] threads = new Thread[N];
            long inicio = System.nanoTime(); //marcador de tempo do inicio

            //vai cria e inicia todas threads aq
            for (int i = 0; i < N; i++) {
                threads[i] = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long soma = 0;
                        for (long k = 0; k < iteracoes; k++) {
                            soma += k;
                        }
                        if (soma == -1) System.out.println("erro");
                    }
                });
                threads[i].start();
            }

            for (int i = 0; i < N; i++) {
                threads[i].join();
            }

            long fim = System.nanoTime();

            long tempo = (fim - inicio) / 1000000;

            System.out.printf("N=%d threads -> tempo total: %d ms%n", N, tempo);
        }

        System.out.println("\nfim dos teste do modelo 1:1");
    }
}


