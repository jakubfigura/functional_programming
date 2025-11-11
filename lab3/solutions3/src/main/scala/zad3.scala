package solutions3

import ujson.{Obj, Value}
import ujson.Arr.from

object zad3 extends cask.MainRoutes{

    /*
    3.0 zwrócą wynik funkcji tail, która usuwa pierwszy element z listy
    (parameter); należy rozważyć przypadek Nil jako parametr 
     */

    @cask.postJson("/tail")
    def jsonEndpoint(data: Seq[ujson.Value]): ujson.Value = {
        

        val myList = SinglyLinkedList(data*)
        val listTail = myList.tail

        Obj(
            "List tail: " -> ujson.Arr.from(listTail.convertToSeq),
        )
 
    }



    initialize()
    
}