@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson7.task1

import java.io.BufferedWriter
import java.io.File
import java.util.*

// Урок 7: работа с файлами
// Урок интегральный, поэтому его задачи имеют сильно увеличенную стоимость
// Максимальное количество баллов = 55
// Рекомендуемое количество баллов = 20
// Вместе с предыдущими уроками (пять лучших, 3-7) = 55/103

/**
 * Пример
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * Вывести его в выходной файл с именем outputName, выровняв по левому краю,
 * чтобы длина каждой строки не превосходила lineLength.
 * Слова в слишком длинных строках следует переносить на следующую строку.
 * Слишком короткие строки следует дополнять словами из следующей строки.
 * Пустые строки во входном файле обозначают конец абзаца,
 * их следует сохранить и в выходном файле
 */
fun alignFile(inputName: String, lineLength: Int, outputName: String) {
    val writer = File(outputName).bufferedWriter()
    var currentLineLength = 0
    fun append(word: String) {
        if (currentLineLength > 0) {
            if (word.length + currentLineLength >= lineLength) {
                writer.newLine()
                currentLineLength = 0
            } else {
                writer.write(" ")
                currentLineLength++
            }
        }
        writer.write(word)
        currentLineLength += word.length
    }
    for (line in File(inputName).readLines()) {
        if (line.isEmpty()) {
            writer.newLine()
            if (currentLineLength > 0) {
                writer.newLine()
                currentLineLength = 0
            }
            continue
        }
        for (word in line.split(Regex("\\s+"))) {
            append(word)
        }
    }
    writer.close()
}


/**
 * Простая (8 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * Некоторые его строки помечены на удаление первым символом _ (подчёркивание).
 * Перенести в выходной файл с именем outputName все строки входного файла, убрав при этом помеченные на удаление.
 * Все остальные строки должны быть перенесены без изменений, включая пустые строки.
 * Подчёркивание в середине и/или в конце строк значения не имеет.
 */
fun deleteMarked(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя (14 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * На вход подаётся список строк substrings.
 * Вернуть ассоциативный массив с числом вхождений каждой из строк в текст.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 */
fun countSubstrings(inputName: String, substrings: List<String>): Map<String, Int> {
    TODO()
}

/**
 * Средняя (12 баллов)
 *
 * В русском языке, как правило, после букв Ж, Ч, Ш, Щ пишется И, А, У, а не Ы, Я, Ю.
 * Во входном файле с именем inputName содержится некоторый текст на русском языке.
 * Проверить текст во входном файле на соблюдение данного правила и вывести в выходной
 * файл outputName текст с исправленными ошибками.
 *
 * Регистр заменённых букв следует сохранять.
 *
 * Исключения (жюри, брошюра, парашют) в рамках данного задания обрабатывать не нужно
 *
 */
fun sibilants(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя (15 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по центру
 * относительно самой длинной строки.
 *
 * Выравнивание следует производить путём добавления пробелов в начало строки.
 *
 *
 * Следующие правила должны быть выполнены:
 * 1) Пробелы в начале и в конце всех строк не следует сохранять.
 * 2) В случае невозможности выравнивания строго по центру, строка должна быть сдвинута в ЛЕВУЮ сторону
 * 3) Пустые строки не являются особым случаем, их тоже следует выравнивать
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых)
 *
 */
fun numOfSpaces(line: String): Int {
    var num = 0
    for (letter in line) {
        if (letter.isWhitespace()) {
            num++
        } else break
    }
    return num
}

fun numOfSpacesBack(line: String): Int {
    var num = 0
    for (i in line.length - 1 downTo 0) {
        if (line[i].isWhitespace()) {
            num++
        } else break
    }
    return num
}

fun centerFile(inputName: String, outputName: String) {
    File(outputName).bufferedWriter().use {
        var max = 0
        for (line in File(inputName).readLines()) {
            val currentLength = line.length - numOfSpaces(line) - numOfSpacesBack(line)
            if (currentLength > max) {
                max = currentLength
            }
        }

        for (line in File(inputName).readLines()) {
            val numOfSpaces = numOfSpaces(line)
            val gap = kotlin.math.abs(
                (max - line.length + numOfSpacesBack(line)
                        + numOfSpaces) / 2
            ) - numOfSpaces

            for (i in 0 until gap) {
                it.write(" ")
            }
            it.write(line)
            it.newLine()
        }
    }
}

