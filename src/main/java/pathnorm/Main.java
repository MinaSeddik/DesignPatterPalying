package pathnorm;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Main {


    private static final List<String> INPUTS = Arrays.asList(
            "/foo", "//foo", "foo/", "foo/bar", "foo/bar/../baz", "foo//bar",
            "/home/mina", "/home/mina/", "/home/mina/../../../../etc/passwd",
            "/home/mina/..", "/home/mina/./mydir", "../../../mydir"
    );

    public static void main(final String... args) throws IOException {
        Path path;
        Path path2;

        for (final String input: INPUTS) {
            path = Paths.get("/", input).normalize();

            path2 = Paths.get(input).normalize();


//            System.out.printf("%s -> %s -> %s\n", input, path, path2);
        }

        System.out.println("======================================");

//        for (final String input: INPUTS) {
//            File file = new File(input);
//            System.out.printf("%s -> %s\n", input, file.getCanonicalFile());
//        }

        Path baseDirPath = Paths.get("/foo/bar/baz");
//        Path userPath = Paths.get("../attack");
        Path userPath = Paths.get("../../../../../../../attack");

        Path resolvedPath = baseDirPath.resolve(userPath);
        Path resolvedNormalizedPath = baseDirPath.resolve(userPath).normalize();

        System.out.printf("RESOLVED: %s and %s -> %s\n", baseDirPath, userPath, resolvedPath);
        System.out.printf("RESOLVED-NORMALIZED: %s and %s -> %s\n", baseDirPath, userPath, resolvedNormalizedPath);


        System.out.println(resolvedNormalizedPath.startsWith(baseDirPath));
        resolvedNormalizedPath.toFile();


    }

    public Path resolvePath(final Path baseDirPath, final Path userPath) {
        if (!baseDirPath.isAbsolute()) {
            throw new IllegalArgumentException("Base path must be absolute");
        }

        if (userPath.isAbsolute()) {
            throw new IllegalArgumentException("User path must be relative");
        }

        // Join the two paths together, then normalize so that any ".." elements
        // in the userPath can remove parts of baseDirPath.
        // (e.g. "/foo/bar/baz" + "../attack" -> "/foo/bar/attack")
        final Path resolvedPath = baseDirPath.resolve(userPath).normalize();

        // Make sure the resulting path is still within the required directory.
        // (In the example above, "/foo/bar/attack" is not.)
        if (!resolvedPath.startsWith(baseDirPath)) {
            throw new IllegalArgumentException("User path escapes the base path");
        }

        return resolvedPath;
    }

}
