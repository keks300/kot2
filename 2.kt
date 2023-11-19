// Имеется массив из символов русского алфавита (все 33 символа, могут быть не по порядку).
// Символы алфавита нумеруются от 1 до 33. Каждое число используется только один раз.
// Сообщение шифруется с помощью ключевого слова, задаваемого пользователем.
// Номер символа ключевого слова показывает, на сколько нужно сдвинуться по массиву из символов русского алфавита.
// Составить программу шифровки и дешифровки строкового выражения (то есть программа спрашивает - зашифровать или расшифровать текст и ключевое слово).
// Первый массив считать закольцованным. Регистр букв не имеет значения. Например:
//
// А	Б	В	Г	Д	Е	Ё	Ж	З	И	Й	К	Л	М	Н	О	П	Р	С	Т	У	Ф	Х	Ц	Ч	Ш	Щ	Ь	Ы	Ъ	Э	Ю	Я
// 21	13	4	20	22	1	25	12	24	14	2	28	9	23	3	29	6	16	15	11	26	5	30	27	8	18	10	33	31	32	19	7	17
// Ключевое слово - ПОЛЕ
// Исходный текст - СООБЩЕНИЕ
// Зашифрованный текст - АЁФИРХЖСЮ

fun main() {
// Определение списка букв алфавита
val alphabet = listOf(
    'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О',
    'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ь', 'Ы', 'Ъ', 'Э', 'Ю', 'Я'
)

// Определение отображения букв алфавита в их числовые значения
val alphabetNum = mapOf(
    'А' to 21, 'Б' to 13, 'В' to 4, 'Г' to 20, 'Д' to 22, 'Е' to 1, 'Ё' to 25, 'Ж' to 12,
    'З' to 24, 'И' to 14, 'Й' to 2, 'К' to 28, 'Л' to 9, 'М' to 23, 'Н' to 3, 'О' to 29,
    'П' to 6, 'Р' to 16, 'С' to 15, 'Т' to 11, 'У' to 26, 'Ф' to 5, 'Х' to 30, 'Ц' to 27,
    'Ч' to 8, 'Ш' to 18, 'Щ' to 10, 'Ь' to 33, 'Ы' to 31, 'Ъ' to 32, 'Э' to 19, 'Ю' to 7, 'Я' to 17
)

// Бесконечный цикл для ввода пользователем действий
while (true) {
    // Вывод меню
    println("Выберите действие:")
    println("1. Зашифровать текст")
    println("2. Расшифровать текст")
    println("3. Выйти")

    // Обработка выбора пользователя
    when (readLine()?.toIntOrNull() ?: 0) {
        1 -> {
            // Шифрование текста
            print("Введите текст для шифровки: ")
            val text = readLine()?.toUpperCase()
            print("Введите ключевое слово: ")
            val key = readLine()?.toUpperCase()

            if (text != null && key != null) {
                val encryptedText = encrypt(text, key, alphabet, alphabetNum)
                println("Зашифрованный текст: $encryptedText")
            } else {
                println("Неверный ввод.")
            }
        }
        2 -> {
            // Расшифрование текста
            print("Введите текст для расшифровки: ")
            val textToDecrypt = readLine()?.toUpperCase()
            print("Введите ключевое слово: ")
            val decryptionKey = readLine()?.toUpperCase()

            if (textToDecrypt != null && decryptionKey != null) {
                val decryptedText = decrypt(textToDecrypt, decryptionKey, alphabet, alphabetNum)
                println("Расшифрованный текст: $decryptedText")
            } else {
                println("Неверный ввод.")
            }
        }
        3 -> {
            // Завершение программы
            println("Программа завершена.")
            return
        }
        else -> {
            // В случае некорректного выбора
            println("Неверный выбор. Попробуйте снова.")
        }
    }
}

}

// Функция для шифрования текста
fun encrypt(text: String, key: String, alphabet: List<Char>, alphabetNum: Map<Char, Int>): String {
    return processText(text, key, alphabet, alphabetNum, true)
}

// Функция для расшифрования текста
fun decrypt(text: String, key: String, alphabet: List<Char>, alphabetNum: Map<Char, Int>): String {
    return processText(text, key, alphabet, alphabetNum, false)
}

// Основная функция для обработки текста
fun processText(text: String, key: String, alphabet: List<Char>, alphabetNum: Map<Char, Int>, encrypt: Boolean): String {
    val result = StringBuilder()
    // Преобразование ключа в список числовых значений
    val keyID = key.map { alphabetNum[it] ?: 0 }

    // Обработка каждого символа в тексте
    for (i in text.indices) {
        val char = text[i].uppercaseChar()//Верхний регистор
        if (char in alphabet) {
            // Получение числового значения текущего символа и вычисление сдвига
            val alphabetIndex = alphabetNum[char] ?: 0
            val shift = keyID[i % key.length]//Позволяет сдвигать символы в шировки и расцифровке
            // В зависимости от шифровки или расшифровки выбирается символ из алфавита если шифруется, то вправа если расшифровывается, то влево
            val newIndex = if (encrypt) (alphabetNum.entries.first { it.value == (alphabetIndex + shift) % alphabet.size }.key)
            else (alphabetNum.entries.first { it.value == (alphabetIndex - shift + alphabet.size) % alphabet.size }.key)
            // Добавление нового символа к результату
            result.append(newIndex)
        } else {
            // Добавление неалфавитных символов к результату
            result.append(char)
        }
    }

    // Возвращение зашифрованного или расшифрованного текста
    return result.toString()
}