/**
 * Сложная (20 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по левому и правому краю относительно
 * самой длинной строки.
 * Выравнивание производить, вставляя дополнительные пробелы между словами: равномерно по всей строке
 *
 * Слова внутри строки отделяются друг от друга одним или более пробелом.
 *
 * Следующие правила должны быть выполнены:
 * 1) Каждая строка входного и выходного файла не должна начинаться или заканчиваться пробелом.
 * 2) Пустые строки или строки из пробелов трансформируются в пустые строки без пробелов.
 * 3) Строки из одного слова выводятся без пробелов.
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых).
 *
 * Равномерность определяется следующими формальными правилами:
 * 5) Число пробелов между каждыми двумя парами соседних слов не должно отличаться более, чем на 1.
 * 6) Число пробелов между более левой парой соседних слов должно быть больше или равно числу пробелов
 *    между более правой парой соседних слов.
 *
 * Следует учесть, что входной файл может содержать последовательности из нескольких пробелов  между слвоами. Такие
 * последовательности следует учитывать при выравнивании и при необходимости избавляться от лишних пробелов.
 * Из этого следуют следующие правила:
 * 7) В самой длинной строке каждая пара соседних слов должна быть отделена В ТОЧНОСТИ одним пробелом
 * 8) Если входной файл удовлетворяет требованиям 1-7, то он должен быть в точности идентичен выходному файлу
 */
