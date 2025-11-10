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
        /*
        Conversion seqToMyList helps to convert JSON data to my own data structure
        var myList = NextNode(1, NextNode(2, NextNode(3, Empty)))
        */
        def seqToMyList(seq: Seq[ujson.Value]): SinglyLinkedList[ujson.Value] = {
            if(seq.isEmpty) Nil
            else NextNode(seq.head, seqToMyList(seq.tail))
        }

        val myList = seqToMyList(data)
        val listTail = myList.tail

        Obj(
            "List tail: " -> ujson.Arr.from(listTail.convertToSeq),
        )
 
    }


    initialize()
    
}