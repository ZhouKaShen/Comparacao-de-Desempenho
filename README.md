# Comparação de Desempenho entre Modelos de Threads N:M e 1:1

Esse projeto demonstra de forma prática, a diferença de desempenho entre dois modelos de gerenciamento de threads:

Modelo 1:1 - cada thread de usuário corresponde a uma thread real do sistema operacional.
Modelo N:M - múltiplas threads de usuário são executadas por um número fixo de threads reais(thread pool).

## Descrição dos Modelos

### Modelo 1:1(Modelo11.java)
Cada tarefa é executada em uma nova thread real.
O sistema operacional precisa criar, agendar e gerenciar cada uma delas, o que pode gerar alta sobrecarga quando o número de threads é grande.

### Modelo N:M(ModeloNM.java)
Um número fixo de threads do sistema(geralmente igual ao número de núcleos da CPU) é usado pra executar muitas tarefas de usuário.
Essas tarefas são enfileiradas e processadas por um pool de threads, reduzindo o custo de criação e troca de contexto.

## Parâmetros do Experimento

| Parâmetro            |                      Descrição                         | Valor                             |
|----------------------|--------------------------------------------------------|-----------------------------------|
| N                    | Número de tarefas/threads de usuário testadas          | 10, 100, 500, 1000                |
| M                    | Threads do sistema (pool) no modelo N:M                | 4 (número de núcleos disponíveis) |
| Iterações por tarefa | Simulação de carga de trabalho (loop)                  | 500000                            |

## Resultados Obtidos

### Modelo N:M (pool de 4 threads)

| N (tarefas) | Tempo total (ms)  |
|--------------|------------------|
| 10           | 195              |
| 100          | 16               |
| 500          | 38               |
| 1000         | 51               |

### Modelo 1:1 (threads diretas)

| N (threads) | Tempo total (ms)  |
|--------------|------------------|
| 10           | 41               |
| 100          | 24               |
| 500          | 144              |
| 1000         | 270              |

## Análise Comparativa

- Para poucas threads(N = 10), o modelo 1:1 é equivalente ou ligeiramente mais rápido, já que a sobrecarga de criação é pequena.
- A partir de N = 100, o modelo N:M se torna muito mais eficiente, mantendo tempos baixos mesmo com muitas tarefas.
- O modelo 1:1 sofre degradação de desempenho ao ultrapassar o número de núcleos disponíveis(M = 4), devido ao custo de gerenciamento e troca de contexto entre centenas de threads reais.

## Conclusão

- Modelo 1:1: vantajoso apenas para poucas threads(até cerca de 10).
- Modelo N:M: muito mais eficiente para cargas maiores(mais de 100 tarefas), aproveitando melhor os núcleos da CPU.
