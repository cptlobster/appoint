import dev.cptlobster.appoint._
import org.scalatra._
import jakarta.servlet.ServletContext

class ScalatraBootstrap
  extends LifeCycle {
  implicit val swagger: AppointSwagger = new AppointSwagger

  override def init(context: ServletContext): Unit = {
    context.mount(new AppointmentServlet, "/api/appointments/*", "appointments")
    context.mount(new ScheduleServlet, "/api/schedules/*", "schedules")
    context.mount(new ResourcesApp, "/apidoc")
  }

  override def destroy(context: ServletContext): Unit = {

  }
}
