package com.mkdvs.cpg

import org.springframework.stereotype.Component
import org.apache.camel.impl.*
import org.apache.camel.builder.*

@Component
class MoveFilesDataRoute extends RouteBuilder {
  String sourceData = "/Users/temoc/development/autotraining/data"

  @Override
  void configure() throws Exception {
    from("file:${sourceData}/in?noop=true")
      .log("Copying files from in....")
		  .to("file:${sourceData}/out")
      .log("Files copied to out....")
  }
}