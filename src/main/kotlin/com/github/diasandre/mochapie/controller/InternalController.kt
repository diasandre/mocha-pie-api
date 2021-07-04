package com.github.diasandre.mochapie.controller

import com.github.diasandre.mochapie.model.DataDTO
import com.github.diasandre.mochapie.service.DataService
import com.github.diasandre.mochapie.util.toUUID
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class InternalController(private val service: DataService) {

    @PostMapping("/")
    fun save(@RequestBody data: DataDTO) = service.save(data)

    @GetMapping("/{uuid}/edit")
    fun getAll(@PathVariable("uuid") uuid: String): DataDTO? = service.get(uuid.toUUID())
}
