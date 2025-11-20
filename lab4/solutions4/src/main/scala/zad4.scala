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
import scala.math._




object zad4 extends cask.MainRoutes{

    override def host: String = "0.0.0.0"
    override def port: Int = 8080

    //Zadanie 1
    def append(list: List[ujson.Value], element: ujson.Value, index: Int): List[ujson.Value] = {

        val (first_part, second_part) = list.splitAt(index)

        first_part ::: (element :: second_part)
    }

    


    @cask.postJson("/append")
    def jsonEndpoint(list: Option[Seq[ujson.Value]] = None, element: Option[ujson.Value] = None, index: Option[Int] = None) = {

        val optional_list = list.getOrElse(Seq.empty)
        val optional_index = index.getOrElse(optional_list.length)
        val dummy_element = element.getOrElse(ujson.Str("Send your data :D"))

            
        if (optional_index < 0 || optional_index > optional_list.length){

            Obj(

            "ERROR" -> s"Index $index out of bounds. Choose index from: 0...${optional_list.length}"

            )
        }else{

            val newList = append(optional_list.toList, dummy_element, optional_index)

            Obj(

            "List with new element" -> newList

            )

        }


    }

    

    def variance(list: List[ujson.Value]): ujson.Value = {

        val data = list.collect {
            case ujson.Num(n) => n
        }
        val n : Int = data.length

        if(n == 0){
            return Obj(
            "ERROR" -> "The list is empty"
            )
        }
        val mean = data.sum / n
        val result = data.map(num => math.pow((num - mean),2)).sum / n

        Obj(
            "Variance" -> result
        )



    }
    
    @cask.postJson("/variance")
    def varianceEndpoint(list: Option[Seq[ujson.Value]] = None) = {

        list match{
            case Some(seq) => variance(seq.toList)
            case None => Obj("ERROR" -> "Please send the data")

        }

    }

    @cask.postJson("/merge")
    def mergeEndpoint(listA: Option[Seq[ujson.Value]] = None, listB: Option[Seq[ujson.Value]] = None): Option[ujson.Obj] = {
        
        (listA, listB) match {
            case (Some(a), Some(b)) => Some(Obj("Merged lists" -> (a ++ b).toList))
            case _ => None
        }

    }

    


    initialize()
    
}