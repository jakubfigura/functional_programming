package solutions3


case object Nil extends SinglyLinkedList[Nothing] with LinkedList[Nothing]
case class NextNode[+A](head: A, next: SinglyLinkedList[A]) extends SinglyLinkedList[A]

sealed trait SinglyLinkedList[+A]{


    def tail: SinglyLinkedList[A] = this match{
        case  Nil => Nil
        case  NextNode(_, next) => next
    }

    def convertToSeq: Seq[A] = this match{
        case Nil => Seq.empty
        case NextNode(head, next) => head +: next.convertToSeq
    }
    
}

object SinglyLinkedList{
    def apply[A](next: A*): SinglyLinkedList[A] = 
    if(next.isEmpty){
        Nil
    }
    else{
    NextNode(next.head, apply(next.tail*))
    }
}


case class Node[+A](head: A, next: LinkedList[A], previous: LinkedList[A]) extends LinkedList[A]

sealed trait LinkedList[+A]{
    def prev: LinkedList[A] = this match {
        case Nil => Nil
        case Node(_, _, previous) => previous
    }

    def listToSeq: Seq[A] = this match {
        case Nil => Seq.empty
        case Node(data, next, _) => data +: next.listToSeq
    }

    def dropN(n: Int): LinkedList[A] = {
        if(n <=0){
            this
        }
        else this match{
            case Nil => Nil
            case Node(_, next, _) => next.dropN(n-1) 
        }
    }

}

object LinkedList{
    def build[A](data: Seq[A], previous: LinkedList[A]) : LinkedList[A] = {
        if(data.isEmpty){
            Nil
        }else{
            val current = Node(data.head, Nil, previous)
            current.copy(next = build(data.tail, current))
        }
    }

    def apply[A](elements: A*): LinkedList[A] = build(elements, Nil)
}