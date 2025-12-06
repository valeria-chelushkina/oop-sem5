# Gemstones Management System

## Опис проекту

**Gemstones Management System** - це консольна програма для управління колекцією дорогоцінних та напівдорогоцінних каменів (gemstones). Система дозволяє створювати "намисто" з каменів, додавати нові камені, сортувати їх за різними критеріями, шукати за прозорістю та зберігати/завантажувати дані з файлу.

## Основні можливості

- Додавання різних типів каменів (Diamond, Ruby, Emerald, Amethyst, Opal, Aventurine)
- Створення власних каменів (PreciousStone, SemiPreciousStone)
- Сортування каменів за вартістю, вагою або цінністю (cost/weight)
- Пошук каменів за діапазоном прозорості (з автоматичною валідацією)
- Збереження та завантаження намиста з/у файл
- Вибір між дефолтним файлом або власним шляхом для збереження/завантаження

## Структура проекту

```
lab1/
├── src/
│   ├── main/java/gemstones/          # Основний код
│   │   ├── Main.java                 # Головний клас з меню
│   │   ├── gemstone/                 # Класи каменів
│   │   │   ├── Gemstone.java         # Базовий абстрактний клас
│   │   │   ├── PreciousStone.java    # Дорогоцінні камені
│   │   │   ├── SemiPreciousStone.java # Напівдорогоцінні камені
│   │   │   ├── Diamond.java          # Діамант
│   │   │   ├── Ruby.java             # Рубін
│   │   │   ├── Emerald.java          # Смарагд
│   │   │   ├── Amethyst.java         # Аметист
│   │   │   ├── Opal.java             # Опал
│   │   │   └── Aventurine.java       # Авантюрин
│   │   ├── necklace/
│   │   │   └── Necklace.java         # Клас намиста
│   │   └── util/
│   │       └── FileManager.java      # Робота з файлами
│   └── test/java/gemstones/          # Юніт тести
│       ├── gemstone/                 
│       ├── necklace/                 
│       └── util/                     
├── lib/                              
│   ├── junit-4.13.2.jar             
│   └── hamcrest-core-1.3.jar        
├── target/                           
│   ├── classes/                     
│   └── test-classes/                
└── pom.xml                          # Maven конфігурація
```

## Використання

Після запуску програми з'явиться меню:

```
=== MENU ===
1. Show the necklace
2. Add gemstone
3. Sort gemstones
4. Find gemstone by transparency
5. Save the necklace
6. Load the necklace
0. Exit
```

### Додавання каменів

При виборі опції "Add gemstone" можна:
- Вибрати зі списку (Diamond, Ruby, Emerald, Amethyst, Opal, Aventurine)
- Створити власний камінь (опція 7) - PreciousStone або SemiPreciousStone

### Пошук за прозорістю

Система автоматично:
- Валідує значення в діапазоні [0-1]
- Обрізає значення, якщо вони виходять за межі
- Міняє місцями min і max, якщо min > max

### Збереження/Завантаження

- Дефолтний файл: `lab1/src/main/java/gemstones/necklace_data.txt`
- Можна вказати власний шлях до файлу

## Запуск тестів

```powershell
# Компіляція тестів
& "C:\Program Files\Java\jdk-25\bin\javac.exe" -d target/test-classes -cp "target/classes;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar" -sourcepath src/test/java src/test/java/gemstones/**/*Test.java

# Запуск тесту
& "C:\Program Files\Java\jdk-25\bin\java.exe" -cp "target/classes;target/test-classes;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar" org.junit.runner.JUnitCore gemstones.gemstone.AmethystTest
```

## Технології

- **Java** - мова програмування
- **JUnit 4** - фреймворк для юніт тестів
- **Maven** - система збірки

## Особливості реалізації

### Об'єктно-орієнтоване програмування
- Абстрактний базовий клас `Gemstone`
- Наслідування: `PreciousStone` та `SemiPreciousStone` розширюють `Gemstone`
- Поліморфізм: різні типи каменів (Diamond, Ruby, тощо) наслідують базові класи
- Інкапсуляція: приватні поля з геттерами/сеттерами

### Валідація даних
- Прозорість (transparency) обмежена діапазоном [0-1]
- Автоматична корекція некоректних значень при пошуку

### Робота з файлами
- Збереження стану намиста у текстовий файл
- Завантаження з файлу при старті програми
- Підтримка власних шляхів до файлів