package com.github.diasandre.mochapie.controller

import com.github.diasandre.mochapie.service.DataService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController

class Controller(private val service: DataService) {

    @PostMapping("/")
    fun save(@RequestBody value: String) = service.save(value)

    @GetMapping("/{uuid}")
    fun get(@PathVariable("uuid") uuid: UUID) = service.get(uuid)
}