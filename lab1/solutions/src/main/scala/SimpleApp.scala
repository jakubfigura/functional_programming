import ujson.{Obj, Value}

object SimpleApp extends cask.MainRoutes{

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

    override def host: String = "0.0.0.0"

    /*
    Excercise 1.1:
    Implement the isSorted function, which checks whether a given list
    is sorted according to the specified comparison function.
    */


    //Here is an endpoint for sending JSON file, to check wheter list is sorted
    @cask.postJson("/isSorted")
    def jsonEndpoint(data: List[Int], key: String) = {
        val sorted = isSorted(data, index = 0, key)
        Obj(
        "sorted" -> sorted,
        "message" -> (if (sorted) "List is sorted" else "List is NOT sorted")
        )
        
    }

    initialize()

}