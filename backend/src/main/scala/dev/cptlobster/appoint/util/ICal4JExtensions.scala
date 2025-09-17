package dev.cptlobster.appoint.util

import net.fortuna.ical4j.model.{FluentComponent, FluentProperty, Parameter, Property}

// This module contains extension functions for ical4j's fluent API, to make it more Scala-like. Functions are added to
// make fluent objects behave like Scala collections, and also for more convenient type handling when running
// getFluentTarget. This should hopefully be more concise, and also provides some other helpful methods (i.e. :++ for
// adding multiple properties or parameters at once).

extension (f: FluentComponent) {
  /** Convenience binding for [[FluentComponent.withProperty()]] */
  def :+(prop: Property): FluentComponent = f.withProperty(prop)
  /** Convenience binding for adding multiple properties using [[FluentComponent.withProperty()]] */
  def :++(props: List[Property]): FluentComponent = props.foldLeft(f)((f, prop) => {
    f.withProperty(prop.getFluentTarget)
  })
  /** Convenience binding for [[FluentComponent.getFluentTarget()]], which also handles type conversions to the intended
   * type. */
  def as[T]: T = f.getFluentTarget.asInstanceOf[T]
}

extension (f: FluentProperty) {
  /** Convenience binding for [[FluentProperty.withParameter()]] */
  def :+(param: Parameter): FluentProperty = f.withParameter(param)
  /** Convenience binding for adding multiple parameters using [[FluentProperty.withParameter()]] */
  def :++(params: List[Parameter]): FluentProperty = params.foldLeft(f)((f, param) => {
    f.withParameter(param)
  })
  /** Convenience binding for [[FluentProperty.getFluentTarget()]], which also handles type conversions to the intended
   * type. */
  def as[T]: T = f.getFluentTarget.asInstanceOf[T]
}