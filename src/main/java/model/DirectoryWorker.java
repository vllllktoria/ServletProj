package model;

import model.FileModel;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DirectoryWorker {

    public static String root = "C:\\\\javaUsers";
    public List<FileModel> getList(String path) {
        if (!path.contains(root)){
            path = root + path;
        }
        File file1 = new File(path);
        if(!file1.exists()){
            file1.mkdir();
        }
        File dir = new File(path);
        File[] list = dir.listFiles();
        if (list == null || list.length == 0){
            return new ArrayList<FileModel>();
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
