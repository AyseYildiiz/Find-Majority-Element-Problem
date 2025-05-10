import java.io.OutputStream;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.IOException;
// Writes the console output to a file
public class WriteOutputFile {
        public static void enable(String fileName) {
            try {
                PrintStream fileOut = new PrintStream(new FileOutputStream(fileName, true)); // append mode
                PrintStream console = System.out;

                PrintStream dual = new PrintStream(new OutputStream() {
                    @Override
                    public void write(int b) throws IOException {
                        console.write(b);
                        fileOut.write(b);
                    }

                    @Override
                    public void flush() throws IOException {
                        console.flush();
                        fileOut.flush();
                    }

                    @Override
                    public void close() throws IOException {
                        console.close();
                        fileOut.close();
                    }
                });

                System.setOut(dual);
                System.setErr(dual);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}

