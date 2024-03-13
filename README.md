# Algoritmo RSA

Algoritmo implementado no final do terceiro período e refatorado.
Abaixo darei a explicação suficiente para o entendimento do algoritmo.

### Devemos saber inicialmente:

 - Teorema de Fermat: Dados dois números 'a' e 'p' inteiros com 'p' primo, então:
   ![image](https://github.com/CostaJoseff/Algoritmo-RSA/assets/97255656/573aa297-16ca-4b97-a508-8e53791aa297)

 - Função totiente de Euler ( φ() ): Sejam 'p' e 'q' dois números primos, então:
   ![image](https://github.com/CostaJoseff/Algoritmo-RSA/assets/97255656/bc15d27e-f729-4701-ae1e-1cfb2deeba08)


### Números utilizados

 - p, q : Dois números primos distintos (de preferência com muitos algarismos)
 - n = pq: Multiplicação entre 'p' e 'q'
 - φ(n) = (p - 1)(q - 1) : Função totiente de 'n' seguindo a função de Euler
 - d : Número aleatório de forma que 'd' e 'φ(n)' sejam primos entre si
 - e : inverso multiplicativo de 'd' na congruência 'd ≡ 1 (mod φ(n))' ou seja, d*e ≡ 1 (mod φ(n))

### Explicação do funcionamento (Prova Matemática )

Vamos iniciar a contrução com

![image](https://github.com/CostaJoseff/Algoritmo-RSA/assets/97255656/8eefabcd-62e8-4ad1-98c6-b3865533f09f)

Definindo 'a' e 'k' números quaisquer e 'p','q' e 'n' como definidos acima (as letras 'a' e 'k' farão sentido)

Pela propriedade de potência (Potência de potência) obtemos (p-1)(q-1)k e pela função totiente de Euler chegamos em

![image](https://github.com/CostaJoseff/Algoritmo-RSA/assets/97255656/7efec391-aeb1-4d53-b077-2ff89587d037)

Multiplicando os 2 lados da congruência por 'a' obtemos

![image](https://github.com/CostaJoseff/Algoritmo-RSA/assets/97255656/ecfa89ca-0541-493d-bb60-cb6cece255f3)

Lembrando que qualquer número que não apresente potência contém implicitamente 1 como potência

Utilizando a propriedade do protudo de potência de mesma base do lado esquerdo da congruência chegamos em 

![image](https://github.com/CostaJoseff/Algoritmo-RSA/assets/97255656/561f1fff-1533-4ca2-af55-0626ef93406c)

A congruência acima nos mostra que o expoente 'φ(n)k + 1' nos retorna sua base como resto na divisão por 'n' podemos utilizar isso para transformar um texto em um número 'a' qualquer e após o processo listado acima, conseguimos reverter o número de volta para 'a'

No algoritmo deste repositório transformamos um texto em um número 'a' e utilizamos o valor 'e' citado acima como expoente da mensagem. 'e' e 'n' são os números públicos.
De posse do número privado 'd' e do número 'n' recebemos o a^e e efetuamos uma nova exponenciação, agora com o número 'd'. Temos então (a^e)^d e novamente utilizando potência de potência obtemos a^(ed).

Como mencionado anteriormente 'e' é o inverso multiplicativo de 'd' na congruência mod φ(n) ou seja quando divide 'ed' por φ(n) obtem-se 1 como resto da divisão. Temos então 'ed ≡ 1 (mod φ(n))'.

ed ≡ 1 (mod φ(n)) é o mesmo que dizer 'k' vezes φ(n) somado com o resto resulta em 'ed', ou seja 'ed = φ(n)k + 1'.

Chegamos na igualdade que precisamos 'de' ou 'ed' é o mesmo que 'φ(n)k + 1' então se a imagem acima mostrou

![image](https://github.com/CostaJoseff/Algoritmo-RSA/assets/97255656/fd821f45-8a5c-4cf3-b8ce-ee1468c93883)

Então, pela igualdade mostrada agora a pouco, o valor 'ed' como expoente do 'a' também resulta sua base 'a' na congruência mod 'n' comprovando que os valores utilizados na criptografia podem alterar um valor qualquer 'a' e após obter o resto da divisão por 'n' conseguimos o valor 'a' novamente.

