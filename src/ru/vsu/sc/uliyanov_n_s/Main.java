package ru.vsu.sc.uliyanov_n_s;

import java.io.PrintStream;
import java.util.Locale;
import ru.vsu.sc.uliyanov_n_s.utils.ArrayUtils;

public class Main {
    public static class CmdParams {
        public String inputFile;
        public String outputFile;
        public int rawNum;
        public boolean error;
        public boolean help;
        public boolean window;
    }

    public static CmdParams parseArgs(String[] args) {
        CmdParams params = new CmdParams();
        if (args.length > 0) {
            if (args[0].equals("--help")) {
                params.help = true;
                return params;
            }
            if (args[0].equals("--window")) {
                params.window = true;
                return params;
            }
            if (args.length < 2) {
                params.help = true;
                params.error = true;
                return params;
            }

            params.rawNum = Integer.parseInt(args[0]);
            params.inputFile = args[1];
            if (args.length > 2) {
                params.outputFile = args[2];
                return params;
            } else
                params.outputFile = null;


        } else {
            params.help = true;
            params.error = true;
        }
        return params;
    }

    public static void winMain() throws Exception {
        Locale.setDefault(Locale.ROOT);

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameMain().setVisible(true);
            }
        });
    }

    public static void main(String[] args) throws Exception {
        CmdParams params = parseArgs(args);
        ArrayTransform arrayTransform = new ArrayTransform();
        if (params.help) {
            PrintStream out = params.error ? System.err : System.out;
            out.println("Usage:");
            out.println("  <cmd> args <rawNum> <input-file> (<output-file>)");
            out.println("  <rawNum>  // integer number of raw");
            out.println("  <cmd> --help");
            out.println("  <cmd> --window  // show window");
            System.exit(params.error ? 1 : 0);
        }

        if (params.window) {
            winMain();
        } else {
            int[][] arr = ArrayUtils.readIntArray2FromFile(params.inputFile);
            if (arr == null) {
                System.err.printf("Can't read array from \"%s\"%n", params.inputFile);
                System.exit(2);
            } else if (params.rawNum > arr.length - 1) {
                System.err.print("Incorrect number of raw");
                System.exit(2);
            }

            arrayTransform.transformArray(arr, params.rawNum);

            if (params.outputFile != null) {
                ArrayUtils.writeArrayToFile(params.outputFile, ArrayUtils.toObjectArray2(arr));
            } else {
                ArrayUtils.printArrayInConsole(arr);
            }
        }
    }
}
