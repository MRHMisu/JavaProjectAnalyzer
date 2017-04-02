package iit.du.ac.bd.misubeimp.example;

import java.io.File;
import java.io.IOException;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.stmt.Statement;
import com.google.common.base.Strings;

import iit.du.ac.bd.misubeimp.support.DirectoryExplorer;
import iit.du.ac.bd.misubeimp.support.NodeIterator;

public class StatementsLinesExample {

    public static void statementsByLine(File projectDir) {
        new DirectoryExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {
            System.out.println(path);
            System.out.println(Strings.repeat("=", path.length()));
            try {
                new NodeIterator(new NodeIterator.NodeHandler() {
                    @Override
                    public boolean handle(Node node) {
                        if (node instanceof Statement) {
                            System.out.println(" [Lines " + node.getRange()+" ] " + node);
                            return false;
                        } else {
                            return true;
                        }
                    }
                }).explore(JavaParser.parse(file));
                System.out.println(); // empty line
            } catch (IOException e) {
                new RuntimeException(e);
            }
        }).explore(projectDir);
    }

    public static void main(String[] args) {
        File projectDir = new File("D:\\Masters Lab\\ParsingJavaProject");
        statementsByLine(projectDir);
    }
}
