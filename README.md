# Про проект

Що вийшло зробити: 
1) Запуск программи з консолі передавши аргументи
2) Запуск программи без аргументів, але запуск консолі за для зчитування аргументів
3) Обробку усіх команд які надходять (треба всеодно допрацьовувати), а саме: 
   1. Валідацію команд (якщо команда випадково написана в нижньому регістрі)
   2. Перевірка шляху (абсолютний чи ні)
   3. перевірка на від'ємність ключа
4) Запис у файл:
   1. З примітками [ENCRYPTED/DECRYPTED/BRUTE_FORCE] 
   2. Прибирання приміток, та їх перезапис при різних командах
5) Зчитування файл 
6) Шифрування, розшифрування шифром цезаря на англійскій та українській мові (великі та малі літери обох алфавітів) 
7) Використовую коректно один і той самий ключ для декодування та кодування
8) Зроблено автоматичне визначення алфавіту 

Що на мою думку видалось цікавим зробити: 
1) Зробив розбиття на класси, саме намагався зробити так, щоб один клас виконував одну функцію. 
2) Розмістив усе в пакетах
3) Зайнявся модифікаторами доступу
4) Використав Enum (для мови та команд)
5) Використав Singleton

Що не выйшло або не зробив:
1) Не вийшло зробити brute-force режим программи 
2) Не зробив обробку шляху з спец. символами 
3) Не зробив кодування символів (, . ! ...) 
4) Не зробив додаткові завдання: 
   1. Частотний аналіз.
   2. Та графічний інтерфейс.
5) Не зробив коментарі до методів (намагатимусь зробити)