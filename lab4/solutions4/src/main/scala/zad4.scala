/*

AUTOR: Jakub Figura

Zadanie 4 Typ opcjonalny

Należy wykorzystać typ opcjonalny i rozszerzyć endpointy o endpoint,
który:
3.0 zwróci taki sam wynik jak zad.1 4.5 ale wykorzysta typ opcjonalny
3.5 zwroci wariancję z listy; do implementacji wykorzystać należy typ
opcjonalny
4.0 zwróci listę (option), która jest wynikiem połączenia dwóch list
(option); w przypadku gdy jedna z list jest None, funkcja powinna
zwrócić None
4.5 zwróci listę za pomocą metody mojeMap[A,B,C](a: Option[A], b:
Option[B])(f:(A,B)=>C):Option[C]; należy wykorzystać metodę flatMap
oraz map
5.0 zwróci taki średnią (mean) z listy zwracając Either
*/


package solutions4

import ujson.{Obj, Value}
import ujson.Arr.from




object zad4 extends cask.MainRoutes{

    override def host: String = "0.0.0.0"
    override def port: Int = 8080




    


    initialize()
    
}