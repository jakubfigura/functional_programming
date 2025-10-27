import ujson.{Obj, Value}
import ujson.Arr.from

object SimpleApp extends cask.MainRoutes{

    override def host: String = "0.0.0.0"

    //Recursive function for checking whether list is sorted.
    def isSorted(list: List[Int], index: Int = 0, key: String): Boolean = {
        if (key == ">"){
            if (index == list.length - 1) {
            true
            } else if (list(index) < list(index + 1)) {
            false
            } else {
            isSorted(list, index + 1, key)
            }
        }else{
            if (index == list.length - 1) {
            true
            } else if (list(index) > list(index + 1)) {
            false
            } else {
            isSorted(list, index + 1, key)
            }
        }
    }

    /*
    Excercise 1.1:
    Implement the isSorted function, which checks whether a given list
    is sorted according to the specified comparison function.
    */


    //Here is an endpoint for sending JSON file, to check whether list is sorted.
    @cask.postJson("/isSorted")
    def jsonEndpoint(data: List[Int], key: String): ujson.Value = {
        if (key != "<" && key != ">"){
            return Obj("ERROR" -> s"Wrong key provided. The expected key must be '<' or '>'" )
        }
        val sorted = isSorted(data, index = 0, key)
        Obj(
            "sorted" -> sorted,
            "message" -> (if (sorted) "List is sorted" else "List is NOT sorted")
        )
 
    }
    /*

    Excercise 1.2:

    Implement function, which sum numbers by rows. Use function which maps three arguments into two.

    */

    def sum(listA : List[Int], listB: List[Int]): List[Int] = {

    /*this method converts two list into sequence of tuples (a, b)

    if size of Lists is diffrent, sequences are filled with (a, 0) or (0, b) tuples.

    */

        listA.zipAll(listB, 0, 0).map {case (a, b) => a + b}

    }

    

    def sumLists(listA: List[Int], listB: List[Int], listC: List[Int]): List[Int] = {

        sum(sum(listA, listB), listC)

    }

    
    
    

    @cask.postJson("/sumLists")

    def jsonEndpoint(listA: List[Int], listB: List[Int], listC: List[Int]) = {

        val summedList = sumLists(listA, listB, listC)

        Obj(

        "The sum of provided lists = " -> summedList

        )

    }
    
    /*

    Excercise 1.3:

    Implement function setHead, which adds element at the begining of list.

    */


    @cask.postJson("/setHead")

    def jsonEndpoint(newHead: Int, list: List[Int]) = {

        val newList = newHead :: list

        Obj(

        "List with new head = " -> newList

        )

    }


    initialize()

}