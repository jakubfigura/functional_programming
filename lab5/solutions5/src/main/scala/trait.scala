package solutions5

trait MapReduce[In, Key, Value, Reduced]{
  def mapper(input: In): Seq[KeyValue[Key, Value]]
  def reducer(key: Key, values: Seq[Value]) : KeyValue[Key, Reduced]
}

case class KeyValue[K, V](key: K, value: V)