import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> CurrentTasks = new ArrayList<>();
        ArrayList<String> CompletedTasks = new ArrayList<>();
        byte Answer;
        String TaskName;
        int TaskID;
        boolean FileExist = false, ReadSuccess = false;

        System.out.println("Это программа ведения задач.");
        System.out.print("Поиск сохраненных задач...");

        //чтение массивов задач из файлов
        try(BufferedReader CurrentTasksFileReader = new BufferedReader(new FileReader("CurrentTasks.txt"))){
            FileExist = true;
            while((TaskName=CurrentTasksFileReader.readLine())!=null){
                CurrentTasks.add(TaskName);
            }
            ReadSuccess = true;
        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
        }catch (IOException exception){
            System.out.println(exception.getMessage());
        }

        try(BufferedReader CompletedTasksFileReader = new BufferedReader(new FileReader("CompletedTasks.txt"))){
            FileExist = true;
            while((TaskName=CompletedTasksFileReader.readLine())!=null){
                CompletedTasks.add(TaskName);
            }
            ReadSuccess = true;
        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
        }catch (IOException exception){
            System.out.println(exception.getMessage());
        }

        if(FileExist){
            if(ReadSuccess){
                System.out.println("успешно");
            }else{
                System.out.println("чтение из файла выполнить не удалось");
            }}
        else{
            System.out.println("не найдены");
            }

        // обработка задач
        System.out.println("Выберите действие:");
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("1 - Текущие задачи");
            System.out.println("2 - Выполненные задачи");
            System.out.println("3 - сохранить задачи и выйти из программы");

            Answer = sc.nextByte();
            sc.nextLine();

            switch (Answer) {
                case 1 -> {
                    if (CurrentTasks.size() == 0) {
                        System.out.println("Список задач пуст");
                    }
                    TaskID = 1;
                    for (Object Task : CurrentTasks) {
                        System.out.printf("%d - %s\n", TaskID, Task.toString());
                        TaskID++;
                    }
                    System.out.println();
                    do {
                        System.out.println("1 - добавить текущую задачу");
                        System.out.println("2 - выполнить текущую задачу");
                        System.out.println("3 - удалить текущую задачу");
                        System.out.println("4 - показать текущие задачи");
                        System.out.println("5 - выйти из текущих задач");
                        Answer = sc.nextByte();
                        sc.nextLine();

                        switch (Answer) {
                            case 1:
                                System.out.println("Введите текст новой задачи: ");
                                TaskName = sc.nextLine();
                                if (TaskName.equals("")) {
                                    System.out.println("Задача с пустым содержанием не может быть добавлена.");
                                    break;
                                }
                                CurrentTasks.add(TaskName);
                                System.out.printf("Задача добавлена под номером %d.\n", CurrentTasks.size());
                                break;
                            case 2:
                                System.out.println("Введите номер выполненной задачи");
                                TaskID = sc.nextInt();
                                sc.nextLine();
                                if (TaskID <= 0 || TaskID > CurrentTasks.size()) {
                                    System.out.println("Нет задачи с таким номером.");
                                    break;
                                }
                                CompletedTasks.add(CurrentTasks.remove(TaskID - 1));
                                System.out.println("Задача перемещена в список выполненных.");
                                break;
                            case 3:
                                System.out.println("Введите номер удаляемой задачи");
                                TaskID = sc.nextInt();
                                sc.nextLine();
                                if (TaskID <= 0 || TaskID > CurrentTasks.size()) {
                                    System.out.println("Нет задачи с таким номером.");
                                    break;
                                }
                                CurrentTasks.remove(TaskID - 1);
                                System.out.println("Задача удалена.");
                                break;
                            case 4:
                                if (CurrentTasks.size() == 0) {
                                    System.out.println("Список задач пуст");
                                    break;
                                }
                                TaskID = 1;
                                for (Object Task : CurrentTasks) {
                                    System.out.printf("%d - %s\n", TaskID, Task.toString());
                                    TaskID++;
                                }
                                break;
                            case 5:
                                break;
                            default:
                                System.out.println("Действие с таким кодом не определено.");
                                break;
                        }
                        System.out.println();
                    }while(Answer != 5); //цикл меню текущих задач
                }
                case 2 -> {
                    if (CompletedTasks.size() == 0) {
                        System.out.println("Список задач пуст");
                        break;
                    }
                    TaskID = 1;
                    for (Object Task : CompletedTasks) {
                        System.out.printf("%d - %s\n", TaskID, Task.toString());
                        TaskID++;
                    }
                    System.out.println();
                    do {
                        System.out.println("1 - отменить выполнение задачи");
                        System.out.println("2 - удалить выполненную задачу");
                        System.out.println("3 - очистить выполненные задачи");
                        System.out.println("4 - показать выполненные задачи");
                        System.out.println("5 - выйти из выполненных задач");
                        Answer = sc.nextByte();
                        sc.nextLine();

                        switch (Answer) {
                            case 1:
                                System.out.println("Введите номер задачи для отмены выполнения");
                                TaskID = sc.nextInt();
                                sc.nextLine();
                                if (TaskID <= 0 || TaskID > CompletedTasks.size()) {
                                    System.out.println("Нет задачи с таким номером.");
                                    break;
                                }
                                CurrentTasks.add(CompletedTasks.remove(TaskID - 1));
                                System.out.println("Задача перемещена в список текущих.");
                                break;
                            case 2:
                                System.out.println("Введите номер удаляемой задачи");
                                TaskID = sc.nextInt();
                                sc.nextLine();
                                if (TaskID <= 0 || TaskID > CompletedTasks.size()) {
                                    System.out.println("Нет задачи с таким номером.");
                                    break;
                                }
                                CompletedTasks.remove(TaskID - 1);
                                System.out.println("Задача удалена.");
                                break;
                            case 3:
                                CompletedTasks.clear();
                                System.out.println("Задачи очищены.");
                                break;
                            case 4:
                                if (CompletedTasks.size() == 0) {
                                    System.out.println("Список задач пуст");
                                    break;
                                }
                                TaskID = 1;
                                for (Object Task : CompletedTasks) {
                                    System.out.printf("%d - %s\n", TaskID, Task.toString());
                                    TaskID++;
                                }
                                break;
                            case 5:
                                break;
                            default:
                                System.out.println("Действие с таким кодом не определено.");
                                break;
                        }
                        System.out.println();
                    }while(Answer != 5); // цикл меню выполненных задач
                }
                case 3 -> {
                    //запись массивов задач в файлы
                    if (CurrentTasks.size() != 0) {
                        try (BufferedWriter CurrentTasksFileWriter = new BufferedWriter(new FileWriter("CurrentTasks.txt", false))) {
                            for (Object Task : CurrentTasks) {
                                CurrentTasksFileWriter.write(Task.toString() + "\n");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (CompletedTasks.size() != 0) {
                        try (BufferedWriter CompletedTasksFileWriter = new BufferedWriter(new FileWriter("CompletedTasks.txt", false))) {
                            for (Object Task : CompletedTasks) {
                                CompletedTasksFileWriter.write(Task.toString() + "\n");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                default -> System.out.println("Действие с таким кодом не определено.");
            }

         }while(Answer != 3);
        }
    }
