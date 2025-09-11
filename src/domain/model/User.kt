package domain.model

import java.time.LocalDateTime

data class User(
        val id: Int,
        val fullName: String,
        val passportData: String,
        val bornDate: LocalDateTime
)