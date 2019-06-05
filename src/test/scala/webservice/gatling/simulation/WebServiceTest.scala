package webservice.gatling.simulation

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

import scala.concurrent.duration._

class WebServiceTest extends Simulation {

  val rampUpTimeSecs = 5
  val testTimeSecs = 500
  val noOfUsers = 1000
  val minWaitMs = 1000 milliseconds
  val maxWaitMs = 3000 milliseconds

  val baseURL = "http://localhost:8080"
  val baseName = "With-graal"
  val requestName = baseName + "-request"
  val scenarioName = baseName + "-scenario"
  val URI = "/product"

  val httpProtocol = http
    .baseUrl(baseURL)
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")

  private val getProducts: HttpRequestBuilder =
    http("Get all products")
      .get(URI)
      .check(status is 200)

  private val getProductById: HttpRequestBuilder =
    http("Get product by id")
      .get("/product/3")
      .check(status is 200)

  private val generateGreeting: HttpRequestBuilder =
    http("Get greeting")
      .get("/badabin")
      .check(status is 200)
      .check(jsonPath("$..content").ofType[String].exists)


  val scn = scenario(scenarioName)
    .exec(getProducts)
    .exec(getProductById)
    .exec(generateGreeting)

    setUp(
      scn.inject(rampUsers(noOfUsers) during (rampUpTimeSecs))
    ).protocols(httpProtocol)
}
