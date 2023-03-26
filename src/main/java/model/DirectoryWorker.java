package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DirectoryWorker {
    public List<FileModel> getList(String path) {
        File userFile = new File(path);
        if(!userFile.exists()){
            userFile.mkdir();
        }
        File dir = new File(path);
        File[] list = dir.listFiles();
        if (list == null || list.length == 0){
            return new ArrayList<>();
        }
        return Stream.of(list)
                .map(file -> new FileModel(
                        file.getName(),
                        file.isDirectory(),
                        file.length(),
                        file.lastModified()
                ))
                .sorted(Comparator.comparing(fileModel -> !fileModel.isDirectory()))
                .collect(Collectors.toList());
    }
}
