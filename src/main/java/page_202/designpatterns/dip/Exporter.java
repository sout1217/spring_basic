package page_202.designpatterns.dip;

import java.util.Collections;
import java.util.List;

class Item {}

interface Repository {
    List<Item> find();
}

interface Generator {
    String generate(List<Item> items);
}

interface Compressor {
    String zip(String path);
}

class JdbcRepository implements Repository {
    @Override
    public List<Item> find() {
        return Collections.emptyList();
    }
}
class CsvGenerator implements Generator {
    @Override
    public String generate(List<Item> items) {
        return "c:/item";
    }
}
class ZipCompressor implements Compressor {
    @Override
    public String zip(String csvFilePath) {
        return "item.zip";
    }
}

public class Exporter {

    private JdbcRepository jdbcRepository;
    private CsvGenerator csvGenerator;
    private ZipCompressor zipCompressor;

    public Exporter(JdbcRepository jdbcRepository, CsvGenerator csvGenerator, ZipCompressor zipCompressor) {
        this.jdbcRepository = jdbcRepository;
        this.csvGenerator = csvGenerator;
        this.zipCompressor = zipCompressor;
    }

    public void export() {
        List<Item> items = jdbcRepository.find();

        if (items.isEmpty()) {
            return;
        }
        String csvFilePath = csvGenerator.generate(items);
        String compressed = zipCompressor.zip(csvFilePath);
    }
}
