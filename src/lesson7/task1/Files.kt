@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson7.task1

import java.io.File
import kotlin.math.abs
import kotlin.Int as Int

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
 * Средняя (14 баллов)       КОРОВА.windowed(3) IT = кор оро ров ова
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * На вход подаётся список строк substrings.
 * Вернуть ассоциативный массив с числом вхождений каждой из строк в текст.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 */
fun countSubstrings(inputName: String, substrings: List<String>): Map<String, Int> {
    val file = File(inputName).readText().toLowerCase()
    val res = mutableMapOf<String, Int>()
    val list = file.split("\n")
    for (i in substrings) {
        val text = i.toLowerCase()
        var count = 0
        for (j in list) {
            count += j.windowed(text.length) {
                if (it == text)
                    1
                else
                    0
            }.sum()
        }
        res[i] = count
    }
    return res
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
fun centerFile(inputName: String, outputName: String) {
    TODO()
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
fun top20Words(inputName: String): Map<String, Int> = TODO()

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
    var maxlength = 0
    val res = StringBuilder()
    val out = File(outputName).bufferedWriter()
    val list = File(inputName).readLines()
    for (i in list.indices) {
        val set = mutableSetOf<Char>()
        for (char in list[i]) {
            val charter = char.lowercaseChar()
            set.add(charter)
        }
        if ((set.size == list[i].length) && (list[i].length > maxlength)) {
            maxlength = list[i].length
            res.clear()
            res.append(list[i])
        } else if ((set.size == list[i].length) && (list[i].length == maxlength)) {
            res.append(", ${list[i]}")
        }
    }
    out.write(res.toString())
    out.close()
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
fun markdownToHtmlSimple(inputName: String, outputName: String) {
    val sign = listOf("*", "**", "~~")
    val input = File(inputName).readText()
    val file = input.replace(Regex("\\n"), "___-___")
    var rep = Regex("\\*\\*(.*?)\\*\\*").replace(file) { replacer ->
        "<b>" + replacer.value.replace("**", "") + "</b>"
    }
    rep = Regex("\\*(.*?)\\*").replace(rep) { replacer ->
        "<i>" + replacer.value.replace("*", "") + "</i>"
    }
    rep = Regex("~~(.*?)~~").replace(rep) { replacer ->
        "<s>" + replacer.value.replace("~~", "") + "</s>"
    }
    val list = rep.split("___-___")
    var count = 0
    var i = 0
    val list1 = mutableListOf("<p>")
    val regex = Regex("\\s")
    for (j in list) {
        if ((j.replace("\t", "") != "") && (j.replace(regex, "") != "")) {
            list1.add(j.replace("\t", ""))
            count += 1
        } else {
            if (count != 0 && i + 1 != list.size) {
                list1.add("</p><p>")
                count = 0
            }
        }
        i += 1
    }
    if (count == 0 && rep.trim() != "") {
        list1.removeLast()
    }
    File(outputName).writeText("<html><body>${list1.joinToString(separator = "\n")}</p></body></html>")
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
fun markdownToHtmlLists(inputName: String, outputName: String) {
    TODO()
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
    /*val list = mutableListOf<Int>()
    val list1 = mutableListOf<Int>()
    val list2 = mutableListOf<Int>()
    var num = lhv
    var i = 0
    var del = rhv
    var ch = 0
    var result = 0
    var count = 0
    while (num > 0) {
        list[count] = num % 10
        num /= 10
        count += 1
    }
    val res = StringBuilder()
    res.append("$num | $del\n")
    val out = File(outputName).bufferedWriter()
    while (num >= del) {
        while (ch < del) {
            ch = ch * 10 + list[count]
            count -= 1

        }
        list1[i] = ch / del
        list2[i] = ch % del

    }
    out.write(res.toString())
    out.close() */
}

/*fun myFun(state: String, move: String): Any {
    val letter = listOf("A", "B", "C", "E", "F", "G", "H")
} */

/**
 * В файле с именем inputName заданы описания квартир,
 * предлагающихся для продажи, в следующем формате:
 *
 * Пионерская 9-17: комната 18, комната 14, кухня 7, коридор 4
 * Школьная 12-14: комната 19, кухня 8, коридор 3
 * Садовая 19-1-55: комната 12, комната 19, кухня 9, коридор 5
 * Железнодорожная 3-6: комната 21, кухня 6, коридор 4
 *
 * Строчка начинается с адреса квартиры, после двоеточия
 * перечисляются помещения квартиры через запятую, с указанием
 * их площади.
 *
 * Параметр query содержит запрос, начинающийся с названия
 * помещения, за которым следует его минимальная площадь,
 * например, “кухня 8”. Через точку с запятой могут следовать
 * другие ограничения, например “кухня 8; коридор 4”
 * Функция должна найти все квартиры в списке,
 * удовлетворяющие запросу (площадь кухни больше или равна 8,
 * площадь коридора больше или равна 4)
 *
 * “Удовлетворительно” -- в запросе может присутствовать только
 * одно помещение, например, “кухня 8”
 *
 * “Хорошо” -- в запросе может присутствовать несколько помещений,
 * например, “кухня 8; комната 15”
 *
 * “Отлично” -- в запросе может присутствовать два и более
 * однотипных помещения, например, “комната 19; комната 12” --
 * двухкомнатная квартира,
 * одна комната не менее 19, другая не менее 12
 *
 * При нарушении форматов входных данных следует выбрасывать
 * исключение IllegalArgumentException, при невозможности
 * прочитать файл выбрасывать исключение IOException.
 *
 * Предложить имя и тип результата функции. Кроме функции
 * следует написать тесты, подтверждающие её работоспособность.
 */
fun foo(inputName: String, query: String): Any {
    val flats = inputName.split("\n")
    var res = ""
    val map = mutableMapOf<String, String>()
    val conditions = query.split("; ")
    for (flat in flats) {
        var count = 0
        var listS = mutableListOf<Int>()
        val rooms = flat.split(": ")[1].split(", ").toMutableList()
        for (room in rooms) {
            map[room.split(" ")[0]] = ""
        }
        for (room in rooms)
            map[room.split(" ")[0]] = map.getValue(room.split(" ")[0]) + room.split(" ")[1] + ", "
        for (condition in conditions) {
            for (room in rooms) {
                if (room.split(" ")[0] == condition.split("")[0]) {
                    map[room.split(" ")[0]] = map.getValue(room.split(" ")[0]).dropLast(2)
                    for (S in map[room.split(" ")[0]]!!.split(", "))
                        listS.add(S.toInt())
                    listS = listS.sorted() as MutableList<Int>
                }
            }
            var roomsCopy = rooms
            var countS = 0
            for (s in listS.indices) {
                var countR = 0
                for (room in rooms.indices) {
                    if (listS[s - countS] >= roomsCopy[room - countR].split(" ")[1].toInt() && roomsCopy[room - countR].split(
                            " "
                        )[0] == condition.split(" ")[0]
                    ) {
                        roomsCopy = roomsCopy.drop(room - count) as MutableList<String>
                        countR += 1
                        count += 1
                        listS = listS.drop(s - countS) as MutableList<Int>
                        countS += 1
                    }
                }
            }
        }
        if (count == conditions.size) {
            res += flat.split(": ")[0] + ", "
        }
    }
    return res.dropLast(2)
}

fun foo1(inputName: String): Any {
    var res = ""
    val listScore = mutableListOf<Int>()
    val list = inputName.split("\n")
    val map = mutableMapOf<String, Int>()
    val allTeams = mutableSetOf<String>()
    for (pri in list) {
        if (pri.isNotEmpty() && pri.split(", ").size == 3) {
            val name = pri.split(", ")[1]
            allTeams.add(name)
            map[name] = 0
        }
    }
    for (pri in list) {
        if (pri.isNotEmpty() && pri.split(", ").size == 3) {
            val name = pri.split(", ")[1]
            map[name] = map.getValue(name) + pri.split(", ")[2].toInt()
        }
    }
    for (team in allTeams) listScore.add(map[team]!!)
    val listScore1 = listScore.sortedDescending()
    for (score in listScore1) {
        for (team in allTeams) {
            if (score == map[team] && "$team, $score, " !in res) {
                res += "$team, $score, "
            }
        }
    }
    return res.dropLast(2)
}

fun exam(inputName: String, dictFileName: String): String {
    var text = inputName
    for (remark in dictFileName.split("\n")) {
        text =
            text.replace(Regex(remark.split(" -> ")[0].lowercase(), RegexOption.IGNORE_CASE), remark.split(" -> ")[1])
    }
    return text
}

/**
 * Робот Вася потерялся на поле. Поле задается в следующем формате:
 * xxxxxxOx
 * xxOxxxxx
 * xxxxxxxx
 * xxxxxxxx
 * xxxxxxxx
 * xxOxxxOx
 * xxRxxxxx
 * xxxxxOOx
 *
 * На поле отмечены состояния клеток, всего их может быть три: x — пустая
 * клетка, O — клетка с препятствием, R — клетка с роботом. Считайте что
 * на поле всегда ровно одна клетка занятая роботом.
 *
 * Роботу прислали список команд, которые он должен выполнить, чтобы добраться до
 * точки эвакуации. Состояние поля задается в файле с именем inputName, список
 * команд для робота приходит в списке commands. Вам необходимо вернуть состояние
 * поля после того как робот выполнит все команды. Если робот пытается выйти за
 * пределы поля или перейти на клетку с препятствием — бросить IllegalStateException.
 *
 * "Удовлетворительно" — Поле всегда имеет размер 8х8. У робота есть две команды:
 * ХОД — робот делает один шаг в том направлении, в которое он
 * сейчас смотрит. Изначально робот всегда смотрит на север (т.е. вверх).
 * На Хорошо: ПОВОРОТ — робот поворачивается на 90 градусов по часовой стрелке
 * (север -> восток -> юг -> запад -> северр).
 *
 *
 * Имя и тип результата функции предложить самостоятельно. Кроме функции,
 * следует написать тесты, подтверждающие её работоспособность
 */

fun robot(inputName: String, commands: List<String>): String {
    val lines = inputName.split("\n")
    var x = 0
    var max = 0
    var y = 0
    var res = ""
    for (line in lines.indices) {
        val cell = lines[line]
        var count = 0
        for (char in cell) {
            if (char == 'R') {
                x = count
                y = line
            }
            count += 1
            if (count > max) max = count
        }
    }
    var x1 = x
    var y1 = y
    var corner = 0
    for (command in commands) {
        if (command == "ПОВОРОТ") {
            corner += 90
        } else {
            if (corner % 360 == 0) y1 -= 1
            if (corner % 360 == 90) x1 += 1
            if (corner % 360 == 180) y1 += 1
            if (corner % 360 == 270) x1 -= 1
        }
        if (x1 > max || y1 >= lines.size || x1 < 0 || y1 < 0 || (lines[y1])[x1] == '0') throw IllegalStateException()
    }
    for (line in lines.indices) {
        val cell = lines[line]
        var count = 0
        for (char in cell) {
            if (count == x1 && line == y1) res += "R"
            else if (count == x && line == y) res += "x"
            else res += char
            count += 1
        }
        if (line != lines.size - 1)
            res += "\n"
    }
    return res
}

fun exam1(inputName: String, changes: List<String>): String {
    var result = ""
    var res = ""
    val listNum = mutableListOf<Int>()
    val map = mutableMapOf<Int, String>()
    var res0 = ""
    var res1 = ""
    val strings = inputName.split("\n")
    for (change in changes) {
        if (change.split(" ")[0].toInt() < strings[0].split(" ")[0].toInt()) res0 = change
        else if (change.split(" ")[0].toInt() > strings[strings.size - 1].split(" ")[0].toInt()) res1 = change
        else
            for (string in 0 until strings.size - 1) {
                if (change.split(" ")[0].toInt() > strings[string].split(" ")[0].toInt() &&
                    change.split(" ")[0].toInt() < strings[string + 1].split(" ")[0].toInt()
                ) {
                    map[change.split(" ")[0].toInt()] = change
                    listNum.add(change.split(" ")[0].toInt())
                }
            }
    }
    for (index in 0..strings.size - 2) {
        res += strings[index] + "\n"
        for (num in listNum) {
            if (num > strings[index].split(" ")[0].toInt() &&
                num < strings[index + 1].split(" ")[0].toInt() && map[num]!! !in res
            ) res += map[num] + "\n"
        }
    }
    res += strings[strings.size - 1]
    if (res0 == "" && res1 != "") result = res + "\n" + res1
    if (res0 != "" && res1 == "") result = res0 + "\n" + res
    if (res0 == "" && res1 == "") result = res
    if (res0 != "" && res1 != "") result = res0 + "\n" + res + "\n" + res1
    return result
}

fun football4(inputName: String): String {
    if (!Regex("""[W0LD](( [W0LD])+)? - [а-я]+((\n[W0LD](( [W0LD])+)? - [а-я]+)?)+""", RegexOption.IGNORE_CASE).matches(
            inputName
        )
    ) {
        throw IllegalStateException()
    }
    val listOfScores = inputName.split("\n")
    val teams = mutableSetOf<String>()
    val listik = mutableListOf<Int>()
    val map = mutableMapOf<String, Int>()
    var winTeam = ""
    for (i in listOfScores) {
        val team = i.split(" - ")[1]
        teams.add(team)
        map[team] = 0
    }
    for (i in listOfScores) {
        val splitLines = i.split(" - ")[0].split(" ")
        for (j in splitLines) {
            if (j == "W") map[i.split(" - ")[1]] = map.getValue(i.split(" - ")[1]) + 3
            if (j == "D") map[i.split(" - ")[1]] = map.getValue(i.split(" - ")[1]) + 1
        }
    }
    for (team in teams) {
        listik.add(map[team]!!)
    }
    val sortedList = listik.sortedDescending()
    for (team in teams) {
        for (score in sortedList) {
            if (score == map[team] && team !in winTeam) {
                winTeam += team + ", "
            }
        }
    }
    return winTeam.dropLast(2)
}

fun segment(tStart: String, tEnd: String): String {
    val listABC = listOf("ABCDEF", "BC", "ABDEG", "ABCDG", "BCGF", "ACDFG", "ACDEFG", "ABCF", "ABCDEFG", "ABCDFG")
    var res = ""
    val tStartMinutes = tStart.split(":")[0].toInt() * 60 + tStart.split(":")[1].toInt()
    val tEndMinutes = tEnd.split(":")[0].toInt() * 60 + tEnd.split(":")[1].toInt()
    var firstNum = ""
    var secondNum = ""
    var firstNum1 = ""
    var secondNum1 = ""
    if (tEndMinutes > tStartMinutes) {
        for (time in tStartMinutes..tEndMinutes) {
            for (number in listABC.indices) {
                firstNum = listABC[((time / 60) / 10)]
                firstNum1 = listABC[((time / 60) % 10)]
                secondNum = listABC[((time % 60) / 10)]
                secondNum1 = listABC[((time % 60) % 10)]
            }
            res += "$firstNum $firstNum1:$secondNum $secondNum1, "
        }
    }
    if (tEndMinutes < tStartMinutes) {
        var time = tStartMinutes
        while (time <= 60 * 24 - 1) {
            firstNum = listABC[((time / 60) / 10)]
            firstNum1 = listABC[((time / 60) % 10)]
            secondNum = listABC[((time % 60) / 10)]
            secondNum1 = listABC[((time % 60) % 10)]
            res += "$firstNum $firstNum1:$secondNum $secondNum1, "
            time += 1
        }
        time = 0
        while (time <= tEndMinutes) {
            firstNum = listABC[((time / 60) / 10)]
            firstNum1 = listABC[((time / 60) % 10)]
            secondNum = listABC[((time % 60) / 10)]
            secondNum1 = listABC[((time % 60) % 10)]
            res += "$firstNum $firstNum1:$secondNum $secondNum1, "
            time += 1
        }
    }
    return res.dropLast(2)
}

fun exam(inputName: String): Any {
    var text = File(inputName).readLines()
    var result = ""
    for (str in text.indices) {
        if (text[str].startsWith("=")) {
            result += (text[str].replaceFirst("=", "<h1>") + "</h1>") + "\n" + "<p>"
        } else
            if (text[str].startsWith("==")) {
                result = result.dropLast(1) + "</p>" + "\n" + text[str].replaceFirst("==", "<h2>") + "</h2>" + "\n" + "<p>"
            } else
                if (text[str].startsWith("===")) {
                    result = result.dropLast (1) + "</p>" + "\n" + text[str].replaceFirst("===", "<h3>") + "</h3>" + "\n" + "<p>"
                } else
                    if (text[str].isEmpty() && "\n" in result) {
                        result = result.dropLast(1) + "</p>" + "\n" + "<p>"
                    } else result += text[str] + "\n"
    }
    return "<html><body><p>${result.replace("+", "<br>")}</p></html></body>" // список в строку с разделителем \n
}

