import java.io.*;
import java.net.*;

public class EchoClient {
    public static void main(String[] args) {
        String host = "localhost"; // или "127.0.0.1"
        int port = 8080;

        try (Socket socket = new Socket(host, port)) {
            // Потоки ввода/вывода
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Отправляем один символ (или строку — по заданию "отредактируйте ему один символ",
            // но обычно отправляют строку; если нужно именно один символ — см. ниже)
            String message = "A"; // Можно заменить на любой один символ, например "X"
            out.println(message); // println добавляет \n, что важно для readLine() на сервере

            // Читаем ответ от сервера
            String response = in.readLine();
            System.out.println("Ответ от сервера: " + response);

        } catch (IOException e) {
            System.err.println("Ошибка клиента: " + e.getMessage());
        }
    }
}