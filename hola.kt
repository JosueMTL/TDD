import kotlin.math.ceil

fun main() {
    println(validateIdentification("0102015591"))
    println(validateCarPlate("ABC1234"))
}

fun validateAge(age: Int): Boolean {
    return age in 1..100
}

fun testAgeRange() {
    val result1 = validateAge(-1)
    val result2 = validateAge(1000)
    val expectedValue = false
    if (result1 == expectedValue && result2 == expectedValue) {
        println("Success")
    } else {
        println("Failure")
    }
}

fun validateIdentification(idNumber: String): Boolean {
    if (idNumber.length != 10) {
        return false
    }

    if (!idNumber.all { it.isDigit() }) {
        return false
    }

    val digits = idNumber.take(9).map { it.toString().toInt() }
    val verificationDigit = idNumber[9].toString().toInt()

    val sum = digits.mapIndexed { index, digit ->
        if (index % 2 == 0) {
            val result = digit * 2
            if (result > 9) result - 9 else result
        } else {
            digit
        }
    }.sum()

    val calculatedDigit = if (sum % 10 == 0) 0 else 10 - (sum % 10)

    return calculatedDigit == verificationDigit
}

fun validateCarPlate(plate: String): Boolean {
    if (plate.length != 7) {
        return false
    }

    val letters = plate.take(3)
    if (!letters.all { it.isLetter() }) {
        return false
    }

    val numbers = plate.takeLast(4)
    if (!numbers.all { it.isDigit() }) {
        return false
    }

    return true
}
