package inno.l4.homework;

import java.util.ArrayList;
import java.util.TreeSet;

public class TextGeneratorExecutor {

    public static void main(String[] args) {
        DictionaryCreator dictionaryCreator = new DictionaryCreator();
        TreeSet<String> dictionary = dictionaryCreator.produceDictionaryCollection();
        TextGenerator textGenerator = new TextGenerator(new ArrayList(dictionary), 1000);
        textGenerator.show();
        textGenerator.generateDankTextFiles(textGenerator.getResultsPath(),
                1, textGenerator.getResultFileSize(),
                textGenerator.getDictionary(), textGenerator.getProbabilityDivider());
    }

}
