package com.olivergg.starttabs.controller

import scala.concurrent.Future
import scala.scalajs.concurrent.JSExecutionContext.Implicits.runNow
import scala.scalajs.js
import scala.scalajs.js.JSON
import scala.scalajs.js.annotation.JSExportAll
import scala.util.Failure
import scala.util.Success
import scala.util.Try
import com.greencatsoft.angularjs.Controller
import com.greencatsoft.angularjs.core.HttpService
import com.greencatsoft.angularjs.core.Scope
import com.greencatsoft.angularjs.inject
import com.olivergg.starttabs.dto.IPAddress
import prickle._
import com.olivergg.starttabs.service.BetterHttpService
import com.greencatsoft.angularjs.injectable
import com.greencatsoft.angularjs.AbstractController

@injectable("AccountCtrl")
class AccountController(scope: AccountScope, betterHttp: BetterHttpService) extends AbstractController[AccountScope](scope) {

  println("init AccountController")
  scope.ip = IPAddress("Unknown")
  val ipAddressFut: Future[IPAddress] = betterHttp.getJsonAndUnpickle[IPAddress]("http://ip.jsontest.com/")
  ipAddressFut.onSuccess {
    case ip => println(s"response from server IP = $ip"); scope.ip = ip
  }

}

trait AccountScope extends Scope {
  var ip: IPAddress = js.native
}

