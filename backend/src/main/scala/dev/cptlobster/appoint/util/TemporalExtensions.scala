package dev.cptlobster.appoint.util

import java.time.temporal.TemporalAmount
import java.time.{Duration, Instant}

extension (i: Instant) {
  def between(start: Instant, end: Instant): Boolean = i.isBefore(end) && i.isAfter(start)
  def between(start: Instant, length: Duration): Boolean = between(start, start.plus(length))
  def +(amount: TemporalAmount): Instant = i.plus(amount)
  def -(amount: TemporalAmount): Instant = i.minus(amount)
  def alignTo(interval: Duration): Instant = ???
}

object TemporalExtensions {
  implicit val InstantOrdering: Ordering[Instant] = Ordering.by(_.toEpochMilli)
  implicit val DurationOrdering: Ordering[Duration] = Ordering.by(_.toMillis)
}