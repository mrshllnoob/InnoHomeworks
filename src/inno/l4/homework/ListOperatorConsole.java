package inno.l4.homework;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Класс предоставляющий возможность консольного управления
 * считыванием информации из указанного файла в поле-коллекцию
 * класса ListOperator, возможность сериализовать полученный
 * в результате считывания класс и десериализовать его.
 *
 * @author Tsapin Anton
 */
public class ListOperatorConsole {
    /**
     * Позволяет вводить команды в stdin. Управление ListOperator
     * классом осуществляется посредством 3х команд:
     * parse %путь до файла%;
     * serialize %путь до файла, куда следует сериализовать объект%;
     * deserialize %путь до файла, откуда следует десериализовать объект%;
     *
     */
    public static void activateConsole() {
        try(InputStreamReader inputStreamReader = new InputStreamReader(System.in);
                BufferedReader console = new BufferedReader(inputStreamReader);
                Scanner scanner = new Scanner(console)) {
            ListOperator listOperator = null;
            boolean isClosed = false;
            while (!isClosed) {
                System.out.println("Для команды parse корневым путем является\nsrc/inno/l4/homework/\n" +
                                    "Для команд serialize и deserialize корневым путем является\n" +
                                    "src/inno/l4/homework/serialized/\nДля выхода введите exit\n");
                String request = scanner.nextLine();
                String[] splitedRequest = request.replaceAll("\n", "").split(" ");
                System.out.println("Request is: " + request);
                switch (splitedRequest[0]) {
                    case "parse":
                        if (new File("src/inno/l4/homework/" +
                                splitedRequest[1]).exists() == false || splitedRequest.length<2) {
                            System.out.println(splitedRequest[1] + " doesnt exist or wrong request");
                            break;
                        }
                        int i=1;
                        listOperator = new ListOperator(splitedRequest[1]);
                        for (String elem : listOperator.getParsedTextFileList()) {
                            System.out.println(elem + " " + i + " element of parsed collection");
                            i++;
                        }
                        System.out.println("_____________________________\nDocument fully parsed");
                        break;
                    case "serialize":
                        if (listOperator == null) {
                            System.out.println("You should parse some file first, or deserialize");
                            break;
                        }
                        else {
                            listOperator.serializeListOperator(splitedRequest[1]);
                            System.out.println("Object serialized to " + splitedRequest[1]);
                            break;
                        }
                    case "deserialize":
                        if (new File("src/inno/l4/homework/serialized/"
                                + splitedRequest[1]).exists() == false || splitedRequest.length<2) {
                            System.out.println(splitedRequest[1] + " doesnt exist or wrong request");
                            break;
                        }
                        listOperator = ListOperator.deserializeListOperator(splitedRequest[1]);
                        break;
                    case "exit":
                        isClosed = true;
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ListOperatorConsole.activateConsole();
    }

}
