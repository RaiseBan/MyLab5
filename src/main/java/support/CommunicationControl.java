package support;

import data.*;
import exceptions.EmptyInputException;
import exceptions.InputException;
import exceptions.WrongArgumentsException;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The `CommunicationControl` class is responsible for managing communication with the user, taking user input, and setting the values of various attributes.
 */
public class CommunicationControl {

    private boolean sriptMode = false;
    public Scanner scanner;

    /**
     * Constructs a new `CommunicationControl` object with a specified `Scanner` for user input.
     *
     * @param scanner the `Scanner` to use for user input
     */
    public CommunicationControl(Scanner scanner) {
        this.scanner = scanner;
    }


    /**
     * Returns true if the given string contains only digits or letters.
     *
     * @param str        the string to check
     * @param onlyDigits true if the string should contain only digits; false if it should contain only letters
     * @return true if the string contains only digits or letters; false otherwise
     */
    public static boolean containsOnlyDigitsOrLetters(String str, boolean onlyDigits) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        String regex = onlyDigits ? "^\\d+$" : "^[a-zA-Z]+$";
        return str.matches(regex);
    }
    public void changeScanner(InputStream inputStream){
        this.scanner = new Scanner(inputStream);
    }

    /**
     * Prompts the user to enter a name, validates the input, and returns the name as a string.
     *
     * @return the name entered by the user
     */
    public String setName() {
        String name;
        while (true) {
            try {
                Console.writeln("введите имя");
                name = scanner.nextLine().trim();
                if (name.equals("")) throw new EmptyInputException("имя не может быть пустым");
                if (!containsOnlyDigitsOrLetters(name, false)) throw new InputException();
                return name;
            } catch (EmptyInputException | InputException e) {
                Console.err("Имя не корректно!");
            }
        }

    }

    /**
     * Prompts the user to enter a height, validates the input, and returns the height as a long.
     *
     * @return the height entered by the user
     */
    private long setHeight() {
        while (true) {
            try {
                System.out.print("Введите рост: ");
                String line = scanner.nextLine();
                long height = Long.parseLong(line);
                if (height <= 0) {
                    throw new WrongArgumentsException("Высота не может быть меньше или равна нулю");
                }
                return height;
            } catch (Exception e) {
                System.out.println("Некорректный ввод. Попробуйте еще раз.");
            }
        }
    }

    /**
     * Prompts the user to enter their passport ID and validates the input.
     *
     * @return The passport ID entered by the user.
     * @throws EmptyInputException     If the user enters an empty string.
     * @throws WrongArgumentsException If the user enters a non-numeric string.
     */
    private String setPassportID() {
        while (true) {
            try {
                System.out.print("Введите номер паспорта: ");
                String passportID = scanner.nextLine().trim();
                if (passportID.isEmpty()) {
                    throw new EmptyInputException("Номер паспорта не может быть пустым");
                }
                if (!passportID.matches("\\d+")) {
                    throw new WrongArgumentsException("Номер паспорта должен содержать только цифры");
                }
                return passportID;
            } catch (Exception e) {
                System.out.println("Некорректный ввод. Попробуйте еще раз.");
            }
        }
    }

    /**
     * Prompts the user to enter their date of birth and validates the input.
     *
     * @return The date of birth entered by the user.
     * @throws WrongArgumentsException If the user enters a date that is in the future.
     */
    private LocalDateTime setBirthday() {
        while (true) {
            try {
                System.out.print("Введите дату рождения в формате ГГГГ-ММ-ДД: ");
                String birthdayStr = scanner.nextLine().trim();
                if (birthdayStr.isEmpty()) throw new IllegalArgumentException();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", new Locale("ru", "Ru"));
                LocalDateTime bd = LocalDate.parse(birthdayStr, formatter).atStartOfDay();
                if (bd.isAfter(LocalDate.now().atStartOfDay())) throw new WrongArgumentsException();
                return bd;
            }catch (WrongArgumentsException e){
                Console.err(e.getMessage());
                return null;
            }catch (DateTimeParseException e){
                Console.err("неверный формат даты!");
            } catch (Exception e) {
                System.out.println("Некорректный ввод. Попробуйте еще раз.");
            }
        }
    }

    /**
     * Prompts the user to enter the details of a person and creates a Person object.
     *
     * @return A Person object with the details entered by the user.
     * @throws IllegalArgumentException If the user enters invalid data for any field.
     */
    public Person setPerson() {
        try {
            LocalDateTime bDay = setBirthday();
            long height = setHeight();
            String passportID = setPassportID();
            return new Person(bDay, height, passportID, setLocation());
        } catch (Exception e) {
            Console.err("Проверьте корректность данных");
            return null;
        }
    }

    /**
     * Prompts the user to enter the X coordinate of a location and validates the input.
     *
     * @return The X coordinate entered by the user.
     * @throws EmptyInputException   If the user enters an empty string.
     * @throws NumberFormatException If the user enters a non-integer string.
     */
    public Integer setCoodrinateX() {
        int coordX;
        String line;
        while (true) {
            try {
                Console.writeln("Введите координату X: ");
                line = scanner.nextLine().trim();
                if (line.equals("")) throw new EmptyInputException("не может быть пустым");
                coordX = Integer.parseInt(line);
                break;
            } catch (EmptyInputException e) {
                Console.err(e.getMessage());
            } catch (NumberFormatException e) {
                Console.err("должно быть числом а еще и целым !!!");
            }

        }
        return coordX;
    }

    /**
     * Prompts the user to enter the Y coordinate of a location and validates the input.
     *
     * @return The Y coordinate entered by the user.
     * @throws EmptyInputException   If the user enters an empty string.
     * @throws NumberFormatException If the user enters a non-integer string.
     */
    public Integer setCoodrinateY() {
        Integer coordY;
        String line;
        while (true) {
            try {
                Console.writeln("Введите координату Y: ");
                line = scanner.nextLine().trim();
                if (line.equals("")) throw new EmptyInputException();
                coordY = Integer.parseInt(line);
                break;
            } catch (Exception e) {
                Console.err("некорректные данные, попробуйте еще раз");
            }

        }
        return coordY;
    }

    /**
     * Prompts the user to enter a salary and validates the input.
     *
     * @return The salary entered by the user.
     * @throws EmptyInputException   If the user enters an empty string.
     * @throws NumberFormatException If the user enters a non-numeric string.
     */
    public Double setSalary() {
        String line;
        double salary;
        while (true) {
            try {
                Console.writeln("введите з/п: ");
                line = scanner.nextLine().trim();
                if (line.equals("")) throw new EmptyInputException();
                salary = Double.parseDouble(line);
                break;
            } catch (Exception e) {
                Console.err("некорректные данные, попробуйте еще раз");
            }

        }
        return salary;
    }

    /**
     * Prompts the user to input coordinates and returns an instance of the Coordinates class.
     *
     * @return an instance of the Coordinates class representing the user's inputted coordinates.
     */
    public Coordinates setCoordinates() {
        Integer x;
        int y;
        x = setCoodrinateX();
        y = setCoodrinateY();

        return new Coordinates(x, y);
    }

    /**
     * Prompts the user to input location information and returns an instance of the Location class.
     *
     * @return an instance of the Location class representing the user's inputted location information.
     */
    public Location setLocation() {
        String name;
        String line;
        float x;
        long y;
        int z;

        while (true) {
            try {

                Console.writeln("Введите координаты x (Float)");
                line = scanner.nextLine().trim();
                if (line.equals("")) throw new EmptyInputException();
                x = Float.parseFloat(line);

                Console.writeln("Введите координаты y (Long)");
                line = scanner.nextLine().trim();
                if (line.equals("")) throw new EmptyInputException();
                y = Long.parseLong(line);

                Console.writeln("Введите координаты z (Integer)");
                line = scanner.nextLine().trim();
                if (line.equals("")) throw new EmptyInputException();
                z = Integer.parseInt(line);

                Console.writeln("Название локации: ");
                name = scanner.nextLine().trim();
                if (name.equals("")) throw new EmptyInputException();

                return new Location(x,y,z,name);
            } catch (EmptyInputException e) {
                //err - ввели пустоту
            } catch (NumberFormatException e) {
                //Console.err должно быть числом !!1
            }
        }
    }

    /**
     * Prompts the user to choose a position and returns the selected Position enum.
     *
     * @return a Position enum representing the user's selected position.
     */
    public Position choosePosition() {
        Position position;
        String setPos;
        while (true) {
            try {

                Console.writeln(Position.nameList());
                Console.writeln("выбирайте");
                setPos = scanner.nextLine().trim();
                position = Position.valueOf(setPos.toUpperCase());
                break;
            } catch (Exception e) {
                Console.err("неверные данные");
            }
        }


        return position;
    }

    /**
     * Prompts the user to choose a status and returns the selected Status enum.
     *
     * @return a Status enum representing the user's selected status.
     */
    public Status chooseStatus() {
        Status status;
        String setStat;
        while (true) {
            try {

                Console.writeln(Status.nameList());
                Console.writeln("какую штуку выберите");
                setStat = scanner.nextLine().trim();
                status = Status.valueOf(setStat.toUpperCase());
                break;
            } catch (NoSuchElementException e) {
                Console.err("нет такого элемента");
            }catch (Exception e){
                Console.err("ошибка данных");
            }
        }
        return status;
    }

    /**
     * Prompts the user to confirm a choice and returns a boolean indicating whether the user confirmed.
     *
     * @return a boolean indicating whether the user confirmed the choice.
     */
    public boolean confirm() {
        String line;
        Console.writeln("y/n");
        line = scanner.nextLine().trim();
        return line.equals("y");
    }

    /**
     * Prompts the user to input any additional information and returns the user's input as a String.
     *
     * @return a String representing any additional information inputted by the user.
     */

    public String setEnotherInfo() {
        String line;
        line = scanner.nextLine().trim();
        return line;
    }
}
