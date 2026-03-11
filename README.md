# TADSS

## Partes:
Theads
- CPU
- Code run
- Stack ()

### Diferença entre Stack e Heap: cada thread tem uma stack (stack overflow). 
Variáveis locais são armazenadas na stack - só a thread acessa. 
Heap - área compartilhada - todas as threads acessam (através de referências na stack)

threads de plataforma (sistema operacional)
green thread - pouco usada
    focava em dispositivos com 1 núcleo e era simulada pela aplicação
thread virtual (corountines) - aplicação faz um controle 