package solutions3


case object Nil extends SinglyLinkedList[Nothing]
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
