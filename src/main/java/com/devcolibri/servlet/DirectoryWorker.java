package com.devcolibri.servlet;
import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toList;

public class DirectoryWorker {
    public List<FileModel> getList(String path) {
        return Stream.of(Objects.requireNonNull(new File(path).listFiles()))
                .map(file -> new FileModel(
                        file.getName(),
                        file.isDirectory(),
                        file.length(),
                        file.lastModified()
                ))
                .collect(toList());
    }
}
