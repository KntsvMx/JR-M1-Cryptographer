### Ukrainian version 1.1

# Про проект

Даний проект є вступом до синтаксису Java, патернів та можливостей мови програмування. Основна ідея полягає у використанні "Шифру Цезаря" для шифрування та запису тексту до файлу.

## Особливості програми:

- Запуск программи з консолі передавши аргументи
- Запуск программи без аргументів з використанням консолі.
- Обробку усіх команд які надходять, а саме:
    - Валідацію команд.
    - Перевірка шляху на "Absolute".
    - перевірка на від'ємність ключа.

- Запис у файл:
    - З примітками [ENCRYPTED/DECRYPTED/BRUTE_FORCE].
    - Прибирання приміток, та їх перезапис при різних командах.
- Зчитування файл.
- Шифрування, розшифрування шифром цезаря на англійскій та українській мові (великі та малі літери обох алфавітів).
- Використовую коректно один і той самий ключ для декодування та кодування.
- Brute force режим.
- Зроблено автоматичне визначення алфавіту.

## Принципи та патерни:

- Виконання Single Responsibility Principle та дотримування SOLID принципів як основи.
- Пакетування частин программи.
- Дотримування принципів "Чистого коду".
- Використав Enum (для мови та команд).
- Використав Singleton.

## Майбутній функціонал

- Обробку шляху з спец. символами
- Кодування символів (, . ! ...)
- Додаткові завдання:
    1. Частотний аналіз.
    2. Та графічний інтерфейс.
- Коментарі до методів.

#
### English version 1.0
# Project Overview

This project serves as an introduction to Java syntax, patterns, and capabilities. The main idea is to utilize the "
Caesar Cipher" for encrypting and writing text to a file.

## Program Features

- Launching the program with console arguments.
- Launching the program without arguments using the console.
- Handling all incoming commands, including validation and checking the path.
- Writing to a file with [ENCRYPTED/DECRYPTED/BRUTE_FORCE] annotations.
- Reading from a file.
- Caesar cipher encryption and decryption for both English and Ukrainian languages.
- Using the same key for encoding and decoding.
- Brute force mode.
- Automatic alphabet detection.

## Principles and Patterns

- Following Single Responsibility Principle.
- Utilizing SOLID principles.
- Proper packaging of program components.
- Adhering to "Clean Code" principles.
- Enum usage for language and commands.
- Singleton pattern implementation.

## Future Plans

- Handling paths with special characters.
- Character encoding (, . ! ...).
- Additional tasks:
    - Frequency analysis.
    - Graphical user interface.
- Adding comments to methods.
