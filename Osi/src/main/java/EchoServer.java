import java.io.*;
import java.net.*;

public class EchoServer {
    public static void main(String[] args) {
        int port = 8080; // Можно изменить на любой свободный порт

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен на порту " + port);
            System.out.println("Ожидание подключения клиента...");

            // Принимаем соединение
            Socket clientSocket = serverSocket.accept();
            System.out.println("Новое соединение принято");

            // Потоки ввода/вывода
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Читаем строку от клиента
            String name = in.readLine();
            if (name == null) {
                name = "Anonymous";
            }

            // Выводим на экран имя и порт клиента
            int clientPort = clientSocket.getPort();
            System.out.println("Получено сообщение от клиента: \"" + name + "\" с порта " + clientPort);

            // Отправляем ответ клиенту
            out.println(String.format("Hi %s, your port is %d", name, clientPort));

            // Закрываем соединение
            clientSocket.close();
        } catch (IOException e) {
            System.err.println("Ошибка сервера: " + e.getMessage());
        }
    }
}