insert into role (role_name, is_active )
    values('ADMIN', true);

insert into role (role_name, is_active )
    values('USER', true);

insert into role (role_name, is_active )
    values('MANAGER', true);

insert into account(user_name, login, password, creation_date, is_active)
    values('Chief admin', 'admin', '$2a$12$etr7wxbjodIHYNQBvC/CBuzKbNVjDMghaKwfp8sWk3qGuJt/lCcOm', '2019-04-15 18:00:00', true);

insert into account(user_name, login, password, creation_date, is_active)
    values('Маргарита', 'margo', '$2a$12$etr7wxbjodIHYNQBvC/CBuzKbNVjDMghaKwfp8sWk3qGuJt/lCcOm', '2019-04-15 18:00:00', true);

insert into account(user_name, login, password, creation_date, is_active)
    values('Антон', 'anton', '$2a$12$etr7wxbjodIHYNQBvC/CBuzKbNVjDMghaKwfp8sWk3qGuJt/lCcOm', '2019-04-15 18:00:00', true);

insert into account(user_name, login, password, creation_date, is_active)
    values('Екатерина', 'ekaterina', '$2a$12$etr7wxbjodIHYNQBvC/CBuzKbNVjDMghaKwfp8sWk3qGuJt/lCcOm', '2019-04-15 18:00:00', true);

insert into account_role (account_id, role_id)
    values(1, 1), (1, 2), (1, 3), (2, 2), (3, 2), (4, 3);

insert into contest (contest_id, name, contest_start, contest_end, is_active, duration_for_person_in_days)
  values (1000, 'First contest', '2019-04-15', '2019-06-06', true, 14);

insert into contest (contest_id, name, contest_start, contest_end, is_active, duration_for_person_in_days)
  values (1001, 'Second contest', '2019-04-15', '2019-06-06', true, 14);

insert into participation (account_id , contest_id)
    values (2, 1000);

insert into participation (account_id , contest_id)
    values (3, 1000);

insert into task (task_id, contest_id, task_text, creation_date, time_limit,
    memory_limit, tries_limit, title, is_chat) values
        (0, 1000, 'Общие вопросы', '2019-04-15', 5, 128000, 100, 'Общие вопросы', true);




insert into task (task_id, contest_id, task_text, creation_date, time_limit,
    memory_limit, tries_limit, title, is_chat) values
        (1000, 1000, E'Найти сумму чисел a и b.', '2019-04-15', 5, 128000, 100, 'Задача 1. Сумма чисел', false);

insert into task_description (task_id, sample_input, sample_output, notes)
    values (1000, E'1\n2', '3', '');

insert into task_description (task_id, sample_input, sample_output, notes)
    values (1000, E'-5\n5', '0', '');

insert into task (task_id, contest_id, task_text, creation_date, time_limit,
    memory_limit, tries_limit, title, is_chat) values
        (1001, 1000, E'Отсортировать массив целлых чисел N.', '2019-04-15', 5, 128000, 100, 'Задача 2. Сортировка целых чисел', false);

insert into task_description (task_id, sample_input, sample_output, notes)
    values (1001, E'3\n3\n2\n1', '1 2 3', '');

insert into task_description (task_id, sample_input, sample_output, notes)
    values (1001, E'1\n5', '5', '');

insert into test (test_id, task_id, test_input, expected_output, is_smoke, author)
    values (1000, 1000, E'0\n0\n', '0', false, 1);

insert into test (test_id, task_id, test_input, expected_output, is_smoke, author)
    values (1001, 1000, E'5\n5\n', '10', false, 1);

insert into test (test_id, task_id, test_input, expected_output, is_smoke, author)
    values (1002, 1000, E'-5\n5\n', '0', false, 1);


insert into solution (task_id, solution_text, language_id, status_id, creation_date, account_id)
    values (1000, 'print(sum(int(input()) for i in range(2)))', 'PYTHON', 'IN_QUEUE', '2019-04-17 16:07:59', 2);

insert into solution (task_id, solution_text, language_id, status_id, creation_date, account_id)
    values (1000, E'a=int(input())\nb=int(input())\nprint(a+b)', 'PYTHON', 'IN_QUEUE', '2019-04-17 16:07:59', 2);

insert into solution (task_id, solution_text, language_id, status_id, creation_date, account_id)
    values (1000, 'print("incorrect solution")', 'PYTHON', 'IN_QUEUE', '2019-04-17 16:07:59', 2);

insert into solution (task_id, solution_text, language_id, status_id, creation_date, account_id)
    values (1000, E'while True:\n\tpass', 'PYTHON', 'IN_QUEUE', '2019-04-17 16:07:59', 2);

insert into solution (task_id, solution_text, language_id, status_id, creation_date, account_id)
    values (1000, E'while True:\n\tbad_syntax', 'PYTHON', 'IN_QUEUE', '2019-04-17 16:07:59', 2);


