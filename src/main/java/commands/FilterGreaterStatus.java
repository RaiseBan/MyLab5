package commands;

import data.Worker;
import exceptions.WrongArgumentsException;
import support.CollectionControl;
import support.CommunicationControl;
import support.Console;

public class FilterGreaterStatus extends AbstractCommand{
    CollectionControl collectionControl;
    CommunicationControl communicationControl;
    public FilterGreaterStatus(CollectionControl collectionControl, CommunicationControl communicationControl) {
        super("filter_greater_than_status", "вывести элементы, значение поля status которых больше заданного");
        this.collectionControl = collectionControl;
        this.communicationControl = communicationControl;


    }

    @Override
    public void execute(String argument) {
        String line;

        try {
            if (!argument.isEmpty()) throw new WrongArgumentsException();
            Console.writeln("Введите значение с которым вы хотите сравнивать");
            line = communicationControl.setEnotherInfo();

            for (Worker worker : collectionControl.filterGreaterThanStatus(line)){
                Console.writeln(worker.toString());
            }
        }catch (IllegalArgumentException e){
            Console.err("Не является элементом Status");
        }catch (WrongArgumentsException e){
            Console.err("Неверное кол-во аргементов...");
        }
    }
}
