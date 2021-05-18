package com.github.diasandre.mochapie.model

import java.util.UUID

data class DataItem(val id: UUID, val responseBody: String, val status: Status)

data class Status(val label: String, val value: Int)