insert into solution (task_id, solution_text, language_id, status_id, creation_date, account_id)
    values (1000,
    'import java.util.Scanner;
    public class Main{
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int num1 = sc.nextInt();
            int num2 = sc.nextInt();
            sc.close();
            System.out.println(num1 + num2);
        }
    }', 'JAVA', 'IN_QUEUE', '2019-04-17 16:07:59', 2);
-- correction solution
insert into solution (task_id, solution_text, language_id, status_id, creation_date, account_id)
    values (1000,
    'var readline = require(''readline'');

var rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

function processSum(number) {
    // Insert code to do whatever with sum here.
    console.log(number);
}

rl.question('''', function (x) {
    rl.question('''', function (y) {
        var sum = parseFloat(x) + parseFloat(y);

        processSum(sum)

        rl.close();
    });
});', 'JAVA_SCRIPT', 'IN_QUEUE', '2019-04-17 16:07:59', 2);

-- compile error
insert into solution (task_id, solution_text, language_id, status_id, creation_date, account_id)
    values (1000,
    'var readline = require(''readline'');

var rl = readline.createInterface({
    input: process.stddin,
    output: process.stdout
});

function processSum(number) {
    // Insert code to do whatever with sum here.
    console.log(number);
}

rl.question('''', function (x) {
    rl.question('''', function (y) {
        var sum = parseFloat(x) + parseFloat(y);

        processSum(sum)

        rl.close();
    });
});', 'JAVA_SCRIPT', 'IN_QUEUE', '2019-04-17 16:07:59', 2);

-- wrong answer
insert into solution (task_id, solution_text, language_id, status_id, creation_date, account_id)
    values (1000,
    'var readline = require(''readline'');

var rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

function processSum(number) {
    // Insert code to do whatever with sum here.
    console.log(number);
}

rl.question('''', function (x) {
    rl.question('''', function (y) {
        var sum = parseFloat(x) * parseFloat(y) + 50;

        processSum(sum)

        rl.close();
    });
});', 'PYTHON', 'IN_QUEUE', '2019-04-17 16:07:59', 2);


-- sorting task
insert into solution (task_id, solution_text, language_id, status_id, creation_date, account_id)
    values (1001,
'def read_arr(n):
    arr = []
    for i in range(n):
        arr.append(int(input()))
    return arr


def native_sort(arr):
    return sorted(arr)

def bubble_sort(arr):
    for i in range(len(arr)-1):
        for j in range(1, len(arr) - i):
            if arr[j-1] > arr[j]:
                arr[j-1], arr[j] = arr[j], arr[j-1]
    return arr


if __name__ == ''__main__'':
    n = int(input())
    arr = read_arr(n)
    #sorted_arr = bubble_sort(arr)
    sorted_arr = native_sort(arr)
    print(" ".join(str(n) for n in sorted_arr))', 'PYTHON', 'IN_QUEUE', '2019-04-17 16:07:59', 2);

insert into solution ( task_id, solution_text, language_id, status_id, creation_date, account_id)
    values (1001,
'def read_arr(n):
    arr = []
    for i in range(n):
        arr.append(int(input()))
    return arr


def native_sort(arr):
    return sorted(arr)

def bubble_sort(arr):
    for i in range(len(arr)-1):
        for j in range(1, len(arr) - i):
            if arr[j-1] > arr[j]:
                arr[j-1], arr[j] = arr[j], arr[j-1]
    return arr


if __name__ == ''__main__'':
    n = int(input())
    arr = read_arr(n)
    sorted_arr = bubble_sort(arr)
    #sorted_arr = native_sort(arr)
    print(" ".join(str(n) for n in sorted_arr))'
    , 'PYTHON', 'IN_QUEUE', '2019-04-18 16:07:59', 2);


insert into message (message_text, sender_id, receiver_id, task_id, creation_date, is_watched)
    values ('Добрый день, удачи!', 1, 2, 1000, '2019-04-15 19:19:00', false);

insert into message (message_text, sender_id, receiver_id, task_id, creation_date, is_watched)
    values ('Привет. Не могу понять что к чему...', 2, 1, 1000, '2019-04-16 19:19:00', false);

insert into message (message_text, sender_id, receiver_id, task_id, creation_date, is_watched)
    values ('Читайте, пожалуйста, условие. Внимательно', 1, 2, 1000, '2019-04-16 19:19:00', false);


insert into browser_code (account_id, task_id, solution_text, language_id)
    values(2, 1000, 'print(sum(int(input()) for i in range(2)))', 2);

insert into browser_code (account_id, task_id, solution_text, language_id)
    values(3, 1001,     'import java.util.Scanner;
    public class Main{
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int num1 = sc.nextInt();
            int num2 = sc.nextInt();
            sc.close();
            System.out.println(num1 + num2);
        }
    }', 0);
