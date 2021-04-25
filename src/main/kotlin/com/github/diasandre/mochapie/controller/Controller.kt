package com.github.diasandre.mochapie.controller

import com.github.diasandre.invoke
import com.github.diasandre.mochapie.model.StoredData
import com.github.diasandre.mochapie.service.DataService
import com.github.diasandre.mochapie.service.ValidateService
import com.github.diasandre.mochapie.util.getStatus
import com.github.diasandre.mochapie.util.setResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.servlet.http.HttpServletResponse

@RestController

class Controller(private val service: DataService, private val validateService: ValidateService) {

    @PostMapping("/")
    fun save(@RequestBody data: StoredData?) = service.save(data)

    @GetMapping("/{uuid}")
    fun get(
        @PathVariable("uuid") uuid: UUID,
        @RequestHeader headers: Map<String, String>,
        response: HttpServletResponse
    ): String? {
        validateService.isValidHeader(headers)
        return headers.getStatus()
            .also(response::setResponse)
            .let((service::getByStatus)(uuid))
    }

    @GetMapping("/{uuid}/edit")
    fun getAll(@PathVariable("uuid") uuid: UUID): StoredData? = service.get(uuid)
}