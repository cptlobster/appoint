package dev.cptlobster.appoint.util

import java.time.{Duration, Instant}
import TemporalExtensions.*

import scala.math.Ordered.orderingToOrdered

case class TemporalRangeIterator(start: Instant, end: Instant, by: Duration) extends Iterator[Instant] {
  private var current: Instant = start

  def hasNext(): Boolean = (current) <= end
  def next(): Instant = {
    val response = current
    current = current + by
    response
  }
}
