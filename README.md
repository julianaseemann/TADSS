# TADSS

Heap - onde fica os objetos
Thread sleep - fica em outro estado por um determinado tempo e depois vai para ready
    unico jeito de ir para reunning é com o schadule 

## Partes:
Theads
- CPU
- Code run
- Stack ()

### Diferença entre Stack e Heap: cada thread tem uma stack (stack overflow). 
Variáveis locais são armazenadas na stack - só a thread acessa. 
Heap - área compartilhada - todas as threads acessam (através de referências na stack)

threads de plataforma (sistema operacional)
TP -> 1 PARA 1com threads do OS

green thread - pouco usada
    focava em dispositivos com 1 núcleo e era simulada pela aplicação
thread virtual (corountines) - aplicação faz um controle 

### Decomposição 
    - Decompositar tarefa, verificar se pode fazer ela de forma paralela

