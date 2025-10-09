package dev.cptlobster.appoint.scheduler.selector

import dev.cptlobster.appoint.scheduler.{AppointmentSchedule, Contact, Host, Slot}

import java.time.Instant
import scala.collection.mutable

/**
 * A strategy for selecting a host from a set of available hosts.
 */
trait HostSelector {
  /**
   * Select a host from the list of available hosts. This may use some parameters of the [[AppointmentSchedule]] to
   * determine the best host for the meeting.
   * @param hosts The set of available hosts.
   * @param slot The appointment slot that is being booked.
   * @param schedule The [[AppointmentSchedule]] to use for selection.
   * @return The selected host for this meeting.
   */
  def select(hosts: List[Host], slot: Slot, schedule: AppointmentSchedule): Host
}

/**
 * If multiple hosts are available, the Least Recently Used (LRU) Host Selector will select the host whose most recent
 * appointment is oldest. It will prioritize hosts that do not have any appointments scheduled; if all hosts have been
 * scheduled, then this will use LRU priority.
 */
case class LRUHostSelector() extends HostSelector {
  /**
   * Select a host from the list of available hosts. This uses the set of appointments in the appointment schedule to
   * determine which host should be selected.
   *
   * @param hosts    The set of available hosts.
   * @param slot     The appointment slot that is being booked.
   * @param schedule The [[AppointmentSchedule]] to use for selection.
   * @return The selected host for this meeting.
   */
  override def select(hosts: List[Host], slot: Slot, schedule: AppointmentSchedule): Host = {
    // Collect all appointments, and sort them by date (earliest to latest)
    val appointments = schedule.appointments.sortBy(_.start.getEpochSecond).reverse
    // Find the latest scheduled appointment for each host
    val hostMap: Map[Host, Instant] = hosts.flatMap(host => {
      appointments.find(a => a.host == host).map(a => host -> a.start)
    }).toMap
    val unscheduledHosts = hosts.diff(hostMap.keys.toList)
    // If any host is not scheduled, select the first one on the list.
    if (unscheduledHosts.isEmpty) {
      unscheduledHosts.head
    }
    else {
      // if all hosts are scheduled, select the one with the least recent appointment.
      val earliest = hostMap.values.min
      hostMap.filter((c, i) => i == earliest).head._1
    }
  }
}

/**
 * If multiple hosts are available, the Least Appointments host selector will select the host with the least total
 * appointments scheduled.
 */
case class LeastAppointmentsHostSelector() extends HostSelector {
  /**
   * Select a host from the list of available hosts. This uses the set of appointments in the appointment schedule to
   * * determine which host should be selected.
   *
   * @param hosts    The set of available hosts.
   * @param slot     The appointment slot that is being booked.
   * @param schedule The [[AppointmentSchedule]] to use for selection.
   * @return The selected host for this meeting.
   */
  override def select(hosts: List[Host], slot: Slot, schedule: AppointmentSchedule): Host = {
    val appointments = schedule.appointments
    val hostMap: Map[Host, Int] = hosts.map(host => {
      host -> appointments.count(a => a.host == host)
    }).toMap
    hostMap.minBy(_._2)._1
  }
}