package solutions3

import ujson.{Obj, Value}
import ujson.Arr.from

object zad3 extends cask.MainRoutes{

    override def host: String = "0.0.0.0"
    override def port: Int = 8080

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

    /*

    3.5 zwróci wynik funkcji drop, która usuwa n elementów z listy

    dwukierunkowej
    
    */

    @cask.postJson("/dropN")
    def jsonEndpoint(data: Seq[ujson.Value], toDrop: Int): ujson.Value = {

        val myList = LinkedList(data*)
        val newList = myList.dropN(toDrop)

        Obj(
            "Recieved list: "-> ujson.Arr.from(myList.listToSeq),
            "List after drop: " -> ujson.Arr.from(newList.listToSeq)
        )


    }
    

    initialize()
    
}