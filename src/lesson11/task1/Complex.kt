@file:Suppress("UNUSED_PARAMETER")

package lesson11.task1

/**
 * Фабричный метод для создания комплексного числа из строки вида x+yi
 */
fun Complex(s: String): Complex {
    val reim = s.split('+', '-')
    val im = reim[1].dropLast(1).toDouble()
    val re = reim[0].toDouble()
    return if (s[0] == '-') {
        val newS = s.drop(1)
        if ('-' in newS) Complex(-re, -im)
        else Complex(-re, im)
    } else if ('-' in s) Complex(re, -im)
    else Complex(re, im)
}

/**
 * Класс "комплексное число".
 *
 * Общая сложность задания -- лёгкая, общая ценность в баллах -- 8.
 * Объект класса -- комплексное число вида x+yi.
 * Про принципы работы с комплексными числами см. статью Википедии "Комплексное число".
 *
 * Аргументы конструктора -- вещественная и мнимая часть числа.
 */
class Complex(val re: Double, val im: Double) {

    /**
     * Конструктор из вещественного числа
     */
    constructor(x: Double) : this(x, 0.0)

    /**
     * Сложение.
     */
    operator fun plus(other: Complex): Complex = Complex(re + other.re, im + other.im)

    /**
     * Смена знака (у обеих частей числа)
     */
    operator fun unaryMinus(): Complex = Complex(-re, -im)

    /**
     * Вычитание
     */
    operator fun minus(other: Complex): Complex = Complex(re - other.re, im - other.im)

    /**
     * Умножение
     */
    operator fun times(other: Complex): Complex = Complex(re * other.re - im * other.im, im * other.re + re * other.im)

    /**
     * Деление
     */
    operator fun div(other: Complex): Complex = Complex(
        (re * other.re + im * other.im) / (other.im * other.im + other.re * other.re),
        (im * other.re - re * other.im) / (other.im * other.im + other.re * other.re)
    )

    /**
     * Сравнение на равенство
     */
    override fun equals(other: Any?): Boolean = other is Complex && other.re == re && other.im == im

    /**
     * Преобразование в строку
     */
    override fun toString(): String {
        var res = ""
        if (im < 0.0) res = "$re$im" + "i"
        if (im > 0.0) res = "$re+$im" + 'i'
        if (im == 0.0) res = "$re"
        return res
    }
}
