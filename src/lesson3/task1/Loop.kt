@file:Suppress("UNUSED_PARAMETER", "DEPRECATED_IDENTITY_EQUALS")

package lesson3.task1

import lesson1.task1.sqr
import lesson4.task1.pow
import kotlin.math.*

// Урок 3: циклы
// Максимальное количество баллов = 9
// Рекомендуемое количество баллов = 7
// Вместе с предыдущими уроками = 16/21

/**
 * Пример
 *
 * Вычисление факториала
 */

fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая (2 балла)
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var count = 0
    var number = n
    do {
        count++
        number /= 10
    } while (abs(number) > 0)
    return count
}

/**
 * Простая (2 балла)
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int) =
    if (n <= 2) 1
    else {
        var num1 = 1
        var num2 = 1
        var num3 = 0
        for (i in 3..n) {
            num3 = num1 + num2
            num1 = num2
            num2 = num3
        }
        num3
    }

/**
 * Простая (2 балла)
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var i = 2
    if (n == 2 || isPrime(n)) return n
    while (i <= sqrt(n.toDouble())) {
        if (n % i == 0) return i
        i++
    }
    return 0
}

/**
 * Простая (2 балла)
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    if (isPrime(n)) return 1
    var max = 0
    for (i in n - 1 downTo sqrt(n.toDouble()).toInt()) {
        if (n % i == 0) {
            max = i
            return max
        }
    }
    return 0
}

/**
 * Простая (2 балла)
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var steps = 0
    var xn = x
    while (xn > 1) {
        steps++
        if (xn % 2 == 0) xn /= 2
        else xn = 3 * xn + 1
    }
    return steps
}

/**
 * Средняя (3 балла)
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var i = max(m, n)
    if (isPrime(n) && isPrime(m) && m != n) return m * n
    while (i % n != 0 || i % m != 0) {
        i += n
    }
    return i
}

/**
 * Средняя (3 балла)
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    for (i in 2..max(m, n)) {
        if (m % i == 0 && n % i == 0) return false
    }
    return true
}

/**
 * Средняя (3 балла)
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    for (i in 0..sqrt(n.toDouble()).toInt()) {
        if (sqr(i) in m..n) return true
    }
    return false
}

/**
 * Средняя (3 балла)
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var number = n
    var result = 0
    var k = digitNumber(n)
    var dec = pow(10, k - 1) //степень десятки в числе
    while (k > 0) {
        result += (number % 10) * dec
        dec /= 10
        number /= 10
        k -= 1
    }
    return result
}

/**
 * Средняя (3 балла)
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int) = revert(n) == n


/**
 * Средняя (3 балла)
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var number = n
    val digit = n % 10
    while (number > 0) {
        if (number % 10 != digit) return true
        number /= 10
    }
    return false
}

/**
 * Средняя (4 балла)
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double) =
    when {
        x % PI == 0.0 -> 0.0
        x % (PI / 2) == 0.0 -> {
            if (x % (2 * PI) > 0 && x % (2 * PI) < PI) 1.0
            else -1.0
        }
        else -> {
            var result = 0.0
            val x1 = x % (2 * PI)
            var a = eps
            var i = 1
            while (abs(a) >= eps) {
                a = x1.pow(2 * i - 1) / factorial(2 * i - 1)
                if (i % 2 != 0) result += a
                else result -= a
                i++
            }
            result
        }
    }

/**
 * Средняя (4 балла)
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double) =
    when {
        x % PI == 0.0 -> {
            if (x % (2 * PI) < PI / 2 && x % (2 * PI) > -PI / 2) 1.0
            else -1.0
        }
        x % (PI / 2) == 0.0 -> 0.0
        else -> {
            var result = 0.0
            val x1 = x % (2 * PI)
            var a = eps
            var i = 0
            while (abs(a) >= eps) {
                a = x1.pow(2 * i) / factorial(2 * i)
                if (i % 2 == 0) result += a
                else result -= a
                i++
            }
            result
        }
    }

/**
 * Сложная (4 балла)
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */

fun squareSequenceDigit(n: Int): Int {
    val result = mutableListOf(0)
    var a = 0 //номер цифры + 1
    var i = 1 //число, возводимое в квадрат
    while (n != a) {
        if (i > 4) {
            var current = revert(sqr(i))
            val k = digitNumber(current)
            for (j in a downTo a - k) {
                result.add(current % 10)
                current /= 10
                if (n == j) return result[j]
            }
            a += k
            i++
        } else {
            a += 1
            result.add(sqr(i))
            if (n == a) return result[a]
            i++
        }
    }
    return 1
}


/**
 * Сложная (5 баллов)
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var k = 2  //счетчик количества цифр в ряде чисел
    var x = 0  //текущее число
    var num1 = 1
    var num2 = 1
    var num3 = 0
    if (n <= 2) return 1
    else while (n != k) {
        num3 = num1 + num2
        x = revert(num3)
        while (x > 0) {
            k += 1
            if (k == n) return x % 10
            x /= 10
        }
        num1 = num2
        num2 = num3
    }
    return 0
}
