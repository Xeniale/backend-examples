package com.github.xeniale.backendexamples.scala.examples

import javax.ws.rs.core.MediaType
import javax.ws.rs._


/**
 * Created by kshekhovtsova on 31.08.2015.
 */
@Path("/offering")
trait
OfferingRestService {

  @GET
  @Path("{subscriberId}/offerings")
  @Produces(Array(MediaType.APPLICATION_XML))
  def readPersonalizedOfferingsList(@PathParam("subscriberId") subscriberId : String , @QueryParam("serviceId") serviceId : String, @QueryParam("detailed") detailed : Boolean) : String
}