fun alignFileByWidth(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя (14 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * Вернуть ассоциативный массив, содержащий 20 наиболее часто встречающихся слов с их количеством.
 * Если в тексте менее 20 различных слов, вернуть все слова.
 * Вернуть ассоциативный массив с числом слов больше 20, если 20-е, 21-е, ..., последнее слова
 * имеют одинаковое количество вхождений (см. также тест файла input/onegin.txt).
 *
 * Словом считается непрерывная последовательность из букв (кириллических,
 * либо латинских, без знаков препинания и цифр).
 * Цифры, пробелы, знаки препинания считаются разделителями слов:
 * Привет, привет42, привет!!! -привет?!
 * ^ В этой строчке слово привет встречается 4 раза.
 *
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 * Ключи в ассоциативном массиве должны быть в нижнем регистре.
 *
 */
fun top20Words(inputName: String): Map<String, Int> {
    TODO()
}

/**
 * Средняя (14 баллов)
 *
 * Реализовать транслитерацию текста из входного файла в выходной файл посредством динамически задаваемых правил.

 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * В ассоциативном массиве dictionary содержится словарь, в котором некоторым символам
 * ставится в соответствие строчка из символов, например
 * mapOf('з' to "zz", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "yy", '!' to "!!!")
 *
 * Необходимо вывести в итоговый файл с именем outputName
 * содержимое текста с заменой всех символов из словаря на соответствующие им строки.
 *
 * При этом регистр символов в словаре должен игнорироваться,
 * но при выводе символ в верхнем регистре отображается в строку, начинающуюся с символа в верхнем регистре.
 *
 * Пример.
 * Входной текст: Здравствуй, мир!
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Пример 2.
 *
 * Входной текст: Здравствуй, мир!
 * Словарь: mapOf('з' to "zZ", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "YY", '!' to "!!!")
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun transliterate(inputName: String, dictionary: Map<Char, String>, outputName: String) {
    TODO()
}

/**
 * Средняя (12 баллов)
 *
 * Во входном файле с именем inputName имеется словарь с одним словом в каждой строчке.
 * Выбрать из данного словаря наиболее длинное слово,
 * в котором все буквы разные, например: Неряшливость, Четырёхдюймовка.
 * Вывести его в выходной файл с именем outputName.
 * Если во входном файле имеется несколько слов с одинаковой длиной, в которых все буквы разные,
 * в выходной файл следует вывести их все через запятую.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 * Пример входного файла:
 * Карминовый
 * Боязливый
 * Некрасивый
 * Остроумный
 * БелогЛазый
 * ФиолетОвый

 * Соответствующий выходной файл:
 * Карминовый, Некрасивый
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun chooseLongestChaoticWord(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сложная (22 балла)
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе элементы текстовой разметки следующих типов:
 * - *текст в курсивном начертании* -- курсив
 * - **текст в полужирном начертании** -- полужирный
 * - ~~зачёркнутый текст~~ -- зачёркивание
 *
 * Следует вывести в выходной файл этот же текст в формате HTML:
 * - <i>текст в курсивном начертании</i>
 * - <b>текст в полужирном начертании</b>
 * - <s>зачёркнутый текст</s>
 *
 * Кроме того, все абзацы исходного текста, отделённые друг от друга пустыми строками, следует обернуть в теги <p>...</p>,
 * а весь текст целиком в теги <html><body>...</body></html>.
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 * Отдельно следует заметить, что открывающая последовательность из трёх звёздочек (***) должна трактоваться как "<b><i>"
 * и никак иначе.
 *
 * При решении этой и двух следующих задач полезно прочитать статью Википедии "Стек".
 *
 * Пример входного файла:
Lorem ipsum *dolor sit amet*, consectetur **adipiscing** elit.
Vestibulum lobortis, ~~Est vehicula rutrum *suscipit*~~, ipsum ~~lib~~ero *placerat **tortor***,

Suspendisse ~~et elit in enim tempus iaculis~~.
 *
 * Соответствующий выходной файл:
<html>
<body>
<p>
Lorem ipsum <i>dolor sit amet</i>, consectetur <b>adipiscing</b> elit.
Vestibulum lobortis. <s>Est vehicula rutrum <i>suscipit</i></s>, ipsum <s>lib</s>ero <i>placerat <b>tortor</b></i>.
</p>
<p>
Suspendisse <s>et elit in enim tempus iaculis</s>.
</p>
</body>
</html>
 *
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun transliterationSimple(word: String, check: MutableList<Boolean>, writer: BufferedWriter): BufferedWriter {
    for (i in word.indices) {
        when {
            word.getOrNull(i - 2).toString() != "*" && word.getOrNull(i - 1).toString() == "*"
                    && word.getOrNull(i).toString() == "*" -> {
                if (check[0]) {
                    writer.write("</b>")
                    check[0] = false
                } else {
                    writer.write("<b>")
                    check[0] = true
                }
            }
            (word.getOrNull(i - 2).toString() != "*" && word.getOrNull(i - 1).toString() == "*"
                    && word.getOrNull(i).toString() != "*") ||
                    (word.getOrNull(i - 2).toString() == "*" && word.getOrNull(i - 1).toString() == "*"
                            && word.getOrNull(i).toString() == "*") ||
                    (word.getOrNull(i).toString() == "*" && i == word.length - 1) -> {
                if (check[1]) {
                    writer.write("</i>")
                    check[1] = false
                } else {
                    writer.write("<i>")
                    check[1] = true
                }
                if (word.getOrNull(i).toString() != "*" && word.getOrNull(i).toString() != "~") {
                    writer.write(word[i].toString()) //Случай, когда word.getOrNull(i - 2).toString() != "*" && word.getOrNull(i - 1).toString() == "*" && word.getOrNull(i).toString() != "*"
                }
            }
            word.getOrNull(i - 1).toString() == "~" && word.getOrNull(i).toString() == "~" -> {
                if (check[2]) {
                    writer.write("</s>")
                    check[2] = false
                } else {
                    writer.write("<s>")
                    check[2] = true
                }
            }
            else -> if (word[i].toString() != "*" && word[i].toString() != "~") writer.write(word[i].toString())
        }
    }
    return writer
}

fun markdownToHtmlSimple(inputName: String, outputName: String) {
    File(outputName).bufferedWriter().use {
        val check = mutableListOf(
            false,
            false,
            false
        ) //Булевые флаги элементов транслитерации ([0] - "**", [1] - "*", [2] - "~~")
        var paragraph = false //Булевый флаг начала/конца абзаца
        var numOfNotEmptyLines = 0

        it.write("<html><body><p>")
        for (line in File(inputName).readLines()) {
            when {
                Regex("[^\\s]").find(line) == null && paragraph -> { //Первая пустая строка (не учитывая табуляцию)
                    it.write("</p>")
                    paragraph = false
                }
                Regex("[^\\s]").find(line) != null -> {
                    if (!paragraph && numOfNotEmptyLines != 0) { //Начало абзаца
                        it.write("<p>")
                    }
                    for (word in line.split(Regex("\\s+"))) {
                        transliterationSimple(word, check, it)
                    }
                    numOfNotEmptyLines++
                    paragraph = true
                }
                else -> continue
            }
        }
        if (paragraph || numOfNotEmptyLines == 0) { //Условие окончания последнего абзаца текста при отсутсвии непустых строк или при последней непустой строке
            it.write("</p>")
        }
        it.write("</body></html>")
    }
}

/**
 * Сложная (23 балла)
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе набор вложенных друг в друга списков.
 * Списки бывают двух типов: нумерованные и ненумерованные.
 *
 * Каждый элемент ненумерованного списка начинается с новой строки и символа '*', каждый элемент нумерованного списка --
 * с новой строки, числа и точки. Каждый элемент вложенного списка начинается с отступа из пробелов, на 4 пробела большего,
 * чем список-родитель. Максимально глубина вложенности списков может достигать 6. "Верхние" списки файла начинются
 * прямо с начала строки.
 *
 * Следует вывести этот же текст в выходной файл в формате HTML:
 * Нумерованный список:
 * <ol>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ol>
 *
 * Ненумерованный список:
 * <ul>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ul>
 *
 * Кроме того, весь текст целиком следует обернуть в теги <html><body><p>...</p></body></html>
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 *
 * Пример входного файла:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
 * Утка по-пекински
 * Утка
 * Соус
 * Салат Оливье
1. Мясо
 * Или колбаса
2. Майонез
3. Картофель
4. Что-то там ещё
 * Помидоры
 * Фрукты
1. Бананы
23. Яблоки
1. Красные
2. Зелёные
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 *
 *
 * Соответствующий выходной файл:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
<html>
<body>
<p>
<ul>
<li>
Утка по-пекински
<ul>
<li>Утка</li>
<li>Соус</li>
</ul>
</li>
<li>
Салат Оливье
<ol>
<li>Мясо
<ul>
<li>Или колбаса</li>
</ul>
</li>
<li>Майонез</li>
<li>Картофель</li>
<li>Что-то там ещё</li>
</ol>
</li>
<li>Помидоры</li>
<li>Фрукты
<ol>
<li>Бананы</li>
<li>Яблоки
<ol>
<li>Красные</li>
<li>Зелёные</li>
</ol>
</li>
</ol>
</li>
</ul>
</p>
</body>
</html>
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun transliterationLists(
    typeOfList: String, line: String, num: MutableList<Int>, unNum: MutableList<Int>, last: Int, xLast: String,
    writer: BufferedWriter
): Pair<Int, String> {
    val current = numOfSpaces(line) / 4 //Табуляция текущей строки
    val paragraph = if (typeOfList == "*") "ul"
    else "ol"
    when {
        current > last -> { //Начало подсписка
            writer.write("<$paragraph>")
            writer.write("<li>")
            if (typeOfList == "*") unNum[current]++
            else num[current]++
        }
        current < last -> { //Конец подсписка
            writer.write("</li>")
            when {
                unNum[last] != 0 -> writer.write("</ul>")
                num[last] != 0 -> writer.write("</ol>")
            }
            writer.write("</li><li>")
            if (xLast == "*") unNum[last] = 0
            else num[last] = 0
        }
        else -> { //Продолжение предыдущего списка
            writer.write("</li><li>")
            if (typeOfList == "*") unNum[current]++
            else num[current]++
        }
    }
    return Pair(current, typeOfList)
}

fun closeLists(
    num: List<Int>, unNum: List<Int>,
    writer: BufferedWriter
): BufferedWriter {
    for (i in 6 downTo 0) {
        if (unNum[i] != 0) writer.write("</li></ul>")
        if (num[i] != 0) writer.write("</li></ol>")
    }
    return writer
}

fun markdownToHtmlLists(inputName: String, outputName: String) {
    File(outputName).bufferedWriter().use {
        val unNum =
            mutableListOf(0, 0, 0, 0, 0, 0, 0) //Счетчик ненумерованных элементов списка при определенной табуляции
        val num = mutableListOf(0, 0, 0, 0, 0, 0, 0) //Счетчик нумерованных элементов списка при определенной табуляции
        var last = -1 //Табуляция предыдущей строки
        var xLast = "" //Вид списка предыдущей строки
        var typeOfList: String
        var regexByType: String

        it.write("<html><body><p>")
        for (line in File(inputName).readLines()) {
            when {
                Regex("""^(\s)?[*]\s|(\s)+[*]\s""").find(line) != null -> {
                    typeOfList = "*"
                    regexByType = "^(\\s)?[*]\\s|(\\s)+[*]\\s"
                }
                Regex("""^(\s)?[0-9]+[.]\s|(\s)+[0-9]+[.]\s""").find(line) != null -> {
                    typeOfList = "0"
                    regexByType = "^(\\s)?[0-9]+[.]\\s|(\\s)+[0-9]+[.]\\s"
                }
                else -> continue
            }
            val res = transliterationLists(typeOfList, line, num, unNum, last, xLast, it)
            last = res.first
            xLast = res.second
            for (word in line.split(Regex("""$regexByType"""))) {
                it.write(word)
            }
        }
        closeLists(num, unNum, it)
        it.write("</p></body></html>")
    }
}

/**
 * Очень сложная (30 баллов)
 *
 * Реализовать преобразования из двух предыдущих задач одновременно над одним и тем же файлом.
 * Следует помнить, что:
 * - Списки, отделённые друг от друга пустой строкой, являются разными и должны оказаться в разных параграфах выходного файла.
 *
 */
fun markdownToHtml(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя (12 баллов)
 *
 * Вывести в выходной файл процесс умножения столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 111):
19935
 *    111
--------
19935
+ 19935
+19935
--------
2212785
 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 * Нули в множителе обрабатывать так же, как и остальные цифры:
235
 *  10
-----
0
+235
-----
2350
 *
 */
fun printMultiplicationProcess(lhv: Int, rhv: Int, outputName: String) {
    TODO()
}


/**
 * Сложная (25 баллов)
 *
 * Вывести в выходной файл процесс деления столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 22):
19935 | 22
-198     906
----
13
-0
--
135
-132
----
3

 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 *
 */
fun printDivisionProcess(lhv: Int, rhv: Int, outputName: String) {
    TODO()
}

/**
 * Индивидульное задание
 *
 * Дан текстовый файл, в котором схематично изображена схема прямоугольного мини-лабиринта:
 * 1) во всех строках одинаковое количество символов
 * 2) символ # обозначает препятствие, символ . свободное место, символ * начальное местоположение "Робота",
 *    символ ^ местоположение "цели"
 *
 * Функция, которую нужно написать, принимает как параметр имя этого файла. Она должна вернуть как результат строку с
 * командами для робота вида "rllluddurld", где r обозначает движение на клетку вправо, l влево, u вверх и d вниз,
 * такую, чтобы робот, исполнив эти команды, прошёл от своего начального местоположения до цели (на препятствия
 * наступать нельзя). В случае, если подобный проход невозможен, следует бросить исключение (любое на ваш выбор).
 * Необходимо написать функцию и тесты к ней. Сделать это можно прямо внутри своего проекта KotlinAsFirst в любом
 * уроке, и, когда будет готово — прислать мне ссылку на Git репозиторий.
 */
class Graph {
    private data class Vertex(val name: String) {
        val neighbors = mutableSetOf<Vertex>()
    }

    private val vertices = mutableMapOf<String, Vertex>()

    private operator fun get(name: String) = vertices[name] ?: throw IllegalArgumentException()

    fun addVertex(name: String) {
        vertices[name] = Vertex(name)
    }

    private fun connect(first: Vertex, second: Vertex) {
        first.neighbors.add(second)
        second.neighbors.add(first)
    }

    fun connect(first: String, second: String) = connect(this[first], this[second])

    fun neighbors(name: String) = vertices[name]?.neighbors?.map { it.name } ?: listOf()

    fun bfs(start: String, startCoordinate: Pair<Int, Int>, finish: String, finishCoordinate: Pair<Int, Int>) =
        bfs(this[start], startCoordinate, this[finish], finishCoordinate)

    private fun bfs(
        start: Vertex, startCoordinate: Pair<Int, Int>, finish: Vertex, finishCoordinate: Pair<Int, Int>
    ): String {
        val queue = LinkedList<Vertex>()
        val visited = mutableMapOf(start to Triple(0, "", startCoordinate))
        queue.add(start)

        while (queue.isNotEmpty()) {
            val next = queue.poll()
            val lastPathway = visited[next]!!.second //Траектория пути, пройденного от заданной вершины до текущей
            val lastCoordinate = visited[next]!!.third //Координата текущей вершины
            if (next == finish) return lastPathway

            for (neighbor in next.neighbors) {
                if (neighbor in visited) continue
                val currentCoordinate = if (neighbor != finish) { //Координата соседней вершины
                    val currentCoordinateList = neighbor.name.split(" ")
                    Pair(currentCoordinateList[0].toInt(), currentCoordinateList[1].toInt())
                } else finishCoordinate
                val currentPathway =
                    when { //Траектория пути, пройденного от заданной вершины до соседней относительно текущей
                        lastCoordinate.first - currentCoordinate.first == 1 -> lastPathway + "l"
                        lastCoordinate.first - currentCoordinate.first == -1 -> lastPathway + "r"
                        lastCoordinate.second - currentCoordinate.second == 1 -> lastPathway + "u"
                        else -> lastPathway + "d"
                    }

                visited[neighbor] = Triple(visited[next]!!.first + 1, currentPathway, currentCoordinate)
                queue.add(neighbor)
            }
        }
        throw Exception()
    }
}

fun labyrinth(inputName: String): String {
    val matrix = mutableListOf<MutableList<Int>>()
    val maze = Graph()
    var start = Pair(-1, -1)
    var finish = Pair(-1, -1)
    /*
    Чтобы в дальнейшем не мучаться, кладу координаты входа и выхода из лабиринта в отдельные переменные
     */

    for ((y, line) in File(inputName).readLines().withIndex()) {
        matrix.add(mutableListOf())
        for ((x, letter) in line.withIndex()) {
            var nameOfVertex: String
            when (letter.toString()) {
                "#" -> {
                    matrix[y].add(0)
                    continue
                }
                "." -> {
                    matrix[y].add(1)
                    nameOfVertex = "$x $y"
                    /*
                    Для удобства использую координаты "ячейки" как ее назвние (достаю координату в ф-ии bfs с помощью split'а)
                     */
                }
                "*" -> {
                    matrix[y].add(2)
                    nameOfVertex = "Start"
                    start = Pair(x, y)
                }
                "^" -> {
                    matrix[y].add(3)
                    nameOfVertex = "Finish"
                    finish = Pair(x, y)
                }
                else -> throw Exception()
            }
            val lastY = matrix.elementAtOrNull(y - 1)?.elementAtOrNull(x) //Значение верхней "ячейки" матрицы
            val lastX = matrix.elementAtOrNull(y)?.elementAtOrNull(x - 1) //Значение левой "ячейки" матрицы
            maze.addVertex(nameOfVertex)

            if (lastY == 1) {
                val currentValue = y - 1
                maze.connect(nameOfVertex, "$x $currentValue")
            }
            if (lastX == 1) {
                val currentValue = x - 1
                maze.connect(nameOfVertex, "$currentValue $y")
            }
            if ((lastY == 2 || lastX == 2) && nameOfVertex != "Start") {
                maze.connect(nameOfVertex, "Start")
            }
            if ((lastY == 3 || lastX == 3) && nameOfVertex != "Finish") {
                maze.connect(nameOfVertex, "Finish")
            }
        }
    }

    if (finish == Pair(-1, -1) || start == Pair(-1, -1)) {
        throw Exception()
    }
    return maze.bfs("Start", start, "Finish", finish)
}