package com.github.xeniale.backendexamples.scala.examples


/**
 * Created by kshekhovtsova on 01.09.2015.
 */
class OfferingRestServiceImpl extends OfferingRestService {
  override def readPersonalizedOfferingsList(subscriberId: String, serviceId: String, detailed: Boolean): String = {
    val result = if (detailed) subscriberId + serviceId else "<result>no_details</result>"
    result
  }
}
