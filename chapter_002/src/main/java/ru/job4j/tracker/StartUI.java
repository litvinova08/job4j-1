package ru.job4j.tracker;

/**
 * @version $$
 * @since 0.1
 */
public class StartUI {

    private static final String ADD = "0";          // Константа меню для добавления новой заявки.

    private static final String SHOW = "1";         // Константа меню для вывода всех заявок.

    private static final String EDIT = "2";         // Константа меню для редактирования заявки.

    private static final String DELETE = "3";       // Константа меню для удаления заявки.

    private static final String FindByID = "4";     // Константа меню для поиска заявки по заданному Id.

    private static final String FindByName = "5";   // Константа меню для поиска заявки по заданному Name.

    private static final String EXIT = "6";         //Константа для выхода из цикла.

    private final Input input;                      // Получение данных от пользователя.

    private final Tracker tracker;                  // Хранилище заявок.

    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню : ");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (SHOW.equals(answer)) {
                this.findAll();
     //       } else if(EDIT.equals(answer)) {
       //         this.replaceItem();
            } else if(DELETE.equals(answer)) {
                this.deleteItem();
           // } else  if(FindByID.equals(answer)) {
           //     this.findById();
   //         } else if(FindByName.equals(answer)) {
   //             this.tracker.findByName(String key);
            } else if (EXIT.equals(answer)) {
                exit = true;
            }
        }
    }

    /**
     * Метод реализует добавленяи новый заявки в хранилище.
     */
    private void createItem() {
        System.out.println("------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки :");
        String desc = this.input.ask("Введите описание заявки :");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        System.out.println("------------ Новая заявка с getId : " + item.getId() + "-----------");
    }

    private void findAll() {
        System.out.println("------------ Список всех заявок");
        this.tracker.findAll();
        for (Item item : tracker.findAll()) {
            System.out.println(item.getName());
        }
    }

  /*  private void replaceItem() {
        System.out.println("------------ Редактирование заявки");
        String id = this.input.ask("Введите id : ");
        this.tracker.replace(id, this.item);
        System.out.println("------------ Заявка после редактирования : " + item.getId() + "---------");
    }*/

    private void deleteItem() {
        System.out.println("------------ Удаление заявки");
        String id = this.input.ask("Введите id : ");
        if(tracker.delete(id)) {
            System.out.println("Заявка удалена");
        } else System.out.println("Нет такой заявки");
    }

   /* private void findByNameItem() {
        System.out.println("------------ Поиск заявки по имени: ");
    }

    String name = this.input.ask("Введите имя : ");
  this.tracker.findByName(this.name);
  System.out.println("------------ Найденная заявка : "+item.getName()+"---------")

    private void findByIdItem() {
        System.out.println("------------ Поиск заявки по Id: ");
    }

    String name = this.input.ask("Введите Id : ");
  this.tracker.findByName(id);
  System.out.println("------------ Найденная заявка : "+item.getName()+"---------")*/


    private void showMenu() {
        System.out.println("Меню.");
        System.out.println("0. Добавить новую заявку.");
        System.out.println("1. Вывести все заявки.");
        System.out.println("2. Редактировать заявку.");
        System.out.println("3. Удалить заявку.");
        System.out.println("4. Найти заявку по Id.");
        System.out.println("5. Найти заявку по имени.");
        System.out.println("6. Выход.");
    }

    /**
     * Запуск программы.
     * */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}