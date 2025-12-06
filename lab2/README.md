# Lab 2 - XML Parsing and Processing

## Опис проекту

Цей проект демонструє роботу з XML файлами для обробки даних про холодну зброю (ножи). Проект реалізує три різні підходи до парсингу XML (SAX, DOM, StAX), валідацію XML документів за XSD схемою, сортування колекцій за допомогою Comparator, та трансформацію XML даних в HTML формат.

## Основні можливості

- **XML Парсинг**: Підтримка трьох методів парсингу XML:
  - **SAX Parser** - потоковий парсер для обробки великих файлів
  - **DOM Parser** - парсер, що створює дерево об'єктів в пам'яті
  - **StAX Parser** - pull-парсер для ефективної обробки XML
  
- **XML Валідація**: Перевірка XML документів на відповідність XSD схемі

- **Сортування**: Використання Comparator для сортування колекцій ножів за типом, походженням та ID

- **Трансформація**: Конвертація XML даних в HTML каталог з таблицею

## Структура проекту

```
lab2/
├── pom.xml                          # Maven конфігурація
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── weapons/
│   │   │       ├── Main.java        # Головний клас програми
│   │   │       ├── model/          # Модельні класи
│   │   │       │   ├── Blade.java
│   │   │       │   ├── Handle.java
│   │   │       │   ├── Knife.java
│   │   │       │   ├── KnifeCollection.java
│   │   │       │   └── Visual.java
│   │   │       ├── parser/         # XML парсери
│   │   │       │   ├── DOMParser.java
│   │   │       │   ├── SAXParser.java
│   │   │       │   └── StAXParser.java
│   │   │       ├── validator/      # Валідатор XML
│   │   │       │   └── XMLValidator.java
│   │   │       ├── transformer/   # Трансформатор XML->HTML
│   │   │       │   └── XMLTransformer.java
│   │   │       └── comparator/    # Компаратор для сортування
│   │   │           └── KnifeComparator.java
│   │   └── resources/
│   │       ├── meleeWeapon.xml     # Вхідний XML файл
│   │       ├── meleeWeapon.xsd     # XSD схема для валідації
│   │       └── knife_catalog.html  # Вихідний HTML файл
│   └── test/
│       └── java/
│           └── weapons/            # Юніт тести
│               ├── model/
│               ├── parser/
│               ├── validator/
│               ├── transformer/
│               └── comparator/
└── lib/                            # Бібліотеки (JUnit 4)
    ├── junit-4.13.2.jar
    └── hamcrest-core-1.3.jar
```

## Модель даних

### Knife (Ніж)
Основний клас, що представляє ніж з наступними атрибутами:
- `id` - унікальний ідентифікатор
- `type` - тип ножа (knife, dagger, sword, saber, machete)
- `handy` - тип використання (one-handed, two-handed)
- `origin` - країна походження
- `value` - чи є колекційним (boolean)
- `visuals` - список візуальних характеристик

### Visual (Візуальні характеристики)
Містить інформацію про:
- `blade` - лезо (довжина, ширина)
- `material` - матеріал леза
- `handle` - рукоятка (матеріал, тип дерева)
- `bloodGroove` - наявність кровостоку (boolean)

## Технології

- **Java 8**
- **JUnit 4** - для юніт тестування
- **Maven** - для управління залежностями та збірки
- **XML APIs**:
  - SAX (Simple API for XML)
  - DOM (Document Object Model)
  - StAX (Streaming API for XML)
- **XSD Schema** - для валідації XML

## Як запустити

### Вимоги
- Java JDK 8 або вище
- Maven (опціонально, для автоматичної збірки)


### Запуск тестів

```bash
java -cp "target/classes;target/test-classes;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar" org.junit.runner.JUnitCore weapons.model.BladeTest weapons.model.HandleTest weapons.model.VisualTest weapons.model.KnifeTest weapons.model.KnifeCollectionTest weapons.comparator.KnifeComparatorTest weapons.parser.DOMParserTest weapons.parser.SAXParserTest weapons.parser.StAXParserTest weapons.validator.XMLValidatorTest weapons.transformer.XMLTransformerTest
```

## Приклад виводу

```
=== XML Parser Demo ===

1. Validating XML against XSD schema...
XML document is valid against XSD schema.

2. Parsing XML using SAX parser...
Parsed with SAX parser:
  Knife{id='k1', type='knife', handy='one-handed', origin='Ukraine', ...}
Total knives: 5

3. Parsing XML using DOM parser...
...

4. Parsing XML using StAX parser...
...

5. Sorting knives using Comparator...
Sorted knives:
  - dagger from Germany (ID: k2)
  - knife from Ukraine (ID: k1)
  ...

6. Transforming XML to HTML...
HTML catalog created: lab2\src\main\resources\knife_catalog.html
```

## Особливості реалізації

1. **SAX Parser**: Використовує подійно-орієнтований підхід, ефективний для великих файлів
2. **DOM Parser**: Створює повне дерево об'єктів в пам'яті, зручний для навігації
3. **StAX Parser**: Pull-модель парсингу, дозволяє контролювати процес читання
4. **KnifeComparator**: Сортує за типом → походженням → ID
5. **XMLTransformer**: Створює HTML таблицю з стилізацією для відображення каталогу