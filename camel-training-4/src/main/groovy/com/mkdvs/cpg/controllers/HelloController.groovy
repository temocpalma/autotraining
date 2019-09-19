package com.mkdvs.cpg

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.beans.factory.annotation.Autowired
import org.apache.camel.ProducerTemplate

@RestController
class HelloController {

	@Autowired
  ProducerTemplate producerTemplate

	@RequestMapping("/")
	String index() {
		"Greetings from Spring Boot!"
	}

}
