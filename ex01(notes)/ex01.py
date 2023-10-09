import datetime
import json

def menu():
    flag = True
    while flag:
        print("1. Создать заметку")
        print("2. Изменить заметку")
        print("3. Удалить заметку")
        print("4. Показать заметку по ID")
        print("5. Показать заметки по дате")
        print("6. Показать все заметки")
        print("7. Выход")
        command = input_command("\u001B[33mВыберите команду: \u001B[0m")
        if command == 1:
            create_note()
        elif command == 2:
            edit_note()
        elif command == 3:
            delete_note()
        elif command == 4:
            show_note_by_id()
        elif command == 5:
            show_notes_by_date()
        elif command == 6:
            show_all_notes()
        elif command == 7:
            flag = False
        else:
            print("\u001B[31mОШИБКА! Такой команды нет, пробуйте снова.\u001B[0m\n")


def input_command(message):
    while True:
        try:
            user_input = int(input(message))
        except ValueError:
            print("\u001B[31mОШИБКА! Вы должны ввести число, попробуйте снова.\u001B[0m\n")
        else:
            break
    return user_input


def create_note():
    title = input("Введите заголовок заметки: ")
    note = input("Введите заметку: ")
    timestamp = datetime.datetime.now()
    note = {
        'id': int(get_max_id()) + 1,
        'title': title,
        'body': note,
        'timestamp': str(timestamp)
    }
    notes.append(note)
    save_notes(notes)
    print('\u001B[32mЗаметка успешно сохранена\u001B[0m\n')


def edit_note():
    note_id = int(input("Введите ID заметки, которую вы хотите изменить: "))
    for note in notes:
        if note['id'] == note_id:
            note['title'] = input("Введите новый заголовок заметки: ")
            note['body'] = input("Напишите новую заметку: ")
            note['timestamp'] = str(datetime.datetime.now())
            save_notes(notes)
            print('\u001B[32mЗаметка успешно изменена\u001B[0m\n')
            return
    print("\u001B[31mЗаметка не найдена.\u001B[0m\n")


def delete_note():
    note_id = int(input("Введите ID заметки, которую хотите удалить: "))
    for note in notes:
        if note['id'] == note_id:
            notes.remove(note)
            save_notes(notes)
            print("\u001B[32mЗаметка успешно удалена.\u001B[0m\n")
            return
    print("\u001B[31mЗаметка не найдена.\u001B[0m\n")


def show_note_by_id():
    note_id = int(input("Введите ID заметки, которую хотите вывести в консоль: "))
    for note in notes:
        if note['id'] == note_id:
            print(f"\n\u001B[36m\tID: {note['id']}")
            print(f"\tЗаголовок: {note['title']}")
            print(f"\tТело заметки: {note['body']}")
            print(f"\tВремя создания: {note['timestamp']}\u001B[0m\n")
            return
    print("\u001B[31mЗаметка не найдена.\u001B[0m\n")


def show_notes_by_date():
    note_date = input("Введите дату заметки, которую хотите вывести в консоль, в формате гггг-мм-дд: ")
    count = 0
    for note in notes:
        if note_date in note['timestamp']:
            print(f"\n\u001B[36m\tID: {note['id']}")
            print(f"\tЗаголовок: {note['title']}")
            print(f"\tТело заметки: {note['body']}")
            print(f"\tВремя создания: {note['timestamp']}\u001B[0m\n")
            count += 1
    if count == 0:
        print("\u001B[31mЗаметок не найдено.\u001B[0m\n")


def show_all_notes():
    if len(notes) == 0:
        print("\u001B[31mСписок заметок пуст\u001B[0m")
    else:
        for note in notes:
            print(f"\n\u001B[36m\tID: {note['id']}")
            print(f"\tЗаголовок: {note['title']}")
            print(f"\tТело заметки: {note['body']}")
            print(f"\tВремя создания: {note['timestamp']}\u001B[0m\n")


def get_max_id():
    max = 0
    for note in notes:
        if note['id'] > max:
            max = note['id']
    return max

            
def save_notes(notes):
    with open('notes.json', 'w') as file:
        json.dump(notes, file)


def load_notes():
    try:
        with open('notes.json', 'r') as file:
            notes = json.load(file)
        return notes
    except FileNotFoundError:
        return []
    
     
notes = load_notes()
menu()
