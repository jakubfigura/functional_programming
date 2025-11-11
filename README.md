# Functional programming / Programowanie funkcyjne :cyclone:
## Autor: Jakub Figura
[ENG] The repository contains solutions to assignments from the functional programming course at Jagiellonian University in the 2025/2026 academic year. <br/>
[PL] Repozytorium zawiera rozwiązania zadań z przedmiotu programowanie funkcyjne na Uniwersytecie Jagiellońskim z roku akademickiego 2025/2026.
<br/>

# Table of Contents

1. [Zestaw 1](#Zestaw-1)
2. [Zestaw 2](#Zestaw-2)
3. [Zestaw 3](#Zestaw-3)
<br/>

# Zestaw 1

✅ 3.0 Zaimplementuj funkcję isSorted, która sprawdza czy dana funkcja jest posortowana zgodnie z podaną funkcją porównawczą
<br/>
[Link do kodu](https://github.com/jakubfigura/functional_programming/blob/main/lab1/solutions/src/main/scala/SimpleApp.scala)
<br/>
![Demo](https://github.com/user-attachments/assets/af7db49c-339e-43ed-9104-ad9f91c9fa83)
<br/>
✅ 3.5 Zaimplementuj funkcję, która zsumuje liczby po wierszach z trzech list na wejściu; wykorzystaj funkcję sprowadzi funkcję z trzema argumentami, do funkcji z dwoma 
<br/>
[Link do kodu](https://github.com/jakubfigura/functional_programming/commit/0e9bda197a79a84c0045b6b107ebcb4f376b6c1d)
<br/>
![Demo](https://github.com/user-attachments/assets/2077db4a-cc09-4a85-aaf0-84e001ab69c7)
<br/>
✅ 4.0 Zaimplementuj funkcję setHead, która doda element na początku listy
<br/>
[Link do kodu](https://github.com/jakubfigura/functional_programming/commit/9bf5e5a7085305fc8bb8c9a5738ed46cc74d2628)
<br/>
![Demo](https://github.com/user-attachments/assets/8af25fec-9503-4f01-b6f4-dcbec35e7dec)
<br/>
✅ 4.5 Zaimplementuj funkcję append, która doda element we wskazanym miejscu w liście
<br/>
[Link do kodu](https://github.com/jakubfigura/functional_programming/commit/9d7fe70a82e411a54bbfe9c0d4adea5e5133763e)
<br/>
![Demo](https://github.com/user-attachments/assets/2da5bda7-fd90-4d57-97b8-b913963bc86c)
<br/>
✅ 5.0 Zaimplementuj funkcję, która policzy kwadrat liczb z dwóch list (po wierszach) za pomocą funkcji map oraz funkcji anonimowej
<br/>
[Link do kodu](https://github.com/jakubfigura/functional_programming/commit/8527b35bc078eef06657e5e980593cbe74df3188)
<br/>
![Demo](https://github.com/user-attachments/assets/2d093f98-6b73-4192-a297-7f3c7cfc1ca1)
<br/>

Kod: [Link kodu implementującego rozwiązania zadań 1.1 - 1.5](https://github.com/jakubfigura/functional_programming/blob/main/lab1/solutions/src/main/scala/SimpleApp.scala)

# Zestaw 2
Zadanie Ubuntu LTS

Stwórz obraz dockerowy, który bazuje na najnowszej wersji Ubuntu LTS oraz który zawiera paczki:

✅ Java 8
✅ Python 3
✅ Scala (dowolna wersja)
✅ sbt
✅ vim

Rozwiązanie: [Dockerfile](https://github.com/jakubfigura/functional_programming/commit/4a4d079b3ef9d8d30aab919c0bfa9c78ba44bca0)

[Dockerhub](https://hub.docker.com/r/jakubfigura/ubuntu_24_python_3-scala-sbt-vim_java_zadanie/tags)

![Demo](https://github.com/user-attachments/assets/f4b22ea9-7b0b-4117-a454-f51184b6624f)

# Zestaw 3
Zestaw 3 Scala
<br/>
[Implementasja List](https://github.com/jakubfigura/functional_programming/blob/main/lab3/solutions3/src/main/scala/LinkedList.scala)
<br/>
Do zadania 1 należy dodać kilka nowych endpointów, które:
<br/>
✅ 3.0 zwrócą wynik funkcji tail, która usuwa pierwszy element z listy (parameter); należy rozważyć przypadek Nil jako parametr
<br/>
[Link do kodu](https://github.com/jakubfigura/functional_programming/blob/main/lab3/solutions3/src/main/scala/zad3.scala)
<br/>
✅ 3.5 zwróci wynik funkcji drop, która usuwa n elementów z listy dwukierunkowej
<br/>
[Link do commita](https://github.com/jakubfigura/functional_programming/commit/977bbe339d818ed05452a30d8e511ab154084428)
<br/>
❌ 4.0 zwróci wynik funkcji dropWhile, która usuwa n elementów z listy dwukierunkowej, które spełniają warunek funkcji (parametr); należy wykorzystać podejście pattern match
<br/>
❌ 4.5 zwróci wynik funkcji foldLeft wykorzystując do tego companion object
<br/>
❌ 5.0 zwróci wynik funkcji concatenate na dwóch listach (parametry), która zwraca jedną listę
<br/>
![DEMO do zadania 3 pkt 1 i 2](https://github.com/user-attachments/assets/02eca4d3-3062-4b7a-be44-4ad50a188259)




