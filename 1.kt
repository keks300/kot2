//  Создать программу, выполняющую следующий функционал:
//- запрашивается количество строк и столбцов для двумерного массива
//- вводится необходимое количество трехзначных чисел (числа могут повторяться)
//- подсчитывается количество различных цифр в полученном массиве
//- на консоль выводится двумерный массив из введенных чисел и количество различных цифр используемых в данном массиве
//Например, для массива
//100   951   101   950
//519   999   155   501
//510   911   144   554
//выведется результат: В массиве использовано 5 различных цифр


// Функция f принимает целое число a и возвращает множество (Set) уникальных цифр, входящих в число.
fun f(a: Int): Set<Int> {
    val d = HashSet<Int>() // Создаем HashSet для хранения уникальных цифр.
    var nbr = a
    while (nbr != 0) {
        val b = nbr % 10 // Получаем последнюю цифру числа.
        d.add(b) // Добавляем цифру в множество.
        nbr /= 10 // Убираем последнюю цифру из числа.
    }
    return d // Возвращаем множество уникальных цифр.
}

fun main(args: Array<String>) {
    // Вводим количество строк и столбцов для создания двумерного массива.
    println("Введите количество строк ")
    val x = readLine()?.toInt() ?: 0
    println("Введите количество столбцов ")
    val y = readLine()?.toInt() ?: 0

    var i = 0
    var j = 0

    // Создаем двумерный массив размером x на y для хранения трехзначных чисел.
    val arr = Array(x) { IntArray(y) }

    println("Введите массив из трехзначных чисел ")

    // Заполняем массив введенными значениями.
    for (k in arr) {
        i++
        for (c in k) {
            j++
            val z = readLine()?.toInt() ?: 0
            arr[i - 1][j - 1] = z
        }
        j = 0
    }

    println("Массив: ")
    // Выводим массив на экран.
    for (row in arr) {
        println(row.contentToString())
    }

    // Создаем множество для хранения уникальных цифр из трехзначных чисел в массиве.
    val aSet = HashSet<Int>()
    for (k in arr) {
        for (a in k) {
            val d = f(a) // Получаем множество уникальных цифр для каждого числа.
            aSet.addAll(d) // Добавляем все уникальные цифры в общее множество.
        }
    }

    // Выводим количество различных цифр.
    val count = aSet.size
    println("Количество различных цифр: $count")
}