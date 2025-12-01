package solutions5

import ujson.{Obj, Value}
import ujson.Arr.from
import scala.math._



class ExcmapReduce extends MapReduce[List[Int], Int, Int, Int] {
  
  def mapper(input: List[Int]): Seq[KeyValue[Int, Int]] = {
    input.map(i => KeyValue(i, 1))
  }

  def reducer(key: Int, values: Seq[Int]): KeyValue[Int, Int] = {
    KeyValue(key, values.sum)
  }    
  
}


object zad5 extends cask.MainRoutes{

    override def host: String = "0.0.0.0"
    override def port: Int = 8080

    
    /*3.0 zwróci słownik z liczbą powtarzających się liczb za pomocą funkcji
    mapreduce dla list z liczbami*/

    @cask.postJson("/zad1")
    def Exc1EndPoint(list: Seq[ujson.Value]) = {
        val numList = list.map(_.num.toInt).toList
        val emr = ExcmapReduce()
        val mapped = emr.mapper(numList)
        val reduced = mapped.groupBy(_.key).map { case (k, kvs) =>
            emr.reducer(k, kvs.map(_.value))
        }
        val result = reduced.map { case KeyValue(k, v) => k -> v }.toMap
        println(result)
        

        Obj(
            "Słownik" -> result.map{case (k, v) => (k.toString, ujson.Num(v))}
        )
        
    }

    @cask.postJson("/zad2")
    def Exc2EndPoint(list: Seq[ujson.Value]) = {
        val numList = list.map(_.num.toInt).toList
        val mapped = Exc1mapReduce.mapper(numList)
        val reduced = mapped.groupBy(_.key).map { case (k, kvs) =>
            Exc1mapReduce.reducer(k, kvs.map(_.value))
        }
        val result = reduced.map { case KeyValue(k, v) => k -> v }.toMap
        println(result)
        

        Obj(
            "Słownik^3" -> result.map{case (k, v) => (k.toString, ujson.Num(pow(v,3)))}
        )
        
    }



    
    initialize()
    
}