package dev.cptlobster.appoint.scheduler.builder.overlay

import net.fortuna.ical4j.model.component.VEvent

import scala.util.matching.Regex

enum ICalEventFilter {
  case Exists
  case NameExact(str: String)
  case NameContains(str: String)
  case NameStartsWith(str: String)
  case NameEndsWith(str: String)
  case NameMatches(pat: String)

  def matches(event: VEvent): Boolean = {
    this match {
      case Exists => true
      case NameExact(str) => event.getName == str
      case NameContains(str) => event.getName.contains(str)
      case NameStartsWith(str) => event.getName.startsWith(str)
      case NameEndsWith(str) => event.getName.endsWith(str)
      case NameMatches(pat) => pat.r.matches(event.getName)
    }
  }
}